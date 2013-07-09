package org.squeryl.internals {
  import org.squeryl.dsl.ast._;
  import org.squeryl.dsl._;
  import org.squeryl._;
  import org.squeryl.dsl.CompositeKey;
  import org.squeryl.{Schema, Session, Table};
  import java.sql._;
  import java.util.UUID;
  abstract trait DatabaseAdapter extends scala.AnyRef {
    def /*DatabaseAdapter*/$init$(): Unit = {
      ()
    };
    class Zip[T >: Nothing <: Any] extends scala.AnyRef {
      <paramaccessor> private[this] val element: T = _;
      <stable> <accessor> <paramaccessor> def element: T = Zip.this.element;
      <paramaccessor> private[this] val isLast: Boolean = _;
      <stable> <accessor> <paramaccessor> def isLast: Boolean = Zip.this.isLast;
      <paramaccessor> private[this] val isFirst: Boolean = _;
      <stable> <accessor> <paramaccessor> def isFirst: Boolean = Zip.this.isFirst;
      def <init>(element: T, isLast: Boolean, isFirst: Boolean): DatabaseAdapter.this.Zip[T] = {
        Zip.super.<init>();
        ()
      }
    };
    class ZipIterable[T >: Nothing <: Any] extends scala.AnyRef {
      <paramaccessor> private[this] val iterable: Iterable[T] = _;
      def <init>(iterable: Iterable[T]): DatabaseAdapter.this.ZipIterable[T] = {
        ZipIterable.super.<init>();
        ()
      };
      private[this] val count: Int = ZipIterable.this.iterable.size;
      <stable> <accessor> def count: Int = ZipIterable.this.count;
      def foreach[U >: Nothing <: Any](f: DatabaseAdapter.this.Zip[T] => U): Unit = {
        var c: Int = 1;
        ZipIterable.this.iterable.foreach[Unit](((i: T) => {
          f.apply(new DatabaseAdapter.this.Zip[T](i, c.==(ZipIterable.this.count), c.==(1)));
          c = c.+(1)
        }))
      };
      def zipi: DatabaseAdapter.this.ZipIterable[T] = this
    };
    implicit def zipIterable[T >: Nothing <: Any](i: Iterable[T]): DatabaseAdapter.this.ZipIterable[T] = new DatabaseAdapter.this.ZipIterable[T](i);
    def writeQuery(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = DatabaseAdapter.this.writeQuery(qen, sw, false, scala.None);
    def verifyDeleteByPK: Boolean = true;
    protected def writeQuery(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter, inverseOrderBy: Boolean, topHint: Option[String]): Unit = {
      sw.write("Select");
      topHint.foreach[String](((x$1: String) => " ".+(sw.write(x$1)).+(" ")));
      if (qen.selectDistinct)
        sw.write(" distinct")
      else
        ();
      sw.nextLine;
      sw.writeIndented(sw.writeNodesWithSeparator(qen.selectList.filter(((e: org.squeryl.dsl.ast.SelectElement) => e.inhibited.unary_!)), ",", true));
      sw.nextLine;
      sw.write("From");
      sw.nextLine;
      if (qen.isJoinForm.unary_!)
        sw.writeIndented({
          DatabaseAdapter.this.zipIterable[org.squeryl.dsl.ast.QueryableExpressionNode](qen.tableExpressions).zipi.foreach[Unit](((z: DatabaseAdapter.this.Zip[org.squeryl.dsl.ast.QueryableExpressionNode]) => {
            z.element.write(sw);
            sw.write(" ");
            sw.write(sw.quoteName(z.element.alias));
            if (z.isLast.unary_!)
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
          val singleNonJoinTableExpression: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode] = qen.tableExpressions.filter(((x$2: org.squeryl.dsl.ast.QueryableExpressionNode) => x$2.isMemberOfJoinList.unary_!));
          scala.this.Predef.assert(singleNonJoinTableExpression.size.==(1), "join query must have exactly one FROM argument, got : ".+(qen.tableExpressions));
          val firstJoinExpr: org.squeryl.dsl.ast.QueryableExpressionNode = singleNonJoinTableExpression.head;
          val restOfJoinExpr: Iterable[org.squeryl.dsl.ast.QueryableExpressionNode] = qen.tableExpressions.filter(((x$3: org.squeryl.dsl.ast.QueryableExpressionNode) => x$3.isMemberOfJoinList));
          firstJoinExpr.write(sw);
          sw.write(" ");
          sw.write(sw.quoteName(firstJoinExpr.alias));
          sw.nextLine;
          DatabaseAdapter.this.zipIterable[org.squeryl.dsl.ast.QueryableExpressionNode](restOfJoinExpr).zipi.foreach[Unit](((z: DatabaseAdapter.this.Zip[org.squeryl.dsl.ast.QueryableExpressionNode]) => {
            DatabaseAdapter.this.writeJoin(z.element, sw);
            if (z.isLast)
              sw.unindent
            else
              ();
            sw.pushPendingNextLine
          }))
        };
      DatabaseAdapter.this.writeEndOfFromHint(qen, sw);
      if (qen.hasUnInhibitedWhereClause)
        {
          sw.write("Where");
          sw.nextLine;
          sw.writeIndented(qen.whereClause.get.write(sw));
          sw.pushPendingNextLine
        }
      else
        ();
      if (qen.groupByClause.isEmpty.unary_!)
        {
          sw.write("Group By");
          sw.nextLine;
          sw.writeIndented(sw.writeNodesWithSeparator(qen.groupByClause.filter(((e: org.squeryl.dsl.ast.ExpressionNode) => e.inhibited.unary_!)), ",", true));
          sw.pushPendingNextLine
        }
      else
        ();
      if (qen.havingClause.isEmpty.unary_!)
        {
          sw.write("Having");
          sw.nextLine;
          sw.writeIndented(sw.writeNodesWithSeparator(scala.this.Option.option2Iterable[org.squeryl.dsl.ast.ExpressionNode](qen.havingClause.filter(((e: org.squeryl.dsl.ast.ExpressionNode) => e.inhibited.unary_!))), ",", true));
          sw.pushPendingNextLine
        }
      else
        ();
      if (qen.orderByClause.isEmpty.unary_!.&&(qen.parent.==(scala.None)))
        {
          sw.write("Order By");
          sw.nextLine;
          val ob0: Iterable[org.squeryl.dsl.ast.ExpressionNode] = qen.orderByClause.filter(((e: org.squeryl.dsl.ast.ExpressionNode) => e.inhibited.unary_!));
          val ob: Iterable[org.squeryl.dsl.ast.ExpressionNode] = if (inverseOrderBy)
            ob0.map[org.squeryl.dsl.ast.OrderByExpression, Iterable[org.squeryl.dsl.ast.OrderByExpression]](((x$4: org.squeryl.dsl.ast.ExpressionNode) => x$4.asInstanceOf[org.squeryl.dsl.ast.OrderByExpression].inverse))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.OrderByExpression])
          else
            ob0;
          sw.writeIndented(sw.writeNodesWithSeparator(ob, ",", true));
          sw.pushPendingNextLine
        }
      else
        ();
      DatabaseAdapter.this.writeEndOfQueryHint(qen, sw);
      DatabaseAdapter.this.writePaginatedQueryDeclaration(qen, sw)
    };
    def writeEndOfQueryHint(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = if (qen.isForUpdate)
      {
        sw.write("for update");
        sw.pushPendingNextLine
      }
    else
      ();
    def writeEndOfFromHint(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = ();
    def writePaginatedQueryDeclaration(qen: org.squeryl.dsl.ast.QueryExpressionElements, sw: org.squeryl.internals.StatementWriter): Unit = qen.page.foreach[Unit](((p: (Int, Int)) => {
      sw.write("limit ");
      sw.write(p._2.toString());
      sw.write(" offset ");
      sw.write(p._1.toString())
    }));
    def writeJoin(queryableExpressionNode: org.squeryl.dsl.ast.QueryableExpressionNode, sw: org.squeryl.internals.StatementWriter): Unit = {
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
    def intTypeDeclaration: String = "int";
    def stringTypeDeclaration: String = "varchar";
    def stringTypeDeclaration(length: Int): String = "varchar(".+(length).+(")");
    def booleanTypeDeclaration: String = "boolean";
    def doubleTypeDeclaration: String = "double";
    def dateTypeDeclaration: String = "date";
    def longTypeDeclaration: String = "bigint";
    def floatTypeDeclaration: String = "real";
    def bigDecimalTypeDeclaration: String = "decimal";
    def bigDecimalTypeDeclaration(precision: Int, scale: Int): String = "decimal(".+(precision).+(",").+(scale).+(")");
    def timestampTypeDeclaration: String = "timestamp";
    def binaryTypeDeclaration: String = "binary";
    def uuidTypeDeclaration: String = "char(36)";
    def intArrayTypeDeclaration: String = DatabaseAdapter.this.intTypeDeclaration.+("[]");
    def longArrayTypeDeclaration: String = DatabaseAdapter.this.longTypeDeclaration.+("[]");
    def doubleArrayTypeDeclaration: String = DatabaseAdapter.this.doubleTypeDeclaration.+("[]");
    def stringArrayTypeDeclaration: String = DatabaseAdapter.this.stringTypeDeclaration.+("[]");
    def jdbcIntArrayCreationType: String = DatabaseAdapter.this.intTypeDeclaration;
    def jdbcLongArrayCreationType: String = DatabaseAdapter.this.longTypeDeclaration;
    def jdbcDoubleArrayCreationType: String = DatabaseAdapter.this.doubleTypeDeclaration;
    def jdbcStringArrayCreationType: String = DatabaseAdapter.this.stringTypeDeclaration;
    final def arrayCreationType(ptype: Class[_]): String = {
      val rv: String = ptype.getName() match {
        case "java.lang.Integer" => DatabaseAdapter.this.jdbcIntArrayCreationType
        case "java.lang.Double" => DatabaseAdapter.this.jdbcDoubleArrayCreationType
        case "java.lang.Long" => DatabaseAdapter.this.jdbcLongArrayCreationType
        case "java.lang.String" => DatabaseAdapter.this.jdbcStringArrayCreationType
        case _ => ""
      };
      rv
    };
    def databaseTypeFor(fmd: org.squeryl.internals.FieldMetaData): String = fmd.explicitDbTypeDeclaration.getOrElse[String](fmd.schema.columnTypeFor(fmd, fmd.parentMetaData.viewOrTable.asInstanceOf[org.squeryl.Table[_]]).getOrElse[String]({
      val nativeJdbcType: Class[_] = fmd.nativeJdbcType;
      if (classOf[java.lang.String].isAssignableFrom(nativeJdbcType))
        DatabaseAdapter.this.stringTypeDeclaration(fmd.length)
      else
        if (classOf[scala.math.BigDecimal].isAssignableFrom(nativeJdbcType))
          DatabaseAdapter.this.bigDecimalTypeDeclaration(fmd.length, fmd.scale)
        else
          DatabaseAdapter.this.databaseTypeFor(fmd.schema.fieldMapper, nativeJdbcType)
    }));
    def writeColumnDeclaration(fmd: org.squeryl.internals.FieldMetaData, isPrimaryKey: Boolean, schema: org.squeryl.Schema): String = {
      val dbTypeDeclaration: String = DatabaseAdapter.this.databaseTypeFor(fmd);
      val sb: StringBuilder = new scala.`package`.StringBuilder(128);
      sb.append("  ");
      sb.append(DatabaseAdapter.this.quoteName(fmd.columnName));
      sb.append(" ");
      sb.append(dbTypeDeclaration);
      fmd.defaultValue.foreach[StringBuilder](((d: org.squeryl.dsl.ast.ConstantTypedExpression[_, _]) => {
        sb.append(" default ");
        val v: AnyRef = DatabaseAdapter.this.convertToJdbcValue(d.value.asInstanceOf[AnyRef]);
        if (v.isInstanceOf[String])
          sb.append("\'".+(v).+("\'"))
        else
          sb.append(v)
      }));
      if (isPrimaryKey)
        sb.append(" primary key")
      else
        ();
      if (fmd.isOption.unary_!)
        sb.append(" not null")
      else
        ();
      if (DatabaseAdapter.this.supportsAutoIncrementInColumnDeclaration.&&(fmd.isAutoIncremented))
        sb.append(" auto_increment")
      else
        ();
      sb.toString()
    };
    def supportsAutoIncrementInColumnDeclaration: Boolean = true;
    def writeCreateTable[T >: Nothing <: Any](t: org.squeryl.Table[T], sw: org.squeryl.internals.StatementWriter, schema: org.squeryl.Schema): Unit = {
      sw.write("create table ");
      sw.write(DatabaseAdapter.this.quoteName(t.prefixedName));
      sw.write(" (\n");
      sw.writeIndented(sw.writeLinesWithSeparator(t.posoMetaData.fieldsMetaData.map[String, Iterable[String]](((fmd: org.squeryl.internals.FieldMetaData) => DatabaseAdapter.this.writeColumnDeclaration(fmd, fmd.declaredAsPrimaryKeyInSchema, schema)))(collection.this.Iterable.canBuildFrom[String]), ","));
      sw.write(")")
    };
    def fillParamsInto(params: Iterable[org.squeryl.internals.StatementParam], s: java.sql.PreparedStatement): Unit = {
      var i: Int = 1;
      params.foreach[Unit](((p: org.squeryl.internals.StatementParam) => {
        DatabaseAdapter.this.setParamInto(s, p, i);
        i = i.+(1)
      }))
    };
    def setParamInto(s: java.sql.PreparedStatement, p: org.squeryl.internals.StatementParam, i: Int): Unit = p match {
      case (p: org.squeryl.dsl.ast.ConstantTypedExpression[_, _])org.squeryl.internals.ConstantStatementParam((constantTypedExpression @ _)) => s.setObject(i, DatabaseAdapter.this.convertToJdbcValue(constantTypedExpression.nativeJdbcValue))
      case (v: AnyRef, fmd: org.squeryl.internals.FieldMetaData)org.squeryl.internals.FieldStatementParam((o @ _), (fieldMetaData @ _)) => s.setObject(i, DatabaseAdapter.this.convertToJdbcValue(fieldMetaData.getNativeJdbcValue(o)))
      case (v: AnyRef, l: org.squeryl.dsl.ast.ConstantExpressionNodeList[_])org.squeryl.internals.ConstantExpressionNodeListParam((v @ _), (constantExpressionNodeList @ _)) => s.setObject(i, DatabaseAdapter.this.convertToJdbcValue(v))
    };
    private def _exec[A >: Nothing <: Any](s: org.squeryl.AbstractSession, sw: org.squeryl.internals.StatementWriter, block: Iterable[org.squeryl.internals.StatementParam] => A, args: Iterable[org.squeryl.internals.StatementParam]): A = try {
      if (s.isLoggingEnabled)
        s.log(sw.toString())
      else
        ();
      block.apply(args)
    } catch {
      case (e @ (_: java.sql.SQLException)) => throw org.squeryl.SquerylSQLException.apply("Exception while executing statement : ".+(e.getMessage()).+("\nerrorCode: ").+(e.getErrorCode()).+(", sqlState: ").+(e.getSQLState()).+("\n").+(sw.statement).+("\njdbcParams:").+(args.mkString("[", ",", "]")), e)
    };
    def failureOfStatementRequiresRollback: Boolean = false;
    protected def execFailSafeExecute(sw: org.squeryl.internals.StatementWriter, silenceException: java.sql.SQLException => Boolean): Unit = {
      val s: org.squeryl.AbstractSession = org.squeryl.Session.currentSession;
      val c: java.sql.Connection = s.connection;
      val stat: java.sql.Statement = DatabaseAdapter.this.createStatement(c);
      val sp: Option[java.sql.Savepoint] = if (DatabaseAdapter.this.failureOfStatementRequiresRollback)
        scala.Some.apply[java.sql.Savepoint](c.setSavepoint())
      else
        scala.None;
      try {
        if (s.isLoggingEnabled)
          s.log(sw.toString())
        else
          ();
        {
          stat.execute(sw.statement);
          ()
        }
      } catch {
        case (e @ (_: java.sql.SQLException)) => if (silenceException.apply(e))
          sp.foreach[Unit](((x$5: java.sql.Savepoint) => c.rollback(x$5)))
        else
          throw org.squeryl.SquerylSQLException.apply("Exception while executing statement,\nSQLState:".+(e.getSQLState()).+(", ErrorCode:").+(e.getErrorCode()).+("\n :").+(sw.statement), e)
      } finally {
        sp.foreach[Unit](((x$6: java.sql.Savepoint) => c.releaseSavepoint(x$6)));
        Utils.close(stat)
      }
    };
    implicit def string2StatementWriter(s: String): org.squeryl.internals.StatementWriter = {
      val sw: org.squeryl.internals.StatementWriter = new StatementWriter(this);
      sw.write(s);
      sw
    };
    protected def exec[A >: Nothing <: Any](s: org.squeryl.AbstractSession, sw: org.squeryl.internals.StatementWriter)(block: Iterable[org.squeryl.internals.StatementParam] => A): A = DatabaseAdapter.this._exec[A](s, sw, block, sw.params);
    protected def prepareStatement(conn: java.sql.Connection, statement: String): java.sql.PreparedStatement = conn.prepareStatement(statement);
    protected def createStatement(conn: java.sql.Connection): java.sql.Statement = conn.createStatement();
    def executeQuery(s: org.squeryl.AbstractSession, sw: org.squeryl.internals.StatementWriter): (java.sql.ResultSet, java.sql.PreparedStatement) = DatabaseAdapter.this.exec[(java.sql.ResultSet, java.sql.PreparedStatement)](s, sw)(((params: Iterable[org.squeryl.internals.StatementParam]) => {
      val st: java.sql.PreparedStatement = DatabaseAdapter.this.prepareStatement(s.connection, sw.statement);
      DatabaseAdapter.this.fillParamsInto(params, st);
      scala.Tuple2.apply[java.sql.ResultSet, java.sql.PreparedStatement](st.executeQuery(), st)
    }));
    def executeUpdate(s: org.squeryl.Session, sw: org.squeryl.internals.StatementWriter): (Int, java.sql.PreparedStatement) = DatabaseAdapter.this.exec[(Int, java.sql.PreparedStatement)](s, sw)(((params: Iterable[org.squeryl.internals.StatementParam]) => {
      val st: java.sql.PreparedStatement = DatabaseAdapter.this.prepareStatement(s.connection, sw.statement);
      DatabaseAdapter.this.fillParamsInto(params, st);
      scala.Tuple2.apply[Int, java.sql.PreparedStatement](st.executeUpdate(), st)
    }));
    def executeUpdateAndCloseStatement(s: org.squeryl.AbstractSession, sw: org.squeryl.internals.StatementWriter): Int = DatabaseAdapter.this.exec[Int](s, sw)(((params: Iterable[org.squeryl.internals.StatementParam]) => {
      val st: java.sql.PreparedStatement = DatabaseAdapter.this.prepareStatement(s.connection, sw.statement);
      DatabaseAdapter.this.fillParamsInto(params, st);
      try {
        st.executeUpdate()
      } finally st.close()
    }));
    def executeUpdateForInsert(s: org.squeryl.AbstractSession, sw: org.squeryl.internals.StatementWriter, ps: java.sql.PreparedStatement): Int = DatabaseAdapter.this.exec[Int](s, sw)(((params: Iterable[org.squeryl.internals.StatementParam]) => {
      DatabaseAdapter.this.fillParamsInto(params, ps);
      ps.executeUpdate()
    }));
    protected def getInsertableFields(fmd: Iterable[org.squeryl.internals.FieldMetaData]): Iterable[org.squeryl.internals.FieldMetaData] = fmd.filter(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isAutoIncremented.unary_!.&&(fmd.isInsertable)));
    def writeInsert[T >: Nothing <: Any](o: T, t: org.squeryl.Table[T], sw: org.squeryl.internals.StatementWriter): Unit = {
      val o_: AnyRef = o.asInstanceOf[AnyRef];
      val f: Iterable[org.squeryl.internals.FieldMetaData] = DatabaseAdapter.this.getInsertableFields(t.posoMetaData.fieldsMetaData);
      sw.write("insert into ");
      sw.write(DatabaseAdapter.this.quoteName(t.prefixedName));
      sw.write(" (");
      sw.write(f.map[String, Iterable[String]](((fmd: org.squeryl.internals.FieldMetaData) => DatabaseAdapter.this.quoteName(fmd.columnName)))(collection.this.Iterable.canBuildFrom[String]).mkString(", "));
      sw.write(") values ");
      sw.write(f.map[String, Iterable[String]](((fmd: org.squeryl.internals.FieldMetaData) => DatabaseAdapter.this.writeValue(o_, fmd, sw)))(collection.this.Iterable.canBuildFrom[String]).mkString("(", ",", ")"))
    };
    def convertToJdbcValue(r: AnyRef): AnyRef = {
      if (r.==(null))
        return r
      else
        ();
      var v: AnyRef = r;
      if (v.isInstanceOf[Product1[_]])
        v = v.asInstanceOf[Product1[Any]]._1.asInstanceOf[AnyRef]
      else
        ();
      if (v.isInstanceOf[java.util.Date].&&(v.isInstanceOf[java.sql.Date].unary_!).&&(v.isInstanceOf[java.sql.Timestamp].unary_!))
        v = new java.sql.Date(v.asInstanceOf[java.util.Date].getTime())
      else
        if (v.isInstanceOf[scala.math.BigDecimal])
          v = v.asInstanceOf[scala.math.BigDecimal].bigDecimal
        else
          if (v.isInstanceOf[Enumeration#Value])
            v = v.asInstanceOf[Enumeration#Value].id.asInstanceOf[AnyRef]
          else
            if (v.isInstanceOf[java.util.UUID])
              v = DatabaseAdapter.this.convertFromUuidForJdbc(v.asInstanceOf[java.util.UUID])
            else
              ();
      v
    };
    protected def writeValue(o: AnyRef, fmd: org.squeryl.internals.FieldMetaData, sw: org.squeryl.internals.StatementWriter): String = if (sw.isForDisplay)
      {
        val v: AnyRef = fmd.getNativeJdbcValue(o);
        if (v.!=(null))
          v.toString()
        else
          "null"
      }
    else
      {
        sw.addParam(FieldStatementParam.apply(o, fmd));
        "?"
      };
    def postCreateTable(t: org.squeryl.Table[_], printSinkWhenWriteOnlyMode: Option[String => Unit]): Unit = ();
    def postDropTable(t: org.squeryl.Table[_]): Unit = ();
    def createSequenceName(fmd: org.squeryl.internals.FieldMetaData): String = "s_".+(fmd.parentMetaData.viewOrTable.name).+("_").+(fmd.columnName);
    def writeConcatFunctionCall(fn: org.squeryl.dsl.ast.FunctionNode, sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write(fn.name);
      sw.write("(");
      sw.writeNodesWithSeparator(fn.args, ",", false);
      sw.write(")")
    };
    def isFullOuterJoinSupported: Boolean = true;
    def writeUpdate[T >: Nothing <: Any](o: T, t: org.squeryl.Table[T], sw: org.squeryl.internals.StatementWriter, checkOCC: Boolean): Unit = {
      val o_: AnyRef = o.asInstanceOf[AnyRef];
      sw.write("update ", DatabaseAdapter.this.quoteName(t.prefixedName), " set ");
      sw.nextLine;
      sw.indent;
      sw.writeLinesWithSeparator(t.posoMetaData.fieldsMetaData.filter(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isIdFieldOfKeyedEntity.unary_!.&&(fmd.isUpdatable))).map[String, Iterable[String]](((fmd: org.squeryl.internals.FieldMetaData) => if (fmd.isOptimisticCounter)
        DatabaseAdapter.this.quoteName(fmd.columnName).+(" = ").+(DatabaseAdapter.this.quoteName(fmd.columnName)).+(" + 1 ")
      else
        DatabaseAdapter.this.quoteName(fmd.columnName).+(" = ").+(DatabaseAdapter.this.writeValue(o_, fmd, sw))))(collection.this.Iterable.canBuildFrom[String]), ",");
      sw.unindent;
      sw.write("where");
      sw.nextLine;
      sw.indent;
      t.posoMetaData.primaryKey.getOrElse[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]](throw new scala.`package`.UnsupportedOperationException("writeUpdate was called on an object that does not extend from KeyedEntity[]")).fold[Any](((pkMd: org.squeryl.internals.FieldMetaData) => sw.write(DatabaseAdapter.this.quoteName(pkMd.columnName), " = ", DatabaseAdapter.this.writeValue(o_, pkMd, sw))), ((pkGetter: java.lang.reflect.Method) => Utils.createQuery4WhereClause[T](t, ((t0: T) => {
        val ck: org.squeryl.dsl.CompositeKey = pkGetter.invoke(t0).asInstanceOf[org.squeryl.dsl.CompositeKey];
        val fieldWhere: Seq[String] = ck._fields.map[String, Seq[String]](((fmd: org.squeryl.internals.FieldMetaData) => DatabaseAdapter.this.quoteName(fmd.columnName).+(" = ").+(DatabaseAdapter.this.writeValue(o_, fmd, sw))))(collection.this.Seq.canBuildFrom[String]);
        sw.write(fieldWhere.mkString(" and "));
        new org.squeryl.dsl.ast.EqualityExpression(org.squeryl.InternalFieldMapper.intTEF.createConstant(1), org.squeryl.InternalFieldMapper.intTEF.createConstant(1))
      }))));
      if (checkOCC)
        t.posoMetaData.optimisticCounter.foreach[Unit](((occ: org.squeryl.internals.FieldMetaData) => {
          sw.write(" and ");
          sw.write(DatabaseAdapter.this.quoteName(occ.columnName));
          sw.write(" = ");
          sw.write(DatabaseAdapter.this.writeValue(o_, occ, sw))
        }))
      else
        ()
    };
    def writeDelete[T >: Nothing <: Any](t: org.squeryl.Table[T], whereClause: Option[org.squeryl.dsl.ast.ExpressionNode], sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write("delete from ");
      sw.write(DatabaseAdapter.this.quoteName(t.prefixedName));
      if (whereClause.!=(scala.None))
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
    def convertToBooleanForJdbc(rs: java.sql.ResultSet, i: Int): Boolean = rs.getBoolean(i);
    def convertFromUuidForJdbc(u: java.util.UUID): AnyRef = u.toString();
    def convertToUuidForJdbc(rs: java.sql.ResultSet, i: Int): java.util.UUID = java.util.UUID.fromString(rs.getString(i));
    def writeUpdate(t: org.squeryl.Table[_], us: org.squeryl.dsl.ast.UpdateStatement, sw: org.squeryl.internals.StatementWriter): Unit = {
      val colsToUpdate: Iterator[org.squeryl.internals.FieldMetaData] = us.columns.iterator;
      sw.write("update ");
      sw.write(DatabaseAdapter.this.quoteName(t.prefixedName));
      sw.write(" set");
      sw.indent;
      sw.nextLine;
      DatabaseAdapter.this.zipIterable[org.squeryl.dsl.ast.ExpressionNode](us.values).zipi.foreach[Unit](((z: DatabaseAdapter.this.Zip[org.squeryl.dsl.ast.ExpressionNode]) => {
        val col: org.squeryl.internals.FieldMetaData = colsToUpdate.next();
        sw.write(DatabaseAdapter.this.quoteName(col.columnName));
        sw.write(" = ");
        val v: org.squeryl.dsl.ast.ExpressionNode = z.element;
        sw.write("(");
        v.write(sw);
        sw.write(")");
        if (z.isLast.unary_!)
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
          val occ: org.squeryl.internals.FieldMetaData = t.posoMetaData.optimisticCounter.get;
          sw.write(DatabaseAdapter.this.quoteName(occ.columnName));
          sw.write(" = ");
          sw.write(DatabaseAdapter.this.quoteName(occ.columnName).+(" + 1"))
        }
      else
        ();
      sw.unindent;
      if (us.whereClause.!=(scala.None))
        {
          sw.nextLine;
          sw.write("Where");
          sw.nextLine;
          sw.writeIndented(us.whereClause.get.write(sw))
        }
      else
        ()
    };
    def nvlToken: String = "coalesce";
    def writeNvlCall(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode, sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write(DatabaseAdapter.this.nvlToken);
      sw.write("(");
      left.write(sw);
      sw.write(",");
      right.write(sw);
      sw.write(")")
    };
    def isNotNullConstraintViolation(e: java.sql.SQLException): Boolean = false;
    def foreignKeyConstraintName(foreignKeyTable: org.squeryl.Table[_], idWithinSchema: Int): String = foreignKeyTable.name.+("FK").+(idWithinSchema);
    def viewAlias(vn: org.squeryl.dsl.ast.ViewExpressionNode[_]): String = if (vn.view.prefix.!=(scala.None))
      vn.view.prefix.get.+("_").+(vn.view.name).+(vn.uniqueId.get)
    else
      vn.view.name.+(vn.uniqueId.get);
    def writeForeignKeyDeclaration(foreignKeyTable: org.squeryl.Table[_], foreignKeyColumnName: String, primaryKeyTable: org.squeryl.Table[_], primaryKeyColumnName: String, referentialAction1: Option[org.squeryl.ReferentialAction], referentialAction2: Option[org.squeryl.ReferentialAction], fkId: Int): String = {
      val sb: StringBuilder = new scala.`package`.StringBuilder(256);
      sb.append("alter table ");
      sb.append(DatabaseAdapter.this.quoteName(foreignKeyTable.prefixedName));
      sb.append(" add constraint ");
      sb.append(DatabaseAdapter.this.quoteName(DatabaseAdapter.this.foreignKeyConstraintName(foreignKeyTable, fkId)));
      sb.append(" foreign key (");
      sb.append(DatabaseAdapter.this.quoteName(foreignKeyColumnName));
      sb.append(") references ");
      sb.append(DatabaseAdapter.this.quoteName(primaryKeyTable.prefixedName));
      sb.append("(");
      sb.append(DatabaseAdapter.this.quoteName(primaryKeyColumnName));
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
    protected def currenSession: org.squeryl.AbstractSession = org.squeryl.Session.currentSession;
    def writeDropForeignKeyStatement(foreignKeyTable: org.squeryl.Table[_], fkName: String): String = "alter table ".+(DatabaseAdapter.this.quoteName(foreignKeyTable.prefixedName)).+(" drop constraint ").+(DatabaseAdapter.this.quoteName(fkName));
    def dropForeignKeyStatement(foreignKeyTable: org.squeryl.Table[_], fkName: String, session: org.squeryl.AbstractSession): Unit = DatabaseAdapter.this.execFailSafeExecute(DatabaseAdapter.this.string2StatementWriter(DatabaseAdapter.this.writeDropForeignKeyStatement(foreignKeyTable, fkName)), ((e: java.sql.SQLException) => true));
    def isTableDoesNotExistException(e: java.sql.SQLException): Boolean;
    def supportsForeignKeyConstraints: Boolean = true;
    def writeDropTable(tableName: String): String = "drop table ".+(DatabaseAdapter.this.quoteName(tableName));
    def dropTable(t: org.squeryl.Table[_]): Unit = DatabaseAdapter.this.execFailSafeExecute(DatabaseAdapter.this.string2StatementWriter(DatabaseAdapter.this.writeDropTable(t.prefixedName)), ((e: java.sql.SQLException) => DatabaseAdapter.this.isTableDoesNotExistException(e)));
    def writeCompositePrimaryKeyConstraint(t: org.squeryl.Table[_], cols: Iterable[org.squeryl.internals.FieldMetaData]): String = DatabaseAdapter.this.writeUniquenessConstraint(t, cols);
    def writeUniquenessConstraint(t: org.squeryl.Table[_], cols: Iterable[org.squeryl.internals.FieldMetaData]): String = {
      val sb: StringBuilder = new scala.`package`.StringBuilder(256);
      sb.append("alter table ");
      sb.append(DatabaseAdapter.this.quoteName(t.prefixedName));
      sb.append(" add constraint ");
      sb.append(DatabaseAdapter.this.quoteName(t.prefixedName.+("CPK")));
      sb.append(" unique(");
      sb.append(cols.map[String, Iterable[String]](((x$7: org.squeryl.internals.FieldMetaData) => x$7.columnName))(collection.this.Iterable.canBuildFrom[String]).map[String, Iterable[String]](((x$8: String) => DatabaseAdapter.this.quoteName(x$8)))(collection.this.Iterable.canBuildFrom[String]).mkString(","));
      sb.append(")");
      sb.toString()
    };
    def writeRegexExpression(left: org.squeryl.dsl.ast.ExpressionNode, pattern: String, sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write("(");
      left.write(sw);
      sw.write(" ~ ?)");
      sw.addParam(ConstantStatementParam.apply(org.squeryl.InternalFieldMapper.stringTEF.createConstant(pattern)))
    };
    def writeConcatOperator(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode, sw: org.squeryl.internals.StatementWriter): Unit = {
      val binaryOpNode: org.squeryl.dsl.ast.BinaryOperatorNode = new org.squeryl.dsl.ast.BinaryOperatorNode(left, right, "||", ast.this.BinaryOperatorNode.<init>$default$4);
      binaryOpNode.doWrite(sw)
    };
    def writeIndexDeclaration(columnDefs: Seq[org.squeryl.internals.FieldMetaData], name: Option[String], nameOfCompositeKey: Option[String], isUnique: Boolean): String = {
      val sb: StringBuilder = new scala.`package`.StringBuilder(256);
      sb.append("create ");
      if (isUnique)
        sb.append("unique ")
      else
        ();
      sb.append("index ");
      val tableName: String = columnDefs.head.parentMetaData.viewOrTable.prefixedName;
      if (name.!=(scala.None))
        sb.append(DatabaseAdapter.this.quoteName(name.get))
      else
        if (nameOfCompositeKey.!=(scala.None))
          sb.append(DatabaseAdapter.this.quoteName("idx".+(nameOfCompositeKey.get)))
        else
          sb.append(DatabaseAdapter.this.quoteName("idx".+(DatabaseAdapter.this.generateAlmostUniqueSuffixWithHash(tableName.+("-").+(columnDefs.map[String, Seq[String]](((x$9: org.squeryl.internals.FieldMetaData) => x$9.columnName))(collection.this.Seq.canBuildFrom[String]).mkString("-"))))));
      sb.append(" on ");
      sb.append(DatabaseAdapter.this.quoteName(tableName));
      sb.append(columnDefs.map[String, Seq[String]](((x$10: org.squeryl.internals.FieldMetaData) => x$10.columnName))(collection.this.Seq.canBuildFrom[String]).map[String, Seq[String]](((x$11: String) => DatabaseAdapter.this.quoteName(x$11)))(collection.this.Seq.canBuildFrom[String]).mkString(" (", ",", ")"));
      sb.toString()
    };
    def generateAlmostUniqueSuffixWithHash(s: String): String = {
      val a32: java.util.zip.Adler32 = new java.util.zip.Adler32();
      a32.update(s.getBytes());
      scala.this.Predef.longWrapper(a32.getValue()).toHexString
    };
    def quoteIdentifier(s: String): String = s;
    def quoteName(s: String): String = scala.this.Predef.refArrayOps[String](scala.this.Predef.refArrayOps[String](scala.this.Predef.augmentString(s).split('.')).map[String, Array[String]](((x$12: String) => DatabaseAdapter.this.quoteIdentifier(x$12)))(scala.this.Array.canBuildFrom[String](ClassTag.apply[String](classOf[java.lang.String])))).mkString(".");
    def fieldAlias(n: org.squeryl.dsl.ast.QueryableExpressionNode, fse: org.squeryl.dsl.ast.FieldSelectElement): String = n.alias.+("_").+(fse.fieldMetaData.columnName);
    def aliasExport(parentOfTarget: org.squeryl.dsl.ast.QueryableExpressionNode, target: org.squeryl.dsl.ast.SelectElement): String = parentOfTarget.alias.+("_").+(target.aliasSegment);
    def writeSelectElementAlias(se: org.squeryl.dsl.ast.SelectElement, sw: org.squeryl.internals.StatementWriter): Unit = {
      val a: String = se.aliasSegment;
      sw.write(DatabaseAdapter.this.quoteName(a))
    };
    def databaseTypeFor(fieldMapper: org.squeryl.internals.FieldMapper, c: Class[_]): String = {
      val ar: AnyRef = fieldMapper.sampleValueFor(c);
      val decl: String = if (ar.isInstanceOf[Enumeration#Value])
        DatabaseAdapter.this.intTypeDeclaration
      else
        if (classOf[java.lang.String].isAssignableFrom(c))
          DatabaseAdapter.this.stringTypeDeclaration
        else
          if (ar.isInstanceOf[java.sql.Timestamp])
            DatabaseAdapter.this.timestampTypeDeclaration
          else
            if (ar.isInstanceOf[java.util.Date])
              DatabaseAdapter.this.dateTypeDeclaration
            else
              if (ar.isInstanceOf[Integer])
                DatabaseAdapter.this.intTypeDeclaration
              else
                if (ar.isInstanceOf[Long])
                  DatabaseAdapter.this.longTypeDeclaration
                else
                  if (ar.isInstanceOf[Boolean])
                    DatabaseAdapter.this.booleanTypeDeclaration
                  else
                    if (ar.isInstanceOf[Double])
                      DatabaseAdapter.this.doubleTypeDeclaration
                    else
                      if (ar.isInstanceOf[Float])
                        DatabaseAdapter.this.floatTypeDeclaration
                      else
                        if (ar.isInstanceOf[java.util.UUID])
                          DatabaseAdapter.this.uuidTypeDeclaration
                        else
                          if (classOf[[B].isAssignableFrom(c))
                            DatabaseAdapter.this.binaryTypeDeclaration
                          else
                            if (classOf[scala.math.BigDecimal].isAssignableFrom(c))
                              DatabaseAdapter.this.bigDecimalTypeDeclaration
                            else
                              if (classOf[[I].isAssignableFrom(c))
                                DatabaseAdapter.this.intArrayTypeDeclaration
                              else
                                if (classOf[[J].isAssignableFrom(c))
                                  DatabaseAdapter.this.longArrayTypeDeclaration
                                else
                                  if (classOf[[D].isAssignableFrom(c))
                                    DatabaseAdapter.this.doubleArrayTypeDeclaration
                                  else
                                    if (classOf[[Ljava.lang.String;].isAssignableFrom(c))
                                      DatabaseAdapter.this.stringArrayTypeDeclaration
                                    else
                                      Utils.throwError("unsupported type ".+(ar.getClass().getCanonicalName()));
      decl
    };
    def jdbcTypeConstantFor(c: Class[_]): Int = c.getCanonicalName() match {
      case "java.lang.String" => 12
      case "java.math.BigDecimal" => 3
      case "java.lang.Boolean" => -7
      case "java.lang.Byte" => -6
      case "java.lang.Integer" => 4
      case "java.lang.Long" => -5
      case "java.lang.Float" => 6
      case "java.lang.Double" => 8
      case "java.lang.Byte[]" => -2
      case "byte[]" => -2
      case "java.sql.Date" => 91
      case "java.util.Date" => 91
      case "java.sql.Timestamp" => 93
      case "java.util.UUID" => 12
      case "scala.math.BigDecimal" => 12
      case (s @ (_: Any)) => throw new scala.`package`.RuntimeException("Don\'t know jdbc type for ".+(s))
    }
  }
}