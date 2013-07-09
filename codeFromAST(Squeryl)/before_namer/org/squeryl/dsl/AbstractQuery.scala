package org.squeryl.dsl {
  import ast._;
  import internal.{InnerJoinedQueryable, OuterJoinedQueryable};
  import java.sql.ResultSet;
  import org.squeryl.internals._;
  import org.squeryl.{View, Queryable, Session, Query};
  import collection.mutable.ArrayBuffer;
  import org.squeryl.logging._;
  import java.io.Closeable;
  abstract class AbstractQuery[R >: _root_.scala.Nothing <: _root_.scala.Any] extends Query[R] {
    <paramaccessor> val isRoot: Boolean = _;
    def <init>(isRoot: Boolean) = {
      super.<init>();
      ()
    };
    private[squeryl] var selectDistinct = false;
    private[squeryl] var isForUpdate = false;
    private[squeryl] var page: Option[scala.Tuple2[Int, Int]] = None;
    val resultSetMapper = new ResultSetMapper();
    val name = "query";
    def give(rsm: ResultSetMapper, rs: ResultSet): R = {
      rsm.pushYieldedValues(rs);
      val r = invokeYield(rsm, rs);
      r
    };
    val definitionSite: Option[StackTraceElement] = if (isRoot.unary_$bang)
      None
    else
      Some(_deduceDefinitionSite);
    private def _deduceDefinitionSite: StackTraceElement = {
      val st = Thread.currentThread.getStackTrace;
      var i = 1;
      while$1(){
        if (i.$less(st.length))
          {
            {
              val e = st(i);
              val cn = e.getClassName;
              if (cn.startsWith("org.squeryl.").$amp$amp(cn.startsWith("org.squeryl.tests.").unary_$bang).$bar$bar(cn.startsWith("scala.")))
                i = i.$plus(1)
              else
                return e
            };
            while$1()
          }
        else
          ()
      };
      new StackTraceElement("unknown", "unknown", "unknown", -1)
    };
    protected def buildAst(qy: QueryYield[R], subQueryables: _root_.scala.<repeated>[SubQueryable[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      val subQueries = new ArrayBuffer[QueryableExpressionNode]();
      val views = new ArrayBuffer[ViewExpressionNode[_$2] forSome { 
        <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
      }]();
      if (qy.joinExpressions.$bang$eq(Nil))
        {
          val sqIterator = subQueryables.iterator;
          val joinExprsIterator = qy.joinExpressions.iterator;
          sqIterator.next;
          while$2(){
            if (sqIterator.hasNext)
              {
                {
                  val nthQueryable = sqIterator.next;
                  val nthJoinExpr = joinExprsIterator.next;
                  nthQueryable.node.joinExpression = Some(nthJoinExpr())
                };
                while$2()
              }
            else
              ()
          }
        }
      else
        ();
      subQueryables.foreach(((sq) => if (sq.isQuery.unary_$bang)
        views.append(sq.node.asInstanceOf[ViewExpressionNode[_$3] forSome { 
          <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
        }])
      else
        ()));
      subQueryables.foreach(((sq) => if (sq.isQuery)
        {
          val z = sq.node.asInstanceOf[QueryExpressionNode[_$4] forSome { 
            <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
          }];
          if (z.isUseableAsSubquery.unary_$bang)
            org.squeryl.internals.Utils.throwError("Sub query returns a primitive type or a Tuple of primitive type, and therefore is not useable as a subquery in a from or join clause, see \nhttp://squeryl.org/limitations.html")
          else
            ();
          subQueries.append(z)
        }
      else
        ()));
      val qen = new QueryExpressionNode[R](this, qy, subQueries, views);
      <synthetic> private[this] val x$1 = qy.invokeYieldForAst(qen, resultSetMapper): @scala.unchecked match {
        case scala.Tuple2((sl @ _), (d @ _)) => scala.Tuple2(sl, d)
      };
      val sl = x$1._1;
      val d = x$1._2;
      qen.setOutExpressionNodesAndSample(sl, d);
      qen
    };
    def ast: QueryExpressionNode[R];
    def copy(asRoot: Boolean) = {
      val c = createCopy(asRoot);
      c.selectDistinct = selectDistinct;
      c
    };
    def createCopy(asRoot: Boolean): AbstractQuery[R];
    def dumpAst = ast.dumpAst;
    def statement: String = _genStatement(true);
    private def _genStatement(forDisplay: Boolean) = {
      val sw = new StatementWriter(forDisplay, Session.currentSession.databaseAdapter);
      ast.write(sw);
      sw.statement
    };
    def distinct = {
      val c = copy(true);
      c.selectDistinct = true;
      c
    };
    def page(offset: Int, pageLength: Int): Query[R] = {
      val c = copy(true);
      c.page = Some(scala.Tuple2(offset, pageLength));
      c
    };
    def forUpdate = {
      val c = copy(true);
      c.isForUpdate = true;
      c
    };
    private def _dbAdapter = Session.currentSession.databaseAdapter;
    def iterator = {
      final class $anon extends Iterator[R] with Closeable {
        def <init>() = {
          super.<init>();
          ()
        };
        val sw = new StatementWriter(false, _dbAdapter);
        ast.write(sw);
        val s = Session.currentSession;
        val beforeQueryExecute = System.currentTimeMillis;
        <synthetic> private[this] val x$2 = _dbAdapter.executeQuery(s, sw): @scala.unchecked match {
          case scala.Tuple2((rs @ _), (stmt @ _)) => scala.Tuple2(rs, stmt)
        };
        val rs = x$2._1;
        val stmt = x$2._2;
        lazy val statEx = new StatementInvocationEvent(definitionSite.get, beforeQueryExecute, System.currentTimeMillis, -1, sw.statement);
        if (s.statisticsListener.$bang$eq(None))
          s.statisticsListener.get.queryExecuted(statEx)
        else
          ();
        s._addStatement(stmt);
        s._addResultSet(rs);
        var _nextCalled = false;
        var _hasNext = false;
        var rowCount = 0;
        def close: scala.Unit = {
          stmt.close;
          rs.close
        };
        def _next = {
          _hasNext = rs.next;
          if (_hasNext.unary_$bang)
            {
              Utils.close(rs);
              stmt.close;
              if (s.statisticsListener.$bang$eq(None))
                s.statisticsListener.get.resultSetIterationEnded(statEx.uuid, System.currentTimeMillis, rowCount, true)
              else
                ()
            }
          else
            ();
          rowCount = rowCount.$plus(1);
          _nextCalled = true
        };
        def hasNext = {
          if (_nextCalled.unary_$bang)
            _next
          else
            ();
          _hasNext
        };
        def next: R = {
          if (_nextCalled.unary_$bang)
            _next
          else
            ();
          if (_hasNext.unary_$bang)
            throw new NoSuchElementException("next called with no rows available")
          else
            ();
          _nextCalled = false;
          if (s.isLoggingEnabled)
            s.log(ResultSetUtils.dumpRow(rs))
          else
            ();
          give(resultSetMapper, rs)
        }
      };
      new $anon()
    };
    override def toString = dumpAst.$plus("\n").$plus(_genStatement(true));
    protected def createSubQueryable[U >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[U]): SubQueryable[U] = if (q.isInstanceOf[View[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }])
      {
        val v = q.asInstanceOf[View[U]];
        val vxn = new ViewExpressionNode(v);
        vxn.sample = v.posoMetaData.createSample(FieldReferenceLinker.createCallBack(vxn));
        new SubQueryable(v, vxn.sample, vxn.resultSetMapper, false, vxn)
      }
    else
      if (q.isInstanceOf[OptionalQueryable[_$6] forSome { 
        <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
      }])
        {
          val oqr = q.asInstanceOf[OptionalQueryable[U]];
          val sq = createSubQueryable[U](oqr.queryable);
          sq.node.inhibited = oqr.inhibited;
          val oqCopy = new OptionalQueryable(sq.queryable);
          oqCopy.inhibited = oqr.inhibited;
          new SubQueryable(oqCopy.asInstanceOf[Queryable[U]], Some(sq.sample).asInstanceOf[U], sq.resultSetMapper, sq.isQuery, sq.node)
        }
      else
        if (q.isInstanceOf[OuterJoinedQueryable[_$7] forSome { 
          <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
        }])
          {
            val ojq = q.asInstanceOf[OuterJoinedQueryable[U]];
            val sq = createSubQueryable[U](ojq.queryable);
            sq.node.joinKind = Some(scala.Tuple2(ojq.leftRightOrFull, "outer"));
            sq.node.inhibited = ojq.inhibited;
            new SubQueryable(sq.queryable, Some(sq.sample).asInstanceOf[U], sq.resultSetMapper, sq.isQuery, sq.node)
          }
        else
          if (q.isInstanceOf[InnerJoinedQueryable[_$8] forSome { 
            <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
          }])
            {
              val ijq = q.asInstanceOf[InnerJoinedQueryable[U]];
              val sq = createSubQueryable[U](ijq.queryable);
              sq.node.joinKind = Some(scala.Tuple2(ijq.leftRightOrFull, "inner"));
              new SubQueryable(sq.queryable, sq.sample, sq.resultSetMapper, sq.isQuery, sq.node)
            }
          else
            if (q.isInstanceOf[DelegateQuery[_$9] forSome { 
              <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
            }])
              createSubQueryable(q.asInstanceOf[DelegateQuery[U]].q)
            else
              {
                val qr = q.asInstanceOf[AbstractQuery[U]];
                val copy = qr.copy(false);
                new SubQueryable(copy, copy.ast.sample.asInstanceOf[U], copy.resultSetMapper, true, copy.ast)
              };
    protected class SubQueryable[U >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
      <paramaccessor> val queryable: Queryable[U] = _;
      <paramaccessor> val sample: U = _;
      <paramaccessor> val resultSetMapper: ResultSetMapper = _;
      <paramaccessor> val isQuery: Boolean = _;
      <paramaccessor> val node: QueryableExpressionNode = _;
      def <init>(queryable: Queryable[U], sample: U, resultSetMapper: ResultSetMapper, isQuery: Boolean, node: QueryableExpressionNode) = {
        super.<init>();
        ()
      };
      def give(rs: ResultSet): U = if (node.joinKind.$bang$eq(None))
        if (node.isOuterJoined)
          {
            val isNoneInOuterJoin = isQuery.unary_$bang.$amp$amp(resultSetMapper.isNoneInOuterJoin(rs));
            if (isNoneInOuterJoin)
              None.asInstanceOf[U]
            else
              Some(queryable.give(resultSetMapper, rs)).asInstanceOf[U]
          }
        else
          queryable.give(resultSetMapper, rs)
      else
        if (node.isRightJoined.$amp$amp(resultSetMapper.isNoneInOuterJoin(rs)))
          sample
        else
          queryable.give(resultSetMapper, rs)
    }
  }
}