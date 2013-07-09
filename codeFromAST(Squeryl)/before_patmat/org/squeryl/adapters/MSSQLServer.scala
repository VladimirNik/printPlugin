package org.squeryl.adapters {
  import java.sql.SQLException;
  import org.squeryl.internals.{StatementWriter, FieldMetaData, DatabaseAdapter};
  import org.squeryl.dsl.ast._;
  import org.squeryl.Schema;
  class MSSQLServer extends Object with org.squeryl.internals.DatabaseAdapter {
    def <init>(): org.squeryl.adapters.MSSQLServer = {
      MSSQLServer.super.<init>();
      ()
    };
    override def isFullOuterJoinSupported: Boolean = false;
    override def intTypeDeclaration: String = "int";
    override def stringTypeDeclaration: String = "varchar";
    override def stringTypeDeclaration(length: Int): String = "varchar(".+(length).+(")");
    override def booleanTypeDeclaration: String = "bit";
    override def doubleTypeDeclaration: String = "float";
    override def longTypeDeclaration: String = "bigint";
    override def bigDecimalTypeDeclaration: String = "decimal";
    override def bigDecimalTypeDeclaration(precision: Int, scale: Int): String = "numeric(".+(precision).+(",").+(scale).+(")");
    override def binaryTypeDeclaration: String = "varbinary(8000)";
    override def dateTypeDeclaration: String = "date";
    override def floatTypeDeclaration: String = "real";
    override def timestampTypeDeclaration: String = "datetime";
    override def writeColumnDeclaration(fmd: org.squeryl.internals.FieldMetaData, isPrimaryKey: Boolean, schema: org.squeryl.Schema): String = {
      var res: String = "  ".+(MSSQLServer.this.quoteIdentifier(fmd.columnName)).+(" ").+(MSSQLServer.this.databaseTypeFor(fmd));
      if (fmd.isOption.unary_!)
        res = res.+(" not null")
      else
        ();
      if (isPrimaryKey)
        res = res.+(" primary key")
      else
        ();
      if (MSSQLServer.this.supportsAutoIncrementInColumnDeclaration.&&(fmd.isAutoIncremented))
        res = res.+(" IDENTITY(1,1)")
      else
        ();
      res
    };
    override def isTableDoesNotExistException(e: java.sql.SQLException): Boolean = e.getErrorCode().==(3701);
    override def writeEndOfQueryHint(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = ();
    override def writeEndOfFromHint(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = if (qen.isForUpdate)
      {
        sw.write("with(updlock, rowlock)");
        sw.pushPendingNextLine
      }
    else
      ();
    override def writeConcatFunctionCall(fn: org.squeryl.dsl.ast.FunctionNode, sw: org.squeryl.internals.StatementWriter): Unit = sw.writeNodesWithSeparator(fn.args, " + ", false);
    override def writeConcatOperator(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode, sw: org.squeryl.internals.StatementWriter): Unit = {
      val binaryOpNode: org.squeryl.dsl.ast.BinaryOperatorNode = new org.squeryl.dsl.ast.BinaryOperatorNode(left, right, "+", ast.this.BinaryOperatorNode.<init>$default$4);
      binaryOpNode.doWrite(sw)
    };
    override def writeRegexExpression(left: org.squeryl.dsl.ast.ExpressionNode, pattern: String, sw: org.squeryl.internals.StatementWriter): Nothing = throw new scala.`package`.UnsupportedOperationException("MSSQL does not yet support a regex function");
    override def writeQuery(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = if (qen.page.==(scala.None))
      MSSQLServer.super.writeQuery(qen, sw)
    else
      {
        val page: (Int, Int) = qen.page.get;
        val beginOffset: Int = page._1;
        val pageSize: Int = page._2;
        sw.write("With ___z____ as (");
        sw.writeIndented(MSSQLServer.super.writeQuery(qen, sw, false, scala.Some.apply[String](" TOP ".+(beginOffset.+(pageSize)).+(" "))));
        sw.write(")")
      };
    private def _stripPrefix(selectE: String): String = {
      val i: Int = selectE.lastIndexOf(" as ");
      selectE.substring(i.+(4), selectE.length())
    };
    override def writePaginatedQueryDeclaration(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = ();
    override def quoteIdentifier(s: String): String = "[".+(s).+("]")
  }
}