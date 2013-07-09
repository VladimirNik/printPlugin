package org.squeryl.logging {
  import org.squeryl.KeyedEntity;
  import org.squeryl.Schema;
  import org.squeryl.dsl.CompositeKey2;
  object StatsSchemaTypeMode extends Object with org.squeryl.PrimitiveTypeMode {
    def <init>(): org.squeryl.logging.StatsSchemaTypeMode.type = {
      StatsSchemaTypeMode.super.<init>();
      ()
    }
  };
  import StatsSchemaTypeMode._;
  class StatementInvocation extends Object with org.squeryl.KeyedEntity[String] {
    <paramaccessor> private[this] val id: String = _;
    <stable> <accessor> <paramaccessor> def id: String = StatementInvocation.this.id;
    <paramaccessor> private[this] val start: Long = _;
    <stable> <accessor> <paramaccessor> def start: Long = StatementInvocation.this.start;
    <paramaccessor> private[this] val end: Long = _;
    <stable> <accessor> <paramaccessor> def end: Long = StatementInvocation.this.end;
    <paramaccessor> private[this] val statementHash: Int = _;
    <stable> <accessor> <paramaccessor> def statementHash: Int = StatementInvocation.this.statementHash;
    <paramaccessor> private[this] val statementHashCollisionNumber: Int = _;
    <stable> <accessor> <paramaccessor> def statementHashCollisionNumber: Int = StatementInvocation.this.statementHashCollisionNumber;
    <paramaccessor> private[this] val hostId: Int = _;
    <stable> <accessor> <paramaccessor> def hostId: Int = StatementInvocation.this.hostId;
    <paramaccessor> private[this] val sessionId: Int = _;
    <stable> <accessor> <paramaccessor> def sessionId: Int = StatementInvocation.this.sessionId;
    <paramaccessor> private[this] val rowCount: Option[Int] = _;
    <stable> <accessor> <paramaccessor> def rowCount: Option[Int] = StatementInvocation.this.rowCount;
    <paramaccessor> private[this] val iterationEndTime: Option[Long] = _;
    <stable> <accessor> <paramaccessor> def iterationEndTime: Option[Long] = StatementInvocation.this.iterationEndTime;
    def <init>(id: String, start: Long, end: Long, statementHash: Int, statementHashCollisionNumber: Int, hostId: Int, sessionId: Int, rowCount: Option[Int], iterationEndTime: Option[Long]): org.squeryl.logging.StatementInvocation = {
      StatementInvocation.super.<init>();
      ()
    };
    def <init>(se: org.squeryl.logging.StatementInvocationEvent, _statementHash: Int, _hCollision: Int): org.squeryl.logging.StatementInvocation = {
      StatementInvocation.this.<init>(se.uuid, se.start, se.end, _statementHash, _hCollision, 0, 0, scala.None, scala.None);
      ()
    };
    def <init>(): org.squeryl.logging.StatementInvocation = {
      StatementInvocation.this.<init>(null, 0L, 0L, 0, 0, 0, 0, scala.Some.apply[Int](0), scala.Some.apply[Long](0L));
      ()
    };
    def statementId: org.squeryl.dsl.CompositeKey2[Int,Int] = new org.squeryl.dsl.CompositeKey2[Int,Int](StatementInvocation.this.statementHash, StatementInvocation.this.statementHashCollisionNumber);
    def executeTime: org.squeryl.dsl.TypedExpression[Long,org.squeryl.dsl.TLong] = StatsSchemaTypeMode.longToTE(StatementInvocation.this.end).minus[org.squeryl.dsl.TLong, org.squeryl.dsl.TLong, Long, Long](StatsSchemaTypeMode.longToTE(StatementInvocation.this.start))(StatsSchemaTypeMode.longTEF)
  };
  object StatementHasher extends scala.AnyRef {
    def <init>(): org.squeryl.logging.StatementHasher.type = {
      StatementHasher.super.<init>();
      ()
    };
    case private class StatementCaseClass4HashGeneration extends AnyRef with Product with Serializable {
      <caseaccessor> <paramaccessor> private[this] val sql: String = _;
      <stable> <caseaccessor> <accessor> <paramaccessor> def sql: String = StatementCaseClass4HashGeneration.this.sql;
      <caseaccessor> <paramaccessor> private[this] val definitionOrCallSite: String = _;
      <stable> <caseaccessor> <accessor> <paramaccessor> def definitionOrCallSite: String = StatementCaseClass4HashGeneration.this.definitionOrCallSite;
      def <init>(sql: String, definitionOrCallSite: String): org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration = {
        StatementCaseClass4HashGeneration.super.<init>();
        ()
      };
      <synthetic> def copy(sql: String = sql, definitionOrCallSite: String = definitionOrCallSite): org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration = new StatementHasher.this.StatementCaseClass4HashGeneration(sql, definitionOrCallSite);
      <synthetic> def copy$default$1: String @scala.annotation.unchecked.uncheckedVariance = StatementCaseClass4HashGeneration.this.sql;
      <synthetic> def copy$default$2: String @scala.annotation.unchecked.uncheckedVariance = StatementCaseClass4HashGeneration.this.definitionOrCallSite;
      override <synthetic> def productPrefix: String = "StatementCaseClass4HashGeneration";
      <synthetic> def productArity: Int = 2;
      <synthetic> def productElement(x$1: Int): Any = x$1 match {
        case 0 => StatementCaseClass4HashGeneration.this.sql
        case 1 => StatementCaseClass4HashGeneration.this.definitionOrCallSite
        case _ => throw new IndexOutOfBoundsException(x$1.toString())
      };
      override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](StatementCaseClass4HashGeneration.this);
      <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration]();
      override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(StatementCaseClass4HashGeneration.this);
      override <synthetic> def toString(): String = ScalaRunTime.this._toString(StatementCaseClass4HashGeneration.this);
      override <synthetic> def equals(x$1: Any): Boolean = StatementCaseClass4HashGeneration.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration].&&({
        <synthetic> val StatementCaseClass4HashGeneration$1: org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration = x$1.asInstanceOf[org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration];
        StatementCaseClass4HashGeneration.this.sql.==(StatementCaseClass4HashGeneration$1.sql).&&(StatementCaseClass4HashGeneration.this.definitionOrCallSite.==(StatementCaseClass4HashGeneration$1.definitionOrCallSite)).&&(StatementCaseClass4HashGeneration$1.canEqual(StatementCaseClass4HashGeneration.this))
      }))
    };
    <synthetic> private object StatementCaseClass4HashGeneration extends scala.runtime.AbstractFunction2[String,String,org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration] with Serializable {
      def <init>(): org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration.type = {
        StatementCaseClass4HashGeneration.super.<init>();
        ()
      };
      final override def toString(): String = "StatementCaseClass4HashGeneration";
      case <synthetic> def apply(sql: String, definitionOrCallSite: String): org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration = new StatementHasher.this.StatementCaseClass4HashGeneration(sql, definitionOrCallSite);
      case <synthetic> def unapply(x$0: org.squeryl.logging.StatementHasher.StatementCaseClass4HashGeneration): Option[(String, String)] = if (x$0.==(null))
        scala.this.None
      else
        Some.apply[(String, String)](Tuple2.apply[String, String](x$0.sql, x$0.definitionOrCallSite));
      <synthetic> private def readResolve(): Object = StatementHasher.this.StatementCaseClass4HashGeneration
    };
    def hash(sql: String, definitionOrCallSite: String): Int = StatementHasher.this.StatementCaseClass4HashGeneration.apply(sql, definitionOrCallSite).hashCode()
  };
  class Statement extends Object with org.squeryl.KeyedEntity[org.squeryl.dsl.CompositeKey2[Int,Int]] {
    <paramaccessor> private[this] val sql: String = _;
    <stable> <accessor> <paramaccessor> def sql: String = Statement.this.sql;
    <paramaccessor> private[this] val definitionOrCallSite: String = _;
    <stable> <accessor> <paramaccessor> def definitionOrCallSite: String = Statement.this.definitionOrCallSite;
    <paramaccessor> private[this] val hash: Int = _;
    <stable> <accessor> <paramaccessor> def hash: Int = Statement.this.hash;
    <paramaccessor> private[this] var statementHashCollisionNumber: Int = _;
    <accessor> <paramaccessor> def statementHashCollisionNumber: Int = Statement.this.statementHashCollisionNumber;
    <accessor> <paramaccessor> def statementHashCollisionNumber_=(x$1: Int): Unit = Statement.this.statementHashCollisionNumber = x$1;
    def <init>(sql: String, definitionOrCallSite: String, hash: Int, statementHashCollisionNumber: Int): org.squeryl.logging.Statement = {
      Statement.super.<init>();
      ()
    };
    def <init>(sql: String, definitionOrCallSite: String): org.squeryl.logging.Statement = {
      Statement.this.<init>(sql, definitionOrCallSite, StatementHasher.hash(sql, definitionOrCallSite), 0);
      ()
    };
    def <init>(): org.squeryl.logging.Statement = {
      Statement.this.<init>("", "", 0, 0);
      ()
    };
    def id: org.squeryl.dsl.CompositeKey2[Int,Int] = new org.squeryl.dsl.CompositeKey2[Int,Int](Statement.this.hash, Statement.this.statementHashCollisionNumber)
  };
  class StatLine extends scala.AnyRef {
    <paramaccessor> private[this] val statement: org.squeryl.logging.Statement = _;
    <stable> <accessor> <paramaccessor> def statement: org.squeryl.logging.Statement = StatLine.this.statement;
    <paramaccessor> private[this] val avgExecTime: Double = _;
    <stable> <accessor> <paramaccessor> def avgExecTime: Double = StatLine.this.avgExecTime;
    <paramaccessor> private[this] val invocationCount: Long = _;
    <stable> <accessor> <paramaccessor> def invocationCount: Long = StatLine.this.invocationCount;
    <paramaccessor> private[this] val cumulativeExecutionTime: Long = _;
    <stable> <accessor> <paramaccessor> def cumulativeExecutionTime: Long = StatLine.this.cumulativeExecutionTime;
    <paramaccessor> private[this] val avgRowCount: Float = _;
    <stable> <accessor> <paramaccessor> def avgRowCount: Float = StatLine.this.avgRowCount;
    def <init>(statement: org.squeryl.logging.Statement, avgExecTime: Double, invocationCount: Long, cumulativeExecutionTime: Long, avgRowCount: Float): org.squeryl.logging.StatLine = {
      StatLine.super.<init>();
      ()
    };
    def definitionSite: String = StatLine.this.statement.definitionOrCallSite
  };
  object Measure extends scala.Enumeration {
    def <init>(): org.squeryl.logging.Measure.type = {
      Measure.super.<init>();
      ()
    };
    type Measure = org.squeryl.logging.Measure.Value;
    private[this] val AvgExecTime: org.squeryl.logging.Measure.Value = Measure.this.Value;
    <stable> <accessor> def AvgExecTime: org.squeryl.logging.Measure.Value = Measure.this.AvgExecTime;
    private[this] val InvocationCount: org.squeryl.logging.Measure.Value = Measure.this.Value;
    <stable> <accessor> def InvocationCount: org.squeryl.logging.Measure.Value = Measure.this.InvocationCount;
    private[this] val CumulativeExecutionTime: org.squeryl.logging.Measure.Value = Measure.this.Value;
    <stable> <accessor> def CumulativeExecutionTime: org.squeryl.logging.Measure.Value = Measure.this.CumulativeExecutionTime;
    private[this] val AvgResultSetSize: org.squeryl.logging.Measure.Value = Measure.this.Value;
    <stable> <accessor> def AvgResultSetSize: org.squeryl.logging.Measure.Value = Measure.this.AvgResultSetSize
  };
  object StatsSchema extends org.squeryl.Schema {
    def <init>(): org.squeryl.logging.StatsSchema.type = {
      StatsSchema.super.<init>()(StatsSchemaTypeMode.thisFieldMapper);
      ()
    };
    override def drop: Unit = StatsSchema.super.drop;
    private[this] val statements: org.squeryl.Table[org.squeryl.logging.Statement] = StatsSchema.this.table[org.squeryl.logging.Statement]("Statementz")(reflect.this.ManifestFactory.classType[org.squeryl.logging.Statement](classOf[org.squeryl.logging.Statement]), StatsSchemaTypeMode.kedForKeyedEntities[org.squeryl.logging.Statement, org.squeryl.dsl.CompositeKey2[Int,Int]](scala.this.Predef.conforms[org.squeryl.logging.Statement], reflect.this.ManifestFactory.classType[org.squeryl.logging.Statement](classOf[org.squeryl.logging.Statement])));
    <stable> <accessor> def statements: org.squeryl.Table[org.squeryl.logging.Statement] = StatsSchema.this.statements;
    private[this] val statementInvocations: org.squeryl.Table[org.squeryl.logging.StatementInvocation] = StatsSchema.this.table[org.squeryl.logging.StatementInvocation]()(reflect.this.ManifestFactory.classType[org.squeryl.logging.StatementInvocation](classOf[org.squeryl.logging.StatementInvocation]), StatsSchemaTypeMode.kedForKeyedEntities[org.squeryl.logging.StatementInvocation, String](scala.this.Predef.conforms[org.squeryl.logging.StatementInvocation], reflect.this.ManifestFactory.classType[org.squeryl.logging.StatementInvocation](classOf[org.squeryl.logging.StatementInvocation])));
    <stable> <accessor> def statementInvocations: org.squeryl.Table[org.squeryl.logging.StatementInvocation] = StatsSchema.this.statementInvocations;
    def invocationStats: org.squeryl.Query[org.squeryl.dsl.GroupWithMeasures[Product2[Int,Int],Product4[Option[Double],Long,Option[Long],Float]]] = StatsSchemaTypeMode.from[org.squeryl.logging.StatementInvocation, org.squeryl.dsl.GroupWithMeasures[Product2[Int,Int],Product4[Option[Double],Long,Option[Long],Float]]](StatsSchema.this.statementInvocations)(((si: org.squeryl.logging.StatementInvocation) => StatsSchemaTypeMode.groupBy[Int, Int](StatsSchemaTypeMode.intToTE(si.statementHash), StatsSchemaTypeMode.intToTE(si.statementHashCollisionNumber)).compute[Option[Double], Long, Option[Long], Float](StatsSchemaTypeMode.avg[org.squeryl.dsl.TOptionDouble, org.squeryl.dsl.TLong, Long, Option[Double]](si.executeTime)(StatsSchemaTypeMode.optionDoubleTEF), StatsSchemaTypeMode.count, StatsSchemaTypeMode.sum[org.squeryl.dsl.TOptionLong, org.squeryl.dsl.TLong, Long, Option[Long]](si.executeTime)(StatsSchemaTypeMode.optionLongTEF), StatsSchemaTypeMode.nvl[org.squeryl.dsl.TFloat, org.squeryl.dsl.TOptionFloat, org.squeryl.dsl.TOptionFloat, org.squeryl.dsl.TInt, Option[Float], Int, Float](StatsSchemaTypeMode.avg[org.squeryl.dsl.TOptionFloat, org.squeryl.dsl.TOptionInt, Option[Int], Option[Float]](StatsSchemaTypeMode.optionIntToTE(si.rowCount))(StatsSchemaTypeMode.optionFloatTEF), StatsSchemaTypeMode.intToTE(0))(StatsSchemaTypeMode.optionFloatTEF))));
    import Measure._;
    def topRankingStatements(topN: Int, measure: org.squeryl.logging.Measure.Measure): org.squeryl.Query[org.squeryl.logging.StatLine] = StatsSchemaTypeMode.from[org.squeryl.dsl.GroupWithMeasures[Product2[Int,Int],Product4[Option[Double],Long,Option[Long],Float]], org.squeryl.logging.Statement, org.squeryl.logging.StatLine](StatsSchema.this.invocationStats, StatsSchema.this.statements)(((si: org.squeryl.dsl.GroupWithMeasures[Product2[Int,Int],Product4[Option[Double],Long,Option[Long],Float]], s: org.squeryl.logging.Statement) => StatsSchemaTypeMode.where(StatsSchemaTypeMode.intToTE(si.key._1).===[Int, org.squeryl.dsl.TInt](StatsSchemaTypeMode.intToTE(s.hash))(StatsSchemaTypeMode.numericComparisonEvidence).and(StatsSchemaTypeMode.intToTE(si.key._2).===[Int, org.squeryl.dsl.TInt](StatsSchemaTypeMode.intToTE(s.statementHashCollisionNumber))(StatsSchemaTypeMode.numericComparisonEvidence))).select[org.squeryl.logging.StatLine](new StatLine(s, si.measures._1.get, si.measures._2, si.measures._3.get, si.measures._4)).orderBy(StatsSchemaTypeMode.orderByArg2OrderByExpression(measure match {
  case Measure.AvgExecTime => StatsSchemaTypeMode.typedExpression2OrderByArg[Option[Double]](si.measures._1)({
  ((f: Option[Double]) => StatsSchemaTypeMode.optionDoubleToTE(f))
}).desc
  case Measure.InvocationCount => StatsSchemaTypeMode.typedExpression2OrderByArg[Long](si.measures._2)({
  ((f: Long) => StatsSchemaTypeMode.longToTE(f))
}).desc
  case Measure.CumulativeExecutionTime => StatsSchemaTypeMode.typedExpression2OrderByArg[Option[Long]](si.measures._3)({
  ((f: Option[Long]) => StatsSchemaTypeMode.optionLongToTE(f))
}).desc
  case Measure.AvgResultSetSize => StatsSchemaTypeMode.typedExpression2OrderByArg[Float](si.measures._4)({
  ((f: Float) => StatsSchemaTypeMode.floatToTE(f))
}).desc
})))).page(0, topN);
    StatsSchema.this.on[org.squeryl.logging.Statement](StatsSchema.this.statements)(((s: org.squeryl.logging.Statement) => StatsSchema.this.declare[Nothing](StatsSchemaTypeMode.stringToTE(s.sql).is(StatsSchema.this.dbType("clob"))(StatsSchema.this.thisSchema), StatsSchemaTypeMode.stringToTE(s.definitionOrCallSite).is(StatsSchema.this.dbType("varchar(512)"))(StatsSchema.this.thisSchema))));
    def recordStatementInvocation(sie: org.squeryl.logging.StatementInvocationEvent): String = {
      val statementK: org.squeryl.dsl.CompositeKey2[Int,Int] = StatsSchema.this._lookupOrCreateStatementAndReturnKey(sie);
      val si: org.squeryl.logging.StatementInvocation = new StatementInvocation(sie, statementK.a1, statementK.a2);
      StatsSchema.this.statementInvocations.insert(si);
      si.id
    };
    def recordEndOfIteration(statementInvocationId: String, iterationEndTime: Long, rowCount: Int, iterationCompleted: Boolean): Int = StatsSchemaTypeMode.update[org.squeryl.logging.StatementInvocation](StatsSchema.this.statementInvocations)(((si: org.squeryl.logging.StatementInvocation) => StatsSchemaTypeMode.where(StatsSchemaTypeMode.stringToTE(si.id).===[String, org.squeryl.dsl.TString](StatsSchemaTypeMode.stringToTE(statementInvocationId))(StatsSchemaTypeMode.stringComparisonEvidence)).set(StatsSchemaTypeMode.optionLongToTE(si.iterationEndTime).:=[Some[Long]](scala.Some.apply[Long](iterationEndTime))({
      ((f: Option[Long]) => StatsSchemaTypeMode.optionLongToTE(f))
    }), StatsSchemaTypeMode.optionIntToTE(si.rowCount).:=[Some[Int]](scala.Some.apply[Int](rowCount))({
      ((f: Option[Int]) => StatsSchemaTypeMode.optionIntToTE(f))
    }))(Predef.this.=:=.tpEquals[org.squeryl.dsl.fsm.Conditioned])));
    private def _lookupOrCreateStatementAndReturnKey(se: org.squeryl.logging.StatementInvocationEvent): org.squeryl.dsl.CompositeKey2[Int,Int] = {
      val s: org.squeryl.logging.Statement = new Statement(se.jdbcStatement, se.definitionOrCallSite);
      val storedStatement: Option[org.squeryl.logging.Statement] = StatsSchema.this.statements.lookup[org.squeryl.dsl.CompositeKey2[Int,Int]](s.id)(StatsSchemaTypeMode.kedForKeyedEntities[org.squeryl.logging.Statement, org.squeryl.dsl.CompositeKey2[Int,Int]](scala.this.Predef.conforms[org.squeryl.logging.Statement], reflect.this.ManifestFactory.classType[org.squeryl.logging.Statement](classOf[org.squeryl.logging.Statement])), StatsSchemaTypeMode.__thisDsl);
      val result: org.squeryl.logging.Statement = if (storedStatement.==(scala.None))
        {
          StatsSchema.this.statements.insert(s);
          s
        }
      else
        {
          val q: org.squeryl.Query[org.squeryl.logging.Statement] = StatsSchemaTypeMode.from[org.squeryl.logging.Statement, org.squeryl.logging.Statement](StatsSchema.this.statements)(((st: org.squeryl.logging.Statement) => StatsSchemaTypeMode.where(StatsSchemaTypeMode.intToTE(st.hash).===[Int, org.squeryl.dsl.TInt](StatsSchemaTypeMode.intToTE(s.hash))(StatsSchemaTypeMode.numericComparisonEvidence)).select[org.squeryl.logging.Statement](st).orderBy(StatsSchemaTypeMode.intToTE(st.statementHashCollisionNumber))));
          var lastCollisionNum: Int = -1;
          val mathingStatement: Option[org.squeryl.logging.Statement] = StatsSchemaTypeMode.queryToIterable[org.squeryl.logging.Statement](q).find(((st: org.squeryl.logging.Statement) => {
            lastCollisionNum = st.statementHashCollisionNumber;
            st.==(s)
          }));
          if (mathingStatement.!=(scala.None))
            mathingStatement.get
          else
            {
              s.statementHashCollisionNumber_=(lastCollisionNum.+(1));
              StatsSchema.this.statements.insert(s);
              s
            }
        };
      result.id
    }
  }
}