package org.squeryl {
  import dsl.ast.ExpressionNode;
  import internals.ResultSetMapper;
  import java.sql.ResultSet;
  abstract trait Query[R >: Nothing <: Any] extends Object with org.squeryl.Queryable[R] {
    def /*Query*/$init$(): Unit = {
      ()
    };
    def iterator: Iterator[R];
    protected[squeryl] def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, resultSet: java.sql.ResultSet): R;
    def dumpAst: String;
    def statement: String;
    def ast: org.squeryl.dsl.ast.ExpressionNode;
    private[squeryl] def copy(asRoot: Boolean): org.squeryl.Query[R];
    def single: R = {
      val i: Iterator[R] = Query.this.iterator;
      val r: R = i.next();
      if (i.hasNext)
        org.squeryl.internals.Utils.throwError("single called on query returning more than one row : \n".+(Query.this.statement))
      else
        ();
      r
    };
    def singleOption: Option[R] = {
      val i: Iterator[R] = Query.this.iterator;
      val res: Option[R] = if (i.hasNext)
        scala.Some.apply[R](i.next())
      else
        scala.None;
      if (i.hasNext)
        org.squeryl.internals.Utils.throwError("singleOption called on query returning more than one row : \n".+(Query.this.statement))
      else
        ();
      res
    };
    def headOption: Option[R] = {
      val i: Iterator[R] = Query.this.iterator;
      if (i.hasNext)
        scala.Some.apply[R](i.next())
      else
        scala.None
    };
    def distinct: org.squeryl.Query[R];
    def union(q: org.squeryl.Query[R]): org.squeryl.Query[R] = throw new scala.`package`.UnsupportedOperationException("not implemented");
    def minus(q: org.squeryl.Query[R]): org.squeryl.Query[R] = throw new scala.`package`.UnsupportedOperationException("not implemented");
    def forUpdate: org.squeryl.Query[R];
    def page(offset: Int, pageLength: Int): org.squeryl.Query[R]
  }
}