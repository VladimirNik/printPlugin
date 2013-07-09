package org.squeryl.adapters {
  import org.squeryl.Schema;
  import org.squeryl.internals.{StatementWriter, FieldMetaData, DatabaseAdapter};
  import org.squeryl.dsl.ast._;
  import java.sql.SQLException;
  class DerbyAdapter extends DatabaseAdapter {
    def <init>() = {
      super.<init>();
      ()
    };
    override def intTypeDeclaration = "integer";
    override def binaryTypeDeclaration = "blob(1M)";
    override def isFullOuterJoinSupported = false;
    override def writeColumnDeclaration(fmd: FieldMetaData, isPrimaryKey: Boolean, schema: Schema): String = {
      var res = "  ".$plus(quoteIdentifier(fmd.columnName)).$plus(" ").$plus(databaseTypeFor(fmd));
      fmd.defaultValue.foreach(((d) => {
        val v = convertToJdbcValue(d.value.asInstanceOf[AnyRef]);
        if (v.isInstanceOf[String])
          res.$plus$eq(" default \'".$plus(v).$plus("\'"))
        else
          res.$plus$eq(" default ".$plus(v))
      }));
      if (fmd.isOption.unary_$bang)
        res.$plus$eq(" not null")
      else
        ();
      if (isPrimaryKey)
        res.$plus$eq(" primary key")
      else
        ();
      if (supportsAutoIncrementInColumnDeclaration.$amp$amp(fmd.isAutoIncremented))
        res.$plus$eq(" generated always as identity")
      else
        ();
      res
    };
    override def writePaginatedQueryDeclaration(qen: QueryExpressionElements, sw: StatementWriter): Unit = qen.page.foreach(((p) => {
      sw.write("offset ");
      sw.write(p._1.toString);
      sw.write(" rows fetch next ");
      sw.write(p._2.toString);
      sw.write("rows only")
    }));
    override def isTableDoesNotExistException(e: SQLException) = e.getSQLState.$eq$eq("42Y55");
    override def writeRegexExpression(left: ExpressionNode, pattern: String, sw: StatementWriter) = throw new UnsupportedOperationException("Derby does not support a regex scalar function");
    override def quoteIdentifier(s: String) = "\"".$plus(s).$plus("\"")
  }
}