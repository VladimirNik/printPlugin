package org.squeryl {
  import dsl.ast._;
  import dsl.{CompositeKey, QueryDsl};
  import internals._;
  import java.sql.Statement;
  import logging.StackMarker;
  import scala.reflect.Manifest;
  import scala.collection.mutable.ArrayBuffer;
  import javax.swing.UIDefaults.LazyValue;
  class Table[T >: Nothing <: Any] extends org.squeryl.View[T] {
    <paramaccessor> private[this] val n: String = _;
    <paramaccessor> private[this] val c: Class[T] = _;
    <paramaccessor> private[this] val schema: org.squeryl.Schema = _;
    <stable> <accessor> <paramaccessor> def schema: org.squeryl.Schema = Table.this.schema;
    <paramaccessor> private[this] val _prefix: Option[String] = _;
    <paramaccessor> private[this] val ked: Option[org.squeryl.KeyedEntityDef[T, _]] = _;
    private[squeryl] def <init>(n: String, c: Class[T], schema: org.squeryl.Schema, _prefix: Option[String], ked: Option[org.squeryl.KeyedEntityDef[T, _]]): org.squeryl.Table[T] = {
      Table.super.<init>(n, c, schema, _prefix, ked);
      ()
    };
    private def _dbAdapter: org.squeryl.internals.DatabaseAdapter = Session.currentSession.databaseAdapter;
    def insert(t: T): T = logging.StackMarker.lastSquerylStackFrame[T]({
      val o: AnyRef = Table.this._callbacks.beforeInsert(t.asInstanceOf[AnyRef]);
      val sess: org.squeryl.AbstractSession = Session.currentSession;
      val sw: org.squeryl.internals.StatementWriter = new internals.StatementWriter(Table.this._dbAdapter);
      Table.this._dbAdapter.writeInsert[T](o.asInstanceOf[T], this, sw);
      val st: java.sql.PreparedStatement = scala.Tuple2.apply[Boolean, Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]]](Table.this._dbAdapter.supportsAutoIncrementInColumnDeclaration, Table.this.posoMetaData.primaryKey) match {
        case (_1: Boolean, _2: Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]])(Boolean, Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]])(true, (a @ (_: Any))) => sess.connection.prepareStatement(sw.statement, 1)
        case (_1: Boolean, _2: Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]])(Boolean, Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]])(false, (x: Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method])Some[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]]((a: org.squeryl.internals.FieldMetaData)scala.util.Left[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]((pk @ (_: org.squeryl.internals.FieldMetaData))))) => {
          val autoIncPk: Array[String] = new Array[String](1);
          autoIncPk.update(0, pk.columnName);
          sess.connection.prepareStatement(sw.statement, autoIncPk)
        }
        case (a @ (_: Any)) => sess.connection.prepareStatement(sw.statement)
      };
      try {
        val cnt: Int = Table.this._dbAdapter.executeUpdateForInsert(sess, sw, st);
        if (cnt.!=(1))
          throw SquerylSQLException.apply("failed to insert.  Expected 1 row, got ".+(cnt))
        else
          ();
        Table.this.posoMetaData.primaryKey match {
          case (x: Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method])Some[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]]((a: org.squeryl.internals.FieldMetaData)scala.util.Left[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]((pk @ (_: org.squeryl.internals.FieldMetaData)))) => if (pk.isAutoIncremented)
            {
              val rs: java.sql.ResultSet = st.getGeneratedKeys();
              try {
                scala.this.Predef.assert(rs.next(), "getGeneratedKeys returned no rows for the auto incremented\n primary key of table \'".+(Table.this.name).+("\' JDBC3 feature might not be supported, \n or").+(" column might not be defined as auto increment"));
                pk.setFromResultSet(o, rs, 1)
              } finally rs.close()
            }
          else
            ()
          case (a @ (_: Any)) => ()
        }
      } finally st.close();
      val r: T = Table.this._callbacks.afterInsert(o).asInstanceOf[T];
      Table.this._setPersisted.apply(r);
      r
    });
    def insert(e: Iterable[T]): Unit = Table.this._batchedUpdateOrInsert(e, ((t: T) => Table.this.posoMetaData.fieldsMetaData.filter(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isAutoIncremented.unary_!.&&(fmd.isInsertable)))), true, false);
    private def _batchedUpdateOrInsert(e: Iterable[T], fmdCallback: T => Iterable[org.squeryl.internals.FieldMetaData], isInsert: Boolean, checkOCC: Boolean): Unit = {
      val it: Iterator[T] = e.iterator;
      if (it.hasNext)
        {
          val e0: T = it.next();
          val fmds: Iterable[org.squeryl.internals.FieldMetaData] = fmdCallback.apply(e0);
          val sess: org.squeryl.AbstractSession = Session.currentSession;
          val dba: org.squeryl.internals.DatabaseAdapter = Table.this._dbAdapter;
          val sw: org.squeryl.internals.StatementWriter = new internals.StatementWriter(dba);
          val forAfterUpdateOrInsert: scala.collection.mutable.ArrayBuffer[AnyRef] = new scala.collection.mutable.ArrayBuffer[AnyRef]();
          if (isInsert)
            {
              val z: AnyRef = Table.this._callbacks.beforeInsert(e0.asInstanceOf[AnyRef]);
              forAfterUpdateOrInsert.append(z);
              dba.writeInsert[T](z.asInstanceOf[T], this, sw)
            }
          else
            {
              val z: AnyRef = Table.this._callbacks.beforeUpdate(e0.asInstanceOf[AnyRef]);
              forAfterUpdateOrInsert.append(z);
              dba.writeUpdate[T](z.asInstanceOf[T], this, sw, checkOCC)
            };
          if (sess.isLoggingEnabled)
            sess.log("Performing batched ".+(if (isInsert)
  "insert"
else
  "update").+(" with ").+(sw.statement))
          else
            ();
          val st: java.sql.PreparedStatement = sess.connection.prepareStatement(sw.statement);
          try {
            dba.fillParamsInto(sw.params, st);
            st.addBatch();
            var updateCount: Int = 1;
            while$1(){
              if (it.hasNext)
                {
                  {
                    val eN0: AnyRef = it.next().asInstanceOf[AnyRef];
                    val eN: AnyRef = if (isInsert)
                      Table.this._callbacks.beforeInsert(eN0)
                    else
                      Table.this._callbacks.beforeUpdate(eN0);
                    forAfterUpdateOrInsert.append(eN);
                    var idx: Int = 1;
                    fmds.foreach[Unit](((fmd: org.squeryl.internals.FieldMetaData) => {
                      dba.setParamInto(st, internals.FieldStatementParam.apply(eN, fmd), idx);
                      idx = idx.+(1)
                    }));
                    st.addBatch();
                    updateCount = updateCount.+(1)
                  };
                  while$1()
                }
              else
                ()
            };
            val execResults: Array[Int] = st.executeBatch();
            if (checkOCC)
              scala.this.Predef.intArrayOps(execResults).foreach[Unit](((b: Int) => if (b.==(0))
                {
                  val updateOrInsert: String = if (isInsert)
                    "insert"
                  else
                    "update";
                  throw new StaleUpdateException("Attemped to ".+(updateOrInsert).+(" stale object under optimistic concurrency control"))
                }
              else
                ()))
            else
              ()
          } finally st.close();
          forAfterUpdateOrInsert.foreach[Any](((a: AnyRef) => if (isInsert)
            Table.this._setPersisted.apply(Table.this._callbacks.afterInsert(a).asInstanceOf[T])
          else
            Table.this._callbacks.afterUpdate(a)))
        }
      else
        ()
    };
    def forceUpdate(o: T)(implicit ked: org.squeryl.KeyedEntityDef[T, _]): AnyRef = Table.this._update(o, false, ked);
    def update(o: T)(implicit ked: org.squeryl.KeyedEntityDef[T, _]): Unit = {
      Table.this._update(o, true, ked);
      ()
    };
    def update(o: Iterable[T])(implicit ked: org.squeryl.KeyedEntityDef[T, _]): Unit = Table.this._update(o, ked.isOptimistic);
    def forceUpdate(o: Iterable[T])(implicit ked: org.squeryl.KeyedEntityDef[T, _]): Unit = Table.this._update(o, ked.isOptimistic);
    private def _update(o: T, checkOCC: Boolean, ked: org.squeryl.KeyedEntityDef[T, _]): AnyRef = {
      val dba: org.squeryl.internals.DatabaseAdapter = Session.currentSession.databaseAdapter;
      val sw: org.squeryl.internals.StatementWriter = new internals.StatementWriter(dba);
      val o0: T = Table.this._callbacks.beforeUpdate(o.asInstanceOf[AnyRef]).asInstanceOf[T];
      dba.writeUpdate[T](o0, this, sw, checkOCC);
      val cnt: Int = dba.executeUpdateAndCloseStatement(Session.currentSession, sw);
      if (cnt.!=(1))
        if (checkOCC.&&(Table.this.posoMetaData.isOptimistic))
          {
            val version: AnyRef = Table.this.posoMetaData.optimisticCounter.get.getNativeJdbcValue(o.asInstanceOf[AnyRef]);
            throw new StaleUpdateException("Object ".+(Table.this.prefixedName).+("(id=").+(ked.getId(o)).+(", occVersionNumber=").+(version).+(") has become stale, it cannot be updated under optimistic concurrency control"))
          }
        else
          throw SquerylSQLException.apply("failed to update.  Expected 1 row, got ".+(cnt))
      else
        ();
      Table.this._callbacks.afterUpdate(o0.asInstanceOf[AnyRef])
    };
    private def _update(e: Iterable[T], checkOCC: Boolean): Unit = {
      def buildFmds(t: T): Iterable[org.squeryl.internals.FieldMetaData] = {
        val pkList: List[org.squeryl.internals.FieldMetaData] = Table.this.posoMetaData.primaryKey.getOrElse[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]](org.squeryl.internals.Utils.throwError("method was called with ".+(Table.this.posoMetaData.clasz.getName()).+(" that is not a KeyedEntity[]"))).fold[List[org.squeryl.internals.FieldMetaData]](((pkMd: org.squeryl.internals.FieldMetaData) => immutable.this.List.apply[org.squeryl.internals.FieldMetaData](pkMd)), ((pkGetter: java.lang.reflect.Method) => {
          var fields: Option[List[org.squeryl.internals.FieldMetaData]] = scala.None;
          internals.Utils.createQuery4WhereClause[T](this, ((t0: T) => {
            val ck: org.squeryl.dsl.CompositeKey = pkGetter.invoke(t0).asInstanceOf[org.squeryl.dsl.CompositeKey];
            fields = scala.Some.apply[List[org.squeryl.internals.FieldMetaData]](ck._fields.toList);
            new dsl.ast.EqualityExpression(InternalFieldMapper.intTEF.createConstant(1), InternalFieldMapper.intTEF.createConstant(1))
          }));
          fields.getOrElse[List[org.squeryl.internals.FieldMetaData]](internals.Utils.throwError("No PK fields found"))
        }));
        immutable.this.List.apply[List[org.squeryl.internals.FieldMetaData]](Table.this.posoMetaData.fieldsMetaData.filter(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isIdFieldOfKeyedEntity.unary_!.&&(fmd.isOptimisticCounter.unary_!).&&(fmd.isUpdatable))).toList, pkList, Table.this.posoMetaData.optimisticCounter.toList).flatten[org.squeryl.internals.FieldMetaData](scala.this.Predef.conforms[List[org.squeryl.internals.FieldMetaData]])
      };
      Table.this._batchedUpdateOrInsert(e, {
        ((t: T) => buildFmds(t))
      }, false, checkOCC)
    };
    def update(s: T => org.squeryl.dsl.ast.UpdateStatement): Int = {
      val vxn: org.squeryl.dsl.ast.ViewExpressionNode[T] = new org.squeryl.dsl.ast.ViewExpressionNode[T](this);
      vxn.sample_=(Table.this.posoMetaData.createSample(internals.FieldReferenceLinker.createCallBack(vxn)));
      val us: org.squeryl.dsl.ast.UpdateStatement = s.apply(vxn.sample);
      vxn.parent_=(scala.Some.apply[org.squeryl.dsl.ast.UpdateStatement](us));
      var idGen: Int = 0;
      us.visitDescendants(((node: org.squeryl.dsl.ast.ExpressionNode, parent: Option[org.squeryl.dsl.ast.ExpressionNode], i: Int) => {
        if (node.parent.==(scala.None))
          node.parent_=(parent)
        else
          ();
        if (node.isInstanceOf[org.squeryl.dsl.ast.UniqueIdInAliaseRequired])
          {
            val nxn: org.squeryl.dsl.ast.UniqueIdInAliaseRequired = node.asInstanceOf[org.squeryl.dsl.ast.UniqueIdInAliaseRequired];
            nxn.uniqueId_=(scala.Some.apply[Int](idGen));
            idGen = idGen.+(1)
          }
        else
          ()
      }));
      vxn.uniqueId_=(scala.Some.apply[Int](idGen));
      val dba: org.squeryl.internals.DatabaseAdapter = Table.this._dbAdapter;
      val sw: org.squeryl.internals.StatementWriter = new internals.StatementWriter(dba);
      dba.writeUpdate(this, us, sw);
      dba.executeUpdateAndCloseStatement(Session.currentSession, sw)
    };
    def delete(q: org.squeryl.Query[T]): Int = {
      val queryAst: org.squeryl.dsl.ast.QueryExpressionElements = q.ast.asInstanceOf[org.squeryl.dsl.ast.QueryExpressionElements];
      queryAst.inhibitAliasOnSelectElementReference_=(true);
      val sw: org.squeryl.internals.StatementWriter = new internals.StatementWriter(Table.this._dbAdapter);
      Table.this._dbAdapter.writeDelete[T](this, queryAst.whereClause, sw);
      Table.this._dbAdapter.executeUpdateAndCloseStatement(Session.currentSession, sw)
    };
    def deleteWhere(whereClause: T => org.squeryl.dsl.ast.LogicalBoolean)(implicit dsl: org.squeryl.dsl.QueryDsl): Int = Table.this.delete(dsl.from[T, T](this)(((t: T) => dsl.where(whereClause.apply(t)).select[T](t))));
    def delete[K >: Nothing <: Any](k: K)(implicit ked: org.squeryl.KeyedEntityDef[T,K], dsl: org.squeryl.dsl.QueryDsl): Boolean = {
      import dsl._;
      val q: org.squeryl.Query[T] = dsl.from[T, T](this)(((a: T) => dsl.where(internals.FieldReferenceLinker.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(ked.getId(a), k)).select[T](a)));
      lazy var z$lzy: Option[T] = q.headOption;
      if (Table.this._callbacks.hasBeforeDelete)
        z.map[AnyRef](((x: T) => Table.this._callbacks.beforeDelete(x.asInstanceOf[AnyRef])))
      else
        ();
      val deleteCount: Int = this.delete(q);
      if (Table.this._callbacks.hasAfterDelete)
        z.map[AnyRef](((x: T) => Table.this._callbacks.afterDelete(x.asInstanceOf[AnyRef])))
      else
        ();
      if (Session.currentSessionOption.map[Boolean](((ses: org.squeryl.AbstractSession) => ses.databaseAdapter.verifyDeleteByPK)).getOrElse[Boolean](true))
        scala.this.Predef.assert(deleteCount.<=(1), "Query :\n".+(q.dumpAst).+("\nshould have deleted at most 1 row but has deleted ").+(deleteCount))
      else
        ();
      deleteCount.>(0)
    };
    def insertOrUpdate(o: T)(implicit ked: org.squeryl.KeyedEntityDef[T, _]): T = {
      if (ked.isPersisted(o))
        Table.this.update(o)(ked)
      else
        Table.this.insert(o);
      o
    }
  }
}