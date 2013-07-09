package org.squeryl.dsl.boilerplate {
  import org.squeryl.dsl.fsm._;
  import org.squeryl.dsl.TypedExpression;
  abstract trait ComputeMeasuresSignaturesFromGroupByState[G >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef { self: GroupQueryYield[G] => 
    def $init$() = {
      ()
    };
    def compute[T1 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): ComputeStateFromGroupByState[G, T1] = new GroupWithMeasuresQueryYield[G, T1](this.queryElementzz, this.groupByClauseClosure, this.unevaluatedHavingClause, (() => List(e1)));
    def compute[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): ComputeStateFromGroupByState[G, Product2[T1, T2]] = new GroupWithMeasuresQueryYield[G, Product2[T1, T2]](this.queryElementzz, this.groupByClauseClosure, this.unevaluatedHavingClause, (() => List(e1, e2)));
    def compute[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): ComputeStateFromGroupByState[G, Product3[T1, T2, T3]] = new GroupWithMeasuresQueryYield[G, Product3[T1, T2, T3]](this.queryElementzz, this.groupByClauseClosure, this.unevaluatedHavingClause, (() => List(e1, e2, e3)));
    def compute[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$8] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): ComputeStateFromGroupByState[G, Product4[T1, T2, T3, T4]] = new GroupWithMeasuresQueryYield[G, Product4[T1, T2, T3, T4]](this.queryElementzz, this.groupByClauseClosure, this.unevaluatedHavingClause, (() => List(e1, e2, e3, e4)));
    def compute[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$11] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$12] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$13] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$14] forSome { 
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e5: _root_.scala.<byname>[TypedExpression[T5, _$15] forSome { 
      <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): ComputeStateFromGroupByState[G, Product5[T1, T2, T3, T4, T5]] = new GroupWithMeasuresQueryYield[G, Product5[T1, T2, T3, T4, T5]](this.queryElementzz, this.groupByClauseClosure, this.unevaluatedHavingClause, (() => List(e1, e2, e3, e4, e5)));
    def compute[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any, T6 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$16] forSome { 
      <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$17] forSome { 
      <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$18] forSome { 
      <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$19] forSome { 
      <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e5: _root_.scala.<byname>[TypedExpression[T5, _$20] forSome { 
      <synthetic> type _$20 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e6: _root_.scala.<byname>[TypedExpression[T6, _$21] forSome { 
      <synthetic> type _$21 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): ComputeStateFromGroupByState[G, Product6[T1, T2, T3, T4, T5, T6]] = new GroupWithMeasuresQueryYield[G, Product6[T1, T2, T3, T4, T5, T6]](this.queryElementzz, this.groupByClauseClosure, this.unevaluatedHavingClause, (() => List(e1, e2, e3, e4, e5, e6)));
    def compute[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any, T6 >: _root_.scala.Nothing <: _root_.scala.Any, T7 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$22] forSome { 
      <synthetic> type _$22 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$23] forSome { 
      <synthetic> type _$23 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$24] forSome { 
      <synthetic> type _$24 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$25] forSome { 
      <synthetic> type _$25 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e5: _root_.scala.<byname>[TypedExpression[T5, _$26] forSome { 
      <synthetic> type _$26 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e6: _root_.scala.<byname>[TypedExpression[T6, _$27] forSome { 
      <synthetic> type _$27 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e7: _root_.scala.<byname>[TypedExpression[T7, _$28] forSome { 
      <synthetic> type _$28 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): ComputeStateFromGroupByState[G, Product7[T1, T2, T3, T4, T5, T6, T7]] = new GroupWithMeasuresQueryYield[G, Product7[T1, T2, T3, T4, T5, T6, T7]](this.queryElementzz, this.groupByClauseClosure, this.unevaluatedHavingClause, (() => List(e1, e2, e3, e4, e5, e6, e7)))
  }
}