package org.squeryl.dsl.boilerplate {
  import org.squeryl.dsl.QueryYield;
  import org.squeryl.dsl.internal.{JoinedQueryable, InnerJoinedQueryable, OuterJoinedQueryable};
  import org.squeryl.{Queryable, Query};
  abstract trait JoinSignatures extends scala.AnyRef { self: FromSignatures => 
    def $init$() = {
      ()
    };
    class JoinPrecursor[A >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
      <paramaccessor> private[this] val q: Queryable[A] = _;
      def <init>(q: Queryable[A]) = {
        super.<init>();
        ()
      };
      def leftOuter = new OuterJoinedQueryable[A](q, "left");
      def rightOuter = new OuterJoinedQueryable[A](q, "right");
      def fullOuter = new OuterJoinedQueryable[A](q, "full")
    };
    implicit def queryable2JoinPrecursor[A >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A]) = new JoinPrecursor[A](q);
    implicit def queryable2RightInnerJoinedQueryable[A >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A]) = new InnerJoinedQueryable[A](q, "");
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1])(f: Function2[A, B1, JoinQueryYield1[C]]): Query[C] = from(q, q1)(((a: A, b1: B1) => f(a, b1).queryYield));
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, B2 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1], q2: JoinedQueryable[B2])(f: Function3[A, B1, B2, JoinQueryYield2[C]]): Query[C] = from(q, q1, q2)(((a: A, b1: B1, b2: B2) => f(a, b1, b2).queryYield));
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, B2 >: _root_.scala.Nothing <: _root_.scala.Any, B3 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1], q2: JoinedQueryable[B2], q3: JoinedQueryable[B3])(f: Function4[A, B1, B2, B3, JoinQueryYield3[C]]): Query[C] = from(q, q1, q2, q3)(((a: A, b1: B1, b2: B2, b3: B3) => f(a, b1, b2, b3).queryYield));
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, B2 >: _root_.scala.Nothing <: _root_.scala.Any, B3 >: _root_.scala.Nothing <: _root_.scala.Any, B4 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1], q2: JoinedQueryable[B2], q3: JoinedQueryable[B3], q4: JoinedQueryable[B4])(f: Function5[A, B1, B2, B3, B4, JoinQueryYield4[C]]): Query[C] = from(q, q1, q2, q3, q4)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4) => f(a, b1, b2, b3, b4).queryYield));
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, B2 >: _root_.scala.Nothing <: _root_.scala.Any, B3 >: _root_.scala.Nothing <: _root_.scala.Any, B4 >: _root_.scala.Nothing <: _root_.scala.Any, B5 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1], q2: JoinedQueryable[B2], q3: JoinedQueryable[B3], q4: JoinedQueryable[B4], q5: JoinedQueryable[B5])(f: Function6[A, B1, B2, B3, B4, B5, JoinQueryYield5[C]]): Query[C] = from(q, q1, q2, q3, q4, q5)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5) => f(a, b1, b2, b3, b4, b5).queryYield));
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, B2 >: _root_.scala.Nothing <: _root_.scala.Any, B3 >: _root_.scala.Nothing <: _root_.scala.Any, B4 >: _root_.scala.Nothing <: _root_.scala.Any, B5 >: _root_.scala.Nothing <: _root_.scala.Any, B6 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1], q2: JoinedQueryable[B2], q3: JoinedQueryable[B3], q4: JoinedQueryable[B4], q5: JoinedQueryable[B5], q6: JoinedQueryable[B6])(f: Function7[A, B1, B2, B3, B4, B5, B6, JoinQueryYield6[C]]): Query[C] = from(q, q1, q2, q3, q4, q5, q6)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5, b6: B6) => f(a, b1, b2, b3, b4, b5, b6).queryYield));
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, B2 >: _root_.scala.Nothing <: _root_.scala.Any, B3 >: _root_.scala.Nothing <: _root_.scala.Any, B4 >: _root_.scala.Nothing <: _root_.scala.Any, B5 >: _root_.scala.Nothing <: _root_.scala.Any, B6 >: _root_.scala.Nothing <: _root_.scala.Any, B7 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1], q2: JoinedQueryable[B2], q3: JoinedQueryable[B3], q4: JoinedQueryable[B4], q5: JoinedQueryable[B5], q6: JoinedQueryable[B6], q7: JoinedQueryable[B7])(f: Function8[A, B1, B2, B3, B4, B5, B6, B7, JoinQueryYield7[C]]): Query[C] = from(q, q1, q2, q3, q4, q5, q6, q7)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5, b6: B6, b7: B7) => f(a, b1, b2, b3, b4, b5, b6, b7).queryYield));
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, B2 >: _root_.scala.Nothing <: _root_.scala.Any, B3 >: _root_.scala.Nothing <: _root_.scala.Any, B4 >: _root_.scala.Nothing <: _root_.scala.Any, B5 >: _root_.scala.Nothing <: _root_.scala.Any, B6 >: _root_.scala.Nothing <: _root_.scala.Any, B7 >: _root_.scala.Nothing <: _root_.scala.Any, B8 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1], q2: JoinedQueryable[B2], q3: JoinedQueryable[B3], q4: JoinedQueryable[B4], q5: JoinedQueryable[B5], q6: JoinedQueryable[B6], q7: JoinedQueryable[B7], q8: JoinedQueryable[B8])(f: Function9[A, B1, B2, B3, B4, B5, B6, B7, B8, JoinQueryYield8[C]]): Query[C] = from(q, q1, q2, q3, q4, q5, q6, q7, q8)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5, b6: B6, b7: B7, b8: B8) => f(a, b1, b2, b3, b4, b5, b6, b7, b8).queryYield));
    def join[A >: _root_.scala.Nothing <: _root_.scala.Any, B1 >: _root_.scala.Nothing <: _root_.scala.Any, B2 >: _root_.scala.Nothing <: _root_.scala.Any, B3 >: _root_.scala.Nothing <: _root_.scala.Any, B4 >: _root_.scala.Nothing <: _root_.scala.Any, B5 >: _root_.scala.Nothing <: _root_.scala.Any, B6 >: _root_.scala.Nothing <: _root_.scala.Any, B7 >: _root_.scala.Nothing <: _root_.scala.Any, B8 >: _root_.scala.Nothing <: _root_.scala.Any, B9 >: _root_.scala.Nothing <: _root_.scala.Any, C >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], q1: JoinedQueryable[B1], q2: JoinedQueryable[B2], q3: JoinedQueryable[B3], q4: JoinedQueryable[B4], q5: JoinedQueryable[B5], q6: JoinedQueryable[B6], q7: JoinedQueryable[B7], q8: JoinedQueryable[B8], q9: JoinedQueryable[B9])(f: Function10[A, B1, B2, B3, B4, B5, B6, B7, B8, B9, JoinQueryYield9[C]]): Query[C] = from(q, q1, q2, q3, q4, q5, q6, q7, q8, q9)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5, b6: B6, b7: B7, b8: B8, b9: B9) => f(a, b1, b2, b3, b4, b5, b6, b7, b8, b9).queryYield))
  };
  class JoinQueryYield1[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  };
  class JoinQueryYield2[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  };
  class JoinQueryYield3[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  };
  class JoinQueryYield4[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  };
  class JoinQueryYield5[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  };
  class JoinQueryYield6[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  };
  class JoinQueryYield7[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  };
  class JoinQueryYield8[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  };
  class JoinQueryYield9[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val queryYield: QueryYield[R] = _;
    def <init>(queryYield: QueryYield[R]) = {
      super.<init>();
      ()
    }
  }
}