package org.squeryl {
  import dsl.ast.ExpressionNode;
  import internals.ResultSetMapper;
  import java.sql.ResultSet;
  abstract trait Query[R >: _root_.scala.Nothing <: _root_.scala.Any] extends Queryable[R] {
    def $init$() = {
      ()
    };
    def iterator: Iterator[R];
    protected[squeryl] def invokeYield(rsm: ResultSetMapper, resultSet: ResultSet): R;
    def dumpAst: String;
    def statement: String;
    def ast: ExpressionNode;
    private[squeryl] def copy(asRoot: Boolean): Query[R];
    def single: R = {
      val i = iterator;
      val r = i.next;
      if (i.hasNext)
        org.squeryl.internals.Utils.throwError("single called on query returning more than one row : \n".$plus(statement))
      else
        ();
      r
    };
    def singleOption: Option[R] = {
      val i = iterator;
      val res = if (i.hasNext)
        Some(i.next)
      else
        None;
      if (i.hasNext)
        org.squeryl.internals.Utils.throwError("singleOption called on query returning more than one row : \n".$plus(statement))
      else
        ();
      res
    };
    def headOption = {
      val i = iterator;
      if (i.hasNext)
        Some(i.next)
      else
        None
    };
    def distinct: Query[R];
    def union(q: Query[R]): Query[R] = throw new UnsupportedOperationException("not implemented");
    def minus(q: Query[R]): Query[R] = throw new UnsupportedOperationException("not implemented");
    def forUpdate: Query[R];
    def page(offset: Int, pageLength: Int): Query[R]
  }
}