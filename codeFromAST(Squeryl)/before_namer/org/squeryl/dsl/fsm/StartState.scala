package org.squeryl.dsl.fsm {
  import org.squeryl.dsl._;
  import ast.{LogicalBoolean, UpdateStatement, UpdateAssignment};
  import boilerplate.{ComputeMeasuresSignaturesFromStartOrWhereState, ComputeMeasuresSignaturesFromGroupByState, GroupBySignatures, OrderBySignatures};
  sealed abstract class Conditioned extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  sealed abstract class Unconditioned extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  abstract trait StartState extends GroupBySignatures with ComputeMeasuresSignaturesFromStartOrWhereState { self: QueryElements[_$1] forSome { 
    <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def select[R >: _root_.scala.Nothing <: _root_.scala.Any](yieldClosure: _root_.scala.<byname>[R]): SelectState[R]
  };
  abstract trait QueryElements[Cond >: _root_.scala.Nothing <: _root_.scala.Any] extends WhereState[Cond] with ComputeMeasuresSignaturesFromStartOrWhereState with StartState {
    def $init$() = {
      ()
    };
    private[squeryl] def whereClause: Option[_root_.scala.Function0[LogicalBoolean]] = None
  };
  abstract trait SelectState[R >: _root_.scala.Nothing <: _root_.scala.Any] extends QueryYield[R] with OrderBySignatures[R] { self: BaseQueryYield[R] => 
    <empty>
  };
  abstract trait ComputeStateStartOrWhereState[M >: _root_.scala.Nothing <: _root_.scala.Any] extends QueryYield[Measures[M]] with OrderBySignatures[Measures[M]] { self: MeasuresQueryYield[M] => 
    <empty>
  };
  abstract trait WhereState[Cond >: _root_.scala.Nothing <: _root_.scala.Any] extends GroupBySignatures with ComputeMeasuresSignaturesFromStartOrWhereState { self: QueryElements[_$2] forSome { 
    <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    def select[R >: _root_.scala.Nothing <: _root_.scala.Any](yieldClosure: _root_.scala.<byname>[R]): SelectState[R] = new BaseQueryYield[R](this, (yieldClosure: (() => <empty>)));
    def set(updateAssignments: _root_.scala.<repeated>[UpdateAssignment])(implicit cond: $eq$colon$eq[Cond, Conditioned]) = new UpdateStatement(whereClause, updateAssignments);
    def setAll(updateAssignments: _root_.scala.<repeated>[UpdateAssignment])(implicit cond: $eq$colon$eq[Cond, Unconditioned]) = new UpdateStatement(whereClause, updateAssignments)
  };
  abstract trait HavingState[G >: _root_.scala.Nothing <: _root_.scala.Any] extends ComputeMeasuresSignaturesFromGroupByState[G] { self: GroupQueryYield[G] => 
    <empty>
  };
  abstract trait ComputeStateFromGroupByState[K >: _root_.scala.Nothing <: _root_.scala.Any, M >: _root_.scala.Nothing <: _root_.scala.Any] extends QueryYield[GroupWithMeasures[K, M]] with OrderBySignatures[GroupWithMeasures[K, M]] { self: GroupWithMeasuresQueryYield[K, M] => 
    <empty>
  };
  abstract trait GroupByState[K >: _root_.scala.Nothing <: _root_.scala.Any] extends QueryYield[Group[K]] with ComputeMeasuresSignaturesFromGroupByState[K] with OrderBySignatures[Group[K]] { self: GroupQueryYield[K] => 
    def $init$() = {
      ()
    };
    def having(b: _root_.scala.<byname>[LogicalBoolean]) = {
      _havingClause = Some((b: (() => <empty>)));
      this
    }
  }
}