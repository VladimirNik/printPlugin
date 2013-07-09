package org.squeryl.annotations {
  import _root_.java.lang.annotation.Retention;
  import _root_.java.lang.annotation.RetentionPolicy;
  object Row extends  {
    def <init>() = _
  };
  class Row extends scala.annotation.Annotation with _root_.java.lang.annotation.Annotation with scala.annotation.ClassfileAnnotation {
    def <init>(x$1: String) = _;
    def <init>() = _;
    @new scala.runtime.AnnotationDefault() def value(): String = _;
    @new scala.runtime.AnnotationDefault() def fieldToColumnCorrespondanceMode(): FieldToColumnCorrespondanceMode = _
  }
}