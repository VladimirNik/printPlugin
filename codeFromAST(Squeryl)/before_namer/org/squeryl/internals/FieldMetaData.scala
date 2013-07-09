package org.squeryl.internals {
  import java.lang.annotation.Annotation;
  import java.lang.reflect.{Field, Method, Constructor, InvocationTargetException, Type, ParameterizedType};
  import java.sql.ResultSet;
  import java.math.BigDecimal;
  import scala.annotation.tailrec;
  import org.squeryl.annotations.{ColumnBase, Column};
  import collection.mutable.{HashMap, HashSet, ArrayBuffer};
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
    <paramaccessor> val parentMetaData: PosoMetaData[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> val nameOfProperty: String = _;
    <paramaccessor> val fieldType: Class[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> val wrappedFieldType: Class[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> val customTypeFactory: Option[_root_.scala.Function1[AnyRef, Product1[Any] with AnyRef]] = _;
    <paramaccessor> val isOption: Boolean = _;
    <paramaccessor> private[this] val getter: Option[Method] = _;
    <paramaccessor> private[this] val setter: Option[Method] = _;
    <paramaccessor> private[this] val field: Option[Field] = _;
    <paramaccessor> private[this] val columnAnnotation: Option[Column] = _;
    <paramaccessor> val isOptimisticCounter: Boolean = _;
    <paramaccessor> val sampleValue: Any = _;
    def <init>(parentMetaData: PosoMetaData[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, nameOfProperty: String, fieldType: Class[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }, wrappedFieldType: Class[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }, customTypeFactory: Option[_root_.scala.Function1[AnyRef, Product1[Any] with AnyRef]], isOption: Boolean, getter: Option[Method], setter: Option[Method], field: Option[Field], columnAnnotation: Option[Column], isOptimisticCounter: Boolean, sampleValue: Any) = {
      super.<init>();
      ()
    };
    def nativeJdbcType = this.schema.fieldMapper.nativeJdbcTypeFor(wrappedFieldType);
    val enumeration: Option[Enumeration] = sampleValue match {
      case Some((e @ (_: Enumeration#Value))) => Some(Utils.enumerationForValue(e))
      case (e @ (_: Enumeration#Value)) => Some(Utils.enumerationForValue(e))
      case _ => None
    };
    def canonicalEnumerationValueFor(id: Int) = if (sampleValue.$eq$eq(null))
      org.squeryl.internals.Utils.throwError("classes with Enumerations must have a zero param constructor that assigns a sample to the enumeration field")
    else
      enumeration.flatMap(((e: Enumeration) => e.values.find(((x$1) => x$1.id.$eq$eq(id))))).get;
    private val _columnAttributes = new HashSet[ColumnAttribute]();
    private[squeryl] def _clearColumnAttributes = _columnAttributes.clear;
    private[squeryl] def _addColumnAttribute(ca: ColumnAttribute) = _columnAttributes.add(ca);
    private val _sequenceNamePerDBAdapter = new HashMap[Class[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }, String]();
    def sequenceName: String = {
      val ai = _columnAttributes.find(((x$2) => x$2.isInstanceOf[AutoIncremented])).getOrElse(org.squeryl.internals.Utils.throwError(this.$plus(" is not declared as autoIncremented, hence it has no sequenceName"))).asInstanceOf[AutoIncremented];
      if (ai.nameOfSequence.$bang$eq(None))
        return ai.nameOfSequence.get
      else
        ();
      synchronized({
        val c = Session.currentSession.databaseAdapter.getClass;
        val s = _sequenceNamePerDBAdapter.get(c);
        if (s.$bang$eq(None))
          return s.get
        else
          ();
        val s0 = Session.currentSession.databaseAdapter.createSequenceName(this);
        _sequenceNamePerDBAdapter.put(c, s0);
        return s0
      })
    };
    def isIdFieldOfKeyedEntity = parentMetaData.viewOrTable.ked.map(((x$3) => x$3.idPropertyName.$eq$eq(nameOfProperty))).getOrElse(false);
    if (isIdFieldOfKeyedEntity.$amp$amp(classOf[CompositeKey].isAssignableFrom(wrappedFieldType).unary_$bang))
      schema.defaultColumnAttributesForKeyedEntityId(wrappedFieldType).foreach(((ca) => {
        if (ca.isInstanceOf[AutoIncremented].$amp$amp(wrappedFieldType.isAssignableFrom(classOf[java.lang.Long]).$bar$bar(wrappedFieldType.isAssignableFrom(classOf[java.lang.Integer])).unary_$bang))
          org.squeryl.internals.Utils.throwError("Schema ".$plus(schema.getClass.getName).$plus(" has method defaultColumnAttributesForKeyedEntityId returning AutoIncremented \nfor ").$plus(" all KeyedEntity tables, while class ").$plus(parentMetaData.clasz.getName).$plus("\n has it\'s id field of type ").$plus(fieldType.getName).$plus(", that is neither an Int or a Long, \n the only two types that can ").$plus("be auto incremented"))
        else
          ();
        _addColumnAttribute(ca)
      }))
    else
      ();
    private[squeryl] var _defaultValue: Option[ConstantTypedExpression[_$5, _$6] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = None;
    def columnAttributes: Iterable[ColumnAttribute] = _columnAttributes;
    def defaultValue: Option[ConstantTypedExpression[_$7, _$8] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _defaultValue;
    def explicitDbTypeDeclaration: Option[String] = {
      val dbt = _columnAttributes.find(((x$4) => x$4.isInstanceOf[DBType]));
      if (dbt.$eq$eq(None))
        None
      else
        Some(dbt.get.asInstanceOf[DBType].declaration)
    };
    def isTransient = _columnAttributes.exists(((x$5) => x$5.isInstanceOf[IsTransient]));
    def isCustomType = customTypeFactory.$bang$eq(None);
    def length: Int = if (columnAnnotation.$eq$eq(None).$bar$bar(columnAnnotation.get.length.$eq$eq(-1)))
      FieldMetaData.defaultFieldLength(wrappedFieldType, this)
    else
      columnAnnotation.get.length;
    def scale: Int = if (columnAnnotation.$eq$eq(None).$bar$bar(columnAnnotation.get.scale.$eq$eq(-1)))
      schema.defaultSizeOfBigDecimal._2
    else
      columnAnnotation.get.scale;
    def schema = parentMetaData.schema;
    def columnName = if (columnAnnotation.$eq$eq(None))
      {
        val nameDefinedInSchema = _columnAttributes.find(((x$6) => x$6.isInstanceOf[Named])).map(((x$7) => x$7.asInstanceOf[Named].name));
        parentMetaData.schema.columnNameFromPropertyName(nameDefinedInSchema.getOrElse(nameOfProperty))
      }
    else
      {
        val ca = columnAnnotation.get;
        var res = ca.name;
        if (res.$eq$eq(""))
          res = ca.value
        else
          ();
        if (res.$eq$eq(""))
          parentMetaData.schema.columnNameFromPropertyName(nameOfProperty)
        else
          res
      };
    protected def createResultSetHandler = this.schema.fieldMapper.resultSetHandlerFor(wrappedFieldType);
    val resultSetHandler = createResultSetHandler;
    if (isCustomType.unary_$bang)
      assert(fieldType.$eq$eq(wrappedFieldType), "expected fieldType == wrappedFieldType in primitive type mode, got ".$plus(fieldType.getName).$plus(" != ").$plus(wrappedFieldType.getName))
    else
      ();
    override def toString = parentMetaData.clasz.getSimpleName.$plus(".").$plus(columnName).$plus(":").$plus(displayType);
    def isStringType = wrappedFieldType.isAssignableFrom(classOf[String]);
    def displayType = if (isOption)
      "Option[".$plus(fieldType.getName).$plus("]")
    else
      fieldType.getName;
    def declaredAsPrimaryKeyInSchema = columnAttributes.exists(((x$8) => x$8.isInstanceOf[PrimaryKey]));
    def isAutoIncremented = columnAttributes.exists(((x$9) => x$9.isInstanceOf[AutoIncremented]));
    def isInsertable = columnAttributes.exists(((x$10) => x$10.isInstanceOf[Uninsertable])).unary_$bang;
    def isUpdatable = columnAttributes.exists(((x$11) => x$11.isInstanceOf[Unupdatable])).unary_$bang;
    def get(o: AnyRef): AnyRef = try {
      val res = if (getter.$bang$eq(None))
        _getFromGetter(o)
      else
        _getFromField(o);
      if (isOption)
        if (res.$eq$eq(None))
          null
        else
          res.asInstanceOf[Option[_$9] forSome { 
  <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
}].get.asInstanceOf[AnyRef]
      else
        res
    } catch {
      case (e @ (_: IllegalArgumentException)) => org.squeryl.internals.Utils.throwError(wrappedFieldType.getName.$plus(" used on ").$plus(o.getClass.getName))
    };
    def getNativeJdbcValue(o: AnyRef): AnyRef = {
      val r = get(o);
      schema.fieldMapper.nativeJdbcValueFor(wrappedFieldType, r)
    };
    def setFromResultSet(target: AnyRef, rs: ResultSet, index: Int) = {
      val v = resultSetHandler(rs, index);
      set(target, v)
    };
    def set(target: AnyRef, v: AnyRef) = try {
      val v0: AnyRef = if (v.$eq$eq(null))
        null
      else
        if (enumeration.$bang$eq(None))
          canonicalEnumerationValueFor(v.asInstanceOf[java.lang.Integer].intValue)
        else
          if (customTypeFactory.$eq$eq(None))
            v
          else
            {
              val f = customTypeFactory.get;
              if (v.isInstanceOf[CustomType[_$10] forSome { 
                <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
              }])
                {
                  val r = v.asInstanceOf[CustomType[_$11] forSome { 
  <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
}]._1;
                  f(if (r.$eq$eq(null))
                    null
                  else
                    r.asInstanceOf[AnyRef])
                }
              else
                f(v)
            };
      val actualValue = if (isOption.unary_$bang)
        v0
      else
        if (v0.$eq$eq(null))
          None
        else
          Some(v0);
      val res = if (setter.$bang$eq(None))
        _setWithSetter(target, actualValue)
      else
        _setWithField(target, actualValue);
      ()
    } catch {
      case (e @ (_: Exception)) => {
        val typeOfV = if (v.$eq$eq(null))
          "null"
        else
          v.getClass.getCanonicalName;
        org.squeryl.internals.Utils.throwError(this.$plus(" was invoked with value \'").$plus(v).$plus("\' of type ").$plus(typeOfV).$plus(" on object of type ").$plus(target.getClass.getName).$plus(" \n").$plus(e))
      }
    };
    private def _getFromGetter(o: AnyRef) = getter.get.invoke(o);
    private def _setWithSetter(target: AnyRef, v: AnyRef) = setter.get.invoke(target, v);
    private def _getFromField(o: AnyRef) = field.get.get(o);
    private def _setWithField(target: AnyRef, v: AnyRef) = field.get.set(target, v)
  };
  abstract trait FieldMetaDataFactory extends scala.AnyRef {
    def $init$() = {
      ()
    };
    def hideFromYieldInspection(o: AnyRef, f: Field): Boolean = false;
    def build(parentMetaData: PosoMetaData[_$12] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }, name: String, property: scala.Tuple4[Option[Field], Option[Method], Option[Method], Set[Annotation]], sampleInstance4OptionTypeDeduction: AnyRef, isOptimisticCounter: Boolean): FieldMetaData;
    def createPosoFactory(posoMetaData: PosoMetaData[_$13] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }): _root_.scala.Function0[AnyRef]
  };
  object FieldMetaData extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    private val _EMPTY_ARRAY = new Array[Object](0);
    var factory = {
      final class $anon extends FieldMetaDataFactory {
        def <init>() = {
          super.<init>();
          ()
        };
        def createPosoFactory(posoMetaData: PosoMetaData[_$14] forSome { 
          <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
        }): _root_.scala.Function0[AnyRef] = (() => {
          val c = posoMetaData.constructor;
          c._1.newInstance((c._2: _*)).asInstanceOf[AnyRef]
        });
        def build(parentMetaData: PosoMetaData[_$15] forSome { 
          <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
        }, name: String, property: scala.Tuple4[Option[Field], Option[Method], Option[Method], Set[Annotation]], sampleInstance4OptionTypeDeduction: AnyRef, isOptimisticCounter: Boolean) = {
          val fieldMapper = parentMetaData.schema.fieldMapper;
          val field = property._1;
          val getter = property._2;
          val setter = property._3;
          val annotations = property._4;
          val colAnnotation = annotations.find(((a) => a.isInstanceOf[ColumnBase])).map(((a) => a.asInstanceOf[ColumnBase]));
          <synthetic> private[this] val x$12 = setter.map(((s) => scala.Tuple3((s: Member), s.getParameterTypes.head, s.getGenericParameterTypes.head))).orElse(getter.map(((g) => scala.Tuple3((g: Member), g.getReturnType, g.getGenericReturnType)))).orElse(field.map(((f) => scala.Tuple3((f: Member), f.getType, f.getType)))).getOrElse(org.squeryl.internals.Utils.throwError("invalid field group")): @scala.unchecked match {
            case scala.Tuple3((member @ _), (clsOfField @ _), (typeOfField @ _)) => scala.Tuple3(member, clsOfField, typeOfField)
          };
          var member = x$12._1;
          var clsOfField = x$12._2;
          var typeOfField = x$12._3;
          var v: AnyRef = if (sampleInstance4OptionTypeDeduction.$bang$eq(null))
            field.flatMap(((f) => f.get(sampleInstance4OptionTypeDeduction) match {
  case (a @ (_: AnyRef)) => Some(a)
  case _ => None
})).orElse(getter.flatMap(((x$13) => x$13.invoke(sampleInstance4OptionTypeDeduction, (_EMPTY_ARRAY: _*)) match {
  case (a @ (_: AnyRef)) => Some(a)
  case _ => None
}))).getOrElse(createDefaultValue(fieldMapper, member, clsOfField, Some(typeOfField), colAnnotation))
          else
            null;
          if (v.$bang$eq(null).$amp$amp(v.$eq$eq(None)))
            v = null
          else
            ();
          val constructorSuppliedDefaultValue = v;
          var customTypeFactory: Option[_root_.scala.Function1[AnyRef, Product1[Any] with AnyRef]] = None;
          if (classOf[Product1[Any]].isAssignableFrom(clsOfField))
            customTypeFactory = _createCustomTypeFactory(fieldMapper, parentMetaData.clasz, clsOfField)
          else
            ();
          if (customTypeFactory.$bang$eq(None))
            {
              val f = customTypeFactory.get;
              v = f(null)
            }
          else
            ();
          if (v.$eq$eq(null))
            v = try {
              createDefaultValue(fieldMapper, member, clsOfField, Some(typeOfField), colAnnotation)
            } catch {
              case (e @ (_: Exception)) => null
            }
          else
            ();
          val deductionFailed = v match {
            case Some(None) => true
            case (a @ (_: Any)) => v.$eq$eq(null)
          };
          if (deductionFailed)
            {
              var errorMessage = "Could not deduce Option[] type of field \'".$plus(name).$plus("\' of class ").$plus(parentMetaData.clasz.getName);
              if (detectScalapOnClasspath().unary_$bang)
                errorMessage.$plus$eq("scalap option deduction not enabled. See: http://squeryl.org/scalap.html for more information.")
              else
                ();
              org.squeryl.internals.Utils.throwError(errorMessage)
            }
          else
            ();
          val isOption = v.isInstanceOf[Some[_$16] forSome { 
            <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
          }];
          val typeOfFieldOrTypeOfOption = if (isOption.unary_$bang)
            v.getClass
          else
            v.asInstanceOf[Option[AnyRef]].get.getClass;
          val primitiveFieldType = if (v.isInstanceOf[Product1[_$17] forSome { 
            <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
          }])
            v.asInstanceOf[Product1[Any]]._1.asInstanceOf[AnyRef].getClass
          else
            if (isOption.$amp$amp(v.asInstanceOf[Option[AnyRef]].get.isInstanceOf[Product1[_$18] forSome { 
              <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any
            }]))
              {
                customTypeFactory = _createCustomTypeFactory(fieldMapper, parentMetaData.clasz, typeOfFieldOrTypeOfOption);
                v.asInstanceOf[Option[AnyRef]].get.asInstanceOf[Product1[Any]]._1.asInstanceOf[AnyRef].getClass
              }
            else
              typeOfFieldOrTypeOfOption;
          new FieldMetaData(parentMetaData, name, typeOfFieldOrTypeOfOption, primitiveFieldType, customTypeFactory, isOption, getter, setter, field, colAnnotation, isOptimisticCounter, constructorSuppliedDefaultValue)
        }
      };
      new $anon()
    };
    private def _createCustomTypeFactory(fieldMapper: FieldMapper, ownerClass: Class[_$19] forSome { 
      <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any
    }, typeOfField: Class[_$20] forSome { 
      <synthetic> type _$20 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Option[_root_.scala.Function1[AnyRef, Product1[Any] with AnyRef]] = {
      @new tailrec() def find(c: Class[_$21] forSome { 
        <synthetic> type _$21 >: _root_.scala.Nothing <: _root_.scala.Any
      }): Option[Method] = if (c.$bang$eq(null))
        c.getMethods.find(((m) => m.getName.$eq$eq("value").$amp$amp(m.getReturnType.$bang$eq(classOf[java.lang.Object])))) match {
          case Some((m @ _)) => Some(m)
          case None => find(c.getSuperclass)
        }
      else
        None;
      def invoke(c: Constructor[_$22] forSome { 
        <synthetic> type _$22 >: _root_.scala.Nothing <: _root_.scala.Any
      }, value: AnyRef) = try {
        c.newInstance(value).asInstanceOf[Product1[Any] with AnyRef]
      } catch {
        case (ex @ (_: InvocationTargetException)) => throw ex.getTargetException
      };
      find(typeOfField).flatMap(((m) => {
        val pType = m.getReturnType;
        assert(fieldMapper.isSupported(pType), "enclosed type %s of CustomType %s is not a supported field type!".format(pType.getName, typeOfField.getName));
        val c = typeOfField.getConstructor(pType);
        val defaultValue = createDefaultValue(fieldMapper, c, pType, None, None);
        if (defaultValue.$eq$eq(null))
          None
        else
          Some(((i: AnyRef) => if (i.$eq$eq(null))
            invoke(c, defaultValue)
          else
            invoke(c, i)))
      }))
    };
    def defaultFieldLength(fieldType: Class[_$23] forSome { 
      <synthetic> type _$23 >: _root_.scala.Nothing <: _root_.scala.Any
    }, fmd: FieldMetaData) = if (classOf[String].isAssignableFrom(fieldType))
      fmd.schema.defaultLengthOfString
    else
      if (classOf[java.math.BigDecimal].isAssignableFrom(fieldType).$bar$bar(classOf[scala.math.BigDecimal].isAssignableFrom(fieldType)))
        fmd.schema.defaultSizeOfBigDecimal._1
      else
        fmd.schema.fieldMapper.defaultColumnLength(fieldType);
    def detectScalapOnClasspath(): Boolean = try {
      Class.forName("scala.tools.scalap.scalax.rules.scalasig.ByteCode");
      true
    } catch {
      case (cnfe @ (_: ClassNotFoundException)) => false
    };
    def optionTypeFromScalaSig(member: Member): Option[Class[_$24] forSome { 
      <synthetic> type _$24 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = {
      val scalaSigOption = ScalaSigParser.parse(member.getDeclaringClass());
      scalaSigOption.flatMap(((scalaSig) => {
        val syms = scalaSig.topLevelClasses;
        val baos = new ByteArrayOutputStream();
        val stream = new PrintStream(baos);
        val printer = new ScalaSigPrinter(stream, true);
        syms.foreach(((c) => if (c.path.$eq$eq(member.getDeclaringClass().getName()))
          printer.printSymbol(c)
        else
          ()));
        val fullSig = baos.toString;
        val matcher = "\\s%s : scala.Option\\[scala\\.(\\w+)\\]?".format(member.getName).r.pattern.matcher(fullSig);
        if (matcher.find)
          matcher.group(1) match {
            case "Int" => Some(classOf[scala.Int])
            case "Short" => Some(classOf[scala.Short])
            case "Long" => Some(classOf[scala.Long])
            case "Double" => Some(classOf[scala.Double])
            case "Float" => Some(classOf[scala.Float])
            case "Boolean" => Some(classOf[scala.Boolean])
            case "Byte" => Some(classOf[scala.Byte])
            case "Char" => Some(classOf[scala.Char])
            case _ => None
          }
        else
          None
      }))
    };
    def createDefaultValue(fieldMapper: FieldMapper, member: Member, p: Class[_$25] forSome { 
      <synthetic> type _$25 >: _root_.scala.Nothing <: _root_.scala.Any
    }, t: Option[Type], optionFieldsInfo: Option[Column]): Object = if (p.isAssignableFrom(classOf[Option[Any]]))
      optionFieldsInfo.flatMap(((ann) => if (ann.optionType.$bang$eq(classOf[Object]))
  Some(createDefaultValue(fieldMapper, member, ann.optionType, None, None))
else
  None)).orElse(t match {
        case Some((pt @ (_: ParameterizedType))) => pt.getActualTypeArguments.toList match {
          case $colon$colon((oType @ _), Nil) => if (classOf[Class[_$26] forSome { 
  <synthetic> type _$26 >: _root_.scala.Nothing <: _root_.scala.Any
}].isInstance(oType))
            {
              val trueTypeOption = if (classOf[Object].$eq$eq(oType).$amp$amp(detectScalapOnClasspath()))
                optionTypeFromScalaSig(member)
              else
                Some(oType.asInstanceOf[Class[_$27] forSome { 
                  <synthetic> type _$27 >: _root_.scala.Nothing <: _root_.scala.Any
                }]);
              trueTypeOption.flatMap(((trueType) => {
                val deduced = createDefaultValue(fieldMapper, member, trueType, None, optionFieldsInfo);
                if (deduced.$bang$eq(null))
                  Some(deduced)
                else
                  None
              }))
            }
          else
            None
          case _ => None
        }
        case _ => None
      })
    else
      fieldMapper.trySampleValueFor(p)
  }
}