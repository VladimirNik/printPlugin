package org.squeryl.dsl.ast {
  import org.squeryl.dsl._;
  import collection.mutable.HashMap;
  import org.squeryl.internals.{StatementWriter, ResultSetMapper, FieldMetaData};
  import org.squeryl.{Session, View};
  class ViewExpressionNode[U >: _root_.scala.Nothing <: _root_.scala.Any] extends QueryableExpressionNode {
    <paramaccessor> val view: View[U] = _;
    def <init>(view: View[U]) = {
      super.<init>();
      ()
    };
    private val _selectElements = new HashMap[FieldMetaData, SelectElement]();
    def isChild(q: QueryableExpressionNode) = false;
    def getOrCreateAllSelectElements(forScope: QueryExpressionElements): Iterable[SelectElement] = {
      val export = forScope.isChild(this).unary_$bang;
      view.posoMetaData.fieldsMetaData.map(((fmd) => getOrCreateSelectElement(fmd, export)))
    };
    private def getOrCreateSelectElement(fmd: FieldMetaData, export: Boolean): SelectElement = {
      val e = _selectElements.get(fmd);
      val n = if (e.$bang$eq(None))
        e.get
      else
        {
          val r = new FieldSelectElement(this, fmd, resultSetMapper);
          _selectElements.put(fmd, r);
          r
        };
      if (export)
        new ExportedSelectElement(n)
      else
        n
    };
    def getOrCreateSelectElement(fmd: FieldMetaData): SelectElement = getOrCreateSelectElement(fmd, false);
    def getOrCreateSelectElement(fmd: FieldMetaData, forScope: QueryExpressionElements): SelectElement = getOrCreateSelectElement(fmd, forScope.isChild(this).unary_$bang);
    val resultSetMapper = new ResultSetMapper();
    def alias = Session.currentSession.databaseAdapter.viewAlias(this);
    def owns(aSample: AnyRef) = aSample.eq(sample.asInstanceOf[AnyRef]);
    private var _sample: Option[U] = None;
    private[squeryl] def sample_$eq(d: U) = _sample = Some(d);
    def sample = _sample.get;
    def doWrite(sw: StatementWriter) = sw.write(sw.quoteName(view.prefixedName));
    override def toString = {
      val sb = new StringBuffer();
      sb.append(scala.Symbol("ViewExpressionNode").$plus("["));
      sb.append(sample);
      sb.append("]:");
      sb.append("rsm=");
      sb.append(resultSetMapper);
      sb.toString
    }
  }
}