package org.squeryl.logging {
  import org.squeryl.Session;
  import org.squeryl.adapters.H2Adapter;
  import org.squeryl.InternalFieldMapper._;
  object LocalH2SinkStatisticsListener extends scala.AnyRef {
    def <init>(): org.squeryl.logging.LocalH2SinkStatisticsListener.type = {
      LocalH2SinkStatisticsListener.super.<init>();
      ()
    };
    def initializeOverwrite(schemaName: String, workingDir: String = "."): org.squeryl.logging.LocalH2SinkStatisticsListener = LocalH2SinkStatisticsListener.this.initialize(schemaName, true, workingDir);
    def initializeAppend(schemaName: String, workingDir: String = "."): org.squeryl.logging.LocalH2SinkStatisticsListener = LocalH2SinkStatisticsListener.this.initialize(schemaName, false, workingDir);
    def initialize(schemaName: String, overwrite: Boolean, workingDir: String): org.squeryl.logging.LocalH2SinkStatisticsListener = {
      java.this.lang.Class.forName("org.h2.Driver");
      val file: java.io.File = new java.io.File(workingDir, schemaName.+(".h2.db")).getCanonicalFile();
      val exists: Boolean = file.exists();
      if (file.exists().&&(overwrite))
        file.delete()
      else
        ();
      val s: org.squeryl.Session = new org.squeryl.Session(java.sql.DriverManager.getConnection("jdbc:h2:".+(workingDir).+("/").+(schemaName), "sa", ""), new org.squeryl.adapters.H2Adapter(), squeryl.this.Session.<init>$default$3);
      if (file.exists().unary_!.||(overwrite))
        org.squeryl.InternalFieldMapper.using[Unit](s)(StatsSchema.create)
      else
        ();
      val l: org.squeryl.logging.LocalH2SinkStatisticsListener = new LocalH2SinkStatisticsListener(s);
      l
    };
    <synthetic> def initializeOverwrite$default$2: String @scala.annotation.unchecked.uncheckedVariance = ".";
    <synthetic> def initializeAppend$default$2: String @scala.annotation.unchecked.uncheckedVariance = "."
  };
  class LocalH2SinkStatisticsListener extends Object with org.squeryl.logging.StatisticsListener {
    <paramaccessor> private[this] val h2Session: org.squeryl.Session = _;
    <stable> <accessor> <paramaccessor> def h2Session: org.squeryl.Session = LocalH2SinkStatisticsListener.this.h2Session;
    def <init>(h2Session: org.squeryl.Session): org.squeryl.logging.LocalH2SinkStatisticsListener = {
      LocalH2SinkStatisticsListener.super.<init>();
      ()
    };
    private[this] var _closed: Boolean = false;
    <accessor> private def _closed: Boolean = LocalH2SinkStatisticsListener.this._closed;
    <accessor> private def _closed_=(x$1: Boolean): Unit = LocalH2SinkStatisticsListener.this._closed = x$1;
    private[this] val _queue: java.util.concurrent.ArrayBlockingQueue[() => Unit] = new java.util.concurrent.ArrayBlockingQueue[() => Unit](1024, false);
    <stable> <accessor> private def _queue: java.util.concurrent.ArrayBlockingQueue[() => Unit] = LocalH2SinkStatisticsListener.this._queue;
    private[this] val _worker: Thread = {
      final class $anon extends java.this.lang.Thread {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        override def run(): Unit = {
          LocalH2SinkStatisticsListener.this.h2Session.bindToCurrentThread;
          while$1(){
            if (LocalH2SinkStatisticsListener.this._closed.unary_!)
              {
                {
                  val op: () => Unit = LocalH2SinkStatisticsListener.this._queue.take();
                  op.apply()
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
    <stable> <accessor> private def _worker: Thread = LocalH2SinkStatisticsListener.this._worker;
    LocalH2SinkStatisticsListener.this._worker.start();
    def shutdown: Unit = LocalH2SinkStatisticsListener.this._closed_=(true);
    private def _pushOp(op: => Unit): Unit = if (LocalH2SinkStatisticsListener.this._closed.unary_!)
      LocalH2SinkStatisticsListener.this._queue.put((() => op))
    else
      throw new java.this.lang.IllegalStateException(scala.this.Predef.any2stringadd(scala.Symbol.apply("LocalH2SinkStatisticsListener")).+(" has been shutdown."));
    def generateStatSummary(staticHtmlFile: java.io.File, n: Int): Unit = LocalH2SinkStatisticsListener.this._pushOp(BarChartRenderer.generateStatSummary(staticHtmlFile, n));
    def queryExecuted(se: org.squeryl.logging.StatementInvocationEvent): Unit = LocalH2SinkStatisticsListener.this._pushOp({
      StatsSchema.recordStatementInvocation(se);
      LocalH2SinkStatisticsListener.this.h2Session.connection.commit()
    });
    def resultSetIterationEnded(invocationId: String, iterationEndTime: Long, rowCount: Int, iterationCompleted: Boolean): Unit = LocalH2SinkStatisticsListener.this._pushOp({
      StatsSchema.recordEndOfIteration(invocationId, (iterationEndTime: Long), (rowCount: Int), (iterationCompleted: Boolean));
      LocalH2SinkStatisticsListener.this.h2Session.connection.commit()
    });
    def updateExecuted(se: org.squeryl.logging.StatementInvocationEvent): Unit = ();
    def insertExecuted(se: org.squeryl.logging.StatementInvocationEvent): Unit = ();
    def deleteExecuted(se: org.squeryl.logging.StatementInvocationEvent): Unit = ()
  }
}