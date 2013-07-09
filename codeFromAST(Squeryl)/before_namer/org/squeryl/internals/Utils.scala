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
    def <init>() = {
      super.<init>();
      ()
    };
    def failSafeString(s: _root_.scala.<byname>[String]) = _failSafeString((s: (() => <empty>)), "cannot evaluate");
    def failSafeString(s: _root_.scala.<byname>[String], valueOnFail: String) = _failSafeString((s: (() => <empty>)), valueOnFail);
    private def _failSafeString(s: _root_.scala.Function0[String], valueOnFail: String) = try {
      s()
    } catch {
      case (e @ (_: Exception)) => valueOnFail
    };
    def close(s: Statement) = try {
      s.close
    } catch {
      case (e @ (_: SQLException)) => ()
    };
    def close(rs: ResultSet) = try {
      rs.close
    } catch {
      case (e @ (_: SQLException)) => ()
    };
    def close(c: Connection) = try {
      c.close
    } catch {
      case (e @ (_: SQLException)) => ()
    };
    private class DummyQueryElements[Cond >: _root_.scala.Nothing <: _root_.scala.Any] extends QueryElements[Cond] {
      override <paramaccessor> val whereClause: Option[_root_.scala.Function0[LogicalBoolean]] = _;
      def <init>(whereClause: Option[_root_.scala.Function0[LogicalBoolean]]) = {
        super.<init>();
        ()
      }
    };
    private class DummyQuery[A >: _root_.scala.Nothing <: _root_.scala.Any, B >: _root_.scala.Nothing <: _root_.scala.Any] extends Query1[A, Int] {
      <paramaccessor> private[this] val q: Queryable[A] = _;
      <paramaccessor> private[this] val f: _root_.scala.Function1[A, B] = _;
      <paramaccessor> private[this] val g: _root_.scala.Function1[B, Unit] = _;
      def <init>(q: Queryable[A], f: _root_.scala.Function1[A, B], g: _root_.scala.Function1[B, Unit]) = {
        super.<init>(q, ((a) => {
          val res = f(a);
          g(res);
          new DummyQueryElements(None).select(0)
        }), true);
        ()
      }
    };
    private class DummyQuery4WhereClause[A >: _root_.scala.Nothing <: _root_.scala.Any, B >: _root_.scala.Nothing <: _root_.scala.Any] extends Query1[A, Int] {
      <paramaccessor> private[this] val q: Queryable[A] = _;
      <paramaccessor> private[this] val whereClause: _root_.scala.Function1[A, LogicalBoolean] = _;
      def <init>(q: Queryable[A], whereClause: _root_.scala.Function1[A, LogicalBoolean]) = {
        super.<init>(q, ((a) => new DummyQueryElements(Some((() => whereClause(a)))).select(0)), true);
        ()
      }
    };
    def createQuery4WhereClause[A >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], whereClause: _root_.scala.Function1[A, LogicalBoolean]): QueryExpressionElements = new DummyQuery4WhereClause(q, whereClause).ast;
    def mapSampleObject[A >: _root_.scala.Nothing <: _root_.scala.Any, B >: _root_.scala.Nothing <: _root_.scala.Any](q: Queryable[A], visitor: _root_.scala.Function1[A, B]): B = FieldReferenceLinker.executeAndRestoreLastAccessedFieldReference({
      var b: Option[B] = None;
      new DummyQuery(q, visitor, ((b0: B) => b = Some(b0)));
      b.get
    });
    def throwError(msg: String): Nothing = throw new RuntimeException(msg);
    def enumerationForValue(v: Enumeration#Value): Enumeration = {
      val m = v.getClass.getField("$outer");
      val enu = m.get(v).asInstanceOf[Enumeration];
      enu
    }
  };
  class IteratorConcatenation[R >: _root_.scala.Nothing <: _root_.scala.Any] extends Iterator[R] {
    <paramaccessor> private[this] val first: Iterator[R] = _;
    <paramaccessor> private[this] val second: Iterator[R] = _;
    def <init>(first: Iterator[R], second: Iterator[R]) = {
      super.<init>();
      ()
    };
    var currentIterator = first;
    def _hasNext = if (currentIterator.hasNext)
      true
    else
      if (currentIterator.$eq$eq(second))
        false
      else
        {
          currentIterator = second;
          currentIterator.hasNext
        };
    def hasNext = _hasNext;
    def next = {
      _hasNext;
      currentIterator.next
    }
  }
}