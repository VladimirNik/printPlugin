package org.squeryl.internals {
  import net.sf.cglib.proxy._;
  import collection.mutable.{HashSet, ArrayBuffer};
  import org.squeryl.dsl.ast._;
  import org.squeryl.dsl.CompositeKey;
  import org.squeryl.dsl.TypedExpression;
  import java.lang.reflect.{Field, Method};
  import org.squeryl.InternalFieldMapper;
  object FieldReferenceLinker extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    def pushExpressionOrCollectValue[T >: _root_.scala.Nothing <: _root_.scala.Any](e: _root_.scala.Function0[TypedExpression[T, _$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): T = if (isYieldInspectionMode)
      {
        val yi = _yieldInspectionTL.get;
        val expr = yi.callWithoutReentrance(e);
        yi.addSelectElement(new ValueSelectElement(expr, yi.resultSetMapper, expr.mapper, yi.queryExpressionNode));
        val r = expr.sample;
        r
      }
    else
      {
        val r = _yieldValues.get.remove(0).asInstanceOf[T];
        if (_yieldValues.get.size.$eq$eq(0))
          _yieldValues.remove()
        else
          ();
        r
      };
    def pushYieldValue(v: AnyRef) = {
      var a = _yieldValues.get;
      if (a.$eq$eq(null))
        {
          a = new ArrayBuffer[AnyRef]();
          _yieldValues.set(a)
        }
      else
        ();
      a.append(v)
    };
    def isYieldInspectionMode = {
      val yi = _yieldInspectionTL.get;
      if (yi.$bang$eq(null))
        yi.isOn
      else
        {
          _yieldInspectionTL.remove();
          false
        }
    };
    def inspectedQueryExpressionNode = _yieldInspectionTL.get.queryExpressionNode;
    private val _yieldValues = new ThreadLocal[ArrayBuffer[AnyRef]]();
    private val __lastAccessedFieldReference = new ThreadLocal[Option[SelectElement]]();
    private[squeryl] def _lastAccessedFieldReference: Option[SelectElement] = {
      val fr = __lastAccessedFieldReference.get;
      if (fr.$eq$eq(null))
        None
      else
        fr
    };
    private[squeryl] def _lastAccessedFieldReference_$eq(se: Option[SelectElement]) = if (se.$eq$eq(None))
      __lastAccessedFieldReference.remove()
    else
      __lastAccessedFieldReference.set(se);
    private val _compositeKeyMembers = new ThreadLocal[Option[ArrayBuffer[SelectElement]]]();
    def executeAndRestoreLastAccessedFieldReference[A >: _root_.scala.Nothing <: _root_.scala.Any](expressionWithSideEffectsASTConstructionThreadLocalState: _root_.scala.<byname>[A]): A = {
      val prev = FieldReferenceLinker._lastAccessedFieldReference;
      val a = expressionWithSideEffectsASTConstructionThreadLocalState;
      FieldReferenceLinker._lastAccessedFieldReference = prev;
      a
    };
    class YieldInspection extends scala.AnyRef {
      def <init>() = {
        super.<init>();
        ()
      };
      private val _utilizedFields = new ArrayBuffer[SelectElement]();
      var _on = false;
      var queryExpressionNode: QueryExpressionNode[_$2] forSome { 
        <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
      } = null;
      var _resultSetMapper: ResultSetMapper = null;
      def isOn = _on;
      def callWithoutReentrance[U >: _root_.scala.Nothing <: _root_.scala.Any](f: _root_.scala.Function0[U]) = {
        val prev = _on;
        _on = false;
        val res = f();
        _on = prev;
        res
      };
      def addSelectElement(e: SelectElement) = if (e.inhibited.unary_$bang)
        {
          _utilizedFields.append(e);
          e.prepareColumnMapper(_utilizedFields.size)
        }
      else
        ();
      def resultSetMapper = _resultSetMapper;
      private var _reentranceDepth = 0;
      def reentranceDepth = _reentranceDepth;
      def incrementReentranceDepth = _reentranceDepth.$plus$eq(1);
      def decrementReentranceDepth = _reentranceDepth.$minus$eq(1);
      def turnOn(q: QueryExpressionNode[_$3] forSome { 
        <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
      }, rsm: ResultSetMapper) = {
        _reentranceDepth = 0;
        queryExpressionNode = q;
        _on = true;
        _resultSetMapper = rsm
      };
      def outExpressions: List[SelectElement] = _utilizedFields.toList
    };
    private val _yieldInspectionTL = new ThreadLocal[YieldInspection]();
    def putLastAccessedSelectElement(e: SelectElement) = if (isYieldInspectionMode)
      _yieldInspectionTL.get.addSelectElement(new ExportedSelectElement(e))
    else
      _lastAccessedFieldReference = Some(e);
    def takeLastAccessedFieldReference: Option[SelectElement] = {
      val res = _lastAccessedFieldReference;
      _lastAccessedFieldReference = None;
      res
    };
    private def _takeLastAccessedUntypedFieldReference: SelectElementReference[_$4, _$5] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    } = FieldReferenceLinker.takeLastAccessedFieldReference match {
      case Some((n @ (_: SelectElement))) => new SelectElementReference(n, NoOpOutMapper)
      case None => org.squeryl.internals.Utils.throwError("Thread local does not have a last accessed field... this is a severe bug !")
    };
    def createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(e: Any, c: Any): LogicalBoolean = if (e.isInstanceOf[CompositeKey])
      e.asInstanceOf[CompositeKey].buildEquality(c.asInstanceOf[CompositeKey])
    else
      createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(c);
    def createEqualityExpressionWithLastAccessedFieldReferenceAndConstant(c: Any): LogicalBoolean = {
      val fr = _takeLastAccessedUntypedFieldReference;
      new BinaryOperatorNodeLogicalBoolean(fr, new InputOnlyConstantExpressionNode(c), "=")
    };
    def determineColumnsUtilizedInYeldInvocation(q: QueryExpressionNode[_$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }, rsm: ResultSetMapper, selectClosure: _root_.scala.Function0[AnyRef]) = {
      val yi = new YieldInspection();
      _yieldInspectionTL.set(yi);
      var result: scala.Tuple2[List[SelectElement], AnyRef] = null;
      try {
        yi.turnOn(q, rsm);
        val prev = _lastAccessedFieldReference;
        val res0 = try {
          selectClosure()
        } finally _lastAccessedFieldReference = prev;
        if (res0.$eq$eq(null))
          org.squeryl.internals.Utils.throwError("query ".$plus(q).$plus(" yielded null"))
        else
          ();
        val visitedSet = new java.util.IdentityHashMap[AnyRef, AnyRef]();
        _populateSelectColsRecurse(visitedSet, yi, q, res0);
        result = scala.Tuple2(yi.outExpressions, res0)
      } finally _yieldInspectionTL.remove();
      result
    };
    private object _declaredFieldCache extends scala.AnyRef {
      def <init>() = {
        super.<init>();
        ()
      };
      @new volatile() var _cache: Map[Class[_$7] forSome { 
        <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
      }, Array[Field]] = Map[Class[_$8] forSome { 
        <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
      }, Array[Field]]();
      def apply(cls: Class[_$9] forSome { 
        <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = _cache.get(cls).getOrElse({
        val declaredFields = cls.getDeclaredFields();
        _cache.$plus$eq(scala.Tuple2(cls, declaredFields));
        declaredFields
      })
    };
    private def _populateSelectColsRecurse(visited: java.util.IdentityHashMap[AnyRef, AnyRef], yi: YieldInspection, q: QueryExpressionElements, o: AnyRef): Unit = if (o.$bang$eq(null).$amp$amp(o.$bang$eq(None)))
      if (visited.containsKey(o).unary_$bang)
        {
          val clazz = o.getClass;
          val clazzName = clazz.getName;
          if (clazzName.startsWith("java.").unary_$bang.$amp$amp(clazzName.startsWith("net.sf.cglib.").unary_$bang).$amp$amp(clazzName.startsWith("scala.Enumeration").unary_$bang))
            {
              visited.put(o, o);
              _populateSelectCols(yi, q, o);
              _declaredFieldCache(clazz).foreach(((f) => {
                f.setAccessible(true);
                val ob = f.get(o);
                if (f.getName.startsWith("CGLIB$").unary_$bang.$amp$amp(f.getType.getName.startsWith("scala.Function").unary_$bang).$amp$amp(FieldMetaData.factory.hideFromYieldInspection(o, f).unary_$bang))
                  _populateSelectColsRecurse(visited, yi, q, ob)
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
    private def _populateSelectCols(yi: YieldInspection, q: QueryExpressionElements, sample: AnyRef): Unit = {
      var owner = _findQENThatOwns(sample, q);
      owner.foreach(((o) => o.getOrCreateAllSelectElements(q).foreach(((e) => yi.addSelectElement(e)))))
    };
    def findOwnerOfSample(s: AnyRef): Option[QueryableExpressionNode] = _findQENThatOwns(s, FieldReferenceLinker.inspectedQueryExpressionNode);
    private def _findQENThatOwns(sample: AnyRef, q: QueryExpressionElements): Option[QueryableExpressionNode] = q.filterDescendantsOfType[QueryableExpressionNode].find(((x$1) => x$1.owns(sample)));
    def createCallBack(v: ViewExpressionNode[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Callback = new PosoPropertyAccessInterceptor(v);
    private class PosoPropertyAccessInterceptor extends MethodInterceptor {
      <paramaccessor> val viewExpressionNode: ViewExpressionNode[_$11] forSome { 
        <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      def <init>(viewExpressionNode: ViewExpressionNode[_$11] forSome { 
        <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = {
        super.<init>();
        ()
      };
      def fmd4Method(m: Method) = viewExpressionNode.view.findFieldMetaDataForProperty(m.getName);
      def intercept(o: Object, m: Method, args: Array[Object], proxy: MethodProxy): Object = {
        val fmd = fmd4Method(m);
        val yi = if (isYieldInspectionMode)
          _yieldInspectionTL.get
        else
          null;
        val isComposite = classOf[CompositeKey].isAssignableFrom(m.getReturnType);
        try {
          if (fmd.$bang$eq(None).$amp$amp(yi.$bang$eq(null)))
            yi.incrementReentranceDepth
          else
            ();
          _intercept(o, m, args, proxy, fmd, yi, isComposite)
        } finally if (fmd.$bang$eq(None).$amp$amp(yi.$bang$eq(null)))
          yi.decrementReentranceDepth
        else
          ()
      };
      private def _intercept(o: Object, m: Method, args: Array[Object], proxy: MethodProxy, fmd: Option[FieldMetaData], yi: YieldInspection, isComposite: Boolean): Object = {
        if (isComposite)
          _compositeKeyMembers.set(Some(new ArrayBuffer[SelectElement]()))
        else
          ();
        var res = proxy.invokeSuper(o, args);
        if (isComposite)
          {
            val ck = res.asInstanceOf[CompositeKey];
            ck._members = Some(_compositeKeyMembers.get.get.map(((x$2) => new SelectElementReference[Any, Any](x$2, NoOpOutMapper))));
            ck._propertyName = Some(m.getName);
            _compositeKeyMembers.remove()
          }
        else
          ();
        if (m.getName.equals("toString").$amp$amp(m.getParameterTypes.length.$eq$eq(0)))
          res = "sample:".$plus(viewExpressionNode.view.name).$plus("[").$plus(Integer.toHexString(System.identityHashCode(o))).$plus("]")
        else
          ();
        if (fmd.$bang$eq(None))
          {
            if (yi.$bang$eq(null).$amp$amp(yi.reentranceDepth.$eq$eq(1)))
              yi.addSelectElement(viewExpressionNode.getOrCreateSelectElement(fmd.get, yi.queryExpressionNode))
            else
              ();
            if (_compositeKeyMembers.get.$eq$eq(null))
              {
                _compositeKeyMembers.remove();
                _lastAccessedFieldReference = Some(viewExpressionNode.getOrCreateSelectElement(fmd.get))
              }
            else
              _compositeKeyMembers.get.get.append(viewExpressionNode.getOrCreateSelectElement(fmd.get))
          }
        else
          ();
        res
      }
    }
  }
}