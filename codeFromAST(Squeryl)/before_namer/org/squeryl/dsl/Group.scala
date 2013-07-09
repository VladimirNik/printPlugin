package org.squeryl.dsl {
  class Group[K >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> private[this] val k: K = _;
    def <init>(k: K) = {
      super.<init>();
      ()
    };
    def key = k
  };
  class Measures[M >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> private[this] val m: M = _;
    def <init>(m: M) = {
      super.<init>();
      ()
    };
    def measures = m
  };
  class GroupWithMeasures[K >: _root_.scala.Nothing <: _root_.scala.Any, M >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> private[this] val k: K = _;
    <paramaccessor> private[this] val m: M = _;
    def <init>(k: K, m: M) = {
      super.<init>();
      ()
    };
    def key = k;
    def measures = m;
    override def toString = {
      val sb = new StringBuffer();
      sb.append("GroupWithMeasures[");
      sb.append("key=");
      sb.append(key);
      sb.append(",measures=");
      sb.append(measures);
      sb.append("]");
      sb.toString
    }
  }
}