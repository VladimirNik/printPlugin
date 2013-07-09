package org.squeryl.dsl.ast {
  import org.squeryl.dsl._;
  import scala.collection.mutable.HashMap;
  import org.squeryl.internals.{StatementWriter, ResultSetMapper, FieldMetaData};
  import org.squeryl.{Session, View};
  class ViewExpressionNode[U >: Nothing <: Any] extends Object with org.squeryl.dsl.ast.QueryableExpressionNode {
    <paramaccessor> private[this] val view: org.squeryl.View[U] = _;
    <stable> <accessor> <paramaccessor> def view: org.squeryl.View[U] = ViewExpressionNode.this.view;
    def <init>(view: org.squeryl.View[U]): org.squeryl.dsl.ast.ViewExpressionNode[U] = {
      ViewExpressionNode.super.<init>();
      ()
    };
    private[this] val _selectElements: scala.collection.mutable.HashMap[org.squeryl.internals.FieldMetaData,org.squeryl.dsl.ast.SelectElement] = new scala.collection.mutable.HashMap[org.squeryl.internals.FieldMetaData,org.squeryl.dsl.ast.SelectElement]();
    <stable> <accessor> private def _selectElements: scala.collection.mutable.HashMap[org.squeryl.internals.FieldMetaData,org.squeryl.dsl.ast.SelectElement] = ViewExpressionNode.this._selectElements;
    def isChild(q: org.squeryl.dsl.ast.QueryableExpressionNode): Boolean = false;
    def getOrCreateAllSelectElements(forScope: org.squeryl.dsl.ast.QueryExpressionElements): Iterable[org.squeryl.dsl.ast.SelectElement] = {
      val export: Boolean = forScope.isChild(this).unary_!;
      ViewExpressionNode.this.view.posoMetaData.fieldsMetaData.map[org.squeryl.dsl.ast.SelectElement, Iterable[org.squeryl.dsl.ast.SelectElement]](((fmd: org.squeryl.internals.FieldMetaData) => ViewExpressionNode.this.getOrCreateSelectElement(fmd, export)))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.SelectElement])
    };
    private def getOrCreateSelectElement(fmd: org.squeryl.internals.FieldMetaData, export: Boolean): org.squeryl.dsl.ast.SelectElement = {
      val e: Option[org.squeryl.dsl.ast.SelectElement] = ViewExpressionNode.this._selectElements.get(fmd);
      val n: org.squeryl.dsl.ast.SelectElement = if (e.!=(scala.None))
        e.get
      else
        {
          val r: org.squeryl.dsl.ast.FieldSelectElement = new FieldSelectElement(this, fmd, ViewExpressionNode.this.resultSetMapper);
          ViewExpressionNode.this._selectElements.put(fmd, r);
          r
        };
      if (export)
        new ExportedSelectElement(n)
      else
        n
    };
    def getOrCreateSelectElement(fmd: org.squeryl.internals.FieldMetaData): org.squeryl.dsl.ast.SelectElement = ViewExpressionNode.this.getOrCreateSelectElement(fmd, false);
    def getOrCreateSelectElement(fmd: org.squeryl.internals.FieldMetaData, forScope: org.squeryl.dsl.ast.QueryExpressionElements): org.squeryl.dsl.ast.SelectElement = ViewExpressionNode.this.getOrCreateSelectElement(fmd, forScope.isChild(this).unary_!);
    private[this] val resultSetMapper: org.squeryl.internals.ResultSetMapper = new org.squeryl.internals.ResultSetMapper();
    <stable> <accessor> def resultSetMapper: org.squeryl.internals.ResultSetMapper = ViewExpressionNode.this.resultSetMapper;
    def alias: String = org.squeryl.Session.currentSession.databaseAdapter.viewAlias(this);
    def owns(aSample: AnyRef): Boolean = aSample.eq(ViewExpressionNode.this.sample.asInstanceOf[AnyRef]);
    private[this] var _sample: Option[U] = scala.None;
    <accessor> private def _sample: Option[U] = ViewExpressionNode.this._sample;
    <accessor> private def _sample_=(x$1: Option[U]): Unit = ViewExpressionNode.this._sample = x$1;
    private[squeryl] def sample_=(d: U): Unit = ViewExpressionNode.this._sample_=(scala.Some.apply[U](d));
    def sample: U = ViewExpressionNode.this._sample.get;
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.write(sw.quoteName(ViewExpressionNode.this.view.prefixedName));
    override def toString: String = {
      val sb: StringBuffer = new java.this.lang.StringBuffer();
      sb.append(scala.this.Predef.any2stringadd(scala.Symbol.apply("ViewExpressionNode")).+("["));
      sb.append(ViewExpressionNode.this.sample);
      sb.append("]:");
      sb.append("rsm=");
      sb.append(ViewExpressionNode.this.resultSetMapper);
      sb.toString()
    }
  }
}