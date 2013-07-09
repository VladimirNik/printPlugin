package org.squeryl.dsl.ast {
  import org.squeryl.internals.StatementWriter;
  class UpdateStatement extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val _whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = _;
    <paramaccessor> private[this] val uas: Seq[org.squeryl.dsl.ast.UpdateAssignment] = _;
    def <init>(_whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean], uas: Seq[org.squeryl.dsl.ast.UpdateAssignment]): org.squeryl.dsl.ast.UpdateStatement = {
      UpdateStatement.super.<init>();
      ()
    };
    private[this] val whereClause: Option[org.squeryl.dsl.ast.LogicalBoolean] = UpdateStatement.this._whereClause.map[org.squeryl.dsl.ast.LogicalBoolean](((x$1: () => org.squeryl.dsl.ast.LogicalBoolean) => x$1.apply()));
    <stable> <accessor> def whereClause: Option[org.squeryl.dsl.ast.LogicalBoolean] = UpdateStatement.this.whereClause;
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = UpdateStatement.this.whereClause.toList.++[org.squeryl.dsl.ast.ExpressionNode, List[org.squeryl.dsl.ast.ExpressionNode]](UpdateStatement.this.values)(immutable.this.List.canBuildFrom[org.squeryl.dsl.ast.ExpressionNode]);
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = ();
    def columns: Seq[org.squeryl.internals.FieldMetaData] = UpdateStatement.this.uas.map[org.squeryl.internals.FieldMetaData, Seq[org.squeryl.internals.FieldMetaData]](((ua: org.squeryl.dsl.ast.UpdateAssignment) => ua.left))(collection.this.Seq.canBuildFrom[org.squeryl.internals.FieldMetaData]);
    def values: Seq[org.squeryl.dsl.ast.ExpressionNode] = UpdateStatement.this.uas.map[org.squeryl.dsl.ast.ExpressionNode, Seq[org.squeryl.dsl.ast.ExpressionNode]](((ua: org.squeryl.dsl.ast.UpdateAssignment) => ua.right))(collection.this.Seq.canBuildFrom[org.squeryl.dsl.ast.ExpressionNode])
  }
}