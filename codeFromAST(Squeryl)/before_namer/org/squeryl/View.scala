package org.squeryl {
  import dsl.QueryDsl;
  import internals._;
  import java.sql.ResultSet;
  class View[T >: _root_.scala.Nothing <: _root_.scala.Any] extends Queryable[T] {
    <paramaccessor> private[this] val _name: String = _;
    <paramaccessor> private[squeryl] val classOfT: Class[T] = _;
    <paramaccessor> private[this] val schema: Schema = _;
    <paramaccessor> private[this] val _prefix: Option[String] = _;
    <paramaccessor> val ked: Option[KeyedEntityDef[T, _$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    private[squeryl] def <init>(_name: String, classOfT: Class[T], schema: Schema, _prefix: Option[String], ked: Option[KeyedEntityDef[T, _$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>();
      ()
    };
    lazy private[squeryl] val _callbacks = schema._callbacks.get(this).getOrElse(NoOpPosoLifecycleEventListener);
    def name = schema.tableNameFromClassName(_name);
    def prefix: Option[String] = if (_prefix.$bang$eq(None))
      _prefix
    else
      schema.name;
    def prefixedName = if (prefix.$bang$eq(None))
      prefix.get.$plus(".").$plus(name)
    else
      name;
    def prefixedPrefixedName(s: String) = if (prefix.$bang$eq(None))
      prefix.get.$plus(".").$plus(s).$plus(name)
    else
      s.$plus(name);
    private[squeryl] def findFieldMetaDataForProperty(name: String) = posoMetaData.findFieldMetaDataForProperty(name);
    val posoMetaData = new PosoMetaData(classOfT, schema, this);
    private[squeryl] def allFieldsMetaData: Iterable[FieldMetaData] = posoMetaData.fieldsMetaData;
    private val _emptyArray = new Array[Object](0);
    protected val _setPersisted = if (classOf[PersistenceStatus].isAssignableFrom(classOfT))
      ((t: T) => t.asInstanceOf[PersistenceStatus]._isPersisted = true)
    else
      ((t: T) => ());
    private val _posoFactory = FieldMetaData.factory.createPosoFactory(posoMetaData);
    private[squeryl] def _createInstanceOfRowObject = _posoFactory();
    private[squeryl] def give(resultSetMapper: ResultSetMapper, resultSet: ResultSet): T = {
      var o = _callbacks.create;
      if (o.$eq$eq(null))
        o = _createInstanceOfRowObject
      else
        ();
      resultSetMapper.map(o, resultSet);
      val t = o.asInstanceOf[T];
      _setPersisted(t);
      t
    };
    def lookup[K >: _root_.scala.Nothing <: _root_.scala.Any](k: K)(implicit ked: KeyedEntityDef[T, K], dsl: QueryDsl): Option[T] = {
      import dsl._;
      val q = from(this)(((a) => dsl.where(FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(ked.getId(a), k)).select(a)));
      val it = q.iterator;
      if (it.hasNext)
        {
          val ret = Some(it.next);
          it.hasNext;
          ret
        }
      else
        None
    };
    def get[K >: _root_.scala.Nothing <: _root_.scala.Any](k: K)(implicit ked: KeyedEntityDef[T, K], dsl: QueryDsl): T = lookup(k).getOrElse(throw new NoSuchElementException("Found no row with key \'".$plus(k).$plus("\' in ").$plus(name).$plus(".")));
    def allRows(implicit dsl: QueryDsl): Iterable[T] = {
      import dsl._;
      dsl.queryToIterable(from(this)(((a) => select(a))))
    }
  }
}