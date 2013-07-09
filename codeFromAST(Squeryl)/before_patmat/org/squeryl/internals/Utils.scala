package org.squeryl.internals {
  import java.sql.{ResultSet, SQLException, Statement};
  import org.squeryl.dsl.boilerplate.Query1;
  import org.squeryl.Queryable;
  import org.squeryl.dsl.fsm.QueryElements;
  import org.squeryl.dsl.QueryYield;
  import org.squeryl.dsl.ast.{QueryExpressionElements, LogicalBoolean};
  import java.lang.RuntimeException;
  import java.sql.Connection;
  object Utils extends scala.AnyRef {
    def <init>(): org.squeryl.internals.Utils.type = {
      Utils.super.<init>();
      ()
    };
    def failSafeString(s: => String): String = Utils.this._failSafeString((() => s), "cannot evaluate");
    def failSafeString(s: => String, valueOnFail: String): String = Utils.this._failSafeString((() => s), valueOnFail);
    private def _failSafeString(s: () => String, valueOnFail: String): String = try {
      s.apply()
    } catch {
      case (e @ (_: Exception)) => valueOnFail
    };
    def close(s: java.sql.Statement): Unit = try {
      s.close()
    } catch {
      case (e @ (_: java.sql.SQLException)) => ()
    };
    def close(rs: java.sql.ResultSet): Unit = try {
      rs.close()
    } catch {
      case (e @ (_: java.sql.SQLException)) => ()
    };
    def close(c: java.sql.Connection): Unit = try {
      c.close()
    } catch {
      case (e @ (_: java.sql.SQLException)) => ()
    };
    private class DummyQueryElements[Cond >: Nothing <: Any] extends Object with org.squeryl.dsl.fsm.QueryElements[Cond] {
      <paramaccessor> private[this] val whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = _;
      override <stable> <accessor> <paramaccessor> def whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = DummyQueryElements.this.whereClause;
      def <init>(whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean]): org.squeryl.internals.Utils.DummyQueryElements[Cond] = {
        DummyQueryElements.super.<init>();
        ()
      }
    };
    private class DummyQuery[A >: Nothing <: Any, B >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.Query1[A,Int] {
      <paramaccessor> private[this] val q: org.squeryl.Queryable[A] = _;
      <paramaccessor> private[this] val f: A => B = _;
      <paramaccessor> private[this] val g: B => Unit = _;
      def <init>(q: org.squeryl.Queryable[A], f: A => B, g: B => Unit): org.squeryl.internals.Utils.DummyQuery[A,B] = {
        DummyQuery.super.<init>(q, ((a: A) => {
          val res: B = f.apply(a);
          g.apply(res);
          new org.squeryl.internals.Utils.DummyQueryElements[Nothing](scala.None).select[Int](0)
        }), true);
        ()
      }
    };
    private class DummyQuery4WhereClause[A >: Nothing <: Any, B >: Nothing <: Any] extends org.squeryl.dsl.boilerplate.Query1[A,Int] {
      <paramaccessor> private[this] val q: org.squeryl.Queryable[A] = _;
      <paramaccessor> private[this] val whereClause: A => org.squeryl.dsl.ast.LogicalBoolean = _;
      def <init>(q: org.squeryl.Queryable[A], whereClause: A => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.internals.Utils.DummyQuery4WhereClause[A,B] = {
        DummyQuery4WhereClause.super.<init>(q, ((a: A) => new org.squeryl.internals.Utils.DummyQueryElements[Nothing](scala.Some.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => whereClause.apply(a)))).select[Int](0)), true);
        ()
      }
    };
    def createQuery4WhereClause[A >: Nothing <: Any](q: org.squeryl.Queryable[A], whereClause: A => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.ast.QueryExpressionElements = new org.squeryl.internals.Utils.DummyQuery4WhereClause[A,Nothing](q, whereClause).ast;
    def mapSampleObject[A >: Nothing <: Any, B >: Nothing <: Any](q: org.squeryl.Queryable[A], visitor: A => B): B = FieldReferenceLinker.executeAndRestoreLastAccessedFieldReference[B]({
      var b: Option[B] = scala.None;
      new org.squeryl.internals.Utils.DummyQuery[A,B](q, visitor, ((b0: B) => b = scala.Some.apply[B](b0)));
      b.get
    });
    def throwError(msg: String): Nothing = throw new java.lang.RuntimeException(msg);
    def enumerationForValue(v: Enumeration#Value): Enumeration = {
      val m: java.lang.reflect.Field = v.getClass().getField("$outer");
      val enu: Enumeration = m.get(v).asInstanceOf[Enumeration];
      enu
    }
  };
  class IteratorConcatenation[R >: Nothing <: Any] extends AnyRef with Iterator[R] {
    <paramaccessor> private[this] val first: Iterator[R] = _;
    <paramaccessor> private[this] val second: Iterator[R] = _;
    def <init>(first: Iterator[R], second: Iterator[R]): org.squeryl.internals.IteratorConcatenation[R] = {
      IteratorConcatenation.super.<init>();
      ()
    };
    private[this] var currentIterator: Iterator[R] = IteratorConcatenation.this.first;
    <accessor> def currentIterator: Iterator[R] = IteratorConcatenation.this.currentIterator;
    <accessor> def currentIterator_=(x$1: Iterator[R]): Unit = IteratorConcatenation.this.currentIterator = x$1;
    def _hasNext: Boolean = if (IteratorConcatenation.this.currentIterator.hasNext)
      true
    else
      if (IteratorConcatenation.this.currentIterator.==(IteratorConcatenation.this.second))
        false
      else
        {
          IteratorConcatenation.this.currentIterator_=(IteratorConcatenation.this.second);
          IteratorConcatenation.this.currentIterator.hasNext
        };
    def hasNext: Boolean = IteratorConcatenation.this._hasNext;
    def next: R = {
      IteratorConcatenation.this._hasNext;
      IteratorConcatenation.this.currentIterator.next()
    }
  }
}