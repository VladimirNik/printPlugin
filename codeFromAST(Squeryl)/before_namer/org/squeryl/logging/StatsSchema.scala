package org.squeryl.logging {
  import org.squeryl.KeyedEntity;
  import org.squeryl.Schema;
  import org.squeryl.dsl.CompositeKey2;
  object StatsSchemaTypeMode extends org.squeryl.PrimitiveTypeMode {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  import StatsSchemaTypeMode._;
  class StatementInvocation extends KeyedEntity[String] {
    <paramaccessor> val id: String = _;
    <paramaccessor> val start: Long = _;
    <paramaccessor> val end: Long = _;
    <paramaccessor> val statementHash: Int = _;
    <paramaccessor> val statementHashCollisionNumber: Int = _;
    <paramaccessor> val hostId: Int = _;
    <paramaccessor> val sessionId: Int = _;
    <paramaccessor> val rowCount: Option[Int] = _;
    <paramaccessor> val iterationEndTime: Option[Long] = _;
    def <init>(id: String, start: Long, end: Long, statementHash: Int, statementHashCollisionNumber: Int, hostId: Int, sessionId: Int, rowCount: Option[Int], iterationEndTime: Option[Long]) = {
      super.<init>();
      ()
    };
    def <init>(se: StatementInvocationEvent, _statementHash: Int, _hCollision: Int) = {
      <init>(se.uuid, se.start, se.end, _statementHash, _hCollision, 0, 0, None, None);
      ()
    };
    def <init>() = {
      <init>(null, 0, 0, 0, 0, 0, 0, Some(0), Some(0));
      ()
    };
    def statementId = new CompositeKey2(statementHash, statementHashCollisionNumber);
    def executeTime = end.minus(start)
  };
  object StatementHasher extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    case private class StatementCaseClass4HashGeneration extends scala.Product with scala.Serializable {
      <caseaccessor> <paramaccessor> val sql: String = _;
      <caseaccessor> <paramaccessor> val definitionOrCallSite: String = _;
      def <init>(sql: String, definitionOrCallSite: String) = {
        super.<init>();
        ()
      }
    };
    def hash(sql: String, definitionOrCallSite: String): Int = StatementCaseClass4HashGeneration(sql, definitionOrCallSite).hashCode
  };
  class Statement extends KeyedEntity[CompositeKey2[Int, Int]] {
    <paramaccessor> val sql: String = _;
    <paramaccessor> val definitionOrCallSite: String = _;
    <paramaccessor> val hash: Int = _;
    <paramaccessor> var statementHashCollisionNumber: Int = _;
    def <init>(sql: String, definitionOrCallSite: String, hash: Int, statementHashCollisionNumber: Int) = {
      super.<init>();
      ()
    };
    def <init>(sql: String, definitionOrCallSite: String) = {
      <init>(sql, definitionOrCallSite, StatementHasher.hash(sql, definitionOrCallSite), 0);
      ()
    };
    def <init>() = {
      <init>("", "", 0, 0);
      ()
    };
    def id = new CompositeKey2(hash, statementHashCollisionNumber)
  };
  class StatLine extends scala.AnyRef {
    <paramaccessor> val statement: Statement = _;
    <paramaccessor> val avgExecTime: Double = _;
    <paramaccessor> val invocationCount: Long = _;
    <paramaccessor> val cumulativeExecutionTime: Long = _;
    <paramaccessor> val avgRowCount: Float = _;
    def <init>(statement: Statement, avgExecTime: Double, invocationCount: Long, cumulativeExecutionTime: Long, avgRowCount: Float) = {
      super.<init>();
      ()
    };
    def definitionSite = statement.definitionOrCallSite
  };
  object Measure extends Enumeration {
    def <init>() = {
      super.<init>();
      ()
    };
    type Measure = Value;
    val AvgExecTime = Value;
    val InvocationCount = Value;
    val CumulativeExecutionTime = Value;
    val AvgResultSetSize = Value
  };
  object StatsSchema extends Schema {
    def <init>() = {
      super.<init>();
      ()
    };
    override def drop = super.drop;
    val statements = table[Statement]("Statementz");
    val statementInvocations = table[StatementInvocation];
    def invocationStats = from(statementInvocations)(((si) => groupBy(si.statementHash, si.statementHashCollisionNumber).compute(avg(si.executeTime), count, sum(si.executeTime), nvl(avg(si.rowCount), 0))));
    import Measure._;
    def topRankingStatements(topN: Int, measure: Measure) = from(invocationStats, statements)(((si, s) => where(si.key._1.$eq$eq$eq(s.hash).and(si.key._2.$eq$eq$eq(s.statementHashCollisionNumber))).select(new StatLine(s, si.measures._1.get, si.measures._2, si.measures._3.get, si.measures._4)).orderBy(measure match {
  case AvgExecTime => si.measures._1.desc
  case InvocationCount => si.measures._2.desc
  case CumulativeExecutionTime => si.measures._3.desc
  case AvgResultSetSize => si.measures._4.desc
}))).page(0, topN);
    on(statements)(((s) => declare(s.sql.is(dbType("clob")), s.definitionOrCallSite.is(dbType("varchar(512)")))));
    def recordStatementInvocation(sie: StatementInvocationEvent) = {
      val statementK = _lookupOrCreateStatementAndReturnKey(sie);
      val si = new StatementInvocation(sie, statementK.a1, statementK.a2);
      statementInvocations.insert(si);
      si.id
    };
    def recordEndOfIteration(statementInvocationId: String, iterationEndTime: Long, rowCount: Int, iterationCompleted: Boolean) = update(statementInvocations)(((si) => where(si.id.$eq$eq$eq(statementInvocationId)).set(si.iterationEndTime.$colon$eq(Some(iterationEndTime)), si.rowCount.$colon$eq(Some(rowCount)))));
    private def _lookupOrCreateStatementAndReturnKey(se: StatementInvocationEvent) = {
      val s = new Statement(se.jdbcStatement, se.definitionOrCallSite);
      val storedStatement = statements.lookup(s.id);
      val result = if (storedStatement.$eq$eq(None))
        {
          statements.insert(s);
          s
        }
      else
        {
          val q = from(statements)(((st) => where(st.hash.$eq$eq$eq(s.hash)).select(st).orderBy(st.statementHashCollisionNumber)));
          var lastCollisionNum = -1;
          val mathingStatement = q.find(((st) => {
            lastCollisionNum = st.statementHashCollisionNumber;
            st.$eq$eq(s)
          }));
          if (mathingStatement.$bang$eq(None))
            mathingStatement.get
          else
            {
              s.statementHashCollisionNumber = lastCollisionNum.$plus(1);
              statements.insert(s);
              s
            }
        };
      result.id
    }
  }
}