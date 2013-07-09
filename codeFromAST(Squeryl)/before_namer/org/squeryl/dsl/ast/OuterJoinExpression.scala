package org.squeryl.dsl.ast {
  class OuterJoinExpression extends scala.AnyRef {
    <paramaccessor> val queryableExpressionNode: QueryableExpressionNode = _;
    <paramaccessor> val leftRightOrFull: String = _;
    <paramaccessor> val matchExpression: ExpressionNode = _;
    def <init>(queryableExpressionNode: QueryableExpressionNode, leftRightOrFull: String, matchExpression: ExpressionNode) = {
      super.<init>();
      ()
    };
    def inhibited = queryableExpressionNode.inhibited
  }
}