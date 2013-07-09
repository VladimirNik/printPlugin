package org.squeryl.dsl.boilerplate {
  import org.squeryl.dsl.QueryYield;
  import org.squeryl.dsl.fsm.BaseQueryYield;
  import org.squeryl.dsl.ast.{ExpressionNode, OrderByArg};
  abstract trait OrderBySignatures[R >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef { self: BaseQueryYield[R] => 
    def $init$() = {
      ()
    };
    type O = ExpressionNode;
    def orderBy(args: List[O]): QueryYield[R] = {
      _orderByExpressions = (() => args.map(((x$1) => (() => x$1))));
      this
    };
    def orderBy(e1: _root_.scala.<byname>[O]): QueryYield[R] = {
      _orderByExpressions = (() => List((e1: (() => <empty>))));
      this
    };
    def orderBy(e1: _root_.scala.<byname>[O], e2: _root_.scala.<byname>[O]): QueryYield[R] = {
      _orderByExpressions = (() => List((e1: (() => <empty>)), (e2: (() => <empty>))));
      this
    };
    def orderBy(e1: _root_.scala.<byname>[O], e2: _root_.scala.<byname>[O], e3: _root_.scala.<byname>[O]): QueryYield[R] = {
      _orderByExpressions = (() => List((e1: (() => <empty>)), (e2: (() => <empty>)), (e3: (() => <empty>))));
      this
    };
    def orderBy(e1: _root_.scala.<byname>[O], e2: _root_.scala.<byname>[O], e3: _root_.scala.<byname>[O], e4: _root_.scala.<byname>[O]): QueryYield[R] = {
      _orderByExpressions = (() => List((e1: (() => <empty>)), (e2: (() => <empty>)), (e3: (() => <empty>)), (e4: (() => <empty>))));
      this
    };
    def orderBy(e1: _root_.scala.<byname>[O], e2: _root_.scala.<byname>[O], e3: _root_.scala.<byname>[O], e4: _root_.scala.<byname>[O], e5: _root_.scala.<byname>[O]): QueryYield[R] = {
      _orderByExpressions = (() => List((e1: (() => <empty>)), (e2: (() => <empty>)), (e3: (() => <empty>)), (e4: (() => <empty>)), (e5: (() => <empty>))));
      this
    };
    def orderBy(e1: _root_.scala.<byname>[O], e2: _root_.scala.<byname>[O], e3: _root_.scala.<byname>[O], e4: _root_.scala.<byname>[O], e5: _root_.scala.<byname>[O], e6: _root_.scala.<byname>[O]): QueryYield[R] = {
      _orderByExpressions = (() => List((e1: (() => <empty>)), (e2: (() => <empty>)), (e3: (() => <empty>)), (e4: (() => <empty>)), (e5: (() => <empty>)), (e6: (() => <empty>))));
      this
    };
    def orderBy(e1: _root_.scala.<byname>[O], e2: _root_.scala.<byname>[O], e3: _root_.scala.<byname>[O], e4: _root_.scala.<byname>[O], e5: _root_.scala.<byname>[O], e6: _root_.scala.<byname>[O], e7: _root_.scala.<byname>[O]): QueryYield[R] = {
      _orderByExpressions = (() => List((e1: (() => <empty>)), (e2: (() => <empty>)), (e3: (() => <empty>)), (e4: (() => <empty>)), (e5: (() => <empty>)), (e6: (() => <empty>)), (e7: (() => <empty>))));
      this
    }
  }
}