package org.squeryl.adapters {
  import org.squeryl.{Session, Table};
  import org.squeryl.dsl.ast._;
  import org.squeryl.dsl._;
  import java.sql.SQLException;
  import collection.Set;
  import collection.immutable.List;
  import collection.mutable.HashSet;
  import org.squeryl.internals.{FieldMetaData, StatementWriter, DatabaseAdapter};
  import org.squeryl.internals.ConstantStatementParam;
  import org.squeryl.InternalFieldMapper;
  class OracleAdapter extends DatabaseAdapter {
    def <init>() = {
      super.<init>();
      ()
    };
    override def intTypeDeclaration = "number";
    override def stringTypeDeclaration = "varchar2";
    override def stringTypeDeclaration(length: Int) = "varchar2(".$plus(length).$plus(")");
    override def booleanTypeDeclaration = "number(1)";
    override def doubleTypeDeclaration = "double precision";
    override def longTypeDeclaration = "number";
    override def binaryTypeDeclaration = "blob";
    override def timestampTypeDeclaration = "timestamp";
    override def supportsAutoIncrementInColumnDeclaration: Boolean = false;
    override def postCreateTable(t: Table[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, printSinkWhenWriteOnlyMode: Option[_root_.scala.Function1[String, Unit]]) = {
      val autoIncrementedFields = t.posoMetaData.fieldsMetaData.filter(((x$1) => x$1.isAutoIncremented));
      autoIncrementedFields.foreach(((fmd) => {
        val sw = new StatementWriter(false, this);
        sw.write("create sequence ", fmd.sequenceName, " start with 1 increment by 1 nomaxvalue");
        if (printSinkWhenWriteOnlyMode.$eq$eq(None))
          {
            val st = Session.currentSession.connection.createStatement;
            st.execute(sw.statement)
          }
        else
          printSinkWhenWriteOnlyMode.get.apply(sw.statement.$plus(";"))
      }))
    };
    override def postDropTable(t: Table[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      val autoIncrementedFields = t.posoMetaData.fieldsMetaData.filter(((x$2) => x$2.isAutoIncremented));
      autoIncrementedFields.foreach(((fmd) => execFailSafeExecute("drop sequence ".$plus(fmd.sequenceName), ((e) => e.getErrorCode.$eq$eq(2289)))))
    };
    override def createSequenceName(fmd: FieldMetaData) = {
      val prefix = "s_".$plus(fmd.columnName.take(6)).$plus("_").$plus(fmd.parentMetaData.viewOrTable.name.take(10));
      val shrunkName = prefix.$plus(generateAlmostUniqueSuffixWithHash(fmd.columnName.$plus("_").$plus(fmd.parentMetaData.viewOrTable.name)));
      shrunkName
    };
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
        <synthetic> val x$3 = List(autoIncPK.get);
        f.toList.$colon$colon$colon(x$3)
      };
      val colVals = {
        <synthetic> val x$4 = List(autoIncPK.get.sequenceName.$plus(".nextval"));
        f.map(((fmd) => writeValue(o_, fmd, sw))).toList.$colon$colon$colon(x$4)
      };
      sw.write("insert into ");
      sw.write(t.prefixedName);
      sw.write(" (");
      sw.write(colNames.map(((fmd) => fmd.columnName)).mkString(", "));
      sw.write(") values ");
      sw.write(colVals.mkString("(", ",", ")"))
    };
    override def writeConcatFunctionCall(fn: FunctionNode, sw: StatementWriter) = sw.writeNodesWithSeparator(fn.args, " || ", false);
    override def writeJoin(queryableExpressionNode: QueryableExpressionNode, sw: StatementWriter) = {
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
    override def writePaginatedQueryDeclaration(qen: QueryExpressionElements, sw: StatementWriter) = ();
    override def writeQuery(qen: QueryExpressionElements, sw: StatementWriter) = if (qen.page.$eq$eq(None))
      super.writeQuery(qen, sw)
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
    override def isTableDoesNotExistException(e: SQLException) = e.getErrorCode.$eq$eq(942);
    def legalOracleSuffixChars = OracleAdapter.legalOracleSuffixChars;
    def paddingPossibilities(start: String, padLength: Int): Iterable[String] = if (padLength.$less(0))
      org.squeryl.internals.Utils.throwError("padLength must be positive, was given : ".$plus(padLength))
    else
      if (padLength.$eq$eq(0))
        Seq(start)
      else
        if (padLength.$eq$eq(1))
          legalOracleSuffixChars.map(((x$5) => start.$plus(x$5)))
        else
          legalOracleSuffixChars.flatMap(((end) => paddingPossibilities(start, padLength.$minus(1)).map(((pad) => pad.$plus(end)))));
    class CouldNotShrinkIdentifierException extends RuntimeException {
      def <init>() = {
        super.<init>();
        ()
      }
    };
    def makeUniqueInScope(s: String, scope: Set[String], padLength: Int): String = {
      var prefix = s.substring(0, s.length.$minus(padLength));
      val possibilities = paddingPossibilities(prefix, padLength);
      possibilities.withFilter(((p) => scope.contains(p).unary_$bang)).foreach(((p) => return p));
      if (s.length.$eq$eq(padLength))
        throw new CouldNotShrinkIdentifierException()
      else
        ();
      makeUniqueInScope(s, scope, padLength.$plus(1))
    };
    def makeUniqueInScope(s: String, scope: scala.collection.Set[String]): String = try {
      if (scope.contains(s))
        makeUniqueInScope(s, scope, 1)
      else
        s
    } catch {
      case (e @ (_: CouldNotShrinkIdentifierException)) => org.squeryl.internals.Utils.throwError("could not make a unique identifier with \'".$plus(s).$plus("\'"))
    };
    def shrinkTo30AndPreserveUniquenessInScope(identifier: String, scope: HashSet[String]) = if (identifier.length.$less$eq(29))
      identifier
    else
      {
        val res = makeUniqueInScope(identifier.substring(0, 30), scope);
        scope.add(res);
        res
      };
    override def writeSelectElementAlias(se: SelectElement, sw: StatementWriter) = sw.write(shrinkTo30AndPreserveUniquenessInScope(se.aliasSegment, sw.scope));
    override def foreignKeyConstraintName(foreignKeyTable: Table[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }, idWithinSchema: Int) = {
      val name = super.foreignKeyConstraintName(foreignKeyTable, idWithinSchema);
      val r = shrinkTo30AndPreserveUniquenessInScope(name, foreignKeyTable.schema._namingScope);
      r
    };
    override def writeRegexExpression(left: ExpressionNode, pattern: String, sw: StatementWriter) = {
      sw.write(" REGEXP_LIKE(");
      left.write(sw);
      sw.write(",?)");
      sw.addParam(ConstantStatementParam(InternalFieldMapper.stringTEF.createConstant(pattern)))
    };
    override def fieldAlias(n: QueryableExpressionNode, fse: FieldSelectElement) = "f".$plus(fse.uniqueId.get);
    override def aliasExport(parentOfTarget: QueryableExpressionNode, target: SelectElement) = "f".$plus(target.actualSelectElement.id);
    override def viewAlias(vn: ViewExpressionNode[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = "t".$plus(vn.uniqueId.get)
  };
  object OracleAdapter extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    val legalOracleSuffixChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789".toCharArray.toList
  }
}