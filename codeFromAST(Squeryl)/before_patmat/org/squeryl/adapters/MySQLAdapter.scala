package org.squeryl.adapters {
  import org.squeryl.{ReferentialAction, Table};
  import java.sql.SQLException;
  import org.squeryl.internals.{StatementWriter, DatabaseAdapter};
  import org.squeryl.dsl.ast.{BinaryOperatorNode, ExpressionNode};
  import org.squeryl.internals.ConstantStatementParam;
  import org.squeryl.InternalFieldMapper;
  class MySQLAdapter extends Object with org.squeryl.internals.DatabaseAdapter {
    def <init>(): org.squeryl.adapters.MySQLAdapter = {
      MySQLAdapter.super.<init>();
      ()
    };
    override def isFullOuterJoinSupported: Boolean = false;
    override def floatTypeDeclaration: String = "float";
    override def binaryTypeDeclaration: String = "blob";
    override def timestampTypeDeclaration: String = "datetime";
    override def writeForeignKeyDeclaration(foreignKeyTable: org.squeryl.Table[_], foreignKeyColumnName: String, primaryKeyTable: org.squeryl.Table[_], primaryKeyColumnName: String, referentialAction1: Option[org.squeryl.ReferentialAction], referentialAction2: Option[org.squeryl.ReferentialAction], fkId: Int): String = {
      val sb: StringBuilder = new scala.`package`.StringBuilder(256);
      sb.append("alter table ");
      sb.append(foreignKeyTable.prefixedName);
      sb.append(" add constraint ");
      sb.append(MySQLAdapter.this.foreignKeyConstraintName(foreignKeyTable, fkId));
      sb.append(" foreign key (");
      sb.append(foreignKeyColumnName);
      sb.append(") references ");
      sb.append(primaryKeyTable.prefixedName);
      sb.append("(");
      sb.append(primaryKeyColumnName);
      sb.append(")");
      val f: org.squeryl.ReferentialAction => StringBuilder = ((ra: org.squeryl.ReferentialAction) => {
        sb.append(" on ");
        sb.append(ra.event);
        sb.append(" ");
        sb.append(ra.action)
      });
      referentialAction1.foreach[StringBuilder](f);
      referentialAction2.foreach[StringBuilder](f);
      sb.toString()
    };
    override def writeDropForeignKeyStatement(foreignKeyTable: org.squeryl.Table[_], fkName: String): String = "alter table ".+(foreignKeyTable.prefixedName).+(" drop foreign key ").+(fkName);
    override def isTableDoesNotExistException(e: java.sql.SQLException): Boolean = e.getErrorCode().==(1051);
    override def supportsForeignKeyConstraints: Boolean = false;
    override def writeRegexExpression(left: org.squeryl.dsl.ast.ExpressionNode, pattern: String, sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write("(");
      left.write(sw);
      sw.write(" regexp ?)");
      sw.addParam(org.squeryl.internals.ConstantStatementParam.apply(org.squeryl.InternalFieldMapper.stringTEF.createConstant(pattern)))
    };
    override def writeConcatOperator(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode, sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write("concat(");
      left.write(sw);
      sw.write(",");
      right.write(sw);
      sw.write(")")
    }
  }
}