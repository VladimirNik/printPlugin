package org.squeryl.dsl.fsm {
  import org.squeryl.dsl.ast._;
  import org.squeryl.dsl._;
  import org.squeryl.dsl.boilerplate._;
  import org.squeryl.internals.{FieldReferenceLinker, ResultSetMapper, ColumnToTupleMapper, OutMapper};
  import java.sql.ResultSet;
  class BaseQueryYield[G >: _root_.scala.Nothing <: _root_.scala.Any] extends SelectState[G] with OrderBySignatures[G] with QueryYield[G] {
    <paramaccessor> val queryElementzz: QueryElements[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> val selectClosure: _root_.scala.Function0[G] = _;
    def <init>(queryElementzz: QueryElements[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, selectClosure: _root_.scala.Function0[G]) = {
      super.<init>();
      ()
    };
    protected def _createColumnToTupleMapper(origin: QueryExpressionNode[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }, agregateArgs: List[TypedExpression[_$3, _$4] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }], offsetInResultSet: Int, isForGroup: Boolean) = {
      var i = -1;
      val nodes = agregateArgs.map(((e) => {
        i.$plus$eq(1);
        new TupleSelectElement(origin, e, i, isForGroup)
      }));
      var o = offsetInResultSet;
      val mappers = new Array[OutMapper[_$5] forSome { 
        <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
      }](agregateArgs.size);
      var k: Int = 0;
      agregateArgs.foreach(((e) => {
        e.mapper.index = o;
        o.$plus$eq(1);
        mappers.update(k, e.mapper);
        k.$plus$eq(1)
      }));
      val m = new ColumnToTupleMapper(mappers);
      nodes.foreach(((n) => n.columnToTupleMapper = Some(m)));
      scala.Tuple2(m, nodes)
    };
    protected var _havingClause: Option[_root_.scala.Function0[LogicalBoolean]] = None;
    def unevaluatedHavingClause = _havingClause;
    protected var _orderByExpressions: _root_.scala.Function0[List[_root_.scala.Function0[ExpressionNode]]] = null;
    def whereClause: Option[ExpressionNode] = queryElementzz.whereClause.map(((b) => b()));
    def havingClause: Option[ExpressionNode] = _havingClause.map(((c) => c()));
    def groupByClause: Iterable[ExpressionNode] = Iterable.empty;
    def queryElements = scala.Tuple4(whereClause, havingClause, groupByClause, orderByClause);
    def computeClause: List[ExpressionNode] = List.empty;
    def orderByClause: Iterable[ExpressionNode] = if (_orderByExpressions.$eq$eq(null))
      List.empty
    else
      _orderByExpressions().map(((c) => c()));
    def invokeYield(rsm: ResultSetMapper, rs: ResultSet): G = selectClosure();
    def invokeYieldForAst(q: QueryExpressionNode[_$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }, rsm: ResultSetMapper) = FieldReferenceLinker.determineColumnsUtilizedInYeldInvocation(q, rsm, (() => invokeYield(rsm, null).asInstanceOf[AnyRef]));
    protected def _sTuple1ToValue[B >: _root_.scala.Nothing <: _root_.scala.Any](b: B) = b match {
      case (t @ (_: STuple1[(_ @ <empty>)])) => if (t.productArity.$eq$eq(1))
        t._1.asInstanceOf[B]
      else
        b
    }
  };
  class GroupQueryYield[K >: _root_.scala.Nothing <: _root_.scala.Any] extends BaseQueryYield[Group[K]] with GroupByState[K] with HavingState[K] with OrderBySignatures[Group[K]] with QueryYield[Group[K]] {
    <paramaccessor> private[this] val _qe: QueryElements[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> val groupByClauseClosure: _root_.scala.Function0[List[TypedExpression[_$8, _$9] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }]] = _;
    def <init>(_qe: QueryElements[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }, groupByClauseClosure: _root_.scala.Function0[List[TypedExpression[_$8, _$9] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }]]) = {
      super.<init>(_qe, null);
      ()
    };
    override def groupByClause: List[ExpressionNode] = groupByClauseClosure().map(((e) => e));
    override def invokeYield(rsm: ResultSetMapper, rs: ResultSet): Group[K] = new Group(rsm.groupKeysMapper.get.mapToTuple(rs));
    override def queryElements = scala.Tuple4(whereClause, havingClause, groupByClause, orderByClause);
    class SampleGroup[K >: _root_.scala.Nothing <: _root_.scala.Any] extends Group {
      <paramaccessor> private[this] val k: K = _;
      def <init>(k: K) = {
        super.<init>(k);
        ()
      };
      override def key = _sTuple1ToValue(k)
    };
    override def invokeYieldForAst(q: QueryExpressionNode[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }, rsm: ResultSetMapper) = {
      val offset = 1;
      <synthetic> private[this] val x$1 = _createColumnToTupleMapper(q, groupByClauseClosure(), offset, true): @scala.unchecked match {
        case scala.Tuple2((m @ _), (nodes @ _)) => scala.Tuple2(m, nodes)
      };
      val m = x$1._1;
      val nodes = x$1._2;
      rsm.groupKeysMapper = Some(m);
      val st = SampleTuple.create(nodes, m.outMappers).asInstanceOf[K];
      scala.Tuple2(nodes, new SampleGroup(st))
    }
  };
  class MeasuresQueryYield[M >: _root_.scala.Nothing <: _root_.scala.Any] extends BaseQueryYield[Measures[M]] with OrderBySignatures[Measures[M]] with ComputeStateStartOrWhereState[M] with QueryYield[Measures[M]] {
    <paramaccessor> private[this] val _qe: QueryElements[_$11] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> private[this] val _computeByClauseClosure: _root_.scala.Function0[List[TypedExpression[_$12, _$13] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }]] = _;
    def <init>(_qe: QueryElements[_$11] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
    }, _computeByClauseClosure: _root_.scala.Function0[List[TypedExpression[_$12, _$13] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }]]) = {
      super.<init>(_qe, null);
      ()
    };
    override def invokeYield(rsm: ResultSetMapper, rs: ResultSet): Measures[M] = new Measures(rsm.groupMeasuresMapper.get.mapToTuple(rs));
    override def queryElements = scala.Tuple4(whereClause, havingClause, groupByClause, orderByClause);
    class SampleMeasures[M >: _root_.scala.Nothing <: _root_.scala.Any] extends Measures {
      <paramaccessor> private[this] val m: M = _;
      def <init>(m: M) = {
        super.<init>(m);
        ()
      };
      override def measures = _sTuple1ToValue(m)
    };
    override def invokeYieldForAst(q: QueryExpressionNode[_$14] forSome { 
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    }, rsm: ResultSetMapper) = {
      val offset = 1;
      <synthetic> private[this] val x$2 = _createColumnToTupleMapper(q, _computeByClauseClosure(), offset, false): @scala.unchecked match {
        case scala.Tuple2((m @ _), (nodes @ _)) => scala.Tuple2(m, nodes)
      };
      val m = x$2._1;
      val nodes = x$2._2;
      rsm.groupMeasuresMapper = Some(m);
      val st = SampleTuple.create(nodes, m.outMappers).asInstanceOf[M];
      scala.Tuple2(nodes, new SampleMeasures(st))
    }
  };
  class GroupWithMeasuresQueryYield[K >: _root_.scala.Nothing <: _root_.scala.Any, M >: _root_.scala.Nothing <: _root_.scala.Any] extends BaseQueryYield[GroupWithMeasures[K, M]] with ComputeStateFromGroupByState[K, M] with OrderBySignatures[GroupWithMeasures[K, M]] with QueryYield[GroupWithMeasures[K, M]] {
    <paramaccessor> private[this] val _qe: QueryElements[_$15] forSome { 
      <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> private[this] val _groupByClauseClosure: _root_.scala.Function0[List[TypedExpression[_$16, _$17] forSome { 
      <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
    }]] = _;
    <paramaccessor> private[this] val _having: Option[_root_.scala.Function0[LogicalBoolean]] = _;
    <paramaccessor> private[this] val _computeClauseClosure: _root_.scala.Function0[List[TypedExpression[_$18, _$19] forSome { 
      <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any
    }]] = _;
    def <init>(_qe: QueryElements[_$15] forSome { 
      <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
    }, _groupByClauseClosure: _root_.scala.Function0[List[TypedExpression[_$16, _$17] forSome { 
      <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
    }]], _having: Option[_root_.scala.Function0[LogicalBoolean]], _computeClauseClosure: _root_.scala.Function0[List[TypedExpression[_$18, _$19] forSome { 
      <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any
    }]]) = {
      super.<init>(_qe, null);
      ()
    };
    class SampleGroupWithMeasures[K >: _root_.scala.Nothing <: _root_.scala.Any, M >: _root_.scala.Nothing <: _root_.scala.Any] extends GroupWithMeasures {
      <paramaccessor> private[this] val k: K = _;
      <paramaccessor> private[this] val m: M = _;
      def <init>(k: K, m: M) = {
        super.<init>(k, m);
        ()
      };
      override def key = _sTuple1ToValue(k);
      override def measures = _sTuple1ToValue(m)
    };
    override def havingClause = if (_having.$bang$eq(None))
      _having.map(((c) => c()))
    else
      super.havingClause;
    override def queryElements = scala.Tuple4(whereClause, havingClause, _groupByClauseClosure().map(((e) => e)), orderByClause);
    override def invokeYield(rsm: ResultSetMapper, rs: ResultSet) = new GroupWithMeasures(rsm.groupKeysMapper.get.mapToTuple(rs), rsm.groupMeasuresMapper.get.mapToTuple(rs));
    override def invokeYieldForAst(q: QueryExpressionNode[_$20] forSome { 
      <synthetic> type _$20 >: _root_.scala.Nothing <: _root_.scala.Any
    }, rsm: ResultSetMapper) = {
      val offset = 1;
      <synthetic> private[this] val x$3 = _createColumnToTupleMapper(q, _groupByClauseClosure(), offset, true): @scala.unchecked match {
        case scala.Tuple2((km @ _), (knodes @ _)) => scala.Tuple2(km, knodes)
      };
      val km = x$3._1;
      val knodes = x$3._2;
      <synthetic> private[this] val x$4 = _createColumnToTupleMapper(q, _computeClauseClosure(), offset.$plus(knodes.size), false): @scala.unchecked match {
        case scala.Tuple2((mm @ _), (mnodes @ _)) => scala.Tuple2(mm, mnodes)
      };
      val mm = x$4._1;
      val mnodes = x$4._2;
      rsm.groupKeysMapper = Some(km);
      rsm.groupMeasuresMapper = Some(mm);
      val stK = SampleTuple.create(knodes, km.outMappers).asInstanceOf[K];
      val stM = SampleTuple.create(mnodes, mm.outMappers).asInstanceOf[M];
      scala.Tuple2(List(knodes, mnodes).flatten, new SampleGroupWithMeasures(stK, stM))
    }
  }
}