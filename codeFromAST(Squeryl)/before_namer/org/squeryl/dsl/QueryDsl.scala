package org.squeryl.dsl {
  import ast._;
  import boilerplate._;
  import fsm._;
  import org.squeryl.internals._;
  import org.squeryl._;
  import java.sql.{SQLException, ResultSet};
  import collection.mutable.ArrayBuffer;
  import scala.util.control.ControlThrowable;
  abstract trait BaseQueryDsl extends scala.AnyRef {
    def $init$() = {
      ()
    };
    implicit def noneKeyedEntityDef[A >: _root_.scala.Nothing <: _root_.scala.Any, K >: _root_.scala.Nothing <: _root_.scala.Any]: OptionalKeyedEntityDef[A, K] = {
      final class $anon extends OptionalKeyedEntityDef[A, K] {
        def <init>() = {
          super.<init>();
          ()
        };
        override def keyedEntityDef: Option[KeyedEntityDef[A, K]] = None
      };
      new $anon()
    }
  };
  abstract trait QueryDsl extends WhereState[Unconditioned] with ComputeMeasuresSignaturesFromStartOrWhereState with StartState with QueryElements[Unconditioned] with JoinSignatures with FromSignatures with BaseQueryDsl { outerQueryDsl => 
    def $init$() = {
      ()
    };
    implicit def kedForKeyedEntities[A >: _root_.scala.Nothing <: _root_.scala.Any, K >: _root_.scala.Nothing <: _root_.scala.Any](implicit ev: $less$colon$less[A, KeyedEntity[K]], m: Manifest[A]): KeyedEntityDef[A, K] = {
      final class $anon extends KeyedEntityDef[A, K] {
        def <init>() = {
          super.<init>();
          ()
        };
        def getId(a: A) = a.id;
        def isPersisted(a: A) = a.isPersisted;
        def idPropertyName = "id";
        override def optimisticCounterPropertyName = if (classOf[Optimistic].isAssignableFrom(m.erasure))
          Some("occVersionNumber")
        else
          None
      };
      new $anon()
    };
    implicit def queryToIterable[R >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[R]): Iterable[R] = {
      val i = q.iterator;
      {
        final class $anon extends Iterable[R] {
          def <init>() = {
            super.<init>();
            ()
          };
          val hasFirst = i.hasNext;
          lazy val firstRow = if (hasFirst)
            Some(i.next)
          else
            None;
          override def head = firstRow.get;
          override def headOption = firstRow;
          override def isEmpty = hasFirst.unary_$bang;
          def iterator = new IteratorConcatenation(firstRow.iterator, i)
        };
        new $anon()
      }
    };
    def using[A >: _root_.scala.Nothing <: _root_.scala.Any](session: Session)(a: _root_.scala.<byname>[A]): A = session.using((a: (() => <empty>)));
    def transaction[A >: _root_.scala.Nothing <: _root_.scala.Any](sf: SessionFactory)(a: _root_.scala.<byname>[A]) = sf.newSession.withinTransaction((a: (() => <empty>)));
    def inTransaction[A >: _root_.scala.Nothing <: _root_.scala.Any](sf: SessionFactory)(a: _root_.scala.<byname>[A]) = if (Session.hasCurrentSession.unary_$bang)
      sf.newSession.withinTransaction((a: (() => <empty>)))
    else
      a;
    def transaction[A >: _root_.scala.Nothing <: _root_.scala.Any](s: Session)(a: _root_.scala.<byname>[A]) = s.withinTransaction((a: (() => <empty>)));
    def transaction[A >: _root_.scala.Nothing <: _root_.scala.Any](a: _root_.scala.<byname>[A]): A = if (Session.hasCurrentSession.unary_$bang)
      SessionFactory.newSession.withinTransaction((a: (() => <empty>)))
    else
      {
        val s = Session.currentSession;
        val res = try {
          s.unbindFromCurrentThread;
          SessionFactory.newSession.withinTransaction((a: (() => <empty>)))
        } finally s.bindToCurrentThread;
        res
      };
    def inTransaction[A >: _root_.scala.Nothing <: _root_.scala.Any](a: _root_.scala.<byname>[A]): A = if (Session.hasCurrentSession.unary_$bang)
      SessionFactory.newSession.withinTransaction((a: (() => <empty>)))
    else
      a;
    implicit def __thisDsl: QueryDsl = this;
    private class QueryElementsImpl[Cond >: _root_.scala.Nothing <: _root_.scala.Any] extends QueryElements[Cond] {
      override <paramaccessor> val whereClause: Option[_root_.scala.Function0[LogicalBoolean]] = _;
      def <init>(whereClause: Option[_root_.scala.Function0[LogicalBoolean]]) = {
        super.<init>();
        ()
      }
    };
    def where(b: _root_.scala.<byname>[LogicalBoolean]): WhereState[Conditioned] = new QueryElementsImpl[Conditioned](Some((b: (() => <empty>))));
    def $amp[A >: _root_.scala.Nothing <: _root_.scala.Any, T >: _root_.scala.Nothing <: _root_.scala.Any](i: _root_.scala.<byname>[TypedExpression[A, T]]): A = FieldReferenceLinker.pushExpressionOrCollectValue[A]((i: (() => <empty>)));
    implicit def typedExpression2OrderByArg[E >: _root_.scala.Nothing <: _root_.scala.Any](e: E)(implicit evidence$1: _root_.scala.Function1[E, TypedExpression[_$1, _$2] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = new OrderByArg(e);
    implicit def orderByArg2OrderByExpression(a: OrderByArg) = new OrderByExpression(a);
    def sDevPopulation[T2 >: TOptionFloat <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: T2, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A2, T2]) = f.convert(new FunctionNode("stddev_pop", Seq(b)));
    def sDevSample[T2 >: TOptionFloat <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: T2, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A2, T2]) = f.convert(new FunctionNode("stddev_samp", Seq(b)));
    def varPopulation[T2 >: TOptionFloat <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: T2, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A2, T2]) = f.convert(new FunctionNode("var_pop", Seq(b)));
    def varSample[T2 >: TOptionFloat <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: T2, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A2, T2]) = f.convert(new FunctionNode("var_samp", Seq(b)));
    def max[T2 >: TOption <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: T2, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A2, T2]) = f.convert(new FunctionNode("max", Seq(b)));
    def min[T2 >: TOption <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: T2, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A2, T2]) = f.convert(new FunctionNode("min", Seq(b)));
    def avg[T2 >: TOptionFloat <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: T2, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A2, T2]) = f.convert(new FunctionNode("avg", Seq(b)));
    def sum[T2 >: TOption <: _root_.scala.Any, T1 >: TNumericLowerTypeBound <: T2, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A2, T2]) = f.convert(new FunctionNode("sum", Seq(b)));
    def nvl[T4 >: _root_.scala.Nothing <: TNonOption, T1 >: TOption <: _root_.scala.Any, T3 >: T1 <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: T3, A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](a: TypedExpression[A1, T1], b: TypedExpression[A2, T2])(implicit d: DeOptionizer[_$3, A3, T4, _$4, T3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }): TypedExpression[A3, T4] = new NvlNode(a, d.deOptionizer.convert(b));
    def not(b: LogicalBoolean) = {
      final class $anon extends FunctionNode with LogicalBoolean {
        def <init>() = {
          super.<init>("not", Seq(b));
          ()
        }
      };
      new $anon()
    };
    def upper[A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any](s: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A1, T1], ev2: $less$colon$less[T1, TOptionString]) = f.convert(new FunctionNode("upper", Seq(s)));
    def lower[A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any](s: TypedExpression[A1, T1])(implicit f: TypedExpressionFactory[A1, T1], ev2: $less$colon$less[T1, TOptionString]) = f.convert(new FunctionNode("lower", Seq(s)));
    def exists[A1 >: _root_.scala.Nothing <: _root_.scala.Any](query: Query[A1]) = new ExistsExpression(query.copy(false).ast, "exists");
    def notExists[A1 >: _root_.scala.Nothing <: _root_.scala.Any](query: Query[A1]) = new ExistsExpression(query.copy(false).ast, "not exists");
    implicit val numericComparisonEvidence = new CanCompare[TNumeric, TNumeric]();
    implicit val dateComparisonEvidence = new CanCompare[TOptionDate, TOptionDate]();
    implicit val timestampComparisonEvidence = new CanCompare[TOptionTimestamp, TOptionTimestamp]();
    implicit val stringComparisonEvidence = new CanCompare[TOptionString, TOptionString]();
    implicit val booleanComparisonEvidence = new CanCompare[TOptionBoolean, TOptionBoolean]();
    implicit val uuidComparisonEvidence = new CanCompare[TOptionUUID, TOptionUUID]();
    implicit def enumComparisonEvidence[A >: _root_.scala.Nothing <: _root_.scala.Any] = new CanCompare[TEnumValue[A], TEnumValue[A]]();
    implicit def concatenationConversion[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](co: ConcatOp[A1, A2, T1, T2]): TypedExpression[String, TString] = new ConcatOperationNode[String, TString](co.a1, co.a2, InternalFieldMapper.stringTEF.createOutMapper);
    implicit def concatenationConversionWithOption1[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](co: ConcatOp[Option[A1], A2, T1, T2]): TypedExpression[Option[String], TOptionString] = new ConcatOperationNode[Option[String], TOptionString](co.a1, co.a2, InternalFieldMapper.optionStringTEF.createOutMapper);
    implicit def concatenationConversionWithOption2[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](co: ConcatOp[A1, Option[A2], T1, T2]): TypedExpression[Option[String], TOptionString] = new ConcatOperationNode[Option[String], TOptionString](co.a1, co.a2, InternalFieldMapper.optionStringTEF.createOutMapper);
    implicit def concatenationConversionWithOption3[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](co: ConcatOp[Option[A1], Option[A2], T1, T2]): TypedExpression[Option[String], TOptionString] = new ConcatOperationNode[Option[String], TOptionString](co.a1, co.a2, InternalFieldMapper.optionStringTEF.createOutMapper);
    class ConcatOperationNode[A >: _root_.scala.Nothing <: _root_.scala.Any, T >: _root_.scala.Nothing <: _root_.scala.Any] extends BinaryOperatorNode with TypedExpression[A, T] {
      <paramaccessor> private[this] val e1: ExpressionNode = _;
      <paramaccessor> private[this] val e2: ExpressionNode = _;
      <paramaccessor> val mapper: OutMapper[A] = _;
      def <init>(e1: ExpressionNode, e2: ExpressionNode, mapper: OutMapper[A]) = {
        super.<init>(e1, e2, "||", false);
        ()
      };
      override def doWrite(sw: StatementWriter) = sw.databaseAdapter.writeConcatOperator(e1, e2, sw)
    };
    abstract trait SingleRowQuery[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef { self: Query[R] => 
      <empty>
    };
    abstract trait SingleColumnQuery[T >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef { self: Query[T] => 
      <empty>
    };
    abstract trait ScalarQuery[T >: _root_.scala.Nothing <: _root_.scala.Any] extends Query[T] with SingleColumnQuery[T] with SingleRowQuery[T];
    implicit def scalarQuery2Scalar[T >: _root_.scala.Nothing <: _root_.scala.Any](sq: ScalarQuery[T]) = sq.head;
    implicit def countQueryableToIntTypeQuery[R >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[R]) = new CountSubQueryableQuery(q);
    def count: CountFunction = count();
    def count(e: _root_.scala.<repeated>[TypedExpression[_$5, _$6] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = new CountFunction(e, false);
    def countDistinct(e: _root_.scala.<repeated>[TypedExpression[_$7, _$8] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = new CountFunction(e, true);
    class CountFunction extends FunctionNode with TypedExpression[Long, TLong] {
      <paramaccessor> private[this] val _args: Seq[ExpressionNode] = _;
      <paramaccessor> private[this] val isDistinct: Boolean = _;
      def <init>(_args: Seq[ExpressionNode], isDistinct: Boolean) = {
        super.<init>("count", _args match {
          case Nil => Seq(new TokenExpressionNode("*"))
          case _ => _args
        });
        ()
      };
      def mapper = InternalFieldMapper.longTEF.createOutMapper;
      override def doWrite(sw: StatementWriter) = {
        sw.write(name);
        sw.write("(");
        if (isDistinct)
          sw.write("distinct ")
        else
          ();
        sw.writeNodesWithSeparator(args, ",", false);
        sw.write(")")
      }
    };
    private def _countFunc = count;
    class CountSubQueryableQuery extends Query[Long] with ScalarQuery[Long] {
      <paramaccessor> private[this] val q: Queryable[_$9] forSome { 
        <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      def <init>(q: Queryable[_$9] forSome { 
        <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = {
        super.<init>();
        ()
      };
      private val _inner: Query[Measures[Long]] = from(q)(((r) => compute(_countFunc)));
      def iterator = _inner.map(((m) => m.measures)).iterator;
      def Count: ScalarQuery[Long] = this;
      def statement: String = _inner.statement;
      def page(offset: Int, length: Int) = this;
      def distinct = this;
      def forUpdate = _inner.forUpdate;
      def dumpAst = _inner.dumpAst;
      def ast = _inner.ast;
      protected[squeryl] def invokeYield(rsm: ResultSetMapper, rs: ResultSet) = _inner.invokeYield(rsm, rs).measures;
      override private[squeryl] def copy(asRoot: Boolean) = new CountSubQueryableQuery(q);
      def name = _inner.name;
      private[squeryl] def give(rsm: ResultSetMapper, rs: ResultSet) = q.invokeYield(rsm, rs)
    };
    implicit def singleColComputeQuery2ScalarQuery[T >: _root_.scala.Nothing <: _root_.scala.Any](cq: Query[Measures[T]]): ScalarQuery[T] = new ScalarMeasureQuery[T](cq);
    implicit def singleColComputeQuery2Scalar[T >: _root_.scala.Nothing <: _root_.scala.Any](cq: Query[Measures[T]]) = new ScalarMeasureQuery[T](cq).head;
    class ScalarMeasureQuery[T >: _root_.scala.Nothing <: _root_.scala.Any] extends Query[T] with ScalarQuery[T] {
      <paramaccessor> private[this] val q: Query[Measures[T]] = _;
      def <init>(q: Query[Measures[T]]) = {
        super.<init>();
        ()
      };
      def iterator = q.map(((m) => m.measures)).iterator;
      def distinct = this;
      def forUpdate = q.forUpdate;
      def dumpAst = q.dumpAst;
      def page(offset: Int, length: Int) = this;
      def statement: String = q.statement;
      def ast = q.ast;
      protected[squeryl] def invokeYield(rsm: ResultSetMapper, rs: ResultSet) = q.invokeYield(rsm, rs).measures;
      override private[squeryl] def copy(asRoot: Boolean) = new ScalarMeasureQuery(q);
      def name = q.name;
      private[squeryl] def give(rsm: ResultSetMapper, rs: ResultSet) = q.invokeYield(rsm, rs).measures
    };
    implicit def queryable2OptionalQueryable[A >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A]) = new OptionalQueryable[A](q);
    def update[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[A])(s: _root_.scala.Function1[A, UpdateStatement]): Int = t.update(s);
    def manyToManyRelation[L >: _root_.scala.Nothing <: _root_.scala.Any, R >: _root_.scala.Nothing <: _root_.scala.Any](l: Table[L], r: Table[R])(implicit kedL: KeyedEntityDef[L, _$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }, kedR: KeyedEntityDef[R, _$11] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = new ManyToManyRelationBuilder(l, r, None, kedL, kedR);
    def manyToManyRelation[L >: _root_.scala.Nothing <: _root_.scala.Any, R >: _root_.scala.Nothing <: _root_.scala.Any](l: Table[L], r: Table[R], nameOfMiddleTable: String)(implicit kedL: KeyedEntityDef[L, _$12] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }, kedR: KeyedEntityDef[R, _$13] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = new ManyToManyRelationBuilder(l, r, Some(nameOfMiddleTable), kedL, kedR);
    class ManyToManyRelationBuilder[L >: _root_.scala.Nothing <: _root_.scala.Any, R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
      <paramaccessor> private[this] val l: Table[L] = _;
      <paramaccessor> private[this] val r: Table[R] = _;
      <paramaccessor> private[this] val nameOverride: Option[String] = _;
      <paramaccessor> private[this] val kedL: KeyedEntityDef[L, _$14] forSome { 
        <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      <paramaccessor> private[this] val kedR: KeyedEntityDef[R, _$15] forSome { 
        <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      def <init>(l: Table[L], r: Table[R], nameOverride: Option[String], kedL: KeyedEntityDef[L, _$14] forSome { 
        <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
      }, kedR: KeyedEntityDef[R, _$15] forSome { 
        <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = {
        super.<init>();
        ()
      };
      def via[A >: _root_.scala.Nothing <: _root_.scala.Any](f: _root_.scala.Function3[L, R, A, Pair[EqualityExpression, EqualityExpression]])(implicit manifestA: Manifest[A], schema: Schema, kedA: KeyedEntityDef[A, _$16] forSome { 
        <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = {
        val m2m = new ManyToManyRelationImpl(l, r, manifestA.erasure.asInstanceOf[Class[A]], f, schema, nameOverride, kedL, kedR, kedA);
        schema._addTable(m2m);
        m2m
      }
    };
    private def invalidBindingExpression = Utils.throwError("Binding expression of relation uses a def, not a field (val or var)");
    class ManyToManyRelationImpl[L >: _root_.scala.Nothing <: _root_.scala.Any, R >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any] extends Table[A] with ManyToManyRelation[L, R, A] { thisTableOfA => 
      <paramaccessor> val leftTable: Table[L] = _;
      <paramaccessor> val rightTable: Table[R] = _;
      <paramaccessor> private[this] val aClass: Class[A] = _;
      <paramaccessor> private[this] val f: _root_.scala.Function3[L, R, A, Pair[EqualityExpression, EqualityExpression]] = _;
      <paramaccessor> private[this] val schema: Schema = _;
      <paramaccessor> private[this] val nameOverride: Option[String] = _;
      <paramaccessor> private[this] val kedL: KeyedEntityDef[L, _$17] forSome { 
        <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      <paramaccessor> private[this] val kedR: KeyedEntityDef[R, _$18] forSome { 
        <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      <paramaccessor> private[this] val kedA: KeyedEntityDef[A, _$19] forSome { 
        <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      def <init>(leftTable: Table[L], rightTable: Table[R], aClass: Class[A], f: _root_.scala.Function3[L, R, A, Pair[EqualityExpression, EqualityExpression]], schema: Schema, nameOverride: Option[String], kedL: KeyedEntityDef[L, _$17] forSome { 
        <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
      }, kedR: KeyedEntityDef[R, _$18] forSome { 
        <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any
      }, kedA: KeyedEntityDef[A, _$19] forSome { 
        <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = {
        super.<init>(nameOverride.getOrElse(schema.tableNameFromClass(aClass)), aClass, schema, None, Some(kedA));
        ()
      };
      def thisTable = thisTableOfA;
      schema._addRelation(this);
      <synthetic> private[this] val x$1 = {
        var e2: Option[Pair[EqualityExpression, EqualityExpression]] = None;
        from(leftTable, rightTable, thisTableOfA)(((l, r, a) => {
          e2 = Some(f(l, r, a));
          select(None)
        }));
        val e2_ = e2.get;
        if (e2_._1.filterDescendantsOfType[ConstantTypedExpression[_$20, _$21] forSome { 
  <synthetic> type _$20 >: _root_.scala.Nothing <: _root_.scala.Any;
  <synthetic> type _$21 >: _root_.scala.Nothing <: _root_.scala.Any
}].isEmpty.unary_$bang)
          invalidBindingExpression
        else
          ();
        if (e2_._2.filterDescendantsOfType[ConstantTypedExpression[_$22, _$23] forSome { 
  <synthetic> type _$22 >: _root_.scala.Nothing <: _root_.scala.Any;
  <synthetic> type _$23 >: _root_.scala.Nothing <: _root_.scala.Any
}].isEmpty.unary_$bang)
          invalidBindingExpression
        else
          ();
        if (_viewReferedInExpression(leftTable, e2_._1))
          {
            assert(_viewReferedInExpression(rightTable, e2_._2));
            e2_
          }
        else
          {
            assert(_viewReferedInExpression(leftTable, e2_._2));
            assert(_viewReferedInExpression(rightTable, e2_._1));
            scala.Tuple2(e2_._2, e2_._1)
          }
      }: @scala.unchecked match {
        case scala.Tuple2((_leftEqualityExpr @ _), (_rightEqualityExpr @ _)) => scala.Tuple2(_leftEqualityExpr, _rightEqualityExpr)
      };
      private val _leftEqualityExpr = x$1._1;
      private val _rightEqualityExpr = x$1._2;
      private def _viewReferedInExpression(v: View[_$24] forSome { 
        <synthetic> type _$24 >: _root_.scala.Nothing <: _root_.scala.Any
      }, ee: EqualityExpression) = ee.filterDescendantsOfType[SelectElementReference[Any, Any]].filter(((x$2) => x$2.selectElement.origin.asInstanceOf[ViewExpressionNode[_$25] forSome { 
  <synthetic> type _$25 >: _root_.scala.Nothing <: _root_.scala.Any
}].view.$eq$eq(v))).headOption.$bang$eq(None);
      <synthetic> private[this] val x$3 = _splitEquality(_leftEqualityExpr, thisTable, false): @scala.unchecked match {
        case scala.Tuple2((leftPkFmd @ _), (leftFkFmd @ _)) => scala.Tuple2(leftPkFmd, leftFkFmd)
      };
      private val leftPkFmd = x$3._1;
      private val leftFkFmd = x$3._2;
      <synthetic> private[this] val x$4 = _splitEquality(_rightEqualityExpr, thisTable, false): @scala.unchecked match {
        case scala.Tuple2((rightPkFmd @ _), (rightFkFmd @ _)) => scala.Tuple2(rightPkFmd, rightFkFmd)
      };
      private val rightPkFmd = x$4._1;
      private val rightFkFmd = x$4._2;
      val leftForeignKeyDeclaration = schema._createForeignKeyDeclaration(leftFkFmd.columnName, leftPkFmd.columnName);
      val rightForeignKeyDeclaration = schema._createForeignKeyDeclaration(rightFkFmd.columnName, rightPkFmd.columnName);
      private def _associate[T >: _root_.scala.Nothing <: _root_.scala.Any](o: T, m2m: ManyToMany[T, A]): A = {
        val aInst = m2m.assign(o);
        try {
          thisTableOfA.insertOrUpdate(aInst)(kedA)
        } catch {
          case (e @ (_: SQLException)) => if (Session.currentSession.databaseAdapter.isNotNullConstraintViolation(e))
            throw new RuntimeException("the ".$plus(scala.Symbol("associate")).$plus(" method created and inserted association object of type ").$plus(posoMetaData.clasz.getName).$plus(" that has NOT NULL colums, plase use the other signature of ").$plus(scala.Symbol("ManyToMany")).$plus(" that takes the association object as argument : associate(o,a) for association objects that have NOT NULL columns"), e)
          else
            throw e
        }
      };
      def left(leftSideMember: L): Query[R] with ManyToMany[R, A] = {
        val q = from(thisTableOfA, rightTable)(((a, r) => {
          val matchClause = f(leftSideMember, r, a);
          outerQueryDsl.where(matchClause._1.and(matchClause._2)).select(r)
        }));
        {
          final class $anon extends DelegateQuery with ManyToMany[R, A] {
            def <init>() = {
              super.<init>(q);
              ()
            };
            def kedL = thisTableOfA.kedR;
            private def _assignKeys(r: R, a: AnyRef): Unit = {
              val leftPk = leftPkFmd.get(leftSideMember.asInstanceOf[AnyRef]);
              val rightPk = rightPkFmd.get(r.asInstanceOf[AnyRef]);
              leftFkFmd.set(a, leftPk);
              rightFkFmd.set(a, rightPk)
            };
            def associationMap = from(thisTableOfA, rightTable)(((a, r) => {
              val matchClause = f(leftSideMember, r, a);
              outerQueryDsl.where(matchClause._1.and(matchClause._2)).select(scala.Tuple2(r, a))
            }));
            def assign(o: R, a: A) = {
              _assignKeys(o, a.asInstanceOf[AnyRef]);
              a
            };
            def associate(o: R, a: A): A = {
              assign(o, a);
              thisTableOfA.insertOrUpdate(a)(kedA);
              a
            };
            def assign(o: R): A = {
              val aInstAny = thisTableOfA._createInstanceOfRowObject;
              val aInst = aInstAny.asInstanceOf[A];
              _assignKeys(o, aInstAny);
              aInst
            };
            def associate(o: R): A = _associate(o, this);
            def dissociate(o: R) = thisTableOfA.deleteWhere(((a0) => _whereClauseForAssociations(a0).and(_equalityForRightSide(a0, o)))).$greater(0);
            def _whereClauseForAssociations(a0: A) = {
              val leftPk = leftPkFmd.get(leftSideMember.asInstanceOf[AnyRef]);
              leftFkFmd.get(a0.asInstanceOf[AnyRef]);
              FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(leftPk)
            };
            def _equalityForRightSide(a0: A, r: R) = {
              val rightPk = rightPkFmd.get(r.asInstanceOf[AnyRef]);
              rightFkFmd.get(a0.asInstanceOf[AnyRef]);
              FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(rightPk)
            };
            def dissociateAll = thisTableOfA.deleteWhere(((a0) => _whereClauseForAssociations(a0)));
            def associations = thisTableOfA.where(((a0) => _whereClauseForAssociations(a0)))
          };
          new $anon()
        }
      };
      def right(rightSideMember: R): Query[L] with ManyToMany[L, A] = {
        val q = from(thisTableOfA, leftTable)(((a, l) => {
          val matchClause = f(l, rightSideMember, a);
          outerQueryDsl.where(matchClause._1.and(matchClause._2)).select(l)
        }));
        {
          final class $anon extends DelegateQuery with ManyToMany[L, A] {
            def <init>() = {
              super.<init>(q);
              ()
            };
            def kedL = thisTableOfA.kedL;
            private def _assignKeys(l: L, a: AnyRef): Unit = {
              val rightPk = rightPkFmd.get(rightSideMember.asInstanceOf[AnyRef]);
              val leftPk = leftPkFmd.get(l.asInstanceOf[AnyRef]);
              rightFkFmd.set(a, rightPk);
              leftFkFmd.set(a, leftPk)
            };
            def associationMap = from(thisTableOfA, leftTable)(((a, l) => {
              val matchClause = f(l, rightSideMember, a);
              outerQueryDsl.where(matchClause._1.and(matchClause._2)).select(scala.Tuple2(l, a))
            }));
            def assign(o: L, a: A) = {
              _assignKeys(o, a.asInstanceOf[AnyRef]);
              a
            };
            def associate(o: L, a: A): A = {
              assign(o, a);
              thisTableOfA.insertOrUpdate(a)(kedA);
              a
            };
            def assign(o: L): A = {
              val aInstAny = thisTableOfA._createInstanceOfRowObject;
              val aInst = aInstAny.asInstanceOf[A];
              _assignKeys(o, aInstAny);
              aInst
            };
            def associate(o: L): A = _associate(o, this);
            def dissociate(o: L) = thisTableOfA.deleteWhere(((a0) => _whereClauseForAssociations(a0).and(_leftEquality(o, a0)))).$greater(0);
            def _leftEquality(l: L, a0: A) = {
              val leftPk = leftPkFmd.get(l.asInstanceOf[AnyRef]);
              leftFkFmd.get(a0.asInstanceOf[AnyRef]);
              FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(leftPk)
            };
            def _whereClauseForAssociations(a0: A) = {
              val rightPk = rightPkFmd.get(rightSideMember.asInstanceOf[AnyRef]);
              rightFkFmd.get(a0.asInstanceOf[AnyRef]);
              FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(rightPk)
            };
            def dissociateAll = thisTableOfA.deleteWhere(((a0) => _whereClauseForAssociations(a0)));
            def associations = thisTableOfA.where(((a0) => _whereClauseForAssociations(a0)))
          };
          new $anon()
        }
      }
    };
    def oneToManyRelation[O >: _root_.scala.Nothing <: _root_.scala.Any, M >: _root_.scala.Nothing <: _root_.scala.Any](ot: Table[O], mt: Table[M])(implicit kedO: KeyedEntityDef[O, _$26] forSome { 
      <synthetic> type _$26 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = new OneToManyRelationBuilder(ot, mt);
    class OneToManyRelationBuilder[O >: _root_.scala.Nothing <: _root_.scala.Any, M >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
      <paramaccessor> private[this] val ot: Table[O] = _;
      <paramaccessor> private[this] val mt: Table[M] = _;
      def <init>(ot: Table[O], mt: Table[M]) = {
        super.<init>();
        ()
      };
      def via(f: _root_.scala.Function2[O, M, EqualityExpression])(implicit schema: Schema, kedM: KeyedEntityDef[M, _$27] forSome { 
        <synthetic> type _$27 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = new OneToManyRelationImpl(ot, mt, f, schema, kedM)
    };
    class OneToManyRelationImpl[O >: _root_.scala.Nothing <: _root_.scala.Any, M >: _root_.scala.Nothing <: _root_.scala.Any] extends OneToManyRelation[O, M] {
      <paramaccessor> val leftTable: Table[O] = _;
      <paramaccessor> val rightTable: Table[M] = _;
      <paramaccessor> private[this] val f: _root_.scala.Function2[O, M, EqualityExpression] = _;
      <paramaccessor> private[this] val schema: Schema = _;
      <paramaccessor> private[this] val kedM: KeyedEntityDef[M, _$28] forSome { 
        <synthetic> type _$28 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      def <init>(leftTable: Table[O], rightTable: Table[M], f: _root_.scala.Function2[O, M, EqualityExpression], schema: Schema, kedM: KeyedEntityDef[M, _$28] forSome { 
        <synthetic> type _$28 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = {
        super.<init>();
        ()
      };
      schema._addRelation(this);
      private def _isSelfReference = leftTable.$eq$eq(rightTable);
      <synthetic> private[this] val x$5 = {
        var ee: Option[EqualityExpression] = None;
        from(leftTable, rightTable)(((o, m) => {
          ee = Some(f(o, m));
          select(None)
        }));
        val ee_ = ee.get;
        if (ee_.filterDescendantsOfType[ConstantTypedExpression[_$29, _$30] forSome { 
  <synthetic> type _$29 >: _root_.scala.Nothing <: _root_.scala.Any;
  <synthetic> type _$30 >: _root_.scala.Nothing <: _root_.scala.Any
}].isEmpty.unary_$bang)
          invalidBindingExpression
        else
          ();
        _splitEquality(ee.get, rightTable, _isSelfReference)
      }: @scala.unchecked match {
        case scala.Tuple2((_leftPkFmd @ _), (_rightFkFmd @ _)) => scala.Tuple2(_leftPkFmd, _rightFkFmd)
      };
      private val _leftPkFmd = x$5._1;
      private val _rightFkFmd = x$5._2;
      val foreignKeyDeclaration = schema._createForeignKeyDeclaration(_rightFkFmd.columnName, _leftPkFmd.columnName);
      def left(leftSide: O): OneToMany[M] = {
        val q = from(rightTable)(((m) => where(f(leftSide, m)).select(m)));
        {
          final class $anon extends DelegateQuery with OneToMany[M] {
            def <init>() = {
              super.<init>(q);
              ()
            };
            def deleteAll = rightTable.deleteWhere(((m) => f(leftSide, m)));
            def assign(m: M) = {
              val m0 = m.asInstanceOf[AnyRef];
              val l0 = leftSide.asInstanceOf[AnyRef];
              val v = _leftPkFmd.get(l0);
              _rightFkFmd.set(m0, v);
              m
            };
            def associate(m: M) = {
              assign(m);
              rightTable.insertOrUpdate(m)(kedM)
            }
          };
          new $anon()
        }
      };
      def right(rightSide: M): ManyToOne[O] = {
        val q = from(leftTable)(((o) => where(f(o, rightSide)).select(o)));
        {
          final class $anon extends DelegateQuery with ManyToOne[O] {
            def <init>() = {
              super.<init>(q);
              ()
            };
            def assign(one: O) = {
              val o = one.asInstanceOf[AnyRef];
              val r = rightSide.asInstanceOf[AnyRef];
              val v = _rightFkFmd.get(r);
              _leftPkFmd.set(o, v);
              one
            };
            def delete = leftTable.deleteWhere(((o) => f(o, rightSide))).$greater(0)
          };
          new $anon()
        }
      }
    };
    private def _splitEquality(ee: EqualityExpression, rightTable: Table[_$31] forSome { 
      <synthetic> type _$31 >: _root_.scala.Nothing <: _root_.scala.Any
    }, isSelfReference: Boolean): scala.Tuple2[FieldMetaData, FieldMetaData] = {
      if (isSelfReference)
        assert(ee.right._fieldMetaData.isIdFieldOfKeyedEntity.$bar$bar(ee.left._fieldMetaData.isIdFieldOfKeyedEntity))
      else
        ();
      def msg = "equality expression incorrect in relation involving table ".$plus(rightTable.prefixedName).$plus(", or perhaps inverted oneToManyRelation");
      if (ee.left._fieldMetaData.parentMetaData.clasz.$eq$eq(rightTable.classOfT).$amp$amp(isSelfReference.unary_$bang.$bar$bar(isSelfReference.$amp$amp(ee.right._fieldMetaData.isIdFieldOfKeyedEntity))))
        {
          assert(ee.right._fieldMetaData.isIdFieldOfKeyedEntity, msg);
          scala.Tuple2(ee.right._fieldMetaData, ee.left._fieldMetaData)
        }
      else
        {
          assert(ee.left._fieldMetaData.isIdFieldOfKeyedEntity, msg);
          scala.Tuple2(ee.left._fieldMetaData, ee.right._fieldMetaData)
        }
    };
    def compositeKey[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](a1: A1, a2: A2) = new CompositeKey2(a1, a2);
    def compositeKey[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](a1: A1, a2: A2, a3: A3) = new CompositeKey3(a1, a2, a3);
    def compositeKey[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any](a1: A1, a2: A2, a3: A3, a4: A4) = new CompositeKey4(a1, a2, a3, a4);
    def compositeKey[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5) = new CompositeKey5(a1, a2, a3, a4, a5);
    def compositeKey[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6) = new CompositeKey6(a1, a2, a3, a4, a5, a6);
    def compositeKey[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7) = new CompositeKey7(a1, a2, a3, a4, a5, a6, a7);
    def compositeKey[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any, A8 >: _root_.scala.Nothing <: _root_.scala.Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8) = new CompositeKey8(a1, a2, a3, a4, a5, a6, a7, a8);
    def compositeKey[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any, A8 >: _root_.scala.Nothing <: _root_.scala.Any, A9 >: _root_.scala.Nothing <: _root_.scala.Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8, a9: A9) = new CompositeKey9(a1, a2, a3, a4, a5, a6, a7, a8, a9);
    implicit def t2te[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any](t: scala.Tuple2[A1, A2]) = new CompositeKey2[A1, A2](t._1, t._2);
    implicit def t3te[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](t: scala.Tuple3[A1, A2, A3]) = new CompositeKey3[A1, A2, A3](t._1, t._2, t._3);
    implicit def t4te[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any](t: scala.Tuple4[A1, A2, A3, A4]) = new CompositeKey4[A1, A2, A3, A4](t._1, t._2, t._3, t._4);
    implicit def t5te[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any](t: scala.Tuple5[A1, A2, A3, A4, A5]) = new CompositeKey5[A1, A2, A3, A4, A5](t._1, t._2, t._3, t._4, t._5);
    implicit def t6te[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any](t: scala.Tuple6[A1, A2, A3, A4, A5, A6]) = new CompositeKey6[A1, A2, A3, A4, A5, A6](t._1, t._2, t._3, t._4, t._5, t._6);
    implicit def t7te[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any](t: scala.Tuple7[A1, A2, A3, A4, A5, A6, A7]) = new CompositeKey7[A1, A2, A3, A4, A5, A6, A7](t._1, t._2, t._3, t._4, t._5, t._6, t._7);
    implicit def t8te[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any, A8 >: _root_.scala.Nothing <: _root_.scala.Any](t: scala.Tuple8[A1, A2, A3, A4, A5, A6, A7, A8]) = new CompositeKey8[A1, A2, A3, A4, A5, A6, A7, A8](t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8);
    implicit def t9te[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any, A8 >: _root_.scala.Nothing <: _root_.scala.Any, A9 >: _root_.scala.Nothing <: _root_.scala.Any](t: scala.Tuple9[A1, A2, A3, A4, A5, A6, A7, A8, A9]) = new CompositeKey9[A1, A2, A3, A4, A5, A6, A7, A8, A9](t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9)
  }
}