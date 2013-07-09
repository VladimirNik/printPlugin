package org.squeryl {
  import logging.StatisticsListener;
  import org.squeryl.internals._;
  import scala.collection.mutable.ArrayBuffer;
  import java.sql.{SQLException, ResultSet, Statement, Connection};
  import scala.util.control.ControlThrowable;
  class LazySession extends Object with org.squeryl.AbstractSession {
    <paramaccessor> private[this] val connectionFunc: () => java.sql.Connection = _;
    <stable> <accessor> <paramaccessor> def connectionFunc: () => java.sql.Connection = LazySession.this.connectionFunc;
    <paramaccessor> private[this] val databaseAdapter: org.squeryl.internals.DatabaseAdapter = _;
    <stable> <accessor> <paramaccessor> def databaseAdapter: org.squeryl.internals.DatabaseAdapter = LazySession.this.databaseAdapter;
    <paramaccessor> private[this] val statisticsListener: Option[org.squeryl.logging.StatisticsListener] = _;
    <stable> <accessor> <paramaccessor> def statisticsListener: Option[org.squeryl.logging.StatisticsListener] = LazySession.this.statisticsListener;
    def <init>(connectionFunc: () => java.sql.Connection, databaseAdapter: org.squeryl.internals.DatabaseAdapter, statisticsListener: Option[org.squeryl.logging.StatisticsListener] = scala.None): org.squeryl.LazySession = {
      LazySession.super.<init>();
      ()
    };
    private[this] var _connection: Option[java.sql.Connection] = scala.None;
    <accessor> private def _connection: Option[java.sql.Connection] = LazySession.this._connection;
    <accessor> private def _connection_=(x$1: Option[java.sql.Connection]): Unit = LazySession.this._connection = x$1;
    def hasConnection: Boolean = LazySession.this._connection.!=(scala.None);
    private[this] var originalAutoCommit: Boolean = true;
    <accessor> def originalAutoCommit: Boolean = LazySession.this.originalAutoCommit;
    <accessor> def originalAutoCommit_=(x$1: Boolean): Unit = LazySession.this.originalAutoCommit = x$1;
    def connection: java.sql.Connection = LazySession.this._connection.getOrElse[java.sql.Connection]({
      val c: java.sql.Connection = LazySession.this.connectionFunc.apply();
      try {
        LazySession.this.originalAutoCommit_=(c.getAutoCommit());
        if (LazySession.this.originalAutoCommit)
          c.setAutoCommit(false)
        else
          ();
        LazySession.this._connection_=(scala.Option.apply[java.sql.Connection](c));
        c
      } catch {
        case (e @ (_: java.sql.SQLException)) => {
          org.squeryl.internals.Utils.close(LazySession.this.connection);
          throw e
        }
      }
    });
    def withinTransaction[A >: Nothing <: Any](f: () => A): A = {
      var txOk: Boolean = false;
      try {
        val res: A = this.using[A](f);
        txOk = true;
        res
      } catch {
        case (e @ (_: scala.util.control.ControlThrowable)) => {
          txOk = true;
          throw e
        }
      } finally if (LazySession.this.hasConnection)
        {
          try {
            if (txOk)
              LazySession.this.connection.commit()
            else
              LazySession.this.connection.rollback();
            if (LazySession.this.originalAutoCommit.!=(LazySession.this.connection.getAutoCommit()))
              LazySession.this.connection.setAutoCommit(LazySession.this.originalAutoCommit)
            else
              ()
          } catch {
            case (e @ (_: java.sql.SQLException)) => {
              org.squeryl.internals.Utils.close(LazySession.this.connection);
              if (txOk)
                throw e
              else
                ()
            }
          };
          try {
            if (LazySession.this.connection.isClosed().unary_!)
              LazySession.this.connection.close()
            else
              ()
          } catch {
            case (e @ (_: java.sql.SQLException)) => if (txOk)
              throw e
            else
              ()
          }
        }
      else
        ()
    }
  };
  <synthetic> object LazySession extends AnyRef {
    def <init>(): org.squeryl.LazySession.type = {
      LazySession.super.<init>();
      ()
    };
    <synthetic> def <init>$default$3: Option[org.squeryl.logging.StatisticsListener] @scala.annotation.unchecked.uncheckedVariance = scala.None
  };
  class Session extends Object with org.squeryl.AbstractSession {
    <paramaccessor> private[this] val connection: java.sql.Connection = _;
    <stable> <accessor> <paramaccessor> def connection: java.sql.Connection = Session.this.connection;
    <paramaccessor> private[this] val databaseAdapter: org.squeryl.internals.DatabaseAdapter = _;
    <stable> <accessor> <paramaccessor> def databaseAdapter: org.squeryl.internals.DatabaseAdapter = Session.this.databaseAdapter;
    <paramaccessor> private[this] val statisticsListener: Option[org.squeryl.logging.StatisticsListener] = _;
    <stable> <accessor> <paramaccessor> def statisticsListener: Option[org.squeryl.logging.StatisticsListener] = Session.this.statisticsListener;
    def <init>(connection: java.sql.Connection, databaseAdapter: org.squeryl.internals.DatabaseAdapter, statisticsListener: Option[org.squeryl.logging.StatisticsListener] = scala.None): org.squeryl.Session = {
      Session.super.<init>();
      ()
    };
    private[this] val hasConnection: Boolean = true;
    <stable> <accessor> def hasConnection: Boolean = Session.this.hasConnection;
    def withinTransaction[A >: Nothing <: Any](f: () => A): A = {
      val originalAutoCommit: Boolean = true;
      try {
        Session.this.connection.getAutoCommit();
        if (originalAutoCommit)
          Session.this.connection.setAutoCommit(false)
        else
          ()
      } catch {
        case (e @ (_: java.sql.SQLException)) => {
          org.squeryl.internals.Utils.close(Session.this.connection);
          throw e
        }
      };
      var txOk: Boolean = false;
      try {
        val res: A = this.using[A](f);
        txOk = true;
        res
      } catch {
        case (e @ (_: scala.util.control.ControlThrowable)) => {
          txOk = true;
          throw e
        }
      } finally {
        try {
          if (txOk)
            Session.this.connection.commit()
          else
            Session.this.connection.rollback();
          if (originalAutoCommit.!=(Session.this.connection.getAutoCommit()))
            Session.this.connection.setAutoCommit(originalAutoCommit)
          else
            ()
        } catch {
          case (e @ (_: java.sql.SQLException)) => {
            org.squeryl.internals.Utils.close(Session.this.connection);
            if (txOk)
              throw e
            else
              ()
          }
        };
        try {
          if (Session.this.connection.isClosed().unary_!)
            Session.this.connection.close()
          else
            ()
        } catch {
          case (e @ (_: java.sql.SQLException)) => if (txOk)
            throw e
          else
            ()
        }
      }
    }
  };
  abstract trait AbstractSession extends scala.AnyRef {
    def /*AbstractSession*/$init$(): Unit = {
      ()
    };
    def connection: java.sql.Connection;
    def hasConnection: Boolean;
    protected[squeryl] def withinTransaction[A >: Nothing <: Any](f: () => A): A;
    protected[squeryl] def using[A >: Nothing <: Any](a: () => A): A = {
      val s: Option[org.squeryl.AbstractSession] = Session.currentSessionOption;
      try {
        if (s.!=(scala.None))
          s.get.unbindFromCurrentThread
        else
          ();
        try {
          this.bindToCurrentThread;
          val r: A = a.apply();
          r
        } finally {
          this.unbindFromCurrentThread;
          this.cleanup
        }
      } finally if (s.!=(scala.None))
        s.get.bindToCurrentThread
      else
        ()
    };
    def databaseAdapter: org.squeryl.internals.DatabaseAdapter;
    def statisticsListener: Option[org.squeryl.logging.StatisticsListener];
    def bindToCurrentThread: Unit = Session.currentSession_=(scala.Some.apply[org.squeryl.AbstractSession](this));
    def unbindFromCurrentThread: Unit = Session.currentSession_=(scala.None);
    private[this] var _logger: String => Unit = null;
    <accessor> private def _logger: String => Unit = AbstractSession.this._logger;
    <accessor> private def _logger_=(x$1: String => Unit): Unit = AbstractSession.this._logger = x$1;
    def logger_=(f: String => Unit): Unit = AbstractSession.this._logger_=(f);
    def setLogger(f: String => Unit): Unit = AbstractSession.this._logger_=(f);
    def isLoggingEnabled: Boolean = AbstractSession.this._logger.!=(null);
    def log(s: String): Unit = if (AbstractSession.this.isLoggingEnabled)
      AbstractSession.this._logger.apply(s)
    else
      ();
    private[this] var logUnclosedStatements: Boolean = false;
    <accessor> def logUnclosedStatements: Boolean = AbstractSession.this.logUnclosedStatements;
    <accessor> def logUnclosedStatements_=(x$1: Boolean): Unit = AbstractSession.this.logUnclosedStatements = x$1;
    private[this] val _statements: scala.collection.mutable.ArrayBuffer[java.sql.Statement] = new scala.collection.mutable.ArrayBuffer[java.sql.Statement]();
    <stable> <accessor> private def _statements: scala.collection.mutable.ArrayBuffer[java.sql.Statement] = AbstractSession.this._statements;
    private[this] val _resultSets: scala.collection.mutable.ArrayBuffer[java.sql.ResultSet] = new scala.collection.mutable.ArrayBuffer[java.sql.ResultSet]();
    <stable> <accessor> private def _resultSets: scala.collection.mutable.ArrayBuffer[java.sql.ResultSet] = AbstractSession.this._resultSets;
    private[squeryl] def _addStatement(s: java.sql.Statement): Unit = AbstractSession.this._statements.append(s);
    private[squeryl] def _addResultSet(rs: java.sql.ResultSet): Unit = AbstractSession.this._resultSets.append(rs);
    def cleanup: Unit = {
      AbstractSession.this._statements.foreach[Unit](((s: java.sql.Statement) => {
        if (AbstractSession.this.logUnclosedStatements.&&(AbstractSession.this.isLoggingEnabled).&&(s.isClosed().unary_!))
          {
            val stackTrace: String = scala.this.Predef.refArrayOps[String](scala.this.Predef.refArrayOps[StackTraceElement](java.this.lang.Thread.currentThread().getStackTrace()).map[String, Array[String]](((x$1: StackTraceElement) => "at ".+(x$1)))(scala.this.Array.canBuildFrom[String](ClassTag.apply[String](classOf[java.lang.String])))).mkString("\n");
            AbstractSession.this.log("Statement is not closed: ".+(s).+(": ").+(java.this.lang.System.identityHashCode(s)).+("\n").+(stackTrace))
          }
        else
          ();
        org.squeryl.internals.Utils.close(s)
      }));
      AbstractSession.this._statements.clear();
      AbstractSession.this._resultSets.foreach[Unit](((rs: java.sql.ResultSet) => org.squeryl.internals.Utils.close(rs)));
      AbstractSession.this._resultSets.clear()
    };
    def close: Unit = {
      AbstractSession.this.cleanup;
      if (AbstractSession.this.hasConnection)
        AbstractSession.this.connection.close()
      else
        ()
    }
  };
  abstract trait SessionFactory extends scala.AnyRef {
    def newSession: org.squeryl.AbstractSession
  };
  object SessionFactory extends scala.AnyRef {
    def <init>(): org.squeryl.SessionFactory.type = {
      SessionFactory.super.<init>();
      ()
    };
    private[this] var concreteFactory: Option[() => org.squeryl.AbstractSession] = scala.None;
    <accessor> def concreteFactory: Option[() => org.squeryl.AbstractSession] = SessionFactory.this.concreteFactory;
    <accessor> def concreteFactory_=(x$1: Option[() => org.squeryl.AbstractSession]): Unit = SessionFactory.this.concreteFactory = x$1;
    private[this] var externalTransactionManagementAdapter: Option[() => Option[org.squeryl.AbstractSession]] = scala.None;
    <accessor> def externalTransactionManagementAdapter: Option[() => Option[org.squeryl.AbstractSession]] = SessionFactory.this.externalTransactionManagementAdapter;
    <accessor> def externalTransactionManagementAdapter_=(x$1: Option[() => Option[org.squeryl.AbstractSession]]): Unit = SessionFactory.this.externalTransactionManagementAdapter = x$1;
    def newSession: org.squeryl.AbstractSession = SessionFactory.this.concreteFactory.getOrElse[() => org.squeryl.AbstractSession](throw new java.this.lang.IllegalStateException("org.squeryl.SessionFactory not initialized, SessionFactory.concreteFactory must be assigned a \nfunction for creating new org.squeryl.Session, before transaction can be used.\nAlternatively SessionFactory.externalTransactionManagementAdapter can initialized, please refer to the documentation.")).apply()
  };
  object Session extends scala.AnyRef {
    def <init>(): org.squeryl.Session.type = {
      Session.super.<init>();
      ()
    };
    private[this] val _currentSessionThreadLocal: ThreadLocal[org.squeryl.AbstractSession] = new ThreadLocal[org.squeryl.AbstractSession]();
    <stable> <accessor> private def _currentSessionThreadLocal: ThreadLocal[org.squeryl.AbstractSession] = Session.this._currentSessionThreadLocal;
    def create(c: java.sql.Connection, a: org.squeryl.internals.DatabaseAdapter): org.squeryl.Session = new Session(c, a, squeryl.this.Session.<init>$default$3);
    def create(connectionFunc: () => java.sql.Connection, a: org.squeryl.internals.DatabaseAdapter): org.squeryl.LazySession = new LazySession(connectionFunc, a, squeryl.this.LazySession.<init>$default$3);
    def currentSessionOption: Option[org.squeryl.AbstractSession] = scala.Option.apply[org.squeryl.AbstractSession](Session.this._currentSessionThreadLocal.get()).orElse[org.squeryl.AbstractSession](SessionFactory.externalTransactionManagementAdapter.flatMap[org.squeryl.AbstractSession](((x$2: () => Option[org.squeryl.AbstractSession]) => x$2.apply())));
    def currentSession: org.squeryl.AbstractSession = if (SessionFactory.externalTransactionManagementAdapter.!=(scala.None))
      SessionFactory.externalTransactionManagementAdapter.get.apply().getOrElse[org.squeryl.AbstractSession](org.squeryl.internals.Utils.throwError("SessionFactory.externalTransactionManagementAdapter was unable to supply a Session for the current scope"))
    else
      Session.this.currentSessionOption.getOrElse[org.squeryl.AbstractSession](throw new java.this.lang.IllegalStateException("No session is bound to current thread, a session must be created via Session.create \nand bound to the thread via \'work\' or \'bindToCurrentThread\'\n Usually this error occurs when a statement is executed outside of a transaction/inTrasaction block"));
    def hasCurrentSession: Boolean = Session.this.currentSessionOption.!=(scala.None);
    def cleanupResources: Unit = Session.this.currentSessionOption.foreach[Unit](((x$3: org.squeryl.AbstractSession) => x$3.cleanup));
    private[squeryl] def currentSession_=(s: Option[org.squeryl.AbstractSession]): Unit = if (s.==(scala.None))
      Session.this._currentSessionThreadLocal.remove()
    else
      Session.this._currentSessionThreadLocal.set(s.get);
    <synthetic> def <init>$default$3: Option[org.squeryl.logging.StatisticsListener] @scala.annotation.unchecked.uncheckedVariance = scala.None
  }
}