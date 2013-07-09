package org.squeryl.logging {
  import org.squeryl.Session;
  import org.squeryl.adapters.H2Adapter;
  import org.squeryl.InternalFieldMapper._;
  object LocalH2SinkStatisticsListener extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    def initializeOverwrite(schemaName: String, workingDir: String = ".") = initialize(schemaName, true, workingDir);
    def initializeAppend(schemaName: String, workingDir: String = ".") = initialize(schemaName, false, workingDir);
    def initialize(schemaName: String, overwrite: Boolean, workingDir: String) = {
      Class.forName("org.h2.Driver");
      val file = new java.io.File(workingDir, schemaName.$plus(".h2.db")).getCanonicalFile;
      val exists = file.exists;
      if (file.exists.$amp$amp(overwrite))
        file.delete
      else
        ();
      val s = new Session(java.sql.DriverManager.getConnection("jdbc:h2:".$plus(workingDir).$plus("/").$plus(schemaName), "sa", ""), new H2Adapter());
      if (file.exists.unary_$bang.$bar$bar(overwrite))
        using(s)(StatsSchema.create)
      else
        ();
      val l = new LocalH2SinkStatisticsListener(s);
      l
    }
  };
  class LocalH2SinkStatisticsListener extends StatisticsListener {
    <paramaccessor> val h2Session: Session = _;
    def <init>(h2Session: Session) = {
      super.<init>();
      ()
    };
    private var _closed = false;
    private val _queue = new java.util.concurrent.ArrayBlockingQueue[_root_.scala.Function0[Unit]](1024, false);
    private val _worker = {
      final class $anon extends Thread {
        def <init>() = {
          super.<init>();
          ()
        };
        override def run(): scala.Unit = {
          h2Session.bindToCurrentThread;
          while$1(){
            if (_closed.unary_$bang)
              {
                {
                  val op = _queue.take;
                  op()
                };
                while$1()
              }
            else
              ()
          }
        }
      };
      new $anon()
    };
    _worker.start;
    def shutdown = _closed = true;
    private def _pushOp(op: _root_.scala.<byname>[Unit]) = if (_closed.unary_$bang)
      _queue.put((op: (() => <empty>)))
    else
      throw new IllegalStateException(scala.Symbol("LocalH2SinkStatisticsListener").$plus(" has been shutdown."));
    def generateStatSummary(staticHtmlFile: java.io.File, n: Int) = _pushOp(BarChartRenderer.generateStatSummary(staticHtmlFile, n));
    def queryExecuted(se: StatementInvocationEvent) = _pushOp({
      StatsSchema.recordStatementInvocation(se);
      h2Session.connection.commit
    });
    def resultSetIterationEnded(invocationId: String, iterationEndTime: Long, rowCount: Int, iterationCompleted: Boolean) = _pushOp({
      StatsSchema.recordEndOfIteration(invocationId, (iterationEndTime: Long), (rowCount: Int), (iterationCompleted: Boolean));
      h2Session.connection.commit
    });
    def updateExecuted(se: StatementInvocationEvent) = ();
    def insertExecuted(se: StatementInvocationEvent) = ();
    def deleteExecuted(se: StatementInvocationEvent) = ()
  }
}