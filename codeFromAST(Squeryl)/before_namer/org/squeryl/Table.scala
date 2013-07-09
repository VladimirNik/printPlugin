package org.squeryl {
  import dsl.ast._;
  import dsl.{CompositeKey, QueryDsl};
  import internals._;
  import java.sql.Statement;
  import logging.StackMarker;
  import scala.reflect.Manifest;
  import collection.mutable.ArrayBuffer;
  import javax.swing.UIDefaults.LazyValue;
  class Table[T >: _root_.scala.Nothing <: _root_.scala.Any] extends View[T] {
    <paramaccessor> private[this] val n: String = _;
    <paramaccessor> private[this] val c: Class[T] = _;
    <paramaccessor> val schema: Schema = _;
    <paramaccessor> private[this] val _prefix: Option[String] = _;
    <paramaccessor> private[this] val ked: Option[KeyedEntityDef[T, _$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    private[squeryl] def <init>(n: String, c: Class[T], schema: Schema, _prefix: Option[String], ked: Option[KeyedEntityDef[T, _$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, c, schema, _prefix, ked);
      ()
    };
    private def _dbAdapter = Session.currentSession.databaseAdapter;
    def insert(t: T): T = StackMarker.lastSquerylStackFrame({
      val o = _callbacks.beforeInsert(t.asInstanceOf[AnyRef]);
      val sess = Session.currentSession;
      val sw = new StatementWriter(_dbAdapter);
      _dbAdapter.writeInsert(o.asInstanceOf[T], this, sw);
      val st = scala.Tuple2(_dbAdapter.supportsAutoIncrementInColumnDeclaration, posoMetaData.primaryKey) match {
        case scala.Tuple2(true, (a @ (_: Any))) => sess.connection.prepareStatement(sw.statement, Statement.RETURN_GENERATED_KEYS)
        case scala.Tuple2(false, Some(Left((pk @ (_: FieldMetaData))))) => {
          val autoIncPk = new Array[String](1);
          autoIncPk.update(0, pk.columnName);
          sess.connection.prepareStatement(sw.statement, autoIncPk)
        }
        case (a @ (_: Any)) => sess.connection.prepareStatement(sw.statement)
      };
      try {
        val cnt = _dbAdapter.executeUpdateForInsert(sess, sw, st);
        if (cnt.$bang$eq(1))
          throw SquerylSQLException("failed to insert.  Expected 1 row, got ".$plus(cnt))
        else
          ();
        posoMetaData.primaryKey match {
          case Some(Left((pk @ (_: FieldMetaData)))) => if (pk.isAutoIncremented)
            {
              val rs = st.getGeneratedKeys;
              try {
                assert(rs.next, "getGeneratedKeys returned no rows for the auto incremented\n".$plus(" primary key of table \'").$plus(name).$plus("\' JDBC3 feature might not be supported, \n or").$plus(" column might not be defined as auto increment"));
                pk.setFromResultSet(o, rs, 1)
              } finally rs.close
            }
          else
            ()
          case (a @ (_: Any)) => ()
        }
      } finally st.close;
      val r = _callbacks.afterInsert(o).asInstanceOf[T];
      _setPersisted(r);
      r
    });
    def insert(e: Iterable[T]): Unit = _batchedUpdateOrInsert(e, ((t) => posoMetaData.fieldsMetaData.filter(((fmd) => fmd.isAutoIncremented.unary_$bang.$amp$amp(fmd.isInsertable)))), true, false);
    private def _batchedUpdateOrInsert(e: Iterable[T], fmdCallback: _root_.scala.Function1[T, Iterable[FieldMetaData]], isInsert: Boolean, checkOCC: Boolean): Unit = {
      val it = e.iterator;
      if (it.hasNext)
        {
          val e0 = it.next;
          val fmds = fmdCallback(e0);
          val sess = Session.currentSession;
          val dba = _dbAdapter;
          val sw = new StatementWriter(dba);
          val forAfterUpdateOrInsert = new ArrayBuffer[AnyRef]();
          if (isInsert)
            {
              val z = _callbacks.beforeInsert(e0.asInstanceOf[AnyRef]);
              forAfterUpdateOrInsert.append(z);
              dba.writeInsert(z.asInstanceOf[T], this, sw)
            }
          else
            {
              val z = _callbacks.beforeUpdate(e0.asInstanceOf[AnyRef]);
              forAfterUpdateOrInsert.append(z);
              dba.writeUpdate(z.asInstanceOf[T], this, sw, checkOCC)
            };
          if (sess.isLoggingEnabled)
            sess.log("Performing batched ".$plus(if (isInsert)
  "insert"
else
  "update").$plus(" with ").$plus(sw.statement))
          else
            ();
          val st = sess.connection.prepareStatement(sw.statement);
          try {
            dba.fillParamsInto(sw.params, st);
            st.addBatch;
            var updateCount = 1;
            while$1(){
              if (it.hasNext)
                {
                  {
                    val eN0 = it.next.asInstanceOf[AnyRef];
                    val eN = if (isInsert)
                      _callbacks.beforeInsert(eN0)
                    else
                      _callbacks.beforeUpdate(eN0);
                    forAfterUpdateOrInsert.append(eN);
                    var idx = 1;
                    fmds.foreach(((fmd) => {
                      dba.setParamInto(st, FieldStatementParam(eN, fmd), idx);
                      idx.$plus$eq(1)
                    }));
                    st.addBatch;
                    updateCount.$plus$eq(1)
                  };
                  while$1()
                }
              else
                ()
            };
            val execResults = st.executeBatch;
            if (checkOCC)
              execResults.foreach(((b) => if (b.$eq$eq(0))
                {
                  val updateOrInsert = if (isInsert)
                    "insert"
                  else
                    "update";
                  throw new StaleUpdateException("Attemped to ".$plus(updateOrInsert).$plus(" stale object under optimistic concurrency control"))
                }
              else
                ()))
            else
              ()
          } finally st.close;
          forAfterUpdateOrInsert.foreach(((a) => if (isInsert)
            _setPersisted(_callbacks.afterInsert(a).asInstanceOf[T])
          else
            _callbacks.afterUpdate(a)))
        }
      else
        ()
    };
    def forceUpdate(o: T)(implicit ked: KeyedEntityDef[T, _$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = _update(o, false, ked);
    def update(o: T)(implicit ked: KeyedEntityDef[T, _$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Unit = _update(o, true, ked);
    def update(o: Iterable[T])(implicit ked: KeyedEntityDef[T, _$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Unit = _update(o, ked.isOptimistic);
    def forceUpdate(o: Iterable[T])(implicit ked: KeyedEntityDef[T, _$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Unit = _update(o, ked.isOptimistic);
    private def _update(o: T, checkOCC: Boolean, ked: KeyedEntityDef[T, _$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      val dba = Session.currentSession.databaseAdapter;
      val sw = new StatementWriter(dba);
      val o0 = _callbacks.beforeUpdate(o.asInstanceOf[AnyRef]).asInstanceOf[T];
      dba.writeUpdate(o0, this, sw, checkOCC);
      val cnt = dba.executeUpdateAndCloseStatement(Session.currentSession, sw);
      if (cnt.$bang$eq(1))
        if (checkOCC.$amp$amp(posoMetaData.isOptimistic))
          {
            val version = posoMetaData.optimisticCounter.get.getNativeJdbcValue(o.asInstanceOf[AnyRef]);
            throw new StaleUpdateException("Object ".$plus(prefixedName).$plus("(id=").$plus(ked.getId(o)).$plus(", occVersionNumber=").$plus(version).$plus(") has become stale, it cannot be updated under optimistic concurrency control"))
          }
        else
          throw SquerylSQLException("failed to update.  Expected 1 row, got ".$plus(cnt))
      else
        ();
      _callbacks.afterUpdate(o0.asInstanceOf[AnyRef])
    };
    private def _update(e: Iterable[T], checkOCC: Boolean): Unit = {
      def buildFmds(t: T): Iterable[FieldMetaData] = {
        val pkList = posoMetaData.primaryKey.getOrElse(org.squeryl.internals.Utils.throwError("method was called with ".$plus(posoMetaData.clasz.getName).$plus(" that is not a KeyedEntity[]"))).fold(((pkMd) => List(pkMd)), ((pkGetter) => {
          var fields: Option[List[FieldMetaData]] = None;
          Utils.createQuery4WhereClause(this, ((t0: T) => {
            val ck = pkGetter.invoke(t0).asInstanceOf[CompositeKey];
            fields = Some(ck._fields.toList);
            new EqualityExpression(InternalFieldMapper.intTEF.createConstant(1), InternalFieldMapper.intTEF.createConstant(1))
          }));
          fields.getOrElse(internals.Utils.throwError("No PK fields found"))
        }));
        List(posoMetaData.fieldsMetaData.filter(((fmd) => fmd.isIdFieldOfKeyedEntity.unary_$bang.$amp$amp(fmd.isOptimisticCounter.unary_$bang).$amp$amp(fmd.isUpdatable))).toList, pkList, posoMetaData.optimisticCounter.toList).flatten
      };
      _batchedUpdateOrInsert(e, (buildFmds: (() => <empty>)), false, checkOCC)
    };
    def update(s: _root_.scala.Function1[T, UpdateStatement]): Int = {
      val vxn = new ViewExpressionNode(this);
      vxn.sample = posoMetaData.createSample(FieldReferenceLinker.createCallBack(vxn));
      val us = s(vxn.sample);
      vxn.parent = Some(us);
      var idGen = 0;
      us.visitDescendants(((node, parent, i) => {
        if (node.parent.$eq$eq(None))
          node.parent = parent
        else
          ();
        if (node.isInstanceOf[UniqueIdInAliaseRequired])
          {
            val nxn = node.asInstanceOf[UniqueIdInAliaseRequired];
            nxn.uniqueId = Some(idGen);
            idGen.$plus$eq(1)
          }
        else
          ()
      }));
      vxn.uniqueId = Some(idGen);
      val dba = _dbAdapter;
      val sw = new StatementWriter(dba);
      dba.writeUpdate(this, us, sw);
      dba.executeUpdateAndCloseStatement(Session.currentSession, sw)
    };
    def delete(q: Query[T]): Int = {
      val queryAst = q.ast.asInstanceOf[QueryExpressionElements];
      queryAst.inhibitAliasOnSelectElementReference = true;
      val sw = new StatementWriter(_dbAdapter);
      _dbAdapter.writeDelete(this, queryAst.whereClause, sw);
      _dbAdapter.executeUpdateAndCloseStatement(Session.currentSession, sw)
    };
    def deleteWhere(whereClause: _root_.scala.Function1[T, LogicalBoolean])(implicit dsl: QueryDsl): Int = delete(dsl.from(this)(((t) => dsl.where(whereClause(t)).select(t))));
    def delete[K >: _root_.scala.Nothing <: _root_.scala.Any](k: K)(implicit ked: KeyedEntityDef[T, K], dsl: QueryDsl): Boolean = {
      import dsl._;
      val q = from(this)(((a) => dsl.where(FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(ked.getId(a), k)).select(a)));
      lazy val z = q.headOption;
      if (_callbacks.hasBeforeDelete)
        z.map(((x) => _callbacks.beforeDelete(x.asInstanceOf[AnyRef])))
      else
        ();
      val deleteCount = this.delete(q);
      if (_callbacks.hasAfterDelete)
        z.map(((x) => _callbacks.afterDelete(x.asInstanceOf[AnyRef])))
      else
        ();
      if (Session.currentSessionOption.map(((ses) => ses.databaseAdapter.verifyDeleteByPK)).getOrElse(true))
        assert(deleteCount.$less$eq(1), "Query :\n".$plus(q.dumpAst).$plus("\nshould have deleted at most 1 row but has deleted ").$plus(deleteCount))
      else
        ();
      deleteCount.$greater(0)
    };
    def insertOrUpdate(o: T)(implicit ked: KeyedEntityDef[T, _$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }): T = {
      if (ked.isPersisted(o))
        update(o)
      else
        insert(o);
      o
    }
  }
}