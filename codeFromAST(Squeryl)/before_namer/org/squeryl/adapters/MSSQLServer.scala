package org.squeryl.adapters {
  import java.sql.SQLException;
  import org.squeryl.internals.{StatementWriter, FieldMetaData, DatabaseAdapter};
  import org.squeryl.dsl.ast._;
  import org.squeryl.Schema;
  class MSSQLServer extends DatabaseAdapter {
    def <init>() = {
      super.<init>();
      ()
    };
    override def isFullOuterJoinSupported = false;
    override def intTypeDeclaration = "int";
    override def stringTypeDeclaration = "varchar";
    override def stringTypeDeclaration(length: Int) = "varchar(".$plus(length).$plus(")");
    override def booleanTypeDeclaration = "bit";
    override def doubleTypeDeclaration = "float";
    override def longTypeDeclaration = "bigint";
    override def bigDecimalTypeDeclaration = "decimal";
    override def bigDecimalTypeDeclaration(precision: Int, scale: Int) = "numeric(".$plus(precision).$plus(",").$plus(scale).$plus(")");
    override def binaryTypeDeclaration = "varbinary(8000)";
    override def dateTypeDeclaration = "date";
    override def floatTypeDeclaration = "real";
    override def timestampTypeDeclaration = "datetime";
    override def writeColumnDeclaration(fmd: FieldMetaData, isPrimaryKey: Boolean, schema: Schema): String = {
      var res = "  ".$plus(quoteIdentifier(fmd.columnName)).$plus(" ").$plus(databaseTypeFor(fmd));
      if (fmd.isOption.unary_$bang)
        res.$plus$eq(" not null")
      else
        ();
      if (isPrimaryKey)
        res.$plus$eq(" primary key")
      else
        ();
      if (supportsAutoIncrementInColumnDeclaration.$amp$amp(fmd.isAutoIncremented))
        res.$plus$eq(" IDENTITY(1,1)")
      else
        ();
      res
    };
    override def isTableDoesNotExistException(e: SQLException): Boolean = e.getErrorCode.$eq$eq(3701);
    override def writeEndOfQueryHint(qen: QueryExpressionElements, sw: StatementWriter) = ();
    override def writeEndOfFromHint(qen: QueryExpressionElements, sw: StatementWriter) = if (qen.isForUpdate)
      {
        sw.write("with(updlock, rowlock)");
        sw.pushPendingNextLine
      }
    else
      ();
    override def writeConcatFunctionCall(fn: FunctionNode, sw: StatementWriter) = sw.writeNodesWithSeparator(fn.args, " + ", false);
    override def writeConcatOperator(left: ExpressionNode, right: ExpressionNode, sw: StatementWriter) = {
      val binaryOpNode = new BinaryOperatorNode(left, right, "+");
      binaryOpNode.doWrite(sw)
    };
    override def writeRegexExpression(left: ExpressionNode, pattern: String, sw: StatementWriter) = throw new UnsupportedOperationException("MSSQL does not yet support a regex function");
    override def writeQuery(qen: QueryExpressionElements, sw: StatementWriter) = if (qen.page.$eq$eq(None))
      super.writeQuery(qen, sw)
    else
      {
        val page = qen.page.get;
        val beginOffset = page._1;
        val pageSize = page._2;
        sw.write("With ___z____ as (");
        sw.writeIndented(super.writeQuery(qen, sw, false, Some(" TOP ".$plus(beginOffset.$plus(pageSize)).$plus(" "))));
        sw.write(")")
      };
    private def _stripPrefix(selectE: String): String = {
      val i = selectE.lastIndexOf(" as ");
      selectE.substring(i.$plus(4), selectE.length)
    };
    override def writePaginatedQueryDeclaration(qen: QueryExpressionElements, sw: StatementWriter): Unit = ();
    override def quoteIdentifier(s: String) = "[".$plus(s).$plus("]")
  }
}