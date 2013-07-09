package org.squeryl.adapters {
  import org.squeryl.Schema;
  import org.squeryl.internals.{StatementWriter, FieldMetaData, DatabaseAdapter};
  import org.squeryl.dsl.ast._;
  import java.sql.SQLException;
  class DerbyAdapter extends Object with org.squeryl.internals.DatabaseAdapter {
    def <init>(): org.squeryl.adapters.DerbyAdapter = {
      DerbyAdapter.super.<init>();
      ()
    };
    override def intTypeDeclaration: String = "integer";
    override def binaryTypeDeclaration: String = "blob(1M)";
    override def isFullOuterJoinSupported: Boolean = false;
    override def writeColumnDeclaration(fmd: org.squeryl.internals.FieldMetaData, isPrimaryKey: Boolean, schema: org.squeryl.Schema): String = {
      var res: String = "  ".+(DerbyAdapter.this.quoteIdentifier(fmd.columnName)).+(" ").+(DerbyAdapter.this.databaseTypeFor(fmd));
      fmd.defaultValue.foreach[Unit](((d: org.squeryl.dsl.ast.ConstantTypedExpression[_, _]) => {
        val v: AnyRef = DerbyAdapter.this.convertToJdbcValue(d.value.asInstanceOf[AnyRef]);
        if (v.isInstanceOf[String])
          res = res.+(" default \'".+(v).+("\'"))
        else
          res = res.+(" default ".+(v))
      }));
      if (fmd.isOption.unary_!)
        res = res.+(" not null")
      else
        ();
      if (isPrimaryKey)
        res = res.+(" primary key")
      else
        ();
      if (DerbyAdapter.this.supportsAutoIncrementInColumnDeclaration.&&(fmd.isAutoIncremented))
        res = res.+(" generated always as identity")
      else
        ();
      res
    };
    override def writePaginatedQueryDeclaration(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = qen.page.foreach[Unit](((p: (Int, Int)) => {
      sw.write("offset ");
      sw.write(p._1.toString());
      sw.write(" rows fetch next ");
      sw.write(p._2.toString());
      sw.write("rows only")
    }));
    override def isTableDoesNotExistException(e: java.sql.SQLException): Boolean = e.getSQLState().==("42Y55");
    override def writeRegexExpression(left: org.squeryl.dsl.ast.ExpressionNode, pattern: String, sw: org.squeryl.internals.StatementWriter): Nothing = throw new scala.`package`.UnsupportedOperationException("Derby does not support a regex scalar function");
    override def quoteIdentifier(s: String): String = "\"".+(s).+("\"")
  }
}