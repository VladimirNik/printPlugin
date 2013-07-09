package org.squeryl.internals {
  import java.lang.Class;
  import java.lang.annotation.Annotation;
  import net.sf.cglib.proxy.{Factory, Callback, CallbackFilter, Enhancer, NoOp};
  import java.lang.reflect.{Member, Constructor, Method, Field, Modifier};
  import collection.mutable.{HashSet, ArrayBuffer};
  import org.squeryl.annotations._;
  import org.squeryl._;
  import dsl.CompositeKey;
  class PosoMetaData[T >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val clasz: Class[T] = _;
    <paramaccessor> val schema: Schema = _;
    <paramaccessor> val viewOrTable: View[T] = _;
    def <init>(clasz: Class[T], schema: Schema, viewOrTable: View[T]) = {
      super.<init>();
      ()
    };
    override def toString = scala.Symbol("PosoMetaData").$plus("[").$plus(clasz.getSimpleName).$plus("]").$plus(fieldsMetaData.mkString("(", ",", ")"));
    def findFieldMetaDataForProperty(name: String) = _fieldsMetaData.find(((fmd) => fmd.nameOfProperty.$eq$eq(name)));
    val isOptimistic = viewOrTable.ked.map(((x$1) => x$1.isOptimistic)).getOrElse(false);
    val constructor = _const.headOption.orElse(org.squeryl.internals.Utils.throwError(clasz.getName.$plus(" must have a 0 param constructor or a constructor with only primitive types"))).get;
    def fieldsMetaData = _fieldsMetaData.filter(((x$2) => x$2.isTransient.unary_$bang));
    <synthetic> private[this] val x$3 = ({
      val isImplicitMode = _isImplicitMode;
      val setters = new ArrayBuffer[Method]();
      val sampleInstance4OptionTypeDeduction = try {
        constructor._1.newInstance((constructor._2: _*)).asInstanceOf[AnyRef]
      } catch {
        case (e @ (_: IllegalArgumentException)) => throw new RuntimeException("invalid constructor choice ".$plus(constructor._1), e)
        case (e @ (_: Exception)) => throw new RuntimeException("exception occurred while invoking constructor : ".$plus(constructor._1), e)
      };
      val members = new ArrayBuffer[scala.Tuple2[Member, HashSet[Annotation]]]();
      _fillWithMembers(clasz, members);
      val name2MembersMap = members.groupBy(((m) => {
        val n = m._1.getName;
        val idx = n.indexOf("_$eq");
        if (idx.$bang$eq(-1))
          n.substring(0, idx)
        else
          n
      }));
      val fmds = new ArrayBuffer[FieldMetaData]();
      name2MembersMap.foreach(((e) => {
        val name = e._1;
        val v = e._2;
        var a: Set[Annotation] = Set.empty;
        v.foreach(((memberWithAnnotationTuple) => a = a.union(memberWithAnnotationTuple._2)));
        val members = v.map(((t) => t._1));
        val o = classOf[java.lang.Object];
        val field = members.filter(((m) => m.isInstanceOf[Field])).map(((m) => m.asInstanceOf[Field])).filter(((f) => f.getType.$bang$eq(o))).headOption;
        val getter = members.filter(((m) => m.isInstanceOf[Method].$amp$amp(m.getName.$eq$eq(name)))).map(((m) => m.asInstanceOf[Method])).filter(((m) => m.getReturnType.$bang$eq(o))).headOption;
        val setter = members.filter(((m) => m.isInstanceOf[Method].$amp$amp(m.getName.endsWith("_$eq")))).map(((m) => m.asInstanceOf[Method])).filter(((m) => m.getParameterTypes.apply(0).$bang$eq(o))).headOption;
        val property = scala.Tuple4(field, getter, setter, a);
        if (isImplicitMode.$amp$amp(_groupOfMembersIsProperty(property)))
          {
            val isOptimisitcCounter = viewOrTable.ked.flatMap(((k) => k.optimisticCounterPropertyName.withFilter(((counterProp) => counterProp.$eq$eq(name))).map(((counterProp) => true)))).isDefined;
            try {
              fmds.append(FieldMetaData.factory.build(this, name, property, sampleInstance4OptionTypeDeduction, isOptimisitcCounter))
            } catch {
              case (e @ (_: Exception)) => throw new RuntimeException(Utils.failSafeString("error while reflecting on metadata for ".$plus(property).$plus(" of class ").$plus(this.clasz.getCanonicalName)), e)
            }
          }
        else
          ()
      }));
      var k = fmds.find(((fmd) => fmd.isIdFieldOfKeyedEntity));
      val compositePrimaryKeyGetter: Option[Method] = if (k.$bang$eq(None))
        None
      else
        viewOrTable.ked.map(((ked) => {
          val pkMethod = clasz.getMethod(ked.idPropertyName);
          assert(pkMethod.$bang$eq(null), "Could not get getter for ".$plus(ked.idPropertyName).$plus(" in ").$plus(clasz.getCanonicalName()));
          pkMethod
        }));
      val metaDataForPk: Option[Either[FieldMetaData, Method]] = if (k.$bang$eq(None))
        Some(Left(k.get))
      else
        if (compositePrimaryKeyGetter.$bang$eq(None))
          Some(Right(compositePrimaryKeyGetter.get))
        else
          None;
      scala.Tuple2(fmds, metaDataForPk)
    }: @scala.unchecked: scala.Tuple2[Iterable[FieldMetaData], Option[Either[FieldMetaData, Method]]]) match {
      case scala.Tuple2((_fieldsMetaData @ _), (primaryKey @ _)) => scala.Tuple2(_fieldsMetaData, primaryKey)
    };
    val _fieldsMetaData = x$3._1;
    val primaryKey = x$3._2;
    def optimisticCounter = fieldsMetaData.find(((fmd) => fmd.isOptimisticCounter));
    if (isOptimistic)
      assert(optimisticCounter.$bang$eq(None))
    else
      ();
    def _const = {
      val r = new ArrayBuffer[scala.Tuple2[Constructor[_$1] forSome { 
        <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
      }, Array[Object]]]();
      clasz.getConstructors.foreach(((ct) => _tryToCreateParamArray(r, ct)));
      r.sortWith(((a: scala.Tuple2[Constructor[_$2] forSome { 
        <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
      }, Array[Object]], b: scala.Tuple2[Constructor[_$3] forSome { 
        <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
      }, Array[Object]]) => a._2.length.$less(b._2.length)))
    };
    def _tryToCreateParamArray(r: ArrayBuffer[scala.Tuple2[Constructor[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }, Array[Object]]], c: Constructor[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Unit = {
      val params: Array[Class[_$6] forSome { 
        <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
      }] = c.getParameterTypes;
      if (params.length.$greater$eq(1))
        {
          val cn = clasz.getName;
          val test = params(0).getName.$plus("$").$plus(clasz.getSimpleName);
          if (cn.$eq$eq(test))
            org.squeryl.internals.Utils.throwError("inner classes are not supported, except when outer class is a singleton (object)\ninner class is : ".$plus(cn))
          else
            ()
        }
      else
        ();
      var res = new Array[Object](params.size);
      0.to(params.length.$minus(1)).foreach(((i) => {
        val v = FieldMetaData.createDefaultValue(schema.fieldMapper, c, params(i), None, None);
        res.update(i, v)
      }));
      r.append(scala.Tuple2(c, res))
    };
    private def _noOptionalColumnDeclared = org.squeryl.internals.Utils.throwError("class ".$plus(clasz.getName).$plus(" has an Option[] member with no Column annotation with optionType declared."));
    def createSample(cb: Callback) = FieldReferenceLinker.executeAndRestoreLastAccessedFieldReference(_builder(cb));
    private val _builder: _root_.scala.Function1[Callback, T] = {
      val e = new Enhancer();
      e.setSuperclass(clasz);
      val pc: Array[Class[_$7] forSome { 
        <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
      }] = constructor._1.getParameterTypes;
      val args: Array[Object] = constructor._2;
      e.setUseFactory(true);
      ((callB: Callback) => {
        val cb = Array[Callback](callB, NoOp.INSTANCE);
        e.setCallbacks(cb);
        e.setCallbackFilter(PosoMetaData.finalizeFilter);
        val fac = e.create(pc, constructor._2).asInstanceOf[Factory];
        fac.newInstance(pc, constructor._2, cb).asInstanceOf[T]
      })
    };
    private def _isImplicitMode = {
      val rowAnnotation = clasz.getAnnotation(classOf[Row]);
      rowAnnotation.$eq$eq(null).$bar$bar(rowAnnotation.fieldToColumnCorrespondanceMode.$eq$eq(FieldToColumnCorrespondanceMode.IMPLICIT))
    };
    private def _groupOfMembersIsProperty(property: scala.Tuple4[Option[Field], Option[Method], Option[Method], Set[Annotation]]): Boolean = {
      if (property._4.find(((an) => an.isInstanceOf[Transient])).$bang$eq(None))
        return false
      else
        ();
      val hasAField = property._1.$bang$eq(None);
      val isAStaticField = property._1.map(((f) => Modifier.isStatic(f.getModifiers))).getOrElse(false);
      val hasGetter = property._2.$bang$eq(None).$amp$amp(classOf[java.lang.Void].isAssignableFrom(property._2.get.getReturnType).unary_$bang).$amp$amp(property._2.get.getParameterTypes.length.$eq$eq(0));
      val hasSetter = property._3.$bang$eq(None).$amp$amp(property._3.get.getParameterTypes.length.$eq$eq(1));
      val memberTypes = new ArrayBuffer[Class[_$8] forSome { 
        <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
      }]();
      if (hasAField)
        memberTypes.append(property._1.get.getType)
      else
        ();
      if (hasGetter)
        memberTypes.append(property._2.get.getReturnType)
      else
        ();
      if (hasSetter)
        memberTypes.append(property._3.get.getParameterTypes.apply(0))
      else
        ();
      if (memberTypes.size.$eq$eq(0))
        return false
      else
        ();
      val c = memberTypes.remove(0);
      memberTypes.foreach(((c0) => if (c0.isAssignableFrom(c).unary_$bang.$amp$amp(c.isAssignableFrom(c0).unary_$bang))
        return false
      else
        ()));
      scala.Tuple3(hasAField, hasGetter, hasSetter) match {
        case scala.Tuple3(true, false, false) => isAStaticField.unary_$bang
        case scala.Tuple3(false, true, true) => true
        case scala.Tuple3(true, true, true) => true
        case scala.Tuple3(true, true, false) => true
        case (a @ (_: Any)) => false
      }
    };
    private def _includeAnnotation(a: Annotation) = a.isInstanceOf[ColumnBase].$bar$bar(a.isInstanceOf[Transient]).$bar$bar(a.isInstanceOf[OptionType]);
    private def _addAnnotations(m: Field, s: HashSet[Annotation]) = m.getAnnotations.withFilter(((a) => _includeAnnotation(a))).foreach(((a) => s.add(a)));
    private def _addAnnotations(m: Method, s: HashSet[Annotation]) = m.getAnnotations.withFilter(((a) => _includeAnnotation(a))).foreach(((a) => s.add(a)));
    private def _includeFieldOrMethodType(c: Class[_$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = schema.fieldMapper.isSupported(c);
    private def _fillWithMembers(clasz: Class[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }, members: ArrayBuffer[scala.Tuple2[Member, HashSet[Annotation]]]): scala.Unit = {
      clasz.getMethods.withFilter(((m) => m.getDeclaringClass.$bang$eq(classOf[Object]).$amp$amp(_includeFieldOrMethodType(m.getReturnType)))).foreach(((m) => {
        m.setAccessible(true);
        val t = scala.Tuple2(m, new HashSet[Annotation]());
        _addAnnotations(m, t._2);
        members.append(t)
      }));
      clasz.getDeclaredFields.withFilter(((m) => m.getName.indexOf("$").$eq$eq(-1).$amp$amp(_includeFieldOrMethodType(m.getType)))).foreach(((m) => {
        m.setAccessible(true);
        val t = scala.Tuple2(m, new HashSet[Annotation]());
        _addAnnotations(m, t._2);
        members.append(t)
      }));
      val c = clasz.getSuperclass;
      if (c.$bang$eq(null))
        _fillWithMembers(c, members)
      else
        ()
    }
  };
  object PosoMetaData extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    val finalizeFilter = {
      final class $anon extends CallbackFilter {
        def <init>() = {
          super.<init>();
          ()
        };
        def accept(method: Method): Int = if (method.getName.$eq$eq("finalize"))
          1
        else
          0
      };
      new $anon()
    }
  }
}