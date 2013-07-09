package org.squeryl.dsl.ast {
  import org.squeryl.internals._;
  import org.squeryl.dsl.{QueryYield, AbstractQuery};
  import scala.collection.mutable.ListBuffer;
  class QueryExpressionNode[R >: _root_.scala.Nothing <: _root_.scala.Any] extends QueryExpressionElements with QueryableExpressionNode {
    <paramaccessor> private[this] val _query: AbstractQuery[R] = _;
    <paramaccessor> private[this] val _queryYield: QueryYield[R] = _;
    <paramaccessor> val subQueries: Iterable[QueryableExpressionNode] = _;
    <paramaccessor> val views: Iterable[ViewExpressionNode[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(_query: AbstractQuery[R], _queryYield: QueryYield[R], subQueries: Iterable[QueryableExpressionNode], views: Iterable[ViewExpressionNode[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>();
      ()
    };
    def tableExpressions: Iterable[QueryableExpressionNode] = List(views.filter(((v) => v.inhibited.unary_$bang)), subQueries.filter(((v) => v.inhibited.unary_$bang))).flatten;
    def isJoinForm = _queryYield.joinExpressions.$bang$eq(Nil);
    <synthetic> private[this] val x$1 = _queryYield.queryElements: @scala.unchecked match {
      case scala.Tuple4((whereClause @ _), (havingClause @ _), (groupByClause @ _), (orderByClause @ _)) => scala.Tuple4(whereClause, havingClause, groupByClause, orderByClause)
    };
    val whereClause = x$1._1;
    val havingClause = x$1._2;
    val groupByClause = x$1._3;
    val orderByClause = x$1._4;
    private var _selectList: Iterable[SelectElement] = Iterable.empty;
    private var _sample: Option[AnyRef] = None;
    private def _isPrimitiveType(o: AnyRef) = o.getClass.isPrimitive;
    def isUseableAsSubquery: Boolean = _sample match {
      case None => throw new IllegalStateException("method cannot be called before initialization")
      case Some((p @ (_: Product))) => if (p.getClass.getName.startsWith("scala.Tuple"))
        {
          val z = 0.to(p.productArity.$minus(1)).map(((i) => p.productElement(i)));
          z.exists(((o) => _isPrimitiveType(o.asInstanceOf[AnyRef]))).unary_$bang
        }
      else
        true
      case Some((a @ (_: AnyRef))) => _isPrimitiveType(a).unary_$bang
    };
    def sample: AnyRef = _sample.get;
    def owns(aSample: AnyRef) = _sample.$bang$eq(None).$amp$amp(_sample.get.eq(aSample));
    def getOrCreateSelectElement(fmd: FieldMetaData, forScope: QueryExpressionElements) = throw new UnsupportedOperationException("implement me");
    override def toString = {
      val sb = new StringBuffer();
      sb.append(scala.Symbol("QueryExpressionNode").$plus("["));
      if (_query.isRoot)
        sb.append("root:")
      else
        ();
      sb.append(id);
      sb.append("]");
      sb.append(":rsm=".$plus(_query.resultSetMapper));
      sb.toString
    };
    override def children = {
      val lb = ListBuffer[ExpressionNode]();
      lb.$plus$plus$eq(selectList);
      lb.$plus$plus$eq(views);
      lb.$plus$plus$eq(subQueries);
      lb.$plus$plus$eq(tableExpressions.filter(((e) => e.joinExpression.$bang$eq(None))).map(((x$2) => x$2.joinExpression.get)));
      lb.$plus$plus$eq(whereClause);
      lb.$plus$plus$eq(groupByClause);
      lb.$plus$plus$eq(havingClause);
      lb.$plus$plus$eq(orderByClause);
      lb.toList
    };
    def isChild(q: QueryableExpressionNode): Boolean = views.find(((n) => n.$eq$eq(q))).$bang$eq(None);
    def selectDistinct = _query.selectDistinct;
    def isForUpdate = _query.isForUpdate;
    def page = _query.page;
    def alias = "q".$plus(uniqueId.get);
    def getOrCreateAllSelectElements(forScope: QueryExpressionElements): Iterable[SelectElement] = _selectList.map(((se) => new ExportedSelectElement(se)));
    def setOutExpressionNodesAndSample(sl: Iterable[SelectElement], s: AnyRef) = {
      _selectList = sl;
      _sample = Some(s);
      if (_query.isRoot)
        {
          var jdbcIndex = 1;
          selectList.foreach(((oen) => {
            oen.prepareMapper(jdbcIndex);
            jdbcIndex.$plus$eq(1)
          }));
          var idGen = 0;
          visitDescendants(((node, parent, i) => {
            node.parent = parent;
            if (node.isInstanceOf[UniqueIdInAliaseRequired])
              {
                val nxn = node.asInstanceOf[UniqueIdInAliaseRequired];
                nxn.uniqueId = Some(idGen);
                idGen.$plus$eq(1)
              }
            else
              ()
          }))
        }
      else
        ()
    };
    def selectList: Iterable[SelectElement] = _selectList;
    def doWrite(sw: StatementWriter) = {
      val isNotRoot = parent.$bang$eq(None);
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