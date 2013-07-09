package org.squeryl {
  import dsl._;
  import dsl.ast._;
  import internals._;
  import scala.reflect.Manifest;
  import java.sql.SQLException;
  import java.io.PrintWriter;
  import scala.collection.mutable.{HashMap, HashSet, ArrayBuffer};
  import org.squeryl.internals.FieldMapper;
  class Schema extends scala.AnyRef {
    <paramaccessor> private[this] val fieldMapper: org.squeryl.internals.FieldMapper = _;
    implicit <stable> <accessor> <paramaccessor> def fieldMapper: org.squeryl.internals.FieldMapper = Schema.this.fieldMapper;
    def <init>()(implicit fieldMapper: org.squeryl.internals.FieldMapper): org.squeryl.Schema = {
      Schema.super.<init>();
      ()
    };
    implicit protected def thisSchema: org.squeryl.Schema = this;
    private[this] val _tables: scala.collection.mutable.ArrayBuffer[org.squeryl.Table[_]] = new scala.collection.mutable.ArrayBuffer[org.squeryl.Table[_]]();
    <stable> <accessor> private def _tables: scala.collection.mutable.ArrayBuffer[org.squeryl.Table[_]] = Schema.this._tables;
    def tables: Seq[org.squeryl.Table[_]] = Schema.this._tables.toSeq;
    private[this] val _tableTypes: scala.collection.mutable.HashMap[Class[_],org.squeryl.Table[_]] = new scala.collection.mutable.HashMap[Class[_],org.squeryl.Table[_]]();
    <stable> <accessor> private def _tableTypes: scala.collection.mutable.HashMap[Class[_],org.squeryl.Table[_]] = Schema.this._tableTypes;
    private[this] val _oneToManyRelations: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.OneToManyRelation[_, _]] = new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.OneToManyRelation[_, _]]();
    <stable> <accessor> private def _oneToManyRelations: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.OneToManyRelation[_, _]] = Schema.this._oneToManyRelations;
    private[this] val _manyToManyRelations: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ManyToManyRelation[_, _, _]] = new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ManyToManyRelation[_, _, _]]();
    <stable> <accessor> private def _manyToManyRelations: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ManyToManyRelation[_, _, _]] = Schema.this._manyToManyRelations;
    private[this] val _columnGroupAttributeAssignments: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.ColumnGroupAttributeAssignment] = new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.ColumnGroupAttributeAssignment]();
    <stable> <accessor> private def _columnGroupAttributeAssignments: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.ColumnGroupAttributeAssignment] = Schema.this._columnGroupAttributeAssignments;
    private[this] val _namingScope: scala.collection.mutable.HashSet[String] = new scala.collection.mutable.HashSet[String]();
    <stable> <accessor> private[squeryl] def _namingScope: scala.collection.mutable.HashSet[String] = Schema.this._namingScope;
    private[squeryl] def _addRelation(r: org.squeryl.dsl.OneToManyRelation[_, _]): Unit = Schema.this._oneToManyRelations.append(r);
    private[squeryl] def _addRelation(r: org.squeryl.dsl.ManyToManyRelation[_, _, _]): Unit = Schema.this._manyToManyRelations.append(r);
    private def _dbAdapter: org.squeryl.internals.DatabaseAdapter = Session.currentSession.databaseAdapter;
    private def _activeForeignKeySpecs: scala.collection.mutable.ArrayBuffer[(org.squeryl.Table[_], org.squeryl.Table[_], org.squeryl.ForeignKeyDeclaration)] = {
      val res: scala.collection.mutable.ArrayBuffer[(org.squeryl.Table[_], org.squeryl.Table[_], org.squeryl.ForeignKeyDeclaration)] = new scala.collection.mutable.ArrayBuffer[(org.squeryl.Table[_], org.squeryl.Table[_], org.squeryl.ForeignKeyDeclaration)]();
      Schema.this._oneToManyRelations.withFilter(((r: org.squeryl.dsl.OneToManyRelation[_, _]) => r.foreignKeyDeclaration._isActive)).foreach[Unit](((r: org.squeryl.dsl.OneToManyRelation[_, _]) => res.append(scala.Tuple3.apply[org.squeryl.Table[_$6], org.squeryl.Table[_$5], org.squeryl.ForeignKeyDeclaration](r.rightTable, r.leftTable, r.foreignKeyDeclaration))));
      Schema.this._manyToManyRelations.foreach[Unit](((r: org.squeryl.dsl.ManyToManyRelation[_, _, _]) => {
        if (r.leftForeignKeyDeclaration._isActive)
          res.append(scala.Tuple3.apply[org.squeryl.Table[_$9], org.squeryl.Table[_$7], org.squeryl.ForeignKeyDeclaration](r.thisTable, r.leftTable, r.leftForeignKeyDeclaration))
        else
          ();
        if (r.rightForeignKeyDeclaration._isActive)
          res.append(scala.Tuple3.apply[org.squeryl.Table[_$9], org.squeryl.Table[_$8], org.squeryl.ForeignKeyDeclaration](r.thisTable, r.rightTable, r.rightForeignKeyDeclaration))
        else
          ()
      }));
      res
    };
    def findTablesFor[A >: Nothing <: Any](a: A): Iterable[org.squeryl.Table[A]] = {
      val c: Class[_ <: AnyRef] = a.asInstanceOf[AnyRef].getClass();
      Schema.this._tables.filter(((x$1: org.squeryl.Table[_]) => x$1.posoMetaData.clasz.==(c))).asInstanceOf[Iterable[org.squeryl.Table[A]]]
    };
    def findAllTablesFor[A >: Nothing <: Any](c: Class[A]): Traversable[org.squeryl.Table[_]] = Schema.this._tables.filter(((t: org.squeryl.Table[_]) => c.isAssignableFrom(t.posoMetaData.clasz))).asInstanceOf[Traversable[org.squeryl.Table[_]]];
    object NamingConventionTransforms extends scala.AnyRef {
      def <init>(): Schema.this.NamingConventionTransforms.type = {
        NamingConventionTransforms.super.<init>();
        ()
      };
      @deprecated("use snakify() instead as of 0.9.5beta", "0.9.5") def camelCase2underScore(name: String): String = scala.this.Predef.augmentString(name).toList.map[Any, List[Any]](((c: Char) => if (scala.this.Predef.charWrapper(c).isUpper)
  "_".+(c)
else
  c))(immutable.this.List.canBuildFrom[Any]).mkString;
      def snakify(name: String): String = name.replaceAll("^([^A-Za-z_])", "_$1").replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase()
    };
    def columnNameFromPropertyName(propertyName: String): String = propertyName;
    def tableNameFromClassName(tableName: String): String = tableName;
    def name: Option[String] = scala.None;
    def printDdl: Unit = Schema.this.printDdl(((x$2: String) => scala.this.Predef.println(x$2)));
    def printDdl(pw: java.io.PrintWriter): Unit = Schema.this.printDdl(((x$3: String) => pw.println(x$3)));
    def printDdl(statementHandler: String => Unit): Unit = {
      statementHandler.apply("-- table declarations :");
      Schema.this._tables.foreach[Unit](((t: org.squeryl.Table[_]) => {
        val sw: org.squeryl.internals.StatementWriter = new internals.StatementWriter(true, Schema.this._dbAdapter);
        Schema.this._dbAdapter.writeCreateTable[_$1](t, sw, this);
        statementHandler.apply(sw.statement.+(";"));
        Schema.this._dbAdapter.postCreateTable(t, scala.Some.apply[String => Unit](statementHandler));
        val indexDecl: List[String] = Schema.this._indexDeclarationsFor(t);
        if (indexDecl.!=(immutable.this.Nil))
          statementHandler.apply("-- indexes on ".+(t.prefixedName))
        else
          ();
        indexDecl.foreach[Unit](((i: String) => statementHandler.apply(i.+(";"))))
      }));
      val constraints: List[String] = Schema.this._foreignKeyConstraints.toList;
      if (constraints.!=(immutable.this.Nil))
        statementHandler.apply("-- foreign key constraints :")
      else
        ();
      constraints.foreach[Unit](((fkc: String) => statementHandler.apply(fkc.+(";"))));
      val compositePKs: List[(org.squeryl.Table[_], Iterable[org.squeryl.internals.FieldMetaData])] = Schema.this._allCompositePrimaryKeys.toList;
      if (compositePKs.!=(immutable.this.Nil))
        statementHandler.apply("-- composite key indexes :")
      else
        ();
      compositePKs.foreach[Unit](((cpk: (org.squeryl.Table[_], Iterable[org.squeryl.internals.FieldMetaData])) => {
        val createConstraintStmt: String = Schema.this._dbAdapter.writeCompositePrimaryKeyConstraint(cpk._1, cpk._2);
        statementHandler.apply(createConstraintStmt.+(";"))
      }));
      val columnGroupIndexes: List[String] = Schema.this._writeColumnGroupAttributeAssignments.toList;
      if (columnGroupIndexes.!=(immutable.this.Nil))
        statementHandler.apply("-- column group indexes :")
      else
        ();
      columnGroupIndexes.foreach[Unit](((decl: String) => statementHandler.apply(decl.+(";"))))
    };
    def drop: Unit = {
      if (Schema.this._dbAdapter.supportsForeignKeyConstraints)
        Schema.this._dropForeignKeyConstraints
      else
        ();
      val s: java.sql.Statement = Session.currentSession.connection.createStatement();
      val con: java.sql.Connection = Session.currentSession.connection;
      Schema.this._tables.foreach[Unit](((t: org.squeryl.Table[_]) => {
        Schema.this._dbAdapter.dropTable(t);
        Schema.this._dbAdapter.postDropTable(t)
      }))
    };
    def create: Unit = {
      Schema.this._createTables;
      if (Schema.this._dbAdapter.supportsForeignKeyConstraints)
        Schema.this._declareForeignKeyConstraints
      else
        ();
      Schema.this._createConstraintsOfCompositePKs;
      Schema.this.createColumnGroupConstraintsAndIndexes
    };
    private def _indexDeclarationsFor(t: org.squeryl.Table[_]): List[String] = {
      val d0: Iterable[Option[String]] = t.posoMetaData.fieldsMetaData.map[Option[String], Iterable[Option[String]]](((fmd: org.squeryl.internals.FieldMetaData) => Schema.this._writeIndexDeclarationIfApplicable(fmd.columnAttributes.toSeq, collection.this.Seq.apply[org.squeryl.internals.FieldMetaData](fmd), scala.None)))(collection.this.Iterable.canBuildFrom[Option[String]]);
      d0.filter(((x$4: Option[String]) => x$4.!=(scala.None))).map[String, Iterable[String]](((x$5: Option[String]) => x$5.get))(collection.this.Iterable.canBuildFrom[String]).toList
    };
    private def _writeColumnGroupAttributeAssignments: Seq[String] = Schema.this._columnGroupAttributeAssignments.map[String, Seq[String]](((cgaa: org.squeryl.dsl.ast.ColumnGroupAttributeAssignment) => Schema.this._writeIndexDeclarationIfApplicable(cgaa.columnAttributes, cgaa.columns, cgaa.name).getOrElse[String](org.squeryl.internals.Utils.throwError("emtpy attribute list should not be possible to create with DSL (Squeryl bug)."))))(mutable.this.ArrayBuffer.canBuildFrom[String]);
    private def _writeIndexDeclarationIfApplicable(columnAttributes: Seq[org.squeryl.internals.ColumnAttribute], cols: Seq[org.squeryl.internals.FieldMetaData], name: Option[String]): Option[String] = {
      val unique: Option[org.squeryl.internals.ColumnAttribute] = columnAttributes.find(((x$6: org.squeryl.internals.ColumnAttribute) => x$6.isInstanceOf[org.squeryl.internals.Unique]));
      val indexed: Option[org.squeryl.internals.Indexed] = columnAttributes.find(((x$7: org.squeryl.internals.ColumnAttribute) => x$7.isInstanceOf[org.squeryl.internals.Indexed])).flatMap[org.squeryl.internals.Indexed](((i: org.squeryl.internals.ColumnAttribute) => i match {
        case (idx @ (_: org.squeryl.internals.Indexed)) => scala.Some.apply[org.squeryl.internals.Indexed](idx)
        case _ => scala.None
      }));
      scala.Tuple2.apply[Option[org.squeryl.internals.ColumnAttribute], Option[org.squeryl.internals.Indexed]](unique, indexed) match {
        case (_1: Option[org.squeryl.internals.ColumnAttribute], _2: Option[org.squeryl.internals.Indexed])(Option[org.squeryl.internals.ColumnAttribute], Option[org.squeryl.internals.Indexed])(scala.None, scala.None) => scala.None
        case (_1: Option[org.squeryl.internals.ColumnAttribute], _2: Option[org.squeryl.internals.Indexed])(Option[org.squeryl.internals.ColumnAttribute], Option[org.squeryl.internals.Indexed])((x: org.squeryl.internals.ColumnAttribute)Some[org.squeryl.internals.ColumnAttribute](_), scala.None) => scala.Some.apply[String](Schema.this._dbAdapter.writeIndexDeclaration(cols, scala.None, name, true))
        case (_1: Option[org.squeryl.internals.ColumnAttribute], _2: Option[org.squeryl.internals.Indexed])(Option[org.squeryl.internals.ColumnAttribute], Option[org.squeryl.internals.Indexed])(scala.None, (x: org.squeryl.internals.Indexed)Some[org.squeryl.internals.Indexed]((nameOfIndex: Option[String])org.squeryl.internals.Indexed((idxName @ _)))) => scala.Some.apply[String](Schema.this._dbAdapter.writeIndexDeclaration(cols, idxName, name, false))
        case (_1: Option[org.squeryl.internals.ColumnAttribute], _2: Option[org.squeryl.internals.Indexed])(Option[org.squeryl.internals.ColumnAttribute], Option[org.squeryl.internals.Indexed])((x: org.squeryl.internals.ColumnAttribute)Some[org.squeryl.internals.ColumnAttribute](_), (x: org.squeryl.internals.Indexed)Some[org.squeryl.internals.Indexed]((nameOfIndex: Option[String])org.squeryl.internals.Indexed((idxName @ _)))) => scala.Some.apply[String](Schema.this._dbAdapter.writeIndexDeclaration(cols, idxName, name, true))
      }
    };
    def createColumnGroupConstraintsAndIndexes: Unit = Schema.this._writeColumnGroupAttributeAssignments.foreach[Boolean](((statement: String) => Schema.this._executeDdl(statement)));
    private def _dropForeignKeyConstraints: Unit = {
      val cs: org.squeryl.AbstractSession = Session.currentSession;
      val dba: org.squeryl.internals.DatabaseAdapter = cs.databaseAdapter;
      Schema.this._activeForeignKeySpecs.foreach[Unit](((fk: (org.squeryl.Table[_], org.squeryl.Table[_], org.squeryl.ForeignKeyDeclaration)) => {
        val s: java.sql.Statement = cs.connection.createStatement();
        dba.dropForeignKeyStatement(fk._1, dba.foreignKeyConstraintName(fk._1, fk._3.idWithinSchema), cs)
      }))
    };
    private def _declareForeignKeyConstraints: Unit = Schema.this._foreignKeyConstraints.foreach[Boolean](((fk: String) => Schema.this._executeDdl(fk)));
    private def _executeDdl(statement: String): Boolean = {
      val cs: org.squeryl.AbstractSession = Session.currentSession;
      cs.log(statement);
      val s: java.sql.Statement = cs.connection.createStatement();
      try {
        s.execute(statement)
      } catch {
        case (e @ (_: java.sql.SQLException)) => throw SquerylSQLException.apply("error executing ".+(statement).+("\n").+(e), e)
      } finally s.close()
    };
    private def _foreignKeyConstraints: scala.collection.mutable.ArrayBuffer[String] = Schema.this._activeForeignKeySpecs.map[String, scala.collection.mutable.ArrayBuffer[String]](((fk: (org.squeryl.Table[_], org.squeryl.Table[_], org.squeryl.ForeignKeyDeclaration)) => {
      val fkDecl: org.squeryl.ForeignKeyDeclaration = fk._3;
      Schema.this._dbAdapter.writeForeignKeyDeclaration(fk._1, fkDecl.foreignKeyColumnName, fk._2, fkDecl.referencedPrimaryKey, fkDecl._referentialAction1, fkDecl._referentialAction2, fkDecl.idWithinSchema)
    }))(mutable.this.ArrayBuffer.canBuildFrom[String]);
    private def _createTables: Unit = Schema.this._tables.foreach[Unit](((t: org.squeryl.Table[_]) => {
      val sw: org.squeryl.internals.StatementWriter = new internals.StatementWriter(Schema.this._dbAdapter);
      Schema.this._dbAdapter.writeCreateTable[_$1](t, sw, this);
      Schema.this._executeDdl(sw.statement);
      Schema.this._dbAdapter.postCreateTable(t, scala.None);
      Schema.this._indexDeclarationsFor(t).foreach[Boolean](((indexDecl: String) => Schema.this._executeDdl(indexDecl)))
    }));
    private def _createConstraintsOfCompositePKs: Unit = Schema.this._allCompositePrimaryKeys.foreach[Boolean](((cpk: (org.squeryl.Table[_], Iterable[org.squeryl.internals.FieldMetaData])) => {
      val createConstraintStmt: String = Schema.this._dbAdapter.writeCompositePrimaryKeyConstraint(cpk._1, cpk._2);
      Schema.this._executeDdl(createConstraintStmt)
    }));
    private def _allCompositePrimaryKeys: scala.collection.mutable.ArrayBuffer[(org.squeryl.Table[_], Iterable[org.squeryl.internals.FieldMetaData])] = {
      val res: scala.collection.mutable.ArrayBuffer[(org.squeryl.Table[_], Iterable[org.squeryl.internals.FieldMetaData])] = new scala.collection.mutable.ArrayBuffer[(org.squeryl.Table[_], Iterable[org.squeryl.internals.FieldMetaData])]();
      Schema.this._tables.foreach[Unit](((t: org.squeryl.Table[_]) => t.ked.foreach[Unit](((ked: org.squeryl.KeyedEntityDef[_$1, _]) => internals.Utils.mapSampleObject[AnyRef, Unit](t.asInstanceOf[org.squeryl.Table[AnyRef]], ((z: AnyRef) => {
        val id: AnyRef = ked.asInstanceOf[org.squeryl.KeyedEntityDef[AnyRef,AnyRef]].getId(z);
        if (id.isInstanceOf[org.squeryl.dsl.CompositeKey])
          {
            val compositeCols: Seq[org.squeryl.internals.FieldMetaData] = id.asInstanceOf[org.squeryl.dsl.CompositeKey]._fields;
            res.append(scala.Tuple2.apply[org.squeryl.Table[_$1], Seq[org.squeryl.internals.FieldMetaData]](t, compositeCols))
          }
        else
          ()
      }))))));
      res
    };
    def columnTypeFor(fieldMetaData: org.squeryl.internals.FieldMetaData, owner: org.squeryl.Table[_]): Option[String] = scala.None;
    def tableNameFromClass(c: Class[_]): String = c.getSimpleName();
    protected def table[T >: Nothing <: Any]()(implicit manifestT: scala.reflect.Manifest[T], ked: org.squeryl.OptionalKeyedEntityDef[T, _]): org.squeryl.Table[T] = Schema.this.table[T](Schema.this.tableNameFromClass(manifestT.erasure))(manifestT, ked);
    protected def table[T >: Nothing <: Any](name: String)(implicit manifestT: scala.reflect.Manifest[T], ked: org.squeryl.OptionalKeyedEntityDef[T, _]): org.squeryl.Table[T] = {
      val typeT: Class[T] = manifestT.erasure.asInstanceOf[Class[T]];
      val t: org.squeryl.Table[T] = new org.squeryl.Table[T](name, typeT, this, scala.None, ked.keyedEntityDef);
      Schema.this._addTable(t);
      Schema.this._addTableType(typeT, t);
      t
    };
    protected def table[T >: Nothing <: Any](name: String, prefix: String)(implicit manifestT: scala.reflect.Manifest[T], ked: org.squeryl.OptionalKeyedEntityDef[T, _]): org.squeryl.Table[T] = {
      val typeT: Class[T] = manifestT.erasure.asInstanceOf[Class[T]];
      val t: org.squeryl.Table[T] = new org.squeryl.Table[T](name, typeT, this, scala.Some.apply[String](prefix), ked.keyedEntityDef);
      Schema.this._addTable(t);
      Schema.this._addTableType(typeT, t);
      t
    };
    private[squeryl] def _addTable(t: org.squeryl.Table[_]): Unit = Schema.this._tables.append(t);
    private[squeryl] def _addTableType(typeT: Class[_], t: org.squeryl.Table[_]): scala.collection.mutable.HashMap[Class[_],org.squeryl.Table[_]] = Schema.this._tableTypes.+=(scala.Tuple2.apply[Class[_$26], org.squeryl.Table[_$27]](typeT, t));
    class ReferentialEvent extends scala.AnyRef {
      <paramaccessor> private[this] val eventName: String = _;
      <stable> <accessor> <paramaccessor> def eventName: String = ReferentialEvent.this.eventName;
      def <init>(eventName: String): Schema.this.ReferentialEvent = {
        ReferentialEvent.super.<init>();
        ()
      };
      def restrict: Schema.this.ReferentialActionImpl = new Schema.this.ReferentialActionImpl("restrict", this);
      def cascade: Schema.this.ReferentialActionImpl = new Schema.this.ReferentialActionImpl("cascade", this);
      def noAction: Schema.this.ReferentialActionImpl = new Schema.this.ReferentialActionImpl("no action", this);
      def setNull: Schema.this.ReferentialActionImpl = new Schema.this.ReferentialActionImpl("set null", this)
    };
    class ReferentialActionImpl extends Object with org.squeryl.ReferentialAction {
      <paramaccessor> private[this] val token: String = _;
      <paramaccessor> private[this] val ev: Schema.this.ReferentialEvent = _;
      def <init>(token: String, ev: Schema.this.ReferentialEvent): Schema.this.ReferentialActionImpl = {
        ReferentialActionImpl.super.<init>();
        ()
      };
      def event: String = ReferentialActionImpl.this.ev.eventName;
      def action: String = ReferentialActionImpl.this.token
    };
    protected def onUpdate: Schema.this.ReferentialEvent = new Schema.this.ReferentialEvent("update");
    protected def onDelete: Schema.this.ReferentialEvent = new Schema.this.ReferentialEvent("delete");
    private[this] var _fkIdGen: Int = 1;
    <accessor> private def _fkIdGen: Int = Schema.this._fkIdGen;
    <accessor> private def _fkIdGen_=(x$1: Int): Unit = Schema.this._fkIdGen = x$1;
    private[squeryl] def _createForeignKeyDeclaration(fkColName: String, pkColName: String): org.squeryl.ForeignKeyDeclaration = {
      val fkd: org.squeryl.ForeignKeyDeclaration = new ForeignKeyDeclaration(Schema.this._fkIdGen, fkColName, pkColName);
      Schema.this._fkIdGen_=(Schema.this._fkIdGen.+(1));
      Schema.this.applyDefaultForeignKeyPolicy(fkd);
      fkd
    };
    def applyDefaultForeignKeyPolicy(foreignKeyDeclaration: org.squeryl.ForeignKeyDeclaration): Unit = foreignKeyDeclaration.constrainReference()(Schema.this.thisSchema);
    def defaultSizeOfBigDecimal: (Int, Int) = scala.Tuple2.apply[Int, Int](20, 16);
    def defaultLengthOfString: Int = 128;
    protected def declare[B >: Nothing <: Any](a: org.squeryl.dsl.ast.BaseColumnAttributeAssignment*): Seq[org.squeryl.dsl.ast.BaseColumnAttributeAssignment] = a;
    protected def on[A >: Nothing <: Any](table: org.squeryl.Table[A])(declarations: A => Seq[org.squeryl.dsl.ast.BaseColumnAttributeAssignment]): Unit = {
      if (table.==(null))
        org.squeryl.internals.Utils.throwError("on function called with null argument in ".+(this.getClass().getName()).+(" tables must be initialized before declarations."))
      else
        ();
      val colAss: Seq[org.squeryl.dsl.ast.BaseColumnAttributeAssignment] = internals.Utils.mapSampleObject[A, Seq[org.squeryl.dsl.ast.BaseColumnAttributeAssignment]](table, declarations);
      colAss.withFilter(((ca: org.squeryl.dsl.ast.BaseColumnAttributeAssignment) => ca.isInstanceOf[org.squeryl.dsl.ast.ColumnAttributeAssignment])).foreach[Unit](((ca: org.squeryl.dsl.ast.BaseColumnAttributeAssignment) => ca.clearColumnAttributes));
      colAss.foreach[AnyVal](((ca: org.squeryl.dsl.ast.BaseColumnAttributeAssignment) => ca match {
        case (dva @ (_: org.squeryl.dsl.ast.DefaultValueAssignment)) => {
          if (dva.value.isInstanceOf[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]].unary_!)
            org.squeryl.internals.Utils.throwError("error in declaration of column ".+(table.prefixedName).+(".").+(dva.left.nameOfProperty).+(", ").+("only constant expressions are supported in \'defaultsTo\' declaration"))
          else
            ();
          dva.left._defaultValue_=(scala.Some.apply[org.squeryl.dsl.ast.ConstantTypedExpression[_$30,_$31]](dva.value.asInstanceOf[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]]))
        }
        case (caa @ (_: org.squeryl.dsl.ast.ColumnAttributeAssignment)) => {
          caa.columnAttributes.foreach[Boolean](((ca: org.squeryl.internals.ColumnAttribute) => caa.left._addColumnAttribute(ca)));
          if (ca.isIdFieldOfKeyedEntityWithoutUniquenessConstraint)
            caa.left._addColumnAttribute(Schema.this.primaryKey)
          else
            ()
        }
        case (ctaa @ (_: org.squeryl.dsl.ast.ColumnGroupAttributeAssignment)) => {
          if (ca.isIdFieldOfKeyedEntityWithoutUniquenessConstraint)
            ctaa.addAttribute(Schema.this.primaryKey)
          else
            ();
          Schema.this._addColumnGroupAttributeAssignment(ctaa)
        }
        case (a @ (_: Any)) => org.squeryl.internals.Utils.throwError("did not match on ".+(a.getClass().getName()))
      }));
      colAss.foreach[Unit](((ca: org.squeryl.dsl.ast.BaseColumnAttributeAssignment) => ca match {
        case (cga @ (_: org.squeryl.dsl.ast.CompositeKeyAttributeAssignment)) => ()
        case (caa @ (_: org.squeryl.dsl.ast.ColumnAttributeAssignment)) => caa.columnAttributes.withFilter(((ca: org.squeryl.internals.ColumnAttribute) => ca.isInstanceOf[org.squeryl.internals.AutoIncremented].&&(caa.left.isIdFieldOfKeyedEntity.unary_!))).foreach[Nothing](((ca: org.squeryl.internals.ColumnAttribute) => org.squeryl.internals.Utils.throwError("Field ".+(caa.left.nameOfProperty).+(" of table ").+(table.name).+(" is declared as autoIncremented, auto increment is currently only supported on KeyedEntity[A].id"))))
        case (dva @ (_: Any)) => ()
      }))
    };
    private def _addColumnGroupAttributeAssignment(cga: org.squeryl.dsl.ast.ColumnGroupAttributeAssignment): Unit = Schema.this._columnGroupAttributeAssignments.append(cga);
    def defaultColumnAttributesForKeyedEntityId(typeOfIdField: Class[_]): scala.collection.immutable.Set[_ >: org.squeryl.internals.PrimaryKey <: org.squeryl.internals.AttributeValidOnNumericalColumn with Product with Serializable] = if (typeOfIdField.isAssignableFrom(classOf[java.lang.Long]).||(typeOfIdField.isAssignableFrom(classOf[java.lang.Integer])))
      scala.this.Predef.Set.apply[org.squeryl.internals.AttributeValidOnNumericalColumn with Product with Serializable](new internals.PrimaryKey(), new internals.AutoIncremented(scala.None))
    else
      scala.this.Predef.Set.apply[org.squeryl.internals.PrimaryKey](new internals.PrimaryKey());
    protected def unique: org.squeryl.internals.Unique = internals.Unique.apply();
    protected def primaryKey: org.squeryl.internals.PrimaryKey = internals.PrimaryKey.apply();
    protected def autoIncremented: org.squeryl.internals.AutoIncremented = internals.AutoIncremented.apply(scala.None);
    protected def autoIncremented(sequenceName: String): org.squeryl.internals.AutoIncremented = internals.AutoIncremented.apply(scala.Some.apply[String](sequenceName));
    protected def indexed: org.squeryl.internals.Indexed = internals.Indexed.apply(scala.None);
    protected def indexed(indexName: String): org.squeryl.internals.Indexed = internals.Indexed.apply(scala.Some.apply[String](indexName));
    protected def dbType(declaration: String): org.squeryl.internals.DBType = internals.DBType.apply(declaration);
    protected def uninsertable: org.squeryl.internals.Uninsertable = internals.Uninsertable.apply();
    protected def unupdatable: org.squeryl.internals.Unupdatable = internals.Unupdatable.apply();
    protected def named(name: String): org.squeryl.internals.Named = internals.Named.apply(name);
    protected def transient: org.squeryl.internals.IsTransient = internals.IsTransient.apply();
    class ColGroupDeclaration extends scala.AnyRef {
      <paramaccessor> private[this] val cols: Seq[org.squeryl.internals.FieldMetaData] = _;
      def <init>(cols: Seq[org.squeryl.internals.FieldMetaData]): Schema.this.ColGroupDeclaration = {
        ColGroupDeclaration.super.<init>();
        ()
      };
      def are(columnAttributes: org.squeryl.internals.AttributeValidOnMultipleColumn*): org.squeryl.dsl.ast.ColumnGroupAttributeAssignment = new dsl.ast.ColumnGroupAttributeAssignment(ColGroupDeclaration.this.cols, columnAttributes)
    };
    def columns(fieldList: org.squeryl.dsl.TypedExpression[_, _]*): Schema.this.ColGroupDeclaration = new Schema.this.ColGroupDeclaration(fieldList.map[org.squeryl.internals.FieldMetaData, Seq[org.squeryl.internals.FieldMetaData]](((x$8: org.squeryl.dsl.TypedExpression[_, _]) => x$8._fieldMetaData))(collection.this.Seq.canBuildFrom[org.squeryl.internals.FieldMetaData]));
    def callbacks: Seq[org.squeryl.internals.LifecycleEvent] = immutable.this.Nil;
    lazy private[this] var _callbacks: Map[org.squeryl.View[_],org.squeryl.internals.LifecycleEventInvoker] = {
      val m: scala.collection.immutable.Map[org.squeryl.View[_],org.squeryl.internals.LifecycleEventInvoker] = Schema.this.callbacks.flatMap[(org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }, Seq[(org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }]](((cb: org.squeryl.internals.LifecycleEvent) => cb.target.map[(org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }, Traversable[(org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }]](((t: org.squeryl.View[_]) => scala.Tuple2.apply[org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent](t, cb)))(collection.this.Traversable.canBuildFrom[(org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }])))(collection.this.Seq.canBuildFrom[(org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }]).groupBy[org.squeryl.View[_]](((x$9: (org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }) => x$9._1)).mapValues[Seq[org.squeryl.internals.LifecycleEvent]](((x$10: Seq[(org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }]) => x$10.map[org.squeryl.internals.LifecycleEvent, Seq[org.squeryl.internals.LifecycleEvent]](((x$11: (org.squeryl.View[_$7], org.squeryl.internals.LifecycleEvent) forSome { type _$7 }) => x$11._2))(collection.this.Seq.canBuildFrom[org.squeryl.internals.LifecycleEvent]))).map[(org.squeryl.View[_], org.squeryl.internals.LifecycleEventInvoker), scala.collection.immutable.Map[org.squeryl.View[_],org.squeryl.internals.LifecycleEventInvoker]](((t: (org.squeryl.View[_], Seq[org.squeryl.internals.LifecycleEvent])) => (scala.Tuple2.apply[org.squeryl.View[_$36], org.squeryl.internals.LifecycleEventInvoker](t._1, new internals.LifecycleEventInvoker(t._2, t._1)): (org.squeryl.View[_], org.squeryl.internals.LifecycleEventInvoker))))(immutable.this.Map.canBuildFrom[org.squeryl.View[_], org.squeryl.internals.LifecycleEventInvoker]).toMap[org.squeryl.View[_], org.squeryl.internals.LifecycleEventInvoker](scala.this.Predef.conforms[(org.squeryl.View[_], org.squeryl.internals.LifecycleEventInvoker)]);
      m
    };
    import internals.PosoLifecycleEvent._;
    protected def beforeInsert[A >: Nothing <: Any](t: org.squeryl.Table[A]): org.squeryl.internals.LifecycleEventPercursorTable[A] = new org.squeryl.internals.LifecycleEventPercursorTable[A](t, internals.PosoLifecycleEvent.BeforeInsert);
    protected def beforeInsert[A >: Nothing <: Any]()(implicit m: scala.reflect.Manifest[A]): org.squeryl.internals.LifecycleEventPercursorClass[A] = new org.squeryl.internals.LifecycleEventPercursorClass[A](m.erasure, this, internals.PosoLifecycleEvent.BeforeInsert);
    protected def beforeUpdate[A >: Nothing <: Any](t: org.squeryl.Table[A]): org.squeryl.internals.LifecycleEventPercursorTable[A] = new org.squeryl.internals.LifecycleEventPercursorTable[A](t, internals.PosoLifecycleEvent.BeforeUpdate);
    protected def beforeUpdate[A >: Nothing <: Any]()(implicit m: scala.reflect.Manifest[A]): org.squeryl.internals.LifecycleEventPercursorClass[A] = new org.squeryl.internals.LifecycleEventPercursorClass[A](m.erasure, this, internals.PosoLifecycleEvent.BeforeUpdate);
    protected def beforeDelete[A >: Nothing <: Any](t: org.squeryl.Table[A])(implicit ev: org.squeryl.KeyedEntityDef[A, _]): org.squeryl.internals.LifecycleEventPercursorTable[A] = new org.squeryl.internals.LifecycleEventPercursorTable[A](t, internals.PosoLifecycleEvent.BeforeDelete);
    protected def beforeDelete[K >: Nothing <: Any, A >: Nothing <: Any]()(implicit m: scala.reflect.Manifest[A], ked: org.squeryl.KeyedEntityDef[A,K]): org.squeryl.internals.LifecycleEventPercursorClass[A] = new org.squeryl.internals.LifecycleEventPercursorClass[A](m.erasure, this, internals.PosoLifecycleEvent.BeforeDelete);
    protected def afterInsert[A >: Nothing <: Any](t: org.squeryl.Table[A]): org.squeryl.internals.LifecycleEventPercursorTable[A] = new org.squeryl.internals.LifecycleEventPercursorTable[A](t, internals.PosoLifecycleEvent.AfterInsert);
    protected def afterInsert[A >: Nothing <: Any]()(implicit m: scala.reflect.Manifest[A]): org.squeryl.internals.LifecycleEventPercursorClass[A] = new org.squeryl.internals.LifecycleEventPercursorClass[A](m.erasure, this, internals.PosoLifecycleEvent.AfterInsert);
    protected def afterUpdate[A >: Nothing <: Any](t: org.squeryl.Table[A]): org.squeryl.internals.LifecycleEventPercursorTable[A] = new org.squeryl.internals.LifecycleEventPercursorTable[A](t, internals.PosoLifecycleEvent.AfterUpdate);
    protected def afterUpdate[A >: Nothing <: Any]()(implicit m: scala.reflect.Manifest[A]): org.squeryl.internals.LifecycleEventPercursorClass[A] = new org.squeryl.internals.LifecycleEventPercursorClass[A](m.erasure, this, internals.PosoLifecycleEvent.AfterUpdate);
    protected def afterDelete[A >: Nothing <: Any](t: org.squeryl.Table[A]): org.squeryl.internals.LifecycleEventPercursorTable[A] = new org.squeryl.internals.LifecycleEventPercursorTable[A](t, internals.PosoLifecycleEvent.AfterDelete);
    protected def afterDelete[A >: Nothing <: Any]()(implicit m: scala.reflect.Manifest[A]): org.squeryl.internals.LifecycleEventPercursorClass[A] = new org.squeryl.internals.LifecycleEventPercursorClass[A](m.erasure, this, internals.PosoLifecycleEvent.AfterDelete);
    protected def factoryFor[A >: Nothing <: Any](table: org.squeryl.Table[A]): org.squeryl.internals.PosoFactoryPercursorTable[A] = new org.squeryl.internals.PosoFactoryPercursorTable[A](table);
    implicit def anyRef2ActiveTransaction[A >: Nothing <: Any](a: A)(implicit queryDsl: org.squeryl.dsl.QueryDsl, m: scala.reflect.Manifest[A]): Schema.this.ActiveRecord[A] = new Schema.this.ActiveRecord[A](a, queryDsl, m);
    class ActiveRecord[A >: Nothing <: Any] extends scala.AnyRef {
      <paramaccessor> private[this] val a: A = _;
      <paramaccessor> private[this] val queryDsl: org.squeryl.dsl.QueryDsl = _;
      <paramaccessor> private[this] val m: scala.reflect.Manifest[A] = _;
      def <init>(a: A, queryDsl: org.squeryl.dsl.QueryDsl, m: scala.reflect.Manifest[A]): Schema.this.ActiveRecord[A] = {
        ActiveRecord.super.<init>();
        ()
      };
      private def _performAction(action: org.squeryl.Table[A] => Unit): Option[Unit] = Schema.this._tableTypes.get(ActiveRecord.this.m.erasure).map[Unit](((table: org.squeryl.Table[_]) => ActiveRecord.this.queryDsl.inTransaction[Unit](action.apply(table.asInstanceOf[org.squeryl.Table[A]]))));
      def save: Option[Unit] = ActiveRecord.this._performAction(((x$12: org.squeryl.Table[A]) => {
        x$12.insert(ActiveRecord.this.a);
        ()
      }));
      def update(implicit ked: org.squeryl.KeyedEntityDef[A, _]): Option[Unit] = ActiveRecord.this._performAction(((x$13: org.squeryl.Table[A]) => x$13.update(ActiveRecord.this.a)(ked)))
    }
  }
}