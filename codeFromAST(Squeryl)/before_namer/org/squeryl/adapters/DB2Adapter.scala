package org.squeryl.adapters {
  import org.squeryl.internals.{StatementWriter, DatabaseAdapter};
  import org.squeryl.dsl.ast.ConstantTypedExpression;
  import org.squeryl.{Session, Table};
  import java.sql.SQLException;
  import org.squeryl.dsl.ast._;
  class DB2Adapter extends DatabaseAdapter {
    def <init>() = {
      super.<init>();
      ()
    };
    override def booleanTypeDeclaration = "char(1)";
    override def timestampTypeDeclaration = "timestamp";
    override def binaryTypeDeclaration = "blob";
    override def supportsAutoIncrementInColumnDeclaration: Boolean = false;
    override def postCreateTable(t: Table[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, printSinkWhenWriteOnlyMode: Option[_root_.scala.Function1[String, Unit]]) = {
      val sw = new StatementWriter(false, this);
      sw.write("create sequence ", sequenceName(t), " start with 1 increment by 1 nomaxvalue");
      if (printSinkWhenWriteOnlyMode.$eq$eq(None))
        {
          val st = Session.currentSession.connection.createStatement;
          st.execute(sw.statement)
        }
      else
        printSinkWhenWriteOnlyMode.get.apply(sw.statement.$plus(";"))
    };
    override def postDropTable(t: Table[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = execFailSafeExecute("drop sequence ".$plus(sequenceName(t)), ((e) => e.getErrorCode.$eq$eq(-204)));
    def sequenceName(t: Table[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = t.prefixedPrefixedName("s_");
    override def writeInsert[T >: _root_.scala.Nothing <: _root_.scala.Any](o: T, t: Table[T], sw: StatementWriter): Unit = {
      val o_ = o.asInstanceOf[AnyRef];
      val autoIncPK = t.posoMetaData.fieldsMetaData.find(((fmd) => fmd.isAutoIncremented));
      if (autoIncPK.$eq$eq(None))
        {
          super.writeInsert(o, t, sw);
          return ()
        }
      else
        ();
      val f = getInsertableFields(t.posoMetaData.fieldsMetaData);
      val colNames = {
        <synthetic> val x$1 = List(autoIncPK.get);
        f.toList.$colon$colon$colon(x$1)
      };
      val colVals = {
        <synthetic> val x$2 = List("next value for ".$plus(sequenceName(t)));
        f.map(((fmd) => writeValue(o_, fmd, sw))).toList.$colon$colon$colon(x$2)
      };
      sw.write("insert into ");
      sw.write(t.prefixedName);
      sw.write(" (");
      sw.write(colNames.map(((fmd) => fmd.columnName)).mkString(", "));
      sw.write(") values ");
      sw.write(colVals.mkString("(", ",", ")"))
    };
    override def writeConcatFunctionCall(fn: FunctionNode, sw: StatementWriter) = sw.writeNodesWithSeparator(fn.args, " || ", false);
    override def isTableDoesNotExistException(e: SQLException) = e.getErrorCode.$eq$eq(-204);
    override def writePaginatedQueryDeclaration(qen: QueryExpressionElements, sw: StatementWriter) = ();
    override def writeQuery(qen: QueryExpressionElements, sw: StatementWriter) = if (qen.page.$eq$eq(None))
      super.writeQuery(qen, sw)
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
            super.writeQuery(qen, sw);
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
          val page = qen.page.get;
          val beginOffset = page._1.$plus(1);
          val endOffset = page._2.$plus(beginOffset).$minus(1);
          sw.write(beginOffset.toString);
          sw.write(" and ");
          sw.write(endOffset.toString)
        })
      };
    override def writeConcatOperator(left: ExpressionNode, right: ExpressionNode, sw: StatementWriter) = {
      sw.write("(");
      _writeConcatOperand(left, sw);
      sw.write(" ");
      sw.write("||");
      sw.write(" ");
      _writeConcatOperand(right, sw);
      sw.write(")")
    };
    private def _writeConcatOperand(e: ExpressionNode, sw: StatementWriter) = if (e.isInstanceOf[ConstantTypedExpression[_$4, _$5] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }])
      {
        val c = e.asInstanceOf[ConstantTypedExpression[Any, Any]];
        sw.write("cast(");
        e.write(sw);
        sw.write(" as varchar(");
        sw.write(c.value.toString.length.toString);
        sw.write("))")
      }
    else
      e.write(sw);
    override def writeRegexExpression(left: ExpressionNode, pattern: String, sw: StatementWriter) = throw new UnsupportedOperationException("DB2 does not support a regex scalar function")
  }
}