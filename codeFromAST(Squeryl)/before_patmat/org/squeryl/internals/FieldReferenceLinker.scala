package org.squeryl.internals {
  import net.sf.cglib.proxy._;
  import scala.collection.mutable.{HashSet, ArrayBuffer};
  import org.squeryl.dsl.ast._;
  import org.squeryl.dsl.CompositeKey;
  import org.squeryl.dsl.TypedExpression;
  import java.lang.reflect.{Field, Method};
  import org.squeryl.InternalFieldMapper;
  object FieldReferenceLinker extends scala.AnyRef {
    def <init>(): org.squeryl.internals.FieldReferenceLinker.type = {
      FieldReferenceLinker.super.<init>();
      ()
    };
    def pushExpressionOrCollectValue[T >: Nothing <: Any](e: () => org.squeryl.dsl.TypedExpression[T, _]): T = if (FieldReferenceLinker.this.isYieldInspectionMode)
      {
        val yi: org.squeryl.internals.FieldReferenceLinker.YieldInspection = FieldReferenceLinker.this._yieldInspectionTL.get();
        val expr: org.squeryl.dsl.TypedExpression[T, _] = yi.callWithoutReentrance[org.squeryl.dsl.TypedExpression[T, _]](e);
        yi.addSelectElement(new org.squeryl.dsl.ast.ValueSelectElement(expr, yi.resultSetMapper, expr.mapper, yi.queryExpressionNode));
        val r: T = expr.sample;
        r
      }
    else
      {
        val r: T = FieldReferenceLinker.this._yieldValues.get().remove(0).asInstanceOf[T];
        if (FieldReferenceLinker.this._yieldValues.get().size.==(0))
          FieldReferenceLinker.this._yieldValues.remove()
        else
          ();
        r
      };
    def pushYieldValue(v: AnyRef): Unit = {
      var a: scala.collection.mutable.ArrayBuffer[AnyRef] = FieldReferenceLinker.this._yieldValues.get();
      if (a.==(null))
        {
          a = new scala.collection.mutable.ArrayBuffer[AnyRef]();
          FieldReferenceLinker.this._yieldValues.set(a)
        }
      else
        ();
      a.append(v)
    };
    def isYieldInspectionMode: Boolean = {
      val yi: org.squeryl.internals.FieldReferenceLinker.YieldInspection = FieldReferenceLinker.this._yieldInspectionTL.get();
      if (yi.!=(null))
        yi.isOn
      else
        {
          FieldReferenceLinker.this._yieldInspectionTL.remove();
          false
        }
    };
    def inspectedQueryExpressionNode: org.squeryl.dsl.ast.QueryExpressionNode[_] = FieldReferenceLinker.this._yieldInspectionTL.get().queryExpressionNode;
    private[this] val _yieldValues: ThreadLocal[scala.collection.mutable.ArrayBuffer[AnyRef]] = new ThreadLocal[scala.collection.mutable.ArrayBuffer[AnyRef]]();
    <stable> <accessor> private def _yieldValues: ThreadLocal[scala.collection.mutable.ArrayBuffer[AnyRef]] = FieldReferenceLinker.this._yieldValues;
    private[this] val __lastAccessedFieldReference: ThreadLocal[Option[org.squeryl.dsl.ast.SelectElement]] = new ThreadLocal[Option[org.squeryl.dsl.ast.SelectElement]]();
    <stable> <accessor> private def __lastAccessedFieldReference: ThreadLocal[Option[org.squeryl.dsl.ast.SelectElement]] = FieldReferenceLinker.this.__lastAccessedFieldReference;
    private[squeryl] def _lastAccessedFieldReference: Option[org.squeryl.dsl.ast.SelectElement] = {
      val fr: Option[org.squeryl.dsl.ast.SelectElement] = FieldReferenceLinker.this.__lastAccessedFieldReference.get();
      if (fr.==(null))
        scala.None
      else
        fr
    };
    private[squeryl] def _lastAccessedFieldReference_=(se: Option[org.squeryl.dsl.ast.SelectElement]): Unit = if (se.==(scala.None))
      FieldReferenceLinker.this.__lastAccessedFieldReference.remove()
    else
      FieldReferenceLinker.this.__lastAccessedFieldReference.set(se);
    private[this] val _compositeKeyMembers: ThreadLocal[Option[scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.SelectElement]]] = new ThreadLocal[Option[scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.SelectElement]]]();
    <stable> <accessor> private def _compositeKeyMembers: ThreadLocal[Option[scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.SelectElement]]] = FieldReferenceLinker.this._compositeKeyMembers;
    def executeAndRestoreLastAccessedFieldReference[A >: Nothing <: Any](expressionWithSideEffectsASTConstructionThreadLocalState: => A): A = {
      val prev: Option[org.squeryl.dsl.ast.SelectElement] = FieldReferenceLinker._lastAccessedFieldReference;
      val a: A = expressionWithSideEffectsASTConstructionThreadLocalState;
      FieldReferenceLinker._lastAccessedFieldReference_=(prev);
      a
    };
    class YieldInspection extends scala.AnyRef {
      def <init>(): org.squeryl.internals.FieldReferenceLinker.YieldInspection = {
        YieldInspection.super.<init>();
        ()
      };
      private[this] val _utilizedFields: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.SelectElement] = new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.SelectElement]();
      <stable> <accessor> private def _utilizedFields: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.SelectElement] = YieldInspection.this._utilizedFields;
      private[this] var _on: Boolean = false;
      <accessor> def _on: Boolean = YieldInspection.this._on;
      <accessor> def _on_=(x$1: Boolean): Unit = YieldInspection.this._on = x$1;
      private[this] var queryExpressionNode: org.squeryl.dsl.ast.QueryExpressionNode[_] = null;
      <accessor> def queryExpressionNode: org.squeryl.dsl.ast.QueryExpressionNode[_] = YieldInspection.this.queryExpressionNode;
      <accessor> def queryExpressionNode_=(x$1: org.squeryl.dsl.ast.QueryExpressionNode[_]): Unit = YieldInspection.this.queryExpressionNode = x$1;
      private[this] var _resultSetMapper: org.squeryl.internals.ResultSetMapper = null;
      <accessor> def _resultSetMapper: org.squeryl.internals.ResultSetMapper = YieldInspection.this._resultSetMapper;
      <accessor> def _resultSetMapper_=(x$1: org.squeryl.internals.ResultSetMapper): Unit = YieldInspection.this._resultSetMapper = x$1;
      def isOn: Boolean = YieldInspection.this._on;
      def callWithoutReentrance[U >: Nothing <: Any](f: () => U): U = {
        val prev: Boolean = YieldInspection.this._on;
        YieldInspection.this._on_=(false);
        val res: U = f.apply();
        YieldInspection.this._on_=(prev);
        res
      };
      def addSelectElement(e: org.squeryl.dsl.ast.SelectElement): Unit = if (e.inhibited.unary_!)
        {
          YieldInspection.this._utilizedFields.append(e);
          e.prepareColumnMapper(YieldInspection.this._utilizedFields.size)
        }
      else
        ();
      def resultSetMapper: org.squeryl.internals.ResultSetMapper = YieldInspection.this._resultSetMapper;
      private[this] var _reentranceDepth: Int = 0;
      <accessor> private def _reentranceDepth: Int = YieldInspection.this._reentranceDepth;
      <accessor> private def _reentranceDepth_=(x$1: Int): Unit = YieldInspection.this._reentranceDepth = x$1;
      def reentranceDepth: Int = YieldInspection.this._reentranceDepth;
      def incrementReentranceDepth: Unit = YieldInspection.this._reentranceDepth_=(YieldInspection.this._reentranceDepth.+(1));
      def decrementReentranceDepth: Unit = YieldInspection.this._reentranceDepth_=(YieldInspection.this._reentranceDepth.-(1));
      def turnOn(q: org.squeryl.dsl.ast.QueryExpressionNode[_], rsm: org.squeryl.internals.ResultSetMapper): Unit = {
        YieldInspection.this._reentranceDepth_=(0);
        YieldInspection.this.queryExpressionNode_=(q);
        YieldInspection.this._on_=(true);
        YieldInspection.this._resultSetMapper_=(rsm)
      };
      def outExpressions: List[org.squeryl.dsl.ast.SelectElement] = YieldInspection.this._utilizedFields.toList
    };
    private[this] val _yieldInspectionTL: ThreadLocal[org.squeryl.internals.FieldReferenceLinker.YieldInspection] = new ThreadLocal[org.squeryl.internals.FieldReferenceLinker.YieldInspection]();
    <stable> <accessor> private def _yieldInspectionTL: ThreadLocal[org.squeryl.internals.FieldReferenceLinker.YieldInspection] = FieldReferenceLinker.this._yieldInspectionTL;
    def putLastAccessedSelectElement(e: org.squeryl.dsl.ast.SelectElement): Unit = if (FieldReferenceLinker.this.isYieldInspectionMode)
      FieldReferenceLinker.this._yieldInspectionTL.get().addSelectElement(new org.squeryl.dsl.ast.ExportedSelectElement(e))
    else
      FieldReferenceLinker.this._lastAccessedFieldReference_=(scala.Some.apply[org.squeryl.dsl.ast.SelectElement](e));
    def takeLastAccessedFieldReference: Option[org.squeryl.dsl.ast.SelectElement] = {
      val res: Option[org.squeryl.dsl.ast.SelectElement] = FieldReferenceLinker.this._lastAccessedFieldReference;
      FieldReferenceLinker.this._lastAccessedFieldReference_=(scala.None);
      res
    };
    private def _takeLastAccessedUntypedFieldReference: org.squeryl.dsl.ast.SelectElementReference[_, _] = FieldReferenceLinker.takeLastAccessedFieldReference match {
      case (x: org.squeryl.dsl.ast.SelectElement)Some[org.squeryl.dsl.ast.SelectElement]((n @ (_: org.squeryl.dsl.ast.SelectElement))) => new org.squeryl.dsl.ast.SelectElementReference[Any,Nothing](n, NoOpOutMapper)
      case scala.None => org.squeryl.internals.Utils.throwError("Thread local does not have a last accessed field... this is a severe bug !")
    };
    def createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(e: Any, c: Any): org.squeryl.dsl.ast.LogicalBoolean = if (e.isInstanceOf[org.squeryl.dsl.CompositeKey])
      e.asInstanceOf[org.squeryl.dsl.CompositeKey].buildEquality(c.asInstanceOf[org.squeryl.dsl.CompositeKey])
    else
      FieldReferenceLinker.this.createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(c);
    def createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(c: Any): org.squeryl.dsl.ast.LogicalBoolean = {
      val fr: org.squeryl.dsl.ast.SelectElementReference[_, _] = FieldReferenceLinker.this._takeLastAccessedUntypedFieldReference;
      new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(fr, new org.squeryl.dsl.ast.InputOnlyConstantExpressionNode(c), "=", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4)
    };
    def determineColumnsUtilizedInYeldInvocation(q: org.squeryl.dsl.ast.QueryExpressionNode[_], rsm: org.squeryl.internals.ResultSetMapper, selectClosure: () => AnyRef): (List[org.squeryl.dsl.ast.SelectElement], AnyRef) = {
      val yi: org.squeryl.internals.FieldReferenceLinker.YieldInspection = new FieldReferenceLinker.this.YieldInspection();
      FieldReferenceLinker.this._yieldInspectionTL.set(yi);
      var result: (List[org.squeryl.dsl.ast.SelectElement], AnyRef) = null;
      try {
        yi.turnOn(q, rsm);
        val prev: Option[org.squeryl.dsl.ast.SelectElement] = FieldReferenceLinker.this._lastAccessedFieldReference;
        val res0: AnyRef = try {
          selectClosure.apply()
        } finally FieldReferenceLinker.this._lastAccessedFieldReference_=(prev);
        if (res0.==(null))
          org.squeryl.internals.Utils.throwError("query ".+(q).+(" yielded null"))
        else
          ();
        val visitedSet: java.util.IdentityHashMap[AnyRef,AnyRef] = new java.util.IdentityHashMap[AnyRef,AnyRef]();
        FieldReferenceLinker.this._populateSelectColsRecurse(visitedSet, yi, q, res0);
        result = scala.Tuple2.apply[List[org.squeryl.dsl.ast.SelectElement], AnyRef](yi.outExpressions, res0)
      } finally FieldReferenceLinker.this._yieldInspectionTL.remove();
      result
    };
    private object _declaredFieldCache extends scala.AnyRef {
      def <init>(): org.squeryl.internals.FieldReferenceLinker._declaredFieldCache.type = {
        _declaredFieldCache.super.<init>();
        ()
      };
      @volatile private[this] var _cache: Map[Class[_],Array[java.lang.reflect.Field]] = scala.this.Predef.Map.apply[Class[_], Array[java.lang.reflect.Field]]();
      <accessor> def _cache: Map[Class[_],Array[java.lang.reflect.Field]] = _declaredFieldCache.this._cache;
      <accessor> def _cache_=(x$1: Map[Class[_],Array[java.lang.reflect.Field]]): Unit = _declaredFieldCache.this._cache = x$1;
      def apply(cls: Class[_]): Array[java.lang.reflect.Field] = _declaredFieldCache.this._cache.get(cls).getOrElse[Array[java.lang.reflect.Field]]({
        val declaredFields: Array[java.lang.reflect.Field] = cls.getDeclaredFields();
        _declaredFieldCache.this._cache_=(_declaredFieldCache.this._cache.+[Array[java.lang.reflect.Field]](scala.Tuple2.apply[Class[_$9], Array[java.lang.reflect.Field]](cls, declaredFields)));
        declaredFields
      })
    };
    private def _populateSelectColsRecurse(visited: java.util.IdentityHashMap[AnyRef,AnyRef], yi: org.squeryl.internals.FieldReferenceLinker.YieldInspection, q: org.squeryl.dsl.ast.QueryExpressionElements, o: AnyRef): Unit = if (o.!=(null).&&(o.!=(scala.None)))
      if (visited.containsKey(o).unary_!)
        {
          val clazz: Class[_ <: AnyRef] = o.getClass();
          val clazzName: String = clazz.getName();
          if (clazzName.startsWith("java.").unary_!.&&(clazzName.startsWith("net.sf.cglib.").unary_!).&&(clazzName.startsWith("scala.Enumeration").unary_!))
            {
              visited.put(o, o);
              FieldReferenceLinker.this._populateSelectCols(yi, q, o);
              scala.this.Predef.refArrayOps[java.lang.reflect.Field](FieldReferenceLinker.this._declaredFieldCache.apply(clazz)).foreach[Unit](((f: java.lang.reflect.Field) => {
                f.setAccessible(true);
                val ob: Object = f.get(o);
                if (f.getName().startsWith("CGLIB$").unary_!.&&(f.getType().getName().startsWith("scala.Function").unary_!).&&(FieldMetaData.factory.hideFromYieldInspection(o, f).unary_!))
                  FieldReferenceLinker.this._populateSelectColsRecurse(visited, yi, q, ob)
                else
                  ()
              }))
            }
          else
            ()
        }
      else
        ()
    else
      ();
    private def _populateSelectCols(yi: org.squeryl.internals.FieldReferenceLinker.YieldInspection, q: org.squeryl.dsl.ast.QueryExpressionElements, sample: AnyRef): Unit = {
      var owner: Option[org.squeryl.dsl.ast.QueryableExpressionNode] = FieldReferenceLinker.this._findQENThatOwns(sample, q);
      owner.foreach[Unit](((o: org.squeryl.dsl.ast.QueryableExpressionNode) => o.getOrCreateAllSelectElements(q).foreach[Unit](((e: org.squeryl.dsl.ast.SelectElement) => yi.addSelectElement(e)))))
    };
    def findOwnerOfSample(s: AnyRef): Option[org.squeryl.dsl.ast.QueryableExpressionNode] = FieldReferenceLinker.this._findQENThatOwns(s, FieldReferenceLinker.inspectedQueryExpressionNode);
    private def _findQENThatOwns(sample: AnyRef, q: org.squeryl.dsl.ast.QueryExpressionElements): Option[org.squeryl.dsl.ast.QueryableExpressionNode] = q.filterDescendantsOfType[org.squeryl.dsl.ast.QueryableExpressionNode](reflect.this.ManifestFactory.classType[org.squeryl.dsl.ast.QueryableExpressionNode](classOf[org.squeryl.dsl.ast.QueryableExpressionNode])).find(((x$1: org.squeryl.dsl.ast.QueryableExpressionNode) => x$1.owns(sample)));
    def createCallBack(v: org.squeryl.dsl.ast.ViewExpressionNode[_]): net.sf.cglib.proxy.Callback = new FieldReferenceLinker.this.PosoPropertyAccessInterceptor(v);
    private class PosoPropertyAccessInterceptor extends Object with net.sf.cglib.proxy.MethodInterceptor {
      <paramaccessor> private[this] val viewExpressionNode: org.squeryl.dsl.ast.ViewExpressionNode[_] = _;
      <stable> <accessor> <paramaccessor> def viewExpressionNode: org.squeryl.dsl.ast.ViewExpressionNode[_] = PosoPropertyAccessInterceptor.this.viewExpressionNode;
      def <init>(viewExpressionNode: org.squeryl.dsl.ast.ViewExpressionNode[_]): org.squeryl.internals.FieldReferenceLinker.PosoPropertyAccessInterceptor = {
        PosoPropertyAccessInterceptor.super.<init>();
        ()
      };
      def fmd4Method(m: java.lang.reflect.Method): Option[org.squeryl.internals.FieldMetaData] = PosoPropertyAccessInterceptor.this.viewExpressionNode.view.findFieldMetaDataForProperty(m.getName());
      def intercept(o: Object, m: java.lang.reflect.Method, args: Array[Object], proxy: net.sf.cglib.proxy.MethodProxy): Object = {
        val fmd: Option[org.squeryl.internals.FieldMetaData] = PosoPropertyAccessInterceptor.this.fmd4Method(m);
        val yi: org.squeryl.internals.FieldReferenceLinker.YieldInspection = if (FieldReferenceLinker.this.isYieldInspectionMode)
          FieldReferenceLinker.this._yieldInspectionTL.get()
        else
          null;
        val isComposite: Boolean = classOf[org.squeryl.dsl.CompositeKey].isAssignableFrom(m.getReturnType());
        try {
          if (fmd.!=(scala.None).&&(yi.!=(null)))
            yi.incrementReentranceDepth
          else
            ();
          PosoPropertyAccessInterceptor.this._intercept(o, m, args, proxy, fmd, yi, isComposite)
        } finally if (fmd.!=(scala.None).&&(yi.!=(null)))
          yi.decrementReentranceDepth
        else
          ()
      };
      private def _intercept(o: Object, m: java.lang.reflect.Method, args: Array[Object], proxy: net.sf.cglib.proxy.MethodProxy, fmd: Option[org.squeryl.internals.FieldMetaData], yi: org.squeryl.internals.FieldReferenceLinker.YieldInspection, isComposite: Boolean): Object = {
        if (isComposite)
          FieldReferenceLinker.this._compositeKeyMembers.set(scala.Some.apply[scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.SelectElement]](new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.SelectElement]()))
        else
          ();
        var res: Object = proxy.invokeSuper(o, args);
        if (isComposite)
          {
            val ck: org.squeryl.dsl.CompositeKey = res.asInstanceOf[org.squeryl.dsl.CompositeKey];
            ck._members_=(scala.Some.apply[Seq[org.squeryl.dsl.ast.SelectElementReference[_, _]]](FieldReferenceLinker.this._compositeKeyMembers.get().get.map[org.squeryl.dsl.ast.SelectElementReference[Any,Any], Seq[org.squeryl.dsl.ast.SelectElementReference[_, _]]](((x$2: org.squeryl.dsl.ast.SelectElement) => new org.squeryl.dsl.ast.SelectElementReference[Any,Any](x$2, NoOpOutMapper)))(mutable.this.ArrayBuffer.canBuildFrom[org.squeryl.dsl.ast.SelectElementReference[Any,Any]])));
            ck._propertyName_=(scala.Some.apply[String](m.getName()));
            FieldReferenceLinker.this._compositeKeyMembers.remove()
          }
        else
          ();
        if (m.getName().equals("toString").&&(m.getParameterTypes().length.==(0)))
          res = "sample:".+(PosoPropertyAccessInterceptor.this.viewExpressionNode.view.name).+("[").+(java.this.lang.Integer.toHexString(java.this.lang.System.identityHashCode(o))).+("]")
        else
          ();
        if (fmd.!=(scala.None))
          {
            if (yi.!=(null).&&(yi.reentranceDepth.==(1)))
              yi.addSelectElement(PosoPropertyAccessInterceptor.this.viewExpressionNode.getOrCreateSelectElement(fmd.get, yi.queryExpressionNode))
            else
              ();
            if (FieldReferenceLinker.this._compositeKeyMembers.get().==(null))
              {
                FieldReferenceLinker.this._compositeKeyMembers.remove();
                FieldReferenceLinker.this._lastAccessedFieldReference_=(scala.Some.apply[org.squeryl.dsl.ast.SelectElement](PosoPropertyAccessInterceptor.this.viewExpressionNode.getOrCreateSelectElement(fmd.get)))
              }
            else
              FieldReferenceLinker.this._compositeKeyMembers.get().get.append(PosoPropertyAccessInterceptor.this.viewExpressionNode.getOrCreateSelectElement(fmd.get))
          }
        else
          ();
        res
      }
    }
  }
}