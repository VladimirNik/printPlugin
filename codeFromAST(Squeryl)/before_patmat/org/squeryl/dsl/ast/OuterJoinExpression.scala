package org.squeryl.dsl.ast {
  class OuterJoinExpression extends scala.AnyRef {
    <paramaccessor> private[this] val queryableExpressionNode: org.squeryl.dsl.ast.QueryableExpressionNode = _;
    <stable> <accessor> <paramaccessor> def queryableExpressionNode: org.squeryl.dsl.ast.QueryableExpressionNode = OuterJoinExpression.this.queryableExpressionNode;
    <paramaccessor> private[this] val leftRightOrFull: String = _;
    <stable> <accessor> <paramaccessor> def leftRightOrFull: String = OuterJoinExpression.this.leftRightOrFull;
    <paramaccessor> private[this] val matchExpression: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def matchExpression: org.squeryl.dsl.ast.ExpressionNode = OuterJoinExpression.this.matchExpression;
    def <init>(queryableExpressionNode: org.squeryl.dsl.ast.QueryableExpressionNode, leftRightOrFull: String, matchExpression: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.OuterJoinExpression = {
      OuterJoinExpression.super.<init>();
      ()
    };
    def inhibited: Boolean = OuterJoinExpression.this.queryableExpressionNode.inhibited
  }
}