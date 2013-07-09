package org.squeryl.dsl.boilerplate {
  import org.squeryl.dsl.QueryYield;
  import org.squeryl.dsl.internal.{JoinedQueryable, InnerJoinedQueryable, OuterJoinedQueryable};
  import org.squeryl.{Queryable, Query};
  abstract trait JoinSignatures extends scala.AnyRef { self: org.squeryl.dsl.boilerplate.JoinSignatures with org.squeryl.dsl.boilerplate.FromSignatures => 
    def /*JoinSignatures*/$init$(): Unit = {
      ()
    };
    class JoinPrecursor[A >: Nothing <: Any] extends scala.AnyRef {
      <paramaccessor> private[this] val q: org.squeryl.Queryable[A] = _;
      def <init>(q: org.squeryl.Queryable[A]): JoinSignatures.this.JoinPrecursor[A] = {
        JoinPrecursor.super.<init>();
        ()
      };
      def leftOuter: org.squeryl.dsl.internal.OuterJoinedQueryable[A] = new org.squeryl.dsl.internal.OuterJoinedQueryable[A](JoinPrecursor.this.q, "left");
      def rightOuter: org.squeryl.dsl.internal.OuterJoinedQueryable[A] = new org.squeryl.dsl.internal.OuterJoinedQueryable[A](JoinPrecursor.this.q, "right");
      def fullOuter: org.squeryl.dsl.internal.OuterJoinedQueryable[A] = new org.squeryl.dsl.internal.OuterJoinedQueryable[A](JoinPrecursor.this.q, "full")
    };
    implicit def queryable2JoinPrecursor[A >: Nothing <: Any](q: org.squeryl.Queryable[A]): JoinSignatures.this.JoinPrecursor[A] = new JoinSignatures.this.JoinPrecursor[A](q);
    implicit def queryable2RightInnerJoinedQueryable[A >: Nothing <: Any](q: org.squeryl.Queryable[A]): org.squeryl.dsl.internal.InnerJoinedQueryable[A] = new org.squeryl.dsl.internal.InnerJoinedQueryable[A](q, "");
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1])(f: (A, B1) => org.squeryl.dsl.boilerplate.JoinQueryYield1[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, C](q, q1)(((a: A, b1: B1) => f.apply(a, b1).queryYield));
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, B2 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1], q2: org.squeryl.dsl.internal.JoinedQueryable[B2])(f: (A, B1, B2) => org.squeryl.dsl.boilerplate.JoinQueryYield2[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, B2, C](q, q1, q2)(((a: A, b1: B1, b2: B2) => f.apply(a, b1, b2).queryYield));
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, B2 >: Nothing <: Any, B3 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1], q2: org.squeryl.dsl.internal.JoinedQueryable[B2], q3: org.squeryl.dsl.internal.JoinedQueryable[B3])(f: (A, B1, B2, B3) => org.squeryl.dsl.boilerplate.JoinQueryYield3[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, B2, B3, C](q, q1, q2, q3)(((a: A, b1: B1, b2: B2, b3: B3) => f.apply(a, b1, b2, b3).queryYield));
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, B2 >: Nothing <: Any, B3 >: Nothing <: Any, B4 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1], q2: org.squeryl.dsl.internal.JoinedQueryable[B2], q3: org.squeryl.dsl.internal.JoinedQueryable[B3], q4: org.squeryl.dsl.internal.JoinedQueryable[B4])(f: (A, B1, B2, B3, B4) => org.squeryl.dsl.boilerplate.JoinQueryYield4[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, B2, B3, B4, C](q, q1, q2, q3, q4)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4) => f.apply(a, b1, b2, b3, b4).queryYield));
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, B2 >: Nothing <: Any, B3 >: Nothing <: Any, B4 >: Nothing <: Any, B5 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1], q2: org.squeryl.dsl.internal.JoinedQueryable[B2], q3: org.squeryl.dsl.internal.JoinedQueryable[B3], q4: org.squeryl.dsl.internal.JoinedQueryable[B4], q5: org.squeryl.dsl.internal.JoinedQueryable[B5])(f: (A, B1, B2, B3, B4, B5) => org.squeryl.dsl.boilerplate.JoinQueryYield5[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, B2, B3, B4, B5, C](q, q1, q2, q3, q4, q5)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5) => f.apply(a, b1, b2, b3, b4, b5).queryYield));
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, B2 >: Nothing <: Any, B3 >: Nothing <: Any, B4 >: Nothing <: Any, B5 >: Nothing <: Any, B6 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1], q2: org.squeryl.dsl.internal.JoinedQueryable[B2], q3: org.squeryl.dsl.internal.JoinedQueryable[B3], q4: org.squeryl.dsl.internal.JoinedQueryable[B4], q5: org.squeryl.dsl.internal.JoinedQueryable[B5], q6: org.squeryl.dsl.internal.JoinedQueryable[B6])(f: (A, B1, B2, B3, B4, B5, B6) => org.squeryl.dsl.boilerplate.JoinQueryYield6[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, B2, B3, B4, B5, B6, C](q, q1, q2, q3, q4, q5, q6)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5, b6: B6) => f.apply(a, b1, b2, b3, b4, b5, b6).queryYield));
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, B2 >: Nothing <: Any, B3 >: Nothing <: Any, B4 >: Nothing <: Any, B5 >: Nothing <: Any, B6 >: Nothing <: Any, B7 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1], q2: org.squeryl.dsl.internal.JoinedQueryable[B2], q3: org.squeryl.dsl.internal.JoinedQueryable[B3], q4: org.squeryl.dsl.internal.JoinedQueryable[B4], q5: org.squeryl.dsl.internal.JoinedQueryable[B5], q6: org.squeryl.dsl.internal.JoinedQueryable[B6], q7: org.squeryl.dsl.internal.JoinedQueryable[B7])(f: (A, B1, B2, B3, B4, B5, B6, B7) => org.squeryl.dsl.boilerplate.JoinQueryYield7[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, B2, B3, B4, B5, B6, B7, C](q, q1, q2, q3, q4, q5, q6, q7)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5, b6: B6, b7: B7) => f.apply(a, b1, b2, b3, b4, b5, b6, b7).queryYield));
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, B2 >: Nothing <: Any, B3 >: Nothing <: Any, B4 >: Nothing <: Any, B5 >: Nothing <: Any, B6 >: Nothing <: Any, B7 >: Nothing <: Any, B8 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1], q2: org.squeryl.dsl.internal.JoinedQueryable[B2], q3: org.squeryl.dsl.internal.JoinedQueryable[B3], q4: org.squeryl.dsl.internal.JoinedQueryable[B4], q5: org.squeryl.dsl.internal.JoinedQueryable[B5], q6: org.squeryl.dsl.internal.JoinedQueryable[B6], q7: org.squeryl.dsl.internal.JoinedQueryable[B7], q8: org.squeryl.dsl.internal.JoinedQueryable[B8])(f: (A, B1, B2, B3, B4, B5, B6, B7, B8) => org.squeryl.dsl.boilerplate.JoinQueryYield8[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, B2, B3, B4, B5, B6, B7, B8, C](q, q1, q2, q3, q4, q5, q6, q7, q8)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5, b6: B6, b7: B7, b8: B8) => f.apply(a, b1, b2, b3, b4, b5, b6, b7, b8).queryYield));
    def join[A >: Nothing <: Any, B1 >: Nothing <: Any, B2 >: Nothing <: Any, B3 >: Nothing <: Any, B4 >: Nothing <: Any, B5 >: Nothing <: Any, B6 >: Nothing <: Any, B7 >: Nothing <: Any, B8 >: Nothing <: Any, B9 >: Nothing <: Any, C >: Nothing <: Any](q: org.squeryl.Queryable[A], q1: org.squeryl.dsl.internal.JoinedQueryable[B1], q2: org.squeryl.dsl.internal.JoinedQueryable[B2], q3: org.squeryl.dsl.internal.JoinedQueryable[B3], q4: org.squeryl.dsl.internal.JoinedQueryable[B4], q5: org.squeryl.dsl.internal.JoinedQueryable[B5], q6: org.squeryl.dsl.internal.JoinedQueryable[B6], q7: org.squeryl.dsl.internal.JoinedQueryable[B7], q8: org.squeryl.dsl.internal.JoinedQueryable[B8], q9: org.squeryl.dsl.internal.JoinedQueryable[B9])(f: (A, B1, B2, B3, B4, B5, B6, B7, B8, B9) => org.squeryl.dsl.boilerplate.JoinQueryYield9[C]): org.squeryl.Query[C] = JoinSignatures.this.from[A, B1, B2, B3, B4, B5, B6, B7, B8, B9, C](q, q1, q2, q3, q4, q5, q6, q7, q8, q9)(((a: A, b1: B1, b2: B2, b3: B3, b4: B4, b5: B5, b6: B6, b7: B7, b8: B8, b9: B9) => f.apply(a, b1, b2, b3, b4, b5, b6, b7, b8, b9).queryYield))
  };
  class JoinQueryYield1[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield1.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield1[R] = {
      JoinQueryYield1.super.<init>();
      ()
    }
  };
  class JoinQueryYield2[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield2.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield2[R] = {
      JoinQueryYield2.super.<init>();
      ()
    }
  };
  class JoinQueryYield3[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield3.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield3[R] = {
      JoinQueryYield3.super.<init>();
      ()
    }
  };
  class JoinQueryYield4[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield4.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield4[R] = {
      JoinQueryYield4.super.<init>();
      ()
    }
  };
  class JoinQueryYield5[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield5.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield5[R] = {
      JoinQueryYield5.super.<init>();
      ()
    }
  };
  class JoinQueryYield6[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield6.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield6[R] = {
      JoinQueryYield6.super.<init>();
      ()
    }
  };
  class JoinQueryYield7[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield7.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield7[R] = {
      JoinQueryYield7.super.<init>();
      ()
    }
  };
  class JoinQueryYield8[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield8.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield8[R] = {
      JoinQueryYield8.super.<init>();
      ()
    }
  };
  class JoinQueryYield9[R >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <stable> <accessor> <paramaccessor> def queryYield: org.squeryl.dsl.QueryYield[R] = JoinQueryYield9.this.queryYield;
    def <init>(queryYield: org.squeryl.dsl.QueryYield[R]): org.squeryl.dsl.boilerplate.JoinQueryYield9[R] = {
      JoinQueryYield9.super.<init>();
      ()
    }
  }
}