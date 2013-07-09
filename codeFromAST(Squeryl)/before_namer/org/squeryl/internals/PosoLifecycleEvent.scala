package org.squeryl.internals {
  import org.squeryl.{Table, View, Schema};
  object PosoLifecycleEvent extends Enumeration {
    def <init>() = {
      super.<init>();
      ()
    };
    type PosoLifeCycleEvent = Value;
    val BeforeInsert = Value;
    val AfterInsert = Value;
    val BeforeDelete = Value;
    val AfterDelete = Value;
    val BeforeUpdate = Value;
    val AfterUpdate = Value;
    val AfterSelect = Value;
    val Create = Value
  };
  class LifecycleEventInvoker extends PosoLifecycleEventListener {
    <paramaccessor> private[this] val i: Iterable[LifecycleEvent] = _;
    <paramaccessor> private[this] val owner: View[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(i: Iterable[LifecycleEvent], owner: View[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>();
      ()
    };
    import PosoLifecycleEvent._;
    private val _beforeInsert = i.filter(((x$1) => x$1.e.$eq$eq(BeforeInsert))).map(((x$2) => x$2.callback));
    private val _afterInsert = i.filter(((x$3) => x$3.e.$eq$eq(AfterInsert))).map(((x$4) => x$4.callback));
    private val _beforeDelete = i.filter(((x$5) => x$5.e.$eq$eq(BeforeDelete))).map(((x$6) => x$6.callback));
    private val _afterDelete = i.filter(((x$7) => x$7.e.$eq$eq(AfterDelete))).map(((x$8) => x$8.callback));
    private val _beforeUpdate = i.filter(((x$9) => x$9.e.$eq$eq(BeforeUpdate))).map(((x$10) => x$10.callback));
    private val _afterUpdate = i.filter(((x$11) => x$11.e.$eq$eq(AfterUpdate))).map(((x$12) => x$12.callback));
    private val _afterSelect = i.filter(((x$13) => x$13.e.$eq$eq(AfterSelect))).map(((x$14) => x$14.callback));
    private val _factory: _root_.scala.Function1[AnyRef, AnyRef] = {
      val f = i.filter(((x$15) => x$15.e.$eq$eq(Create))).map(((x$16) => x$16.callback));
      if (f.size.$greater(1))
        org.squeryl.internals.Utils.throwError(owner.name.$plus(" has more than one factory defined."))
      else
        ();
      f.headOption.getOrElse(((r: AnyRef) => null))
    };
    override def hasBeforeDelete = _beforeDelete.$bang$eq(Nil);
    override def hasAfterDelete = _afterDelete.$bang$eq(Nil);
    private def applyFuncs(fs: Traversable[_root_.scala.Function1[AnyRef, AnyRef]], a: AnyRef) = {
      var res = a;
      fs.foreach(((f) => res = f(res)));
      res
    };
    def create: AnyRef = _factory(null);
    def beforeInsert(a: AnyRef) = applyFuncs(_beforeInsert, a);
    def afterInsert(a: AnyRef) = applyFuncs(_afterInsert, a);
    def beforeDelete(a: AnyRef) = applyFuncs(_beforeDelete, a);
    def afterDelete(a: AnyRef) = applyFuncs(_afterDelete, a);
    def beforeUpdate(a: AnyRef) = applyFuncs(_beforeUpdate, a);
    def afterUpdate(a: AnyRef) = applyFuncs(_afterUpdate, a);
    def afterSelect(a: AnyRef) = applyFuncs(_afterSelect, a)
  };
  abstract trait BaseLifecycleEventPercursor extends scala.AnyRef {
    def $init$() = {
      ()
    };
    protected def createLCEMap[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Traversable[View[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e: PosoLifecycleEvent.Value, f: _root_.scala.Function1[A, A]) = new LifecycleEvent(t, e, f.asInstanceOf[_root_.scala.Function1[AnyRef, AnyRef]]);
    protected def createLCECall[A >: _root_.scala.Nothing <: _root_.scala.Any](t: Traversable[View[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e: PosoLifecycleEvent.Value, f: _root_.scala.Function1[A, Unit]) = createLCEMap[A](t, e, ((ar: A) => {
      f(ar.asInstanceOf[A]);
      ar
    }))
  };
  class PosoFactoryPercursorTable[A >: _root_.scala.Nothing <: _root_.scala.Any] extends BaseLifecycleEventPercursor {
    <paramaccessor> private[this] val target: View[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(target: View[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>();
      ()
    };
    def is(f: _root_.scala.<byname>[A]) = new LifecycleEvent(Seq(target), PosoLifecycleEvent.Create, ((r: AnyRef) => {
      val a = f;
      a.asInstanceOf[AnyRef]
    }))
  };
  class LifecycleEventPercursorTable[A >: _root_.scala.Nothing <: _root_.scala.Any] extends BaseLifecycleEventPercursor {
    <paramaccessor> private[this] val target: View[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> private[this] val e: PosoLifecycleEvent.Value = _;
    def <init>(target: View[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }, e: PosoLifecycleEvent.Value) = {
      super.<init>();
      ()
    };
    def call(f: _root_.scala.Function1[A, Unit]) = createLCECall(Seq(target), e, f);
    def map(a: _root_.scala.Function1[A, A]) = createLCEMap(Seq(target), e, a)
  };
  class LifecycleEventPercursorClass[A >: _root_.scala.Nothing <: _root_.scala.Any] extends BaseLifecycleEventPercursor {
    <paramaccessor> private[this] val target: Class[_$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> private[this] val schema: Schema = _;
    <paramaccessor> private[this] val e: PosoLifecycleEvent.Value = _;
    def <init>(target: Class[_$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }, schema: Schema, e: PosoLifecycleEvent.Value) = {
      super.<init>();
      ()
    };
    def call(f: _root_.scala.Function1[A, Unit]) = createLCECall(schema.findAllTablesFor(target), e, f);
    def map(a: _root_.scala.Function1[A, A]) = createLCEMap(schema.findAllTablesFor(target), e, a)
  };
  class LifecycleEvent extends scala.AnyRef {
    <paramaccessor> val target: Traversable[View[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    <paramaccessor> val e: PosoLifecycleEvent.Value = _;
    <paramaccessor> val callback: _root_.scala.Function1[AnyRef, AnyRef] = _;
    def <init>(target: Traversable[View[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e: PosoLifecycleEvent.Value, callback: _root_.scala.Function1[AnyRef, AnyRef]) = {
      super.<init>();
      ()
    }
  };
  abstract trait PosoLifecycleEventListener extends scala.AnyRef {
    def $init$() = {
      ()
    };
    def hasBeforeDelete = false;
    def hasAfterDelete = false;
    def create: AnyRef;
    def beforeInsert(a: AnyRef): AnyRef;
    def afterInsert(a: AnyRef): AnyRef;
    def beforeDelete(a: AnyRef): AnyRef;
    def afterDelete(a: AnyRef): AnyRef;
    def beforeUpdate(a: AnyRef): AnyRef;
    def afterUpdate(a: AnyRef): AnyRef;
    def afterSelect(a: AnyRef): AnyRef
  };
  object NoOpPosoLifecycleEventListener extends PosoLifecycleEventListener {
    def <init>() = {
      super.<init>();
      ()
    };
    def create: AnyRef = null;
    def beforeInsert(a: AnyRef) = a;
    def afterInsert(a: AnyRef) = a;
    def beforeDelete(a: AnyRef) = a;
    def afterDelete(a: AnyRef) = a;
    def beforeUpdate(a: AnyRef) = a;
    def afterUpdate(a: AnyRef) = a;
    def afterSelect(a: AnyRef) = a
  }
}