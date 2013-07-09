package org.squeryl.annotations {
  import _root_.java.lang.annotation.Retention;
  import _root_.java.lang.annotation.RetentionPolicy;
  object OptionType extends  {
    def <init>() = _
  };
  class OptionType extends scala.annotation.Annotation with _root_.java.lang.annotation.Annotation with scala.annotation.ClassfileAnnotation {
    def <init>(x$1: Class[_$1] forSome { 
      type _$1 >: Nothing <: Any
    }) = _;
    def <init>() = _;
    def value(): Class[_$1] forSome { 
      type _$1 >: Nothing <: Any
    }
  }
}