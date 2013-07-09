package org.squeryl.logging {
  import org.squeryl.Session;
  import org.squeryl.adapters.H2Adapter;
  import org.squeryl.logging.StatsSchemaTypeMode._;
  object UsageProfileConsolidator extends scala.AnyRef {
    def <init>(): org.squeryl.logging.UsageProfileConsolidator.type = {
      UsageProfileConsolidator.super.<init>();
      ()
    };
    def main(args: Array[String]): Unit = if (args.length.<(2))
      UsageProfileConsolidator.this.printUsage
    else
      {
        <synthetic> private[this] val x$2: (Array[java.io.File], Array[java.io.File]) = (scala.this.Predef.refArrayOps[java.io.File](scala.this.Predef.refArrayOps[String](args).map[java.io.File, Array[java.io.File]](((x$1: String) => new java.io.File(x$1)))(scala.this.Array.canBuildFrom[java.io.File](ClassTag.apply[java.io.File](classOf[java.io.File])))).splitAt(1): (Array[java.io.File], Array[java.io.File]) @unchecked) match {
          case (_1: Array[java.io.File], _2: Array[java.io.File])(Array[java.io.File], Array[java.io.File])((dst @ _), (src @ _)) => scala.Tuple2.apply[Array[java.io.File], Array[java.io.File]](dst, src)
        };
        val dst: Array[java.io.File] = x$2._1;
        val src: Array[java.io.File] = x$2._2;
        val notExists: Array[java.io.File] = scala.this.Predef.refArrayOps[java.io.File](src).filterNot(((x$3: java.io.File) => x$3.exists()));
        if (scala.this.Predef.refArrayOps[java.io.File](notExists).size.>(0))
          org.squeryl.internals.Utils.throwError("Files don\'t exist : \n".+(scala.this.Predef.refArrayOps[java.io.File](notExists).mkString(",\n")))
        else
          ();
        java.this.lang.Class.forName("org.h2.Driver");
        val dstDb: org.squeryl.Session = new org.squeryl.Session(java.sql.DriverManager.getConnection("jdbc:h2:".+(scala.this.Predef.refArrayOps[java.io.File](dst).head.getAbsolutePath()), "sa", ""), new org.squeryl.adapters.H2Adapter(), squeryl.this.Session.<init>$default$3);
        org.squeryl.logging.StatsSchemaTypeMode.using[Unit](dstDb)(scala.this.Predef.refArrayOps[java.io.File](src).foreach[Unit](((src_i: java.io.File) => {
          val srcDb_i: org.squeryl.Session = new org.squeryl.Session(java.sql.DriverManager.getConnection("jdbc:h2:".+(src_i.getAbsolutePath()), "sa", ""), new org.squeryl.adapters.H2Adapter(), squeryl.this.Session.<init>$default$3);
          <synthetic> private[this] val x$4: (Iterable[org.squeryl.logging.StatementInvocation], Iterable[org.squeryl.logging.Statement]) = (org.squeryl.logging.StatsSchemaTypeMode.using[(Iterable[org.squeryl.logging.StatementInvocation], Iterable[org.squeryl.logging.Statement])](srcDb_i)(scala.Tuple2.apply[Iterable[org.squeryl.logging.StatementInvocation], Iterable[org.squeryl.logging.Statement]](StatsSchema.statementInvocations.allRows(org.squeryl.logging.StatsSchemaTypeMode.__thisDsl), StatsSchema.statements.allRows(org.squeryl.logging.StatsSchemaTypeMode.__thisDsl))): (Iterable[org.squeryl.logging.StatementInvocation], Iterable[org.squeryl.logging.Statement]) @unchecked) match {
            case (_1: Iterable[org.squeryl.logging.StatementInvocation], _2: Iterable[org.squeryl.logging.Statement])(Iterable[org.squeryl.logging.StatementInvocation], Iterable[org.squeryl.logging.Statement])((invocations @ _), (statements @ _)) => scala.Tuple2.apply[Iterable[org.squeryl.logging.StatementInvocation], Iterable[org.squeryl.logging.Statement]](invocations, statements)
          };
          val invocations: Iterable[org.squeryl.logging.StatementInvocation] = x$4._1;
          val statements: Iterable[org.squeryl.logging.Statement] = x$4._2;
          val stmtsToInsert: Iterable[org.squeryl.logging.Statement] = statements.filter(((stmt: org.squeryl.logging.Statement) => StatsSchema.statements.lookup[org.squeryl.dsl.CompositeKey2[Int,Int]](stmt.id)(org.squeryl.logging.StatsSchemaTypeMode.kedForKeyedEntities[org.squeryl.logging.Statement, org.squeryl.dsl.CompositeKey2[Int,Int]](scala.this.Predef.conforms[org.squeryl.logging.Statement], reflect.this.ManifestFactory.classType[org.squeryl.logging.Statement](classOf[org.squeryl.logging.Statement])), org.squeryl.logging.StatsSchemaTypeMode.__thisDsl).==(scala.None)));
          StatsSchema.statements.insert(stmtsToInsert);
          StatsSchema.statementInvocations.insert(invocations)
        })))
      };
    def printUsage: Unit = {
      scala.this.Predef.println("Usage : ");
      scala.this.Predef.println("java org.squeryl.logging.UsageProfileConsolidator <h2FileForConsolidatedStatsProfile> <list of h2 files to consolidate>")
    }
  }
}