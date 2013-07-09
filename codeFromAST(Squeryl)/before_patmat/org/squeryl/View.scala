package org.squeryl {
  import dsl.QueryDsl;
  import internals._;
  import java.sql.ResultSet;
  class View[T >: Nothing <: Any] extends Object with org.squeryl.Queryable[T] {
    <paramaccessor> private[this] val _name: String = _;
    <paramaccessor> private[this] val classOfT: Class[T] = _;
    <stable> <accessor> <paramaccessor> private[squeryl] def classOfT: Class[T] = View.this.classOfT;
    <paramaccessor> private[this] val schema: org.squeryl.Schema = _;
    <paramaccessor> private[this] val _prefix: Option[String] = _;
    <paramaccessor> private[this] val ked: Option[org.squeryl.KeyedEntityDef[T, _]] = _;
    <stable> <accessor> <paramaccessor> def ked: Option[org.squeryl.KeyedEntityDef[T, _]] = View.this.ked;
    private[squeryl] def <init>(_name: String, classOfT: Class[T], schema: org.squeryl.Schema, _prefix: Option[String], ked: Option[org.squeryl.KeyedEntityDef[T, _]]): org.squeryl.View[T] = {
      View.super.<init>();
      ()
    };
    lazy private[this] var _callbacks: org.squeryl.internals.PosoLifecycleEventListener = View.this.schema._callbacks.get(this).getOrElse[org.squeryl.internals.PosoLifecycleEventListener](internals.NoOpPosoLifecycleEventListener);
    def name: String = View.this.schema.tableNameFromClassName(View.this._name);
    def prefix: Option[String] = if (View.this._prefix.!=(scala.None))
      View.this._prefix
    else
      View.this.schema.name;
    def prefixedName: String = if (View.this.prefix.!=(scala.None))
      View.this.prefix.get.+(".").+(View.this.name)
    else
      View.this.name;
    def prefixedPrefixedName(s: String): String = if (View.this.prefix.!=(scala.None))
      View.this.prefix.get.+(".").+(s).+(View.this.name)
    else
      s.+(View.this.name);
    private[squeryl] def findFieldMetaDataForProperty(name: String): Option[org.squeryl.internals.FieldMetaData] = View.this.posoMetaData.findFieldMetaDataForProperty(name);
    private[this] val posoMetaData: org.squeryl.internals.PosoMetaData[T] = new org.squeryl.internals.PosoMetaData[T](View.this.classOfT, View.this.schema, this);
    <stable> <accessor> def posoMetaData: org.squeryl.internals.PosoMetaData[T] = View.this.posoMetaData;
    private[squeryl] def allFieldsMetaData: Iterable[org.squeryl.internals.FieldMetaData] = View.this.posoMetaData.fieldsMetaData;
    private[this] val _emptyArray: Array[Object] = new Array[Object](0);
    <stable> <accessor> private def _emptyArray: Array[Object] = View.this._emptyArray;
    private[this] val _setPersisted: T => Unit = if (classOf[org.squeryl.PersistenceStatus].isAssignableFrom(View.this.classOfT))
      ((t: T) => t.asInstanceOf[org.squeryl.PersistenceStatus]._isPersisted_=(true))
    else
      ((t: T) => ());
    <stable> <accessor> protected def _setPersisted: T => Unit = View.this._setPersisted;
    private[this] val _posoFactory: () => AnyRef = internals.FieldMetaData.factory.createPosoFactory(View.this.posoMetaData);
    <stable> <accessor> private def _posoFactory: () => AnyRef = View.this._posoFactory;
    private[squeryl] def _createInstanceOfRowObject: AnyRef = View.this._posoFactory.apply();
    private[squeryl] def give(resultSetMapper: org.squeryl.internals.ResultSetMapper, resultSet: java.sql.ResultSet): T = {
      var o: AnyRef = View.this._callbacks.create;
      if (o.==(null))
        o = View.this._createInstanceOfRowObject
      else
        ();
      resultSetMapper.map(o, resultSet);
      val t: T = o.asInstanceOf[T];
      View.this._setPersisted.apply(t);
      t
    };
    def lookup[K >: Nothing <: Any](k: K)(implicit ked: org.squeryl.KeyedEntityDef[T,K], dsl: org.squeryl.dsl.QueryDsl): Option[T] = {
      import dsl._;
      val q: org.squeryl.Query[T] = dsl.from[T, T](this)(((a: T) => dsl.where(internals.FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(ked.getId(a), k)).select[T](a)));
      val it: Iterator[T] = q.iterator;
      if (it.hasNext)
        {
          val ret: Some[T] = scala.Some.apply[T](it.next());
          it.hasNext;
          ret
        }
      else
        scala.None
    };
    def get[K >: Nothing <: Any](k: K)(implicit ked: org.squeryl.KeyedEntityDef[T,K], dsl: org.squeryl.dsl.QueryDsl): T = View.this.lookup[K](k)(ked, dsl).getOrElse[T](throw new scala.`package`.NoSuchElementException("Found no row with key \'".+(k).+("\' in ").+(View.this.name).+(".")));
    def allRows(implicit dsl: org.squeryl.dsl.QueryDsl): Iterable[T] = {
      import dsl._;
      dsl.queryToIterable[T](dsl.from[T, T](this)(((a: T) => dsl.select[T](a))))
    }
  }
}