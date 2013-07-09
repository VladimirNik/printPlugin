package org.squeryl.dsl {
  import ast._;
  import internal.{InnerJoinedQueryable, OuterJoinedQueryable};
  import java.sql.ResultSet;
  import org.squeryl.internals._;
  import org.squeryl.{View, Queryable, Session, Query};
  import scala.collection.mutable.ArrayBuffer;
  import org.squeryl.logging._;
  import java.io.Closeable;
  abstract class AbstractQuery[R >: Nothing <: Any] extends Object with org.squeryl.Query[R] {
    <paramaccessor> private[this] val isRoot: Boolean = _;
    <stable> <accessor> <paramaccessor> def isRoot: Boolean = AbstractQuery.this.isRoot;
    def <init>(isRoot: Boolean): org.squeryl.dsl.AbstractQuery[R] = {
      AbstractQuery.super.<init>();
      ()
    };
    private[this] var selectDistinct: Boolean = false;
    <accessor> private[squeryl] def selectDistinct: Boolean = AbstractQuery.this.selectDistinct;
    <accessor> private[squeryl] def selectDistinct_=(x$1: Boolean): Unit = AbstractQuery.this.selectDistinct = x$1;
    private[this] var isForUpdate: Boolean = false;
    <accessor> private[squeryl] def isForUpdate: Boolean = AbstractQuery.this.isForUpdate;
    <accessor> private[squeryl] def isForUpdate_=(x$1: Boolean): Unit = AbstractQuery.this.isForUpdate = x$1;
    private[this] var page: Option[(Int, Int)] = scala.None;
    <accessor> private[squeryl] def page: Option[(Int, Int)] = AbstractQuery.this.page;
    <accessor> private[squeryl] def page_=(x$1: Option[(Int, Int)]): Unit = AbstractQuery.this.page = x$1;
    private[this] val resultSetMapper: org.squeryl.internals.ResultSetMapper = new org.squeryl.internals.ResultSetMapper();
    <stable> <accessor> def resultSetMapper: org.squeryl.internals.ResultSetMapper = AbstractQuery.this.resultSetMapper;
    private[this] val name: String = "query";
    <stable> <accessor> def name: String = AbstractQuery.this.name;
    def give(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = {
      rsm.pushYieldedValues(rs);
      val r: R = AbstractQuery.this.invokeYield(rsm, rs);
      r
    };
    private[this] val definitionSite: Option[StackTraceElement] = if (AbstractQuery.this.isRoot.unary_!)
      scala.None
    else
      scala.Some.apply[StackTraceElement](AbstractQuery.this._deduceDefinitionSite);
    <stable> <accessor> def definitionSite: Option[StackTraceElement] = AbstractQuery.this.definitionSite;
    private def _deduceDefinitionSite: StackTraceElement = {
      val st: Array[StackTraceElement] = java.this.lang.Thread.currentThread().getStackTrace();
      var i: Int = 1;
      while$1(){
        if (i.<(st.length))
          {
            {
              val e: StackTraceElement = st.apply(i);
              val cn: String = e.getClassName();
              if (cn.startsWith("org.squeryl.").&&(cn.startsWith("org.squeryl.tests.").unary_!).||(cn.startsWith("scala.")))
                i = i.+(1)
              else
                return e
            };
            while$1()
          }
        else
          ()
      };
      new java.this.lang.StackTraceElement("unknown", "unknown", "unknown", -1)
    };
    protected def buildAst(qy: org.squeryl.dsl.QueryYield[R], subQueryables: AbstractQuery.this.SubQueryable[_]*): org.squeryl.dsl.ast.QueryExpressionNode[R] = {
      val subQueries: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.QueryableExpressionNode] = new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.QueryableExpressionNode]();
      val views: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.ViewExpressionNode[_]] = new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.ViewExpressionNode[_]]();
      if (qy.joinExpressions.!=(immutable.this.Nil))
        {
          val sqIterator: Iterator[AbstractQuery.this.SubQueryable[_]] = subQueryables.iterator;
          val joinExprsIterator: Iterator[() => org.squeryl.dsl.ast.LogicalBoolean] = qy.joinExpressions.iterator;
          sqIterator.next();
          while$2(){
            if (sqIterator.hasNext)
              {
                {
                  val nthQueryable: AbstractQuery.this.SubQueryable[_] = sqIterator.next();
                  val nthJoinExpr: () => org.squeryl.dsl.ast.LogicalBoolean = joinExprsIterator.next();
                  nthQueryable.node.joinExpression_=(scala.Some.apply[org.squeryl.dsl.ast.LogicalBoolean](nthJoinExpr.apply()))
                };
                while$2()
              }
            else
              ()
          }
        }
      else
        ();
      subQueryables.foreach[Unit](((sq: AbstractQuery.this.SubQueryable[_]) => if (sq.isQuery.unary_!)
        views.append(sq.node.asInstanceOf[org.squeryl.dsl.ast.ViewExpressionNode[_]])
      else
        ()));
      subQueryables.foreach[Unit](((sq: AbstractQuery.this.SubQueryable[_]) => if (sq.isQuery)
        {
          val z: org.squeryl.dsl.ast.QueryExpressionNode[_] = sq.node.asInstanceOf[org.squeryl.dsl.ast.QueryExpressionNode[_]];
          if (z.isUseableAsSubquery.unary_!)
            org.squeryl.internals.Utils.throwError("Sub query returns a primitive type or a Tuple of primitive type, and therefore is not useable as a subquery in a from or join clause, see \nhttp://squeryl.org/limitations.html")
          else
            ();
          subQueries.append(z)
        }
      else
        ()));
      val qen: org.squeryl.dsl.ast.QueryExpressionNode[R] = new org.squeryl.dsl.ast.QueryExpressionNode[R](this, qy, subQueries, views);
      <synthetic> private[this] val x$1: (Iterable[org.squeryl.dsl.ast.SelectElement], AnyRef) = (qy.invokeYieldForAst(qen, AbstractQuery.this.resultSetMapper): (Iterable[org.squeryl.dsl.ast.SelectElement], AnyRef) @unchecked) match {
        case (_1: Iterable[org.squeryl.dsl.ast.SelectElement], _2: AnyRef)(Iterable[org.squeryl.dsl.ast.SelectElement], AnyRef)((sl @ _), (d @ _)) => scala.Tuple2.apply[Iterable[org.squeryl.dsl.ast.SelectElement], AnyRef](sl, d)
      };
      val sl: Iterable[org.squeryl.dsl.ast.SelectElement] = x$1._1;
      val d: AnyRef = x$1._2;
      qen.setOutExpressionNodesAndSample(sl, d);
      qen
    };
    def ast: org.squeryl.dsl.ast.QueryExpressionNode[R];
    def copy(asRoot: Boolean): org.squeryl.dsl.AbstractQuery[R] = {
      val c: org.squeryl.dsl.AbstractQuery[R] = AbstractQuery.this.createCopy(asRoot);
      c.selectDistinct_=(AbstractQuery.this.selectDistinct);
      c
    };
    def createCopy(asRoot: Boolean): org.squeryl.dsl.AbstractQuery[R];
    def dumpAst: String = AbstractQuery.this.ast.dumpAst;
    def statement: String = AbstractQuery.this._genStatement(true);
    private def _genStatement(forDisplay: Boolean): String = {
      val sw: org.squeryl.internals.StatementWriter = new org.squeryl.internals.StatementWriter(forDisplay, org.squeryl.Session.currentSession.databaseAdapter);
      AbstractQuery.this.ast.write(sw);
      sw.statement
    };
    def distinct: org.squeryl.dsl.AbstractQuery[R] = {
      val c: org.squeryl.dsl.AbstractQuery[R] = AbstractQuery.this.copy(true);
      c.selectDistinct_=(true);
      c
    };
    def page(offset: Int, pageLength: Int): org.squeryl.Query[R] = {
      val c: org.squeryl.dsl.AbstractQuery[R] = AbstractQuery.this.copy(true);
      c.page_=(scala.Some.apply[(Int, Int)](scala.Tuple2.apply[Int, Int](offset, pageLength)));
      c
    };
    def forUpdate: org.squeryl.dsl.AbstractQuery[R] = {
      val c: org.squeryl.dsl.AbstractQuery[R] = AbstractQuery.this.copy(true);
      c.isForUpdate_=(true);
      c
    };
    private def _dbAdapter: org.squeryl.internals.DatabaseAdapter = org.squeryl.Session.currentSession.databaseAdapter;
    def iterator: Iterator[R] with java.io.Closeable = {
      final class $anon extends AnyRef with Iterator[R] with java.io.Closeable {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val sw: org.squeryl.internals.StatementWriter = new org.squeryl.internals.StatementWriter(false, AbstractQuery.this._dbAdapter);
        <stable> <accessor> private def sw: org.squeryl.internals.StatementWriter = $anon.this.sw;
        AbstractQuery.this.ast.write($anon.this.sw);
        private[this] val s: org.squeryl.AbstractSession = org.squeryl.Session.currentSession;
        <stable> <accessor> private def s: org.squeryl.AbstractSession = $anon.this.s;
        private[this] val beforeQueryExecute: Long = java.this.lang.System.currentTimeMillis();
        <stable> <accessor> private def beforeQueryExecute: Long = $anon.this.beforeQueryExecute;
        <synthetic> private[this] val x$2: (java.sql.ResultSet, java.sql.PreparedStatement) = (AbstractQuery.this._dbAdapter.executeQuery($anon.this.s, $anon.this.sw): (java.sql.ResultSet, java.sql.PreparedStatement) @unchecked) match {
          case (_1: java.sql.ResultSet, _2: java.sql.PreparedStatement)(java.sql.ResultSet, java.sql.PreparedStatement)((rs @ _), (stmt @ _)) => scala.Tuple2.apply[java.sql.ResultSet, java.sql.PreparedStatement](rs, stmt)
        };
        private[this] val rs: java.sql.ResultSet = $anon.this.x$2._1;
        <stable> <accessor> private def rs: java.sql.ResultSet = $anon.this.rs;
        private[this] val stmt: java.sql.PreparedStatement = $anon.this.x$2._2;
        <stable> <accessor> private def stmt: java.sql.PreparedStatement = $anon.this.stmt;
        lazy private[this] var statEx: org.squeryl.logging.StatementInvocationEvent = new org.squeryl.logging.StatementInvocationEvent(AbstractQuery.this.definitionSite.get, $anon.this.beforeQueryExecute, java.this.lang.System.currentTimeMillis(), -1, $anon.this.sw.statement);
        if ($anon.this.s.statisticsListener.!=(scala.None))
          $anon.this.s.statisticsListener.get.queryExecuted($anon.this.statEx)
        else
          ();
        $anon.this.s._addStatement($anon.this.stmt);
        $anon.this.s._addResultSet($anon.this.rs);
        private[this] var _nextCalled: Boolean = false;
        <accessor> private def _nextCalled: Boolean = $anon.this._nextCalled;
        <accessor> private def _nextCalled_=(x$1: Boolean): Unit = $anon.this._nextCalled = x$1;
        private[this] var _hasNext: Boolean = false;
        <accessor> private def _hasNext: Boolean = $anon.this._hasNext;
        <accessor> private def _hasNext_=(x$1: Boolean): Unit = $anon.this._hasNext = x$1;
        private[this] var rowCount: Int = 0;
        <accessor> private def rowCount: Int = $anon.this.rowCount;
        <accessor> private def rowCount_=(x$1: Int): Unit = $anon.this.rowCount = x$1;
        def close: Unit = {
          $anon.this.stmt.close();
          $anon.this.rs.close()
        };
        private def _next: Unit = {
          $anon.this._hasNext_=($anon.this.rs.next());
          if ($anon.this._hasNext.unary_!)
            {
              org.squeryl.internals.Utils.close($anon.this.rs);
              $anon.this.stmt.close();
              if ($anon.this.s.statisticsListener.!=(scala.None))
                $anon.this.s.statisticsListener.get.resultSetIterationEnded($anon.this.statEx.uuid, java.this.lang.System.currentTimeMillis(), $anon.this.rowCount, true)
              else
                ()
            }
          else
            ();
          $anon.this.rowCount_=($anon.this.rowCount.+(1));
          $anon.this._nextCalled_=(true)
        };
        def hasNext: Boolean = {
          if ($anon.this._nextCalled.unary_!)
            $anon.this._next
          else
            ();
          $anon.this._hasNext
        };
        def next: R = {
          if ($anon.this._nextCalled.unary_!)
            $anon.this._next
          else
            ();
          if ($anon.this._hasNext.unary_!)
            throw new scala.`package`.NoSuchElementException("next called with no rows available")
          else
            ();
          $anon.this._nextCalled_=(false);
          if ($anon.this.s.isLoggingEnabled)
            $anon.this.s.log(org.squeryl.internals.ResultSetUtils.dumpRow($anon.this.rs))
          else
            ();
          AbstractQuery.this.give(AbstractQuery.this.resultSetMapper, $anon.this.rs)
        }
      };
      new $anon()
    };
    override def toString: String = AbstractQuery.this.dumpAst.+("\n").+(AbstractQuery.this._genStatement(true));
    protected def createSubQueryable[U >: Nothing <: Any](q: org.squeryl.Queryable[U]): AbstractQuery.this.SubQueryable[U] = if (q.isInstanceOf[org.squeryl.View[_]])
      {
        val v: org.squeryl.View[U] = q.asInstanceOf[org.squeryl.View[U]];
        val vxn: org.squeryl.dsl.ast.ViewExpressionNode[U] = new org.squeryl.dsl.ast.ViewExpressionNode[U](v);
        vxn.sample_=(v.posoMetaData.createSample(org.squeryl.internals.FieldReferenceLinker.createCallBack(vxn)));
        new AbstractQuery.this.SubQueryable[U](v, vxn.sample, vxn.resultSetMapper, false, vxn)
      }
    else
      if (q.isInstanceOf[org.squeryl.dsl.OptionalQueryable[_]])
        {
          val oqr: org.squeryl.dsl.OptionalQueryable[U] = q.asInstanceOf[org.squeryl.dsl.OptionalQueryable[U]];
          val sq: AbstractQuery.this.SubQueryable[U] = AbstractQuery.this.createSubQueryable[U](oqr.queryable);
          sq.node.inhibited_=(oqr.inhibited);
          val oqCopy: org.squeryl.dsl.OptionalQueryable[U] = new org.squeryl.dsl.OptionalQueryable[U](sq.queryable);
          oqCopy.inhibited_=(oqr.inhibited);
          new AbstractQuery.this.SubQueryable[U](oqCopy.asInstanceOf[org.squeryl.Queryable[U]], scala.Some.apply[U](sq.sample).asInstanceOf[U], sq.resultSetMapper, sq.isQuery, sq.node)
        }
      else
        if (q.isInstanceOf[org.squeryl.dsl.internal.OuterJoinedQueryable[_]])
          {
            val ojq: org.squeryl.dsl.internal.OuterJoinedQueryable[U] = q.asInstanceOf[org.squeryl.dsl.internal.OuterJoinedQueryable[U]];
            val sq: AbstractQuery.this.SubQueryable[U] = AbstractQuery.this.createSubQueryable[U](ojq.queryable);
            sq.node.joinKind_=(scala.Some.apply[(String, String)](scala.Tuple2.apply[String, String](ojq.leftRightOrFull, "outer")));
            sq.node.inhibited_=(ojq.inhibited);
            new AbstractQuery.this.SubQueryable[U](sq.queryable, scala.Some.apply[U](sq.sample).asInstanceOf[U], sq.resultSetMapper, sq.isQuery, sq.node)
          }
        else
          if (q.isInstanceOf[org.squeryl.dsl.internal.InnerJoinedQueryable[_]])
            {
              val ijq: org.squeryl.dsl.internal.InnerJoinedQueryable[U] = q.asInstanceOf[org.squeryl.dsl.internal.InnerJoinedQueryable[U]];
              val sq: AbstractQuery.this.SubQueryable[U] = AbstractQuery.this.createSubQueryable[U](ijq.queryable);
              sq.node.joinKind_=(scala.Some.apply[(String, String)](scala.Tuple2.apply[String, String](ijq.leftRightOrFull, "inner")));
              new AbstractQuery.this.SubQueryable[U](sq.queryable, sq.sample, sq.resultSetMapper, sq.isQuery, sq.node)
            }
          else
            if (q.isInstanceOf[org.squeryl.dsl.DelegateQuery[_]])
              AbstractQuery.this.createSubQueryable[U](q.asInstanceOf[org.squeryl.dsl.DelegateQuery[U]].q)
            else
              {
                val qr: org.squeryl.dsl.AbstractQuery[U] = q.asInstanceOf[org.squeryl.dsl.AbstractQuery[U]];
                val copy: org.squeryl.dsl.AbstractQuery[U] = qr.copy(false);
                new AbstractQuery.this.SubQueryable[U](copy, copy.ast.sample.asInstanceOf[U], copy.resultSetMapper, true, copy.ast)
              };
    protected class SubQueryable[U >: Nothing <: Any] extends scala.AnyRef {
      <paramaccessor> private[this] val queryable: org.squeryl.Queryable[U] = _;
      <stable> <accessor> <paramaccessor> def queryable: org.squeryl.Queryable[U] = SubQueryable.this.queryable;
      <paramaccessor> private[this] val sample: U = _;
      <stable> <accessor> <paramaccessor> def sample: U = SubQueryable.this.sample;
      <paramaccessor> private[this] val resultSetMapper: org.squeryl.internals.ResultSetMapper = _;
      <stable> <accessor> <paramaccessor> def resultSetMapper: org.squeryl.internals.ResultSetMapper = SubQueryable.this.resultSetMapper;
      <paramaccessor> private[this] val isQuery: Boolean = _;
      <stable> <accessor> <paramaccessor> def isQuery: Boolean = SubQueryable.this.isQuery;
      <paramaccessor> private[this] val node: org.squeryl.dsl.ast.QueryableExpressionNode = _;
      <stable> <accessor> <paramaccessor> def node: org.squeryl.dsl.ast.QueryableExpressionNode = SubQueryable.this.node;
      def <init>(queryable: org.squeryl.Queryable[U], sample: U, resultSetMapper: org.squeryl.internals.ResultSetMapper, isQuery: Boolean, node: org.squeryl.dsl.ast.QueryableExpressionNode): AbstractQuery.this.SubQueryable[U] = {
        SubQueryable.super.<init>();
        ()
      };
      def give(rs: java.sql.ResultSet): U = if (SubQueryable.this.node.joinKind.!=(scala.None))
        if (SubQueryable.this.node.isOuterJoined)
          {
            val isNoneInOuterJoin: Boolean = SubQueryable.this.isQuery.unary_!.&&(SubQueryable.this.resultSetMapper.isNoneInOuterJoin(rs));
            if (isNoneInOuterJoin)
              scala.None.asInstanceOf[U]
            else
              scala.Some.apply[U](SubQueryable.this.queryable.give(SubQueryable.this.resultSetMapper, rs)).asInstanceOf[U]
          }
        else
          SubQueryable.this.queryable.give(SubQueryable.this.resultSetMapper, rs)
      else
        if (SubQueryable.this.node.isRightJoined.&&(SubQueryable.this.resultSetMapper.isNoneInOuterJoin(rs)))
          SubQueryable.this.sample
        else
          SubQueryable.this.queryable.give(SubQueryable.this.resultSetMapper, rs)
    }
  }
}