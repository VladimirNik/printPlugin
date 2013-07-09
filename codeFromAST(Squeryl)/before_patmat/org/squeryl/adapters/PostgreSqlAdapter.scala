package org.squeryl.adapters {
  import org.squeryl.dsl.ast.FunctionNode;
  import java.sql.{ResultSet, SQLException};
  import java.util.UUID;
  import org.squeryl.internals.{StatementWriter, DatabaseAdapter, FieldMetaData};
  import org.squeryl.{Session, Table};
  class PostgreSqlAdapter extends Object with org.squeryl.internals.DatabaseAdapter {
    def <init>(): org.squeryl.adapters.PostgreSqlAdapter = {
      PostgreSqlAdapter.super.<init>();
      ()
    };
    def usePostgresSequenceNamingScheme: Boolean = false;
    override def intTypeDeclaration: String = "integer";
    override def stringTypeDeclaration: String = "varchar";
    override def stringTypeDeclaration(length: Int): String = "varchar(".+(length).+(")");
    override def booleanTypeDeclaration: String = "boolean";
    override def doubleTypeDeclaration: String = "double precision";
    override def longTypeDeclaration: String = "bigint";
    override def bigDecimalTypeDeclaration: String = "numeric";
    override def bigDecimalTypeDeclaration(precision: Int, scale: Int): String = "numeric(".+(precision).+(",").+(scale).+(")");
    override def binaryTypeDeclaration: String = "bytea";
    override def uuidTypeDeclaration: String = "uuid";
    override def jdbcIntArrayCreationType: String = "int4";
    override def jdbcLongArrayCreationType: String = "int8";
    override def jdbcDoubleArrayCreationType: String = "float8";
    override def jdbcStringArrayCreationType: String = "varchar";
    override def foreignKeyConstraintName(foreignKeyTable: org.squeryl.Table[_], idWithinSchema: Int): String = foreignKeyTable.name.+("FK").+(idWithinSchema);
    override def postCreateTable(t: org.squeryl.Table[_], printSinkWhenWriteOnlyMode: Option[String => Unit]): Unit = {
      val autoIncrementedFields: Iterable[org.squeryl.internals.FieldMetaData] = t.posoMetaData.fieldsMetaData.filter(((x$1: org.squeryl.internals.FieldMetaData) => x$1.isAutoIncremented));
      autoIncrementedFields.foreach[AnyVal](((fmd: org.squeryl.internals.FieldMetaData) => {
        val sw: org.squeryl.internals.StatementWriter = new org.squeryl.internals.StatementWriter(false, this);
        sw.write("create sequence ", PostgreSqlAdapter.this.quoteName(fmd.sequenceName));
        if (printSinkWhenWriteOnlyMode.==(scala.None))
          {
            val st: java.sql.Statement = org.squeryl.Session.currentSession.connection.createStatement();
            st.execute(sw.statement)
          }
        else
          printSinkWhenWriteOnlyMode.get.apply(sw.statement.+(";"))
      }))
    };
    def sequenceName(t: org.squeryl.Table[_]): String = if (PostgreSqlAdapter.this.usePostgresSequenceNamingScheme)
      {
        val autoIncPK: Option[org.squeryl.internals.FieldMetaData] = t.posoMetaData.fieldsMetaData.find(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isAutoIncremented));
        t.name.+("_").+(autoIncPK.get.nameOfProperty).+("_seq")
      }
    else
      t.prefixedPrefixedName("seq_");
    override def createSequenceName(fmd: org.squeryl.internals.FieldMetaData): String = if (PostgreSqlAdapter.this.usePostgresSequenceNamingScheme)
      fmd.parentMetaData.viewOrTable.name.+("_").+(fmd.columnName).+("_seq")
    else
      PostgreSqlAdapter.super.createSequenceName(fmd);
    override def writeConcatFunctionCall(fn: org.squeryl.dsl.ast.FunctionNode, sw: org.squeryl.internals.StatementWriter): Unit = sw.writeNodesWithSeparator(fn.args, " || ", false);
    override def writeInsert[T >: Nothing <: Any](o: T, t: org.squeryl.Table[T], sw: org.squeryl.internals.StatementWriter): Unit = {
      val o_: AnyRef = o.asInstanceOf[AnyRef];
      val autoIncPK: Option[org.squeryl.internals.FieldMetaData] = t.posoMetaData.fieldsMetaData.find(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isAutoIncremented));
      if (autoIncPK.==(scala.None))
        {
          PostgreSqlAdapter.super.writeInsert[T](o, t, sw);
          return ()
        }
      else
        ();
      val f: Iterable[org.squeryl.internals.FieldMetaData] = PostgreSqlAdapter.this.getInsertableFields(t.posoMetaData.fieldsMetaData);
      val colNames: List[org.squeryl.internals.FieldMetaData] = {
        <synthetic> val x$2: List[org.squeryl.internals.FieldMetaData] = immutable.this.List.apply[org.squeryl.internals.FieldMetaData](autoIncPK.get);
        f.toList.:::[org.squeryl.internals.FieldMetaData](x$2)
      };
      val colVals: List[String] = {
        <synthetic> val x$3: List[String] = immutable.this.List.apply[String]("nextval(\'".+(PostgreSqlAdapter.this.quoteName(autoIncPK.get.sequenceName)).+("\')"));
        f.map[String, Iterable[String]](((fmd: org.squeryl.internals.FieldMetaData) => PostgreSqlAdapter.this.writeValue(o_, fmd, sw)))(collection.this.Iterable.canBuildFrom[String]).toList.:::[String](x$3)
      };
      sw.write("insert into ");
      sw.write(PostgreSqlAdapter.this.quoteName(t.prefixedName));
      sw.write(" (");
      sw.write(colNames.map[String, List[String]](((fmd: org.squeryl.internals.FieldMetaData) => PostgreSqlAdapter.this.quoteName(fmd.columnName)))(immutable.this.List.canBuildFrom[String]).mkString(", "));
      sw.write(") values ");
      sw.write(colVals.mkString("(", ",", ")"))
    };
    override def supportsAutoIncrementInColumnDeclaration: Boolean = false;
    override def isTableDoesNotExistException(e: java.sql.SQLException): Boolean = e.getSQLState().equals("42P01");
    override def writeCompositePrimaryKeyConstraint(t: org.squeryl.Table[_], cols: Iterable[org.squeryl.internals.FieldMetaData]): String = {
      val sb: StringBuilder = new scala.`package`.StringBuilder(256);
      sb.append("alter table ");
      sb.append(PostgreSqlAdapter.this.quoteName(t.prefixedName));
      sb.append(" add primary key (");
      sb.append(cols.map[String, Iterable[String]](((x$4: org.squeryl.internals.FieldMetaData) => x$4.columnName))(collection.this.Iterable.canBuildFrom[String]).map[String, Iterable[String]](((x$5: String) => PostgreSqlAdapter.this.quoteName(x$5)))(collection.this.Iterable.canBuildFrom[String]).mkString(","));
      sb.append(")");
      sb.toString()
    };
    override def writeDropForeignKeyStatement(foreignKeyTable: org.squeryl.Table[_], fkName: String): String = "alter table ".+(PostgreSqlAdapter.this.quoteName(foreignKeyTable.prefixedName)).+(" drop constraint ").+(PostgreSqlAdapter.this.quoteName(fkName));
    override def failureOfStatementRequiresRollback: Boolean = true;
    override def postDropTable(t: org.squeryl.Table[_]): Unit = {
      val autoIncrementedFields: Iterable[org.squeryl.internals.FieldMetaData] = t.posoMetaData.fieldsMetaData.filter(((x$6: org.squeryl.internals.FieldMetaData) => x$6.isAutoIncremented));
      autoIncrementedFields.foreach[Unit](((fmd: org.squeryl.internals.FieldMetaData) => PostgreSqlAdapter.this.execFailSafeExecute(PostgreSqlAdapter.this.string2StatementWriter("drop sequence ".+(PostgreSqlAdapter.this.quoteName(fmd.sequenceName))), ((e: java.sql.SQLException) => e.getSQLState().equals("42P01")))))
    };
    override def quoteIdentifier(s: String): String = immutable.this.List.apply[String]("\"", s.replace("\"", "\"\""), "\"").mkString;
    override def convertFromUuidForJdbc(u: java.util.UUID): AnyRef = u;
    override def convertToUuidForJdbc(rs: java.sql.ResultSet, i: Int): java.util.UUID = rs.getObject(i).asInstanceOf[java.util.UUID]
  }
}