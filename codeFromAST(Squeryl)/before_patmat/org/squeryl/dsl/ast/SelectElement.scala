package org.squeryl.dsl.ast {
  import scala.collection.mutable.ArrayBuffer;
  import org.squeryl.internals._;
  import java.sql.ResultSet;
  import org.squeryl.Session;
  import org.squeryl.dsl.TypedExpression;
  import scala.annotation.tailrec;
  abstract trait SelectElement extends Object with org.squeryl.dsl.ast.ExpressionNode { outer: org.squeryl.dsl.ast.SelectElement => 
    def /*SelectElement*/$init$(): Unit = {
      ()
    };
    def origin: org.squeryl.dsl.ast.QueryableExpressionNode;
    def parentQueryable: org.squeryl.dsl.ast.QueryableExpressionNode = SelectElement.this.parent.get.asInstanceOf[org.squeryl.dsl.ast.QueryableExpressionNode];
    def resultSetMapper: org.squeryl.internals.ResultSetMapper;
    def alias: String;
    def aliasSegment: String = SelectElement.this.alias;
    def actualSelectElement: org.squeryl.dsl.ast.SelectElement = this;
    def inhibitAliasOnSelectElementReference: Boolean = {
      def shouldInhibit(e: org.squeryl.dsl.ast.ExpressionNode): Boolean = e.parent.map[Boolean](((p: org.squeryl.dsl.ast.ExpressionNode) => if (p.isInstanceOf[org.squeryl.dsl.ast.QueryExpressionElements])
  p.asInstanceOf[org.squeryl.dsl.ast.QueryExpressionElements].inhibitAliasOnSelectElementReference
else
  shouldInhibit(p))).getOrElse[Boolean](true);
      shouldInhibit(SelectElement.this.origin)
    };
    def realTableNamePrefix: Boolean = {
      def parent(e: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.ExpressionNode = e.parent.map[org.squeryl.dsl.ast.ExpressionNode](((p: org.squeryl.dsl.ast.ExpressionNode) => parent(p))).getOrElse[org.squeryl.dsl.ast.ExpressionNode](e);
      val p: org.squeryl.dsl.ast.ExpressionNode = parent(SelectElement.this.origin);
      p.isInstanceOf[org.squeryl.dsl.ast.UpdateStatement].||(p.isInstanceOf[org.squeryl.dsl.ast.QueryExpressionElements])
    };
    def prepareColumnMapper(index: Int): Unit;
    def prepareMapper(jdbcIndex: Int): Unit;
    override def inhibited: Boolean = SelectElement.this.origin.inhibited;
    def isActive: Boolean = SelectElement.this._isActive;
    private[this] var _isActive: Boolean = false;
    <accessor> protected[squeryl] def _isActive: Boolean = SelectElement.this._isActive;
    <accessor> protected[squeryl] def _isActive_=(x$1: Boolean): Unit = SelectElement.this._isActive = x$1;
    def expression: org.squeryl.dsl.ast.ExpressionNode;
    def typeOfExpressionToString: String;
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = immutable.this.List.apply[org.squeryl.dsl.ast.ExpressionNode](SelectElement.this.expression);
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      SelectElement.this.expression.write(sw);
      sw.write(" as ");
      sw.databaseAdapter.writeSelectElementAlias(this, sw)
    }
  };
  class TupleSelectElement extends Object with org.squeryl.dsl.ast.SelectElement {
    <paramaccessor> private[this] val origin: org.squeryl.dsl.ast.QueryExpressionNode[_] = _;
    <stable> <accessor> <paramaccessor> def origin: org.squeryl.dsl.ast.QueryExpressionNode[_] = TupleSelectElement.this.origin;
    <paramaccessor> private[this] val expression: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def expression: org.squeryl.dsl.ast.ExpressionNode = TupleSelectElement.this.expression;
    <paramaccessor> private[this] val indexInTuple: Int = _;
    <paramaccessor> private[this] val isGroupTuple: Boolean = _;
    def <init>(origin: org.squeryl.dsl.ast.QueryExpressionNode[_], expression: org.squeryl.dsl.ast.ExpressionNode, indexInTuple: Int, isGroupTuple: Boolean): org.squeryl.dsl.ast.TupleSelectElement = {
      TupleSelectElement.super.<init>();
      ()
    };
    def resultSetMapper: org.squeryl.internals.ResultSetMapper = throw new scala.`package`.UnsupportedOperationException("refactor me");
    def alias: String = if (TupleSelectElement.this.isGroupTuple)
      "g".+(TupleSelectElement.this.indexInTuple)
    else
      "c".+(TupleSelectElement.this.indexInTuple);
    private[this] var columnToTupleMapper: Option[org.squeryl.internals.ColumnToTupleMapper] = scala.None;
    <accessor> def columnToTupleMapper: Option[org.squeryl.internals.ColumnToTupleMapper] = TupleSelectElement.this.columnToTupleMapper;
    <accessor> def columnToTupleMapper_=(x$1: Option[org.squeryl.internals.ColumnToTupleMapper]): Unit = TupleSelectElement.this.columnToTupleMapper = x$1;
    def prepareColumnMapper(index: Int): Unit = ();
    def typeOfExpressionToString: String = if (TupleSelectElement.this.columnToTupleMapper.==(scala.None))
      "unknown"
    else
      TupleSelectElement.this.columnToTupleMapper.get.typeOfExpressionToString(TupleSelectElement.this.indexInTuple);
    override def prepareMapper(jdbcIndex: Int): Unit = if (TupleSelectElement.this.columnToTupleMapper.!=(scala.None))
      TupleSelectElement.this.columnToTupleMapper.get.activate(TupleSelectElement.this.indexInTuple, jdbcIndex)
    else
      ();
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("TupleSelectElement")).+(":").+(TupleSelectElement.this.indexInTuple).+(":").+(TupleSelectElement.this.writeToString)
  };
  class FieldSelectElement extends Object with org.squeryl.dsl.ast.SelectElement with org.squeryl.dsl.ast.UniqueIdInAliaseRequired {
    <paramaccessor> private[this] val origin: org.squeryl.dsl.ast.ViewExpressionNode[_] = _;
    <stable> <accessor> <paramaccessor> def origin: org.squeryl.dsl.ast.ViewExpressionNode[_] = FieldSelectElement.this.origin;
    <paramaccessor> private[this] val fieldMetaData: org.squeryl.internals.FieldMetaData = _;
    <stable> <accessor> <paramaccessor> def fieldMetaData: org.squeryl.internals.FieldMetaData = FieldSelectElement.this.fieldMetaData;
    <paramaccessor> private[this] val resultSetMapper: org.squeryl.internals.ResultSetMapper = _;
    <stable> <accessor> <paramaccessor> def resultSetMapper: org.squeryl.internals.ResultSetMapper = FieldSelectElement.this.resultSetMapper;
    def <init>(origin: org.squeryl.dsl.ast.ViewExpressionNode[_], fieldMetaData: org.squeryl.internals.FieldMetaData, resultSetMapper: org.squeryl.internals.ResultSetMapper): org.squeryl.dsl.ast.FieldSelectElement = {
      FieldSelectElement.super.<init>();
      ()
    };
    def alias: String = if (FieldSelectElement.this.inhibitAliasOnSelectElementReference)
      if (FieldSelectElement.this.realTableNamePrefix)
        FieldSelectElement.this.origin.view.name.+(".").+(FieldSelectElement.this.fieldMetaData.columnName)
      else
        FieldSelectElement.this.fieldMetaData.columnName
    else
      FieldSelectElement.this.origin.alias.+(".").+(FieldSelectElement.this.fieldMetaData.columnName);
    override def aliasSegment: String = org.squeryl.Session.currentSession.databaseAdapter.fieldAlias(FieldSelectElement.this.origin, this);
    private[this] val expression: org.squeryl.dsl.ast.ExpressionNode = {
      final class $anon extends Object with org.squeryl.dsl.ast.ExpressionNode {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.write(sw.quoteName(FieldSelectElement.this.alias))
      };
      new $anon()
    };
    <stable> <accessor> def expression: org.squeryl.dsl.ast.ExpressionNode = FieldSelectElement.this.expression;
    def prepareColumnMapper(index: Int): Unit = FieldSelectElement.this.columnMapper_=(scala.Some.apply[org.squeryl.internals.ColumnToFieldMapper](new org.squeryl.internals.ColumnToFieldMapper(index, FieldSelectElement.this.fieldMetaData, this)));
    private[this] var columnMapper: Option[org.squeryl.internals.ColumnToFieldMapper] = scala.None;
    <accessor> private def columnMapper: Option[org.squeryl.internals.ColumnToFieldMapper] = FieldSelectElement.this.columnMapper;
    <accessor> private def columnMapper_=(x$1: Option[org.squeryl.internals.ColumnToFieldMapper]): Unit = FieldSelectElement.this.columnMapper = x$1;
    def prepareMapper(jdbcIndex: Int): Unit = if (FieldSelectElement.this.columnMapper.!=(scala.None))
      {
        FieldSelectElement.this.resultSetMapper.addColumnMapper(FieldSelectElement.this.columnMapper.get);
        FieldSelectElement.this.resultSetMapper.isActive_=(true);
        FieldSelectElement.this._isActive_=(true)
      }
    else
      ();
    def typeOfExpressionToString: String = FieldSelectElement.this.fieldMetaData.displayType;
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("FieldSelectElement")).+(":").+(org.squeryl.internals.Utils.failSafeString(FieldSelectElement.this.alias, FieldSelectElement.this.fieldMetaData.nameOfProperty))
  };
  class ValueSelectElement extends Object with org.squeryl.dsl.ast.SelectElement with org.squeryl.dsl.ast.UniqueIdInAliaseRequired {
    <paramaccessor> private[this] val expression: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def expression: org.squeryl.dsl.ast.ExpressionNode = ValueSelectElement.this.expression;
    <paramaccessor> private[this] val resultSetMapper: org.squeryl.internals.ResultSetMapper = _;
    <stable> <accessor> <paramaccessor> def resultSetMapper: org.squeryl.internals.ResultSetMapper = ValueSelectElement.this.resultSetMapper;
    <paramaccessor> private[this] val mapper: org.squeryl.internals.OutMapper[_] = _;
    <paramaccessor> private[this] val origin: org.squeryl.dsl.ast.QueryExpressionNode[_] = _;
    <stable> <accessor> <paramaccessor> def origin: org.squeryl.dsl.ast.QueryExpressionNode[_] = ValueSelectElement.this.origin;
    def <init>(expression: org.squeryl.dsl.ast.ExpressionNode, resultSetMapper: org.squeryl.internals.ResultSetMapper, mapper: org.squeryl.internals.OutMapper[_], origin: org.squeryl.dsl.ast.QueryExpressionNode[_]): org.squeryl.dsl.ast.ValueSelectElement = {
      ValueSelectElement.super.<init>();
      ()
    };
    def alias: String = "v".+(ValueSelectElement.this.uniqueId.get);
    private[this] var yieldPusher: Option[org.squeryl.internals.YieldValuePusher] = scala.None;
    <accessor> def yieldPusher: Option[org.squeryl.internals.YieldValuePusher] = ValueSelectElement.this.yieldPusher;
    <accessor> def yieldPusher_=(x$1: Option[org.squeryl.internals.YieldValuePusher]): Unit = ValueSelectElement.this.yieldPusher = x$1;
    def prepareColumnMapper(index: Int): Unit = ValueSelectElement.this.yieldPusher_=(scala.Some.apply[org.squeryl.internals.YieldValuePusher](new org.squeryl.internals.YieldValuePusher(index, this, ValueSelectElement.this.mapper)));
    def typeOfExpressionToString: String = if (ValueSelectElement.this.yieldPusher.==(scala.None))
      "unknown"
    else
      ValueSelectElement.this.yieldPusher.get.selectElement.typeOfExpressionToString;
    override def prepareMapper(jdbcIndex: Int): Unit = if (ValueSelectElement.this.yieldPusher.!=(scala.None))
      {
        ValueSelectElement.this.resultSetMapper.addYieldValuePusher(ValueSelectElement.this.yieldPusher.get);
        ValueSelectElement.this.resultSetMapper.isActive_=(true);
        ValueSelectElement.this._isActive_=(true)
      }
    else
      ();
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("ValueSelectElement")).+(":").+(ValueSelectElement.this.expression.writeToString)
  };
  class SelectElementReference[A >: Nothing <: Any, T >: Nothing <: Any] extends Object with org.squeryl.dsl.TypedExpression[A,T] {
    <paramaccessor> private[this] val selectElement: org.squeryl.dsl.ast.SelectElement = _;
    <stable> <accessor> <paramaccessor> def selectElement: org.squeryl.dsl.ast.SelectElement = SelectElementReference.this.selectElement;
    <paramaccessor> private[this] val mapper: org.squeryl.internals.OutMapper[A] = _;
    <stable> <accessor> <paramaccessor> def mapper: org.squeryl.internals.OutMapper[A] = SelectElementReference.this.mapper;
    def <init>(selectElement: org.squeryl.dsl.ast.SelectElement, mapper: org.squeryl.internals.OutMapper[A]): org.squeryl.dsl.ast.SelectElementReference[A,T] = {
      SelectElementReference.super.<init>();
      ()
    };
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("SelectElementReference")).+(":").+(org.squeryl.internals.Utils.failSafeString(SelectElementReference.this.delegateAtUseSite.alias)).+(":").+(SelectElementReference.this.selectElement.typeOfExpressionToString).+(SelectElementReference.this.inhibitedFlagForAstDump);
    override def inhibited: Boolean = SelectElementReference.this.selectElement.inhibited;
    private def _useSite: org.squeryl.dsl.ast.QueryExpressionNode[_] = {
      def findQueryExpressionNode(e: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.QueryExpressionNode[_] = e match {
        case (qe @ (_: org.squeryl.dsl.ast.QueryExpressionNode[_])) => qe
        case _ => e.parent match {
          case (x: org.squeryl.dsl.ast.ExpressionNode)Some[org.squeryl.dsl.ast.ExpressionNode]((e_ @ _)) => findQueryExpressionNode(e_)
          case _ => org.squeryl.internals.Utils.throwError("could not determine use site of ".+(this))
        }
      };
      findQueryExpressionNode(this)
    };
    lazy private[this] var delegateAtUseSite: org.squeryl.dsl.ast.SelectElement = if (SelectElementReference.this.selectElement.parent.==(scala.None))
      SelectElementReference.this.selectElement
    else
      {
        val us: org.squeryl.dsl.ast.QueryExpressionNode[_] = this._useSite;
        if (SelectElementReference.this.selectElement.parentQueryable.==(us))
          SelectElementReference.this.selectElement
        else
          {
            val ese: org.squeryl.dsl.ast.ExportedSelectElement = new ExportedSelectElement(this.selectElement);
            ese.parent_=(scala.Some.apply[org.squeryl.dsl.ast.QueryExpressionNode[_$5]](us));
            ese
          }
      };
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.write(sw.quoteName(SelectElementReference.this.delegateAtUseSite.alias))
  };
  class ExportedSelectElement extends Object with org.squeryl.dsl.ast.SelectElement {
    <paramaccessor> private[this] val selectElement: org.squeryl.dsl.ast.SelectElement = _;
    <stable> <accessor> <paramaccessor> def selectElement: org.squeryl.dsl.ast.SelectElement = ExportedSelectElement.this.selectElement;
    def <init>(selectElement: org.squeryl.dsl.ast.SelectElement): org.squeryl.dsl.ast.ExportedSelectElement = {
      ExportedSelectElement.super.<init>();
      ()
    };
    def resultSetMapper: org.squeryl.internals.ResultSetMapper = ExportedSelectElement.this.selectElement.resultSetMapper;
    override def inhibited: Boolean = ExportedSelectElement.this.selectElement.inhibited;
    override def prepareMapper(jdbcIndex: Int): Unit = ExportedSelectElement.this.selectElement.prepareMapper(jdbcIndex);
    def prepareColumnMapper(index: Int): Unit = ExportedSelectElement.this.selectElement.prepareColumnMapper(index);
    def typeOfExpressionToString: String = ExportedSelectElement.this.selectElement.typeOfExpressionToString;
    def origin: org.squeryl.dsl.ast.QueryableExpressionNode = ExportedSelectElement.this.selectElement.origin;
    private[this] val expression: org.squeryl.dsl.ast.ExpressionNode = {
      final class $anon extends Object with org.squeryl.dsl.ast.ExpressionNode {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.write(sw.quoteName(ExportedSelectElement.this.alias))
      };
      new $anon()
    };
    <stable> <accessor> def expression: org.squeryl.dsl.ast.ExpressionNode = ExportedSelectElement.this.expression;
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("ExportedSelectElement")).+(":").+(ExportedSelectElement.this.alias).+(",(selectElement=").+(ExportedSelectElement.this.selectElement).+(")");
    def alias: String = if (ExportedSelectElement.this.isDirectOuterReference)
      ExportedSelectElement.this.selectElement.alias
    else
      ExportedSelectElement.this.target.parent.get.asInstanceOf[org.squeryl.dsl.ast.QueryableExpressionNode].alias.+(".").+(ExportedSelectElement.this.target.aliasSegment);
    override def aliasSegment: String = if (ExportedSelectElement.this.isDirectOuterReference)
      ExportedSelectElement.this.selectElement.aliasSegment
    else
      org.squeryl.Session.currentSession.databaseAdapter.aliasExport(ExportedSelectElement.this.target.parent.get.asInstanceOf[org.squeryl.dsl.ast.QueryableExpressionNode], ExportedSelectElement.this.target);
    override def actualSelectElement: org.squeryl.dsl.ast.SelectElement = ExportedSelectElement.this.selectElement.actualSelectElement;
    lazy private[this] var target: org.squeryl.dsl.ast.SelectElement = ExportedSelectElement.this.innerTarget.getOrElse[org.squeryl.dsl.ast.SelectElement](ExportedSelectElement.this.outerTarget.getOrElse[org.squeryl.dsl.ast.SelectElement](org.squeryl.internals.Utils.throwError("could not find the target of : ".+(ExportedSelectElement.this.selectElement))));
    private def outerScopes: List[org.squeryl.dsl.ast.QueryExpressionNode[_]] = ExportedSelectElement.this.outerScopes0(this, immutable.this.Nil);
    @scala.annotation.tailrec private def outerScopes0(current: org.squeryl.dsl.ast.ExpressionNode, scopes: List[org.squeryl.dsl.ast.QueryExpressionNode[_]]): List[org.squeryl.dsl.ast.QueryExpressionNode[_]] = current.parent match {
      case (x: org.squeryl.dsl.ast.ExpressionNode)Some[org.squeryl.dsl.ast.ExpressionNode]((s @ (_: org.squeryl.dsl.ast.QueryExpressionNode[_]))) => ExportedSelectElement.this.outerScopes0(s, scopes.:+[org.squeryl.dsl.ast.QueryExpressionNode[_], List[org.squeryl.dsl.ast.QueryExpressionNode[_]]](s)(immutable.this.List.canBuildFrom[org.squeryl.dsl.ast.QueryExpressionNode[_]]))
      case (x: org.squeryl.dsl.ast.ExpressionNode)Some[org.squeryl.dsl.ast.ExpressionNode]((n @ _)) => ExportedSelectElement.this.outerScopes0(n, scopes)
      case scala.None => scopes
    };
    private def isDirectOuterReference: Boolean = ExportedSelectElement.this.outerScopes.exists(((outer: org.squeryl.dsl.ast.QueryExpressionNode[_]) => outer.==(ExportedSelectElement.this.selectElement.parentQueryable)));
    private def outerTarget: Option[org.squeryl.dsl.ast.SelectElement] = {
      val q: List[org.squeryl.dsl.ast.SelectElement] = ExportedSelectElement.this.outerScopes.flatMap[org.squeryl.dsl.ast.SelectElement, List[org.squeryl.dsl.ast.SelectElement]](((outer: org.squeryl.dsl.ast.QueryExpressionNode[_]) => outer.subQueries.flatMap[org.squeryl.dsl.ast.SelectElement, Iterable[org.squeryl.dsl.ast.SelectElement]](((subQuery: org.squeryl.dsl.ast.QueryableExpressionNode) => subQuery.asInstanceOf[org.squeryl.dsl.ast.QueryExpressionElements].selectList.withFilter(((se: org.squeryl.dsl.ast.SelectElement) => se.==(ExportedSelectElement.this.selectElement).||(se.actualSelectElement.==(ExportedSelectElement.this.selectElement)))).map[org.squeryl.dsl.ast.SelectElement, Iterable[org.squeryl.dsl.ast.SelectElement]](((se: org.squeryl.dsl.ast.SelectElement) => se))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.SelectElement])))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.SelectElement])))(immutable.this.List.canBuildFrom[org.squeryl.dsl.ast.SelectElement]);
      q.headOption
    };
    private def innerTarget: Option[org.squeryl.dsl.ast.SelectElement] = if (ExportedSelectElement.this.parent.==(scala.None))
      return scala.None
    else
      {
        val parentOfThis: org.squeryl.dsl.ast.QueryExpressionElements = ExportedSelectElement.this.parent.get.asInstanceOf[org.squeryl.dsl.ast.QueryExpressionElements];
        if (ExportedSelectElement.this.selectElement.origin.parent.get.==(parentOfThis))
          scala.Some.apply[org.squeryl.dsl.ast.SelectElement](ExportedSelectElement.this.selectElement)
        else
          {
            val q: Iterable[org.squeryl.dsl.ast.SelectElement] = parentOfThis.subQueries.flatMap[org.squeryl.dsl.ast.SelectElement, Iterable[org.squeryl.dsl.ast.SelectElement]](((q: org.squeryl.dsl.ast.QueryableExpressionNode) => q.asInstanceOf[org.squeryl.dsl.ast.QueryExpressionElements].selectList.withFilter(((se: org.squeryl.dsl.ast.SelectElement) => se.==(ExportedSelectElement.this.selectElement).||(se.actualSelectElement.==(ExportedSelectElement.this.selectElement)))).map[org.squeryl.dsl.ast.SelectElement, Iterable[org.squeryl.dsl.ast.SelectElement]](((se: org.squeryl.dsl.ast.SelectElement) => se))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.SelectElement])))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.SelectElement]);
            q.headOption
          }
      }
  }
}