package org.squeryl.dsl {
  import org.squeryl.{ForeignKeyDeclaration, Table, Query, KeyedEntity};
  import scala.collection.mutable.{HashMap, ArrayBuffer, Buffer};
  import org.squeryl.KeyedEntityDef;
  abstract trait Relation[L >: Nothing <: Any, R >: Nothing <: Any] extends scala.AnyRef {
    def leftTable: org.squeryl.Table[L];
    def rightTable: org.squeryl.Table[R]
  };
  abstract trait OneToManyRelation[O >: Nothing <: Any, M >: Nothing <: Any] extends Object with org.squeryl.dsl.Relation[O,M] {
    def /*OneToManyRelation*/$init$(): Unit = {
      ()
    };
    def foreignKeyDeclaration: org.squeryl.ForeignKeyDeclaration;
    def left(leftSide: O): org.squeryl.dsl.OneToMany[M];
    def leftStateful(leftSide: O): org.squeryl.dsl.StatefulOneToMany[M] = new org.squeryl.dsl.StatefulOneToMany[M](OneToManyRelation.this.left(leftSide));
    def right(rightSide: M): org.squeryl.dsl.ManyToOne[O];
    def rightStateful(rightSide: M): org.squeryl.dsl.StatefulManyToOne[O] = new org.squeryl.dsl.StatefulManyToOne[O](OneToManyRelation.this.right(rightSide))
  };
  class StatefulOneToMany[M >: Nothing <: Any] extends AnyRef with Iterable[M] {
    <paramaccessor> private[this] val relation: org.squeryl.dsl.OneToMany[M] = _;
    <stable> <accessor> <paramaccessor> def relation: org.squeryl.dsl.OneToMany[M] = StatefulOneToMany.this.relation;
    def <init>(relation: org.squeryl.dsl.OneToMany[M]): org.squeryl.dsl.StatefulOneToMany[M] = {
      StatefulOneToMany.super.<init>();
      ()
    };
    private[this] val _buffer: scala.collection.mutable.ArrayBuffer[M] = new scala.collection.mutable.ArrayBuffer[M]();
    <stable> <accessor> private def _buffer: scala.collection.mutable.ArrayBuffer[M] = StatefulOneToMany.this._buffer;
    StatefulOneToMany.this.refresh;
    def refresh: Unit = {
      StatefulOneToMany.this._buffer.clear();
      StatefulOneToMany.this.relation.iterator.toSeq.foreach[Unit](((m: M) => StatefulOneToMany.this._buffer.append(m)))
    };
    def iterator: Iterator[M] = StatefulOneToMany.this._buffer.iterator;
    def associate(m: M): M = {
      StatefulOneToMany.this.relation.associate(m);
      StatefulOneToMany.this._buffer.append(m);
      m
    };
    def deleteAll: Int = {
      val r: Int = StatefulOneToMany.this.relation.deleteAll;
      StatefulOneToMany.this._buffer.clear();
      r
    }
  };
  class StatefulManyToOne[O >: Nothing <: Any] extends scala.AnyRef {
    <paramaccessor> private[this] val relation: org.squeryl.dsl.ManyToOne[O] = _;
    <stable> <accessor> <paramaccessor> def relation: org.squeryl.dsl.ManyToOne[O] = StatefulManyToOne.this.relation;
    def <init>(relation: org.squeryl.dsl.ManyToOne[O]): org.squeryl.dsl.StatefulManyToOne[O] = {
      StatefulManyToOne.super.<init>();
      ()
    };
    private[this] var _one: Option[O] = scala.None;
    <accessor> private def _one: Option[O] = StatefulManyToOne.this._one;
    <accessor> private def _one_=(x$1: Option[O]): Unit = StatefulManyToOne.this._one = x$1;
    StatefulManyToOne.this.refresh;
    def refresh: Unit = StatefulManyToOne.this._one_=(StatefulManyToOne.this.relation.iterator.toSeq.headOption);
    def one: Option[O] = StatefulManyToOne.this._one;
    def assign(o: O): O = {
      StatefulManyToOne.this.relation.assign(o);
      StatefulManyToOne.this._one_=(scala.Some.apply[O](o));
      o
    };
    def delete: Boolean = {
      val b: Boolean = StatefulManyToOne.this.relation.delete;
      StatefulManyToOne.this._one_=(scala.None);
      b
    }
  };
  abstract trait ManyToManyRelation[L >: Nothing <: Any, R >: Nothing <: Any, A >: Nothing <: Any] extends Object with org.squeryl.dsl.Relation[L,R] { self: org.squeryl.dsl.ManyToManyRelation[L,R,A] with org.squeryl.Table[A] => 
    def /*ManyToManyRelation*/$init$(): Unit = {
      ()
    };
    def thisTable: org.squeryl.Table[A];
    def leftForeignKeyDeclaration: org.squeryl.ForeignKeyDeclaration;
    def rightForeignKeyDeclaration: org.squeryl.ForeignKeyDeclaration;
    def left(leftSide: L): org.squeryl.dsl.ManyToMany[R,A];
    def leftStateful(leftSide: L): org.squeryl.dsl.StatefulManyToMany[R,A] = new org.squeryl.dsl.StatefulManyToMany[R,A](ManyToManyRelation.this.left(leftSide));
    def right(rightSide: R): org.squeryl.dsl.ManyToMany[L,A];
    def rightStateful(rightSide: R): org.squeryl.dsl.StatefulManyToMany[L,A] = new org.squeryl.dsl.StatefulManyToMany[L,A](ManyToManyRelation.this.right(rightSide))
  };
  abstract trait ManyToMany[O >: Nothing <: Any, A >: Nothing <: Any] extends Object with org.squeryl.Query[O] {
    def kedL: org.squeryl.KeyedEntityDef[O, _];
    def assign(o: O, a: A): A;
    def associate(o: O, a: A): A;
    def assign(o: O): A;
    def associate(o: O): A;
    def dissociate(o: O): Boolean;
    def dissociateAll: Int;
    def associations: org.squeryl.Query[A];
    def associationMap: org.squeryl.Query[(O, A)]
  };
  class StatefulManyToMany[O >: Nothing <: Any, A >: Nothing <: Any] extends AnyRef with Iterable[O] {
    <paramaccessor> private[this] val relation: org.squeryl.dsl.ManyToMany[O,A] = _;
    <stable> <accessor> <paramaccessor> def relation: org.squeryl.dsl.ManyToMany[O,A] = StatefulManyToMany.this.relation;
    def <init>(relation: org.squeryl.dsl.ManyToMany[O,A]): org.squeryl.dsl.StatefulManyToMany[O,A] = {
      StatefulManyToMany.super.<init>();
      ()
    };
    private[this] val _map: scala.collection.mutable.HashMap[O,A] = new scala.collection.mutable.HashMap[O,A]();
    <stable> <accessor> private def _map: scala.collection.mutable.HashMap[O,A] = StatefulManyToMany.this._map;
    StatefulManyToMany.this.refresh;
    def refresh: Unit = {
      StatefulManyToMany.this._map.clear();
      StatefulManyToMany.this.relation.associationMap.iterator.toSeq.foreach[Option[A]](((e: (O, A)) => StatefulManyToMany.this._map.put(e._1, e._2)))
    };
    def iterator: Iterator[O] = StatefulManyToMany.this._map.keysIterator;
    def associate(o: O, a: A): A = {
      StatefulManyToMany.this.relation.associate(o, a);
      StatefulManyToMany.this._map.put(o, a);
      a
    };
    def associate(o: O): A = {
      val a: A = StatefulManyToMany.this.relation.associate(o);
      StatefulManyToMany.this._map.put(o, a);
      a
    };
    def dissociate(o: O): Boolean = {
      val b1: Boolean = StatefulManyToMany.this.relation.dissociate(o);
      val b2: Boolean = StatefulManyToMany.this._map.remove(o).!=(scala.None);
      scala.this.Predef.assert(b1.==(b2), scala.this.Predef.any2stringadd(scala.Symbol.apply("MutableManyToMany")).+(" out of sync ").+(o.asInstanceOf[AnyRef].getClass().getName()).+(" with id=").+(StatefulManyToMany.this.relation.kedL.getId(o)).+(if (b1)
  ""
else
  "does not").+(" exist in the db, and cached collection says the opposite"));
      b1
    };
    def dissociateAll: Int = {
      val r: Int = StatefulManyToMany.this.relation.dissociateAll;
      StatefulManyToMany.this._map.clear();
      r
    };
    def associations: Iterable[A] = StatefulManyToMany.this._map.values.toSeq
  };
  abstract trait OneToMany[M >: Nothing <: Any] extends Object with org.squeryl.Query[M] {
    def assign(m: M): M;
    def associate(m: M): M;
    def deleteAll: Int
  };
  abstract trait ManyToOne[O >: Nothing <: Any] extends Object with org.squeryl.Query[O] {
    def assign(one: O): O;
    def delete: Boolean
  }
}