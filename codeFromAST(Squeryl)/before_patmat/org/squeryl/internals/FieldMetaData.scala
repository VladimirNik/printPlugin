package org.squeryl.internals {
  import java.lang.annotation.Annotation;
  import java.lang.reflect.{Field, Method, Constructor, InvocationTargetException, Type, ParameterizedType};
  import java.sql.ResultSet;
  import java.math.BigDecimal;
  import scala.annotation.tailrec;
  import org.squeryl.annotations.{ColumnBase, Column};
  import scala.collection.mutable.{HashMap, HashSet, ArrayBuffer};
  import org.squeryl.Session;
  import org.squeryl.dsl.CompositeKey;
  import org.squeryl.customtypes.CustomType;
  import scala.tools.scalap.scalax.rules.scalasig.{ScalaSigAttributeParsers, ByteCode, ScalaSigPrinter};
  import java.io.ByteArrayOutputStream;
  import java.io.PrintStream;
  import java.lang.reflect.Member;
  import scala.tools.scalap.scalax.rules.scalasig.ScalaSigParser;
  import org.squeryl.dsl.ast.ConstantTypedExpression;
  import org.squeryl.customtypes.CustomType;
  class FieldMetaData extends scala.AnyRef {
    <paramaccessor> private[this] val parentMetaData: org.squeryl.internals.PosoMetaData[_] = _;
    <stable> <accessor> <paramaccessor> def parentMetaData: org.squeryl.internals.PosoMetaData[_] = FieldMetaData.this.parentMetaData;
    <paramaccessor> private[this] val nameOfProperty: String = _;
    <stable> <accessor> <paramaccessor> def nameOfProperty: String = FieldMetaData.this.nameOfProperty;
    <paramaccessor> private[this] val fieldType: Class[_] = _;
    <stable> <accessor> <paramaccessor> def fieldType: Class[_] = FieldMetaData.this.fieldType;
    <paramaccessor> private[this] val wrappedFieldType: Class[_] = _;
    <stable> <accessor> <paramaccessor> def wrappedFieldType: Class[_] = FieldMetaData.this.wrappedFieldType;
    <paramaccessor> private[this] val customTypeFactory: Option[AnyRef => Product1[Any]] = _;
    <stable> <accessor> <paramaccessor> def customTypeFactory: Option[AnyRef => Product1[Any]] = FieldMetaData.this.customTypeFactory;
    <paramaccessor> private[this] val isOption: Boolean = _;
    <stable> <accessor> <paramaccessor> def isOption: Boolean = FieldMetaData.this.isOption;
    <paramaccessor> private[this] val getter: Option[java.lang.reflect.Method] = _;
    <paramaccessor> private[this] val setter: Option[java.lang.reflect.Method] = _;
    <paramaccessor> private[this] val field: Option[java.lang.reflect.Field] = _;
    <paramaccessor> private[this] val columnAnnotation: Option[org.squeryl.annotations.Column] = _;
    <paramaccessor> private[this] val isOptimisticCounter: Boolean = _;
    <stable> <accessor> <paramaccessor> def isOptimisticCounter: Boolean = FieldMetaData.this.isOptimisticCounter;
    <paramaccessor> private[this] val sampleValue: Any = _;
    <stable> <accessor> <paramaccessor> def sampleValue: Any = FieldMetaData.this.sampleValue;
    def <init>(parentMetaData: org.squeryl.internals.PosoMetaData[_], nameOfProperty: String, fieldType: Class[_], wrappedFieldType: Class[_], customTypeFactory: Option[AnyRef => Product1[Any]], isOption: Boolean, getter: Option[java.lang.reflect.Method], setter: Option[java.lang.reflect.Method], field: Option[java.lang.reflect.Field], columnAnnotation: Option[org.squeryl.annotations.Column], isOptimisticCounter: Boolean, sampleValue: Any): org.squeryl.internals.FieldMetaData = {
      FieldMetaData.super.<init>();
      ()
    };
    def nativeJdbcType: Class[_] = this.schema.fieldMapper.nativeJdbcTypeFor(FieldMetaData.this.wrappedFieldType);
    private[this] val enumeration: Option[Enumeration] = FieldMetaData.this.sampleValue match {
      case (x: Any)Some[Any]((e @ (_: Enumeration#Value))) => scala.Some.apply[Enumeration](Utils.enumerationForValue(e))
      case (e @ (_: Enumeration#Value)) => scala.Some.apply[Enumeration](Utils.enumerationForValue(e))
      case _ => scala.None
    };
    <stable> <accessor> def enumeration: Option[Enumeration] = FieldMetaData.this.enumeration;
    def canonicalEnumerationValueFor(id: Int): Enumeration#Value = if (FieldMetaData.this.sampleValue.==(null))
      org.squeryl.internals.Utils.throwError("classes with Enumerations must have a zero param constructor that assigns a sample to the enumeration field")
    else
      FieldMetaData.this.enumeration.flatMap[Enumeration#Value](((e: Enumeration) => e.values.find(((x$1: e.Value) => x$1.id.==(id))))).get;
    private[this] val _columnAttributes: scala.collection.mutable.HashSet[org.squeryl.internals.ColumnAttribute] = new scala.collection.mutable.HashSet[org.squeryl.internals.ColumnAttribute]();
    <stable> <accessor> private def _columnAttributes: scala.collection.mutable.HashSet[org.squeryl.internals.ColumnAttribute] = FieldMetaData.this._columnAttributes;
    private[squeryl] def _clearColumnAttributes: Unit = FieldMetaData.this._columnAttributes.clear();
    private[squeryl] def _addColumnAttribute(ca: org.squeryl.internals.ColumnAttribute): Boolean = FieldMetaData.this._columnAttributes.add(ca);
    private[this] val _sequenceNamePerDBAdapter: scala.collection.mutable.HashMap[Class[_],String] = new scala.collection.mutable.HashMap[Class[_],String]();
    <stable> <accessor> private def _sequenceNamePerDBAdapter: scala.collection.mutable.HashMap[Class[_],String] = FieldMetaData.this._sequenceNamePerDBAdapter;
    def sequenceName: String = {
      val ai: org.squeryl.internals.AutoIncremented = FieldMetaData.this._columnAttributes.find(((x$2: org.squeryl.internals.ColumnAttribute) => x$2.isInstanceOf[org.squeryl.internals.AutoIncremented])).getOrElse[org.squeryl.internals.ColumnAttribute](org.squeryl.internals.Utils.throwError(scala.this.Predef.any2stringadd(this).+(" is not declared as autoIncremented, hence it has no sequenceName"))).asInstanceOf[org.squeryl.internals.AutoIncremented];
      if (ai.nameOfSequence.!=(scala.None))
        return ai.nameOfSequence.get
      else
        ();
      FieldMetaData.this.synchronized[Nothing]({
        val c: Class[_ <: org.squeryl.internals.DatabaseAdapter] = org.squeryl.Session.currentSession.databaseAdapter.getClass();
        val s: Option[String] = FieldMetaData.this._sequenceNamePerDBAdapter.get(c);
        if (s.!=(scala.None))
          return s.get
        else
          ();
        val s0: String = org.squeryl.Session.currentSession.databaseAdapter.createSequenceName(this);
        FieldMetaData.this._sequenceNamePerDBAdapter.put(c, s0);
        return s0
      })
    };
    def isIdFieldOfKeyedEntity: Boolean = FieldMetaData.this.parentMetaData.viewOrTable.ked.map[Boolean](((x$3: org.squeryl.KeyedEntityDef[_$1, _]) => x$3.idPropertyName.==(FieldMetaData.this.nameOfProperty))).getOrElse[Boolean](false);
    if (FieldMetaData.this.isIdFieldOfKeyedEntity.&&(classOf[org.squeryl.dsl.CompositeKey].isAssignableFrom(FieldMetaData.this.wrappedFieldType).unary_!))
      FieldMetaData.this.schema.defaultColumnAttributesForKeyedEntityId(FieldMetaData.this.wrappedFieldType).foreach[Boolean](((ca: _27) => {
        if (ca.isInstanceOf[org.squeryl.internals.AutoIncremented].&&(FieldMetaData.this.wrappedFieldType.isAssignableFrom(classOf[java.lang.Long]).||(FieldMetaData.this.wrappedFieldType.isAssignableFrom(classOf[java.lang.Integer])).unary_!))
          org.squeryl.internals.Utils.throwError("Schema ".+(FieldMetaData.this.schema.getClass().getName()).+(" has method defaultColumnAttributesForKeyedEntityId returning AutoIncremented \nfor ").+(" all KeyedEntity tables, while class ").+(FieldMetaData.this.parentMetaData.clasz.getName()).+("\n has it\'s id field of type ").+(FieldMetaData.this.fieldType.getName()).+(", that is neither an Int or a Long, \n the only two types that can ").+("be auto incremented"))
        else
          ();
        FieldMetaData.this._addColumnAttribute(ca)
      }))
    else
      ();
    private[this] var _defaultValue: Option[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]] = scala.None;
    <accessor> private[squeryl] def _defaultValue: Option[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]] = FieldMetaData.this._defaultValue;
    <accessor> private[squeryl] def _defaultValue_=(x$1: Option[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]]): Unit = FieldMetaData.this._defaultValue = x$1;
    def columnAttributes: Iterable[org.squeryl.internals.ColumnAttribute] = FieldMetaData.this._columnAttributes;
    def defaultValue: Option[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]] = FieldMetaData.this._defaultValue;
    def explicitDbTypeDeclaration: Option[String] = {
      val dbt: Option[org.squeryl.internals.ColumnAttribute] = FieldMetaData.this._columnAttributes.find(((x$4: org.squeryl.internals.ColumnAttribute) => x$4.isInstanceOf[org.squeryl.internals.DBType]));
      if (dbt.==(scala.None))
        scala.None
      else
        scala.Some.apply[String](dbt.get.asInstanceOf[org.squeryl.internals.DBType].declaration)
    };
    def isTransient: Boolean = FieldMetaData.this._columnAttributes.exists(((x$5: org.squeryl.internals.ColumnAttribute) => x$5.isInstanceOf[org.squeryl.internals.IsTransient]));
    def isCustomType: Boolean = FieldMetaData.this.customTypeFactory.!=(scala.None);
    def length: Int = if (FieldMetaData.this.columnAnnotation.==(scala.None).||(FieldMetaData.this.columnAnnotation.get.length().==(-1)))
      FieldMetaData.defaultFieldLength(FieldMetaData.this.wrappedFieldType, this)
    else
      FieldMetaData.this.columnAnnotation.get.length();
    def scale: Int = if (FieldMetaData.this.columnAnnotation.==(scala.None).||(FieldMetaData.this.columnAnnotation.get.scale().==(-1)))
      FieldMetaData.this.schema.defaultSizeOfBigDecimal._2
    else
      FieldMetaData.this.columnAnnotation.get.scale();
    def schema: org.squeryl.Schema = FieldMetaData.this.parentMetaData.schema;
    def columnName: String = if (FieldMetaData.this.columnAnnotation.==(scala.None))
      {
        val nameDefinedInSchema: Option[String] = FieldMetaData.this._columnAttributes.find(((x$6: org.squeryl.internals.ColumnAttribute) => x$6.isInstanceOf[org.squeryl.internals.Named])).map[String](((x$7: org.squeryl.internals.ColumnAttribute) => x$7.asInstanceOf[org.squeryl.internals.Named].name));
        FieldMetaData.this.parentMetaData.schema.columnNameFromPropertyName(nameDefinedInSchema.getOrElse[String](FieldMetaData.this.nameOfProperty))
      }
    else
      {
        val ca: org.squeryl.annotations.Column = FieldMetaData.this.columnAnnotation.get;
        var res: String = ca.name();
        if (res.==(""))
          res = ca.value()
        else
          ();
        if (res.==(""))
          FieldMetaData.this.parentMetaData.schema.columnNameFromPropertyName(FieldMetaData.this.nameOfProperty)
        else
          res
      };
    protected def createResultSetHandler: (java.sql.ResultSet, Int) => AnyRef = this.schema.fieldMapper.resultSetHandlerFor(FieldMetaData.this.wrappedFieldType);
    private[this] val resultSetHandler: (java.sql.ResultSet, Int) => AnyRef = FieldMetaData.this.createResultSetHandler;
    <stable> <accessor> def resultSetHandler: (java.sql.ResultSet, Int) => AnyRef = FieldMetaData.this.resultSetHandler;
    if (FieldMetaData.this.isCustomType.unary_!)
      scala.this.Predef.assert(FieldMetaData.this.fieldType.==(FieldMetaData.this.wrappedFieldType), "expected fieldType == wrappedFieldType in primitive type mode, got ".+(FieldMetaData.this.fieldType.getName()).+(" != ").+(FieldMetaData.this.wrappedFieldType.getName()))
    else
      ();
    override def toString: String = FieldMetaData.this.parentMetaData.clasz.getSimpleName().+(".").+(FieldMetaData.this.columnName).+(":").+(FieldMetaData.this.displayType);
    def isStringType: Boolean = FieldMetaData.this.wrappedFieldType.isAssignableFrom(classOf[java.lang.String]);
    def displayType: String = if (FieldMetaData.this.isOption)
      "Option[".+(FieldMetaData.this.fieldType.getName()).+("]")
    else
      FieldMetaData.this.fieldType.getName();
    def declaredAsPrimaryKeyInSchema: Boolean = FieldMetaData.this.columnAttributes.exists(((x$8: org.squeryl.internals.ColumnAttribute) => x$8.isInstanceOf[org.squeryl.internals.PrimaryKey]));
    def isAutoIncremented: Boolean = FieldMetaData.this.columnAttributes.exists(((x$9: org.squeryl.internals.ColumnAttribute) => x$9.isInstanceOf[org.squeryl.internals.AutoIncremented]));
    def isInsertable: Boolean = FieldMetaData.this.columnAttributes.exists(((x$10: org.squeryl.internals.ColumnAttribute) => x$10.isInstanceOf[org.squeryl.internals.Uninsertable])).unary_!;
    def isUpdatable: Boolean = FieldMetaData.this.columnAttributes.exists(((x$11: org.squeryl.internals.ColumnAttribute) => x$11.isInstanceOf[org.squeryl.internals.Unupdatable])).unary_!;
    def get(o: AnyRef): AnyRef = try {
      val res: Object = if (FieldMetaData.this.getter.!=(scala.None))
        FieldMetaData.this._getFromGetter(o)
      else
        FieldMetaData.this._getFromField(o);
      if (FieldMetaData.this.isOption)
        if (res.==(scala.None))
          null
        else
          res.asInstanceOf[Option[_]].get.asInstanceOf[AnyRef]
      else
        res
    } catch {
      case (e @ (_: IllegalArgumentException)) => org.squeryl.internals.Utils.throwError(FieldMetaData.this.wrappedFieldType.getName().+(" used on ").+(o.getClass().getName()))
    };
    def getNativeJdbcValue(o: AnyRef): AnyRef = {
      val r: AnyRef = FieldMetaData.this.get(o);
      FieldMetaData.this.schema.fieldMapper.nativeJdbcValueFor(FieldMetaData.this.wrappedFieldType, r)
    };
    def setFromResultSet(target: AnyRef, rs: java.sql.ResultSet, index: Int): Unit = {
      val v: AnyRef = FieldMetaData.this.resultSetHandler.apply(rs, index);
      FieldMetaData.this.set(target, v)
    };
    def set(target: AnyRef, v: AnyRef): Unit = try {
      val v0: AnyRef = if (v.==(null))
        null
      else
        if (FieldMetaData.this.enumeration.!=(scala.None))
          FieldMetaData.this.canonicalEnumerationValueFor(v.asInstanceOf[Integer].intValue())
        else
          if (FieldMetaData.this.customTypeFactory.==(scala.None))
            v
          else
            {
              val f: AnyRef => Product1[Any] = FieldMetaData.this.customTypeFactory.get;
              if (v.isInstanceOf[org.squeryl.customtypes.CustomType[_]])
                {
                  val r: Any = v.asInstanceOf[org.squeryl.customtypes.CustomType[_]]._1;
                  f.apply(if (r.==(null))
                    null
                  else
                    r.asInstanceOf[AnyRef])
                }
              else
                f.apply(v)
            };
      val actualValue: AnyRef = if (FieldMetaData.this.isOption.unary_!)
        v0
      else
        if (v0.==(null))
          scala.None
        else
          scala.Some.apply[AnyRef](v0);
      val res: Any = if (FieldMetaData.this.setter.!=(scala.None))
        FieldMetaData.this._setWithSetter(target, actualValue)
      else
        FieldMetaData.this._setWithField(target, actualValue);
      ()
    } catch {
      case (e @ (_: Exception)) => {
        val typeOfV: String = if (v.==(null))
          "null"
        else
          v.getClass().getCanonicalName();
        org.squeryl.internals.Utils.throwError(scala.this.Predef.any2stringadd(this).+(" was invoked with value \'").+(v).+("\' of type ").+(typeOfV).+(" on object of type ").+(target.getClass().getName()).+(" \n").+(e))
      }
    };
    private def _getFromGetter(o: AnyRef): Object = FieldMetaData.this.getter.get.invoke(o);
    private def _setWithSetter(target: AnyRef, v: AnyRef): Object = FieldMetaData.this.setter.get.invoke(target, v);
    private def _getFromField(o: AnyRef): Object = FieldMetaData.this.field.get.get(o);
    private def _setWithField(target: AnyRef, v: AnyRef): Unit = FieldMetaData.this.field.get.set(target, v)
  };
  abstract trait FieldMetaDataFactory extends scala.AnyRef {
    def /*FieldMetaDataFactory*/$init$(): Unit = {
      ()
    };
    def hideFromYieldInspection(o: AnyRef, f: java.lang.reflect.Field): Boolean = false;
    def build(parentMetaData: org.squeryl.internals.PosoMetaData[_], name: String, property: (Option[java.lang.reflect.Field], Option[java.lang.reflect.Method], Option[java.lang.reflect.Method], Set[java.lang.annotation.Annotation]), sampleInstance4OptionTypeDeduction: AnyRef, isOptimisticCounter: Boolean): org.squeryl.internals.FieldMetaData;
    def createPosoFactory(posoMetaData: org.squeryl.internals.PosoMetaData[_]): () => AnyRef
  };
  object FieldMetaData extends scala.AnyRef {
    def <init>(): org.squeryl.internals.FieldMetaData.type = {
      FieldMetaData.super.<init>();
      ()
    };
    private[this] val _EMPTY_ARRAY: Array[Object] = new Array[Object](0);
    <stable> <accessor> private def _EMPTY_ARRAY: Array[Object] = FieldMetaData.this._EMPTY_ARRAY;
    private[this] var factory: org.squeryl.internals.FieldMetaDataFactory = {
      final class $anon extends Object with org.squeryl.internals.FieldMetaDataFactory {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        def createPosoFactory(posoMetaData: org.squeryl.internals.PosoMetaData[_]): () => AnyRef = (() => {
          val c: (java.lang.reflect.Constructor[_], Array[Object]) = posoMetaData.constructor;
          c._1.newInstance((c._2: _*)).asInstanceOf[AnyRef]
        });
        def build(parentMetaData: org.squeryl.internals.PosoMetaData[_], name: String, property: (Option[java.lang.reflect.Field], Option[java.lang.reflect.Method], Option[java.lang.reflect.Method], Set[java.lang.annotation.Annotation]), sampleInstance4OptionTypeDeduction: AnyRef, isOptimisticCounter: Boolean): org.squeryl.internals.FieldMetaData = {
          val fieldMapper: org.squeryl.internals.FieldMapper = parentMetaData.schema.fieldMapper;
          val field: Option[java.lang.reflect.Field] = property._1;
          val getter: Option[java.lang.reflect.Method] = property._2;
          val setter: Option[java.lang.reflect.Method] = property._3;
          val annotations: Set[java.lang.annotation.Annotation] = property._4;
          val colAnnotation: Option[org.squeryl.annotations.ColumnBase] = annotations.find(((a: java.lang.annotation.Annotation) => a.isInstanceOf[org.squeryl.annotations.ColumnBase])).map[org.squeryl.annotations.ColumnBase](((a: java.lang.annotation.Annotation) => a.asInstanceOf[org.squeryl.annotations.ColumnBase]));
          <synthetic> private[this] val x$12: (java.lang.reflect.Member, Class[?0], java.lang.reflect.Type) forSome { type ?0 } = (setter.map[(java.lang.reflect.Member, Class[?0], java.lang.reflect.Type) forSome { type ?0 }](((s: java.lang.reflect.Method) => scala.Tuple3.apply[java.lang.reflect.Member, Class[?0], java.lang.reflect.Type]((s: java.lang.reflect.Member), scala.this.Predef.refArrayOps[Class[_]](s.getParameterTypes()).head, scala.this.Predef.refArrayOps[java.lang.reflect.Type](s.getGenericParameterTypes()).head))).orElse[(java.lang.reflect.Member, Class[?0], java.lang.reflect.Type) forSome { type ?0 }](getter.map[(java.lang.reflect.Member, Class[?0], java.lang.reflect.Type) forSome { type ?0 }](((g: java.lang.reflect.Method) => scala.Tuple3.apply[java.lang.reflect.Member, Class[?0], java.lang.reflect.Type]((g: java.lang.reflect.Member), g.getReturnType(), g.getGenericReturnType())))).orElse[(java.lang.reflect.Member, Class[?0], java.lang.reflect.Type) forSome { type ?0 }](field.map[(java.lang.reflect.Member, Class[?0], Class[?0]) forSome { type ?0; type ?0 }](((f: java.lang.reflect.Field) => scala.Tuple3.apply[java.lang.reflect.Member, Class[?0], Class[?0]]((f: java.lang.reflect.Member), f.getType(), f.getType())))).getOrElse[(java.lang.reflect.Member, Class[?0], java.lang.reflect.Type) forSome { type ?0 }](org.squeryl.internals.Utils.throwError("invalid field group")): (java.lang.reflect.Member, Class[?0], java.lang.reflect.Type) @unchecked) match {
            case (_1: java.lang.reflect.Member, _2: Class[?0], _3: java.lang.reflect.Type)(java.lang.reflect.Member, Class[?0], java.lang.reflect.Type)((member @ _), (clsOfField @ _), (typeOfField @ _)) => scala.Tuple3.apply[java.lang.reflect.Member, Class[?0], java.lang.reflect.Type](member, clsOfField, typeOfField)
          };
          var member: java.lang.reflect.Member = x$12._1;
          var clsOfField: Class[_] = x$12._2;
          var typeOfField: java.lang.reflect.Type = x$12._3;
          var v: AnyRef = if (sampleInstance4OptionTypeDeduction.!=(null))
            field.flatMap[Object](((f: java.lang.reflect.Field) => f.get(sampleInstance4OptionTypeDeduction) match {
  case (a @ (_: AnyRef)) => scala.Some.apply[Object](a)
  case _ => scala.None
})).orElse[Object](getter.flatMap[Object](((x$13: java.lang.reflect.Method) => x$13.invoke(sampleInstance4OptionTypeDeduction, (FieldMetaData.this._EMPTY_ARRAY: _*)) match {
  case (a @ (_: AnyRef)) => scala.Some.apply[Object](a)
  case _ => scala.None
}))).getOrElse[Object](FieldMetaData.this.createDefaultValue(fieldMapper, member, clsOfField, scala.Some.apply[java.lang.reflect.Type](typeOfField), colAnnotation))
          else
            null;
          if (v.!=(null).&&(v.==(scala.None)))
            v = null
          else
            ();
          val constructorSuppliedDefaultValue: AnyRef = v;
          var customTypeFactory: Option[AnyRef => Product1[Any]] = scala.None;
          if (classOf[scala.Product1].isAssignableFrom(clsOfField))
            customTypeFactory = FieldMetaData.this._createCustomTypeFactory(fieldMapper, parentMetaData.clasz, clsOfField)
          else
            ();
          if (customTypeFactory.!=(scala.None))
            {
              val f: AnyRef => Product1[Any] = customTypeFactory.get;
              v = f.apply(null)
            }
          else
            ();
          if (v.==(null))
            v = try {
              FieldMetaData.this.createDefaultValue(fieldMapper, member, clsOfField, scala.Some.apply[java.lang.reflect.Type](typeOfField), colAnnotation)
            } catch {
              case (e @ (_: Exception)) => null
            }
          else
            ();
          val deductionFailed: Boolean = v match {
            case (x: Any)Some[Any](scala.None) => true
            case (a @ (_: Any)) => v.==(null)
          };
          if (deductionFailed)
            {
              var errorMessage: String = "Could not deduce Option[] type of field \'".+(name).+("\' of class ").+(parentMetaData.clasz.getName());
              if (FieldMetaData.this.detectScalapOnClasspath().unary_!)
                errorMessage = errorMessage.+("scalap option deduction not enabled. See: http://squeryl.org/scalap.html for more information.")
              else
                ();
              org.squeryl.internals.Utils.throwError(errorMessage)
            }
          else
            ();
          val isOption: Boolean = v.isInstanceOf[Some[_]];
          val typeOfFieldOrTypeOfOption: Class[_ <: AnyRef] = if (isOption.unary_!)
            v.getClass()
          else
            v.asInstanceOf[Option[AnyRef]].get.getClass();
          val primitiveFieldType: Class[_ <: AnyRef] = if (v.isInstanceOf[Product1[_]])
            v.asInstanceOf[Product1[Any]]._1.asInstanceOf[AnyRef].getClass()
          else
            if (isOption.&&(v.asInstanceOf[Option[AnyRef]].get.isInstanceOf[Product1[_]]))
              {
                customTypeFactory = FieldMetaData.this._createCustomTypeFactory(fieldMapper, parentMetaData.clasz, typeOfFieldOrTypeOfOption);
                v.asInstanceOf[Option[AnyRef]].get.asInstanceOf[Product1[Any]]._1.asInstanceOf[AnyRef].getClass()
              }
            else
              typeOfFieldOrTypeOfOption;
          new FieldMetaData(parentMetaData, name, typeOfFieldOrTypeOfOption, primitiveFieldType, customTypeFactory, isOption, getter, setter, field, colAnnotation, isOptimisticCounter, constructorSuppliedDefaultValue)
        }
      };
      new $anon()
    };
    <accessor> def factory: org.squeryl.internals.FieldMetaDataFactory = FieldMetaData.this.factory;
    <accessor> def factory_=(x$1: org.squeryl.internals.FieldMetaDataFactory): Unit = FieldMetaData.this.factory = x$1;
    private def _createCustomTypeFactory(fieldMapper: org.squeryl.internals.FieldMapper, ownerClass: Class[_], typeOfField: Class[_]): Option[AnyRef => Product1[Any]] = {
      @@<?> def find(c: Class[_]): Option[java.lang.reflect.Method] = if (c.!=(null))
        scala.this.Predef.refArrayOps[java.lang.reflect.Method](c.getMethods()).find(((m: java.lang.reflect.Method) => m.getName().==("value").&&(m.getReturnType().!=(classOf[java.lang.Object])))) match {
          case (x: java.lang.reflect.Method)Some[java.lang.reflect.Method]((m @ _)) => scala.Some.apply[java.lang.reflect.Method](m)
          case scala.None => find(c.getSuperclass())
        }
      else
        scala.None;
      def invoke(c: java.lang.reflect.Constructor[_], value: AnyRef): Product1[Any] = try {
        c.newInstance(value).asInstanceOf[Product1[Any]]
      } catch {
        case (ex @ (_: java.lang.reflect.InvocationTargetException)) => throw ex.getTargetException()
      };
      find(typeOfField).flatMap[AnyRef => Product1[Any]](((m: java.lang.reflect.Method) => {
        val pType: Class[_] = m.getReturnType();
        scala.this.Predef.assert(fieldMapper.isSupported(pType), scala.this.Predef.augmentString("enclosed type %s of CustomType %s is not a supported field type!").format(pType.getName(), typeOfField.getName()));
        val c: java.lang.reflect.Constructor[_] = typeOfField.getConstructor(pType);
        val defaultValue: Object = FieldMetaData.this.createDefaultValue(fieldMapper, c, pType, scala.None, scala.None);
        if (defaultValue.==(null))
          scala.None
        else
          scala.Some.apply[AnyRef => Product1[Any]](((i: AnyRef) => if (i.==(null))
            invoke(c, defaultValue)
          else
            invoke(c, i)))
      }))
    };
    def defaultFieldLength(fieldType: Class[_], fmd: org.squeryl.internals.FieldMetaData): Int = if (classOf[java.lang.String].isAssignableFrom(fieldType))
      fmd.schema.defaultLengthOfString
    else
      if (classOf[java.math.BigDecimal].isAssignableFrom(fieldType).||(classOf[scala.math.BigDecimal].isAssignableFrom(fieldType)))
        fmd.schema.defaultSizeOfBigDecimal._1
      else
        fmd.schema.fieldMapper.defaultColumnLength(fieldType);
    def detectScalapOnClasspath(): Boolean = try {
      java.this.lang.Class.forName("scala.tools.scalap.scalax.rules.scalasig.ByteCode");
      true
    } catch {
      case (cnfe @ (_: ClassNotFoundException)) => false
    };
    def optionTypeFromScalaSig(member: java.lang.reflect.Member): Option[Class[_]] = {
      val scalaSigOption: Option[scala.tools.scalap.scalax.rules.scalasig.ScalaSig] = scala.tools.scalap.scalax.rules.scalasig.ScalaSigParser.parse(member.getDeclaringClass());
      scalaSigOption.flatMap[Class[_]](((scalaSig: scala.tools.scalap.scalax.rules.scalasig.ScalaSig) => {
        val syms: List[scala.tools.scalap.scalax.rules.scalasig.ClassSymbol] = scalaSig.topLevelClasses;
        val baos: java.io.ByteArrayOutputStream = new java.io.ByteArrayOutputStream();
        val stream: java.io.PrintStream = new java.io.PrintStream(baos);
        val printer: scala.tools.scalap.scalax.rules.scalasig.ScalaSigPrinter = new scala.tools.scalap.scalax.rules.scalasig.ScalaSigPrinter(stream, true);
        syms.foreach[Unit](((c: scala.tools.scalap.scalax.rules.scalasig.ClassSymbol) => if (c.path.==(member.getDeclaringClass().getName()))
          printer.printSymbol(c)
        else
          ()));
        val fullSig: String = baos.toString();
        val matcher: java.util.regex.Matcher = scala.this.Predef.augmentString(scala.this.Predef.augmentString("\\s%s : scala.Option\\[scala\\.(\\w+)\\]?").format(member.getName())).r.pattern.matcher(fullSig);
        if (matcher.find())
          matcher.group(1) match {
            case "Int" => scala.Some.apply[Class[Int]](classOf[scala.Int])
            case "Short" => scala.Some.apply[Class[Short]](classOf[scala.Short])
            case "Long" => scala.Some.apply[Class[Long]](classOf[scala.Long])
            case "Double" => scala.Some.apply[Class[Double]](classOf[scala.Double])
            case "Float" => scala.Some.apply[Class[Float]](classOf[scala.Float])
            case "Boolean" => scala.Some.apply[Class[Boolean]](classOf[scala.Boolean])
            case "Byte" => scala.Some.apply[Class[Byte]](classOf[scala.Byte])
            case "Char" => scala.Some.apply[Class[Char]](classOf[scala.Char])
            case _ => scala.None
          }
        else
          scala.None
      }))
    };
    def createDefaultValue(fieldMapper: org.squeryl.internals.FieldMapper, member: java.lang.reflect.Member, p: Class[_], t: Option[java.lang.reflect.Type], optionFieldsInfo: Option[org.squeryl.annotations.Column]): Object = if (p.isAssignableFrom(classOf[scala.Option]))
      optionFieldsInfo.flatMap[Object](((ann: org.squeryl.annotations.Column) => if (ann.optionType().!=(classOf[java.lang.Object]))
  scala.Some.apply[Object](FieldMetaData.this.createDefaultValue(fieldMapper, member, ann.optionType(), scala.None, scala.None))
else
  scala.None)).orElse[Object](t match {
        case (x: java.lang.reflect.Type)Some[java.lang.reflect.Type]((pt @ (_: java.lang.reflect.ParameterizedType))) => scala.this.Predef.refArrayOps[java.lang.reflect.Type](pt.getActualTypeArguments()).toList match {
          case (hd: java.lang.reflect.Type, tl: List[java.lang.reflect.Type])scala.collection.immutable.::[java.lang.reflect.Type]((oType @ _), immutable.this.Nil) => if (classOf[java.lang.Class].isInstance(oType))
            {
              val trueTypeOption: Option[Class[_]] = if (classOf[java.lang.Object].==(oType).&&(FieldMetaData.this.detectScalapOnClasspath()))
                FieldMetaData.this.optionTypeFromScalaSig(member)
              else
                scala.Some.apply[Class[_$27]](oType.asInstanceOf[Class[_]]);
              trueTypeOption.flatMap[Object](((trueType: Class[_]) => {
                val deduced: Object = FieldMetaData.this.createDefaultValue(fieldMapper, member, trueType, scala.None, optionFieldsInfo);
                if (deduced.!=(null))
                  scala.Some.apply[Object](deduced)
                else
                  scala.None
              }))
            }
          else
            scala.None
          case _ => scala.None
        }
        case _ => scala.None
      })
    else
      fieldMapper.trySampleValueFor(p)
  }
}