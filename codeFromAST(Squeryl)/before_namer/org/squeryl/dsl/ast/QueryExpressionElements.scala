package org.squeryl.dsl.ast {
  abstract trait QueryExpressionElements extends ExpressionNode {
    def $init$() = {
      ()
    };
    var inhibitAliasOnSelectElementReference = false;
    def isChild(q: QueryableExpressionNode): Boolean;
    def alias: String;
    def selectDistinct: Boolean;
    def isForUpdate: Boolean;
    def page: Option[scala.Tuple2[Int, Int]];
    def views: Iterable[QueryableExpressionNode];
    def isJoinForm: Boolean;
    def subQueries: Iterable[QueryableExpressionNode];
    def tableExpressions: Iterable[QueryableExpressionNode];
    def selectList: Iterable[SelectElement];
    def whereClause: Option[ExpressionNode];
    def hasUnInhibitedWhereClause = whereClause match {
      case None => false
      case Some((e @ (_: ExpressionNode))) => if (e.inhibited)
        false
      else
        if (e.children.size.$eq$eq(0))
          true
        else
          e.children.exists(((x$1) => x$1.inhibited.unary_$bang))
    };
    def havingClause: Option[ExpressionNode];
    def groupByClause: Iterable[ExpressionNode];
    def orderByClause: Iterable[ExpressionNode]
  }
}