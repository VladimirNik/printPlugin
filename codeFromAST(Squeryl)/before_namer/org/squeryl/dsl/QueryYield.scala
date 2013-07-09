package org.squeryl.dsl {
  import ast.{LogicalBoolean, ExpressionNode, QueryExpressionNode, SelectElement};
  import boilerplate._;
  import org.squeryl.internals.ResultSetMapper;
  import java.sql.ResultSet;
  abstract trait QueryYield[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    def $init$() = {
      ()
    };
    def invokeYield(resultSetMapper: ResultSetMapper, rs: ResultSet): R;
    def invokeYieldForAst(q: QueryExpressionNode[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, rsm: ResultSetMapper): scala.Tuple2[Iterable[SelectElement], AnyRef];
    def queryElements: scala.Tuple4[Option[ExpressionNode], Option[ExpressionNode], Iterable[ExpressionNode], Iterable[ExpressionNode]];
    private[squeryl] var joinExpressions: Seq[_root_.scala.Function0[LogicalBoolean]] = Nil;
    def on(lb1: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)));
      new JoinQueryYield1(this)
    };
    def on(lb1: _root_.scala.<byname>[LogicalBoolean], lb2: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)), (lb2: (() => <empty>)));
      new JoinQueryYield2(this)
    };
    def on(lb1: _root_.scala.<byname>[LogicalBoolean], lb2: _root_.scala.<byname>[LogicalBoolean], lb3: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)), (lb2: (() => <empty>)), (lb3: (() => <empty>)));
      new JoinQueryYield3(this)
    };
    def on(lb1: _root_.scala.<byname>[LogicalBoolean], lb2: _root_.scala.<byname>[LogicalBoolean], lb3: _root_.scala.<byname>[LogicalBoolean], lb4: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)), (lb2: (() => <empty>)), (lb3: (() => <empty>)), (lb4: (() => <empty>)));
      new JoinQueryYield4(this)
    };
    def on(lb1: _root_.scala.<byname>[LogicalBoolean], lb2: _root_.scala.<byname>[LogicalBoolean], lb3: _root_.scala.<byname>[LogicalBoolean], lb4: _root_.scala.<byname>[LogicalBoolean], lb5: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)), (lb2: (() => <empty>)), (lb3: (() => <empty>)), (lb4: (() => <empty>)), (lb5: (() => <empty>)));
      new JoinQueryYield5(this)
    };
    def on(lb1: _root_.scala.<byname>[LogicalBoolean], lb2: _root_.scala.<byname>[LogicalBoolean], lb3: _root_.scala.<byname>[LogicalBoolean], lb4: _root_.scala.<byname>[LogicalBoolean], lb5: _root_.scala.<byname>[LogicalBoolean], lb6: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)), (lb2: (() => <empty>)), (lb3: (() => <empty>)), (lb4: (() => <empty>)), (lb5: (() => <empty>)), (lb6: (() => <empty>)));
      new JoinQueryYield6(this)
    };
    def on(lb1: _root_.scala.<byname>[LogicalBoolean], lb2: _root_.scala.<byname>[LogicalBoolean], lb3: _root_.scala.<byname>[LogicalBoolean], lb4: _root_.scala.<byname>[LogicalBoolean], lb5: _root_.scala.<byname>[LogicalBoolean], lb6: _root_.scala.<byname>[LogicalBoolean], lb7: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)), (lb2: (() => <empty>)), (lb3: (() => <empty>)), (lb4: (() => <empty>)), (lb5: (() => <empty>)), (lb6: (() => <empty>)), (lb7: (() => <empty>)));
      new JoinQueryYield7(this)
    };
    def on(lb1: _root_.scala.<byname>[LogicalBoolean], lb2: _root_.scala.<byname>[LogicalBoolean], lb3: _root_.scala.<byname>[LogicalBoolean], lb4: _root_.scala.<byname>[LogicalBoolean], lb5: _root_.scala.<byname>[LogicalBoolean], lb6: _root_.scala.<byname>[LogicalBoolean], lb7: _root_.scala.<byname>[LogicalBoolean], lb8: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)), (lb2: (() => <empty>)), (lb3: (() => <empty>)), (lb4: (() => <empty>)), (lb5: (() => <empty>)), (lb6: (() => <empty>)), (lb7: (() => <empty>)), (lb8: (() => <empty>)));
      new JoinQueryYield8(this)
    };
    def on(lb1: _root_.scala.<byname>[LogicalBoolean], lb2: _root_.scala.<byname>[LogicalBoolean], lb3: _root_.scala.<byname>[LogicalBoolean], lb4: _root_.scala.<byname>[LogicalBoolean], lb5: _root_.scala.<byname>[LogicalBoolean], lb6: _root_.scala.<byname>[LogicalBoolean], lb7: _root_.scala.<byname>[LogicalBoolean], lb8: _root_.scala.<byname>[LogicalBoolean], lb9: _root_.scala.<byname>[LogicalBoolean]) = {
      joinExpressions = Seq((lb1: (() => <empty>)), (lb2: (() => <empty>)), (lb3: (() => <empty>)), (lb4: (() => <empty>)), (lb5: (() => <empty>)), (lb6: (() => <empty>)), (lb7: (() => <empty>)), (lb8: (() => <empty>)), (lb9: (() => <empty>)));
      new JoinQueryYield9(this)
    }
  }
}