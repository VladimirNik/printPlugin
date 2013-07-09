package org.squeryl.logging {
  import org.squeryl.Session;
  import org.squeryl.adapters.H2Adapter;
  import org.squeryl.logging.StatsSchemaTypeMode._;
  object UsageProfileConsolidator extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    def main(args: Array[String]): Unit = if (args.length.$less(2))
      printUsage
    else
      {
        <synthetic> private[this] val x$2 = args.map(((x$1) => new java.io.File(x$1))).splitAt(1): @scala.unchecked match {
          case scala.Tuple2((dst @ _), (src @ _)) => scala.Tuple2(dst, src)
        };
        val dst = x$2._1;
        val src = x$2._2;
        val notExists = src.filterNot(((x$3) => x$3.exists));
        if (notExists.size.$greater(0))
          org.squeryl.internals.Utils.throwError("Files don\'t exist : \n".$plus(notExists.mkString(",\n")))
        else
          ();
        Class.forName("org.h2.Driver");
        val dstDb = new Session(java.sql.DriverManager.getConnection("jdbc:h2:".$plus(dst.head.getAbsolutePath), "sa", ""), new H2Adapter());
        using(dstDb)(src.foreach(((src_i) => {
          val srcDb_i = new Session(java.sql.DriverManager.getConnection("jdbc:h2:".$plus(src_i.getAbsolutePath), "sa", ""), new H2Adapter());
          <synthetic> private[this] val x$4 = using(srcDb_i)(scala.Tuple2(StatsSchema.statementInvocations.allRows, StatsSchema.statements.allRows)): @scala.unchecked match {
            case scala.Tuple2((invocations @ _), (statements @ _)) => scala.Tuple2(invocations, statements)
          };
          val invocations = x$4._1;
          val statements = x$4._2;
          val stmtsToInsert = statements.filter(((stmt) => StatsSchema.statements.lookup(stmt.id).$eq$eq(None)));
          StatsSchema.statements.insert(stmtsToInsert);
          StatsSchema.statementInvocations.insert(invocations)
        })))
      };
    def printUsage = {
      println("Usage : ");
      println("java org.squeryl.logging.UsageProfileConsolidator <h2FileForConsolidatedStatsProfile> <list of h2 files to consolidate>")
    }
  }
}