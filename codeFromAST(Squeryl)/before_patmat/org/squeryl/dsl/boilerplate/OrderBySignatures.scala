package org.squeryl.dsl.boilerplate {
  import org.squeryl.dsl.QueryYield;
  import org.squeryl.dsl.fsm.BaseQueryYield;
  import org.squeryl.dsl.ast.{ExpressionNode, OrderByArg};
  abstract trait OrderBySignatures[R >: Nothing <: Any] extends scala.AnyRef { self: org.squeryl.dsl.fsm.BaseQueryYield[R] => 
    def /*OrderBySignatures*/$init$(): Unit = {
      ()
    };
    type O = org.squeryl.dsl.ast.ExpressionNode;
    def orderBy(args: List[OrderBySignatures.this.O]): org.squeryl.dsl.QueryYield[R] = {
      OrderBySignatures.this._orderByExpressions_=((() => args.map[() => OrderBySignatures.this.O, List[() => org.squeryl.dsl.ast.ExpressionNode]](((x$1: OrderBySignatures.this.O) => (() => x$1)))(immutable.this.List.canBuildFrom[() => OrderBySignatures.this.O])));
      this
    };
    def orderBy(e1: => OrderBySignatures.this.O): org.squeryl.dsl.QueryYield[R] = {
      OrderBySignatures.this._orderByExpressions_=((() => immutable.this.List.apply[() => OrderBySignatures.this.O]((() => e1))));
      this
    };
    def orderBy(e1: => OrderBySignatures.this.O, e2: => OrderBySignatures.this.O): org.squeryl.dsl.QueryYield[R] = {
      OrderBySignatures.this._orderByExpressions_=((() => immutable.this.List.apply[() => OrderBySignatures.this.O]((() => e1), (() => e2))));
      this
    };
    def orderBy(e1: => OrderBySignatures.this.O, e2: => OrderBySignatures.this.O, e3: => OrderBySignatures.this.O): org.squeryl.dsl.QueryYield[R] = {
      OrderBySignatures.this._orderByExpressions_=((() => immutable.this.List.apply[() => OrderBySignatures.this.O]((() => e1), (() => e2), (() => e3))));
      this
    };
    def orderBy(e1: => OrderBySignatures.this.O, e2: => OrderBySignatures.this.O, e3: => OrderBySignatures.this.O, e4: => OrderBySignatures.this.O): org.squeryl.dsl.QueryYield[R] = {
      OrderBySignatures.this._orderByExpressions_=((() => immutable.this.List.apply[() => OrderBySignatures.this.O]((() => e1), (() => e2), (() => e3), (() => e4))));
      this
    };
    def orderBy(e1: => OrderBySignatures.this.O, e2: => OrderBySignatures.this.O, e3: => OrderBySignatures.this.O, e4: => OrderBySignatures.this.O, e5: => OrderBySignatures.this.O): org.squeryl.dsl.QueryYield[R] = {
      OrderBySignatures.this._orderByExpressions_=((() => immutable.this.List.apply[() => OrderBySignatures.this.O]((() => e1), (() => e2), (() => e3), (() => e4), (() => e5))));
      this
    };
    def orderBy(e1: => OrderBySignatures.this.O, e2: => OrderBySignatures.this.O, e3: => OrderBySignatures.this.O, e4: => OrderBySignatures.this.O, e5: => OrderBySignatures.this.O, e6: => OrderBySignatures.this.O): org.squeryl.dsl.QueryYield[R] = {
      OrderBySignatures.this._orderByExpressions_=((() => immutable.this.List.apply[() => OrderBySignatures.this.O]((() => e1), (() => e2), (() => e3), (() => e4), (() => e5), (() => e6))));
      this
    };
    def orderBy(e1: => OrderBySignatures.this.O, e2: => OrderBySignatures.this.O, e3: => OrderBySignatures.this.O, e4: => OrderBySignatures.this.O, e5: => OrderBySignatures.this.O, e6: => OrderBySignatures.this.O, e7: => OrderBySignatures.this.O): org.squeryl.dsl.QueryYield[R] = {
      OrderBySignatures.this._orderByExpressions_=((() => immutable.this.List.apply[() => OrderBySignatures.this.O]((() => e1), (() => e2), (() => e3), (() => e4), (() => e5), (() => e6), (() => e7))));
      this
    }
  }
}