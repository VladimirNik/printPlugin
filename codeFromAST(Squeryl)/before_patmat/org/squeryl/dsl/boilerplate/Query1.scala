package org.squeryl.dsl.boilerplate {
  import java.sql.ResultSet;
  import org.squeryl.Queryable;
  import org.squeryl.internals.ResultSetMapper;
  import org.squeryl.dsl.{QueryYield, AbstractQuery};
  class Query1[T1 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val f: T1 => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], f: T1 => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query1[T1,R] = {
      Query1.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query1.this.SubQueryable[T1] = Query1.this.createSubQueryable[T1](Query1.this.t1);
    <stable> <accessor> def sq1: Query1.this.SubQueryable[T1] = Query1.this.sq1;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query1[T1,R] = new org.squeryl.dsl.boilerplate.Query1[T1,R](Query1.this.t1, Query1.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query1.this.f.apply(Query1.this.sq1.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query1.this.buildAst(Query1.this.f.apply(Query1.this.sq1.sample), Query1.this.sq1);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query1.this.ast
  };
  class Query2[T1 >: Nothing <: Any, T2 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val f: (T1, T2) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], f: (T1, T2) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query2[T1,T2,R] = {
      Query2.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query2.this.SubQueryable[T1] = Query2.this.createSubQueryable[T1](Query2.this.t1);
    <stable> <accessor> def sq1: Query2.this.SubQueryable[T1] = Query2.this.sq1;
    private[this] val sq2: Query2.this.SubQueryable[T2] = Query2.this.createSubQueryable[T2](Query2.this.t2);
    <stable> <accessor> def sq2: Query2.this.SubQueryable[T2] = Query2.this.sq2;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query2[T1,T2,R] = new org.squeryl.dsl.boilerplate.Query2[T1,T2,R](Query2.this.t1, Query2.this.t2, Query2.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query2.this.f.apply(Query2.this.sq1.give(rs), Query2.this.sq2.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query2.this.buildAst(Query2.this.f.apply(Query2.this.sq1.sample, Query2.this.sq2.sample), Query2.this.sq1, Query2.this.sq2);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query2.this.ast
  };
  class Query3[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val t3: org.squeryl.Queryable[T3] = _;
    <paramaccessor> private[this] val f: (T1, T2, T3) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], t3: org.squeryl.Queryable[T3], f: (T1, T2, T3) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query3[T1,T2,T3,R] = {
      Query3.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query3.this.SubQueryable[T1] = Query3.this.createSubQueryable[T1](Query3.this.t1);
    <stable> <accessor> def sq1: Query3.this.SubQueryable[T1] = Query3.this.sq1;
    private[this] val sq2: Query3.this.SubQueryable[T2] = Query3.this.createSubQueryable[T2](Query3.this.t2);
    <stable> <accessor> def sq2: Query3.this.SubQueryable[T2] = Query3.this.sq2;
    private[this] val sq3: Query3.this.SubQueryable[T3] = Query3.this.createSubQueryable[T3](Query3.this.t3);
    <stable> <accessor> def sq3: Query3.this.SubQueryable[T3] = Query3.this.sq3;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query3[T1,T2,T3,R] = new org.squeryl.dsl.boilerplate.Query3[T1,T2,T3,R](Query3.this.t1, Query3.this.t2, Query3.this.t3, Query3.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query3.this.f.apply(Query3.this.sq1.give(rs), Query3.this.sq2.give(rs), Query3.this.sq3.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query3.this.buildAst(Query3.this.f.apply(Query3.this.sq1.sample, Query3.this.sq2.sample, Query3.this.sq3.sample), Query3.this.sq1, Query3.this.sq2, Query3.this.sq3);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query3.this.ast
  };
  class Query4[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val t3: org.squeryl.Queryable[T3] = _;
    <paramaccessor> private[this] val t4: org.squeryl.Queryable[T4] = _;
    <paramaccessor> private[this] val f: (T1, T2, T3, T4) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], t3: org.squeryl.Queryable[T3], t4: org.squeryl.Queryable[T4], f: (T1, T2, T3, T4) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query4[T1,T2,T3,T4,R] = {
      Query4.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query4.this.SubQueryable[T1] = Query4.this.createSubQueryable[T1](Query4.this.t1);
    <stable> <accessor> def sq1: Query4.this.SubQueryable[T1] = Query4.this.sq1;
    private[this] val sq2: Query4.this.SubQueryable[T2] = Query4.this.createSubQueryable[T2](Query4.this.t2);
    <stable> <accessor> def sq2: Query4.this.SubQueryable[T2] = Query4.this.sq2;
    private[this] val sq3: Query4.this.SubQueryable[T3] = Query4.this.createSubQueryable[T3](Query4.this.t3);
    <stable> <accessor> def sq3: Query4.this.SubQueryable[T3] = Query4.this.sq3;
    private[this] val sq4: Query4.this.SubQueryable[T4] = Query4.this.createSubQueryable[T4](Query4.this.t4);
    <stable> <accessor> def sq4: Query4.this.SubQueryable[T4] = Query4.this.sq4;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query4[T1,T2,T3,T4,R] = new org.squeryl.dsl.boilerplate.Query4[T1,T2,T3,T4,R](Query4.this.t1, Query4.this.t2, Query4.this.t3, Query4.this.t4, Query4.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query4.this.f.apply(Query4.this.sq1.give(rs), Query4.this.sq2.give(rs), Query4.this.sq3.give(rs), Query4.this.sq4.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query4.this.buildAst(Query4.this.f.apply(Query4.this.sq1.sample, Query4.this.sq2.sample, Query4.this.sq3.sample, Query4.this.sq4.sample), Query4.this.sq1, Query4.this.sq2, Query4.this.sq3, Query4.this.sq4);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query4.this.ast
  };
  class Query5[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val t3: org.squeryl.Queryable[T3] = _;
    <paramaccessor> private[this] val t4: org.squeryl.Queryable[T4] = _;
    <paramaccessor> private[this] val t5: org.squeryl.Queryable[T5] = _;
    <paramaccessor> private[this] val f: (T1, T2, T3, T4, T5) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], t3: org.squeryl.Queryable[T3], t4: org.squeryl.Queryable[T4], t5: org.squeryl.Queryable[T5], f: (T1, T2, T3, T4, T5) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query5[T1,T2,T3,T4,T5,R] = {
      Query5.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query5.this.SubQueryable[T1] = Query5.this.createSubQueryable[T1](Query5.this.t1);
    <stable> <accessor> def sq1: Query5.this.SubQueryable[T1] = Query5.this.sq1;
    private[this] val sq2: Query5.this.SubQueryable[T2] = Query5.this.createSubQueryable[T2](Query5.this.t2);
    <stable> <accessor> def sq2: Query5.this.SubQueryable[T2] = Query5.this.sq2;
    private[this] val sq3: Query5.this.SubQueryable[T3] = Query5.this.createSubQueryable[T3](Query5.this.t3);
    <stable> <accessor> def sq3: Query5.this.SubQueryable[T3] = Query5.this.sq3;
    private[this] val sq4: Query5.this.SubQueryable[T4] = Query5.this.createSubQueryable[T4](Query5.this.t4);
    <stable> <accessor> def sq4: Query5.this.SubQueryable[T4] = Query5.this.sq4;
    private[this] val sq5: Query5.this.SubQueryable[T5] = Query5.this.createSubQueryable[T5](Query5.this.t5);
    <stable> <accessor> def sq5: Query5.this.SubQueryable[T5] = Query5.this.sq5;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query5[T1,T2,T3,T4,T5,R] = new org.squeryl.dsl.boilerplate.Query5[T1,T2,T3,T4,T5,R](Query5.this.t1, Query5.this.t2, Query5.this.t3, Query5.this.t4, Query5.this.t5, Query5.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query5.this.f.apply(Query5.this.sq1.give(rs), Query5.this.sq2.give(rs), Query5.this.sq3.give(rs), Query5.this.sq4.give(rs), Query5.this.sq5.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query5.this.buildAst(Query5.this.f.apply(Query5.this.sq1.sample, Query5.this.sq2.sample, Query5.this.sq3.sample, Query5.this.sq4.sample, Query5.this.sq5.sample), Query5.this.sq1, Query5.this.sq2, Query5.this.sq3, Query5.this.sq4, Query5.this.sq5);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query5.this.ast
  };
  class Query6[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, T6 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val t3: org.squeryl.Queryable[T3] = _;
    <paramaccessor> private[this] val t4: org.squeryl.Queryable[T4] = _;
    <paramaccessor> private[this] val t5: org.squeryl.Queryable[T5] = _;
    <paramaccessor> private[this] val t6: org.squeryl.Queryable[T6] = _;
    <paramaccessor> private[this] val f: (T1, T2, T3, T4, T5, T6) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], t3: org.squeryl.Queryable[T3], t4: org.squeryl.Queryable[T4], t5: org.squeryl.Queryable[T5], t6: org.squeryl.Queryable[T6], f: (T1, T2, T3, T4, T5, T6) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query6[T1,T2,T3,T4,T5,T6,R] = {
      Query6.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query6.this.SubQueryable[T1] = Query6.this.createSubQueryable[T1](Query6.this.t1);
    <stable> <accessor> def sq1: Query6.this.SubQueryable[T1] = Query6.this.sq1;
    private[this] val sq2: Query6.this.SubQueryable[T2] = Query6.this.createSubQueryable[T2](Query6.this.t2);
    <stable> <accessor> def sq2: Query6.this.SubQueryable[T2] = Query6.this.sq2;
    private[this] val sq3: Query6.this.SubQueryable[T3] = Query6.this.createSubQueryable[T3](Query6.this.t3);
    <stable> <accessor> def sq3: Query6.this.SubQueryable[T3] = Query6.this.sq3;
    private[this] val sq4: Query6.this.SubQueryable[T4] = Query6.this.createSubQueryable[T4](Query6.this.t4);
    <stable> <accessor> def sq4: Query6.this.SubQueryable[T4] = Query6.this.sq4;
    private[this] val sq5: Query6.this.SubQueryable[T5] = Query6.this.createSubQueryable[T5](Query6.this.t5);
    <stable> <accessor> def sq5: Query6.this.SubQueryable[T5] = Query6.this.sq5;
    private[this] val sq6: Query6.this.SubQueryable[T6] = Query6.this.createSubQueryable[T6](Query6.this.t6);
    <stable> <accessor> def sq6: Query6.this.SubQueryable[T6] = Query6.this.sq6;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query6[T1,T2,T3,T4,T5,T6,R] = new org.squeryl.dsl.boilerplate.Query6[T1,T2,T3,T4,T5,T6,R](Query6.this.t1, Query6.this.t2, Query6.this.t3, Query6.this.t4, Query6.this.t5, Query6.this.t6, Query6.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query6.this.f.apply(Query6.this.sq1.give(rs), Query6.this.sq2.give(rs), Query6.this.sq3.give(rs), Query6.this.sq4.give(rs), Query6.this.sq5.give(rs), Query6.this.sq6.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query6.this.buildAst(Query6.this.f.apply(Query6.this.sq1.sample, Query6.this.sq2.sample, Query6.this.sq3.sample, Query6.this.sq4.sample, Query6.this.sq5.sample, Query6.this.sq6.sample), Query6.this.sq1, Query6.this.sq2, Query6.this.sq3, Query6.this.sq4, Query6.this.sq5, Query6.this.sq6);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query6.this.ast
  };
  class Query7[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, T6 >: Nothing <: Any, T7 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val t3: org.squeryl.Queryable[T3] = _;
    <paramaccessor> private[this] val t4: org.squeryl.Queryable[T4] = _;
    <paramaccessor> private[this] val t5: org.squeryl.Queryable[T5] = _;
    <paramaccessor> private[this] val t6: org.squeryl.Queryable[T6] = _;
    <paramaccessor> private[this] val t7: org.squeryl.Queryable[T7] = _;
    <paramaccessor> private[this] val f: (T1, T2, T3, T4, T5, T6, T7) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], t3: org.squeryl.Queryable[T3], t4: org.squeryl.Queryable[T4], t5: org.squeryl.Queryable[T5], t6: org.squeryl.Queryable[T6], t7: org.squeryl.Queryable[T7], f: (T1, T2, T3, T4, T5, T6, T7) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query7[T1,T2,T3,T4,T5,T6,T7,R] = {
      Query7.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query7.this.SubQueryable[T1] = Query7.this.createSubQueryable[T1](Query7.this.t1);
    <stable> <accessor> def sq1: Query7.this.SubQueryable[T1] = Query7.this.sq1;
    private[this] val sq2: Query7.this.SubQueryable[T2] = Query7.this.createSubQueryable[T2](Query7.this.t2);
    <stable> <accessor> def sq2: Query7.this.SubQueryable[T2] = Query7.this.sq2;
    private[this] val sq3: Query7.this.SubQueryable[T3] = Query7.this.createSubQueryable[T3](Query7.this.t3);
    <stable> <accessor> def sq3: Query7.this.SubQueryable[T3] = Query7.this.sq3;
    private[this] val sq4: Query7.this.SubQueryable[T4] = Query7.this.createSubQueryable[T4](Query7.this.t4);
    <stable> <accessor> def sq4: Query7.this.SubQueryable[T4] = Query7.this.sq4;
    private[this] val sq5: Query7.this.SubQueryable[T5] = Query7.this.createSubQueryable[T5](Query7.this.t5);
    <stable> <accessor> def sq5: Query7.this.SubQueryable[T5] = Query7.this.sq5;
    private[this] val sq6: Query7.this.SubQueryable[T6] = Query7.this.createSubQueryable[T6](Query7.this.t6);
    <stable> <accessor> def sq6: Query7.this.SubQueryable[T6] = Query7.this.sq6;
    private[this] val sq7: Query7.this.SubQueryable[T7] = Query7.this.createSubQueryable[T7](Query7.this.t7);
    <stable> <accessor> def sq7: Query7.this.SubQueryable[T7] = Query7.this.sq7;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query7[T1,T2,T3,T4,T5,T6,T7,R] = new org.squeryl.dsl.boilerplate.Query7[T1,T2,T3,T4,T5,T6,T7,R](Query7.this.t1, Query7.this.t2, Query7.this.t3, Query7.this.t4, Query7.this.t5, Query7.this.t6, Query7.this.t7, Query7.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query7.this.f.apply(Query7.this.sq1.give(rs), Query7.this.sq2.give(rs), Query7.this.sq3.give(rs), Query7.this.sq4.give(rs), Query7.this.sq5.give(rs), Query7.this.sq6.give(rs), Query7.this.sq7.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query7.this.buildAst(Query7.this.f.apply(Query7.this.sq1.sample, Query7.this.sq2.sample, Query7.this.sq3.sample, Query7.this.sq4.sample, Query7.this.sq5.sample, Query7.this.sq6.sample, Query7.this.sq7.sample), Query7.this.sq1, Query7.this.sq2, Query7.this.sq3, Query7.this.sq4, Query7.this.sq5, Query7.this.sq6, Query7.this.sq7);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query7.this.ast
  };
  class Query8[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, T6 >: Nothing <: Any, T7 >: Nothing <: Any, T8 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val t3: org.squeryl.Queryable[T3] = _;
    <paramaccessor> private[this] val t4: org.squeryl.Queryable[T4] = _;
    <paramaccessor> private[this] val t5: org.squeryl.Queryable[T5] = _;
    <paramaccessor> private[this] val t6: org.squeryl.Queryable[T6] = _;
    <paramaccessor> private[this] val t7: org.squeryl.Queryable[T7] = _;
    <paramaccessor> private[this] val t8: org.squeryl.Queryable[T8] = _;
    <paramaccessor> private[this] val f: (T1, T2, T3, T4, T5, T6, T7, T8) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], t3: org.squeryl.Queryable[T3], t4: org.squeryl.Queryable[T4], t5: org.squeryl.Queryable[T5], t6: org.squeryl.Queryable[T6], t7: org.squeryl.Queryable[T7], t8: org.squeryl.Queryable[T8], f: (T1, T2, T3, T4, T5, T6, T7, T8) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query8[T1,T2,T3,T4,T5,T6,T7,T8,R] = {
      Query8.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query8.this.SubQueryable[T1] = Query8.this.createSubQueryable[T1](Query8.this.t1);
    <stable> <accessor> def sq1: Query8.this.SubQueryable[T1] = Query8.this.sq1;
    private[this] val sq2: Query8.this.SubQueryable[T2] = Query8.this.createSubQueryable[T2](Query8.this.t2);
    <stable> <accessor> def sq2: Query8.this.SubQueryable[T2] = Query8.this.sq2;
    private[this] val sq3: Query8.this.SubQueryable[T3] = Query8.this.createSubQueryable[T3](Query8.this.t3);
    <stable> <accessor> def sq3: Query8.this.SubQueryable[T3] = Query8.this.sq3;
    private[this] val sq4: Query8.this.SubQueryable[T4] = Query8.this.createSubQueryable[T4](Query8.this.t4);
    <stable> <accessor> def sq4: Query8.this.SubQueryable[T4] = Query8.this.sq4;
    private[this] val sq5: Query8.this.SubQueryable[T5] = Query8.this.createSubQueryable[T5](Query8.this.t5);
    <stable> <accessor> def sq5: Query8.this.SubQueryable[T5] = Query8.this.sq5;
    private[this] val sq6: Query8.this.SubQueryable[T6] = Query8.this.createSubQueryable[T6](Query8.this.t6);
    <stable> <accessor> def sq6: Query8.this.SubQueryable[T6] = Query8.this.sq6;
    private[this] val sq7: Query8.this.SubQueryable[T7] = Query8.this.createSubQueryable[T7](Query8.this.t7);
    <stable> <accessor> def sq7: Query8.this.SubQueryable[T7] = Query8.this.sq7;
    private[this] val sq8: Query8.this.SubQueryable[T8] = Query8.this.createSubQueryable[T8](Query8.this.t8);
    <stable> <accessor> def sq8: Query8.this.SubQueryable[T8] = Query8.this.sq8;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query8[T1,T2,T3,T4,T5,T6,T7,T8,R] = new org.squeryl.dsl.boilerplate.Query8[T1,T2,T3,T4,T5,T6,T7,T8,R](Query8.this.t1, Query8.this.t2, Query8.this.t3, Query8.this.t4, Query8.this.t5, Query8.this.t6, Query8.this.t7, Query8.this.t8, Query8.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query8.this.f.apply(Query8.this.sq1.give(rs), Query8.this.sq2.give(rs), Query8.this.sq3.give(rs), Query8.this.sq4.give(rs), Query8.this.sq5.give(rs), Query8.this.sq6.give(rs), Query8.this.sq7.give(rs), Query8.this.sq8.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query8.this.buildAst(Query8.this.f.apply(Query8.this.sq1.sample, Query8.this.sq2.sample, Query8.this.sq3.sample, Query8.this.sq4.sample, Query8.this.sq5.sample, Query8.this.sq6.sample, Query8.this.sq7.sample, Query8.this.sq8.sample), Query8.this.sq1, Query8.this.sq2, Query8.this.sq3, Query8.this.sq4, Query8.this.sq5, Query8.this.sq6, Query8.this.sq7, Query8.this.sq8);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query8.this.ast
  };
  class Query9[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, T6 >: Nothing <: Any, T7 >: Nothing <: Any, T8 >: Nothing <: Any, T9 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val t3: org.squeryl.Queryable[T3] = _;
    <paramaccessor> private[this] val t4: org.squeryl.Queryable[T4] = _;
    <paramaccessor> private[this] val t5: org.squeryl.Queryable[T5] = _;
    <paramaccessor> private[this] val t6: org.squeryl.Queryable[T6] = _;
    <paramaccessor> private[this] val t7: org.squeryl.Queryable[T7] = _;
    <paramaccessor> private[this] val t8: org.squeryl.Queryable[T8] = _;
    <paramaccessor> private[this] val t9: org.squeryl.Queryable[T9] = _;
    <paramaccessor> private[this] val f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], t3: org.squeryl.Queryable[T3], t4: org.squeryl.Queryable[T4], t5: org.squeryl.Queryable[T5], t6: org.squeryl.Queryable[T6], t7: org.squeryl.Queryable[T7], t8: org.squeryl.Queryable[T8], t9: org.squeryl.Queryable[T9], f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query9[T1,T2,T3,T4,T5,T6,T7,T8,T9,R] = {
      Query9.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query9.this.SubQueryable[T1] = Query9.this.createSubQueryable[T1](Query9.this.t1);
    <stable> <accessor> def sq1: Query9.this.SubQueryable[T1] = Query9.this.sq1;
    private[this] val sq2: Query9.this.SubQueryable[T2] = Query9.this.createSubQueryable[T2](Query9.this.t2);
    <stable> <accessor> def sq2: Query9.this.SubQueryable[T2] = Query9.this.sq2;
    private[this] val sq3: Query9.this.SubQueryable[T3] = Query9.this.createSubQueryable[T3](Query9.this.t3);
    <stable> <accessor> def sq3: Query9.this.SubQueryable[T3] = Query9.this.sq3;
    private[this] val sq4: Query9.this.SubQueryable[T4] = Query9.this.createSubQueryable[T4](Query9.this.t4);
    <stable> <accessor> def sq4: Query9.this.SubQueryable[T4] = Query9.this.sq4;
    private[this] val sq5: Query9.this.SubQueryable[T5] = Query9.this.createSubQueryable[T5](Query9.this.t5);
    <stable> <accessor> def sq5: Query9.this.SubQueryable[T5] = Query9.this.sq5;
    private[this] val sq6: Query9.this.SubQueryable[T6] = Query9.this.createSubQueryable[T6](Query9.this.t6);
    <stable> <accessor> def sq6: Query9.this.SubQueryable[T6] = Query9.this.sq6;
    private[this] val sq7: Query9.this.SubQueryable[T7] = Query9.this.createSubQueryable[T7](Query9.this.t7);
    <stable> <accessor> def sq7: Query9.this.SubQueryable[T7] = Query9.this.sq7;
    private[this] val sq8: Query9.this.SubQueryable[T8] = Query9.this.createSubQueryable[T8](Query9.this.t8);
    <stable> <accessor> def sq8: Query9.this.SubQueryable[T8] = Query9.this.sq8;
    private[this] val sq9: Query9.this.SubQueryable[T9] = Query9.this.createSubQueryable[T9](Query9.this.t9);
    <stable> <accessor> def sq9: Query9.this.SubQueryable[T9] = Query9.this.sq9;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query9[T1,T2,T3,T4,T5,T6,T7,T8,T9,R] = new org.squeryl.dsl.boilerplate.Query9[T1,T2,T3,T4,T5,T6,T7,T8,T9,R](Query9.this.t1, Query9.this.t2, Query9.this.t3, Query9.this.t4, Query9.this.t5, Query9.this.t6, Query9.this.t7, Query9.this.t8, Query9.this.t9, Query9.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query9.this.f.apply(Query9.this.sq1.give(rs), Query9.this.sq2.give(rs), Query9.this.sq3.give(rs), Query9.this.sq4.give(rs), Query9.this.sq5.give(rs), Query9.this.sq6.give(rs), Query9.this.sq7.give(rs), Query9.this.sq8.give(rs), Query9.this.sq9.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query9.this.buildAst(Query9.this.f.apply(Query9.this.sq1.sample, Query9.this.sq2.sample, Query9.this.sq3.sample, Query9.this.sq4.sample, Query9.this.sq5.sample, Query9.this.sq6.sample, Query9.this.sq7.sample, Query9.this.sq8.sample, Query9.this.sq9.sample), Query9.this.sq1, Query9.this.sq2, Query9.this.sq3, Query9.this.sq4, Query9.this.sq5, Query9.this.sq6, Query9.this.sq7, Query9.this.sq8, Query9.this.sq9);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query9.this.ast
  };
  class Query10[T1 >: Nothing <: Any, T2 >: Nothing <: Any, T3 >: Nothing <: Any, T4 >: Nothing <: Any, T5 >: Nothing <: Any, T6 >: Nothing <: Any, T7 >: Nothing <: Any, T8 >: Nothing <: Any, T9 >: Nothing <: Any, T10 >: Nothing <: Any, R >: Nothing <: Any] extends org.squeryl.dsl.AbstractQuery[R] {
    <paramaccessor> private[this] val t1: org.squeryl.Queryable[T1] = _;
    <paramaccessor> private[this] val t2: org.squeryl.Queryable[T2] = _;
    <paramaccessor> private[this] val t3: org.squeryl.Queryable[T3] = _;
    <paramaccessor> private[this] val t4: org.squeryl.Queryable[T4] = _;
    <paramaccessor> private[this] val t5: org.squeryl.Queryable[T5] = _;
    <paramaccessor> private[this] val t6: org.squeryl.Queryable[T6] = _;
    <paramaccessor> private[this] val t7: org.squeryl.Queryable[T7] = _;
    <paramaccessor> private[this] val t8: org.squeryl.Queryable[T8] = _;
    <paramaccessor> private[this] val t9: org.squeryl.Queryable[T9] = _;
    <paramaccessor> private[this] val t10: org.squeryl.Queryable[T10] = _;
    <paramaccessor> private[this] val f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => org.squeryl.dsl.QueryYield[R] = _;
    <paramaccessor> private[this] val isRoot: Boolean = _;
    def <init>(t1: org.squeryl.Queryable[T1], t2: org.squeryl.Queryable[T2], t3: org.squeryl.Queryable[T3], t4: org.squeryl.Queryable[T4], t5: org.squeryl.Queryable[T5], t6: org.squeryl.Queryable[T6], t7: org.squeryl.Queryable[T7], t8: org.squeryl.Queryable[T8], t9: org.squeryl.Queryable[T9], t10: org.squeryl.Queryable[T10], f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => org.squeryl.dsl.QueryYield[R], isRoot: Boolean): org.squeryl.dsl.boilerplate.Query10[T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R] = {
      Query10.super.<init>(isRoot);
      ()
    };
    private[this] val sq1: Query10.this.SubQueryable[T1] = Query10.this.createSubQueryable[T1](Query10.this.t1);
    <stable> <accessor> def sq1: Query10.this.SubQueryable[T1] = Query10.this.sq1;
    private[this] val sq2: Query10.this.SubQueryable[T2] = Query10.this.createSubQueryable[T2](Query10.this.t2);
    <stable> <accessor> def sq2: Query10.this.SubQueryable[T2] = Query10.this.sq2;
    private[this] val sq3: Query10.this.SubQueryable[T3] = Query10.this.createSubQueryable[T3](Query10.this.t3);
    <stable> <accessor> def sq3: Query10.this.SubQueryable[T3] = Query10.this.sq3;
    private[this] val sq4: Query10.this.SubQueryable[T4] = Query10.this.createSubQueryable[T4](Query10.this.t4);
    <stable> <accessor> def sq4: Query10.this.SubQueryable[T4] = Query10.this.sq4;
    private[this] val sq5: Query10.this.SubQueryable[T5] = Query10.this.createSubQueryable[T5](Query10.this.t5);
    <stable> <accessor> def sq5: Query10.this.SubQueryable[T5] = Query10.this.sq5;
    private[this] val sq6: Query10.this.SubQueryable[T6] = Query10.this.createSubQueryable[T6](Query10.this.t6);
    <stable> <accessor> def sq6: Query10.this.SubQueryable[T6] = Query10.this.sq6;
    private[this] val sq7: Query10.this.SubQueryable[T7] = Query10.this.createSubQueryable[T7](Query10.this.t7);
    <stable> <accessor> def sq7: Query10.this.SubQueryable[T7] = Query10.this.sq7;
    private[this] val sq8: Query10.this.SubQueryable[T8] = Query10.this.createSubQueryable[T8](Query10.this.t8);
    <stable> <accessor> def sq8: Query10.this.SubQueryable[T8] = Query10.this.sq8;
    private[this] val sq9: Query10.this.SubQueryable[T9] = Query10.this.createSubQueryable[T9](Query10.this.t9);
    <stable> <accessor> def sq9: Query10.this.SubQueryable[T9] = Query10.this.sq9;
    private[this] val sq10: Query10.this.SubQueryable[T10] = Query10.this.createSubQueryable[T10](Query10.this.t10);
    <stable> <accessor> def sq10: Query10.this.SubQueryable[T10] = Query10.this.sq10;
    def createCopy(root: Boolean): org.squeryl.dsl.boilerplate.Query10[T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R] = new org.squeryl.dsl.boilerplate.Query10[T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,R](Query10.this.t1, Query10.this.t2, Query10.this.t3, Query10.this.t4, Query10.this.t5, Query10.this.t6, Query10.this.t7, Query10.this.t8, Query10.this.t9, Query10.this.t10, Query10.this.f, root);
    def invokeYield(rsm: org.squeryl.internals.ResultSetMapper, rs: java.sql.ResultSet): R = Query10.this.f.apply(Query10.this.sq1.give(rs), Query10.this.sq2.give(rs), Query10.this.sq3.give(rs), Query10.this.sq4.give(rs), Query10.this.sq5.give(rs), Query10.this.sq6.give(rs), Query10.this.sq7.give(rs), Query10.this.sq8.give(rs), Query10.this.sq9.give(rs), Query10.this.sq10.give(rs)).invokeYield(rsm, rs);
    private[this] val ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query10.this.buildAst(Query10.this.f.apply(Query10.this.sq1.sample, Query10.this.sq2.sample, Query10.this.sq3.sample, Query10.this.sq4.sample, Query10.this.sq5.sample, Query10.this.sq6.sample, Query10.this.sq7.sample, Query10.this.sq8.sample, Query10.this.sq9.sample, Query10.this.sq10.sample), Query10.this.sq1, Query10.this.sq2, Query10.this.sq3, Query10.this.sq4, Query10.this.sq5, Query10.this.sq6, Query10.this.sq7, Query10.this.sq8, Query10.this.sq9, Query10.this.sq10);
    <stable> <accessor> def ast: org.squeryl.dsl.ast.QueryExpressionNode[R] = Query10.this.ast
  }
}