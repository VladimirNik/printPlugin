package org.squeryl.internals {
  import org.squeryl.{Table, View, Schema};
  object PosoLifecycleEvent extends scala.Enumeration {
    def <init>(): org.squeryl.internals.PosoLifecycleEvent.type = {
      PosoLifecycleEvent.super.<init>();
      ()
    };
    type PosoLifeCycleEvent = org.squeryl.internals.PosoLifecycleEvent.Value;
    private[this] val BeforeInsert: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Value;
    <stable> <accessor> def BeforeInsert: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.BeforeInsert;
    private[this] val AfterInsert: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Value;
    <stable> <accessor> def AfterInsert: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.AfterInsert;
    private[this] val BeforeDelete: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Value;
    <stable> <accessor> def BeforeDelete: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.BeforeDelete;
    private[this] val AfterDelete: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Value;
    <stable> <accessor> def AfterDelete: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.AfterDelete;
    private[this] val BeforeUpdate: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Value;
    <stable> <accessor> def BeforeUpdate: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.BeforeUpdate;
    private[this] val AfterUpdate: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Value;
    <stable> <accessor> def AfterUpdate: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.AfterUpdate;
    private[this] val AfterSelect: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Value;
    <stable> <accessor> def AfterSelect: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.AfterSelect;
    private[this] val Create: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Value;
    <stable> <accessor> def Create: org.squeryl.internals.PosoLifecycleEvent.Value = PosoLifecycleEvent.this.Create
  };
  class LifecycleEventInvoker extends Object with org.squeryl.internals.PosoLifecycleEventListener {
    <paramaccessor> private[this] val i: Iterable[org.squeryl.internals.LifecycleEvent] = _;
    <paramaccessor> private[this] val owner: org.squeryl.View[_] = _;
    def <init>(i: Iterable[org.squeryl.internals.LifecycleEvent], owner: org.squeryl.View[_]): org.squeryl.internals.LifecycleEventInvoker = {
      LifecycleEventInvoker.super.<init>();
      ()
    };
    import PosoLifecycleEvent._;
    private[this] val _beforeInsert: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this.i.filter(((x$1: org.squeryl.internals.LifecycleEvent) => x$1.e.==(PosoLifecycleEvent.BeforeInsert))).map[AnyRef => AnyRef, Iterable[AnyRef => AnyRef]](((x$2: org.squeryl.internals.LifecycleEvent) => x$2.callback))(collection.this.Iterable.canBuildFrom[AnyRef => AnyRef]);
    <stable> <accessor> private def _beforeInsert: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this._beforeInsert;
    private[this] val _afterInsert: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this.i.filter(((x$3: org.squeryl.internals.LifecycleEvent) => x$3.e.==(PosoLifecycleEvent.AfterInsert))).map[AnyRef => AnyRef, Iterable[AnyRef => AnyRef]](((x$4: org.squeryl.internals.LifecycleEvent) => x$4.callback))(collection.this.Iterable.canBuildFrom[AnyRef => AnyRef]);
    <stable> <accessor> private def _afterInsert: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this._afterInsert;
    private[this] val _beforeDelete: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this.i.filter(((x$5: org.squeryl.internals.LifecycleEvent) => x$5.e.==(PosoLifecycleEvent.BeforeDelete))).map[AnyRef => AnyRef, Iterable[AnyRef => AnyRef]](((x$6: org.squeryl.internals.LifecycleEvent) => x$6.callback))(collection.this.Iterable.canBuildFrom[AnyRef => AnyRef]);
    <stable> <accessor> private def _beforeDelete: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this._beforeDelete;
    private[this] val _afterDelete: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this.i.filter(((x$7: org.squeryl.internals.LifecycleEvent) => x$7.e.==(PosoLifecycleEvent.AfterDelete))).map[AnyRef => AnyRef, Iterable[AnyRef => AnyRef]](((x$8: org.squeryl.internals.LifecycleEvent) => x$8.callback))(collection.this.Iterable.canBuildFrom[AnyRef => AnyRef]);
    <stable> <accessor> private def _afterDelete: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this._afterDelete;
    private[this] val _beforeUpdate: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this.i.filter(((x$9: org.squeryl.internals.LifecycleEvent) => x$9.e.==(PosoLifecycleEvent.BeforeUpdate))).map[AnyRef => AnyRef, Iterable[AnyRef => AnyRef]](((x$10: org.squeryl.internals.LifecycleEvent) => x$10.callback))(collection.this.Iterable.canBuildFrom[AnyRef => AnyRef]);
    <stable> <accessor> private def _beforeUpdate: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this._beforeUpdate;
    private[this] val _afterUpdate: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this.i.filter(((x$11: org.squeryl.internals.LifecycleEvent) => x$11.e.==(PosoLifecycleEvent.AfterUpdate))).map[AnyRef => AnyRef, Iterable[AnyRef => AnyRef]](((x$12: org.squeryl.internals.LifecycleEvent) => x$12.callback))(collection.this.Iterable.canBuildFrom[AnyRef => AnyRef]);
    <stable> <accessor> private def _afterUpdate: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this._afterUpdate;
    private[this] val _afterSelect: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this.i.filter(((x$13: org.squeryl.internals.LifecycleEvent) => x$13.e.==(PosoLifecycleEvent.AfterSelect))).map[AnyRef => AnyRef, Iterable[AnyRef => AnyRef]](((x$14: org.squeryl.internals.LifecycleEvent) => x$14.callback))(collection.this.Iterable.canBuildFrom[AnyRef => AnyRef]);
    <stable> <accessor> private def _afterSelect: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this._afterSelect;
    private[this] val _factory: AnyRef => AnyRef = {
      val f: Iterable[AnyRef => AnyRef] = LifecycleEventInvoker.this.i.filter(((x$15: org.squeryl.internals.LifecycleEvent) => x$15.e.==(PosoLifecycleEvent.Create))).map[AnyRef => AnyRef, Iterable[AnyRef => AnyRef]](((x$16: org.squeryl.internals.LifecycleEvent) => x$16.callback))(collection.this.Iterable.canBuildFrom[AnyRef => AnyRef]);
      if (f.size.>(1))
        org.squeryl.internals.Utils.throwError(LifecycleEventInvoker.this.owner.name.+(" has more than one factory defined."))
      else
        ();
      f.headOption.getOrElse[AnyRef => AnyRef](((r: AnyRef) => null))
    };
    <stable> <accessor> private def _factory: AnyRef => AnyRef = LifecycleEventInvoker.this._factory;
    override def hasBeforeDelete: Boolean = LifecycleEventInvoker.this._beforeDelete.!=(immutable.this.Nil);
    override def hasAfterDelete: Boolean = LifecycleEventInvoker.this._afterDelete.!=(immutable.this.Nil);
    private def applyFuncs(fs: Traversable[AnyRef => AnyRef], a: AnyRef): AnyRef = {
      var res: AnyRef = a;
      fs.foreach[Unit](((f: AnyRef => AnyRef) => res = f.apply(res)));
      res
    };
    def create: AnyRef = LifecycleEventInvoker.this._factory.apply(null);
    def beforeInsert(a: AnyRef): AnyRef = LifecycleEventInvoker.this.applyFuncs(LifecycleEventInvoker.this._beforeInsert, a);
    def afterInsert(a: AnyRef): AnyRef = LifecycleEventInvoker.this.applyFuncs(LifecycleEventInvoker.this._afterInsert, a);
    def beforeDelete(a: AnyRef): AnyRef = LifecycleEventInvoker.this.applyFuncs(LifecycleEventInvoker.this._beforeDelete, a);
    def afterDelete(a: AnyRef): AnyRef = LifecycleEventInvoker.this.applyFuncs(LifecycleEventInvoker.this._afterDelete, a);
    def beforeUpdate(a: AnyRef): AnyRef = LifecycleEventInvoker.this.applyFuncs(LifecycleEventInvoker.this._beforeUpdate, a);
    def afterUpdate(a: AnyRef): AnyRef = LifecycleEventInvoker.this.applyFuncs(LifecycleEventInvoker.this._afterUpdate, a);
    def afterSelect(a: AnyRef): AnyRef = LifecycleEventInvoker.this.applyFuncs(LifecycleEventInvoker.this._afterSelect, a)
  };
  abstract trait BaseLifecycleEventPercursor extends scala.AnyRef {
    def /*BaseLifecycleEventPercursor*/$init$(): Unit = {
      ()
    };
    protected def createLCEMap[A >: Nothing <: Any](t: Traversable[org.squeryl.View[_]], e: org.squeryl.internals.PosoLifecycleEvent.Value, f: A => A): org.squeryl.internals.LifecycleEvent = new LifecycleEvent(t, e, f.asInstanceOf[AnyRef => AnyRef]);
    protected def createLCECall[A >: Nothing <: Any](t: Traversable[org.squeryl.View[_]], e: org.squeryl.internals.PosoLifecycleEvent.Value, f: A => Unit): org.squeryl.internals.LifecycleEvent = BaseLifecycleEventPercursor.this.createLCEMap[A](t, e, ((ar: A) => {
      f.apply(ar.asInstanceOf[A]);
      ar
    }))
  };
  class PosoFactoryPercursorTable[A >: Nothing <: Any] extends Object with org.squeryl.internals.BaseLifecycleEventPercursor {
    <paramaccessor> private[this] val target: org.squeryl.View[_] = _;
    def <init>(target: org.squeryl.View[_]): org.squeryl.internals.PosoFactoryPercursorTable[A] = {
      PosoFactoryPercursorTable.super.<init>();
      ()
    };
    def is(f: => A): org.squeryl.internals.LifecycleEvent = new LifecycleEvent(collection.this.Seq.apply[org.squeryl.View[_$4]](PosoFactoryPercursorTable.this.target), PosoLifecycleEvent.Create, ((r: AnyRef) => {
      val a: A = f;
      a.asInstanceOf[AnyRef]
    }))
  };
  class LifecycleEventPercursorTable[A >: Nothing <: Any] extends Object with org.squeryl.internals.BaseLifecycleEventPercursor {
    <paramaccessor> private[this] val target: org.squeryl.View[_] = _;
    <paramaccessor> private[this] val e: org.squeryl.internals.PosoLifecycleEvent.Value = _;
    def <init>(target: org.squeryl.View[_], e: org.squeryl.internals.PosoLifecycleEvent.Value): org.squeryl.internals.LifecycleEventPercursorTable[A] = {
      LifecycleEventPercursorTable.super.<init>();
      ()
    };
    def call(f: A => Unit): org.squeryl.internals.LifecycleEvent = LifecycleEventPercursorTable.this.createLCECall[A](collection.this.Seq.apply[org.squeryl.View[_$5]](LifecycleEventPercursorTable.this.target), LifecycleEventPercursorTable.this.e, f);
    def map(a: A => A): org.squeryl.internals.LifecycleEvent = LifecycleEventPercursorTable.this.createLCEMap[A](collection.this.Seq.apply[org.squeryl.View[_$5]](LifecycleEventPercursorTable.this.target), LifecycleEventPercursorTable.this.e, a)
  };
  class LifecycleEventPercursorClass[A >: Nothing <: Any] extends Object with org.squeryl.internals.BaseLifecycleEventPercursor {
    <paramaccessor> private[this] val target: Class[_] = _;
    <paramaccessor> private[this] val schema: org.squeryl.Schema = _;
    <paramaccessor> private[this] val e: org.squeryl.internals.PosoLifecycleEvent.Value = _;
    def <init>(target: Class[_], schema: org.squeryl.Schema, e: org.squeryl.internals.PosoLifecycleEvent.Value): org.squeryl.internals.LifecycleEventPercursorClass[A] = {
      LifecycleEventPercursorClass.super.<init>();
      ()
    };
    def call(f: A => Unit): org.squeryl.internals.LifecycleEvent = LifecycleEventPercursorClass.this.createLCECall[A](LifecycleEventPercursorClass.this.schema.findAllTablesFor[_$6](LifecycleEventPercursorClass.this.target), LifecycleEventPercursorClass.this.e, f);
    def map(a: A => A): org.squeryl.internals.LifecycleEvent = LifecycleEventPercursorClass.this.createLCEMap[A](LifecycleEventPercursorClass.this.schema.findAllTablesFor[_$6](LifecycleEventPercursorClass.this.target), LifecycleEventPercursorClass.this.e, a)
  };
  class LifecycleEvent extends scala.AnyRef {
    <paramaccessor> private[this] val target: Traversable[org.squeryl.View[_]] = _;
    <stable> <accessor> <paramaccessor> def target: Traversable[org.squeryl.View[_]] = LifecycleEvent.this.target;
    <paramaccessor> private[this] val e: org.squeryl.internals.PosoLifecycleEvent.Value = _;
    <stable> <accessor> <paramaccessor> def e: org.squeryl.internals.PosoLifecycleEvent.Value = LifecycleEvent.this.e;
    <paramaccessor> private[this] val callback: AnyRef => AnyRef = _;
    <stable> <accessor> <paramaccessor> def callback: AnyRef => AnyRef = LifecycleEvent.this.callback;
    def <init>(target: Traversable[org.squeryl.View[_]], e: org.squeryl.internals.PosoLifecycleEvent.Value, callback: AnyRef => AnyRef): org.squeryl.internals.LifecycleEvent = {
      LifecycleEvent.super.<init>();
      ()
    }
  };
  abstract trait PosoLifecycleEventListener extends scala.AnyRef {
    def /*PosoLifecycleEventListener*/$init$(): Unit = {
      ()
    };
    def hasBeforeDelete: Boolean = false;
    def hasAfterDelete: Boolean = false;
    def create: AnyRef;
    def beforeInsert(a: AnyRef): AnyRef;
    def afterInsert(a: AnyRef): AnyRef;
    def beforeDelete(a: AnyRef): AnyRef;
    def afterDelete(a: AnyRef): AnyRef;
    def beforeUpdate(a: AnyRef): AnyRef;
    def afterUpdate(a: AnyRef): AnyRef;
    def afterSelect(a: AnyRef): AnyRef
  };
  object NoOpPosoLifecycleEventListener extends Object with org.squeryl.internals.PosoLifecycleEventListener {
    def <init>(): org.squeryl.internals.NoOpPosoLifecycleEventListener.type = {
      NoOpPosoLifecycleEventListener.super.<init>();
      ()
    };
    def create: AnyRef = null;
    def beforeInsert(a: AnyRef): AnyRef = a;
    def afterInsert(a: AnyRef): AnyRef = a;
    def beforeDelete(a: AnyRef): AnyRef = a;
    def afterDelete(a: AnyRef): AnyRef = a;
    def beforeUpdate(a: AnyRef): AnyRef = a;
    def afterUpdate(a: AnyRef): AnyRef = a;
    def afterSelect(a: AnyRef): AnyRef = a
  }
}