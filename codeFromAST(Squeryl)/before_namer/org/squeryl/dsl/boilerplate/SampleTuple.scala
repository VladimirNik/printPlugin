package org.squeryl.dsl.boilerplate {
  import org.squeryl.internals.{OutMapper, FieldReferenceLinker};
  import org.squeryl.dsl.ast.SelectElement;
  class SampleTuple extends Product {
    <paramaccessor> val outNodes: List[SelectElement] = _;
    <paramaccessor> val outMappers: Array[OutMapper[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(outNodes: List[SelectElement], outMappers: Array[OutMapper[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>();
      ()
    };
    override def canEqual(a: Any) = false;
    override def equals(a: Any) = false;
    override def productArity = outNodes.size;
    override def productElement(n: Int): Any = _get(n.$plus(1));
    protected def _get[B >: _root_.scala.Nothing <: _root_.scala.Any](i: Int) = {
      FieldReferenceLinker.putLastAccessedSelectElement(outNodes.apply(i.$minus(1)));
      outMappers.apply(i.$minus(1)).sample.asInstanceOf[B]
    }
  };
  object SampleTuple extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    def create(n: List[SelectElement], m: Array[OutMapper[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = m.length match {
      case 1 => new STuple1[Any](n, m)
      case 2 => new STuple2[Any, Any](n, m)
      case 3 => new STuple3[Any, Any, Any](n, m)
      case 4 => new STuple4[Any, Any, Any, Any](n, m)
      case 5 => new STuple5[Any, Any, Any, Any, Any](n, m)
      case 6 => new STuple6[Any, Any, Any, Any, Any, Any](n, m)
      case 7 => new STuple7[Any, Any, Any, Any, Any, Any, Any](n, m)
      case 8 => new STuple8[Any, Any, Any, Any, Any, Any, Any, Any](n, m)
      case _ => org.squeryl.internals.Utils.throwError("Tuple9 is not supported, please send a request for supporting up to Product22")
    }
  };
  class STuple1[T1 >: _root_.scala.Nothing <: _root_.scala.Any] extends SampleTuple {
    <paramaccessor> private[this] val n: List[SelectElement] = _;
    <paramaccessor> private[this] val m: Array[OutMapper[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(n: List[SelectElement], m: Array[OutMapper[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, m);
      ()
    };
    def _1 = _get[T1](1)
  };
  class STuple2[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any] extends STuple1[T1] with Product2[T1, T2] {
    <paramaccessor> private[this] val n: List[SelectElement] = _;
    <paramaccessor> private[this] val m: Array[OutMapper[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(n: List[SelectElement], m: Array[OutMapper[_$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, m);
      ()
    };
    def _2 = _get[T2](2)
  };
  class STuple3[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any] extends STuple2[T1, T2] with Product3[T1, T2, T3] {
    <paramaccessor> private[this] val n: List[SelectElement] = _;
    <paramaccessor> private[this] val m: Array[OutMapper[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(n: List[SelectElement], m: Array[OutMapper[_$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, m);
      ()
    };
    def _3 = _get[T3](3)
  };
  class STuple4[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any] extends STuple3[T1, T2, T3] with Product4[T1, T2, T3, T4] {
    <paramaccessor> private[this] val n: List[SelectElement] = _;
    <paramaccessor> private[this] val m: Array[OutMapper[_$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(n: List[SelectElement], m: Array[OutMapper[_$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, m);
      ()
    };
    def _4 = _get[T4](4)
  };
  class STuple5[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any] extends STuple4[T1, T2, T3, T4] with Product5[T1, T2, T3, T4, T5] {
    <paramaccessor> private[this] val n: List[SelectElement] = _;
    <paramaccessor> private[this] val m: Array[OutMapper[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(n: List[SelectElement], m: Array[OutMapper[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, m);
      ()
    };
    def _5 = _get[T5](5)
  };
  class STuple6[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any, T6 >: _root_.scala.Nothing <: _root_.scala.Any] extends STuple5[T1, T2, T3, T4, T5] with Product6[T1, T2, T3, T4, T5, T6] {
    <paramaccessor> private[this] val n: List[SelectElement] = _;
    <paramaccessor> private[this] val m: Array[OutMapper[_$8] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(n: List[SelectElement], m: Array[OutMapper[_$8] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, m);
      ()
    };
    def _6 = _get[T6](6)
  };
  class STuple7[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any, T6 >: _root_.scala.Nothing <: _root_.scala.Any, T7 >: _root_.scala.Nothing <: _root_.scala.Any] extends STuple6[T1, T2, T3, T4, T5, T6] with Product7[T1, T2, T3, T4, T5, T6, T7] {
    <paramaccessor> private[this] val n: List[SelectElement] = _;
    <paramaccessor> private[this] val m: Array[OutMapper[_$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(n: List[SelectElement], m: Array[OutMapper[_$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, m);
      ()
    };
    def _7 = _get[T7](7)
  };
  class STuple8[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any, T6 >: _root_.scala.Nothing <: _root_.scala.Any, T7 >: _root_.scala.Nothing <: _root_.scala.Any, T8 >: _root_.scala.Nothing <: _root_.scala.Any] extends STuple7[T1, T2, T3, T4, T5, T6, T7] with Product8[T1, T2, T3, T4, T5, T6, T7, T8] {
    <paramaccessor> private[this] val n: List[SelectElement] = _;
    <paramaccessor> private[this] val m: Array[OutMapper[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(n: List[SelectElement], m: Array[OutMapper[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>(n, m);
      ()
    };
    def _8 = _get[T8](8)
  }
}