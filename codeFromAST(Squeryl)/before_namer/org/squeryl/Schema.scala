package org.squeryl {
  import dsl._;
  import ast._;
  import internals._;
  import reflect.Manifest;
  import java.sql.SQLException;
  import java.io.PrintWriter;
  import collection.mutable.{HashMap, HashSet, ArrayBuffer};
  import org.squeryl.internals.FieldMapper;
  class Schema extends scala.AnyRef {
    implicit <paramaccessor> val fieldMapper: FieldMapper = _;
    def <init>()(implicit fieldMapper: FieldMapper) = {
      super.<init>();
      ()
    };
    implicit protected def thisSchema = this;
    private val _tables = new ArrayBuffer[Table[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]();
    def tables: Seq[Table[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _tables.toSeq;
    private val _tableTypes = new HashMap[Class[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }, Table[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }]();
    private val _oneToManyRelations = new ArrayBuffer[OneToManyRelation[_$5, _$6] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }]();
    private val _manyToManyRelations = new ArrayBuffer[ManyToManyRelation[_$7, _$8, _$9] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }]();
    private val _columnGroupAttributeAssignments = new ArrayBuffer[ColumnGroupAttributeAssignment]();
    private[squeryl] val _namingScope = new HashSet[String]();
    private[squeryl] def _addRelation(r: OneToManyRelation[_$10, _$11] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = _oneToManyRelations.append(r);
    private[squeryl] def _addRelation(r: ManyToManyRelation[_$12, _$13, _$14] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = _manyToManyRelations.append(r);
    private def _dbAdapter = Session.currentSession.databaseAdapter;
    private def _activeForeignKeySpecs = {
      val res = new ArrayBuffer[scala.Tuple3[Table[_$15] forSome { 
        <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
      }, Table[_$16] forSome { 
        <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
      }, ForeignKeyDeclaration]]();
      _oneToManyRelations.withFilter(((r) => r.foreignKeyDeclaration._isActive)).foreach(((r) => res.append(scala.Tuple3(r.rightTable, r.leftTable, r.foreignKeyDeclaration))));
      _manyToManyRelations.foreach(((r) => {
        if (r.leftForeignKeyDeclaration._isActive)
          res.append(scala.Tuple3(r.thisTable, r.leftTable, r.leftForeignKeyDeclaration))
        else
          ();
        if (r.rightForeignKeyDeclaration._isActive)
          res.append(scala.Tuple3(r.thisTable, r.rightTable, r.rightForeignKeyDeclaration))
        else
          ()
      }));
      res
    };
    def findTablesFor[A >: _root_.scala.Nothing <: _root_.scala.Any](a: A): Iterable[Table[A]] = {
      val c = a.asInstanceOf[AnyRef].getClass;
      _tables.filter(((x$1) => x$1.posoMetaData.clasz.$eq$eq(c))).asInstanceOf[Iterable[Table[A]]]
    };
    def findAllTablesFor[A >: _root_.scala.Nothing <: _root_.scala.Any](c: Class[A]) = _tables.filter(((t) => c.isAssignableFrom(t.posoMetaData.clasz))).asInstanceOf[Traversable[Table[_$17] forSome { 
      <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
    }]];
    object NamingConventionTransforms extends scala.AnyRef {
      def <init>() = {
        super.<init>();
        ()
      };
      @new deprecated("use snakify() instead as of 0.9.5beta", "0.9.5") def camelCase2underScore(name: String) = name.toList.map(((c) => if (c.isUpper)
  "_".$plus(c)
else
  c)).mkString;
      def snakify(name: String) = name.replaceAll("^([^A-Za-z_])", "_$1").replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase
    };
    def columnNameFromPropertyName(propertyName: String) = propertyName;
    def tableNameFromClassName(tableName: String) = tableName;
    def name: Option[String] = None;
    def printDdl: Unit = printDdl(((x$2) => println(x$2)));
    def printDdl(pw: PrintWriter): Unit = printDdl(((x$3) => pw.println(x$3)));
    def printDdl(statementHandler: _root_.scala.Function1[String, Unit]): Unit = {
      statementHandler("-- table declarations :");
      _tables.foreach(((t) => {
        val sw = new StatementWriter(true, _dbAdapter);
        _dbAdapter.writeCreateTable(t, sw, this);
        statementHandler(sw.statement.$plus(";"));
        _dbAdapter.postCreateTable(t, Some(statementHandler));
        val indexDecl = _indexDeclarationsFor(t);
        if (indexDecl.$bang$eq(Nil))
          statementHandler("-- indexes on ".$plus(t.prefixedName))
        else
          ();
        indexDecl.foreach(((i) => statementHandler(i.$plus(";"))))
      }));
      val constraints = _foreignKeyConstraints.toList;
      if (constraints.$bang$eq(Nil))
        statementHandler("-- foreign key constraints :")
      else
        ();
      constraints.foreach(((fkc) => statementHandler(fkc.$plus(";"))));
      val compositePKs = _allCompositePrimaryKeys.toList;
      if (compositePKs.$bang$eq(Nil))
        statementHandler("-- composite key indexes :")
      else
        ();
      compositePKs.foreach(((cpk) => {
        val createConstraintStmt = _dbAdapter.writeCompositePrimaryKeyConstraint(cpk._1, cpk._2);
        statementHandler(createConstraintStmt.$plus(";"))
      }));
      val columnGroupIndexes = _writeColumnGroupAttributeAssignments.toList;
      if (columnGroupIndexes.$bang$eq(Nil))
        statementHandler("-- column group indexes :")
      else
        ();
      columnGroupIndexes.foreach(((decl) => statementHandler(decl.$plus(";"))))
    };
    def drop: Unit = {
      if (_dbAdapter.supportsForeignKeyConstraints)
        _dropForeignKeyConstraints
      else
        ();
      val s = Session.currentSession.connection.createStatement;
      val con = Session.currentSession.connection;
      _tables.foreach(((t) => {
        _dbAdapter.dropTable(t);
        _dbAdapter.postDropTable(t)
      }))
    };
    def create = {
      _createTables;
      if (_dbAdapter.supportsForeignKeyConstraints)
        _declareForeignKeyConstraints
      else
        ();
      _createConstraintsOfCompositePKs;
      createColumnGroupConstraintsAndIndexes
    };
    private def _indexDeclarationsFor(t: Table[_$18] forSome { 
      <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      val d0 = t.posoMetaData.fieldsMetaData.map(((fmd) => _writeIndexDeclarationIfApplicable(fmd.columnAttributes.toSeq, Seq(fmd), None)));
      d0.filter(((x$4) => x$4.$bang$eq(None))).map(((x$5) => x$5.get)).toList
    };
    private def _writeColumnGroupAttributeAssignments: Seq[String] = _columnGroupAttributeAssignments.map(((cgaa) => _writeIndexDeclarationIfApplicable(cgaa.columnAttributes, cgaa.columns, cgaa.name).getOrElse(org.squeryl.internals.Utils.throwError("emtpy attribute list should not be possible to create with DSL (Squeryl bug)."))));
    private def _writeIndexDeclarationIfApplicable(columnAttributes: Seq[ColumnAttribute], cols: Seq[FieldMetaData], name: Option[String]): Option[String] = {
      val unique = columnAttributes.find(((x$6) => x$6.isInstanceOf[Unique]));
      val indexed = columnAttributes.find(((x$7) => x$7.isInstanceOf[Indexed])).flatMap(((i) => i match {
        case (idx @ (_: Indexed)) => Some(idx)
        case _ => None
      }));
      scala.Tuple2(unique, indexed) match {
        case scala.Tuple2(None, None) => None
        case scala.Tuple2(Some(_), None) => Some(_dbAdapter.writeIndexDeclaration(cols, None, name, true))
        case scala.Tuple2(None, Some(Indexed((idxName @ _)))) => Some(_dbAdapter.writeIndexDeclaration(cols, idxName, name, false))
        case scala.Tuple2(Some(_), Some(Indexed((idxName @ _)))) => Some(_dbAdapter.writeIndexDeclaration(cols, idxName, name, true))
      }
    };
    def createColumnGroupConstraintsAndIndexes = _writeColumnGroupAttributeAssignments.foreach(((statement) => _executeDdl(statement)));
    private def _dropForeignKeyConstraints = {
      val cs = Session.currentSession;
      val dba = cs.databaseAdapter;
      _activeForeignKeySpecs.foreach(((fk) => {
        val s = cs.connection.createStatement;
        dba.dropForeignKeyStatement(fk._1, dba.foreignKeyConstraintName(fk._1, fk._3.idWithinSchema), cs)
      }))
    };
    private def _declareForeignKeyConstraints = _foreignKeyConstraints.foreach(((fk) => _executeDdl(fk)));
    private def _executeDdl(statement: String) = {
      val cs = Session.currentSession;
      cs.log(statement);
      val s = cs.connection.createStatement;
      try {
        s.execute(statement)
      } catch {
        case (e @ (_: SQLException)) => throw SquerylSQLException("error executing ".$plus(statement).$plus("\n").$plus(e), e)
      } finally s.close
    };
    private def _foreignKeyConstraints = _activeForeignKeySpecs.map(((fk) => {
      val fkDecl = fk._3;
      _dbAdapter.writeForeignKeyDeclaration(fk._1, fkDecl.foreignKeyColumnName, fk._2, fkDecl.referencedPrimaryKey, fkDecl._referentialAction1, fkDecl._referentialAction2, fkDecl.idWithinSchema)
    }));
    private def _createTables = _tables.foreach(((t) => {
      val sw = new StatementWriter(_dbAdapter);
      _dbAdapter.writeCreateTable(t, sw, this);
      _executeDdl(sw.statement);
      _dbAdapter.postCreateTable(t, None);
      _indexDeclarationsFor(t).foreach(((indexDecl) => _executeDdl(indexDecl)))
    }));
    private def _createConstraintsOfCompositePKs = _allCompositePrimaryKeys.foreach(((cpk) => {
      val createConstraintStmt = _dbAdapter.writeCompositePrimaryKeyConstraint(cpk._1, cpk._2);
      _executeDdl(createConstraintStmt)
    }));
    private def _allCompositePrimaryKeys = {
      val res = new ArrayBuffer[scala.Tuple2[Table[_$19] forSome { 
        <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any
      }, Iterable[FieldMetaData]]]();
      _tables.foreach(((t) => t.ked.foreach(((ked) => Utils.mapSampleObject(t.asInstanceOf[Table[AnyRef]], ((z: AnyRef) => {
        val id = ked.asInstanceOf[KeyedEntityDef[AnyRef, AnyRef]].getId(z);
        if (id.isInstanceOf[CompositeKey])
          {
            val compositeCols = id.asInstanceOf[CompositeKey]._fields;
            res.append(scala.Tuple2(t, compositeCols))
          }
        else
          ()
      }))))));
      res
    };
    def columnTypeFor(fieldMetaData: FieldMetaData, owner: Table[_$20] forSome { 
      <synthetic> type _$20 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Option[String] = None;
    def tableNameFromClass(c: Class[_$21] forSome { 
      <synthetic> type _$21 >: _root_.scala.Nothing <: _root_.scala.Any
    }): String = c.getSimpleName;
    protected def table[T >: _root_.scala.Nothing <: _root_.scala.Any]()(implicit manifestT: Manifest[T], ked: OptionalKeyedEntityDef[T, _$22] forSome { 
      <synthetic> type _$22 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Table[T] = table(tableNameFromClass(manifestT.erasure))(manifestT, ked);
    protected def table[T >: _root_.scala.Nothing <: _root_.scala.Any](name: String)(implicit manifestT: Manifest[T], ked: OptionalKeyedEntityDef[T, _$23] forSome { 
      <synthetic> type _$23 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Table[T] = {
      val typeT = manifestT.erasure.asInstanceOf[Class[T]];
      val t = new Table[T](name, typeT, this, None, ked.keyedEntityDef);
      _addTable(t);
      _addTableType(typeT, t);
      t
    };
    protected def table[T >: _root_.scala.Nothing <: _root_.scala.Any](name: String, prefix: String)(implicit manifestT: Manifest[T], ked: OptionalKeyedEntityDef[T, _$24] forSome { 
      <synthetic> type _$24 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Table[T] = {
      val typeT = manifestT.erasure.asInstanceOf[Class[T]];
      val t = new Table[T](name, typeT, this, Some(prefix), ked.keyedEntityDef);
      _addTable(t);
      _addTableType(typeT, t);
      t
    };
    private[squeryl] def _addTable(t: Table[_$25] forSome { 
      <synthetic> type _$25 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = _tables.append(t);
    private[squeryl] def _addTableType(typeT: Class[_$26] forSome { 
      <synthetic> type _$26 >: _root_.scala.Nothing <: _root_.scala.Any
    }, t: Table[_$27] forSome { 
      <synthetic> type _$27 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = _tableTypes.$plus$eq(scala.Tuple2(typeT, t));
    class ReferentialEvent extends scala.AnyRef {
      <paramaccessor> val eventName: String = _;
      def <init>(eventName: String) = {
        super.<init>();
        ()
      };
      def restrict = new ReferentialActionImpl("restrict", this);
      def cascade = new ReferentialActionImpl("cascade", this);
      def noAction = new ReferentialActionImpl("no action", this);
      def setNull = new ReferentialActionImpl("set null", this)
    };
    class ReferentialActionImpl extends ReferentialAction {
      <paramaccessor> private[this] val token: String = _;
      <paramaccessor> private[this] val ev: ReferentialEvent = _;
      def <init>(token: String, ev: ReferentialEvent) = {
        super.<init>();
        ()
      };
      def event = ev.eventName;
      def action = token
    };
    protected def onUpdate = new ReferentialEvent("update");
    protected def onDelete = new ReferentialEvent("delete");
    private var _fkIdGen = 1;
    private[squeryl] def _createForeignKeyDeclaration(fkColName: String, pkColName: String) = {
      val fkd = new ForeignKeyDeclaration(_fkIdGen, fkColName, pkColName);
      _fkIdGen.$plus$eq(1);
      applyDefaultForeignKeyPolicy(fkd);
      fkd
    };
    def applyDefaultForeignKeyPolicy(foreignKeyDeclaration: ForeignKeyDeclaration) = foreignKeyDeclaration.constrainReference;
    def defaultSizeOfBigDecimal = scala.Tuple2(20, 16);
    def defaultLengthOfString = 128;
    protected def declare[B >: _root_.scala.Nothing <: _root_.scala.Any](a: _root_.scala.<repeated>[BaseColumnAttributeAssignment]) = a;
    protected def on[A >: _root_.scala.Nothing <: _root_.scala.Any](table: Table[A])(declarations: _root_.scala.Function1[A, Seq[BaseColumnAttributeAssignment]]) = {
      if (table.$eq$eq(null))
        org.squeryl.internals.Utils.throwError("on function called with null argument in ".$plus(this.getClass.getName).$plus(" tables must be initialized before declarations."))
      else
        ();
      val colAss: Seq[BaseColumnAttributeAssignment] = Utils.mapSampleObject(table, declarations);
      colAss.withFilter(((ca) => ca.isInstanceOf[ColumnAttributeAssignment])).foreach(((ca) => ca.clearColumnAttributes));
      colAss.foreach(((ca) => ca match {
        case (dva @ (_: DefaultValueAssignment)) => {
          if (dva.value.isInstanceOf[ConstantTypedExpression[_$28, _$29] forSome { 
  <synthetic> type _$28 >: _root_.scala.Nothing <: _root_.scala.Any;
  <synthetic> type _$29 >: _root_.scala.Nothing <: _root_.scala.Any
}].unary_$bang)
            org.squeryl.internals.Utils.throwError("error in declaration of column ".$plus(table.prefixedName).$plus(".").$plus(dva.left.nameOfProperty).$plus(", ").$plus("only constant expressions are supported in \'defaultsTo\' declaration"))
          else
            ();
          dva.left._defaultValue = Some(dva.value.asInstanceOf[ConstantTypedExpression[_$30, _$31] forSome { 
            <synthetic> type _$30 >: _root_.scala.Nothing <: _root_.scala.Any;
            <synthetic> type _$31 >: _root_.scala.Nothing <: _root_.scala.Any
          }])
        }
        case (caa @ (_: ColumnAttributeAssignment)) => {
          caa.columnAttributes.foreach(((ca) => caa.left._addColumnAttribute(ca)));
          if (ca.isIdFieldOfKeyedEntityWithoutUniquenessConstraint)
            caa.left._addColumnAttribute(primaryKey)
          else
            ()
        }
        case (ctaa @ (_: ColumnGroupAttributeAssignment)) => {
          if (ca.isIdFieldOfKeyedEntityWithoutUniquenessConstraint)
            ctaa.addAttribute(primaryKey)
          else
            ();
          _addColumnGroupAttributeAssignment(ctaa)
        }
        case (a @ (_: Any)) => org.squeryl.internals.Utils.throwError("did not match on ".$plus(a.getClass.getName))
      }));
      colAss.foreach(((ca) => ca match {
        case (cga @ (_: CompositeKeyAttributeAssignment)) => ()
        case (caa @ (_: ColumnAttributeAssignment)) => caa.columnAttributes.withFilter(((ca) => ca.isInstanceOf[AutoIncremented].$amp$amp(caa.left.isIdFieldOfKeyedEntity.unary_$bang))).foreach(((ca) => org.squeryl.internals.Utils.throwError("Field ".$plus(caa.left.nameOfProperty).$plus(" of table ").$plus(table.name).$plus(" is declared as autoIncremented, auto increment is currently only supported on KeyedEntity[A].id"))))
        case (dva @ (_: Any)) => ()
      }))
    };
    private def _addColumnGroupAttributeAssignment(cga: ColumnGroupAttributeAssignment) = _columnGroupAttributeAssignments.append(cga);
    def defaultColumnAttributesForKeyedEntityId(typeOfIdField: Class[_$32] forSome { 
      <synthetic> type _$32 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = if (typeOfIdField.isAssignableFrom(classOf[java.lang.Long]).$bar$bar(typeOfIdField.isAssignableFrom(classOf[java.lang.Integer])))
      Set(new PrimaryKey(), new AutoIncremented(None))
    else
      Set(new PrimaryKey());
    protected def unique = Unique();
    protected def primaryKey = PrimaryKey();
    protected def autoIncremented = AutoIncremented(None);
    protected def autoIncremented(sequenceName: String) = AutoIncremented(Some(sequenceName));
    protected def indexed = Indexed(None);
    protected def indexed(indexName: String) = Indexed(Some(indexName));
    protected def dbType(declaration: String) = DBType(declaration);
    protected def uninsertable = Uninsertable();
    protected def unupdatable = Unupdatable();
    protected def named(name: String) = Named(name);
    protected def transient = IsTransient();
    class ColGroupDeclaration extends scala.AnyRef {
      <paramaccessor> private[this] val cols: Seq[FieldMetaData] = _;
      def <init>(cols: Seq[FieldMetaData]) = {
        super.<init>();
        ()
      };
      def are(columnAttributes: _root_.scala.<repeated>[AttributeValidOnMultipleColumn]) = new ColumnGroupAttributeAssignment(cols, columnAttributes)
    };
    def columns(fieldList: _root_.scala.<repeated>[TypedExpression[_$33, _$34] forSome { 
      <synthetic> type _$33 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$34 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = new ColGroupDeclaration(fieldList.map(((x$8) => x$8._fieldMetaData)));
    def callbacks: Seq[LifecycleEvent] = Nil;
    lazy private[squeryl] val _callbacks: Map[View[_$35] forSome { 
      <synthetic> type _$35 >: _root_.scala.Nothing <: _root_.scala.Any
    }, LifecycleEventInvoker] = {
      val m = callbacks.flatMap(((cb) => cb.target.map(((t) => scala.Tuple2(t, cb))))).groupBy(((x$9) => x$9._1)).mapValues(((x$10) => x$10.map(((x$11) => x$11._2)))).map(((t: Tuple2[View[_$36] forSome { 
  <synthetic> type _$36 >: _root_.scala.Nothing <: _root_.scala.Any
}, Seq[LifecycleEvent]]) => (scala.Tuple2(t._1, new LifecycleEventInvoker(t._2, t._1)): scala.Tuple2[View[_$37] forSome { 
  <synthetic> type _$37 >: _root_.scala.Nothing <: _root_.scala.Any
}, LifecycleEventInvoker]))).toMap;
      m
    };
    import internals.PosoLifecycleEvent._;
    protected def beforeInsert[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[A]) = new LifecycleEventPercursorTable[A](t, BeforeInsert);
    protected def beforeInsert[A >: _root_.scala.Nothing <: _root_.scala.Any]()(implicit m: Manifest[A]) = new LifecycleEventPercursorClass[A](m.erasure, this, BeforeInsert);
    protected def beforeUpdate[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[A]) = new LifecycleEventPercursorTable[A](t, BeforeUpdate);
    protected def beforeUpdate[A >: _root_.scala.Nothing <: _root_.scala.Any]()(implicit m: Manifest[A]) = new LifecycleEventPercursorClass[A](m.erasure, this, BeforeUpdate);
    protected def beforeDelete[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[A])(implicit ev: KeyedEntityDef[A, _$38] forSome { 
      <synthetic> type _$38 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = new LifecycleEventPercursorTable[A](t, BeforeDelete);
    protected def beforeDelete[K >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any]()(implicit m: Manifest[A], ked: KeyedEntityDef[A, K]) = new LifecycleEventPercursorClass[A](m.erasure, this, BeforeDelete);
    protected def afterInsert[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[A]) = new LifecycleEventPercursorTable[A](t, AfterInsert);
    protected def afterInsert[A >: _root_.scala.Nothing <: _root_.scala.Any]()(implicit m: Manifest[A]) = new LifecycleEventPercursorClass[A](m.erasure, this, AfterInsert);
    protected def afterUpdate[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[A]) = new LifecycleEventPercursorTable[A](t, AfterUpdate);
    protected def afterUpdate[A >: _root_.scala.Nothing <: _root_.scala.Any]()(implicit m: Manifest[A]) = new LifecycleEventPercursorClass[A](m.erasure, this, AfterUpdate);
    protected def afterDelete[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Table[A]) = new LifecycleEventPercursorTable[A](t, AfterDelete);
    protected def afterDelete[A >: _root_.scala.Nothing <: _root_.scala.Any]()(implicit m: Manifest[A]) = new LifecycleEventPercursorClass[A](m.erasure, this, AfterDelete);
    protected def factoryFor[A >: _root_.scala.Nothing <: _root_.scala.Any](table: Table[A]) = new PosoFactoryPercursorTable[A](table);
    implicit def anyRef2ActiveTransaction[A >: _root_.scala.Nothing <: _root_.scala.Any](a: A)(implicit queryDsl: QueryDsl, m: Manifest[A]) = new ActiveRecord(a, queryDsl, m);
    class ActiveRecord[A >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
      <paramaccessor> private[this] val a: A = _;
      <paramaccessor> private[this] val queryDsl: QueryDsl = _;
      <paramaccessor> private[this] val m: Manifest[A] = _;
      def <init>(a: A, queryDsl: QueryDsl, m: Manifest[A]) = {
        super.<init>();
        ()
      };
      private def _performAction(action: _root_.scala.Function1[Table[A], Unit]) = _tableTypes.get(m.erasure).map(((table: Table[_$39] forSome { 
        <synthetic> type _$39 >: _root_.scala.Nothing <: _root_.scala.Any
      }) => queryDsl.inTransaction(action(table.asInstanceOf[Table[A]]))));
      def save = _performAction(((x$12) => x$12.insert(a)));
      def update(implicit ked: KeyedEntityDef[A, _$40] forSome { 
        <synthetic> type _$40 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = _performAction(((x$13) => x$13.update(a)))
    }
  }
}