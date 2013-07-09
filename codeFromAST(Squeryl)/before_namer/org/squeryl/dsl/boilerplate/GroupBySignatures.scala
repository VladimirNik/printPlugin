package org.squeryl.dsl.boilerplate {
  import org.squeryl.dsl.fsm.{GroupQueryYield, QueryElements, GroupByState};
  import org.squeryl.dsl.TypedExpression;
  abstract trait GroupBySignatures extends scala.AnyRef { self: QueryElements[_$1] forSome { 
    <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    def groupBy[T1 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): GroupByState[T1] = new GroupQueryYield[T1](this, (() => List(e1)));
    def groupBy[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$4] forSome { 
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): GroupByState[Product2[T1, T2]] = new GroupQueryYield[Product2[T1, T2]](this, (() => List(e1, e2)));
    def groupBy[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$6] forSome { 
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): GroupByState[Product3[T1, T2, T3]] = new GroupQueryYield[Product3[T1, T2, T3]](this, (() => List(e1, e2, e3)));
    def groupBy[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$8] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$11] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): GroupByState[Product4[T1, T2, T3, T4]] = new GroupQueryYield[Product4[T1, T2, T3, T4]](this, (() => List(e1, e2, e3, e4)));
    def groupBy[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$12] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$13] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$14] forSome { 
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$15] forSome { 
      <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e5: _root_.scala.<byname>[TypedExpression[T5, _$16] forSome { 
      <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): GroupByState[Product5[T1, T2, T3, T4, T5]] = new GroupQueryYield[Product5[T1, T2, T3, T4, T5]](this, (() => List(e1, e2, e3, e4, e5)));
    def groupBy[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any, T6 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$17] forSome { 
      <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$18] forSome { 
      <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$19] forSome { 
      <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$20] forSome { 
      <synthetic> type _$20 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e5: _root_.scala.<byname>[TypedExpression[T5, _$21] forSome { 
      <synthetic> type _$21 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e6: _root_.scala.<byname>[TypedExpression[T6, _$22] forSome { 
      <synthetic> type _$22 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): GroupByState[Product6[T1, T2, T3, T4, T5, T6]] = new GroupQueryYield[Product6[T1, T2, T3, T4, T5, T6]](this, (() => List(e1, e2, e3, e4, e5, e6)));
    def groupBy[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any, T6 >: _root_.scala.Nothing <: _root_.scala.Any, T7 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$23] forSome { 
      <synthetic> type _$23 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$24] forSome { 
      <synthetic> type _$24 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$25] forSome { 
      <synthetic> type _$25 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$26] forSome { 
      <synthetic> type _$26 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e5: _root_.scala.<byname>[TypedExpression[T5, _$27] forSome { 
      <synthetic> type _$27 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e6: _root_.scala.<byname>[TypedExpression[T6, _$28] forSome { 
      <synthetic> type _$28 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e7: _root_.scala.<byname>[TypedExpression[T7, _$29] forSome { 
      <synthetic> type _$29 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): GroupByState[Product7[T1, T2, T3, T4, T5, T6, T7]] = new GroupQueryYield[Product7[T1, T2, T3, T4, T5, T6, T7]](this, (() => List(e1, e2, e3, e4, e5, e6, e7)));
    def groupBy[T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any, T5 >: _root_.scala.Nothing <: _root_.scala.Any, T6 >: _root_.scala.Nothing <: _root_.scala.Any, T7 >: _root_.scala.Nothing <: _root_.scala.Any, T8 >: _root_.scala.Nothing <: _root_.scala.Any](e1: _root_.scala.<byname>[TypedExpression[T1, _$30] forSome { 
      <synthetic> type _$30 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e2: _root_.scala.<byname>[TypedExpression[T2, _$31] forSome { 
      <synthetic> type _$31 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e3: _root_.scala.<byname>[TypedExpression[T3, _$32] forSome { 
      <synthetic> type _$32 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e4: _root_.scala.<byname>[TypedExpression[T4, _$33] forSome { 
      <synthetic> type _$33 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e5: _root_.scala.<byname>[TypedExpression[T5, _$34] forSome { 
      <synthetic> type _$34 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e6: _root_.scala.<byname>[TypedExpression[T6, _$35] forSome { 
      <synthetic> type _$35 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e7: _root_.scala.<byname>[TypedExpression[T7, _$36] forSome { 
      <synthetic> type _$36 >: _root_.scala.Nothing <: _root_.scala.Any
    }], e8: _root_.scala.<byname>[TypedExpression[T8, _$37] forSome { 
      <synthetic> type _$37 >: _root_.scala.Nothing <: _root_.scala.Any
    }]): GroupByState[Product8[T1, T2, T3, T4, T5, T6, T7, T8]] = new GroupQueryYield[Product8[T1, T2, T3, T4, T5, T6, T7, T8]](this, (() => List(e1, e2, e3, e4, e5, e6, e7, e8)))
  }
}