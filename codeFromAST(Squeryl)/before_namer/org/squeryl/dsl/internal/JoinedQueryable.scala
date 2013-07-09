package org.squeryl.dsl.internal {
  import org.squeryl.Queryable;
  import org.squeryl.internals.ResultSetMapper;
  import java.sql.ResultSet;
  abstract trait JoinedQueryable[A >: _root_.scala.Nothing <: _root_.scala.Any] extends Queryable[A] {
    def $init$() = {
      ()
    };
    def name = throw new UnsupportedOperationException(scala.Symbol("OuterJoinedQueryable").$plus(" is a temporary class, not meant to become part of the ast"));
    private[squeryl] def give(resultSetMapper: ResultSetMapper, rs: ResultSet) = throw new UnsupportedOperationException(scala.Symbol("OuterJoinedQueryable").$plus(" is a temporary class, not meant to become part of the ast"))
  };
  class OuterJoinedQueryable[A >: _root_.scala.Nothing <: _root_.scala.Any] extends JoinedQueryable[Option[A]] {
    <paramaccessor> val queryable: Queryable[A] = _;
    <paramaccessor> val leftRightOrFull: String = _;
    def <init>(queryable: Queryable[A], leftRightOrFull: String) = {
      super.<init>();
      ()
    };
    def inhibitWhen(inhibited: Boolean) = {
      this.inhibited = inhibited;
      this
    }
  };
  class InnerJoinedQueryable[A >: _root_.scala.Nothing <: _root_.scala.Any] extends JoinedQueryable[A] {
    <paramaccessor> val queryable: Queryable[A] = _;
    <paramaccessor> val leftRightOrFull: String = _;
    def <init>(queryable: Queryable[A], leftRightOrFull: String) = {
      super.<init>();
      ()
    }
  }
}