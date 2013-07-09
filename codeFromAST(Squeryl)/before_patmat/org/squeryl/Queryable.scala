package org.squeryl {
  import dsl.ast.LogicalBoolean;
  import dsl.QueryDsl;
  import internals.ResultSetMapper;
  import java.sql.ResultSet;
  abstract trait Queryable[T >: Nothing <: Any] extends scala.AnyRef {
    def /*Queryable*/$init$(): Unit = {
      ()
    };
    def name: String;
    private[this] var inhibited: Boolean = false;
    <accessor> private[squeryl] def inhibited: Boolean = Queryable.this.inhibited;
    <accessor> private[squeryl] def inhibited_=(x$1: Boolean): Unit = Queryable.this.inhibited = x$1;
    private[squeryl] def give(resultSetMapper: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): T;
    def where(whereClauseFunctor: T => org.squeryl.dsl.ast.LogicalBoolean)(implicit dsl: org.squeryl.dsl.QueryDsl): org.squeryl.Query[T] = {
      import dsl._;
      dsl.from[T, T](this)(((q0: T) => dsl.where(whereClauseFunctor.apply(q0)).select[T](q0)))
    }
  }
}