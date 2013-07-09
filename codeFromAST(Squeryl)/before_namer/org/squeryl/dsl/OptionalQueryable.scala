package org.squeryl.dsl {
  import org.squeryl.Queryable;
  import org.squeryl.internals.ResultSetMapper;
  import java.sql.ResultSet;
  class OptionalQueryable[A >: _root_.scala.Nothing <: _root_.scala.Any] extends Queryable[Option[A]] {
    <paramaccessor> val queryable: Queryable[A] = _;
    def <init>(queryable: Queryable[A]) = {
      super.<init>();
      ()
    };
    def name = queryable.name;
    def inhibitWhen(b: Boolean) = {
      inhibited = b;
      this
    };
    private[squeryl] def give(resultSetMapper: ResultSetMapper, rs: ResultSet) = if (inhibited)
      None
    else
      Some(queryable.give(resultSetMapper, rs))
  }
}