package org.squeryl.adapters {
  import org.squeryl.dsl.ast.FunctionNode;
  import java.sql.{ResultSet, SQLException};
  import java.util.UUID;
  import org.squeryl.internals.{StatementWriter, DatabaseAdapter, FieldMetaData};
  import org.squeryl.{Session, Table};
  class PostgreSqlAdapter extends DatabaseAdapter {
    def <init>() = {
      super.<init>();
      ()
    };
    def usePostgresSequenceNamingScheme: Boolean = false;
    override def intTypeDeclaration = "integer";
    override def stringTypeDeclaration = "varchar";
    override def stringTypeDeclaration(length: Int) = "varchar(".$plus(length).$plus(")");
    override def booleanTypeDeclaration = "boolean";
    override def doubleTypeDeclaration = "double precision";
    override def longTypeDeclaration = "bigint";
    override def bigDecimalTypeDeclaration = "numeric";
    override def bigDecimalTypeDeclaration(precision: Int, scale: Int) = "numeric(".$plus(precision).$plus(",").$plus(scale).$plus(")");
    override def binaryTypeDeclaration = "bytea";
    override def uuidTypeDeclaration = "uuid";
    override def jdbcIntArrayCreationType = "int4";
    override def jdbcLongArrayCreationType = "int8";
    override def jdbcDoubleArrayCreationType = "float8";
    override def jdbcStringArrayCreationType = "varchar";
    override def foreignKeyConstraintName(foreignKeyTable: Table[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, idWithinSchema: Int) = foreignKeyTable.name.$plus("FK").$plus(idWithinSchema);
    override def postCreateTable(t: Table[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }, printSinkWhenWriteOnlyMode: Option[_root_.scala.Function1[String, Unit]]) = {
      val autoIncrementedFields = t.posoMetaData.fieldsMetaData.filter(((x$1) => x$1.isAutoIncremented));
      autoIncrementedFields.foreach(((fmd) => {
        val sw = new StatementWriter(false, this);
        sw.write("create sequence ", quoteName(fmd.sequenceName));
        if (printSinkWhenWriteOnlyMode.$eq$eq(None))
          {
            val st = Session.currentSession.connection.createStatement;
            st.execute(sw.statement)
          }
        else
          printSinkWhenWriteOnlyMode.get.apply(sw.statement.$plus(";"))
      }))
    };
    def sequenceName(t: Table[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = if (usePostgresSequenceNamingScheme)
      {
        val autoIncPK = t.posoMetaData.fieldsMetaData.find(((fmd) => fmd.isAutoIncremented));
        t.name.$plus("_").$plus(autoIncPK.get.nameOfProperty).$plus("_seq")
      }
    else
      t.prefixedPrefixedName("seq_");
    override def createSequenceName(fmd: FieldMetaData) = if (usePostgresSequenceNamingScheme)
      fmd.parentMetaData.viewOrTable.name.$plus("_").$plus(fmd.columnName).$plus("_seq")
    else
      super.createSequenceName(fmd);
    override def writeConcatFunctionCall(fn: FunctionNode, sw: StatementWriter) = sw.writeNodesWithSeparator(fn.args, " || ", false);
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
        <synthetic> val x$2 = List(autoIncPK.get);
        f.toList.$colon$colon$colon(x$2)
      };
      val colVals = {
        <synthetic> val x$3 = List("nextval(\'".$plus(quoteName(autoIncPK.get.sequenceName)).$plus("\')"));
        f.map(((fmd) => writeValue(o_, fmd, sw))).toList.$colon$colon$colon(x$3)
      };
      sw.write("insert into ");
      sw.write(quoteName(t.prefixedName));
      sw.write(" (");
      sw.write(colNames.map(((fmd) => quoteName(fmd.columnName))).mkString(", "));
      sw.write(") values ");
      sw.write(colVals.mkString("(", ",", ")"))
    };
    override def supportsAutoIncrementInColumnDeclaration: Boolean = false;
    override def isTableDoesNotExistException(e: SQLException) = e.getSQLState.equals("42P01");
    override def writeCompositePrimaryKeyConstraint(t: Table[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }, cols: Iterable[FieldMetaData]) = {
      val sb = new StringBuilder(256);
      sb.append("alter table ");
      sb.append(quoteName(t.prefixedName));
      sb.append(" add primary key (");
      sb.append(cols.map(((x$4) => x$4.columnName)).map(((x$5) => quoteName(x$5))).mkString(","));
      sb.append(")");
      sb.toString
    };
    override def writeDropForeignKeyStatement(foreignKeyTable: Table[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }, fkName: String) = "alter table ".$plus(quoteName(foreignKeyTable.prefixedName)).$plus(" drop constraint ").$plus(quoteName(fkName));
    override def failureOfStatementRequiresRollback = true;
    override def postDropTable(t: Table[_$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      val autoIncrementedFields = t.posoMetaData.fieldsMetaData.filter(((x$6) => x$6.isAutoIncremented));
      autoIncrementedFields.foreach(((fmd) => execFailSafeExecute("drop sequence ".$plus(quoteName(fmd.sequenceName)), ((e) => e.getSQLState.equals("42P01")))))
    };
    override def quoteIdentifier(s: String) = List("\"", s.replace("\"", "\"\""), "\"").mkString;
    override def convertFromUuidForJdbc(u: UUID): AnyRef = u;
    override def convertToUuidForJdbc(rs: ResultSet, i: Int): UUID = rs.getObject(i).asInstanceOf[UUID]
  }
}