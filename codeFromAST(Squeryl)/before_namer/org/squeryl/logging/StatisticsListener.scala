package org.squeryl.logging {
  import org.squeryl.dsl.ast.ExpressionNode;
  import org.squeryl.internals.Utils;
  import org.squeryl.dsl.CompositeKey2;
  import org.squeryl.{Schema, KeyedEntity};
  class StatementInvocationEvent extends scala.AnyRef {
    <paramaccessor> private[this] val _definitionOrCallSite: StackTraceElement = _;
    <paramaccessor> val start: Long = _;
    <paramaccessor> val end: Long = _;
    <paramaccessor> val rowCount: Int = _;
    <paramaccessor> val jdbcStatement: String = _;
    def <init>(_definitionOrCallSite: StackTraceElement, start: Long, end: Long, rowCount: Int, jdbcStatement: String) = {
      super.<init>();
      ()
    };
    val uuid = {
      val tmp = java.util.UUID.randomUUID;
      java.lang.Long.toHexString(tmp.getMostSignificantBits).$plus("-").$plus(java.lang.Long.toHexString(tmp.getLeastSignificantBits))
    };
    def definitionOrCallSite = _definitionOrCallSite.toString
  };
  abstract trait StatisticsListener extends scala.AnyRef {
    def queryExecuted(se: StatementInvocationEvent): Unit;
    def resultSetIterationEnded(statementInvocationId: String, iterationEndTime: Long, rowCount: Int, iterationCompleted: Boolean): Unit;
    def updateExecuted(se: StatementInvocationEvent): Unit;
    def insertExecuted(se: StatementInvocationEvent): Unit;
    def deleteExecuted(se: StatementInvocationEvent): Unit
  };
  object StackMarker extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    def lastSquerylStackFrame[A >: _root_.scala.Nothing <: _root_.scala.Any](a: _root_.scala.<byname>[A]) = a
  }
}