package org.squeryl.dsl.fsm {
  import org.squeryl.dsl.ast._;
  import org.squeryl.dsl._;
  import org.squeryl.dsl.boilerplate._;
  import org.squeryl.internals.{FieldReferenceLinker, ResultSetMapper, ColumnToTupleMapper, OutMapper};
  import java.sql.ResultSet;
  class BaseQueryYield[G >: Nothing <: Any] extends Object with org.squeryl.dsl.fsm.SelectState[G] with org.squeryl.dsl.boilerplate.OrderBySignatures[G] with org.squeryl.dsl.QueryYield[G] {
    <paramaccessor> private[this] val queryElementzz: org.squeryl.dsl.fsm.QueryElements[_] = _;
    <stable> <accessor> <paramaccessor> def queryElementzz: org.squeryl.dsl.fsm.QueryElements[_] = BaseQueryYield.this.queryElementzz;
    <paramaccessor> private[this] val selectClosure: () => G = _;
    <stable> <accessor> <paramaccessor> def selectClosure: () => G = BaseQueryYield.this.selectClosure;
    def <init>(queryElementzz: org.squeryl.dsl.fsm.QueryElements[_], selectClosure: () => G): org.squeryl.dsl.fsm.BaseQueryYield[G] = {
      BaseQueryYield.super.<init>();
      ()
    };
    protected def _createColumnToTupleMapper(origin: org.squeryl.dsl.ast.QueryExpressionNode[_], agregateArgs: List[org.squeryl.dsl.TypedExpression[_, _]], offsetInResultSet: Int, isForGroup: Boolean): (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) = {
      var i: Int = -1;
      val nodes: List[org.squeryl.dsl.ast.TupleSelectElement] = agregateArgs.map[org.squeryl.dsl.ast.TupleSelectElement, List[org.squeryl.dsl.ast.TupleSelectElement]](((e: org.squeryl.dsl.TypedExpression[_, _]) => {
        i = i.+(1);
        new org.squeryl.dsl.ast.TupleSelectElement(origin, e, i, isForGroup)
      }))(immutable.this.List.canBuildFrom[org.squeryl.dsl.ast.TupleSelectElement]);
      var o: Int = offsetInResultSet;
      val mappers: Array[org.squeryl.internals.OutMapper[_]] = new Array[org.squeryl.internals.OutMapper[_]](agregateArgs.size);
      var k: Int = 0;
      agregateArgs.foreach[Unit](((e: org.squeryl.dsl.TypedExpression[_, _]) => {
        e.mapper.index_=(o);
        o = o.+(1);
        mappers.update(k, e.mapper);
        k = k.+(1)
      }));
      val m: org.squeryl.internals.ColumnToTupleMapper = new org.squeryl.internals.ColumnToTupleMapper(mappers);
      nodes.foreach[Unit](((n: org.squeryl.dsl.ast.TupleSelectElement) => n.columnToTupleMapper_=(scala.Some.apply[org.squeryl.internals.ColumnToTupleMapper](m))));
      scala.Tuple2.apply[org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]](m, nodes)
    };
    private[this] var _havingClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = scala.None;
    <accessor> protected def _havingClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = BaseQueryYield.this._havingClause;
    <accessor> protected def _havingClause_=(x$1: Option[() => org.squeryl.dsl.ast.LogicalBoolean]): Unit = BaseQueryYield.this._havingClause = x$1;
    def unevaluatedHavingClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = BaseQueryYield.this._havingClause;
    private[this] var _orderByExpressions: () => List[() => org.squeryl.dsl.ast.ExpressionNode] = null;
    <accessor> protected def _orderByExpressions: () => List[() => org.squeryl.dsl.ast.ExpressionNode] = BaseQueryYield.this._orderByExpressions;
    <accessor> protected def _orderByExpressions_=(x$1: () => List[() => org.squeryl.dsl.ast.ExpressionNode]): Unit = BaseQueryYield.this._orderByExpressions = x$1;
    def whereClause: Option[org.squeryl.dsl.ast.ExpressionNode] = BaseQueryYield.this.queryElementzz.whereClause.map[org.squeryl.dsl.ast.LogicalBoolean](((b: () => org.squeryl.dsl.ast.LogicalBoolean) => b.apply()));
    def havingClause: Option[org.squeryl.dsl.ast.ExpressionNode] = BaseQueryYield.this._havingClause.map[org.squeryl.dsl.ast.LogicalBoolean](((c: () => org.squeryl.dsl.ast.LogicalBoolean) => c.apply()));
    def groupByClause: Iterable[org.squeryl.dsl.ast.ExpressionNode] = scala.`package`.Iterable.empty[Nothing];
    def queryElements: (Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]) = scala.Tuple4.apply[Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]](BaseQueryYield.this.whereClause, BaseQueryYield.this.havingClause, BaseQueryYield.this.groupByClause, BaseQueryYield.this.orderByClause);
    def computeClause: List[org.squeryl.dsl.ast.ExpressionNode] = immutable.this.List.empty[Nothing];
    def orderByClause: Iterable[org.squeryl.dsl.ast.ExpressionNode] = if (BaseQueryYield.this._orderByExpressions.==(null))
      immutable.this.List.empty[Nothing]
    else
      BaseQueryYield.this._orderByExpressions.apply().map[org.squeryl.dsl.ast.ExpressionNode, Iterable[org.squeryl.dsl.ast.ExpressionNode]](((c: () => org.squeryl.dsl.ast.ExpressionNode) => c.apply()))(immutable.this.List.canBuildFrom[org.squeryl.dsl.ast.ExpressionNode]);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): G = BaseQueryYield.this.selectClosure.apply();
    def invokeYieldForAst(q: org.squeryl.dsl.ast.QueryExpressionNode[_], rsm: org.squeryl.internals.ResultSetMapper): (List[org.squeryl.dsl.ast.SelectElement], AnyRef) = org.squeryl.internals.FieldReferenceLinker.determineColumnsUtilizedInYeldInvocation(q, rsm, (() => BaseQueryYield.this.invokeYield(rsm, null).asInstanceOf[AnyRef]));
    protected def _sTuple1ToValue[B >: Nothing <: Any](b: B): B = b match {
      case (t @ (_: org.squeryl.dsl.boilerplate.STuple1[_])) => if (t.productArity.==(1))
        t._1.asInstanceOf[B]
      else
        b
    }
  };
  class GroupQueryYield[K >: Nothing <: Any] extends org.squeryl.dsl.fsm.BaseQueryYield[org.squeryl.dsl.Group[K]] with org.squeryl.dsl.fsm.GroupByState[K] with org.squeryl.dsl.fsm.HavingState[K] with org.squeryl.dsl.boilerplate.OrderBySignatures[org.squeryl.dsl.Group[K]] with org.squeryl.dsl.QueryYield[org.squeryl.dsl.Group[K]] {
    <paramaccessor> private[this] val _qe: org.squeryl.dsl.fsm.QueryElements[_] = _;
    <paramaccessor> private[this] val groupByClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]] = _;
    <stable> <accessor> <paramaccessor> def groupByClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]] = GroupQueryYield.this.groupByClauseClosure;
    def <init>(_qe: org.squeryl.dsl.fsm.QueryElements[_], groupByClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]]): org.squeryl.dsl.fsm.GroupQueryYield[K] = {
      GroupQueryYield.super.<init>(_qe, null);
      ()
    };
    override def groupByClause: List[org.squeryl.dsl.ast.ExpressionNode] = GroupQueryYield.this.groupByClauseClosure.apply().map[org.squeryl.dsl.TypedExpression[_, _], List[org.squeryl.dsl.ast.ExpressionNode]](((e: org.squeryl.dsl.TypedExpression[_, _]) => e))(immutable.this.List.canBuildFrom[org.squeryl.dsl.TypedExpression[_, _]]);
    override def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): org.squeryl.dsl.Group[K] = new org.squeryl.dsl.Group[K](rsm.groupKeysMapper.get.mapToTuple[Nothing](rs));
    override def queryElements: (Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], List[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]) = scala.Tuple4.apply[Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], List[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]](GroupQueryYield.this.whereClause, GroupQueryYield.this.havingClause, GroupQueryYield.this.groupByClause, GroupQueryYield.this.orderByClause);
    class SampleGroup[K >: Nothing <: Any] extends org.squeryl.dsl.Group[K] {
      <paramaccessor> private[this] val k: K = _;
      def <init>(k: K): GroupQueryYield.this.SampleGroup[K] = {
        SampleGroup.super.<init>(k);
        ()
      };
      override def key: K = GroupQueryYield.this._sTuple1ToValue[K](SampleGroup.this.k)
    };
    override def invokeYieldForAst(q: org.squeryl.dsl.ast.QueryExpressionNode[_], rsm: org.squeryl.internals.ResultSetMapper): (List[org.squeryl.dsl.ast.TupleSelectElement], GroupQueryYield.this.SampleGroup[K]) = {
      val offset: Int = 1;
      <synthetic> private[this] val x$1: (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) = (GroupQueryYield.this._createColumnToTupleMapper(q, GroupQueryYield.this.groupByClauseClosure.apply(), offset, true): (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) @unchecked) match {
        case (_1: org.squeryl.internals.ColumnToTupleMapper, _2: List[org.squeryl.dsl.ast.TupleSelectElement])(org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement])((m @ _), (nodes @ _)) => scala.Tuple2.apply[org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]](m, nodes)
      };
      val m: org.squeryl.internals.ColumnToTupleMapper = x$1._1;
      val nodes: List[org.squeryl.dsl.ast.TupleSelectElement] = x$1._2;
      rsm.groupKeysMapper_=(scala.Some.apply[org.squeryl.internals.ColumnToTupleMapper](m));
      val st: K = org.squeryl.dsl.boilerplate.SampleTuple.create(nodes, m.outMappers).asInstanceOf[K];
      scala.Tuple2.apply[List[org.squeryl.dsl.ast.TupleSelectElement], GroupQueryYield.this.SampleGroup[K]](nodes, new GroupQueryYield.this.SampleGroup[K](st))
    }
  };
  class MeasuresQueryYield[M >: Nothing <: Any] extends org.squeryl.dsl.fsm.BaseQueryYield[org.squeryl.dsl.Measures[M]] with org.squeryl.dsl.boilerplate.OrderBySignatures[org.squeryl.dsl.Measures[M]] with org.squeryl.dsl.fsm.ComputeStateStartOrWhereState[M] with org.squeryl.dsl.QueryYield[org.squeryl.dsl.Measures[M]] {
    <paramaccessor> private[this] val _qe: org.squeryl.dsl.fsm.QueryElements[_] = _;
    <paramaccessor> private[this] val _computeByClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]] = _;
    def <init>(_qe: org.squeryl.dsl.fsm.QueryElements[_], _computeByClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]]): org.squeryl.dsl.fsm.MeasuresQueryYield[M] = {
      MeasuresQueryYield.super.<init>(_qe, null);
      ()
    };
    override def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): org.squeryl.dsl.Measures[M] = new org.squeryl.dsl.Measures[M](rsm.groupMeasuresMapper.get.mapToTuple[Nothing](rs));
    override def queryElements: (Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]) = scala.Tuple4.apply[Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]](MeasuresQueryYield.this.whereClause, MeasuresQueryYield.this.havingClause, MeasuresQueryYield.this.groupByClause, MeasuresQueryYield.this.orderByClause);
    class SampleMeasures[M >: Nothing <: Any] extends org.squeryl.dsl.Measures[M] {
      <paramaccessor> private[this] val m: M = _;
      def <init>(m: M): MeasuresQueryYield.this.SampleMeasures[M] = {
        SampleMeasures.super.<init>(m);
        ()
      };
      override def measures: M = MeasuresQueryYield.this._sTuple1ToValue[M](SampleMeasures.this.m)
    };
    override def invokeYieldForAst(q: org.squeryl.dsl.ast.QueryExpressionNode[_], rsm: org.squeryl.internals.ResultSetMapper): (List[org.squeryl.dsl.ast.TupleSelectElement], MeasuresQueryYield.this.SampleMeasures[M]) = {
      val offset: Int = 1;
      <synthetic> private[this] val x$2: (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) = (MeasuresQueryYield.this._createColumnToTupleMapper(q, MeasuresQueryYield.this._computeByClauseClosure.apply(), offset, false): (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) @unchecked) match {
        case (_1: org.squeryl.internals.ColumnToTupleMapper, _2: List[org.squeryl.dsl.ast.TupleSelectElement])(org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement])((m @ _), (nodes @ _)) => scala.Tuple2.apply[org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]](m, nodes)
      };
      val m: org.squeryl.internals.ColumnToTupleMapper = x$2._1;
      val nodes: List[org.squeryl.dsl.ast.TupleSelectElement] = x$2._2;
      rsm.groupMeasuresMapper_=(scala.Some.apply[org.squeryl.internals.ColumnToTupleMapper](m));
      val st: M = org.squeryl.dsl.boilerplate.SampleTuple.create(nodes, m.outMappers).asInstanceOf[M];
      scala.Tuple2.apply[List[org.squeryl.dsl.ast.TupleSelectElement], MeasuresQueryYield.this.SampleMeasures[M]](nodes, new MeasuresQueryYield.this.SampleMeasures[M](st))
    }
  };
  class GroupWithMeasuresQueryYield[K >: Nothing <: Any, M >: Nothing <: Any] extends org.squeryl.dsl.fsm.BaseQueryYield[org.squeryl.dsl.GroupWithMeasures[K,M]] with org.squeryl.dsl.fsm.ComputeStateFromGroupByState[K,M] with org.squeryl.dsl.boilerplate.OrderBySignatures[org.squeryl.dsl.GroupWithMeasures[K,M]] with org.squeryl.dsl.QueryYield[org.squeryl.dsl.GroupWithMeasures[K,M]] {
    <paramaccessor> private[this] val _qe: org.squeryl.dsl.fsm.QueryElements[_] = _;
    <paramaccessor> private[this] val _groupByClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]] = _;
    <paramaccessor> private[this] val _having: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = _;
    <paramaccessor> private[this] val _computeClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]] = _;
    def <init>(_qe: org.squeryl.dsl.fsm.QueryElements[_], _groupByClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]], _having: Option[() => org.squeryl.dsl.ast.LogicalBoolean], _computeClauseClosure: () => List[org.squeryl.dsl.TypedExpression[_, _]]): org.squeryl.dsl.fsm.GroupWithMeasuresQueryYield[K,M] = {
      GroupWithMeasuresQueryYield.super.<init>(_qe, null);
      ()
    };
    class SampleGroupWithMeasures[K >: Nothing <: Any, M >: Nothing <: Any] extends org.squeryl.dsl.GroupWithMeasures[K,M] {
      <paramaccessor> private[this] val k: K = _;
      <paramaccessor> private[this] val m: M = _;
      def <init>(k: K, m: M): GroupWithMeasuresQueryYield.this.SampleGroupWithMeasures[K,M] = {
        SampleGroupWithMeasures.super.<init>(k, m);
        ()
      };
      override def key: K = GroupWithMeasuresQueryYield.this._sTuple1ToValue[K](SampleGroupWithMeasures.this.k);
      override def measures: M = GroupWithMeasuresQueryYield.this._sTuple1ToValue[M](SampleGroupWithMeasures.this.m)
    };
    override def havingClause: Option[org.squeryl.dsl.ast.ExpressionNode] = if (GroupWithMeasuresQueryYield.this._having.!=(scala.None))
      GroupWithMeasuresQueryYield.this._having.map[org.squeryl.dsl.ast.LogicalBoolean](((c: () => org.squeryl.dsl.ast.LogicalBoolean) => c.apply()))
    else
      GroupWithMeasuresQueryYield.super.havingClause;
    override def queryElements: (Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]) = scala.Tuple4.apply[Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]](GroupWithMeasuresQueryYield.this.whereClause, GroupWithMeasuresQueryYield.this.havingClause, GroupWithMeasuresQueryYield.this._groupByClauseClosure.apply().map[org.squeryl.dsl.TypedExpression[_, _], Iterable[org.squeryl.dsl.ast.ExpressionNode]](((e: org.squeryl.dsl.TypedExpression[_, _]) => e))(immutable.this.List.canBuildFrom[org.squeryl.dsl.TypedExpression[_, _]]), GroupWithMeasuresQueryYield.this.orderByClause);
    override def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): org.squeryl.dsl.GroupWithMeasures[K,M] = new org.squeryl.dsl.GroupWithMeasures[K,M](rsm.groupKeysMapper.get.mapToTuple[Nothing](rs), rsm.groupMeasuresMapper.get.mapToTuple[Nothing](rs));
    override def invokeYieldForAst(q: org.squeryl.dsl.ast.QueryExpressionNode[_], rsm: org.squeryl.internals.ResultSetMapper): (List[org.squeryl.dsl.ast.SelectElement], GroupWithMeasuresQueryYield.this.SampleGroupWithMeasures[K,M]) = {
      val offset: Int = 1;
      <synthetic> private[this] val x$3: (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) = (GroupWithMeasuresQueryYield.this._createColumnToTupleMapper(q, GroupWithMeasuresQueryYield.this._groupByClauseClosure.apply(), offset, true): (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) @unchecked) match {
        case (_1: org.squeryl.internals.ColumnToTupleMapper, _2: List[org.squeryl.dsl.ast.TupleSelectElement])(org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement])((km @ _), (knodes @ _)) => scala.Tuple2.apply[org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]](km, knodes)
      };
      val km: org.squeryl.internals.ColumnToTupleMapper = x$3._1;
      val knodes: List[org.squeryl.dsl.ast.TupleSelectElement] = x$3._2;
      <synthetic> private[this] val x$4: (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) = (GroupWithMeasuresQueryYield.this._createColumnToTupleMapper(q, GroupWithMeasuresQueryYield.this._computeClauseClosure.apply(), offset.+(knodes.size), false): (org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]) @unchecked) match {
        case (_1: org.squeryl.internals.ColumnToTupleMapper, _2: List[org.squeryl.dsl.ast.TupleSelectElement])(org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement])((mm @ _), (mnodes @ _)) => scala.Tuple2.apply[org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.dsl.ast.TupleSelectElement]](mm, mnodes)
      };
      val mm: org.squeryl.internals.ColumnToTupleMapper = x$4._1;
      val mnodes: List[org.squeryl.dsl.ast.TupleSelectElement] = x$4._2;
      rsm.groupKeysMapper_=(scala.Some.apply[org.squeryl.internals.ColumnToTupleMapper](km));
      rsm.groupMeasuresMapper_=(scala.Some.apply[org.squeryl.internals.ColumnToTupleMapper](mm));
      val stK: K = org.squeryl.dsl.boilerplate.SampleTuple.create(knodes, km.outMappers).asInstanceOf[K];
      val stM: M = org.squeryl.dsl.boilerplate.SampleTuple.create(mnodes, mm.outMappers).asInstanceOf[M];
      scala.Tuple2.apply[List[org.squeryl.dsl.ast.SelectElement], GroupWithMeasuresQueryYield.this.SampleGroupWithMeasures[K,M]](immutable.this.List.apply[List[org.squeryl.dsl.ast.TupleSelectElement]](knodes, mnodes).flatten[org.squeryl.dsl.ast.SelectElement](scala.this.Predef.conforms[List[org.squeryl.dsl.ast.TupleSelectElement]]), new GroupWithMeasuresQueryYield.this.SampleGroupWithMeasures[K,M](stK, stM))
    }
  }
}