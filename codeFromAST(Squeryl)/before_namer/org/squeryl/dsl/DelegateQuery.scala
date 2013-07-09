package org.squeryl.dsl {
  import org.squeryl.Query;
  import org.squeryl.internals.ResultSetMapper;
  import java.sql.ResultSet;
  class DelegateQuery[M >: _root_.scala.Nothing <: _root_.scala.Any] extends Query[M] {
    <paramaccessor> val q: Query[M] = _;
    def <init>(q: Query[M]) = {
      super.<init>();
      ()
    };
    def iterator = q.iterator;
    def distinct = q.distinct;
    def forUpdate = q.forUpdate;
    def dumpAst = q.dumpAst;
    def page(offset: Int, length: Int) = q.page(offset, length);
    def statement: String = q.statement;
    def ast = q.ast;
    protected[squeryl] def invokeYield(rsm: ResultSetMapper, rs: ResultSet) = q.invokeYield(rsm, rs);
    override private[squeryl] def copy(asRoot: Boolean) = q.copy(asRoot);
    def name = q.name;
    private[squeryl] def give(rsm: ResultSetMapper, rs: ResultSet) = q.invokeYield(rsm, rs)
  }
}