package org.squeryl.dsl.internal {
  import org.squeryl.Queryable;
  import org.squeryl.internals.ResultSetMapper;
  import java.sql.ResultSet;
  abstract trait JoinedQueryable[A >: Nothing <: Any] extends Object with org.squeryl.Queryable[A] {
    def /*JoinedQueryable*/$init$(): Unit = {
      ()
    };
    def name: Nothing = throw new scala.`package`.UnsupportedOperationException(scala.this.Predef.any2stringadd(scala.Symbol.apply("OuterJoinedQueryable")).+(" is a temporary class, not meant to become part of the ast"));
    private[squeryl] def give(resultSetMapper: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): Nothing = throw new scala.`package`.UnsupportedOperationException(scala.this.Predef.any2stringadd(scala.Symbol.apply("OuterJoinedQueryable")).+(" is a temporary class, not meant to become part of the ast"))
  };
  class OuterJoinedQueryable[A >: Nothing <: Any] extends Object with org.squeryl.dsl.internal.JoinedQueryable[Option[A]] {
    <paramaccessor> private[this] val queryable: org.squeryl.Queryable[A] = _;
    <stable> <accessor> <paramaccessor> def queryable: org.squeryl.Queryable[A] = OuterJoinedQueryable.this.queryable;
    <paramaccessor> private[this] val leftRightOrFull: String = _;
    <stable> <accessor> <paramaccessor> def leftRightOrFull: String = OuterJoinedQueryable.this.leftRightOrFull;
    def <init>(queryable: org.squeryl.Queryable[A], leftRightOrFull: String): org.squeryl.dsl.internal.OuterJoinedQueryable[A] = {
      OuterJoinedQueryable.super.<init>();
      ()
    };
    def inhibitWhen(inhibited: Boolean): org.squeryl.dsl.internal.OuterJoinedQueryable[A] = {
      this.inhibited_=(inhibited);
      this
    }
  };
  class InnerJoinedQueryable[A >: Nothing <: Any] extends Object with org.squeryl.dsl.internal.JoinedQueryable[A] {
    <paramaccessor> private[this] val queryable: org.squeryl.Queryable[A] = _;
    <stable> <accessor> <paramaccessor> def queryable: org.squeryl.Queryable[A] = InnerJoinedQueryable.this.queryable;
    <paramaccessor> private[this] val leftRightOrFull: String = _;
    <stable> <accessor> <paramaccessor> def leftRightOrFull: String = InnerJoinedQueryable.this.leftRightOrFull;
    def <init>(queryable: org.squeryl.Queryable[A], leftRightOrFull: String): org.squeryl.dsl.internal.InnerJoinedQueryable[A] = {
      InnerJoinedQueryable.super.<init>();
      ()
    }
  }
}