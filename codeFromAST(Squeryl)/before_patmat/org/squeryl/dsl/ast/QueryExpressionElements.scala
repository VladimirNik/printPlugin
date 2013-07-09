package org.squeryl.dsl.ast {
  abstract trait QueryExpressionElements extends Object with org.squeryl.dsl.ast.ExpressionNode {
    def /*QueryExpressionElements*/$init$(): Unit = {
      ()
    };
    private[this] var inhibitAliasOnSelectElementReference: Boolean = false;
    <accessor> def inhibitAliasOnSelectElementReference: Boolean = QueryExpressionElements.this.inhibitAliasOnSelectElementReference;
    <accessor> def inhibitAliasOnSelectElementReference_=(x$1: Boolean): Unit = QueryExpressionElements.this.inhibitAliasOnSelectElementReference = x$1;
    def isChild(q: org.squeryl.dsl.ast.QueryableExpressionNode): Boolean;
    def alias: String;
    def selectDistinct: Boolean;
    def isForUpdate: Boolean;
    def page: Option[(Int, Int)];
    def views: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode];
    def isJoinForm: Boolean;
    def subQueries: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode];
    def tableExpressions: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode];
    def selectList: Iterable[org.squeryl.dsl.ast.SelectElement];
    def whereClause: Option[org.squeryl.dsl.ast.ExpressionNode];
    def hasUnInhibitedWhereClause: Boolean = QueryExpressionElements.this.whereClause match {
      case scala.None => false
      case (x: org.squeryl.dsl.ast.ExpressionNode)Some[org.squeryl.dsl.ast.ExpressionNode]((e @ (_: org.squeryl.dsl.ast.ExpressionNode))) => if (e.inhibited)
        false
      else
        if (e.children.size.==(0))
          true
        else
          e.children.exists(((x$1: org.squeryl.dsl.ast.ExpressionNode) => x$1.inhibited.unary_!))
    };
    def havingClause: Option[org.squeryl.dsl.ast.ExpressionNode];
    def groupByClause: Iterable[org.squeryl.dsl.ast.ExpressionNode];
    def orderByClause: Iterable[org.squeryl.dsl.ast.ExpressionNode]
  }
}