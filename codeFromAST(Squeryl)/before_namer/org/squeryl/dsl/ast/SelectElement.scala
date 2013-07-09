package org.squeryl.dsl.ast {
  import collection.mutable.ArrayBuffer;
  import org.squeryl.internals._;
  import java.sql.ResultSet;
  import org.squeryl.Session;
  import org.squeryl.dsl.TypedExpression;
  import scala.annotation.tailrec;
  abstract trait SelectElement extends ExpressionNode { outer => 
    def $init$() = {
      ()
    };
    def origin: QueryableExpressionNode;
    def parentQueryable = parent.get.asInstanceOf[QueryableExpressionNode];
    def resultSetMapper: ResultSetMapper;
    def alias: String;
    def aliasSegment: String = alias;
    def actualSelectElement: SelectElement = this;
    def inhibitAliasOnSelectElementReference: Boolean = {
      def shouldInhibit(e: ExpressionNode): Boolean = e.parent.map(((p) => if (p.isInstanceOf[QueryExpressionElements])
  p.asInstanceOf[QueryExpressionElements].inhibitAliasOnSelectElementReference
else
  shouldInhibit(p))).getOrElse(true);
      shouldInhibit(origin)
    };
    def realTableNamePrefix: Boolean = {
      def parent(e: ExpressionNode): ExpressionNode = e.parent.map(((p) => parent(p))).getOrElse(e);
      val p = parent(origin);
      p.isInstanceOf[UpdateStatement].$bar$bar(p.isInstanceOf[QueryExpressionElements])
    };
    def prepareColumnMapper(index: Int): Unit;
    def prepareMapper(jdbcIndex: Int): Unit;
    override def inhibited = origin.inhibited;
    def isActive = _isActive;
    protected[squeryl] var _isActive = false;
    def expression: ExpressionNode;
    def typeOfExpressionToString: String;
    override def children = List(expression);
    def doWrite(sw: StatementWriter) = {
      expression.write(sw);
      sw.write(" as ");
      sw.databaseAdapter.writeSelectElementAlias(this, sw)
    }
  };
  class TupleSelectElement extends SelectElement {
    <paramaccessor> val origin: QueryExpressionNode[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> val expression: ExpressionNode = _;
    <paramaccessor> private[this] val indexInTuple: Int = _;
    <paramaccessor> private[this] val isGroupTuple: Boolean = _;
    def <init>(origin: QueryExpressionNode[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, expression: ExpressionNode, indexInTuple: Int, isGroupTuple: Boolean) = {
      super.<init>();
      ()
    };
    def resultSetMapper: ResultSetMapper = throw new UnsupportedOperationException("refactor me");
    def alias = if (isGroupTuple)
      "g".$plus(indexInTuple)
    else
      "c".$plus(indexInTuple);
    var columnToTupleMapper: Option[ColumnToTupleMapper] = None;
    def prepareColumnMapper(index: Int) = ();
    def typeOfExpressionToString: String = if (columnToTupleMapper.$eq$eq(None))
      "unknown"
    else
      columnToTupleMapper.get.typeOfExpressionToString(indexInTuple);
    override def prepareMapper(jdbcIndex: Int) = if (columnToTupleMapper.$bang$eq(None))
      columnToTupleMapper.get.activate(indexInTuple, jdbcIndex)
    else
      ();
    override def toString = scala.Symbol("TupleSelectElement").$plus(":").$plus(indexInTuple).$plus(":").$plus(writeToString)
  };
  class FieldSelectElement extends SelectElement with UniqueIdInAliaseRequired {
    <paramaccessor> val origin: ViewExpressionNode[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> val fieldMetaData: FieldMetaData = _;
    <paramaccessor> val resultSetMapper: ResultSetMapper = _;
    def <init>(origin: ViewExpressionNode[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }, fieldMetaData: FieldMetaData, resultSetMapper: ResultSetMapper) = {
      super.<init>();
      ()
    };
    def alias = if (inhibitAliasOnSelectElementReference)
      if (realTableNamePrefix)
        origin.view.name.$plus(".").$plus(fieldMetaData.columnName)
      else
        fieldMetaData.columnName
    else
      origin.alias.$plus(".").$plus(fieldMetaData.columnName);
    override def aliasSegment: String = Session.currentSession.databaseAdapter.fieldAlias(origin, this);
    val expression = {
      final class $anon extends ExpressionNode {
        def <init>() = {
          super.<init>();
          ()
        };
        def doWrite(sw: StatementWriter) = sw.write(sw.quoteName(alias))
      };
      new $anon()
    };
    def prepareColumnMapper(index: Int) = columnMapper = Some(new ColumnToFieldMapper(index, fieldMetaData, this));
    private var columnMapper: Option[ColumnToFieldMapper] = None;
    def prepareMapper(jdbcIndex: Int) = if (columnMapper.$bang$eq(None))
      {
        resultSetMapper.addColumnMapper(columnMapper.get);
        resultSetMapper.isActive = true;
        _isActive = true
      }
    else
      ();
    def typeOfExpressionToString = fieldMetaData.displayType;
    override def toString = scala.Symbol("FieldSelectElement").$plus(":").$plus(Utils.failSafeString(alias, fieldMetaData.nameOfProperty))
  };
  class ValueSelectElement extends SelectElement with UniqueIdInAliaseRequired {
    <paramaccessor> val expression: ExpressionNode = _;
    <paramaccessor> val resultSetMapper: ResultSetMapper = _;
    <paramaccessor> private[this] val mapper: OutMapper[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> val origin: QueryExpressionNode[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(expression: ExpressionNode, resultSetMapper: ResultSetMapper, mapper: OutMapper[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }, origin: QueryExpressionNode[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>();
      ()
    };
    def alias = "v".$plus(uniqueId.get);
    var yieldPusher: Option[YieldValuePusher] = None;
    def prepareColumnMapper(index: Int) = yieldPusher = Some(new YieldValuePusher(index, this, mapper));
    def typeOfExpressionToString = if (yieldPusher.$eq$eq(None))
      "unknown"
    else
      yieldPusher.get.selectElement.typeOfExpressionToString;
    override def prepareMapper(jdbcIndex: Int) = if (yieldPusher.$bang$eq(None))
      {
        resultSetMapper.addYieldValuePusher(yieldPusher.get);
        resultSetMapper.isActive = true;
        _isActive = true
      }
    else
      ();
    override def toString = scala.Symbol("ValueSelectElement").$plus(":").$plus(expression.writeToString)
  };
  class SelectElementReference[A >: _root_.scala.Nothing <: _root_.scala.Any, T >: _root_.scala.Nothing <: _root_.scala.Any] extends TypedExpression[A, T] {
    <paramaccessor> val selectElement: SelectElement = _;
    <paramaccessor> val mapper: OutMapper[A] = _;
    def <init>(selectElement: SelectElement, mapper: OutMapper[A]) = {
      super.<init>();
      ()
    };
    override def toString = scala.Symbol("SelectElementReference").$plus(":").$plus(Utils.failSafeString(delegateAtUseSite.alias)).$plus(":").$plus(selectElement.typeOfExpressionToString).$plus(inhibitedFlagForAstDump);
    override def inhibited = selectElement.inhibited;
    private def _useSite: QueryExpressionNode[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    } = {
      def findQueryExpressionNode(e: ExpressionNode): QueryExpressionNode[_$6] forSome { 
        <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
      } = e match {
        case (qe @ (_: QueryExpressionNode[(_ @ <empty>)])) => qe
        case _ => e.parent match {
          case Some((e_ @ _)) => findQueryExpressionNode(e_)
          case _ => org.squeryl.internals.Utils.throwError("could not determine use site of ".$plus(this))
        }
      };
      findQueryExpressionNode(this)
    };
    lazy val delegateAtUseSite = if (selectElement.parent.$eq$eq(None))
      selectElement
    else
      {
        val us = this._useSite;
        if (selectElement.parentQueryable.$eq$eq(us))
          selectElement
        else
          {
            val ese = new ExportedSelectElement(this.selectElement);
            ese.parent = Some(us);
            ese
          }
      };
    override def doWrite(sw: StatementWriter) = sw.write(sw.quoteName(delegateAtUseSite.alias))
  };
  class ExportedSelectElement extends SelectElement {
    <paramaccessor> val selectElement: SelectElement = _;
    def <init>(selectElement: SelectElement) = {
      super.<init>();
      ()
    };
    def resultSetMapper = selectElement.resultSetMapper;
    override def inhibited = selectElement.inhibited;
    override def prepareMapper(jdbcIndex: Int) = selectElement.prepareMapper(jdbcIndex);
    def prepareColumnMapper(index: Int) = selectElement.prepareColumnMapper(index);
    def typeOfExpressionToString = selectElement.typeOfExpressionToString;
    def origin = selectElement.origin;
    val expression = {
      final class $anon extends ExpressionNode {
        def <init>() = {
          super.<init>();
          ()
        };
        def doWrite(sw: StatementWriter) = sw.write(sw.quoteName(alias))
      };
      new $anon()
    };
    override def toString = scala.Symbol("ExportedSelectElement").$plus(":").$plus(alias).$plus(",(selectElement=").$plus(selectElement).$plus(")");
    def alias: String = if (isDirectOuterReference)
      selectElement.alias
    else
      target.parent.get.asInstanceOf[QueryableExpressionNode].alias.$plus(".").$plus(target.aliasSegment);
    override def aliasSegment: String = if (isDirectOuterReference)
      selectElement.aliasSegment
    else
      Session.currentSession.databaseAdapter.aliasExport(target.parent.get.asInstanceOf[QueryableExpressionNode], target);
    override def actualSelectElement: SelectElement = selectElement.actualSelectElement;
    lazy val target: SelectElement = innerTarget.getOrElse(outerTarget.getOrElse(org.squeryl.internals.Utils.throwError("could not find the target of : ".$plus(selectElement))));
    private def outerScopes: List[QueryExpressionNode[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = outerScopes0(this, Nil);
    @new tailrec() private def outerScopes0(current: ExpressionNode, scopes: List[QueryExpressionNode[_$8] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): List[QueryExpressionNode[_$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = current.parent match {
      case Some((s @ (_: QueryExpressionNode[(_ @ <empty>)]))) => outerScopes0(s, scopes.$colon$plus(s))
      case Some((n @ _)) => outerScopes0(n, scopes)
      case None => scopes
    };
    private def isDirectOuterReference: Boolean = outerScopes.exists(((outer) => outer.$eq$eq(selectElement.parentQueryable)));
    private def outerTarget: Option[SelectElement] = {
      val q = outerScopes.flatMap(((outer) => outer.subQueries.flatMap(((subQuery) => subQuery.asInstanceOf[QueryExpressionElements].selectList.withFilter(((se) => se.$eq$eq(selectElement).$bar$bar(se.actualSelectElement.$eq$eq(selectElement)))).map(((se) => se))))));
      q.headOption
    };
    private def innerTarget: Option[SelectElement] = if (parent.$eq$eq(None))
      return None
    else
      {
        val parentOfThis = parent.get.asInstanceOf[QueryExpressionElements];
        if (selectElement.origin.parent.get.$eq$eq(parentOfThis))
          Some(selectElement)
        else
          {
            val q = parentOfThis.subQueries.flatMap(((q) => q.asInstanceOf[QueryExpressionElements].selectList.withFilter(((se) => se.$eq$eq(selectElement).$bar$bar(se.actualSelectElement.$eq$eq(selectElement)))).map(((se) => se))));
            q.headOption
          }
      }
  }
}