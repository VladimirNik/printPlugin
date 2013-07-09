package org.squeryl.dsl {
  class Group[K >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val k: K = _;
    def <init>(k: K): org.squeryl.dsl.Group[K] = {
      Group.super.<init>();
      ()
    };
    def key: K = Group.this.k
  };
  class Measures[M >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val m: M = _;
    def <init>(m: M): org.squeryl.dsl.Measures[M] = {
      Measures.super.<init>();
      ()
    };
    def measures: M = Measures.this.m
  };
  class GroupWithMeasures[K >: Nothing <: Any, M >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val k: K = _;
    <paramaccessor> private[this] val m: M = _;
    def <init>(k: K, m: M): org.squeryl.dsl.GroupWithMeasures[K,M] = {
      GroupWithMeasures.super.<init>();
      ()
    };
    def key: K = GroupWithMeasures.this.k;
    def measures: M = GroupWithMeasures.this.m;
    override def toString: String = {
      val sb: StringBuffer = new java.this.lang.StringBuffer();
      sb.append("GroupWithMeasures[");
      sb.append("key=");
      sb.append(GroupWithMeasures.this.key);
      sb.append(",measures=");
      sb.append(GroupWithMeasures.this.measures);
      sb.append("]");
      sb.toString()
    }
  }
}