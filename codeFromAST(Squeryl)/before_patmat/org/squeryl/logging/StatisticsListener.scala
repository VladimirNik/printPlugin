package org.squeryl.logging {
  import org.squeryl.dsl.ast.ExpressionNode;
  import org.squeryl.internals.Utils;
  import org.squeryl.dsl.CompositeKey2;
  import org.squeryl.{Schema, KeyedEntity};
  class StatementInvocationEvent extends scala.AnyRef {
    <paramaccessor> private[this] val _definitionOrCallSite: StackTraceElement = _;
    <paramaccessor> private[this] val start: Long = _;
    <stable> <accessor> <paramaccessor> def start: Long = StatementInvocationEvent.this.start;
    <paramaccessor> private[this] val end: Long = _;
    <stable> <accessor> <paramaccessor> def end: Long = StatementInvocationEvent.this.end;
    <paramaccessor> private[this] val rowCount: Int = _;
    <stable> <accessor> <paramaccessor> def rowCount: Int = StatementInvocationEvent.this.rowCount;
    <paramaccessor> private[this] val jdbcStatement: String = _;
    <stable> <accessor> <paramaccessor> def jdbcStatement: String = StatementInvocationEvent.this.jdbcStatement;
    def <init>(_definitionOrCallSite: StackTraceElement, start: Long, end: Long, rowCount: Int, jdbcStatement: String): org.squeryl.logging.StatementInvocationEvent = {
      StatementInvocationEvent.super.<init>();
      ()
    };
    private[this] val uuid: String = {
      val tmp: java.util.UUID = java.util.UUID.randomUUID();
      java.lang.Long.toHexString(tmp.getMostSignificantBits()).+("-").+(java.lang.Long.toHexString(tmp.getLeastSignificantBits()))
    };
    <stable> <accessor> def uuid: String = StatementInvocationEvent.this.uuid;
    def definitionOrCallSite: String = StatementInvocationEvent.this._definitionOrCallSite.toString()
  };
  abstract trait StatisticsListener extends scala.AnyRef {
    def queryExecuted(se: org.squeryl.logging.StatementInvocationEvent): Unit;
    def resultSetIterationEnded(statementInvocationId: String, iterationEndTime: Long, rowCount: Int, iterationCompleted: Boolean): Unit;
    def updateExecuted(se: org.squeryl.logging.StatementInvocationEvent): Unit;
    def insertExecuted(se: org.squeryl.logging.StatementInvocationEvent): Unit;
    def deleteExecuted(se: org.squeryl.logging.StatementInvocationEvent): Unit
  };
  object StackMarker extends scala.AnyRef {
    def <init>(): org.squeryl.logging.StackMarker.type = {
      StackMarker.super.<init>();
      ()
    };
    def lastSquerylStackFrame[A >: Nothing <: Any](a: => A): A = a
  }
}