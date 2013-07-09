package org.squeryl.dsl.boilerplate {
  import org.squeryl.internals.{OutMapper, FieldReferenceLinker};
  import org.squeryl.dsl.ast.SelectElement;
  class SampleTuple extends AnyRef with Product {
    <paramaccessor> private[this] val outNodes: List[org.squeryl.dsl.ast.SelectElement] = _;
    <stable> <accessor> <paramaccessor> def outNodes: List[org.squeryl.dsl.ast.SelectElement] = SampleTuple.this.outNodes;
    <paramaccessor> private[this] val outMappers: Array[org.squeryl.internals.OutMapper[_]] = _;
    <stable> <accessor> <paramaccessor> def outMappers: Array[org.squeryl.internals.OutMapper[_]] = SampleTuple.this.outMappers;
    def <init>(outNodes: List[org.squeryl.dsl.ast.SelectElement], outMappers: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.SampleTuple = {
      SampleTuple.super.<init>();
      ()
    };
    override def canEqual(a: Any): Boolean = false;
    override def equals(a: Any): Boolean = false;
    override def productArity: Int = SampleTuple.this.outNodes.size;
    override def productElement(n: Int): Any = SampleTuple.this._get[Nothing](n.+(1));
    protected def _get[B >: Nothing <: Any](i: Int): B = {
      org.squeryl.internals.FieldReferenceLinker.putLastAccessedSelectElement(SampleTuple.this.outNodes.apply(i.-(1)));
      SampleTuple.this.outMappers.apply(i.-(1)).sample.asInstanceOf[B]
    }
  };
  object SampleTuple extends scala.AnyRef {
    def <init>(): org.squeryl.dsl.boilerplate.SampleTuple.type = {
      SampleTuple.super.<init>();
      ()
    };
    def create(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple1[Any] = m.length match {
      case 1 => new org.squeryl.dsl.boilerplate.STuple1[Any](n, m)
      case 2 => new org.squeryl.dsl.boilerplate.STuple2[Any,Any](n, m)
      case 3 => new org.squeryl.dsl.boilerplate.STuple3[Any,Any,Any](n, m)
      case 4 => new org.squeryl.dsl.boilerplate.STuple4[Any,Any,Any,Any](n, m)
      case 5 => new org.squeryl.dsl.boilerplate.STuple5[Any,Any,Any,Any,Any](n, m)
      case 6 => new org.squeryl.dsl.boilerplate.STuple6[Any,Any,Any,Any,Any,Any](n, m)
      case 7 => new org.squeryl.dsl.boilerplate.STuple7[Any,Any,Any,Any,Any,Any,Any](n, m)
      case 8 => new org.squeryl.dsl.boilerplate.STuple8[Any,Any,Any,Any,Any,Any,Any,Any](n, m)
      case _ => org.squeryl.internals.Utils.throwError("Tuple9 is not supported, please send a request for supporting up to Product22")
    }
  };
  class STuple1[T1 >: Nothing <: Any] extends SampleTuple {
    <paramaccessor> private[this] val n: List[org.squeryl.dsl.ast.SelectElement] = _;
    <paramaccessor> private[this] val m: Array[org.squeryl.internals.OutMapper[_]] = _;
    def <init>(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple1[T1] = {
      STuple1.super.<init>(n, m);
      ()
    };
    def _1: T1 = STuple1.this._get[T1](1)
  };
  class STuple2[T1 >: Nothing <: Any, T2 >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.STuple1[T1] with Product2[T1,T2] {
    <paramaccessor> private[this] val n: List[org.squeryl.dsl.ast.SelectElement] = _;
    <paramaccessor> private[this] val m: Array[org.squeryl.internals.OutMapper[_]] = _;
    def <init>(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple2[T1,T2] = {
      STuple2.super.<init>(n, m);
      ()
    };
    def _2: T2 = STuple2.this._get[T2](2)
  };
  class STuple3[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.STuple2[T1,T2] with Product3[T1,T2,T3] {
    <paramaccessor> private[this] val n: List[org.squeryl.dsl.ast.SelectElement] = _;
    <paramaccessor> private[this] val m: Array[org.squeryl.internals.OutMapper[_]] = _;
    def <init>(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple3[T1,T2,T3] = {
      STuple3.super.<init>(n, m);
      ()
    };
    def _3: T3 = STuple3.this._get[T3](3)
  };
  class STuple4[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.STuple3[T1,T2,T3] with Product4[T1,T2,T3,T4] {
    <paramaccessor> private[this] val n: List[org.squeryl.dsl.ast.SelectElement] = _;
    <paramaccessor> private[this] val m: Array[org.squeryl.internals.OutMapper[_]] = _;
    def <init>(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple4[T1,T2,T3,T4] = {
      STuple4.super.<init>(n, m);
      ()
    };
    def _4: T4 = STuple4.this._get[T4](4)
  };
  class STuple5[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.STuple4[T1,T2,T3,T4] with Product5[T1,T2,T3,T4,T5] {
    <paramaccessor> private[this] val n: List[org.squeryl.dsl.ast.SelectElement] = _;
    <paramaccessor> private[this] val m: Array[org.squeryl.internals.OutMapper[_]] = _;
    def <init>(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple5[T1,T2,T3,T4,T5] = {
      STuple5.super.<init>(n, m);
      ()
    };
    def _5: T5 = STuple5.this._get[T5](5)
  };
  class STuple6[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, T6 >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.STuple5[T1,T2,T3,T4,T5] with Product6[T1,T2,T3,T4,T5,T6] {
    <paramaccessor> private[this] val n: List[org.squeryl.dsl.ast.SelectElement] = _;
    <paramaccessor> private[this] val m: Array[org.squeryl.internals.OutMapper[_]] = _;
    def <init>(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple6[T1,T2,T3,T4,T5,T6] = {
      STuple6.super.<init>(n, m);
      ()
    };
    def _6: T6 = STuple6.this._get[T6](6)
  };
  class STuple7[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, T6 >: Nothing <: Any, T7 >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.STuple6[T1,T2,T3,T4,T5,T6] with Product7[T1,T2,T3,T4,T5,T6,T7] {
    <paramaccessor> private[this] val n: List[org.squeryl.dsl.ast.SelectElement] = _;
    <paramaccessor> private[this] val m: Array[org.squeryl.internals.OutMapper[_]] = _;
    def <init>(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple7[T1,T2,T3,T4,T5,T6,T7] = {
      STuple7.super.<init>(n, m);
      ()
    };
    def _7: T7 = STuple7.this._get[T7](7)
  };
  class STuple8[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, T6 >: Nothing <: Any, T7 >: Nothing <: Any, T8 >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.STuple7[T1,T2,T3,T4,T5,T6,T7] with Product8[T1,T2,T3,T4,T5,T6,T7,T8] {
    <paramaccessor> private[this] val n: List[org.squeryl.dsl.ast.SelectElement] = _;
    <paramaccessor> private[this] val m: Array[org.squeryl.internals.OutMapper[_]] = _;
    def <init>(n: List[org.squeryl.dsl.ast.SelectElement], m: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.dsl.boilerplate.STuple8[T1,T2,T3,T4,T5,T6,T7,T8] = {
      STuple8.super.<init>(n, m);
      ()
    };
    def _8: T8 = STuple8.this._get[T8](8)
  }
}