package org.squeryl {
  import logging.StatisticsListener;
  import org.squeryl.internals._;
  import collection.mutable.ArrayBuffer;
  import java.sql.{SQLException, ResultSet, Statement, Connection};
  import scala.util.control.ControlThrowable;
  class LazySession extends AbstractSession {
    <paramaccessor> val connectionFunc: _root_.scala.Function0[Connection] = _;
    <paramaccessor> val databaseAdapter: DatabaseAdapter = _;
    <paramaccessor> val statisticsListener: Option[StatisticsListener] = _;
    def <init>(connectionFunc: _root_.scala.Function0[Connection], databaseAdapter: DatabaseAdapter, statisticsListener: Option[StatisticsListener] = None) = {
      super.<init>();
      ()
    };
    private var _connection: Option[Connection] = None;
    def hasConnection = _connection.$bang$eq(None);
    var originalAutoCommit = true;
    def connection: Connection = _connection.getOrElse({
      val c = connectionFunc();
      try {
        originalAutoCommit = c.getAutoCommit;
        if (originalAutoCommit)
          c.setAutoCommit(false)
        else
          ();
        _connection = Option(c);
        c
      } catch {
        case (e @ (_: SQLException)) => {
          Utils.close(connection);
          throw e
        }
      }
    });
    def withinTransaction[A >: _root_.scala.Nothing <: _root_.scala.Any](f: _root_.scala.Function0[A]): A = {
      var txOk = false;
      try {
        val res = this.using[A](f);
        txOk = true;
        res
      } catch {
        case (e @ (_: ControlThrowable)) => {
          txOk = true;
          throw e
        }
      } finally if (hasConnection)
        {
          try {
            if (txOk)
              connection.commit
            else
              connection.rollback;
            if (originalAutoCommit.$bang$eq(connection.getAutoCommit))
              connection.setAutoCommit(originalAutoCommit)
            else
              ()
          } catch {
            case (e @ (_: SQLException)) => {
              Utils.close(connection);
              if (txOk)
                throw e
              else
                ()
            }
          };
          try {
            if (connection.isClosed.unary_$bang)
              connection.close
            else
              ()
          } catch {
            case (e @ (_: SQLException)) => if (txOk)
              throw e
            else
              ()
          }
        }
      else
        ()
    }
  };
  class Session extends AbstractSession {
    <paramaccessor> val connection: Connection = _;
    <paramaccessor> val databaseAdapter: DatabaseAdapter = _;
    <paramaccessor> val statisticsListener: Option[StatisticsListener] = _;
    def <init>(connection: Connection, databaseAdapter: DatabaseAdapter, statisticsListener: Option[StatisticsListener] = None) = {
      super.<init>();
      ()
    };
    val hasConnection = true;
    def withinTransaction[A >: _root_.scala.Nothing <: _root_.scala.Any](f: _root_.scala.Function0[A]): A = {
      val originalAutoCommit = true;
      try {
        connection.getAutoCommit;
        if (originalAutoCommit)
          connection.setAutoCommit(false)
        else
          ()
      } catch {
        case (e @ (_: SQLException)) => {
          Utils.close(connection);
          throw e
        }
      };
      var txOk = false;
      try {
        val res = this.using[A](f);
        txOk = true;
        res
      } catch {
        case (e @ (_: ControlThrowable)) => {
          txOk = true;
          throw e
        }
      } finally {
        try {
          if (txOk)
            connection.commit
          else
            connection.rollback;
          if (originalAutoCommit.$bang$eq(connection.getAutoCommit))
            connection.setAutoCommit(originalAutoCommit)
          else
            ()
        } catch {
          case (e @ (_: SQLException)) => {
            Utils.close(connection);
            if (txOk)
              throw e
            else
              ()
          }
        };
        try {
          if (connection.isClosed.unary_$bang)
            connection.close
          else
            ()
        } catch {
          case (e @ (_: SQLException)) => if (txOk)
            throw e
          else
            ()
        }
      }
    }
  };
  abstract trait AbstractSession extends scala.AnyRef {
    def $init$() = {
      ()
    };
    def connection: Connection;
    def hasConnection: Boolean;
    protected[squeryl] def withinTransaction[A >: _root_.scala.Nothing <: _root_.scala.Any](f: _root_.scala.Function0[A]): A;
    protected[squeryl] def using[A >: _root_.scala.Nothing <: _root_.scala.Any](a: _root_.scala.Function0[A]): A = {
      val s = Session.currentSessionOption;
      try {
        if (s.$bang$eq(None))
          s.get.unbindFromCurrentThread
        else
          ();
        try {
          this.bindToCurrentThread;
          val r = a();
          r
        } finally {
          this.unbindFromCurrentThread;
          this.cleanup
        }
      } finally if (s.$bang$eq(None))
        s.get.bindToCurrentThread
      else
        ()
    };
    def databaseAdapter: DatabaseAdapter;
    def statisticsListener: Option[StatisticsListener];
    def bindToCurrentThread = Session.currentSession = Some(this);
    def unbindFromCurrentThread = Session.currentSession = None;
    private var _logger: _root_.scala.Function1[String, Unit] = null;
    def logger_$eq(f: _root_.scala.Function1[String, Unit]) = _logger = f;
    def setLogger(f: _root_.scala.Function1[String, Unit]) = _logger = f;
    def isLoggingEnabled = _logger.$bang$eq(null);
    def log(s: String) = if (isLoggingEnabled)
      _logger(s)
    else
      ();
    var logUnclosedStatements = false;
    private val _statements = new ArrayBuffer[Statement]();
    private val _resultSets = new ArrayBuffer[ResultSet]();
    private[squeryl] def _addStatement(s: Statement) = _statements.append(s);
    private[squeryl] def _addResultSet(rs: ResultSet) = _resultSets.append(rs);
    def cleanup = {
      _statements.foreach(((s) => {
        if (logUnclosedStatements.$amp$amp(isLoggingEnabled).$amp$amp(s.isClosed.unary_$bang))
          {
            val stackTrace = Thread.currentThread.getStackTrace.map(((x$1) => "at ".$plus(x$1))).mkString("\n");
            log("Statement is not closed: ".$plus(s).$plus(": ").$plus(System.identityHashCode(s)).$plus("\n").$plus(stackTrace))
          }
        else
          ();
        Utils.close(s)
      }));
      _statements.clear;
      _resultSets.foreach(((rs) => Utils.close(rs)));
      _resultSets.clear
    };
    def close = {
      cleanup;
      if (hasConnection)
        connection.close
      else
        ()
    }
  };
  abstract trait SessionFactory extends scala.AnyRef {
    def newSession: AbstractSession
  };
  object SessionFactory extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    var concreteFactory: Option[_root_.scala.Function0[AbstractSession]] = None;
    var externalTransactionManagementAdapter: Option[_root_.scala.Function0[Option[AbstractSession]]] = None;
    def newSession: AbstractSession = concreteFactory.getOrElse(throw new IllegalStateException("org.squeryl.SessionFactory not initialized, SessionFactory.concreteFactory must be assigned a \n".$plus("function for creating new org.squeryl.Session, before transaction can be used.\n").$plus("Alternatively SessionFactory.externalTransactionManagementAdapter can initialized, please refer to the documentation."))).apply
  };
  object Session extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    private val _currentSessionThreadLocal = new ThreadLocal[AbstractSession]();
    def create(c: Connection, a: DatabaseAdapter) = new Session(c, a);
    def create(connectionFunc: _root_.scala.Function0[Connection], a: DatabaseAdapter) = new LazySession(connectionFunc, a);
    def currentSessionOption: Option[AbstractSession] = Option(_currentSessionThreadLocal.get).orElse(SessionFactory.externalTransactionManagementAdapter.flatMap(((x$2) => x$2.apply())));
    def currentSession: AbstractSession = if (SessionFactory.externalTransactionManagementAdapter.$bang$eq(None))
      SessionFactory.externalTransactionManagementAdapter.get.apply.getOrElse(org.squeryl.internals.Utils.throwError("SessionFactory.externalTransactionManagementAdapter was unable to supply a Session for the current scope"))
    else
      currentSessionOption.getOrElse(throw new IllegalStateException("No session is bound to current thread, a session must be created via Session.create \nand bound to the thread via \'work\' or \'bindToCurrentThread\'\n Usually this error occurs when a statement is executed outside of a transaction/inTrasaction block"));
    def hasCurrentSession = currentSessionOption.$bang$eq(None);
    def cleanupResources = currentSessionOption.foreach(((x$3) => x$3.cleanup));
    private[squeryl] def currentSession_$eq(s: Option[AbstractSession]) = if (s.$eq$eq(None))
      _currentSessionThreadLocal.remove()
    else
      _currentSessionThreadLocal.set(s.get)
  }
}