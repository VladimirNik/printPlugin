package org.squeryl.dsl {
  import org.squeryl.Query;
  import org.squeryl.internals.ResultSetMapper;
  import java.sql.ResultSet;
  class DelegateQuery[M >: Nothing <: Any] extends Object with org.squeryl.Query[M] {
    <paramaccessor> private[this] val q: org.squeryl.Query[M] = _;
    <stable> <accessor> <paramaccessor> def q: org.squeryl.Query[M] = DelegateQuery.this.q;
    def <init>(q: org.squeryl.Query[M]): org.squeryl.dsl.DelegateQuery[M] = {
      DelegateQuery.super.<init>();
      ()
    };
    def iterator: Iterator[M] = DelegateQuery.this.q.iterator;
    def distinct: org.squeryl.Query[M] = DelegateQuery.this.q.distinct;
    def forUpdate: org.squeryl.Query[M] = DelegateQuery.this.q.forUpdate;
    def dumpAst: String = DelegateQuery.this.q.dumpAst;
    def page(offset: Int, length: Int): org.squeryl.Query[M] = DelegateQuery.this.q.page(offset, length);
    def statement: String = DelegateQuery.this.q.statement;
    def ast: org.squeryl.dsl.ast.ExpressionNode = DelegateQuery.this.q.ast;
    protected[squeryl] def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): M = DelegateQuery.this.q.invokeYield(rsm, rs);
    override private[squeryl] def copy(asRoot: Boolean): org.squeryl.Query[M] = DelegateQuery.this.q.copy(asRoot);
    def name: String = DelegateQuery.this.q.name;
    private[squeryl] def give(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): M = DelegateQuery.this.q.invokeYield(rsm, rs)
  }
}