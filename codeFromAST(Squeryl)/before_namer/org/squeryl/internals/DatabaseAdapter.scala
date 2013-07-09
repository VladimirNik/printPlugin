package org.squeryl.internals {
  import org.squeryl.dsl.ast._;
  import org.squeryl.dsl._;
  import org.squeryl._;
  import dsl.CompositeKey;
  import org.squeryl.{Schema, Session, Table};
  import java.sql._;
  import java.util.UUID;
  abstract trait DatabaseAdapter extends scala.AnyRef {
    def $init$() = {
      ()
    };
    class Zip[T >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
      <paramaccessor> val element: T = _;
      <paramaccessor> val isLast: Boolean = _;
      <paramaccessor> val isFirst: Boolean = _;
      def <init>(element: T, isLast: Boolean, isFirst: Boolean) = {
        super.<init>();
        ()
      }
    };
    class ZipIterable[T >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
      <paramaccessor> private[this] val iterable: Iterable[T] = _;
      def <init>(iterable: Iterable[T]) = {
        super.<init>();
        ()
      };
      val count = iterable.size;
      def foreach[U >: _root_.scala.Nothing <: _root_.scala.Any](f: _root_.scala.Function1[Zip[T], U]): Unit = {
        var c = 1;
        iterable.foreach(((i) => {
          f(new Zip(i, c.$eq$eq(count), c.$eq$eq(1)));
          c.$plus$eq(1)
        }))
      };
      def zipi = this
    };
    implicit def zipIterable[T >: _root_.scala.Nothing <: _root_.scala.Any](i: Iterable[T]) = new ZipIterable(i);
    def writeQuery(qen: QueryExpressionElements, sw: StatementWriter): Unit = writeQuery(qen, sw, false, None);
    def verifyDeleteByPK: Boolean = true;
    protected def writeQuery(qen: QueryExpressionElements, sw: StatementWriter, inverseOrderBy: Boolean, topHint: Option[String]): Unit = {
      sw.write("Select");
      topHint.foreach(((x$1) => " ".$plus(sw.write(x$1)).$plus(" ")));
      if (qen.selectDistinct)
        sw.write(" distinct")
      else
        ();
      sw.nextLine;
      sw.writeIndented(sw.writeNodesWithSeparator(qen.selectList.filter(((e) => e.inhibited.unary_$bang)), ",", true));
      sw.nextLine;
      sw.write("From");
      sw.nextLine;
      if (qen.isJoinForm.unary_$bang)
        sw.writeIndented({
          qen.tableExpressions.zipi.foreach(((z) => {
            z.element.write(sw);
            sw.write(" ");
            sw.write(sw.quoteName(z.element.alias));
            if (z.isLast.unary_$bang)
              {
                sw.write(",");
                sw.nextLine
              }
            else
              ()
          }));
          sw.pushPendingNextLine
        })
      else
        {
          val singleNonJoinTableExpression = qen.tableExpressions.filter(((x$2) => x$2.isMemberOfJoinList.unary_$bang));
          assert(singleNonJoinTableExpression.size.$eq$eq(1), "join query must have exactly one FROM argument, got : ".$plus(qen.tableExpressions));
          val firstJoinExpr = singleNonJoinTableExpression.head;
          val restOfJoinExpr = qen.tableExpressions.filter(((x$3) => x$3.isMemberOfJoinList));
          firstJoinExpr.write(sw);
          sw.write(" ");
          sw.write(sw.quoteName(firstJoinExpr.alias));
          sw.nextLine;
          restOfJoinExpr.zipi.foreach(((z) => {
            writeJoin(z.element, sw);
            if (z.isLast)
              sw.unindent
            else
              ();
            sw.pushPendingNextLine
          }))
        };
      writeEndOfFromHint(qen, sw);
      if (qen.hasUnInhibitedWhereClause)
        {
          sw.write("Where");
          sw.nextLine;
          sw.writeIndented(qen.whereClause.get.write(sw));
          sw.pushPendingNextLine
        }
      else
        ();
      if (qen.groupByClause.isEmpty.unary_$bang)
        {
          sw.write("Group By");
          sw.nextLine;
          sw.writeIndented(sw.writeNodesWithSeparator(qen.groupByClause.filter(((e) => e.inhibited.unary_$bang)), ",", true));
          sw.pushPendingNextLine
        }
      else
        ();
      if (qen.havingClause.isEmpty.unary_$bang)
        {
          sw.write("Having");
          sw.nextLine;
          sw.writeIndented(sw.writeNodesWithSeparator(qen.havingClause.filter(((e) => e.inhibited.unary_$bang)), ",", true));
          sw.pushPendingNextLine
        }
      else
        ();
      if (qen.orderByClause.isEmpty.unary_$bang.$amp$amp(qen.parent.$eq$eq(None)))
        {
          sw.write("Order By");
          sw.nextLine;
          val ob0 = qen.orderByClause.filter(((e) => e.inhibited.unary_$bang));
          val ob = if (inverseOrderBy)
            ob0.map(((x$4) => x$4.asInstanceOf[OrderByExpression].inverse))
          else
            ob0;
          sw.writeIndented(sw.writeNodesWithSeparator(ob, ",", true));
          sw.pushPendingNextLine
        }
      else
        ();
      writeEndOfQueryHint(qen, sw);
      writePaginatedQueryDeclaration(qen, sw)
    };
    def writeEndOfQueryHint(qen: QueryExpressionElements, sw: StatementWriter) = if (qen.isForUpdate)
      {
        sw.write("for update");
        sw.pushPendingNextLine
      }
    else
      ();
    def writeEndOfFromHint(qen: QueryExpressionElements, sw: StatementWriter) = ();
    def writePaginatedQueryDeclaration(qen: QueryExpressionElements, sw: StatementWriter): Unit = qen.page.foreach(((p) => {
      sw.write("limit ");
      sw.write(p._2.toString);
      sw.write(" offset ");
      sw.write(p._1.toString)
    }));
    def writeJoin(queryableExpressionNode: QueryableExpressionNode, sw: StatementWriter) = {
      sw.write(queryableExpressionNode.joinKind.get._1);
      sw.write(" ");
      sw.write(queryableExpressionNode.joinKind.get._2);
      sw.write(" join ");
      queryableExpressionNode.write(sw);
      sw.write(" as ");
      sw.write(sw.quoteName(queryableExpressionNode.alias));
      sw.write(" on ");
      queryableExpressionNode.joinExpression.get.write(sw)
    };
    def intTypeDeclaration = "int";
    def stringTypeDeclaration = "varchar";
    def stringTypeDeclaration(length: Int) = "varchar(".$plus(length).$plus(")");
    def booleanTypeDeclaration = "boolean";
    def doubleTypeDeclaration = "double";
    def dateTypeDeclaration = "date";
    def longTypeDeclaration = "bigint";
    def floatTypeDeclaration = "real";
    def bigDecimalTypeDeclaration = "decimal";
    def bigDecimalTypeDeclaration(precision: Int, scale: Int) = "decimal(".$plus(precision).$plus(",").$plus(scale).$plus(")");
    def timestampTypeDeclaration = "timestamp";
    def binaryTypeDeclaration = "binary";
    def uuidTypeDeclaration = "char(36)";
    def intArrayTypeDeclaration = intTypeDeclaration.$plus("[]");
    def longArrayTypeDeclaration = longTypeDeclaration.$plus("[]");
    def doubleArrayTypeDeclaration = doubleTypeDeclaration.$plus("[]");
    def stringArrayTypeDeclaration = stringTypeDeclaration.$plus("[]");
    def jdbcIntArrayCreationType = intTypeDeclaration;
    def jdbcLongArrayCreationType = longTypeDeclaration;
    def jdbcDoubleArrayCreationType = doubleTypeDeclaration;
    def jdbcStringArrayCreationType = stringTypeDeclaration;
    final def arrayCreationType(ptype: Class[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }): String = {
      val rv = ptype.getName() match {
        case "java.lang.Integer" => jdbcIntArrayCreationType
        case "java.lang.Double" => jdbcDoubleArrayCreationType
        case "java.lang.Long" => jdbcLongArrayCreationType
        case "java.lang.String" => jdbcStringArrayCreationType
        case _ => ""
      };
      rv
    };
    def databaseTypeFor(fmd: FieldMetaData): String = fmd.explicitDbTypeDeclaration.getOrElse(fmd.schema.columnTypeFor(fmd, fmd.parentMetaData.viewOrTable.asInstanceOf[Table[_$2] forSome { 
  <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
}]).getOrElse({
      val nativeJdbcType = fmd.nativeJdbcType;
      if (classOf[String].isAssignableFrom(nativeJdbcType))
        stringTypeDeclaration(fmd.length)
      else
        if (classOf[BigDecimal].isAssignableFrom(nativeJdbcType))
          bigDecimalTypeDeclaration(fmd.length, fmd.scale)
        else
          databaseTypeFor(fmd.schema.fieldMapper, nativeJdbcType)
    }));
    def writeColumnDeclaration(fmd: FieldMetaData, isPrimaryKey: Boolean, schema: Schema): String = {
      val dbTypeDeclaration = databaseTypeFor(fmd);
      val sb = new StringBuilder(128);
      sb.append("  ");
      sb.append(quoteName(fmd.columnName));
      sb.append(" ");
      sb.append(dbTypeDeclaration);
      fmd.defaultValue.foreach(((d) => {
        sb.append(" default ");
        val v = convertToJdbcValue(d.value.asInstanceOf[AnyRef]);
        if (v.isInstanceOf[String])
          sb.append("\'".$plus(v).$plus("\'"))
        else
          sb.append(v)
      }));
      if (isPrimaryKey)
        sb.append(" primary key")
      else
        ();
      if (fmd.isOption.unary_$bang)
        sb.append(" not null")
      else
        ();
      if (supportsAutoIncrementInColumnDeclaration.$amp$amp(fmd.isAutoIncremented))
        sb.append(" auto_increment")
      else
        ();
      sb.toString
    };
    def supportsAutoIncrementInColumnDeclaration: Boolean = true;
    def writeCreateTable[T >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[T], sw: StatementWriter, schema: Schema) = {
      sw.write("create table ");
      sw.write(quoteName(t.prefixedName));
      sw.write(" (\n");
      sw.writeIndented(sw.writeLinesWithSeparator(t.posoMetaData.fieldsMetaData.map(((fmd) => writeColumnDeclaration(fmd, fmd.declaredAsPrimaryKeyInSchema, schema))), ","));
      sw.write(")")
    };
    def fillParamsInto(params: Iterable[StatementParam], s: PreparedStatement): scala.Unit = {
      var i = 1;
      params.foreach(((p) => {
        setParamInto(s, p, i);
        i.$plus$eq(1)
      }))
    };
    def setParamInto(s: PreparedStatement, p: StatementParam, i: Int) = p match {
      case ConstantStatementParam((constantTypedExpression @ _)) => s.setObject(i, convertToJdbcValue(constantTypedExpression.nativeJdbcValue))
      case FieldStatementParam((o @ _), (fieldMetaData @ _)) => s.setObject(i, convertToJdbcValue(fieldMetaData.getNativeJdbcValue(o)))
      case ConstantExpressionNodeListParam((v @ _), (constantExpressionNodeList @ _)) => s.setObject(i, convertToJdbcValue(v))
    };
    private def _exec[A >: _root_.scala.Nothing <: _root_.scala.Any](s: AbstractSession, sw: StatementWriter, block: _root_.scala.Function1[Iterable[StatementParam], A], args: Iterable[StatementParam]): A = try {
      if (s.isLoggingEnabled)
        s.log(sw.toString)
      else
        ();
      block(args)
    } catch {
      case (e @ (_: SQLException)) => throw SquerylSQLException("Exception while executing statement : ".$plus(e.getMessage).$plus("\nerrorCode: ").$plus(e.getErrorCode).$plus(", sqlState: ").$plus(e.getSQLState).$plus("\n").$plus(sw.statement).$plus("\njdbcParams:").$plus(args.mkString("[", ",", "]")), e)
    };
    def failureOfStatementRequiresRollback = false;
    protected def execFailSafeExecute(sw: StatementWriter, silenceException: _root_.scala.Function1[SQLException, Boolean]): Unit = {
      val s = Session.currentSession;
      val c = s.connection;
      val stat = createStatement(c);
      val sp = if (failureOfStatementRequiresRollback)
        Some(c.setSavepoint)
      else
        None;
      try {
        if (s.isLoggingEnabled)
          s.log(sw.toString)
        else
          ();
        stat.execute(sw.statement)
      } catch {
        case (e @ (_: SQLException)) => if (silenceException(e))
          sp.foreach(((x$5) => c.rollback(x$5)))
        else
          throw SquerylSQLException("Exception while executing statement,\n".$plus("SQLState:").$plus(e.getSQLState).$plus(", ErrorCode:").$plus(e.getErrorCode).$plus("\n :").$plus(sw.statement), e)
      } finally {
        sp.foreach(((x$6) => c.releaseSavepoint(x$6)));
        Utils.close(stat)
      }
    };
    implicit def string2StatementWriter(s: String) = {
      val sw = new StatementWriter(this);
      sw.write(s);
      sw
    };
    protected def exec[A >: _root_.scala.Nothing <: _root_.scala.Any](s: AbstractSession, sw: StatementWriter)(block: _root_.scala.Function1[Iterable[StatementParam], A]): A = _exec[A](s, sw, block, sw.params);
    protected def prepareStatement(conn: Connection, statement: String): PreparedStatement = conn.prepareStatement(statement);
    protected def createStatement(conn: Connection): Statement = conn.createStatement();
    def executeQuery(s: AbstractSession, sw: StatementWriter) = exec(s, sw)(((params) => {
      val st = prepareStatement(s.connection, sw.statement);
      fillParamsInto(params, st);
      scala.Tuple2(st.executeQuery, st)
    }));
    def executeUpdate(s: Session, sw: StatementWriter): scala.Tuple2[Int, PreparedStatement] = exec(s, sw)(((params) => {
      val st = prepareStatement(s.connection, sw.statement);
      fillParamsInto(params, st);
      scala.Tuple2(st.executeUpdate, st)
    }));
    def executeUpdateAndCloseStatement(s: AbstractSession, sw: StatementWriter): Int = exec(s, sw)(((params) => {
      val st = prepareStatement(s.connection, sw.statement);
      fillParamsInto(params, st);
      try {
        st.executeUpdate
      } finally st.close
    }));
    def executeUpdateForInsert(s: AbstractSession, sw: StatementWriter, ps: PreparedStatement) = exec(s, sw)(((params) => {
      fillParamsInto(params, ps);
      ps.executeUpdate
    }));
    protected def getInsertableFields(fmd: Iterable[FieldMetaData]) = fmd.filter(((fmd) => fmd.isAutoIncremented.unary_$bang.$amp$amp(fmd.isInsertable)));
    def writeInsert[T >: _root_.scala.Nothing <: _root_.scala.Any](o: T, t: Table[T], sw: StatementWriter): Unit = {
      val o_ = o.asInstanceOf[AnyRef];
      val f = getInsertableFields(t.posoMetaData.fieldsMetaData);
      sw.write("insert into ");
      sw.write(quoteName(t.prefixedName));
      sw.write(" (");
      sw.write(f.map(((fmd) => quoteName(fmd.columnName))).mkString(", "));
      sw.write(") values ");
      sw.write(f.map(((fmd) => writeValue(o_, fmd, sw))).mkString("(", ",", ")"))
    };
    def convertToJdbcValue(r: AnyRef): AnyRef = {
      if (r.$eq$eq(null))
        return r
      else
        ();
      var v = r;
      if (v.isInstanceOf[Product1[_$3] forSome { 
        <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
      }])
        v = v.asInstanceOf[Product1[Any]]._1.asInstanceOf[AnyRef]
      else
        ();
      if (v.isInstanceOf[java.util.Date].$amp$amp(v.isInstanceOf[java.sql.Date].unary_$bang).$amp$amp(v.isInstanceOf[Timestamp].unary_$bang))
        v = new java.sql.Date(v.asInstanceOf[java.util.Date].getTime)
      else
        if (v.isInstanceOf[scala.math.BigDecimal])
          v = v.asInstanceOf[scala.math.BigDecimal].bigDecimal
        else
          if (v.isInstanceOf[scala.Enumeration#Value])
            v = v.asInstanceOf[scala.Enumeration#Value].id.asInstanceOf[AnyRef]
          else
            if (v.isInstanceOf[java.util.UUID])
              v = convertFromUuidForJdbc(v.asInstanceOf[UUID])
            else
              ();
      v
    };
    protected def writeValue(o: AnyRef, fmd: FieldMetaData, sw: StatementWriter): String = if (sw.isForDisplay)
      {
        val v = fmd.getNativeJdbcValue(o);
        if (v.$bang$eq(null))
          v.toString
        else
          "null"
      }
    else
      {
        sw.addParam(FieldStatementParam(o, fmd));
        "?"
      };
    def postCreateTable(t: Table[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }, printSinkWhenWriteOnlyMode: Option[_root_.scala.Function1[String, Unit]]) = ();
    def postDropTable(t: Table[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = ();
    def createSequenceName(fmd: FieldMetaData) = "s_".$plus(fmd.parentMetaData.viewOrTable.name).$plus("_").$plus(fmd.columnName);
    def writeConcatFunctionCall(fn: FunctionNode, sw: StatementWriter) = {
      sw.write(fn.name);
      sw.write("(");
      sw.writeNodesWithSeparator(fn.args, ",", false);
      sw.write(")")
    };
    def isFullOuterJoinSupported = true;
    def writeUpdate[T >: _root_.scala.Nothing <: _root_.scala.Any](o: T, t: Table[T], sw: StatementWriter, checkOCC: Boolean) = {
      val o_ = o.asInstanceOf[AnyRef];
      sw.write("update ", quoteName(t.prefixedName), " set ");
      sw.nextLine;
      sw.indent;
      sw.writeLinesWithSeparator(t.posoMetaData.fieldsMetaData.filter(((fmd) => fmd.isIdFieldOfKeyedEntity.unary_$bang.$amp$amp(fmd.isUpdatable))).map(((fmd) => if (fmd.isOptimisticCounter)
        quoteName(fmd.columnName).$plus(" = ").$plus(quoteName(fmd.columnName)).$plus(" + 1 ")
      else
        quoteName(fmd.columnName).$plus(" = ").$plus(writeValue(o_, fmd, sw)))), ",");
      sw.unindent;
      sw.write("where");
      sw.nextLine;
      sw.indent;
      t.posoMetaData.primaryKey.getOrElse(throw new UnsupportedOperationException("writeUpdate was called on an object that does not extend from KeyedEntity[]")).fold(((pkMd) => sw.write(quoteName(pkMd.columnName), " = ", writeValue(o_, pkMd, sw))), ((pkGetter) => Utils.createQuery4WhereClause(t, ((t0: T) => {
        val ck = pkGetter.invoke(t0).asInstanceOf[CompositeKey];
        val fieldWhere = ck._fields.map(((fmd) => quoteName(fmd.columnName).$plus(" = ").$plus(writeValue(o_, fmd, sw))));
        sw.write(fieldWhere.mkString(" and "));
        new EqualityExpression(InternalFieldMapper.intTEF.createConstant(1), InternalFieldMapper.intTEF.createConstant(1))
      }))));
      if (checkOCC)
        t.posoMetaData.optimisticCounter.foreach(((occ) => {
          sw.write(" and ");
          sw.write(quoteName(occ.columnName));
          sw.write(" = ");
          sw.write(writeValue(o_, occ, sw))
        }))
      else
        ()
    };
    def writeDelete[T >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[T], whereClause: Option[ExpressionNode], sw: StatementWriter) = {
      sw.write("delete from ");
      sw.write(quoteName(t.prefixedName));
      if (whereClause.$bang$eq(None))
        {
          sw.nextLine;
          sw.write("where");
          sw.nextLine;
          sw.writeIndented(whereClause.get.write(sw))
        }
      else
        ()
    };
    def convertFromBooleanForJdbc(b: Boolean): Boolean = b;
    def convertToBooleanForJdbc(rs: ResultSet, i: Int): Boolean = rs.getBoolean(i);
    def convertFromUuidForJdbc(u: UUID): AnyRef = u.toString;
    def convertToUuidForJdbc(rs: ResultSet, i: Int): UUID = UUID.fromString(rs.getString(i));
    def writeUpdate(t: Table[_$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }, us: UpdateStatement, sw: StatementWriter) = {
      val colsToUpdate = us.columns.iterator;
      sw.write("update ");
      sw.write(quoteName(t.prefixedName));
      sw.write(" set");
      sw.indent;
      sw.nextLine;
      us.values.zipi.foreach(((z) => {
        val col = colsToUpdate.next;
        sw.write(quoteName(col.columnName));
        sw.write(" = ");
        val v = z.element;
        sw.write("(");
        v.write(sw);
        sw.write(")");
        if (z.isLast.unary_$bang)
          {
            sw.write(",");
            sw.nextLine
          }
        else
          ()
      }));
      if (t.posoMetaData.isOptimistic)
        {
          sw.write(",");
          sw.nextLine;
          val occ = t.posoMetaData.optimisticCounter.get;
          sw.write(quoteName(occ.columnName));
          sw.write(" = ");
          sw.write(quoteName(occ.columnName).$plus(" + 1"))
        }
      else
        ();
      sw.unindent;
      if (us.whereClause.$bang$eq(None))
        {
          sw.nextLine;
          sw.write("Where");
          sw.nextLine;
          sw.writeIndented(us.whereClause.get.write(sw))
        }
      else
        ()
    };
    def nvlToken = "coalesce";
    def writeNvlCall(left: ExpressionNode, right: ExpressionNode, sw: StatementWriter) = {
      sw.write(nvlToken);
      sw.write("(");
      left.write(sw);
      sw.write(",");
      right.write(sw);
      sw.write(")")
    };
    def isNotNullConstraintViolation(e: SQLException): Boolean = false;
    def foreignKeyConstraintName(foreignKeyTable: Table[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }, idWithinSchema: Int) = foreignKeyTable.name.$plus("FK").$plus(idWithinSchema);
    def viewAlias(vn: ViewExpressionNode[_$8] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = if (vn.view.prefix.$bang$eq(None))
      vn.view.prefix.get.$plus("_").$plus(vn.view.name).$plus(vn.uniqueId.get)
    else
      vn.view.name.$plus(vn.uniqueId.get);
    def writeForeignKeyDeclaration(foreignKeyTable: Table[_$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }, foreignKeyColumnName: String, primaryKeyTable: Table[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }, primaryKeyColumnName: String, referentialAction1: Option[ReferentialAction], referentialAction2: Option[ReferentialAction], fkId: Int) = {
      val sb = new StringBuilder(256);
      sb.append("alter table ");
      sb.append(quoteName(foreignKeyTable.prefixedName));
      sb.append(" add constraint ");
      sb.append(quoteName(foreignKeyConstraintName(foreignKeyTable, fkId)));
      sb.append(" foreign key (");
      sb.append(quoteName(foreignKeyColumnName));
      sb.append(") references ");
      sb.append(quoteName(primaryKeyTable.prefixedName));
      sb.append("(");
      sb.append(quoteName(primaryKeyColumnName));
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
    protected def currenSession = Session.currentSession;
    def writeDropForeignKeyStatement(foreignKeyTable: Table[_$11] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
    }, fkName: String) = "alter table ".$plus(quoteName(foreignKeyTable.prefixedName)).$plus(" drop constraint ").$plus(quoteName(fkName));
    def dropForeignKeyStatement(foreignKeyTable: Table[_$12] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }, fkName: String, session: AbstractSession): Unit = execFailSafeExecute(writeDropForeignKeyStatement(foreignKeyTable, fkName), ((e) => true));
    def isTableDoesNotExistException(e: SQLException): Boolean;
    def supportsForeignKeyConstraints = true;
    def writeDropTable(tableName: String) = "drop table ".$plus(quoteName(tableName));
    def dropTable(t: Table[_$13] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = execFailSafeExecute(writeDropTable(t.prefixedName), ((e) => isTableDoesNotExistException(e)));
    def writeCompositePrimaryKeyConstraint(t: Table[_$14] forSome { 
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    }, cols: Iterable[FieldMetaData]) = writeUniquenessConstraint(t, cols);
    def writeUniquenessConstraint(t: Table[_$15] forSome { 
      <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
    }, cols: Iterable[FieldMetaData]) = {
      val sb = new StringBuilder(256);
      sb.append("alter table ");
      sb.append(quoteName(t.prefixedName));
      sb.append(" add constraint ");
      sb.append(quoteName(t.prefixedName.$plus("CPK")));
      sb.append(" unique(");
      sb.append(cols.map(((x$7) => x$7.columnName)).map(((x$8) => quoteName(x$8))).mkString(","));
      sb.append(")");
      sb.toString
    };
    def writeRegexExpression(left: ExpressionNode, pattern: String, sw: StatementWriter) = {
      sw.write("(");
      left.write(sw);
      sw.write(" ~ ?)");
      sw.addParam(ConstantStatementParam(InternalFieldMapper.stringTEF.createConstant(pattern)))
    };
    def writeConcatOperator(left: ExpressionNode, right: ExpressionNode, sw: StatementWriter) = {
      val binaryOpNode = new BinaryOperatorNode(left, right, "||");
      binaryOpNode.doWrite(sw)
    };
    def writeIndexDeclaration(columnDefs: Seq[FieldMetaData], name: Option[String], nameOfCompositeKey: Option[String], isUnique: Boolean) = {
      val sb = new StringBuilder(256);
      sb.append("create ");
      if (isUnique)
        sb.append("unique ")
      else
        ();
      sb.append("index ");
      val tableName = columnDefs.head.parentMetaData.viewOrTable.prefixedName;
      if (name.$bang$eq(None))
        sb.append(quoteName(name.get))
      else
        if (nameOfCompositeKey.$bang$eq(None))
          sb.append(quoteName("idx".$plus(nameOfCompositeKey.get)))
        else
          sb.append(quoteName("idx".$plus(generateAlmostUniqueSuffixWithHash(tableName.$plus("-").$plus(columnDefs.map(((x$9) => x$9.columnName)).mkString("-"))))));
      sb.append(" on ");
      sb.append(quoteName(tableName));
      sb.append(columnDefs.map(((x$10) => x$10.columnName)).map(((x$11) => quoteName(x$11))).mkString(" (", ",", ")"));
      sb.toString
    };
    def generateAlmostUniqueSuffixWithHash(s: String): String = {
      val a32 = new java.util.zip.Adler32();
      a32.update(s.getBytes);
      a32.getValue.toHexString
    };
    def quoteIdentifier(s: String) = s;
    def quoteName(s: String) = s.split('.').map(((x$12) => quoteIdentifier(x$12))).mkString(".");
    def fieldAlias(n: QueryableExpressionNode, fse: FieldSelectElement) = n.alias.$plus("_").$plus(fse.fieldMetaData.columnName);
    def aliasExport(parentOfTarget: QueryableExpressionNode, target: SelectElement) = parentOfTarget.alias.$plus("_").$plus(target.aliasSegment);
    def writeSelectElementAlias(se: SelectElement, sw: StatementWriter) = {
      val a = se.aliasSegment;
      sw.write(quoteName(a))
    };
    def databaseTypeFor(fieldMapper: FieldMapper, c: Class[_$16] forSome { 
      <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
    }): String = {
      val ar = fieldMapper.sampleValueFor(c);
      val decl = if (ar.isInstanceOf[Enumeration#Value])
        intTypeDeclaration
      else
        if (classOf[String].isAssignableFrom(c))
          stringTypeDeclaration
        else
          if (ar.isInstanceOf[java.sql.Timestamp])
            timestampTypeDeclaration
          else
            if (ar.isInstanceOf[java.util.Date])
              dateTypeDeclaration
            else
              if (ar.isInstanceOf[java.lang.Integer])
                intTypeDeclaration
              else
                if (ar.isInstanceOf[java.lang.Long])
                  longTypeDeclaration
                else
                  if (ar.isInstanceOf[java.lang.Boolean])
                    booleanTypeDeclaration
                  else
                    if (ar.isInstanceOf[java.lang.Double])
                      doubleTypeDeclaration
                    else
                      if (ar.isInstanceOf[java.lang.Float])
                        floatTypeDeclaration
                      else
                        if (ar.isInstanceOf[java.util.UUID])
                          uuidTypeDeclaration
                        else
                          if (classOf[scala.Array[Byte]].isAssignableFrom(c))
                            binaryTypeDeclaration
                          else
                            if (classOf[BigDecimal].isAssignableFrom(c))
                              bigDecimalTypeDeclaration
                            else
                              if (classOf[scala.Array[Int]].isAssignableFrom(c))
                                intArrayTypeDeclaration
                              else
                                if (classOf[scala.Array[Long]].isAssignableFrom(c))
                                  longArrayTypeDeclaration
                                else
                                  if (classOf[scala.Array[Double]].isAssignableFrom(c))
                                    doubleArrayTypeDeclaration
                                  else
                                    if (classOf[scala.Array[String]].isAssignableFrom(c))
                                      stringArrayTypeDeclaration
                                    else
                                      Utils.throwError("unsupported type ".$plus(ar.getClass.getCanonicalName));
      decl
    };
    def jdbcTypeConstantFor(c: Class[_$17] forSome { 
      <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = c.getCanonicalName match {
      case "java.lang.String" => Types.VARCHAR
      case "java.math.BigDecimal" => Types.DECIMAL
      case "java.lang.Boolean" => Types.BIT
      case "java.lang.Byte" => Types.TINYINT
      case "java.lang.Integer" => Types.INTEGER
      case "java.lang.Long" => Types.BIGINT
      case "java.lang.Float" => Types.FLOAT
      case "java.lang.Double" => Types.DOUBLE
      case "java.lang.Byte[]" => Types.BINARY
      case "byte[]" => Types.BINARY
      case "java.sql.Date" => Types.DATE
      case "java.util.Date" => Types.DATE
      case "java.sql.Timestamp" => Types.TIMESTAMP
      case "java.util.UUID" => Types.VARCHAR
      case "scala.math.BigDecimal" => Types.VARCHAR
      case (s @ (_: Any)) => throw new RuntimeException("Don\'t know jdbc type for ".$plus(s))
    }
  }
}