/*******************************************************************************
 * Copyright 2010 Maxime Lévesque
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************** */
package org.squeryl.dsl.ast

import org.squeryl.internals._
import org.squeryl.dsl.{QueryYield, AbstractQuery}
import scala.collection.mutable.ListBuffer

class QueryExpressionNode[R](_query: AbstractQuery[R],
                             _queryYield:QueryYield[R],
                             val subQueries: Iterable[QueryableExpressionNode],
                             val views: Iterable[ViewExpressionNode[_]])
  extends QueryExpressionElements
    with QueryableExpressionNode {

  def tableExpressions: Iterable[QueryableExpressionNode] = 
    List(views.filter(v => ! v.inhibited),
         subQueries.filter(v => ! v.inhibited)).flatten

  def isJoinForm = _queryYield.joinExpressions != Nil

  val (whereClause, havingClause, groupByClause, orderByClause) =
     _queryYield.queryElements

  private var _selectList: Iterable[SelectElement] = Iterable.empty

  private var _sample: Option[AnyRef] = None

  private def _isPrimitiveType(o: AnyRef) =
    o.getClass.isPrimitive

  def isUseableAsSubquery: Boolean =
    _sample match {
      case None => throw new IllegalStateException("method cannot be called before initialization")
      case Some(p:Product) =>
        if(p.getClass.getName.startsWith("scala.Tuple")) {
          val z = (for(i <- 0 to (p.productArity - 1)) yield p.productElement(i))
          ! z.exists(o => _isPrimitiveType(o.asInstanceOf[AnyRef]))
        }
        else
          true
      case Some(a:AnyRef) => ! _isPrimitiveType(a)
    }


  def sample:AnyRef = _sample.get

  def owns(aSample: AnyRef) = 
    _sample != None && _sample.get.eq(aSample)
  
  def getOrCreateSelectElement(fmd: FieldMetaData, forScope: QueryExpressionElements) = throw new UnsupportedOperationException("implement me")

  override def toString = {
    val sb = new StringBuffer
    sb.append('QueryExpressionNode + "[")
    if(_query.isRoot)
      sb.append("root:")
    sb.append(id)
    sb.append("]")
    sb.append(":rsm="+_query.resultSetMapper)
    sb.toString
  }

  override def children = {
    val lb = ListBuffer[ExpressionNode]();
    lb ++= selectList
    lb ++= views
    lb ++= subQueries
    lb ++= tableExpressions.filter(e=> e.joinExpression != None).map(_.joinExpression.get)  
    lb ++= whereClause
    lb ++= groupByClause
    lb ++= havingClause
    lb ++= orderByClause      
    lb.toList
  }

  def isChild(q: QueryableExpressionNode):Boolean =
    views.find(n => n == q) != None

  def selectDistinct = _query.selectDistinct

  def isForUpdate = _query.isForUpdate

  def page = _query.page

  def alias = "q" + uniqueId.get

  def getOrCreateAllSelectElements(forScope: QueryExpressionElements): Iterable[SelectElement] = {
    _selectList.map(se => new ExportedSelectElement(se))
  }

  def setOutExpressionNodesAndSample(sl: Iterable[SelectElement], s: AnyRef) = {
    _selectList = sl
    _sample = Some(s)

    if(_query.isRoot) {

      var jdbcIndex = 1
      for(oen <- selectList) {
        oen.prepareMapper(jdbcIndex)
        jdbcIndex += 1
      }

      var idGen = 0
      visitDescendants((node,parent,i) => {
        node.parent = parent

        if(node.isInstanceOf[UniqueIdInAliaseRequired]) {
          val nxn = node.asInstanceOf[UniqueIdInAliaseRequired]
          nxn.uniqueId = Some(idGen)
          idGen += 1
        }
      })
    }
  }

  def selectList: Iterable[SelectElement] = _selectList

  def doWrite(sw: StatementWriter) = {
    val isNotRoot = parent != None

    if(isNotRoot) {
      sw.write("(")
      sw.indent(1)
    }
    sw.databaseAdapter.writeQuery(this, sw)
    if(isNotRoot) {
      sw.unindent(1)
      sw.write(") ")
    }
  }
}

