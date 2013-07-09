package org.squeryl.dsl {
  import ast._;
  import boilerplate._;
  import fsm._;
  import org.squeryl.internals._;
  import org.squeryl._;
  import java.sql.{SQLException, ResultSet};
  import scala.collection.mutable.ArrayBuffer;
  import scala.util.control.ControlThrowable;
  abstract trait BaseQueryDsl extends scala.AnyRef {
    def /*BaseQueryDsl*/$init$(): Unit = {
      ()
    };
    implicit def noneKeyedEntityDef[A >: Nothing <: Any, K >: Nothing <: Any]: org.squeryl.OptionalKeyedEntityDef[A,K] = {
      final class $anon extends Object with org.squeryl.OptionalKeyedEntityDef[A,K] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        override def keyedEntityDef: Option[org.squeryl.KeyedEntityDef[A,K]] = scala.None
      };
      new $anon()
    }
  };
  abstract trait QueryDsl extends Object with org.squeryl.dsl.fsm.WhereState[org.squeryl.dsl.fsm.Unconditioned] with org.squeryl.dsl.boilerplate.ComputeMeasuresSignaturesFromStartOrWhereState with org.squeryl.dsl.fsm.StartState with org.squeryl.dsl.fsm.QueryElements[org.squeryl.dsl.fsm.Unconditioned] with org.squeryl.dsl.boilerplate.JoinSignatures with org.squeryl.dsl.boilerplate.FromSignatures with org.squeryl.dsl.BaseQueryDsl { outerQueryDsl: org.squeryl.dsl.QueryDsl => 
    def /*QueryDsl*/$init$(): Unit = {
      ()
    };
    implicit def kedForKeyedEntities[A >: Nothing <: Any, K >: Nothing <: Any](implicit ev: <:<[A,org.squeryl.KeyedEntity[K]], m: Manifest[A]): org.squeryl.KeyedEntityDef[A,K] = {
      final class $anon extends Object with org.squeryl.KeyedEntityDef[A,K] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        def getId(a: A): K = ev.apply(a).id;
        def isPersisted(a: A): Boolean = ev.apply(a).isPersisted;
        def idPropertyName: String = "id";
        override def optimisticCounterPropertyName: Option[String] = if (classOf[org.squeryl.Optimistic].isAssignableFrom(m.erasure))
          scala.Some.apply[String]("occVersionNumber")
        else
          scala.None
      };
      new $anon()
    };
    implicit def queryToIterable[R >: Nothing <: Any](q: org.squeryl.Query[R]): Iterable[R] = {
      val i: Iterator[R] = q.iterator;
      {
        final class $anon extends AnyRef with Iterable[R] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val hasFirst: Boolean = i.hasNext;
          <stable> <accessor> private def hasFirst: Boolean = $anon.this.hasFirst;
          lazy private[this] var firstRow: Option[R] = if ($anon.this.hasFirst)
            scala.Some.apply[R](i.next())
          else
            scala.None;
          override def head: R = $anon.this.firstRow.get;
          override def headOption: Option[R] = $anon.this.firstRow;
          override def isEmpty: Boolean = $anon.this.hasFirst.unary_!;
          def iterator: org.squeryl.internals.IteratorConcatenation[R] = new org.squeryl.internals.IteratorConcatenation[R]($anon.this.firstRow.iterator, i)
        };
        new $anon()
      }
    };
    def using[A >: Nothing <: Any](session: org.squeryl.Session)(a: => A): A = session.using[A]((() => a));
    def transaction[A >: Nothing <: Any](sf: org.squeryl.SessionFactory)(a: => A): A = sf.newSession.withinTransaction[A]((() => a));
    def inTransaction[A >: Nothing <: Any](sf: org.squeryl.SessionFactory)(a: => A): A = if (org.squeryl.Session.hasCurrentSession.unary_!)
      sf.newSession.withinTransaction[A]((() => a))
    else
      a;
    def transaction[A >: Nothing <: Any](s: org.squeryl.Session)(a: => A): A = s.withinTransaction[A]((() => a));
    def transaction[A >: Nothing <: Any](a: => A): A = if (org.squeryl.Session.hasCurrentSession.unary_!)
      org.squeryl.SessionFactory.newSession.withinTransaction[A]((() => a))
    else
      {
        val s: org.squeryl.AbstractSession = org.squeryl.Session.currentSession;
        val res: A = try {
          s.unbindFromCurrentThread;
          org.squeryl.SessionFactory.newSession.withinTransaction[A]((() => a))
        } finally s.bindToCurrentThread;
        res
      };
    def inTransaction[A >: Nothing <: Any](a: => A): A = if (org.squeryl.Session.hasCurrentSession.unary_!)
      org.squeryl.SessionFactory.newSession.withinTransaction[A]((() => a))
    else
      a;
    implicit def __thisDsl: org.squeryl.dsl.QueryDsl = this;
    private class QueryElementsImpl[Cond >: Nothing <: Any] extends Object with org.squeryl.dsl.fsm.QueryElements[Cond] {
      <paramaccessor> private[this] val whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = _;
      override <stable> <accessor> <paramaccessor> def whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = QueryElementsImpl.this.whereClause;
      def <init>(whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean]): QueryDsl.this.QueryElementsImpl[Cond] = {
        QueryElementsImpl.super.<init>();
        ()
      }
    };
    def where(b: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.fsm.WhereState[org.squeryl.dsl.fsm.Conditioned] = new QueryDsl.this.QueryElementsImpl[org.squeryl.dsl.fsm.Conditioned](scala.Some.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => b)));
    def &[A >: Nothing <: Any, T >: Nothing <: Any](i: => org.squeryl.dsl.TypedExpression[A,T]): A = org.squeryl.internals.FieldReferenceLinker.pushExpressionOrCollectValue[A]((() => i));
    implicit def typedExpression2OrderByArg[E >: Nothing <: Any](e: E)(implicit evidence$1: E => org.squeryl.dsl.TypedExpression[_, _]): org.squeryl.dsl.ast.OrderByArg = new ast.OrderByArg(evidence$1.apply(e));
    implicit def orderByArg2OrderByExpression(a: org.squeryl.dsl.ast.OrderByArg): org.squeryl.dsl.ast.OrderByExpression = new ast.OrderByExpression(a);
    def sDevPopulation[T2 >: org.squeryl.dsl.TOptionFloat <: Any, T1 >: Nothing <: T2, A1 >: Nothing <: Any, A2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A2,T2]): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = f.convert(new ast.FunctionNode("stddev_pop", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](b)));
    def sDevSample[T2 >: org.squeryl.dsl.TOptionFloat <: Any, T1 >: Nothing <: T2, A1 >: Nothing <: Any, A2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A2,T2]): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = f.convert(new ast.FunctionNode("stddev_samp", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](b)));
    def varPopulation[T2 >: org.squeryl.dsl.TOptionFloat <: Any, T1 >: Nothing <: T2, A1 >: Nothing <: Any, A2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A2,T2]): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = f.convert(new ast.FunctionNode("var_pop", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](b)));
    def varSample[T2 >: org.squeryl.dsl.TOptionFloat <: Any, T1 >: Nothing <: T2, A1 >: Nothing <: Any, A2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A2,T2]): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = f.convert(new ast.FunctionNode("var_samp", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](b)));
    def max[T2 >: org.squeryl.dsl.TOption <: Any, T1 >: Nothing <: T2, A1 >: Nothing <: Any, A2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A2,T2]): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = f.convert(new ast.FunctionNode("max", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](b)));
    def min[T2 >: org.squeryl.dsl.TOption <: Any, T1 >: Nothing <: T2, A1 >: Nothing <: Any, A2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A2,T2]): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = f.convert(new ast.FunctionNode("min", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](b)));
    def avg[T2 >: org.squeryl.dsl.TOptionFloat <: Any, T1 >: Nothing <: T2, A1 >: Nothing <: Any, A2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A2,T2]): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = f.convert(new ast.FunctionNode("avg", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](b)));
    def sum[T2 >: org.squeryl.dsl.TOption <: Any, T1 >: org.squeryl.dsl.TNumericLowerTypeBound <: T2, A1 >: Nothing <: Any, A2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A2,T2]): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = f.convert(new ast.FunctionNode("sum", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](b)));
    def nvl[T4 >: Nothing <: org.squeryl.dsl.TNonOption, T1 >: org.squeryl.dsl.TOption <: Any, T3 >: T1 <: Any, T2 >: Nothing <: T3, A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any](a: org.squeryl.dsl.TypedExpression[A1,T1], b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit d: org.squeryl.dsl.DeOptionizer[_, A3, T4, _, T3]): org.squeryl.dsl.TypedExpression[A3,T4] = new org.squeryl.dsl.NvlNode[A3,T4](a, d.deOptionizer.convert(b));
    def not(b: org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.ast.FunctionNode with org.squeryl.dsl.ast.LogicalBoolean = {
      final class $anon extends ast.FunctionNode with org.squeryl.dsl.ast.LogicalBoolean {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>("not", collection.this.Seq.apply[org.squeryl.dsl.ast.LogicalBoolean](b));
          ()
        }
      };
      new $anon()
    };
    def upper[A1 >: Nothing <: Any, T1 >: Nothing <: Any](s: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A1,T1], ev2: <:<[T1,org.squeryl.dsl.TOptionString]): org.squeryl.dsl.TypedExpressionConversion[A1,T1] = f.convert(new ast.FunctionNode("upper", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](s)));
    def lower[A1 >: Nothing <: Any, T1 >: Nothing <: Any](s: org.squeryl.dsl.TypedExpression[A1,T1])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A1,T1], ev2: <:<[T1,org.squeryl.dsl.TOptionString]): org.squeryl.dsl.TypedExpressionConversion[A1,T1] = f.convert(new ast.FunctionNode("lower", collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](s)));
    def exists[A1 >: Nothing <: Any](query: org.squeryl.Query[A1]): org.squeryl.dsl.ast.ExistsExpression = new ast.ExistsExpression(query.copy(false).ast, "exists");
    def notExists[A1 >: Nothing <: Any](query: org.squeryl.Query[A1]): org.squeryl.dsl.ast.ExistsExpression = new ast.ExistsExpression(query.copy(false).ast, "not exists");
    private[this] val numericComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TNumeric,org.squeryl.dsl.TNumeric] = new org.squeryl.dsl.CanCompare[org.squeryl.dsl.TNumeric,org.squeryl.dsl.TNumeric]();
    implicit <stable> <accessor> def numericComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TNumeric,org.squeryl.dsl.TNumeric] = QueryDsl.this.numericComparisonEvidence;
    private[this] val dateComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionDate,org.squeryl.dsl.TOptionDate] = new org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionDate,org.squeryl.dsl.TOptionDate]();
    implicit <stable> <accessor> def dateComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionDate,org.squeryl.dsl.TOptionDate] = QueryDsl.this.dateComparisonEvidence;
    private[this] val timestampComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionTimestamp,org.squeryl.dsl.TOptionTimestamp] = new org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionTimestamp,org.squeryl.dsl.TOptionTimestamp]();
    implicit <stable> <accessor> def timestampComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionTimestamp,org.squeryl.dsl.TOptionTimestamp] = QueryDsl.this.timestampComparisonEvidence;
    private[this] val stringComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionString,org.squeryl.dsl.TOptionString] = new org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionString,org.squeryl.dsl.TOptionString]();
    implicit <stable> <accessor> def stringComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionString,org.squeryl.dsl.TOptionString] = QueryDsl.this.stringComparisonEvidence;
    private[this] val booleanComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionBoolean,org.squeryl.dsl.TOptionBoolean] = new org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionBoolean,org.squeryl.dsl.TOptionBoolean]();
    implicit <stable> <accessor> def booleanComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionBoolean,org.squeryl.dsl.TOptionBoolean] = QueryDsl.this.booleanComparisonEvidence;
    private[this] val uuidComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionUUID,org.squeryl.dsl.TOptionUUID] = new org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionUUID,org.squeryl.dsl.TOptionUUID]();
    implicit <stable> <accessor> def uuidComparisonEvidence: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TOptionUUID,org.squeryl.dsl.TOptionUUID] = QueryDsl.this.uuidComparisonEvidence;
    implicit def enumComparisonEvidence[A >: Nothing <: Any]: org.squeryl.dsl.CanCompare[org.squeryl.dsl.TEnumValue[A],org.squeryl.dsl.TEnumValue[A]] = new org.squeryl.dsl.CanCompare[org.squeryl.dsl.TEnumValue[A],org.squeryl.dsl.TEnumValue[A]]();
    implicit def concatenationConversion[A1 >: Nothing <: Any, A2 >: Nothing <: Any, T1 >: Nothing <: Any, T2 >: Nothing <: Any](co: org.squeryl.dsl.ConcatOp[A1,A2,T1,T2]): org.squeryl.dsl.TypedExpression[String,org.squeryl.dsl.TString] = new QueryDsl.this.ConcatOperationNode[String,org.squeryl.dsl.TString](co.a1, co.a2, org.squeryl.InternalFieldMapper.stringTEF.createOutMapper);
    implicit def concatenationConversionWithOption1[A1 >: Nothing <: Any, A2 >: Nothing <: Any, T1 >: Nothing <: Any, T2 >: Nothing <: Any](co: org.squeryl.dsl.ConcatOp[Option[A1],A2,T1,T2]): org.squeryl.dsl.TypedExpression[Option[String],org.squeryl.dsl.TOptionString] = new QueryDsl.this.ConcatOperationNode[Option[String],org.squeryl.dsl.TOptionString](co.a1, co.a2, org.squeryl.InternalFieldMapper.optionStringTEF.createOutMapper);
    implicit def concatenationConversionWithOption2[A1 >: Nothing <: Any, A2 >: Nothing <: Any, T1 >: Nothing <: Any, T2 >: Nothing <: Any](co: org.squeryl.dsl.ConcatOp[A1,Option[A2],T1,T2]): org.squeryl.dsl.TypedExpression[Option[String],org.squeryl.dsl.TOptionString] = new QueryDsl.this.ConcatOperationNode[Option[String],org.squeryl.dsl.TOptionString](co.a1, co.a2, org.squeryl.InternalFieldMapper.optionStringTEF.createOutMapper);
    implicit def concatenationConversionWithOption3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, T1 >: Nothing <: Any, T2 >: Nothing <: Any](co: org.squeryl.dsl.ConcatOp[Option[A1],Option[A2],T1,T2]): org.squeryl.dsl.TypedExpression[Option[String],org.squeryl.dsl.TOptionString] = new QueryDsl.this.ConcatOperationNode[Option[String],org.squeryl.dsl.TOptionString](co.a1, co.a2, org.squeryl.InternalFieldMapper.optionStringTEF.createOutMapper);
    class ConcatOperationNode[A >: Nothing <: Any, T >: Nothing <: Any] extends ast.BinaryOperatorNode with org.squeryl.dsl.TypedExpression[A,T] {
      <paramaccessor> private[this] val e1: org.squeryl.dsl.ast.ExpressionNode = _;
      <paramaccessor> private[this] val e2: org.squeryl.dsl.ast.ExpressionNode = _;
      <paramaccessor> private[this] val mapper: org.squeryl.internals.OutMapper[A] = _;
      <stable> <accessor> <paramaccessor> def mapper: org.squeryl.internals.OutMapper[A] = ConcatOperationNode.this.mapper;
      def <init>(e1: org.squeryl.dsl.ast.ExpressionNode, e2: org.squeryl.dsl.ast.ExpressionNode, mapper: org.squeryl.internals.OutMapper[A]): QueryDsl.this.ConcatOperationNode[A,T] = {
        ConcatOperationNode.super.<init>(e1, e2, "||", false);
        ()
      };
      override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.databaseAdapter.writeConcatOperator(ConcatOperationNode.this.e1, ConcatOperationNode.this.e2, sw)
    };
    abstract trait SingleRowQuery[R >: Nothing <: Any] extends scala.AnyRef { self: QueryDsl.this.SingleRowQuery[R] with org.squeryl.Query[R] => 
      <empty>
    };
    abstract trait SingleColumnQuery[T >: Nothing <: Any] extends scala.AnyRef { self: QueryDsl.this.SingleColumnQuery[T] with org.squeryl.Query[T] => 
      <empty>
    };
    abstract trait ScalarQuery[T >: Nothing <: Any] extends Object with org.squeryl.Query[T] with QueryDsl.this.SingleColumnQuery[T] with QueryDsl.this.SingleRowQuery[T];
    implicit def scalarQuery2Scalar[T >: Nothing <: Any](sq: QueryDsl.this.ScalarQuery[T]): T = QueryDsl.this.queryToIterable[T](sq).head;
    implicit def countQueryableToIntTypeQuery[R >: Nothing <: Any](q: org.squeryl.Queryable[R]): QueryDsl.this.CountSubQueryableQuery = new QueryDsl.this.CountSubQueryableQuery(q);
    def count: QueryDsl.this.CountFunction = QueryDsl.this.count();
    def count(e: org.squeryl.dsl.TypedExpression[_, _]*): QueryDsl.this.CountFunction = new QueryDsl.this.CountFunction(e, false);
    def countDistinct(e: org.squeryl.dsl.TypedExpression[_, _]*): QueryDsl.this.CountFunction = new QueryDsl.this.CountFunction(e, true);
    class CountFunction extends ast.FunctionNode with org.squeryl.dsl.TypedExpression[Long,org.squeryl.dsl.TLong] {
      <paramaccessor> private[this] val _args: Seq[org.squeryl.dsl.ast.ExpressionNode] = _;
      <paramaccessor> private[this] val isDistinct: Boolean = _;
      def <init>(_args: Seq[org.squeryl.dsl.ast.ExpressionNode], isDistinct: Boolean): QueryDsl.this.CountFunction = {
        CountFunction.super.<init>("count", _args match {
          case immutable.this.Nil => collection.this.Seq.apply[org.squeryl.dsl.ast.TokenExpressionNode](new ast.TokenExpressionNode("*"))
          case _ => _args
        });
        ()
      };
      def mapper: org.squeryl.internals.OutMapper[Long] = org.squeryl.InternalFieldMapper.longTEF.createOutMapper;
      override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
        sw.write(CountFunction.this.name);
        sw.write("(");
        if (CountFunction.this.isDistinct)
          sw.write("distinct ")
        else
          ();
        sw.writeNodesWithSeparator(CountFunction.this.args, ",", false);
        sw.write(")")
      }
    };
    private def _countFunc: QueryDsl.this.CountFunction = QueryDsl.this.count;
    class CountSubQueryableQuery extends Object with org.squeryl.Query[Long] with QueryDsl.this.ScalarQuery[Long] {
      <paramaccessor> private[this] val q: org.squeryl.Queryable[_] = _;
      def <init>(q: org.squeryl.Queryable[_]): QueryDsl.this.CountSubQueryableQuery = {
        CountSubQueryableQuery.super.<init>();
        ()
      };
      private[this] val _inner: org.squeryl.Query[org.squeryl.dsl.Measures[Long]] = QueryDsl.this.from[_$9, org.squeryl.dsl.Measures[Long]](CountSubQueryableQuery.this.q)(((r: _$9) => QueryDsl.this.compute[Long](QueryDsl.this._countFunc)));
      <stable> <accessor> private def _inner: org.squeryl.Query[org.squeryl.dsl.Measures[Long]] = CountSubQueryableQuery.this._inner;
      def iterator: Iterator[Long] = QueryDsl.this.queryToIterable[org.squeryl.dsl.Measures[Long]](CountSubQueryableQuery.this._inner).map[Long, Iterable[Long]](((m: org.squeryl.dsl.Measures[Long]) => m.measures))(collection.this.Iterable.canBuildFrom[Long]).iterator;
      def Count: QueryDsl.this.ScalarQuery[Long] = this;
      def statement: String = CountSubQueryableQuery.this._inner.statement;
      def page(offset: Int, length: Int): QueryDsl.this.CountSubQueryableQuery = this;
      def distinct: QueryDsl.this.CountSubQueryableQuery = this;
      def forUpdate: QueryDsl.this.ScalarQuery[Long] = QueryDsl.this.singleColComputeQuery2ScalarQuery[Long](CountSubQueryableQuery.this._inner.forUpdate);
      def dumpAst: String = CountSubQueryableQuery.this._inner.dumpAst;
      def ast: org.squeryl.dsl.ast.ExpressionNode = CountSubQueryableQuery.this._inner.ast;
      protected[squeryl] def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): Long = CountSubQueryableQuery.this._inner.invokeYield(rsm, rs).measures;
      override private[squeryl] def copy(asRoot: Boolean): QueryDsl.this.CountSubQueryableQuery = new QueryDsl.this.CountSubQueryableQuery(CountSubQueryableQuery.this.q);
      def name: String = CountSubQueryableQuery.this._inner.name;
      private[squeryl] def give(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): Long = QueryDsl.this.countQueryableToIntTypeQuery[_$9](CountSubQueryableQuery.this.q).invokeYield(rsm, rs)
    };
    implicit def singleColComputeQuery2ScalarQuery[T >: Nothing <: Any](cq: org.squeryl.Query[org.squeryl.dsl.Measures[T]]): QueryDsl.this.ScalarQuery[T] = new QueryDsl.this.ScalarMeasureQuery[T](cq);
    implicit def singleColComputeQuery2Scalar[T >: Nothing <: Any](cq: org.squeryl.Query[org.squeryl.dsl.Measures[T]]): T = QueryDsl.this.queryToIterable[T](new QueryDsl.this.ScalarMeasureQuery[T](cq)).head;
    class ScalarMeasureQuery[T >: Nothing <: Any] extends Object with org.squeryl.Query[T] with QueryDsl.this.ScalarQuery[T] {
      <paramaccessor> private[this] val q: org.squeryl.Query[org.squeryl.dsl.Measures[T]] = _;
      def <init>(q: org.squeryl.Query[org.squeryl.dsl.Measures[T]]): QueryDsl.this.ScalarMeasureQuery[T] = {
        ScalarMeasureQuery.super.<init>();
        ()
      };
      def iterator: Iterator[T] = QueryDsl.this.queryToIterable[org.squeryl.dsl.Measures[T]](ScalarMeasureQuery.this.q).map[T, Iterable[T]](((m: org.squeryl.dsl.Measures[T]) => m.measures))(collection.this.Iterable.canBuildFrom[T]).iterator;
      def distinct: QueryDsl.this.ScalarMeasureQuery[T] = this;
      def forUpdate: QueryDsl.this.ScalarQuery[T] = QueryDsl.this.singleColComputeQuery2ScalarQuery[T](ScalarMeasureQuery.this.q.forUpdate);
      def dumpAst: String = ScalarMeasureQuery.this.q.dumpAst;
      def page(offset: Int, length: Int): QueryDsl.this.ScalarMeasureQuery[T] = this;
      def statement: String = ScalarMeasureQuery.this.q.statement;
      def ast: org.squeryl.dsl.ast.ExpressionNode = ScalarMeasureQuery.this.q.ast;
      protected[squeryl] def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): T = ScalarMeasureQuery.this.q.invokeYield(rsm, rs).measures;
      override private[squeryl] def copy(asRoot: Boolean): QueryDsl.this.ScalarMeasureQuery[T] = new QueryDsl.this.ScalarMeasureQuery[T](ScalarMeasureQuery.this.q);
      def name: String = ScalarMeasureQuery.this.q.name;
      private[squeryl] def give(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): T = ScalarMeasureQuery.this.q.invokeYield(rsm, rs).measures
    };
    implicit def queryable2OptionalQueryable[A >: Nothing <: Any](q: org.squeryl.Queryable[A]): org.squeryl.dsl.OptionalQueryable[A] = new org.squeryl.dsl.OptionalQueryable[A](q);
    def update[A >: Nothing <: Any](t: org.squeryl.Table[A])(s: A => org.squeryl.dsl.ast.UpdateStatement): Int = t.update(s);
    def manyToManyRelation[L >: Nothing <: Any, R >: Nothing <: Any](l: org.squeryl.Table[L], r: org.squeryl.Table[R])(implicit kedL: org.squeryl.KeyedEntityDef[L, _], kedR: org.squeryl.KeyedEntityDef[R, _]): QueryDsl.this.ManyToManyRelationBuilder[L,R] = new QueryDsl.this.ManyToManyRelationBuilder[L,R](l, r, scala.None, kedL, kedR);
    def manyToManyRelation[L >: Nothing <: Any, R >: Nothing <: Any](l: org.squeryl.Table[L], r: org.squeryl.Table[R], nameOfMiddleTable: String)(implicit kedL: org.squeryl.KeyedEntityDef[L, _], kedR: org.squeryl.KeyedEntityDef[R, _]): QueryDsl.this.ManyToManyRelationBuilder[L,R] = new QueryDsl.this.ManyToManyRelationBuilder[L,R](l, r, scala.Some.apply[String](nameOfMiddleTable), kedL, kedR);
    class ManyToManyRelationBuilder[L >: Nothing <: Any, R >: Nothing <: Any] extends scala.AnyRef {
      <paramaccessor> private[this] val l: org.squeryl.Table[L] = _;
      <paramaccessor> private[this] val r: org.squeryl.Table[R] = _;
      <paramaccessor> private[this] val nameOverride: Option[String] = _;
      <paramaccessor> private[this] val kedL: org.squeryl.KeyedEntityDef[L, _] = _;
      <paramaccessor> private[this] val kedR: org.squeryl.KeyedEntityDef[R, _] = _;
      def <init>(l: org.squeryl.Table[L], r: org.squeryl.Table[R], nameOverride: Option[String], kedL: org.squeryl.KeyedEntityDef[L, _], kedR: org.squeryl.KeyedEntityDef[R, _]): QueryDsl.this.ManyToManyRelationBuilder[L,R] = {
        ManyToManyRelationBuilder.super.<init>();
        ()
      };
      def via[A >: Nothing <: Any](f: (L, R, A) => (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression))(implicit manifestA: Manifest[A], schema: org.squeryl.Schema, kedA: org.squeryl.KeyedEntityDef[A, _]): QueryDsl.this.ManyToManyRelationImpl[L,R,A] = {
        val m2m: QueryDsl.this.ManyToManyRelationImpl[L,R,A] = new QueryDsl.this.ManyToManyRelationImpl[L,R,A](ManyToManyRelationBuilder.this.l, ManyToManyRelationBuilder.this.r, manifestA.erasure.asInstanceOf[Class[A]], f, schema, ManyToManyRelationBuilder.this.nameOverride, ManyToManyRelationBuilder.this.kedL, ManyToManyRelationBuilder.this.kedR, kedA);
        schema._addTable(m2m);
        m2m
      }
    };
    private def invalidBindingExpression: Nothing = org.squeryl.internals.Utils.throwError("Binding expression of relation uses a def, not a field (val or var)");
    class ManyToManyRelationImpl[L >: Nothing <: Any, R >: Nothing <: Any, A >: Nothing <: Any] extends org.squeryl.Table[A] with org.squeryl.dsl.ManyToManyRelation[L,R,A] { thisTableOfA: QueryDsl.this.ManyToManyRelationImpl[L,R,A] => 
      <paramaccessor> private[this] val leftTable: org.squeryl.Table[L] = _;
      <stable> <accessor> <paramaccessor> def leftTable: org.squeryl.Table[L] = ManyToManyRelationImpl.this.leftTable;
      <paramaccessor> private[this] val rightTable: org.squeryl.Table[R] = _;
      <stable> <accessor> <paramaccessor> def rightTable: org.squeryl.Table[R] = ManyToManyRelationImpl.this.rightTable;
      <paramaccessor> private[this] val aClass: Class[A] = _;
      <paramaccessor> private[this] val f: (L, R, A) => (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression) = _;
      <paramaccessor> private[this] val schema: org.squeryl.Schema = _;
      <paramaccessor> private[this] val nameOverride: Option[String] = _;
      <paramaccessor> private[this] val kedL: org.squeryl.KeyedEntityDef[L, _] = _;
      <paramaccessor> private[this] val kedR: org.squeryl.KeyedEntityDef[R, _] = _;
      <paramaccessor> private[this] val kedA: org.squeryl.KeyedEntityDef[A, _] = _;
      def <init>(leftTable: org.squeryl.Table[L], rightTable: org.squeryl.Table[R], aClass: Class[A], f: (L, R, A) => (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression), schema: org.squeryl.Schema, nameOverride: Option[String], kedL: org.squeryl.KeyedEntityDef[L, _], kedR: org.squeryl.KeyedEntityDef[R, _], kedA: org.squeryl.KeyedEntityDef[A, _]): QueryDsl.this.ManyToManyRelationImpl[L,R,A] = {
        ManyToManyRelationImpl.super.<init>(nameOverride.getOrElse[String](schema.tableNameFromClass(aClass)), aClass, schema, scala.None, scala.Some.apply[org.squeryl.KeyedEntityDef[A,_$19]](kedA));
        ()
      };
      def thisTable: QueryDsl.this.ManyToManyRelationImpl[L,R,A] = ManyToManyRelationImpl.this;
      ManyToManyRelationImpl.this.schema._addRelation(this);
      <synthetic> private[this] val x$1: (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression) = ({
        var e2: Option[(org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression)] = scala.None;
        QueryDsl.this.from[L, R, A, None.type](ManyToManyRelationImpl.this.leftTable, ManyToManyRelationImpl.this.rightTable, ManyToManyRelationImpl.this)(((l: L, r: R, a: A) => {
          e2 = scala.Some.apply[(org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression)](ManyToManyRelationImpl.this.f.apply(l, r, a));
          QueryDsl.this.select[None.type](scala.None)
        }));
        val e2_: (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression) = e2.get;
        if (e2_._1.filterDescendantsOfType[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]](reflect.this.ManifestFactory.classType[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]](classOf[org.squeryl.dsl.ast.ConstantTypedExpression].asInstanceOf[Class[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]]], reflect.this.ManifestFactory.wildcardType[_$20](reflect.this.ManifestFactory.Nothing, reflect.this.ManifestFactory.Any), reflect.this.ManifestFactory.wildcardType[_$21](reflect.this.ManifestFactory.Nothing, reflect.this.ManifestFactory.Any))).isEmpty.unary_!)
          QueryDsl.this.invalidBindingExpression
        else
          ();
        if (e2_._2.filterDescendantsOfType[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]](reflect.this.ManifestFactory.classType[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]](classOf[org.squeryl.dsl.ast.ConstantTypedExpression].asInstanceOf[Class[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]]], reflect.this.ManifestFactory.wildcardType[_$22](reflect.this.ManifestFactory.Nothing, reflect.this.ManifestFactory.Any), reflect.this.ManifestFactory.wildcardType[_$23](reflect.this.ManifestFactory.Nothing, reflect.this.ManifestFactory.Any))).isEmpty.unary_!)
          QueryDsl.this.invalidBindingExpression
        else
          ();
        if (ManyToManyRelationImpl.this._viewReferedInExpression(ManyToManyRelationImpl.this.leftTable, e2_._1))
          {
            scala.this.Predef.assert(ManyToManyRelationImpl.this._viewReferedInExpression(ManyToManyRelationImpl.this.rightTable, e2_._2));
            e2_
          }
        else
          {
            scala.this.Predef.assert(ManyToManyRelationImpl.this._viewReferedInExpression(ManyToManyRelationImpl.this.leftTable, e2_._2));
            scala.this.Predef.assert(ManyToManyRelationImpl.this._viewReferedInExpression(ManyToManyRelationImpl.this.rightTable, e2_._1));
            scala.Tuple2.apply[org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression](e2_._2, e2_._1)
          }
      }: (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression) @unchecked) match {
        case (_1: org.squeryl.dsl.ast.EqualityExpression, _2: org.squeryl.dsl.ast.EqualityExpression)(org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression)((_leftEqualityExpr @ _), (_rightEqualityExpr @ _)) => scala.Tuple2.apply[org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression](_leftEqualityExpr, _rightEqualityExpr)
      };
      private[this] val _leftEqualityExpr: org.squeryl.dsl.ast.EqualityExpression = ManyToManyRelationImpl.this.x$1._1;
      <stable> <accessor> private def _leftEqualityExpr: org.squeryl.dsl.ast.EqualityExpression = ManyToManyRelationImpl.this._leftEqualityExpr;
      private[this] val _rightEqualityExpr: org.squeryl.dsl.ast.EqualityExpression = ManyToManyRelationImpl.this.x$1._2;
      <stable> <accessor> private def _rightEqualityExpr: org.squeryl.dsl.ast.EqualityExpression = ManyToManyRelationImpl.this._rightEqualityExpr;
      private def _viewReferedInExpression(v: org.squeryl.View[_], ee: org.squeryl.dsl.ast.EqualityExpression): Boolean = ee.filterDescendantsOfType[org.squeryl.dsl.ast.SelectElementReference[Any,Any]](reflect.this.ManifestFactory.classType[org.squeryl.dsl.ast.SelectElementReference[Any,Any]](classOf[org.squeryl.dsl.ast.SelectElementReference], reflect.this.ManifestFactory.Any, reflect.this.ManifestFactory.Any)).filter(((x$2: org.squeryl.dsl.ast.SelectElementReference[Any,Any]) => x$2.selectElement.origin.asInstanceOf[org.squeryl.dsl.ast.ViewExpressionNode[_]].view.==(v))).headOption.!=(scala.None);
      <synthetic> private[this] val x$3: (org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData) = (QueryDsl.this._splitEquality(ManyToManyRelationImpl.this._leftEqualityExpr, ManyToManyRelationImpl.this.thisTable, false): (org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData) @unchecked) match {
        case (_1: org.squeryl.internals.FieldMetaData, _2: org.squeryl.internals.FieldMetaData)(org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData)((leftPkFmd @ _), (leftFkFmd @ _)) => scala.Tuple2.apply[org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData](leftPkFmd, leftFkFmd)
      };
      private[this] val leftPkFmd: org.squeryl.internals.FieldMetaData = ManyToManyRelationImpl.this.x$3._1;
      <stable> <accessor> private def leftPkFmd: org.squeryl.internals.FieldMetaData = ManyToManyRelationImpl.this.leftPkFmd;
      private[this] val leftFkFmd: org.squeryl.internals.FieldMetaData = ManyToManyRelationImpl.this.x$3._2;
      <stable> <accessor> private def leftFkFmd: org.squeryl.internals.FieldMetaData = ManyToManyRelationImpl.this.leftFkFmd;
      <synthetic> private[this] val x$4: (org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData) = (QueryDsl.this._splitEquality(ManyToManyRelationImpl.this._rightEqualityExpr, ManyToManyRelationImpl.this.thisTable, false): (org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData) @unchecked) match {
        case (_1: org.squeryl.internals.FieldMetaData, _2: org.squeryl.internals.FieldMetaData)(org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData)((rightPkFmd @ _), (rightFkFmd @ _)) => scala.Tuple2.apply[org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData](rightPkFmd, rightFkFmd)
      };
      private[this] val rightPkFmd: org.squeryl.internals.FieldMetaData = ManyToManyRelationImpl.this.x$4._1;
      <stable> <accessor> private def rightPkFmd: org.squeryl.internals.FieldMetaData = ManyToManyRelationImpl.this.rightPkFmd;
      private[this] val rightFkFmd: org.squeryl.internals.FieldMetaData = ManyToManyRelationImpl.this.x$4._2;
      <stable> <accessor> private def rightFkFmd: org.squeryl.internals.FieldMetaData = ManyToManyRelationImpl.this.rightFkFmd;
      private[this] val leftForeignKeyDeclaration: org.squeryl.ForeignKeyDeclaration = ManyToManyRelationImpl.this.schema._createForeignKeyDeclaration(ManyToManyRelationImpl.this.leftFkFmd.columnName, ManyToManyRelationImpl.this.leftPkFmd.columnName);
      <stable> <accessor> def leftForeignKeyDeclaration: org.squeryl.ForeignKeyDeclaration = ManyToManyRelationImpl.this.leftForeignKeyDeclaration;
      private[this] val rightForeignKeyDeclaration: org.squeryl.ForeignKeyDeclaration = ManyToManyRelationImpl.this.schema._createForeignKeyDeclaration(ManyToManyRelationImpl.this.rightFkFmd.columnName, ManyToManyRelationImpl.this.rightPkFmd.columnName);
      <stable> <accessor> def rightForeignKeyDeclaration: org.squeryl.ForeignKeyDeclaration = ManyToManyRelationImpl.this.rightForeignKeyDeclaration;
      private def _associate[T >: Nothing <: Any](o: T, m2m: org.squeryl.dsl.ManyToMany[T,A]): A = {
        val aInst: A = m2m.assign(o);
        try {
          ManyToManyRelationImpl.this.insertOrUpdate(aInst)(ManyToManyRelationImpl.this.kedA)
        } catch {
          case (e @ (_: java.sql.SQLException)) => if (org.squeryl.Session.currentSession.databaseAdapter.isNotNullConstraintViolation(e))
            throw new scala.`package`.RuntimeException("the ".+(scala.Symbol.apply("associate")).+(" method created and inserted association object of type ").+(ManyToManyRelationImpl.this.posoMetaData.clasz.getName()).+(" that has NOT NULL colums, plase use the other signature of ").+(scala.Symbol.apply("ManyToMany")).+(" that takes the association object as argument : associate(o,a) for association objects that have NOT NULL columns"), e)
          else
            throw e
        }
      };
      def left(leftSideMember: L): org.squeryl.Query[R] with org.squeryl.dsl.ManyToMany[R,A] = {
        val q: org.squeryl.Query[R] = QueryDsl.this.from[A, R, R](ManyToManyRelationImpl.this, ManyToManyRelationImpl.this.rightTable)(((a: A, r: R) => {
          val matchClause: (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression) = ManyToManyRelationImpl.this.f.apply(leftSideMember, r, a);
          QueryDsl.this.where(matchClause._1.and(matchClause._2)).select[R](r)
        }));
        {
          final class $anon extends org.squeryl.dsl.DelegateQuery[R] with org.squeryl.dsl.ManyToMany[R,A] {
            def <init>(): anonymous class $anon = {
              $anon.super.<init>(q);
              ()
            };
            def kedL: org.squeryl.KeyedEntityDef[R, _] = ManyToManyRelationImpl.this.kedR;
            private def _assignKeys(r: R, a: AnyRef): Unit = {
              val leftPk: AnyRef = ManyToManyRelationImpl.this.leftPkFmd.get(leftSideMember.asInstanceOf[AnyRef]);
              val rightPk: AnyRef = ManyToManyRelationImpl.this.rightPkFmd.get(r.asInstanceOf[AnyRef]);
              ManyToManyRelationImpl.this.leftFkFmd.set(a, leftPk);
              ManyToManyRelationImpl.this.rightFkFmd.set(a, rightPk)
            };
            def associationMap: org.squeryl.Query[(R, A)] = QueryDsl.this.from[A, R, (R, A)](ManyToManyRelationImpl.this, ManyToManyRelationImpl.this.rightTable)(((a: A, r: R) => {
              val matchClause: (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression) = ManyToManyRelationImpl.this.f.apply(leftSideMember, r, a);
              QueryDsl.this.where(matchClause._1.and(matchClause._2)).select[(R, A)](scala.Tuple2.apply[R, A](r, a))
            }));
            def assign(o: R, a: A): A = {
              $anon.this._assignKeys(o, a.asInstanceOf[AnyRef]);
              a
            };
            def associate(o: R, a: A): A = {
              $anon.this.assign(o, a);
              ManyToManyRelationImpl.this.insertOrUpdate(a)(ManyToManyRelationImpl.this.kedA);
              a
            };
            def assign(o: R): A = {
              val aInstAny: AnyRef = ManyToManyRelationImpl.this._createInstanceOfRowObject;
              val aInst: A = aInstAny.asInstanceOf[A];
              $anon.this._assignKeys(o, aInstAny);
              aInst
            };
            def associate(o: R): A = ManyToManyRelationImpl.this._associate[R](o, this);
            def dissociate(o: R): Boolean = ManyToManyRelationImpl.this.deleteWhere(((a0: A) => $anon.this._whereClauseForAssociations(a0).and($anon.this._equalityForRightSide(a0, o))))(QueryDsl.this.__thisDsl).>(0);
            private def _whereClauseForAssociations(a0: A): org.squeryl.dsl.ast.LogicalBoolean = {
              val leftPk: AnyRef = ManyToManyRelationImpl.this.leftPkFmd.get(leftSideMember.asInstanceOf[AnyRef]);
              ManyToManyRelationImpl.this.leftFkFmd.get(a0.asInstanceOf[AnyRef]);
              org.squeryl.internals.FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(leftPk)
            };
            private def _equalityForRightSide(a0: A, r: R): org.squeryl.dsl.ast.LogicalBoolean = {
              val rightPk: AnyRef = ManyToManyRelationImpl.this.rightPkFmd.get(r.asInstanceOf[AnyRef]);
              ManyToManyRelationImpl.this.rightFkFmd.get(a0.asInstanceOf[AnyRef]);
              org.squeryl.internals.FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(rightPk)
            };
            def dissociateAll: Int = ManyToManyRelationImpl.this.deleteWhere(((a0: A) => $anon.this._whereClauseForAssociations(a0)))(QueryDsl.this.__thisDsl);
            def associations: org.squeryl.Query[A] = ManyToManyRelationImpl.this.where(((a0: A) => $anon.this._whereClauseForAssociations(a0)))(QueryDsl.this.__thisDsl)
          };
          new $anon()
        }
      };
      def right(rightSideMember: R): org.squeryl.Query[L] with org.squeryl.dsl.ManyToMany[L,A] = {
        val q: org.squeryl.Query[L] = QueryDsl.this.from[A, L, L](ManyToManyRelationImpl.this, ManyToManyRelationImpl.this.leftTable)(((a: A, l: L) => {
          val matchClause: (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression) = ManyToManyRelationImpl.this.f.apply(l, rightSideMember, a);
          QueryDsl.this.where(matchClause._1.and(matchClause._2)).select[L](l)
        }));
        {
          final class $anon extends org.squeryl.dsl.DelegateQuery[L] with org.squeryl.dsl.ManyToMany[L,A] {
            def <init>(): anonymous class $anon = {
              $anon.super.<init>(q);
              ()
            };
            def kedL: org.squeryl.KeyedEntityDef[L, _] = ManyToManyRelationImpl.this.kedL;
            private def _assignKeys(l: L, a: AnyRef): Unit = {
              val rightPk: AnyRef = ManyToManyRelationImpl.this.rightPkFmd.get(rightSideMember.asInstanceOf[AnyRef]);
              val leftPk: AnyRef = ManyToManyRelationImpl.this.leftPkFmd.get(l.asInstanceOf[AnyRef]);
              ManyToManyRelationImpl.this.rightFkFmd.set(a, rightPk);
              ManyToManyRelationImpl.this.leftFkFmd.set(a, leftPk)
            };
            def associationMap: org.squeryl.Query[(L, A)] = QueryDsl.this.from[A, L, (L, A)](ManyToManyRelationImpl.this, ManyToManyRelationImpl.this.leftTable)(((a: A, l: L) => {
              val matchClause: (org.squeryl.dsl.ast.EqualityExpression, org.squeryl.dsl.ast.EqualityExpression) = ManyToManyRelationImpl.this.f.apply(l, rightSideMember, a);
              QueryDsl.this.where(matchClause._1.and(matchClause._2)).select[(L, A)](scala.Tuple2.apply[L, A](l, a))
            }));
            def assign(o: L, a: A): A = {
              $anon.this._assignKeys(o, a.asInstanceOf[AnyRef]);
              a
            };
            def associate(o: L, a: A): A = {
              $anon.this.assign(o, a);
              ManyToManyRelationImpl.this.insertOrUpdate(a)(ManyToManyRelationImpl.this.kedA);
              a
            };
            def assign(o: L): A = {
              val aInstAny: AnyRef = ManyToManyRelationImpl.this._createInstanceOfRowObject;
              val aInst: A = aInstAny.asInstanceOf[A];
              $anon.this._assignKeys(o, aInstAny);
              aInst
            };
            def associate(o: L): A = ManyToManyRelationImpl.this._associate[L](o, this);
            def dissociate(o: L): Boolean = ManyToManyRelationImpl.this.deleteWhere(((a0: A) => $anon.this._whereClauseForAssociations(a0).and($anon.this._leftEquality(o, a0))))(QueryDsl.this.__thisDsl).>(0);
            private def _leftEquality(l: L, a0: A): org.squeryl.dsl.ast.LogicalBoolean = {
              val leftPk: AnyRef = ManyToManyRelationImpl.this.leftPkFmd.get(l.asInstanceOf[AnyRef]);
              ManyToManyRelationImpl.this.leftFkFmd.get(a0.asInstanceOf[AnyRef]);
              org.squeryl.internals.FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(leftPk)
            };
            private def _whereClauseForAssociations(a0: A): org.squeryl.dsl.ast.LogicalBoolean = {
              val rightPk: AnyRef = ManyToManyRelationImpl.this.rightPkFmd.get(rightSideMember.asInstanceOf[AnyRef]);
              ManyToManyRelationImpl.this.rightFkFmd.get(a0.asInstanceOf[AnyRef]);
              org.squeryl.internals.FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(rightPk)
            };
            def dissociateAll: Int = ManyToManyRelationImpl.this.deleteWhere(((a0: A) => $anon.this._whereClauseForAssociations(a0)))(QueryDsl.this.__thisDsl);
            def associations: org.squeryl.Query[A] = ManyToManyRelationImpl.this.where(((a0: A) => $anon.this._whereClauseForAssociations(a0)))(QueryDsl.this.__thisDsl)
          };
          new $anon()
        }
      }
    };
    def oneToManyRelation[O >: Nothing <: Any, M >: Nothing <: Any](ot: org.squeryl.Table[O], mt: org.squeryl.Table[M])(implicit kedO: org.squeryl.KeyedEntityDef[O, _]): QueryDsl.this.OneToManyRelationBuilder[O,M] = new QueryDsl.this.OneToManyRelationBuilder[O,M](ot, mt);
    class OneToManyRelationBuilder[O >: Nothing <: Any, M >: Nothing <: Any] extends scala.AnyRef {
      <paramaccessor> private[this] val ot: org.squeryl.Table[O] = _;
      <paramaccessor> private[this] val mt: org.squeryl.Table[M] = _;
      def <init>(ot: org.squeryl.Table[O], mt: org.squeryl.Table[M]): QueryDsl.this.OneToManyRelationBuilder[O,M] = {
        OneToManyRelationBuilder.super.<init>();
        ()
      };
      def via(f: (O, M) => org.squeryl.dsl.ast.EqualityExpression)(implicit schema: org.squeryl.Schema, kedM: org.squeryl.KeyedEntityDef[M, _]): QueryDsl.this.OneToManyRelationImpl[O,M] = new QueryDsl.this.OneToManyRelationImpl[O,M](OneToManyRelationBuilder.this.ot, OneToManyRelationBuilder.this.mt, f, schema, kedM)
    };
    class OneToManyRelationImpl[O >: Nothing <: Any, M >: Nothing <: Any] extends Object with org.squeryl.dsl.OneToManyRelation[O,M] {
      <paramaccessor> private[this] val leftTable: org.squeryl.Table[O] = _;
      <stable> <accessor> <paramaccessor> def leftTable: org.squeryl.Table[O] = OneToManyRelationImpl.this.leftTable;
      <paramaccessor> private[this] val rightTable: org.squeryl.Table[M] = _;
      <stable> <accessor> <paramaccessor> def rightTable: org.squeryl.Table[M] = OneToManyRelationImpl.this.rightTable;
      <paramaccessor> private[this] val f: (O, M) => org.squeryl.dsl.ast.EqualityExpression = _;
      <paramaccessor> private[this] val schema: org.squeryl.Schema = _;
      <paramaccessor> private[this] val kedM: org.squeryl.KeyedEntityDef[M, _] = _;
      def <init>(leftTable: org.squeryl.Table[O], rightTable: org.squeryl.Table[M], f: (O, M) => org.squeryl.dsl.ast.EqualityExpression, schema: org.squeryl.Schema, kedM: org.squeryl.KeyedEntityDef[M, _]): QueryDsl.this.OneToManyRelationImpl[O,M] = {
        OneToManyRelationImpl.super.<init>();
        ()
      };
      OneToManyRelationImpl.this.schema._addRelation(this);
      private def _isSelfReference: Boolean = OneToManyRelationImpl.this.leftTable.==(OneToManyRelationImpl.this.rightTable);
      <synthetic> private[this] val x$5: (org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData) = ({
        var ee: Option[org.squeryl.dsl.ast.EqualityExpression] = scala.None;
        QueryDsl.this.from[O, M, None.type](OneToManyRelationImpl.this.leftTable, OneToManyRelationImpl.this.rightTable)(((o: O, m: M) => {
          ee = scala.Some.apply[org.squeryl.dsl.ast.EqualityExpression](OneToManyRelationImpl.this.f.apply(o, m));
          QueryDsl.this.select[None.type](scala.None)
        }));
        val ee_: org.squeryl.dsl.ast.EqualityExpression = ee.get;
        if (ee_.filterDescendantsOfType[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]](reflect.this.ManifestFactory.classType[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]](classOf[org.squeryl.dsl.ast.ConstantTypedExpression].asInstanceOf[Class[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]]], reflect.this.ManifestFactory.wildcardType[_$29](reflect.this.ManifestFactory.Nothing, reflect.this.ManifestFactory.Any), reflect.this.ManifestFactory.wildcardType[_$30](reflect.this.ManifestFactory.Nothing, reflect.this.ManifestFactory.Any))).isEmpty.unary_!)
          QueryDsl.this.invalidBindingExpression
        else
          ();
        QueryDsl.this._splitEquality(ee.get, OneToManyRelationImpl.this.rightTable, OneToManyRelationImpl.this._isSelfReference)
      }: (org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData) @unchecked) match {
        case (_1: org.squeryl.internals.FieldMetaData, _2: org.squeryl.internals.FieldMetaData)(org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData)((_leftPkFmd @ _), (_rightFkFmd @ _)) => scala.Tuple2.apply[org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData](_leftPkFmd, _rightFkFmd)
      };
      private[this] val _leftPkFmd: org.squeryl.internals.FieldMetaData = OneToManyRelationImpl.this.x$5._1;
      <stable> <accessor> private def _leftPkFmd: org.squeryl.internals.FieldMetaData = OneToManyRelationImpl.this._leftPkFmd;
      private[this] val _rightFkFmd: org.squeryl.internals.FieldMetaData = OneToManyRelationImpl.this.x$5._2;
      <stable> <accessor> private def _rightFkFmd: org.squeryl.internals.FieldMetaData = OneToManyRelationImpl.this._rightFkFmd;
      private[this] val foreignKeyDeclaration: org.squeryl.ForeignKeyDeclaration = OneToManyRelationImpl.this.schema._createForeignKeyDeclaration(OneToManyRelationImpl.this._rightFkFmd.columnName, OneToManyRelationImpl.this._leftPkFmd.columnName);
      <stable> <accessor> def foreignKeyDeclaration: org.squeryl.ForeignKeyDeclaration = OneToManyRelationImpl.this.foreignKeyDeclaration;
      def left(leftSide: O): org.squeryl.dsl.OneToMany[M] = {
        val q: org.squeryl.Query[M] = QueryDsl.this.from[M, M](OneToManyRelationImpl.this.rightTable)(((m: M) => QueryDsl.this.where(OneToManyRelationImpl.this.f.apply(leftSide, m)).select[M](m)));
        {
          final class $anon extends org.squeryl.dsl.DelegateQuery[M] with org.squeryl.dsl.OneToMany[M] {
            def <init>(): anonymous class $anon = {
              $anon.super.<init>(q);
              ()
            };
            def deleteAll: Int = OneToManyRelationImpl.this.rightTable.deleteWhere(((m: M) => OneToManyRelationImpl.this.f.apply(leftSide, m)))(QueryDsl.this.__thisDsl);
            def assign(m: M): M = {
              val m0: AnyRef = m.asInstanceOf[AnyRef];
              val l0: AnyRef = leftSide.asInstanceOf[AnyRef];
              val v: AnyRef = OneToManyRelationImpl.this._leftPkFmd.get(l0);
              OneToManyRelationImpl.this._rightFkFmd.set(m0, v);
              m
            };
            def associate(m: M): M = {
              $anon.this.assign(m);
              OneToManyRelationImpl.this.rightTable.insertOrUpdate(m)(OneToManyRelationImpl.this.kedM)
            }
          };
          new $anon()
        }
      };
      def right(rightSide: M): org.squeryl.dsl.ManyToOne[O] = {
        val q: org.squeryl.Query[O] = QueryDsl.this.from[O, O](OneToManyRelationImpl.this.leftTable)(((o: O) => QueryDsl.this.where(OneToManyRelationImpl.this.f.apply(o, rightSide)).select[O](o)));
        {
          final class $anon extends org.squeryl.dsl.DelegateQuery[O] with org.squeryl.dsl.ManyToOne[O] {
            def <init>(): anonymous class $anon = {
              $anon.super.<init>(q);
              ()
            };
            def assign(one: O): O = {
              val o: AnyRef = one.asInstanceOf[AnyRef];
              val r: AnyRef = rightSide.asInstanceOf[AnyRef];
              val v: AnyRef = OneToManyRelationImpl.this._rightFkFmd.get(r);
              OneToManyRelationImpl.this._leftPkFmd.set(o, v);
              one
            };
            def delete: Boolean = OneToManyRelationImpl.this.leftTable.deleteWhere(((o: O) => OneToManyRelationImpl.this.f.apply(o, rightSide)))(QueryDsl.this.__thisDsl).>(0)
          };
          new $anon()
        }
      }
    };
    private def _splitEquality(ee: org.squeryl.dsl.ast.EqualityExpression, rightTable: org.squeryl.Table[_], isSelfReference: Boolean): (org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData) = {
      if (isSelfReference)
        scala.this.Predef.assert(ee.right._fieldMetaData.isIdFieldOfKeyedEntity.||(ee.left._fieldMetaData.isIdFieldOfKeyedEntity))
      else
        ();
      def msg: String = "equality expression incorrect in relation involving table ".+(rightTable.prefixedName).+(", or perhaps inverted oneToManyRelation");
      if (ee.left._fieldMetaData.parentMetaData.clasz.==(rightTable.classOfT).&&(isSelfReference.unary_!.||(isSelfReference.&&(ee.right._fieldMetaData.isIdFieldOfKeyedEntity))))
        {
          scala.this.Predef.assert(ee.right._fieldMetaData.isIdFieldOfKeyedEntity, msg);
          scala.Tuple2.apply[org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData](ee.right._fieldMetaData, ee.left._fieldMetaData)
        }
      else
        {
          scala.this.Predef.assert(ee.left._fieldMetaData.isIdFieldOfKeyedEntity, msg);
          scala.Tuple2.apply[org.squeryl.internals.FieldMetaData, org.squeryl.internals.FieldMetaData](ee.left._fieldMetaData, ee.right._fieldMetaData)
        }
    };
    def compositeKey[A1 >: Nothing <: Any, A2 >: Nothing <: Any](a1: A1, a2: A2): org.squeryl.dsl.CompositeKey2[A1,A2] = new org.squeryl.dsl.CompositeKey2[A1,A2](a1, a2);
    def compositeKey[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any](a1: A1, a2: A2, a3: A3): org.squeryl.dsl.CompositeKey3[A1,A2,A3] = new org.squeryl.dsl.CompositeKey3[A1,A2,A3](a1, a2, a3);
    def compositeKey[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4): org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4] = new org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4](a1, a2, a3, a4);
    def compositeKey[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5] = new org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5](a1, a2, a3, a4, a5);
    def compositeKey[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6): org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6] = new org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6](a1, a2, a3, a4, a5, a6);
    def compositeKey[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7): org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7] = new org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7](a1, a2, a3, a4, a5, a6, a7);
    def compositeKey[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8): org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8] = new org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8](a1, a2, a3, a4, a5, a6, a7, a8);
    def compositeKey[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8, a9: A9): org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9] = new org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9](a1, a2, a3, a4, a5, a6, a7, a8, a9);
    implicit def t2te[A1 >: Nothing <: Any, A2 >: Nothing <: Any](t: (A1, A2)): org.squeryl.dsl.CompositeKey2[A1,A2] = new org.squeryl.dsl.CompositeKey2[A1,A2](t._1, t._2);
    implicit def t3te[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any](t: (A1, A2, A3)): org.squeryl.dsl.CompositeKey3[A1,A2,A3] = new org.squeryl.dsl.CompositeKey3[A1,A2,A3](t._1, t._2, t._3);
    implicit def t4te[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any](t: (A1, A2, A3, A4)): org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4] = new org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4](t._1, t._2, t._3, t._4);
    implicit def t5te[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any](t: (A1, A2, A3, A4, A5)): org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5] = new org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5](t._1, t._2, t._3, t._4, t._5);
    implicit def t6te[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any](t: (A1, A2, A3, A4, A5, A6)): org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6] = new org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6](t._1, t._2, t._3, t._4, t._5, t._6);
    implicit def t7te[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any](t: (A1, A2, A3, A4, A5, A6, A7)): org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7] = new org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7](t._1, t._2, t._3, t._4, t._5, t._6, t._7);
    implicit def t8te[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any](t: (A1, A2, A3, A4, A5, A6, A7, A8)): org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8] = new org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8](t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
    implicit def t9te[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any](t: (A1, A2, A3, A4, A5, A6, A7, A8, A9)): org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9] = new org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9](t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9)
  }
}