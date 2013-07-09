package org.squeryl.dsl {
  import ast.{LogicalBoolean, ExpressionNode, QueryExpressionNode, SelectElement};
  import boilerplate._;
  import org.squeryl.internals.ResultSetMapper;
  import java.sql.ResultSet;
  abstract trait QueryYield[R >: Nothing <: Any] extends scala.AnyRef {
    def /*QueryYield*/$init$(): Unit = {
      ()
    };
    def invokeYield(resultSetMapper: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R;
    def invokeYieldForAst(q: org.squeryl.dsl.ast.QueryExpressionNode[_], rsm: org.squeryl.internals.ResultSetMapper): (Iterable[org.squeryl.dsl.ast.SelectElement], AnyRef);
    def queryElements: (Option[org.squeryl.dsl.ast.ExpressionNode], Option[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode], Iterable[org.squeryl.dsl.ast.ExpressionNode]);
    private[this] var joinExpressions: Seq[() => org.squeryl.dsl.ast.LogicalBoolean] = immutable.this.Nil;
    <accessor> private[squeryl] def joinExpressions: Seq[() => org.squeryl.dsl.ast.LogicalBoolean] = QueryYield.this.joinExpressions;
    <accessor> private[squeryl] def joinExpressions_=(x$1: Seq[() => org.squeryl.dsl.ast.LogicalBoolean]): Unit = QueryYield.this.joinExpressions = x$1;
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield1[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield1[R](this)
    };
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean, lb2: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield2[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1), (() => lb2)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield2[R](this)
    };
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean, lb2: => org.squeryl.dsl.ast.LogicalBoolean, lb3: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield3[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1), (() => lb2), (() => lb3)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield3[R](this)
    };
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean, lb2: => org.squeryl.dsl.ast.LogicalBoolean, lb3: => org.squeryl.dsl.ast.LogicalBoolean, lb4: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield4[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1), (() => lb2), (() => lb3), (() => lb4)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield4[R](this)
    };
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean, lb2: => org.squeryl.dsl.ast.LogicalBoolean, lb3: => org.squeryl.dsl.ast.LogicalBoolean, lb4: => org.squeryl.dsl.ast.LogicalBoolean, lb5: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield5[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1), (() => lb2), (() => lb3), (() => lb4), (() => lb5)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield5[R](this)
    };
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean, lb2: => org.squeryl.dsl.ast.LogicalBoolean, lb3: => org.squeryl.dsl.ast.LogicalBoolean, lb4: => org.squeryl.dsl.ast.LogicalBoolean, lb5: => org.squeryl.dsl.ast.LogicalBoolean, lb6: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield6[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1), (() => lb2), (() => lb3), (() => lb4), (() => lb5), (() => lb6)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield6[R](this)
    };
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean, lb2: => org.squeryl.dsl.ast.LogicalBoolean, lb3: => org.squeryl.dsl.ast.LogicalBoolean, lb4: => org.squeryl.dsl.ast.LogicalBoolean, lb5: => org.squeryl.dsl.ast.LogicalBoolean, lb6: => org.squeryl.dsl.ast.LogicalBoolean, lb7: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield7[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1), (() => lb2), (() => lb3), (() => lb4), (() => lb5), (() => lb6), (() => lb7)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield7[R](this)
    };
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean, lb2: => org.squeryl.dsl.ast.LogicalBoolean, lb3: => org.squeryl.dsl.ast.LogicalBoolean, lb4: => org.squeryl.dsl.ast.LogicalBoolean, lb5: => org.squeryl.dsl.ast.LogicalBoolean, lb6: => org.squeryl.dsl.ast.LogicalBoolean, lb7: => org.squeryl.dsl.ast.LogicalBoolean, lb8: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield8[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1), (() => lb2), (() => lb3), (() => lb4), (() => lb5), (() => lb6), (() => lb7), (() => lb8)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield8[R](this)
    };
    def on(lb1: => org.squeryl.dsl.ast.LogicalBoolean, lb2: => org.squeryl.dsl.ast.LogicalBoolean, lb3: => org.squeryl.dsl.ast.LogicalBoolean, lb4: => org.squeryl.dsl.ast.LogicalBoolean, lb5: => org.squeryl.dsl.ast.LogicalBoolean, lb6: => org.squeryl.dsl.ast.LogicalBoolean, lb7: => org.squeryl.dsl.ast.LogicalBoolean, lb8: => org.squeryl.dsl.ast.LogicalBoolean, lb9: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.boilerplate.JoinQueryYield9[R] = {
      QueryYield.this.joinExpressions_=(collection.this.Seq.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => lb1), (() => lb2), (() => lb3), (() => lb4), (() => lb5), (() => lb6), (() => lb7), (() => lb8), (() => lb9)));
      new org.squeryl.dsl.boilerplate.JoinQueryYield9[R](this)
    }
  }
}