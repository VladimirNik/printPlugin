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

trait QueryExpressionElements extends ExpressionNode {

  var inhibitAliasOnSelectElementReference = false

  def isChild(q: QueryableExpressionNode): Boolean

  def alias: String

  def selectDistinct: Boolean

  def isForUpdate: Boolean

  def page: Option[(Int,Int)]

  def views: Iterable[QueryableExpressionNode]

  def isJoinForm: Boolean

  def subQueries: Iterable[QueryableExpressionNode]

  def tableExpressions: Iterable[QueryableExpressionNode]

  def selectList: Iterable[SelectElement]

  def whereClause: Option[ExpressionNode]

  def hasUnInhibitedWhereClause =
    whereClause match {
      case None => false
      case Some(e:ExpressionNode) =>
        if (e.inhibited) false
        else if (e.children.size == 0) true  // for constant
        else (e.children.exists(! _.inhibited))
    }

  def havingClause: Option[ExpressionNode]

  def groupByClause: Iterable[ExpressionNode]

  def orderByClause: Iterable[ExpressionNode]
}
