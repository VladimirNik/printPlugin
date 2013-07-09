package org.squeryl.dsl.ast {
  import org.squeryl.internals.StatementWriter;
  class UpdateStatement extends ExpressionNode {
    <paramaccessor> private[this] val _whereClause: Option[_root_.scala.Function0[LogicalBoolean]] = _;
    <paramaccessor> private[this] val uas: Seq[UpdateAssignment] = _;
    def <init>(_whereClause: Option[_root_.scala.Function0[LogicalBoolean]], uas: Seq[UpdateAssignment]) = {
      super.<init>();
      ()
    };
    val whereClause: Option[LogicalBoolean] = _whereClause.map(((x$1) => x$1.apply));
    override def children = whereClause.toList.$plus$plus(values);
    def doWrite(sw: StatementWriter) = ();
    def columns = uas.map(((ua) => ua.left));
    def values = uas.map(((ua) => ua.right))
  }
}