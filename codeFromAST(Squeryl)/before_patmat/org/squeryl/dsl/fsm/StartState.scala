package org.squeryl.dsl.fsm {
  import org.squeryl.dsl._;
  import org.squeryl.dsl.ast.{LogicalBoolean, UpdateStatement, UpdateAssignment};
  import org.squeryl.dsl.boilerplate.{ComputeMeasuresSignaturesFromStartOrWhereState, ComputeMeasuresSignaturesFromGroupByState, GroupBySignatures, OrderBySignatures};
  sealed abstract class Conditioned extends scala.AnyRef {
    def <init>(): org.squeryl.dsl.fsm.Conditioned = {
      Conditioned.super.<init>();
      ()
    }
  };
  sealed abstract class Unconditioned extends scala.AnyRef {
    def <init>(): org.squeryl.dsl.fsm.Unconditioned = {
      Unconditioned.super.<init>();
      ()
    }
  };
  abstract trait StartState extends Object with org.squeryl.dsl.boilerplate.GroupBySignatures with org.squeryl.dsl.boilerplate.ComputeMeasuresSignaturesFromStartOrWhereState { self: org.squeryl.dsl.fsm.QueryElements[_] => 
    def select[R >: Nothing <: Any](yieldClosure: => R): org.squeryl.dsl.fsm.SelectState[R]
  };
  abstract trait QueryElements[Cond >: Nothing <: Any] extends Object with org.squeryl.dsl.fsm.WhereState[Cond] with org.squeryl.dsl.boilerplate.ComputeMeasuresSignaturesFromStartOrWhereState with org.squeryl.dsl.fsm.StartState {
    def /*QueryElements*/$init$(): Unit = {
      ()
    };
    private[squeryl] def whereClause: Option[() => org.squeryl.dsl.ast.LogicalBoolean] = scala.None
  };
  abstract trait SelectState[R >: Nothing <: Any] extends Object with org.squeryl.dsl.QueryYield[R] with org.squeryl.dsl.boilerplate.OrderBySignatures[R] { self: org.squeryl.dsl.fsm.BaseQueryYield[R] => 
    <empty>
  };
  abstract trait ComputeStateStartOrWhereState[M >: Nothing <: Any] extends Object with org.squeryl.dsl.QueryYield[org.squeryl.dsl.Measures[M]] with org.squeryl.dsl.boilerplate.OrderBySignatures[org.squeryl.dsl.Measures[M]] { self: org.squeryl.dsl.fsm.MeasuresQueryYield[M] => 
    <empty>
  };
  abstract trait WhereState[Cond >: Nothing <: Any] extends Object with org.squeryl.dsl.boilerplate.GroupBySignatures with org.squeryl.dsl.boilerplate.ComputeMeasuresSignaturesFromStartOrWhereState { self: org.squeryl.dsl.fsm.QueryElements[_] => 
    def /*WhereState*/$init$(): Unit = {
      ()
    };
    def select[R >: Nothing <: Any](yieldClosure: => R): org.squeryl.dsl.fsm.SelectState[R] = new org.squeryl.dsl.fsm.BaseQueryYield[R](this, (() => yieldClosure));
    def set(updateAssignments: org.squeryl.dsl.ast.UpdateAssignment*)(implicit cond: =:=[Cond,org.squeryl.dsl.fsm.Conditioned]): org.squeryl.dsl.ast.UpdateStatement = new org.squeryl.dsl.ast.UpdateStatement(WhereState.this.whereClause, updateAssignments);
    def setAll(updateAssignments: org.squeryl.dsl.ast.UpdateAssignment*)(implicit cond: =:=[Cond,org.squeryl.dsl.fsm.Unconditioned]): org.squeryl.dsl.ast.UpdateStatement = new org.squeryl.dsl.ast.UpdateStatement(WhereState.this.whereClause, updateAssignments)
  };
  abstract trait HavingState[G >: Nothing <: Any] extends Object with org.squeryl.dsl.boilerplate.ComputeMeasuresSignaturesFromGroupByState[G] { self: org.squeryl.dsl.fsm.GroupQueryYield[G] => 
    <empty>
  };
  abstract trait ComputeStateFromGroupByState[K >: Nothing <: Any, M >: Nothing <: Any] extends Object with org.squeryl.dsl.QueryYield[org.squeryl.dsl.GroupWithMeasures[K,M]] with org.squeryl.dsl.boilerplate.OrderBySignatures[org.squeryl.dsl.GroupWithMeasures[K,M]] { self: org.squeryl.dsl.fsm.GroupWithMeasuresQueryYield[K,M] => 
    <empty>
  };
  abstract trait GroupByState[K >: Nothing <: Any] extends Object with org.squeryl.dsl.QueryYield[org.squeryl.dsl.Group[K]] with org.squeryl.dsl.boilerplate.ComputeMeasuresSignaturesFromGroupByState[K] with org.squeryl.dsl.boilerplate.OrderBySignatures[org.squeryl.dsl.Group[K]] { self: org.squeryl.dsl.fsm.GroupQueryYield[K] => 
    def /*GroupByState*/$init$(): Unit = {
      ()
    };
    def having(b: => org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.fsm.GroupQueryYield[K] = {
      GroupByState.this._havingClause_=(scala.Some.apply[() => org.squeryl.dsl.ast.LogicalBoolean]((() => b)));
      this
    }
  }
}