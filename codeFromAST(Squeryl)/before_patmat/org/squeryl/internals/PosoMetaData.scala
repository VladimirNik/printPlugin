package org.squeryl.internals {
  import java.lang.Class;
  import java.lang.annotation.Annotation;
  import net.sf.cglib.proxy.{Factory, Callback, CallbackFilter, Enhancer, NoOp};
  import java.lang.reflect.{Member, Constructor, Method, Field, Modifier};
  import scala.collection.mutable.{HashSet, ArrayBuffer};
  import org.squeryl.annotations._;
  import org.squeryl._;
  import org.squeryl.dsl.CompositeKey;
  class PosoMetaData[T >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val clasz: Class[T] = _;
    <stable> <accessor> <paramaccessor> def clasz: Class[T] = PosoMetaData.this.clasz;
    <paramaccessor> private[this] val schema: org.squeryl.Schema = _;
    <stable> <accessor> <paramaccessor> def schema: org.squeryl.Schema = PosoMetaData.this.schema;
    <paramaccessor> private[this] val viewOrTable: org.squeryl.View[T] = _;
    <stable> <accessor> <paramaccessor> def viewOrTable: org.squeryl.View[T] = PosoMetaData.this.viewOrTable;
    def <init>(clasz: Class[T], schema: org.squeryl.Schema, viewOrTable: org.squeryl.View[T]): org.squeryl.internals.PosoMetaData[T] = {
      PosoMetaData.super.<init>();
      ()
    };
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("PosoMetaData")).+("[").+(PosoMetaData.this.clasz.getSimpleName()).+("]").+(PosoMetaData.this.fieldsMetaData.mkString("(", ",", ")"));
    def findFieldMetaDataForProperty(name: String): Option[org.squeryl.internals.FieldMetaData] = PosoMetaData.this._fieldsMetaData.find(((fmd: org.squeryl.internals.FieldMetaData) => fmd.nameOfProperty.==(name)));
    private[this] val isOptimistic: Boolean = PosoMetaData.this.viewOrTable.ked.map[Boolean](((x$1: org.squeryl.KeyedEntityDef[T, _]) => x$1.isOptimistic)).getOrElse[Boolean](false);
    <stable> <accessor> def isOptimistic: Boolean = PosoMetaData.this.isOptimistic;
    private[this] val constructor: (java.lang.reflect.Constructor[_], Array[Object]) = PosoMetaData.this._const.headOption.orElse[(java.lang.reflect.Constructor[_], Array[Object])](org.squeryl.internals.Utils.throwError(PosoMetaData.this.clasz.getName().+(" must have a 0 param constructor or a constructor with only primitive types"))).get;
    <stable> <accessor> def constructor: (java.lang.reflect.Constructor[_], Array[Object]) = PosoMetaData.this.constructor;
    def fieldsMetaData: Iterable[org.squeryl.internals.FieldMetaData] = PosoMetaData.this._fieldsMetaData.filter(((x$2: org.squeryl.internals.FieldMetaData) => x$2.isTransient.unary_!));
    <synthetic> private[this] val x$3: (Iterable[org.squeryl.internals.FieldMetaData], Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]]) = (({
      val isImplicitMode: Boolean = PosoMetaData.this._isImplicitMode;
      val setters: scala.collection.mutable.ArrayBuffer[java.lang.reflect.Method] = new scala.collection.mutable.ArrayBuffer[java.lang.reflect.Method]();
      val sampleInstance4OptionTypeDeduction: AnyRef = try {
        PosoMetaData.this.constructor._1.newInstance((PosoMetaData.this.constructor._2: _*)).asInstanceOf[AnyRef]
      } catch {
        case (e @ (_: IllegalArgumentException)) => throw new scala.`package`.RuntimeException("invalid constructor choice ".+(PosoMetaData.this.constructor._1), e)
        case (e @ (_: Exception)) => throw new scala.`package`.RuntimeException("exception occurred while invoking constructor : ".+(PosoMetaData.this.constructor._1), e)
      };
      val members: scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])] = new scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])]();
      PosoMetaData.this._fillWithMembers(PosoMetaData.this.clasz, members);
      val name2MembersMap: scala.collection.immutable.Map[String,scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])]] = members.groupBy[String](((m: (java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])) => {
        val n: String = m._1.getName();
        val idx: Int = n.indexOf("_$eq");
        if (idx.!=(-1))
          n.substring(0, idx)
        else
          n
      }));
      val fmds: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.FieldMetaData] = new scala.collection.mutable.ArrayBuffer[org.squeryl.internals.FieldMetaData]();
      name2MembersMap.foreach[Unit](((e: (String, scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])])) => {
        val name: String = e._1;
        val v: scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])] = e._2;
        var a: Set[java.lang.annotation.Annotation] = scala.this.Predef.Set.empty[java.lang.annotation.Annotation];
        v.foreach[Unit](((memberWithAnnotationTuple: (java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])) => a = a.union(memberWithAnnotationTuple._2)));
        val members: scala.collection.mutable.ArrayBuffer[java.lang.reflect.Member] = v.map[java.lang.reflect.Member, scala.collection.mutable.ArrayBuffer[java.lang.reflect.Member]](((t: (java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])) => t._1))(mutable.this.ArrayBuffer.canBuildFrom[java.lang.reflect.Member]);
        val o: Class[Object] = classOf[java.lang.Object];
        val field: Option[java.lang.reflect.Field] = members.filter(((m: java.lang.reflect.Member) => m.isInstanceOf[java.lang.reflect.Field])).map[java.lang.reflect.Field, scala.collection.mutable.ArrayBuffer[java.lang.reflect.Field]](((m: java.lang.reflect.Member) => m.asInstanceOf[java.lang.reflect.Field]))(mutable.this.ArrayBuffer.canBuildFrom[java.lang.reflect.Field]).filter(((f: java.lang.reflect.Field) => f.getType().!=(o))).headOption;
        val getter: Option[java.lang.reflect.Method] = members.filter(((m: java.lang.reflect.Member) => m.isInstanceOf[java.lang.reflect.Method].&&(m.getName().==(name)))).map[java.lang.reflect.Method, scala.collection.mutable.ArrayBuffer[java.lang.reflect.Method]](((m: java.lang.reflect.Member) => m.asInstanceOf[java.lang.reflect.Method]))(mutable.this.ArrayBuffer.canBuildFrom[java.lang.reflect.Method]).filter(((m: java.lang.reflect.Method) => m.getReturnType().!=(o))).headOption;
        val setter: Option[java.lang.reflect.Method] = members.filter(((m: java.lang.reflect.Member) => m.isInstanceOf[java.lang.reflect.Method].&&(m.getName().endsWith("_$eq")))).map[java.lang.reflect.Method, scala.collection.mutable.ArrayBuffer[java.lang.reflect.Method]](((m: java.lang.reflect.Member) => m.asInstanceOf[java.lang.reflect.Method]))(mutable.this.ArrayBuffer.canBuildFrom[java.lang.reflect.Method]).filter(((m: java.lang.reflect.Method) => m.getParameterTypes().apply(0).!=(o))).headOption;
        val property: (Option[java.lang.reflect.Field], Option[java.lang.reflect.Method], Option[java.lang.reflect.Method], Set[java.lang.annotation.Annotation]) = scala.Tuple4.apply[Option[java.lang.reflect.Field], Option[java.lang.reflect.Method], Option[java.lang.reflect.Method], Set[java.lang.annotation.Annotation]](field, getter, setter, a);
        if (isImplicitMode.&&(PosoMetaData.this._groupOfMembersIsProperty(property)))
          {
            val isOptimisitcCounter: Boolean = PosoMetaData.this.viewOrTable.ked.flatMap[Boolean](((k: org.squeryl.KeyedEntityDef[T, _]) => k.optimisticCounterPropertyName.withFilter(((counterProp: String) => counterProp.==(name))).map[Boolean](((counterProp: String) => true)))).isDefined;
            try {
              fmds.append(FieldMetaData.factory.build(this, name, property, sampleInstance4OptionTypeDeduction, isOptimisitcCounter))
            } catch {
              case (e @ (_: Exception)) => throw new scala.`package`.RuntimeException(Utils.failSafeString("error while reflecting on metadata for ".+(property).+(" of class ").+(this.clasz.getCanonicalName())), e)
            }
          }
        else
          ()
      }));
      var k: Option[org.squeryl.internals.FieldMetaData] = fmds.find(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isIdFieldOfKeyedEntity));
      val compositePrimaryKeyGetter: Option[java.lang.reflect.Method] = if (k.!=(scala.None))
        scala.None
      else
        PosoMetaData.this.viewOrTable.ked.map[java.lang.reflect.Method](((ked: org.squeryl.KeyedEntityDef[T, _]) => {
          val pkMethod: java.lang.reflect.Method = PosoMetaData.this.clasz.getMethod(ked.idPropertyName);
          scala.this.Predef.assert(pkMethod.!=(null), "Could not get getter for ".+(ked.idPropertyName).+(" in ").+(PosoMetaData.this.clasz.getCanonicalName()));
          pkMethod
        }));
      val metaDataForPk: Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]] = if (k.!=(scala.None))
        scala.Some.apply[scala.util.Left[org.squeryl.internals.FieldMetaData,Nothing]](scala.`package`.Left.apply[org.squeryl.internals.FieldMetaData, Nothing](k.get))
      else
        if (compositePrimaryKeyGetter.!=(scala.None))
          scala.Some.apply[scala.util.Right[Nothing,java.lang.reflect.Method]](scala.`package`.Right.apply[Nothing, java.lang.reflect.Method](compositePrimaryKeyGetter.get))
        else
          scala.None;
      scala.Tuple2.apply[scala.collection.mutable.ArrayBuffer[org.squeryl.internals.FieldMetaData], Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]]](fmds, metaDataForPk)
    }: (scala.collection.mutable.ArrayBuffer[org.squeryl.internals.FieldMetaData], Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]]) @unchecked): (Iterable[org.squeryl.internals.FieldMetaData], Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]])) match {
      case (_1: Iterable[org.squeryl.internals.FieldMetaData], _2: Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]])(Iterable[org.squeryl.internals.FieldMetaData], Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]])((_fieldsMetaData @ _), (primaryKey @ _)) => scala.Tuple2.apply[Iterable[org.squeryl.internals.FieldMetaData], Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]]](_fieldsMetaData, primaryKey)
    };
    private[this] val _fieldsMetaData: Iterable[org.squeryl.internals.FieldMetaData] = PosoMetaData.this.x$3._1;
    <stable> <accessor> def _fieldsMetaData: Iterable[org.squeryl.internals.FieldMetaData] = PosoMetaData.this._fieldsMetaData;
    private[this] val primaryKey: Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]] = PosoMetaData.this.x$3._2;
    <stable> <accessor> def primaryKey: Option[Either[org.squeryl.internals.FieldMetaData,java.lang.reflect.Method]] = PosoMetaData.this.primaryKey;
    def optimisticCounter: Option[org.squeryl.internals.FieldMetaData] = PosoMetaData.this.fieldsMetaData.find(((fmd: org.squeryl.internals.FieldMetaData) => fmd.isOptimisticCounter));
    if (PosoMetaData.this.isOptimistic)
      scala.this.Predef.assert(PosoMetaData.this.optimisticCounter.!=(scala.None))
    else
      ();
    def _const: scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Constructor[_], Array[Object])] = {
      val r: scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Constructor[_], Array[Object])] = new scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Constructor[_], Array[Object])]();
      scala.this.Predef.refArrayOps[java.lang.reflect.Constructor[_]](PosoMetaData.this.clasz.getConstructors()).foreach[Unit](((ct: java.lang.reflect.Constructor[_]) => PosoMetaData.this._tryToCreateParamArray(r, ct)));
      r.sortWith(((a: (java.lang.reflect.Constructor[_], Array[Object]), b: (java.lang.reflect.Constructor[_], Array[Object])) => a._2.length.<(b._2.length)))
    };
    def _tryToCreateParamArray(r: scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Constructor[_], Array[Object])], c: java.lang.reflect.Constructor[_]): Unit = {
      val params: Array[Class[_]] = c.getParameterTypes();
      if (params.length.>=(1))
        {
          val cn: String = PosoMetaData.this.clasz.getName();
          val test: String = params.apply(0).getName().+("$").+(PosoMetaData.this.clasz.getSimpleName());
          if (cn.==(test))
            org.squeryl.internals.Utils.throwError("inner classes are not supported, except when outer class is a singleton (object)\ninner class is : ".+(cn))
          else
            ()
        }
      else
        ();
      var res: Array[Object] = new Array[Object](scala.this.Predef.refArrayOps[Class[_]](params).size);
      scala.this.Predef.intWrapper(0).to(params.length.-(1)).foreach[Unit](((i: Int) => {
        val v: Object = FieldMetaData.createDefaultValue(PosoMetaData.this.schema.fieldMapper, c, params.apply(i), scala.None, scala.None);
        res.update(i, v)
      }));
      r.append(scala.Tuple2.apply[java.lang.reflect.Constructor[_$5], Array[Object]](c, res))
    };
    private def _noOptionalColumnDeclared: Nothing = org.squeryl.internals.Utils.throwError("class ".+(PosoMetaData.this.clasz.getName()).+(" has an Option[] member with no Column annotation with optionType declared."));
    def createSample(cb: net.sf.cglib.proxy.Callback): T = FieldReferenceLinker.executeAndRestoreLastAccessedFieldReference[T](PosoMetaData.this._builder.apply(cb));
    private[this] val _builder: net.sf.cglib.proxy.Callback => T = {
      val e: net.sf.cglib.proxy.Enhancer = new net.sf.cglib.proxy.Enhancer();
      e.setSuperclass(PosoMetaData.this.clasz);
      val pc: Array[Class[_]] = PosoMetaData.this.constructor._1.getParameterTypes();
      val args: Array[Object] = PosoMetaData.this.constructor._2;
      e.setUseFactory(true);
      ((callB: net.sf.cglib.proxy.Callback) => {
        val cb: Array[net.sf.cglib.proxy.Callback] = scala.Array.apply[net.sf.cglib.proxy.Callback](callB, net.sf.cglib.proxy.NoOp.INSTANCE)(ClassTag.apply[net.sf.cglib.proxy.Callback](classOf[net.sf.cglib.proxy.Callback]));
        e.setCallbacks(cb);
        e.setCallbackFilter(PosoMetaData.finalizeFilter);
        val fac: net.sf.cglib.proxy.Factory = e.create(pc, PosoMetaData.this.constructor._2).asInstanceOf[net.sf.cglib.proxy.Factory];
        fac.newInstance(pc, PosoMetaData.this.constructor._2, cb).asInstanceOf[T]
      })
    };
    <stable> <accessor> private def _builder: net.sf.cglib.proxy.Callback => T = PosoMetaData.this._builder;
    private def _isImplicitMode: Boolean = {
      val rowAnnotation: org.squeryl.annotations.Row = PosoMetaData.this.clasz.getAnnotation[org.squeryl.annotations.Row](classOf[org.squeryl.annotations.Row]);
      rowAnnotation.==(null).||(rowAnnotation.fieldToColumnCorrespondanceMode().==(IMPLICIT))
    };
    private def _groupOfMembersIsProperty(property: (Option[java.lang.reflect.Field], Option[java.lang.reflect.Method], Option[java.lang.reflect.Method], Set[java.lang.annotation.Annotation])): Boolean = {
      if (property._4.find(((an: java.lang.annotation.Annotation) => an.isInstanceOf[org.squeryl.annotations.Transient])).!=(scala.None))
        return false
      else
        ();
      val hasAField: Boolean = property._1.!=(scala.None);
      val isAStaticField: Boolean = property._1.map[Boolean](((f: java.lang.reflect.Field) => java.lang.reflect.Modifier.isStatic(f.getModifiers()))).getOrElse[Boolean](false);
      val hasGetter: Boolean = property._2.!=(scala.None).&&(classOf[java.lang.Void].isAssignableFrom(property._2.get.getReturnType()).unary_!).&&(property._2.get.getParameterTypes().length.==(0));
      val hasSetter: Boolean = property._3.!=(scala.None).&&(property._3.get.getParameterTypes().length.==(1));
      val memberTypes: scala.collection.mutable.ArrayBuffer[Class[_]] = new scala.collection.mutable.ArrayBuffer[Class[_]]();
      if (hasAField)
        memberTypes.append(property._1.get.getType())
      else
        ();
      if (hasGetter)
        memberTypes.append(property._2.get.getReturnType())
      else
        ();
      if (hasSetter)
        memberTypes.append(property._3.get.getParameterTypes().apply(0))
      else
        ();
      if (memberTypes.size.==(0))
        return false
      else
        ();
      val c: Class[_] = memberTypes.remove(0);
      memberTypes.foreach[Unit](((c0: Class[_]) => if (c0.isAssignableFrom(c).unary_!.&&(c.isAssignableFrom(c0).unary_!))
        return false
      else
        ()));
      scala.Tuple3.apply[Boolean, Boolean, Boolean](hasAField, hasGetter, hasSetter) match {
        case (_1: Boolean, _2: Boolean, _3: Boolean)(Boolean, Boolean, Boolean)(true, false, false) => isAStaticField.unary_!
        case (_1: Boolean, _2: Boolean, _3: Boolean)(Boolean, Boolean, Boolean)(false, true, true) => true
        case (_1: Boolean, _2: Boolean, _3: Boolean)(Boolean, Boolean, Boolean)(true, true, true) => true
        case (_1: Boolean, _2: Boolean, _3: Boolean)(Boolean, Boolean, Boolean)(true, true, false) => true
        case (a @ (_: Any)) => false
      }
    };
    private def _includeAnnotation(a: java.lang.annotation.Annotation): Boolean = a.isInstanceOf[org.squeryl.annotations.ColumnBase].||(a.isInstanceOf[org.squeryl.annotations.Transient]).||(a.isInstanceOf[org.squeryl.annotations.OptionType]);
    private def _addAnnotations(m: java.lang.reflect.Field, s: scala.collection.mutable.HashSet[java.lang.annotation.Annotation]): Unit = scala.this.Predef.refArrayOps[java.lang.annotation.Annotation](m.getAnnotations()).withFilter(((a: java.lang.annotation.Annotation) => PosoMetaData.this._includeAnnotation(a))).foreach[Boolean](((a: java.lang.annotation.Annotation) => s.add(a)));
    private def _addAnnotations(m: java.lang.reflect.Method, s: scala.collection.mutable.HashSet[java.lang.annotation.Annotation]): Unit = scala.this.Predef.refArrayOps[java.lang.annotation.Annotation](m.getAnnotations()).withFilter(((a: java.lang.annotation.Annotation) => PosoMetaData.this._includeAnnotation(a))).foreach[Boolean](((a: java.lang.annotation.Annotation) => s.add(a)));
    private def _includeFieldOrMethodType(c: Class[_]): Boolean = PosoMetaData.this.schema.fieldMapper.isSupported(c);
    private def _fillWithMembers(clasz: Class[_], members: scala.collection.mutable.ArrayBuffer[(java.lang.reflect.Member, scala.collection.mutable.HashSet[java.lang.annotation.Annotation])]): Unit = {
      scala.this.Predef.refArrayOps[java.lang.reflect.Method](clasz.getMethods()).withFilter(((m: java.lang.reflect.Method) => m.getDeclaringClass().!=(classOf[java.lang.Object]).&&(PosoMetaData.this._includeFieldOrMethodType(m.getReturnType())))).foreach[Unit](((m: java.lang.reflect.Method) => {
        m.setAccessible(true);
        val t: (java.lang.reflect.Method, scala.collection.mutable.HashSet[java.lang.annotation.Annotation]) = scala.Tuple2.apply[java.lang.reflect.Method, scala.collection.mutable.HashSet[java.lang.annotation.Annotation]](m, new scala.collection.mutable.HashSet[java.lang.annotation.Annotation]());
        PosoMetaData.this._addAnnotations(m, t._2);
        members.append(t)
      }));
      scala.this.Predef.refArrayOps[java.lang.reflect.Field](clasz.getDeclaredFields()).withFilter(((m: java.lang.reflect.Field) => m.getName().indexOf("$").==(-1).&&(PosoMetaData.this._includeFieldOrMethodType(m.getType())))).foreach[Unit](((m: java.lang.reflect.Field) => {
        m.setAccessible(true);
        val t: (java.lang.reflect.Field, scala.collection.mutable.HashSet[java.lang.annotation.Annotation]) = scala.Tuple2.apply[java.lang.reflect.Field, scala.collection.mutable.HashSet[java.lang.annotation.Annotation]](m, new scala.collection.mutable.HashSet[java.lang.annotation.Annotation]());
        PosoMetaData.this._addAnnotations(m, t._2);
        members.append(t)
      }));
      val c: Class[?0] forSome { type ?0 >: _$10; type _$10 } = clasz.getSuperclass();
      if (c.!=(null))
        PosoMetaData.this._fillWithMembers(c, members)
      else
        ()
    }
  };
  object PosoMetaData extends scala.AnyRef {
    def <init>(): org.squeryl.internals.PosoMetaData.type = {
      PosoMetaData.super.<init>();
      ()
    };
    private[this] val finalizeFilter: net.sf.cglib.proxy.CallbackFilter = {
      final class $anon extends Object with net.sf.cglib.proxy.CallbackFilter {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        def accept(method: java.lang.reflect.Method): Int = if (method.getName().==("finalize"))
          1
        else
          0
      };
      new $anon()
    };
    <stable> <accessor> def finalizeFilter: net.sf.cglib.proxy.CallbackFilter = PosoMetaData.this.finalizeFilter
  }
}