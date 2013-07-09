package org.squeryl.dsl {
  import org.squeryl.Queryable;
  import org.squeryl.internals.ResultSetMapper;
  import java.sql.ResultSet;
  class OptionalQueryable[A >: Nothing <: Any] extends Object with org.squeryl.Queryable[Option[A]] {
    <paramaccessor> private[this] val queryable: org.squeryl.Queryable[A] = _;
    <stable> <accessor> <paramaccessor> def queryable: org.squeryl.Queryable[A] = OptionalQueryable.this.queryable;
    def <init>(queryable: org.squeryl.Queryable[A]): org.squeryl.dsl.OptionalQueryable[A] = {
      OptionalQueryable.super.<init>();
      ()
    };
    def name: String = OptionalQueryable.this.queryable.name;
    def inhibitWhen(b: Boolean): org.squeryl.dsl.OptionalQueryable[A] = {
      OptionalQueryable.this.inhibited_=(b);
      this
    };
    private[squeryl] def give(resultSetMapper: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): Option[A] = if (OptionalQueryable.this.inhibited)
      scala.None
    else
      scala.Some.apply[A](OptionalQueryable.this.queryable.give(resultSetMapper, rs))
  }
}