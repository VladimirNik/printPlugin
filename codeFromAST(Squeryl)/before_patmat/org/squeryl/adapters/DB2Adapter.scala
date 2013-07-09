package org.squeryl.adapters {
  import org.squeryl.internals.{StatementWriter, DatabaseAdapter};
  import org.squeryl.dsl.ast.ConstantTypedExpression;
  import org.squeryl.{Session, Table};
  import java.sql.SQLException;
  import org.squeryl.dsl.ast._;
  class DB2Adapter extends Object with org.squeryl.internals.DatabaseAdapter {
    def <init>(): org.squeryl.adapters.DB2Adapter = {
      DB2Adapter.super.<init>();
      ()
    };
    override def booleanTypeDeclaration: String = "char(1)";
    override def timestampTypeDeclaration: String = "timestamp";
    override def binaryTypeDeclaration: String = "blob";
    override def supportsAutoIncrementInColumnDeclaration: Boolean = false;
    override def postCreateTable(t: org.squeryl.Table[_], printSinkWhenWriteOnlyMode: Option[String => Unit]): Unit = {
      val sw: org.squeryl.internals.StatementWriter = new org.squeryl.internals.StatementWriter(false, this);
      sw.write("create sequence ", DB2Adapter.this.sequenceName(t), " start with 1 increment by 1 nomaxvalue");
      if (printSinkWhenWriteOnlyMode.==(scala.None))
        {
          val st: java.sql.Statement = org.squeryl.Session.currentSession.connection.createStatement();
          {
            st.execute(sw.statement);
            ()
          }
        }
      else
        printSinkWhenWriteOnlyMode.get.apply(sw.statement.+(";"))
    };
    override def postDropTable(t: org.squeryl.Table[_]): Unit = DB2Adapter.this.execFailSafeExecute(DB2Adapter.this.string2StatementWriter("drop sequence ".+(DB2Adapter.this.sequenceName(t))), ((e: java.sql.SQLException) => e.getErrorCode().==(-204)));
    def sequenceName(t: org.squeryl.Table[_]): String = t.prefixedPrefixedName("s_");
    override def writeInsert[T >: Nothing <: Any](o: T, t: org.squeryl.Table[T], sw: org.squeryl.internals.StatementWriter): Unit = {
      val o_: AnyRef = o.asInstanceOf[AnyRef];
      val autoIncPK: Option[org.squeryl.internals.FieldMetaData] = t.posoMetaData.fieldsMetaData.find(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isAutoIncremented));
      if (autoIncPK.==(scala.None))
        {
          DB2Adapter.super.writeInsert[T](o, t, sw);
          return ()
        }
      else
        ();
      val f: Iterable[org.squeryl.internals.FieldMetaData] = DB2Adapter.this.getInsertableFields(t.posoMetaData.fieldsMetaData);
      val colNames: List[org.squeryl.internals.FieldMetaData] = {
        <synthetic> val x$1: List[org.squeryl.internals.FieldMetaData] = immutable.this.List.apply[org.squeryl.internals.FieldMetaData](autoIncPK.get);
        f.toList.:::[org.squeryl.internals.FieldMetaData](x$1)
      };
      val colVals: List[String] = {
        <synthetic> val x$2: List[String] = immutable.this.List.apply[String]("next value for ".+(DB2Adapter.this.sequenceName(t)));
        f.map[String, Iterable[String]](((fmd: org.squeryl.internals.FieldMetaData) => DB2Adapter.this.writeValue(o_, fmd, sw)))(collection.this.Iterable.canBuildFrom[String]).toList.:::[String](x$2)
      };
      sw.write("insert into ");
      sw.write(t.prefixedName);
      sw.write(" (");
      sw.write(colNames.map[String, List[String]](((fmd: org.squeryl.internals.FieldMetaData) => fmd.columnName))(immutable.this.List.canBuildFrom[String]).mkString(", "));
      sw.write(") values ");
      sw.write(colVals.mkString("(", ",", ")"))
    };
    override def writeConcatFunctionCall(fn: org.squeryl.dsl.ast.FunctionNode, sw: org.squeryl.internals.StatementWriter): Unit = sw.writeNodesWithSeparator(fn.args, " || ", false);
    override def isTableDoesNotExistException(e: java.sql.SQLException): Boolean = e.getErrorCode().==(-204);
    override def writePaginatedQueryDeclaration(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = ();
    override def writeQuery(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = if (qen.page.==(scala.None))
      DB2Adapter.super.writeQuery(qen, sw)
    else
      {
        sw.write("select sq____1.* from (");
        sw.nextLine;
        sw.writeIndented({
          sw.write("select sq____0.*, row_number() over() as rn____");
          sw.nextLine;
          sw.write("from");
          sw.nextLine;
          sw.writeIndented({
            sw.write("(");
            DB2Adapter.super.writeQuery(qen, sw);
            sw.write(") sq____0")
          })
        });
        sw.nextLine;
        sw.write(") sq____1");
        sw.nextLine;
        sw.write("where");
        sw.nextLine;
        sw.writeIndented({
          sw.write("rn____ between ");
          val page: (Int, Int) = qen.page.get;
          val beginOffset: Int = page._1.+(1);
          val endOffset: Int = page._2.+(beginOffset).-(1);
          sw.write(beginOffset.toString());
          sw.write(" and ");
          sw.write(endOffset.toString())
        })
      };
    override def writeConcatOperator(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode, sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write("(");
      DB2Adapter.this._writeConcatOperand(left, sw);
      sw.write(" ");
      sw.write("||");
      sw.write(" ");
      DB2Adapter.this._writeConcatOperand(right, sw);
      sw.write(")")
    };
    private def _writeConcatOperand(e: org.squeryl.dsl.ast.ExpressionNode, sw: org.squeryl.internals.StatementWriter): Unit = if (e.isInstanceOf[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]])
      {
        val c: org.squeryl.dsl.ast.ConstantTypedExpression[Any,Any] = e.asInstanceOf[org.squeryl.dsl.ast.ConstantTypedExpression[Any,Any]];
        sw.write("cast(");
        e.write(sw);
        sw.write(" as varchar(");
        sw.write(c.value.toString().length().toString());
        sw.write("))")
      }
    else
      e.write(sw);
    override def writeRegexExpression(left: org.squeryl.dsl.ast.ExpressionNode, pattern: String, sw: org.squeryl.internals.StatementWriter): Nothing = throw new scala.`package`.UnsupportedOperationException("DB2 does not support a regex scalar function")
  }
}