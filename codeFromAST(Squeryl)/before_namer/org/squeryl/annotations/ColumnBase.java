package org.squeryl.annotations {
  import _root_.java.lang.annotation.Retention;
  import _root_.java.lang.annotation.RetentionPolicy;
  object ColumnBase extends  {
    def <init>() = _
  };
  class ColumnBase extends scala.annotation.Annotation with _root_.java.lang.annotation.Annotation with scala.annotation.ClassfileAnnotation {
    def <init>(x$1: String) = _;
    def <init>() = _;
    @new scala.runtime.AnnotationDefault() def value(): String = _;
    @new scala.runtime.AnnotationDefault() def name(): String = _;
    @new scala.runtime.AnnotationDefault() def length(): Int = _;
    @new scala.runtime.AnnotationDefault() def scale(): Int = _;
    @new scala.runtime.AnnotationDefault() def optionType(): Class[_$1] forSome { 
      type _$1 >: Nothing <: Any
    } = _
  }
}