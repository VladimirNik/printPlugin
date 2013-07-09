package org.squeryl.dsl {
  import org.squeryl.{ForeignKeyDeclaration, Table, Query, KeyedEntity};
  import collection.mutable.{HashMap, ArrayBuffer, Buffer};
  import org.squeryl.KeyedEntityDef;
  abstract trait Relation[L >: _root_.scala.Nothing <: _root_.scala.Any, R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    def leftTable: Table[L];
    def rightTable: Table[R]
  };
  abstract trait OneToManyRelation[O >: _root_.scala.Nothing <: _root_.scala.Any, M >: _root_.scala.Nothing <: _root_.scala.Any] extends Relation[O, M] {
    def $init$() = {
      ()
    };
    def foreignKeyDeclaration: ForeignKeyDeclaration;
    def left(leftSide: O): OneToMany[M];
    def leftStateful(leftSide: O) = new StatefulOneToMany[M](left(leftSide));
    def right(rightSide: M): ManyToOne[O];
    def rightStateful(rightSide: M) = new StatefulManyToOne[O](right(rightSide))
  };
  class StatefulOneToMany[M >: _root_.scala.Nothing <: _root_.scala.Any] extends Iterable[M] {
    <paramaccessor> val relation: OneToMany[M] = _;
    def <init>(relation: OneToMany[M]) = {
      super.<init>();
      ()
    };
    private val _buffer = new ArrayBuffer[M]();
    refresh;
    def refresh = {
      _buffer.clear;
      relation.iterator.toSeq.foreach(((m) => _buffer.append(m)))
    };
    def iterator = _buffer.iterator;
    def associate(m: M) = {
      relation.associate(m);
      _buffer.append(m);
      m
    };
    def deleteAll: Int = {
      val r = relation.deleteAll;
      _buffer.clear;
      r
    }
  };
  class StatefulManyToOne[O >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    <paramaccessor> val relation: ManyToOne[O] = _;
    def <init>(relation: ManyToOne[O]) = {
      super.<init>();
      ()
    };
    private var _one: Option[O] = None;
    refresh;
    def refresh = _one = relation.iterator.toSeq.headOption;
    def one = _one;
    def assign(o: O) = {
      relation.assign(o);
      _one = Some(o);
      o
    };
    def delete = {
      val b = relation.delete;
      _one = None;
      b
    }
  };
  abstract trait ManyToManyRelation[L >: _root_.scala.Nothing <: _root_.scala.Any, R >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any] extends Relation[L, R] { self: Table[A] => 
    def $init$() = {
      ()
    };
    def thisTable: Table[A];
    def leftForeignKeyDeclaration: ForeignKeyDeclaration;
    def rightForeignKeyDeclaration: ForeignKeyDeclaration;
    def left(leftSide: L): ManyToMany[R, A];
    def leftStateful(leftSide: L) = new StatefulManyToMany[R, A](left(leftSide));
    def right(rightSide: R): ManyToMany[L, A];
    def rightStateful(rightSide: R) = new StatefulManyToMany[L, A](right(rightSide))
  };
  abstract trait ManyToMany[O >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any] extends Query[O] {
    def kedL: KeyedEntityDef[O, _$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    };
    def assign(o: O, a: A): A;
    def associate(o: O, a: A): A;
    def assign(o: O): A;
    def associate(o: O): A;
    def dissociate(o: O): Boolean;
    def dissociateAll: Int;
    def associations: Query[A];
    def associationMap: Query[scala.Tuple2[O, A]]
  };
  class StatefulManyToMany[O >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any] extends Iterable[O] {
    <paramaccessor> val relation: ManyToMany[O, A] = _;
    def <init>(relation: ManyToMany[O, A]) = {
      super.<init>();
      ()
    };
    private val _map = new HashMap[O, A]();
    refresh;
    def refresh = {
      _map.clear;
      relation.associationMap.iterator.toSeq.foreach(((e) => _map.put(e._1, e._2)))
    };
    def iterator = _map.keysIterator;
    def associate(o: O, a: A) = {
      relation.associate(o, a);
      _map.put(o, a);
      a
    };
    def associate(o: O): A = {
      val a = relation.associate(o);
      _map.put(o, a);
      a
    };
    def dissociate(o: O): Boolean = {
      val b1 = relation.dissociate(o);
      val b2 = _map.remove(o).$bang$eq(None);
      assert(b1.$eq$eq(b2), scala.Symbol("MutableManyToMany").$plus(" out of sync ").$plus(o.asInstanceOf[AnyRef].getClass.getName).$plus(" with id=").$plus(relation.kedL.getId(o)).$plus(if (b1)
  ""
else
  "does not").$plus(" exist in the db, and cached collection says the opposite"));
      b1
    };
    def dissociateAll: Int = {
      val r = relation.dissociateAll;
      _map.clear;
      r
    };
    def associations: Iterable[A] = _map.values.toSeq
  };
  abstract trait OneToMany[M >: _root_.scala.Nothing <: _root_.scala.Any] extends Query[M] {
    def assign(m: M): M;
    def associate(m: M): M;
    def deleteAll: Int
  };
  abstract trait ManyToOne[O >: _root_.scala.Nothing <: _root_.scala.Any] extends Query[O] {
    def assign(one: O): O;
    def delete: Boolean
  }
}