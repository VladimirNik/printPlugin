package org.squeryl.adapters {
  import org.squeryl.{Session, Table};
  import org.squeryl.dsl.ast._;
  import org.squeryl.dsl._;
  import java.sql.SQLException;
  import scala.collection.Set;
  import scala.collection.immutable.List;
  import scala.collection.mutable.HashSet;
  import org.squeryl.internals.{FieldMetaData, StatementWriter, DatabaseAdapter};
  import org.squeryl.internals.ConstantStatementParam;
  import org.squeryl.InternalFieldMapper;
  class OracleAdapter extends Object with org.squeryl.internals.DatabaseAdapter {
    def <init>(): org.squeryl.adapters.OracleAdapter = {
      OracleAdapter.super.<init>();
      ()
    };
    override def intTypeDeclaration: String = "number";
    override def stringTypeDeclaration: String = "varchar2";
    override def stringTypeDeclaration(length: Int): String = "varchar2(".+(length).+(")");
    override def booleanTypeDeclaration: String = "number(1)";
    override def doubleTypeDeclaration: String = "double precision";
    override def longTypeDeclaration: String = "number";
    override def binaryTypeDeclaration: String = "blob";
    override def timestampTypeDeclaration: String = "timestamp";
    override def supportsAutoIncrementInColumnDeclaration: Boolean = false;
    override def postCreateTable(t: org.squeryl.Table[_], printSinkWhenWriteOnlyMode: Option[String => Unit]): Unit = {
      val autoIncrementedFields: Iterable[org.squeryl.internals.FieldMetaData] = t.posoMetaData.fieldsMetaData.filter(((x$1: org.squeryl.internals.FieldMetaData) => x$1.isAutoIncremented));
      autoIncrementedFields.foreach[AnyVal](((fmd: org.squeryl.internals.FieldMetaData) => {
        val sw: org.squeryl.internals.StatementWriter = new org.squeryl.internals.StatementWriter(false, this);
        sw.write("create sequence ", fmd.sequenceName, " start with 1 increment by 1 nomaxvalue");
        if (printSinkWhenWriteOnlyMode.==(scala.None))
          {
            val st: java.sql.Statement = org.squeryl.Session.currentSession.connection.createStatement();
            st.execute(sw.statement)
          }
        else
          printSinkWhenWriteOnlyMode.get.apply(sw.statement.+(";"))
      }))
    };
    override def postDropTable(t: org.squeryl.Table[_]): Unit = {
      val autoIncrementedFields: Iterable[org.squeryl.internals.FieldMetaData] = t.posoMetaData.fieldsMetaData.filter(((x$2: org.squeryl.internals.FieldMetaData) => x$2.isAutoIncremented));
      autoIncrementedFields.foreach[Unit](((fmd: org.squeryl.internals.FieldMetaData) => OracleAdapter.this.execFailSafeExecute(OracleAdapter.this.string2StatementWriter("drop sequence ".+(fmd.sequenceName)), ((e: java.sql.SQLException) => e.getErrorCode().==(2289)))))
    };
    override def createSequenceName(fmd: org.squeryl.internals.FieldMetaData): String = {
      val prefix: String = "s_".+(scala.this.Predef.augmentString(fmd.columnName).take(6)).+("_").+(scala.this.Predef.augmentString(fmd.parentMetaData.viewOrTable.name).take(10));
      val shrunkName: String = prefix.+(OracleAdapter.this.generateAlmostUniqueSuffixWithHash(fmd.columnName.+("_").+(fmd.parentMetaData.viewOrTable.name)));
      shrunkName
    };
    override def writeInsert[T >: Nothing <: Any](o: T, t: org.squeryl.Table[T], sw: org.squeryl.internals.StatementWriter): Unit = {
      val o_: AnyRef = o.asInstanceOf[AnyRef];
      val autoIncPK: Option[org.squeryl.internals.FieldMetaData] = t.posoMetaData.fieldsMetaData.find(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isAutoIncremented));
      if (autoIncPK.==(scala.None))
        {
          OracleAdapter.super.writeInsert[T](o, t, sw);
          return ()
        }
      else
        ();
      val f: Iterable[org.squeryl.internals.FieldMetaData] = OracleAdapter.this.getInsertableFields(t.posoMetaData.fieldsMetaData);
      val colNames: List[org.squeryl.internals.FieldMetaData] = {
        <synthetic> val x$3: List[org.squeryl.internals.FieldMetaData] = scala.collection.immutable.List.apply[org.squeryl.internals.FieldMetaData](autoIncPK.get);
        f.toList.:::[org.squeryl.internals.FieldMetaData](x$3)
      };
      val colVals: List[String] = {
        <synthetic> val x$4: List[String] = scala.collection.immutable.List.apply[String](autoIncPK.get.sequenceName.+(".nextval"));
        f.map[String, Iterable[String]](((fmd: org.squeryl.internals.FieldMetaData) => OracleAdapter.this.writeValue(o_, fmd, sw)))(collection.this.Iterable.canBuildFrom[String]).toList.:::[String](x$4)
      };
      sw.write("insert into ");
      sw.write(t.prefixedName);
      sw.write(" (");
      sw.write(colNames.map[String, List[String]](((fmd: org.squeryl.internals.FieldMetaData) => fmd.columnName))(immutable.this.List.canBuildFrom[String]).mkString(", "));
      sw.write(") values ");
      sw.write(colVals.mkString("(", ",", ")"))
    };
    override def writeConcatFunctionCall(fn: org.squeryl.dsl.ast.FunctionNode, sw: org.squeryl.internals.StatementWriter): Unit = sw.writeNodesWithSeparator(fn.args, " || ", false);
    override def writeJoin(queryableExpressionNode: org.squeryl.dsl.ast.QueryableExpressionNode, sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write(queryableExpressionNode.joinKind.get._1);
      sw.write(" ");
      sw.write(queryableExpressionNode.joinKind.get._2);
      sw.write(" join ");
      queryableExpressionNode.write(sw);
      sw.write(" ");
      sw.write(queryableExpressionNode.alias);
      sw.write(" on ");
      queryableExpressionNode.joinExpression.get.write(sw)
    };
    override def writePaginatedQueryDeclaration(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = ();
    override def writeQuery(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = if (qen.page.==(scala.None))
      OracleAdapter.super.writeQuery(qen, sw)
    else
      {
        sw.write("select sq____1.* from (");
        sw.nextLine;
        sw.writeIndented({
          sw.write("select sq____0.*, rownum as rn____");
          sw.nextLine;
          sw.write("from");
          sw.nextLine;
          sw.writeIndented({
            sw.write("(");
            OracleAdapter.super.writeQuery(qen, sw);
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
    override def isTableDoesNotExistException(e: java.sql.SQLException): Boolean = e.getErrorCode().==(942);
    def legalOracleSuffixChars: List[Char] = OracleAdapter.legalOracleSuffixChars;
    def paddingPossibilities(start: String, padLength: Int): Iterable[String] = if (padLength.<(0))
      org.squeryl.internals.Utils.throwError("padLength must be positive, was given : ".+(padLength))
    else
      if (padLength.==(0))
        collection.this.Seq.apply[String](start)
      else
        if (padLength.==(1))
          OracleAdapter.this.legalOracleSuffixChars.map[String, Iterable[String]](((x$5: Char) => start.+(x$5)))(immutable.this.List.canBuildFrom[String])
        else
          OracleAdapter.this.legalOracleSuffixChars.flatMap[String, Iterable[String]](((end: Char) => OracleAdapter.this.paddingPossibilities(start, padLength.-(1)).map[String, Iterable[String]](((pad: String) => pad.+(end)))(collection.this.Iterable.canBuildFrom[String])))(immutable.this.List.canBuildFrom[String]);
    class CouldNotShrinkIdentifierException extends scala.`package`.RuntimeException {
      def <init>(): OracleAdapter.this.CouldNotShrinkIdentifierException = {
        CouldNotShrinkIdentifierException.super.<init>();
        ()
      }
    };
    def makeUniqueInScope(s: String, scope: scala.collection.Set[String], padLength: Int): String = {
      var prefix: String = s.substring(0, s.length().-(padLength));
      val possibilities: Iterable[String] = OracleAdapter.this.paddingPossibilities(prefix, padLength);
      possibilities.withFilter(((p: String) => scope.contains(p).unary_!)).foreach[Nothing](((p: String) => return p));
      if (s.length().==(padLength))
        throw new OracleAdapter.this.CouldNotShrinkIdentifierException()
      else
        ();
      OracleAdapter.this.makeUniqueInScope(s, scope, padLength.+(1))
    };
    def makeUniqueInScope(s: String, scope: scala.collection.Set[String]): String = try {
      if (scope.contains(s))
        OracleAdapter.this.makeUniqueInScope(s, scope, 1)
      else
        s
    } catch {
      case (e @ (_: OracleAdapter.this.CouldNotShrinkIdentifierException)) => org.squeryl.internals.Utils.throwError("could not make a unique identifier with \'".+(s).+("\'"))
    };
    def shrinkTo30AndPreserveUniquenessInScope(identifier: String, scope: scala.collection.mutable.HashSet[String]): String = if (identifier.length().<=(29))
      identifier
    else
      {
        val res: String = OracleAdapter.this.makeUniqueInScope(identifier.substring(0, 30), scope);
        scope.add(res);
        res
      };
    override def writeSelectElementAlias(se: org.squeryl.dsl.ast.SelectElement, sw: org.squeryl.internals.StatementWriter): Unit = sw.write(OracleAdapter.this.shrinkTo30AndPreserveUniquenessInScope(se.aliasSegment, sw.scope));
    override def foreignKeyConstraintName(foreignKeyTable: org.squeryl.Table[_], idWithinSchema: Int): String = {
      val name: String = OracleAdapter.super.foreignKeyConstraintName(foreignKeyTable, idWithinSchema);
      val r: String = OracleAdapter.this.shrinkTo30AndPreserveUniquenessInScope(name, foreignKeyTable.schema._namingScope);
      r
    };
    override def writeRegexExpression(left: org.squeryl.dsl.ast.ExpressionNode, pattern: String, sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write(" REGEXP_LIKE(");
      left.write(sw);
      sw.write(",?)");
      sw.addParam(org.squeryl.internals.ConstantStatementParam.apply(org.squeryl.InternalFieldMapper.stringTEF.createConstant(pattern)))
    };
    override def fieldAlias(n: org.squeryl.dsl.ast.QueryableExpressionNode, fse: org.squeryl.dsl.ast.FieldSelectElement): String = "f".+(fse.uniqueId.get);
    override def aliasExport(parentOfTarget: org.squeryl.dsl.ast.QueryableExpressionNode, target: org.squeryl.dsl.ast.SelectElement): String = "f".+(target.actualSelectElement.id);
    override def viewAlias(vn: org.squeryl.dsl.ast.ViewExpressionNode[_]): String = "t".+(vn.uniqueId.get)
  };
  object OracleAdapter extends scala.AnyRef {
    def <init>(): org.squeryl.adapters.OracleAdapter.type = {
      OracleAdapter.super.<init>();
      ()
    };
    private[this] val legalOracleSuffixChars: List[Char] = scala.this.Predef.charArrayOps("ABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789".toCharArray()).toList;
    <stable> <accessor> def legalOracleSuffixChars: List[Char] = OracleAdapter.this.legalOracleSuffixChars
  }
}