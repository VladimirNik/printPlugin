package org.squeryl {
  import dsl.ast.LogicalBoolean;
  import dsl.QueryDsl;
  import internals.ResultSetMapper;
  import java.sql.ResultSet;
  abstract trait Queryable[T >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    def $init$() = {
      ()
    };
    def name: String;
    private[squeryl] var inhibited = false;
    private[squeryl] def give(resultSetMapper: ResultSetMapper, rs: ResultSet): T;
    def where(whereClauseFunctor: _root_.scala.Function1[T, LogicalBoolean])(implicit dsl: QueryDsl): Query[T] = {
      import dsl._;
      from(this)(((q0) => dsl.where(whereClauseFunctor(q0)).select(q0)))
    }
  }
}