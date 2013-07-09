package org.squeryl.dsl.ast {
  import org.squeryl.internals._;
  import org.squeryl.dsl.{QueryYield, AbstractQuery};
  import scala.collection.mutable.ListBuffer;
  class QueryExpressionNode[R >: Nothing <: Any] extends Object with org.squeryl.dsl.ast.QueryExpressionElements with org.squeryl.dsl.ast.QueryableExpressionNode {
    <paramaccessor> private[this] val _query: org.squeryl.dsl.AbstractQuery[R] = _;
    <paramaccessor> private[this] val _queryYield: org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val subQueries: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode] = _;
    <stable> <accessor> <paramaccessor> def subQueries: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode] = QueryExpressionNode.this.subQueries;
    <paramaccessor> private[this] val views: Iterable[org.squeryl.dsl.ast.ViewExpressionNode[_]] = _;
    <stable> <accessor> <paramaccessor> def views: Iterable[org.squeryl.dsl.ast.ViewExpressionNode[_]] = QueryExpressionNode.this.views;
    def <init>(_query: org.squeryl.dsl.AbstractQuery[R], _queryYield: org.squeryl.dsl.QueryYield[R], subQueries: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode], views: Iterable[org.squeryl.dsl.ast.ViewExpressionNode[_]]): org.squeryl.dsl.ast.QueryExpressionNode[R] = {
      QueryExpressionNode.super.<init>();
      ()
    };
    def tableExpressions: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode] = immutable.this.List.apply[Iterable[org.squeryl.dsl.ast.QueryableExpressionNode]](QueryExpressionNode.this.views.filter(((v: org.squeryl.dsl.ast.ViewExpressionNode[_]) => v.inhibited.unary_!)), QueryExpressionNode.this.subQueries.filter(((v: org.squeryl.dsl.ast.QueryableExpressionNode) => v.inhibited.unary_!))).flatten[org.squeryl.dsl.ast.QueryableExpressionNode](scala.this.Predef.conforms[Iterable[org.squeryl.dsl.ast.QueryableExpressionNode]]);
    def isJoinForm: Boolean = QueryExpressionNode.this._queryYield.joinExpressions.!=(immutable.this.Nil);
    <synthetic> private[this] val x$1: (Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]) = (QueryExpressionNode.this._queryYield.queryElements: (Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]) @unchecked) match {
      case (_1: Option[org.squeryl.dsl.ast.ExpressionNode], _2: Option[org.squeryl.dsl.ast.ExpressionNode], _3: Iterable[org.squeryl.dsl.ast.ExpressionNode], _4: Iterable[org.squeryl.dsl.ast.ExpressionNode])(Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode])((whereClause @ _), (havingClause @ _), (groupByClause @ _), (orderByClause @ _)) => scala.Tuple4.apply[Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]](whereClause, havingClause, groupByClause, orderByClause)
    };
    private[this] val whereClause: Option[org.squeryl.dsl.ast.ExpressionNode] = QueryExpressionNode.this.x$1._1;
    <stable> <accessor> def whereClause: Option[org.squeryl.dsl.ast.ExpressionNode] = QueryExpressionNode.this.whereClause;
    private[this] val havingClause: Option[org.squeryl.dsl.ast.ExpressionNode] = QueryExpressionNode.this.x$1._2;
    <stable> <accessor> def havingClause: Option[org.squeryl.dsl.ast.ExpressionNode] = QueryExpressionNode.this.havingClause;
    private[this] val groupByClause: Iterable[org.squeryl.dsl.ast.ExpressionNode] = QueryExpressionNode.this.x$1._3;
    <stable> <accessor> def groupByClause: Iterable[org.squeryl.dsl.ast.ExpressionNode] = QueryExpressionNode.this.groupByClause;
    private[this] val orderByClause: Iterable[org.squeryl.dsl.ast.ExpressionNode] = QueryExpressionNode.this.x$1._4;
    <stable> <accessor> def orderByClause: Iterable[org.squeryl.dsl.ast.ExpressionNode] = QueryExpressionNode.this.orderByClause;
    private[this] var _selectList: Iterable[org.squeryl.dsl.ast.SelectElement] = scala.`package`.Iterable.empty[Nothing];
    <accessor> private def _selectList: Iterable[org.squeryl.dsl.ast.SelectElement] = QueryExpressionNode.this._selectList;
    <accessor> private def _selectList_=(x$1: Iterable[org.squeryl.dsl.ast.SelectElement]): Unit = QueryExpressionNode.this._selectList = x$1;
    private[this] var _sample: Option[AnyRef] = scala.None;
    <accessor> private def _sample: Option[AnyRef] = QueryExpressionNode.this._sample;
    <accessor> private def _sample_=(x$1: Option[AnyRef]): Unit = QueryExpressionNode.this._sample = x$1;
    private def _isPrimitiveType(o: AnyRef): Boolean = o.getClass().isPrimitive();
    def isUseableAsSubquery: Boolean = QueryExpressionNode.this._sample match {
      case scala.None => throw new java.this.lang.IllegalStateException("method cannot be called before initialization")
      case (x: AnyRef)Some[AnyRef]((p @ (_: Product))) => if (p.getClass().getName().startsWith("scala.Tuple"))
        {
          val z: scala.collection.immutable.IndexedSeq[Any] = scala.this.Predef.intWrapper(0).to(p.productArity.-(1)).map[Any, scala.collection.immutable.IndexedSeq[Any]](((i: Int) => p.productElement(i)))(immutable.this.IndexedSeq.canBuildFrom[Any]);
          z.exists(((o: Any) => QueryExpressionNode.this._isPrimitiveType(o.asInstanceOf[AnyRef]))).unary_!
        }
      else
        true
      case (x: AnyRef)Some[AnyRef]((a @ (_: AnyRef))) => QueryExpressionNode.this._isPrimitiveType(a).unary_!
    };
    def sample: AnyRef = QueryExpressionNode.this._sample.get;
    def owns(aSample: AnyRef): Boolean = QueryExpressionNode.this._sample.!=(scala.None).&&(QueryExpressionNode.this._sample.get.eq(aSample));
    def getOrCreateSelectElement(fmd: org.squeryl.internals.FieldMetaData, forScope: org.squeryl.dsl.ast.QueryExpressionElements): Nothing = throw new scala.`package`.UnsupportedOperationException("implement me");
    override def toString: String = {
      val sb: StringBuffer = new java.this.lang.StringBuffer();
      sb.append(scala.this.Predef.any2stringadd(scala.Symbol.apply("QueryExpressionNode")).+("["));
      if (QueryExpressionNode.this._query.isRoot)
        sb.append("root:")
      else
        ();
      sb.append(QueryExpressionNode.this.id);
      sb.append("]");
      sb.append(":rsm=".+(QueryExpressionNode.this._query.resultSetMapper));
      sb.toString()
    };
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = {
      val lb: scala.collection.mutable.ListBuffer[org.squeryl.dsl.ast.ExpressionNode] = scala.collection.mutable.ListBuffer.apply[org.squeryl.dsl.ast.ExpressionNode]();
      lb.++=(QueryExpressionNode.this.selectList);
      lb.++=(QueryExpressionNode.this.views);
      lb.++=(QueryExpressionNode.this.subQueries);
      lb.++=(QueryExpressionNode.this.tableExpressions.filter(((e: org.squeryl.dsl.ast.QueryableExpressionNode) => e.joinExpression.!=(scala.None))).map[org.squeryl.dsl.ast.LogicalBoolean, scala.collection.TraversableOnce[org.squeryl.dsl.ast.ExpressionNode]](((x$2: org.squeryl.dsl.ast.QueryableExpressionNode) => x$2.joinExpression.get))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.LogicalBoolean]));
      lb.++=(scala.this.Option.option2Iterable[org.squeryl.dsl.ast.ExpressionNode](QueryExpressionNode.this.whereClause));
      lb.++=(QueryExpressionNode.this.groupByClause);
      lb.++=(scala.this.Option.option2Iterable[org.squeryl.dsl.ast.ExpressionNode](QueryExpressionNode.this.havingClause));
      lb.++=(QueryExpressionNode.this.orderByClause);
      lb.toList
    };
    def isChild(q: org.squeryl.dsl.ast.QueryableExpressionNode): Boolean = QueryExpressionNode.this.views.find(((n: org.squeryl.dsl.ast.ViewExpressionNode[_]) => n.==(q))).!=(scala.None);
    def selectDistinct: Boolean = QueryExpressionNode.this._query.selectDistinct;
    def isForUpdate: Boolean = QueryExpressionNode.this._query.isForUpdate;
    def page: Option[(Int, Int)] = QueryExpressionNode.this._query.page;
    def alias: String = "q".+(QueryExpressionNode.this.uniqueId.get);
    def getOrCreateAllSelectElements(forScope: org.squeryl.dsl.ast.QueryExpressionElements): Iterable[org.squeryl.dsl.ast.SelectElement] = QueryExpressionNode.this._selectList.map[org.squeryl.dsl.ast.ExportedSelectElement, Iterable[org.squeryl.dsl.ast.SelectElement]](((se: org.squeryl.dsl.ast.SelectElement) => new ExportedSelectElement(se)))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.ExportedSelectElement]);
    def setOutExpressionNodesAndSample(sl: Iterable[org.squeryl.dsl.ast.SelectElement], s: AnyRef): Unit = {
      QueryExpressionNode.this._selectList_=(sl);
      QueryExpressionNode.this._sample_=(scala.Some.apply[AnyRef](s));
      if (QueryExpressionNode.this._query.isRoot)
        {
          var jdbcIndex: Int = 1;
          QueryExpressionNode.this.selectList.foreach[Unit](((oen: org.squeryl.dsl.ast.SelectElement) => {
            oen.prepareMapper(jdbcIndex);
            jdbcIndex = jdbcIndex.+(1)
          }));
          var idGen: Int = 0;
          QueryExpressionNode.this.visitDescendants(((node: org.squeryl.dsl.ast.ExpressionNode, parent: Option[org.squeryl.dsl.ast.ExpressionNode], i: Int) => {
            node.parent_=(parent);
            if (node.isInstanceOf[org.squeryl.dsl.ast.UniqueIdInAliaseRequired])
              {
                val nxn: org.squeryl.dsl.ast.UniqueIdInAliaseRequired = node.asInstanceOf[org.squeryl.dsl.ast.UniqueIdInAliaseRequired];
                nxn.uniqueId_=(scala.Some.apply[Int](idGen));
                idGen = idGen.+(1)
              }
            else
              ()
          }))
        }
      else
        ()
    };
    def selectList: Iterable[org.squeryl.dsl.ast.SelectElement] = QueryExpressionNode.this._selectList;
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      val isNotRoot: Boolean = QueryExpressionNode.this.parent.!=(scala.None);
      if (isNotRoot)
        {
          sw.write("(");
          sw.indent(1)
        }
      else
        ();
      sw.databaseAdapter.writeQuery(this, sw);
      if (isNotRoot)
        {
          sw.unindent(1);
          sw.write(") ")
        }
      else
        ()
    }
  }
}