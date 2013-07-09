package org.squeryl.adapters {
  import org.squeryl.{ReferentialAction, Table};
  import java.sql.SQLException;
  import org.squeryl.internals.{StatementWriter, DatabaseAdapter};
  import org.squeryl.dsl.ast.{BinaryOperatorNode, ExpressionNode};
  import org.squeryl.internals.ConstantStatementParam;
  import org.squeryl.InternalFieldMapper;
  class MySQLAdapter extends DatabaseAdapter {
    def <init>() = {
      super.<init>();
      ()
    };
    override def isFullOuterJoinSupported = false;
    override def floatTypeDeclaration = "float";
    override def binaryTypeDeclaration = "blob";
    override def timestampTypeDeclaration = "datetime";
    override def writeForeignKeyDeclaration(foreignKeyTable: Table[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, foreignKeyColumnName: String, primaryKeyTable: Table[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }, primaryKeyColumnName: String, referentialAction1: Option[ReferentialAction], referentialAction2: Option[ReferentialAction], fkId: Int) = {
      val sb = new StringBuilder(256);
      sb.append("alter table ");
      sb.append(foreignKeyTable.prefixedName);
      sb.append(" add constraint ");
      sb.append(foreignKeyConstraintName(foreignKeyTable, fkId));
      sb.append(" foreign key (");
      sb.append(foreignKeyColumnName);
      sb.append(") references ");
      sb.append(primaryKeyTable.prefixedName);
      sb.append("(");
      sb.append(primaryKeyColumnName);
      sb.append(")");
      val f = ((ra: ReferentialAction) => {
        sb.append(" on ");
        sb.append(ra.event);
        sb.append(" ");
        sb.append(ra.action)
      });
      referentialAction1.foreach(f);
      referentialAction2.foreach(f);
      sb.toString
    };
    override def writeDropForeignKeyStatement(foreignKeyTable: Table[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }, fkName: String) = "alter table ".$plus(foreignKeyTable.prefixedName).$plus(" drop foreign key ").$plus(fkName);
    override def isTableDoesNotExistException(e: SQLException) = e.getErrorCode.$eq$eq(1051);
    override def supportsForeignKeyConstraints = false;
    override def writeRegexExpression(left: ExpressionNode, pattern: String, sw: StatementWriter) = {
      sw.write("(");
      left.write(sw);
      sw.write(" regexp ?)");
      sw.addParam(ConstantStatementParam(InternalFieldMapper.stringTEF.createConstant(pattern)))
    };
    override def writeConcatOperator(left: ExpressionNode, right: ExpressionNode, sw: StatementWriter) = {
      sw.write("concat(");
      left.write(sw);
      sw.write(",");
      right.write(sw);
      sw.write(")")
    }
  }
}