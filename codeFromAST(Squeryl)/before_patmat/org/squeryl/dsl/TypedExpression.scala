package org.squeryl.dsl {
  import org.squeryl.dsl.ast._;
  import org.squeryl.internals._;
  import org.squeryl.Session;
  import org.squeryl.Schema;
  import org.squeryl.internals.AttributeValidOnNumericalColumn;
  import org.squeryl.Query;
  import java.util.Date;
  import java.sql.ResultSet;
  import org.squeryl.internals.Utils;
  sealed abstract trait TNumeric extends scala.AnyRef;
  sealed abstract trait TOptionBigDecimal extends Object with org.squeryl.dsl.TNumeric;
  sealed abstract trait TBigDecimal extends Object with org.squeryl.dsl.TOptionBigDecimal with org.squeryl.dsl.TNonOption;
  sealed abstract trait TOptionDouble extends Object with org.squeryl.dsl.TOptionBigDecimal;
  sealed abstract trait TDouble extends Object with org.squeryl.dsl.TOptionDouble with org.squeryl.dsl.TBigDecimal with org.squeryl.dsl.TNonOption;
  sealed abstract trait TOptionLong extends Object with org.squeryl.dsl.TOptionDouble;
  sealed abstract trait TLong extends Object with org.squeryl.dsl.TOptionLong with org.squeryl.dsl.TDouble with org.squeryl.dsl.TNonOption;
  sealed abstract trait TOptionFloat extends Object with org.squeryl.dsl.TOptionDouble;
  sealed abstract trait TFloat extends Object with org.squeryl.dsl.TOptionFloat with org.squeryl.dsl.TDouble with org.squeryl.dsl.TNonOption;
  sealed abstract trait TOptionInt extends Object with org.squeryl.dsl.TOptionLong with org.squeryl.dsl.TOptionFloat;
  sealed abstract trait TInt extends Object with org.squeryl.dsl.TOptionInt with org.squeryl.dsl.TLong with org.squeryl.dsl.TNonOption with org.squeryl.dsl.TFloat;
  sealed abstract trait TOptionByte extends Object with org.squeryl.dsl.TOptionInt;
  sealed abstract trait TByte extends Object with org.squeryl.dsl.TOptionByte with org.squeryl.dsl.TInt with org.squeryl.dsl.TNonOption;
  sealed abstract trait TOption extends Object with org.squeryl.dsl.TOptionByte with org.squeryl.dsl.TOptionInt with org.squeryl.dsl.TOptionFloat with org.squeryl.dsl.TOptionLong with org.squeryl.dsl.TOptionDouble with org.squeryl.dsl.TOptionBigDecimal with org.squeryl.dsl.TOptionDate with org.squeryl.dsl.TOptionString with org.squeryl.dsl.TOptionTimestamp;
  sealed abstract trait TNumericLowerTypeBound extends Object with org.squeryl.dsl.TByte with org.squeryl.dsl.TInt with org.squeryl.dsl.TFloat with org.squeryl.dsl.TLong with org.squeryl.dsl.TDouble with org.squeryl.dsl.TBigDecimal;
  sealed abstract trait TNonOption extends scala.AnyRef;
  sealed abstract trait TOptionLowerBound extends Object with org.squeryl.dsl.TOptionByte with org.squeryl.dsl.TOptionInt with org.squeryl.dsl.TOptionFloat with org.squeryl.dsl.TOptionLong with org.squeryl.dsl.TOptionDouble with org.squeryl.dsl.TOptionBigDecimal with org.squeryl.dsl.TOptionDate with org.squeryl.dsl.TOptionString with org.squeryl.dsl.TOptionTimestamp;
  sealed abstract trait TEnumValue[A >: Nothing <: Any] extends scala.AnyRef;
  sealed abstract trait TOptionEnumValue[A >: Nothing <: Any] extends Object with org.squeryl.dsl.TEnumValue[A];
  sealed abstract trait TString extends Object with org.squeryl.dsl.TOptionString with org.squeryl.dsl.TNonOption;
  sealed abstract trait TDate extends Object with org.squeryl.dsl.TOptionDate with org.squeryl.dsl.TNonOption;
  sealed abstract trait TTimestamp extends Object with org.squeryl.dsl.TOptionTimestamp with org.squeryl.dsl.TNonOption;
  sealed abstract trait TByteArray extends Object with org.squeryl.dsl.TOptionByteArray with org.squeryl.dsl.TNonOption;
  sealed abstract trait TIntArray extends Object with org.squeryl.dsl.TOptionIntArray with org.squeryl.dsl.TNonOption;
  sealed abstract trait TLongArray extends Object with org.squeryl.dsl.TOptionLongArray with org.squeryl.dsl.TNonOption;
  sealed abstract trait TDoubleArray extends Object with org.squeryl.dsl.TOptionDoubleArray with org.squeryl.dsl.TNonOption;
  sealed abstract trait TStringArray extends Object with org.squeryl.dsl.TOptionStringArray with org.squeryl.dsl.TNonOption;
  sealed abstract trait TOptionString extends scala.AnyRef;
  sealed abstract trait TOptionDate extends scala.AnyRef;
  sealed abstract trait TOptionTimestamp extends scala.AnyRef;
  sealed abstract trait TOptionByteArray extends scala.AnyRef;
  sealed abstract trait TOptionIntArray extends scala.AnyRef;
  sealed abstract trait TOptionLongArray extends scala.AnyRef;
  sealed abstract trait TOptionDoubleArray extends scala.AnyRef;
  sealed abstract trait TOptionStringArray extends scala.AnyRef;
  sealed abstract trait TBoolean extends Object with org.squeryl.dsl.TOptionBoolean with org.squeryl.dsl.TNonOption;
  sealed abstract trait TOptionBoolean extends scala.AnyRef;
  sealed abstract trait TUUID extends Object with org.squeryl.dsl.TOptionUUID with org.squeryl.dsl.TNonOption;
  sealed abstract trait TOptionUUID extends scala.AnyRef;
  @scala.annotation.implicitNotFound("The left side of the comparison (===, <>, between, ...) is not compatible with the right side.") sealed class CanCompare[A1 >: Nothing <: Any, A2 >: Nothing <: Any] extends scala.AnyRef {
    def <init>(): org.squeryl.dsl.CanCompare[A1,A2] = {
      CanCompare.super.<init>();
      ()
    }
  };
  abstract trait TypedExpression[A1 >: Nothing <: Any, T1 >: Nothing <: Any] extends Object with org.squeryl.dsl.ast.ExpressionNode { outer: org.squeryl.dsl.TypedExpression[A1,T1] => 
    def /*TypedExpression*/$init$(): Unit = {
      ()
    };
    def plus[T3 >: T1 <: org.squeryl.dsl.TNumeric, T2 >: Nothing <: T3, A2 >: Nothing <: Any, A3 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A3,T3]): org.squeryl.dsl.TypedExpression[A3,T3] = f.convert(new org.squeryl.dsl.ast.BinaryOperatorNode(this, e, "+", ast.this.BinaryOperatorNode.<init>$default$4));
    def times[T3 >: T1 <: org.squeryl.dsl.TNumeric, T2 >: Nothing <: T3, A2 >: Nothing <: Any, A3 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A3,T3]): org.squeryl.dsl.TypedExpression[A3,T3] = f.convert(new org.squeryl.dsl.ast.BinaryOperatorNode(this, e, "*", ast.this.BinaryOperatorNode.<init>$default$4));
    def minus[T3 >: T1 <: org.squeryl.dsl.TNumeric, T2 >: Nothing <: T3, A2 >: Nothing <: Any, A3 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A3,T3]): org.squeryl.dsl.TypedExpression[A3,T3] = f.convert(new org.squeryl.dsl.ast.BinaryOperatorNode(this, e, "-", ast.this.BinaryOperatorNode.<init>$default$4));
    def div[T3 >: T1 <: org.squeryl.dsl.TNumeric, T2 >: Nothing <: T3, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, T4 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A3,T3], tf: org.squeryl.dsl.Floatifier[T3,A4,T4]): org.squeryl.dsl.TypedExpression[A4,T4] = tf.floatify(new org.squeryl.dsl.ast.BinaryOperatorNode(this, e, "/", ast.this.BinaryOperatorNode.<init>$default$4));
    def +[T3 >: T1 <: org.squeryl.dsl.TNumeric, T2 >: Nothing <: T3, A2 >: Nothing <: Any, A3 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A3,T3]): org.squeryl.dsl.TypedExpression[A3,T3] = TypedExpression.this.plus[T3, T2, A2, A3](e)(f);
    def *[T3 >: T1 <: org.squeryl.dsl.TNumeric, T2 >: Nothing <: T3, A2 >: Nothing <: Any, A3 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A3,T3]): org.squeryl.dsl.TypedExpression[A3,T3] = TypedExpression.this.times[T3, T2, A2, A3](e)(f);
    def -[T3 >: T1 <: org.squeryl.dsl.TNumeric, T2 >: Nothing <: T3, A2 >: Nothing <: Any, A3 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A3,T3]): org.squeryl.dsl.TypedExpression[A3,T3] = TypedExpression.this.minus[T3, T2, A2, A3](e)(f);
    def /[T3 >: T1 <: org.squeryl.dsl.TNumeric, T2 >: Nothing <: T3, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, T4 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2])(implicit f: org.squeryl.dsl.TypedExpressionFactory[A3,T3], tf: org.squeryl.dsl.Floatifier[T3,A4,T4]): org.squeryl.dsl.TypedExpression[A4,T4] = tf.floatify(new org.squeryl.dsl.ast.BinaryOperatorNode(this, e, "/", ast.this.BinaryOperatorNode.<init>$default$4));
    def ===[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.EqualityExpression = new org.squeryl.dsl.ast.EqualityExpression(this, b);
    def <>[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, b, "<>", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def ===[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[org.squeryl.dsl.Measures[A2]])(implicit tef: org.squeryl.dsl.TypedExpressionFactory[A2,T2], ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, "=", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def <>[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[org.squeryl.dsl.Measures[A2]])(implicit tef: org.squeryl.dsl.TypedExpressionFactory[A2,T2], ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, "=", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def gt[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, b, ">", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def lt[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, b, "<", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def gte[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, b, ">=", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def lte[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, b, "<=", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def gt[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, ">", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def gte[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, ">=", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def lt[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, "<", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def lte[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, "<=", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def >[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = TypedExpression.this.gt[A2, Nothing](q)(cc);
    def >=[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = TypedExpression.this.gte[A2, Nothing](q)(cc);
    def <[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = TypedExpression.this.lt[A2, Nothing](q)(cc);
    def <=[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = TypedExpression.this.lte[A2, Nothing](q)(cc);
    def >[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = TypedExpression.this.gt[A2, T2](b)(ev);
    def <[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = TypedExpression.this.lt[A2, T2](b)(ev);
    def >=[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = TypedExpression.this.gte[A2, T2](b)(ev);
    def <=[A2 >: Nothing <: Any, T2 >: Nothing <: Any](b: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = TypedExpression.this.lte[A2, T2](b)(ev);
    def isNull: org.squeryl.dsl.ast.PostfixOperatorNode with org.squeryl.dsl.ast.LogicalBoolean = {
      final class $anon extends org.squeryl.dsl.ast.PostfixOperatorNode with org.squeryl.dsl.ast.LogicalBoolean {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>("is null", this);
          ()
        }
      };
      new $anon()
    };
    def isNotNull: org.squeryl.dsl.ast.PostfixOperatorNode with org.squeryl.dsl.ast.LogicalBoolean = {
      final class $anon extends org.squeryl.dsl.ast.PostfixOperatorNode with org.squeryl.dsl.ast.LogicalBoolean {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>("is not null", this);
          ()
        }
      };
      new $anon()
    };
    def between[A2 >: Nothing <: Any, T2 >: Nothing <: Any, A3 >: Nothing <: Any, T3 >: Nothing <: Any](b1: org.squeryl.dsl.TypedExpression[A2,T2], b2: org.squeryl.dsl.TypedExpression[A3,T3])(implicit ev1: org.squeryl.dsl.CanCompare[T1,T2], ev2: org.squeryl.dsl.CanCompare[T2,T3]): org.squeryl.dsl.ast.BetweenExpression = new org.squeryl.dsl.ast.BetweenExpression(this, b1, b2);
    def like[A2 >: Nothing <: Any, T2 >: Nothing <: org.squeryl.dsl.TOptionString](s: org.squeryl.dsl.TypedExpression[A2,T2])(implicit ev: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = new org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean(this, s, "like", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def ||[A2 >: Nothing <: Any, T2 >: Nothing <: Any](e: org.squeryl.dsl.TypedExpression[A2,T2]): org.squeryl.dsl.ConcatOp[A1,A2,T1,T2] = new org.squeryl.dsl.ConcatOp[A1,A2,T1,T2](this, e);
    def regex(pattern: String): org.squeryl.dsl.ast.FunctionNode with org.squeryl.dsl.ast.LogicalBoolean = {
      final class $anon extends org.squeryl.dsl.ast.FunctionNode with org.squeryl.dsl.ast.LogicalBoolean {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(pattern, collection.this.Seq.apply[org.squeryl.dsl.TypedExpression[A1,T1]](this));
          ()
        };
        override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = org.squeryl.Session.currentSession.databaseAdapter.writeRegexExpression(TypedExpression.this, pattern, sw)
      };
      new $anon()
    };
    def is(columnAttributes: org.squeryl.internals.AttributeValidOnNumericalColumn*)(implicit restrictUsageWithinSchema: org.squeryl.Schema): org.squeryl.dsl.ast.ColumnAttributeAssignment = new org.squeryl.dsl.ast.ColumnAttributeAssignment(TypedExpression.this._fieldMetaData, columnAttributes);
    def in[A2 >: Nothing <: Any, T2 >: Nothing <: Any](t: Traversable[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = new org.squeryl.dsl.ast.InclusionOperator(this, new org.squeryl.dsl.ast.RightHandSideOfIn[Nothing](new org.squeryl.dsl.ast.ConstantExpressionNodeList[A2](t, TypedExpression.this.mapper), ast.this.RightHandSideOfIn.<init>$default$2[Nothing]).toIn);
    def in[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = new org.squeryl.dsl.ast.InclusionOperator(this, new org.squeryl.dsl.ast.RightHandSideOfIn[Nothing](q.copy(false).ast, ast.this.RightHandSideOfIn.<init>$default$2[Nothing]));
    def notIn[A2 >: Nothing <: Any, T2 >: Nothing <: Any](t: Traversable[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = new org.squeryl.dsl.ast.ExclusionOperator(this, new org.squeryl.dsl.ast.RightHandSideOfIn[Nothing](new org.squeryl.dsl.ast.ConstantExpressionNodeList[A2](t, TypedExpression.this.mapper), ast.this.RightHandSideOfIn.<init>$default$2[Nothing]).toNotIn);
    def notIn[A2 >: Nothing <: Any, T2 >: Nothing <: Any](q: org.squeryl.Query[A2])(implicit cc: org.squeryl.dsl.CanCompare[T1,T2]): org.squeryl.dsl.ast.LogicalBoolean = new org.squeryl.dsl.ast.ExclusionOperator(this, new org.squeryl.dsl.ast.RightHandSideOfIn[Nothing](q.copy(false).ast, ast.this.RightHandSideOfIn.<init>$default$2[Nothing]));
    def ~: org.squeryl.dsl.TypedExpression[A1,T1] = this;
    def sample: A1 = TypedExpression.this.mapper.sample;
    def mapper: org.squeryl.internals.OutMapper[A1];
    def :=[B >: Nothing <: Any](b: B)(implicit evidence$1: B => org.squeryl.dsl.TypedExpression[A1,T1]): org.squeryl.dsl.ast.UpdateAssignment = new org.squeryl.dsl.ast.UpdateAssignment(TypedExpression.this._fieldMetaData, (evidence$1.apply(b): org.squeryl.dsl.TypedExpression[A1,T1]));
    def :=(q: org.squeryl.Query[org.squeryl.dsl.Measures[A1]]): org.squeryl.dsl.ast.UpdateAssignment = new org.squeryl.dsl.ast.UpdateAssignment(TypedExpression.this._fieldMetaData, q.ast);
    def defaultsTo[B >: Nothing <: Any](b: B)(implicit evidence$2: B => org.squeryl.dsl.TypedExpression[A1,T1]): org.squeryl.dsl.ast.DefaultValueAssignment = new org.squeryl.dsl.ast.DefaultValueAssignment(TypedExpression.this._fieldMetaData, (evidence$2.apply(b): org.squeryl.dsl.TypedExpression[A1,T1]));
    private[squeryl] def _fieldMetaData: org.squeryl.internals.FieldMetaData = {
      val ser: org.squeryl.dsl.ast.SelectElementReference[_, _] = try {
        this.asInstanceOf[org.squeryl.dsl.ast.SelectElementReference[_, _]]
      } catch {
        case (e @ (_: ClassCastException)) => throw new scala.`package`.RuntimeException("left side of assignment \'".+(org.squeryl.internals.Utils.failSafeString(this.toString())).+("\' is invalid, make sure statement uses *only* closure argument."), e)
      };
      val fmd: org.squeryl.internals.FieldMetaData = try {
        ser.selectElement.asInstanceOf[org.squeryl.dsl.ast.FieldSelectElement].fieldMetaData
      } catch {
        case (e @ (_: ClassCastException)) => throw new scala.`package`.RuntimeException("left side of assignment \'".+(org.squeryl.internals.Utils.failSafeString(this.toString())).+("\' is invalid, make sure statement uses *only* closure argument."), e)
      };
      fmd
    }
  };
  class TypedExpressionConversion[A1 >: Nothing <: Any, T1 >: Nothing <: Any] extends Object with org.squeryl.dsl.TypedExpression[A1,T1] {
    <paramaccessor> private[this] val e: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def e: org.squeryl.dsl.ast.ExpressionNode = TypedExpressionConversion.this.e;
    <paramaccessor> private[this] val bf: org.squeryl.dsl.TypedExpressionFactory[A1,T1] = _;
    def <init>(e: org.squeryl.dsl.ast.ExpressionNode, bf: org.squeryl.dsl.TypedExpressionFactory[A1,T1]): org.squeryl.dsl.TypedExpressionConversion[A1,T1] = {
      TypedExpressionConversion.super.<init>();
      ()
    };
    def mapper: org.squeryl.internals.OutMapper[A1] = TypedExpressionConversion.this.bf.createOutMapper;
    override def inhibited: Boolean = TypedExpressionConversion.this.e.inhibited;
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = TypedExpressionConversion.this.e.doWrite(sw);
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = TypedExpressionConversion.this.e.children
  };
  abstract trait Floatifier[T1 >: Nothing <: Any, A2 >: Nothing <: Any, T2 >: Nothing <: Any] extends scala.AnyRef {
    def floatify(v: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.TypedExpressionConversion[A2,T2]
  };
  abstract trait IdentityFloatifier[A1 >: Nothing <: Any, T1 >: Nothing <: Any] extends Object with org.squeryl.dsl.Floatifier[T1,A1,T1];
  abstract trait FloatTypedExpressionFactory[A1 >: Nothing <: Any, T1 >: Nothing <: Any] extends Object with org.squeryl.dsl.TypedExpressionFactory[A1,T1] with org.squeryl.dsl.IdentityFloatifier[A1,T1] { self: org.squeryl.dsl.FloatTypedExpressionFactory[A1,T1] with org.squeryl.dsl.JdbcMapper[_, A1] => 
    def /*FloatTypedExpressionFactory*/$init$(): Unit = {
      ()
    };
    def floatify(v: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.TypedExpressionConversion[A1,T1] = FloatTypedExpressionFactory.this.convert(v)
  };
  abstract trait JdbcMapper[P >: Nothing <: Any, A >: Nothing <: Any] extends scala.AnyRef { self: org.squeryl.dsl.JdbcMapper[P,A] with org.squeryl.dsl.TypedExpressionFactory[A, _] => 
    def /*JdbcMapper*/$init$(): Unit = {
      ()
    };
    def thisTypedExpressionFactory: org.squeryl.dsl.TypedExpressionFactory[A, _] = this;
    def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): P;
    def convertFromJdbc(v: P): A;
    def convertToJdbc(v: A): P;
    def defaultColumnLength: Int;
    def map(rs: java.sql.ResultSet, i: Int): A = JdbcMapper.this.convertFromJdbc(JdbcMapper.this.extractNativeJdbcValue(rs, i))
  };
  abstract trait ArrayJdbcMapper[P >: Nothing <: Any, A >: Nothing <: Any] extends Object with org.squeryl.dsl.JdbcMapper[P,A] { self: org.squeryl.dsl.ArrayJdbcMapper[P,A] with org.squeryl.dsl.TypedExpressionFactory[A, _] => 
    def /*ArrayJdbcMapper*/$init$(): Unit = {
      ()
    };
    def nativeJdbcType: Class[_ <: AnyRef] = ArrayJdbcMapper.this.sample.asInstanceOf[AnyRef].getClass()
  };
  abstract trait PrimitiveJdbcMapper[A >: Nothing <: Any] extends Object with org.squeryl.dsl.JdbcMapper[A,A] { self: org.squeryl.dsl.PrimitiveJdbcMapper[A] with org.squeryl.dsl.TypedExpressionFactory[A, _] => 
    def /*PrimitiveJdbcMapper*/$init$(): Unit = {
      ()
    };
    def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): A;
    def convertFromJdbc(v: A): A = v;
    def convertToJdbc(v: A): A = v;
    def nativeJdbcType: Class[_ <: AnyRef] = PrimitiveJdbcMapper.this.sample.asInstanceOf[AnyRef].getClass()
  };
  abstract class NonPrimitiveJdbcMapper[P >: Nothing <: Any, A >: Nothing <: Any, T >: Nothing <: Any] extends Object with org.squeryl.dsl.JdbcMapper[P,A] with org.squeryl.dsl.TypedExpressionFactory[A,T] { self: org.squeryl.dsl.NonPrimitiveJdbcMapper[P,A,T] with org.squeryl.dsl.TypedExpressionFactory[A,T] => 
    <paramaccessor> private[this] val primitiveMapper: org.squeryl.dsl.PrimitiveJdbcMapper[P] = _;
    <stable> <accessor> <paramaccessor> def primitiveMapper: org.squeryl.dsl.PrimitiveJdbcMapper[P] = NonPrimitiveJdbcMapper.this.primitiveMapper;
    <paramaccessor> private[this] val fieldMapper: org.squeryl.internals.FieldMapper = _;
    <stable> <accessor> <paramaccessor> def fieldMapper: org.squeryl.internals.FieldMapper = NonPrimitiveJdbcMapper.this.fieldMapper;
    def <init>(primitiveMapper: org.squeryl.dsl.PrimitiveJdbcMapper[P], fieldMapper: org.squeryl.internals.FieldMapper): org.squeryl.dsl.NonPrimitiveJdbcMapper[P,A,T] = {
      NonPrimitiveJdbcMapper.super.<init>();
      ()
    };
    def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): P = NonPrimitiveJdbcMapper.this.primitiveMapper.extractNativeJdbcValue(rs, i);
    def defaultColumnLength: Int = NonPrimitiveJdbcMapper.this.primitiveMapper.defaultColumnLength;
    def sample: A = NonPrimitiveJdbcMapper.this.convertFromJdbc(NonPrimitiveJdbcMapper.this.primitiveMapper.thisTypedExpressionFactory.sample);
    def createFromNativeJdbcValue(v: P): org.squeryl.dsl.TypedExpression[A,T] = NonPrimitiveJdbcMapper.this.create(NonPrimitiveJdbcMapper.this.convertFromJdbc(v));
    NonPrimitiveJdbcMapper.this.fieldMapper.register[P, A](this)
  };
  abstract trait TypedExpressionFactory[A >: Nothing <: Any, T >: Nothing <: Any] extends scala.AnyRef { self: org.squeryl.dsl.TypedExpressionFactory[A,T] with org.squeryl.dsl.JdbcMapper[_, A] => 
    def /*TypedExpressionFactory*/$init$(): Unit = {
      ()
    };
    def thisAnyRefMapper: org.squeryl.dsl.JdbcMapper[AnyRef,A] = this.asInstanceOf[org.squeryl.dsl.JdbcMapper[AnyRef,A]];
    def create(a: A): org.squeryl.dsl.TypedExpression[A,T] = org.squeryl.internals.FieldReferenceLinker.takeLastAccessedFieldReference match {
      case scala.None => TypedExpressionFactory.this.createConstant(a)
      case (x: org.squeryl.dsl.ast.SelectElement)Some[org.squeryl.dsl.ast.SelectElement]((n @ (_: org.squeryl.dsl.ast.SelectElement))) => new org.squeryl.dsl.ast.SelectElementReference[A,T](n, TypedExpressionFactory.this.createOutMapper)
    };
    def createConstant(a: A): org.squeryl.dsl.ast.ConstantTypedExpression[A,T] = new org.squeryl.dsl.ast.ConstantTypedExpression[A,T](a, TypedExpressionFactory.this.thisAnyRefMapper.convertToJdbc(a), scala.Some.apply[org.squeryl.dsl.TypedExpressionFactory[A,T] with org.squeryl.dsl.JdbcMapper[_, A]](this));
    def jdbcSample: AnyRef = TypedExpressionFactory.this.thisAnyRefMapper.convertToJdbc(TypedExpressionFactory.this.sample);
    def convert(v: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.TypedExpressionConversion[A,T] = new org.squeryl.dsl.TypedExpressionConversion[A,T](v, this);
    def sample: A;
    def defaultColumnLength: Int;
    def thisMapper: org.squeryl.dsl.JdbcMapper[_, A] = this;
    private def zis: org.squeryl.dsl.TypedExpressionFactory[A,T] with org.squeryl.dsl.JdbcMapper[_, A] = this;
    def createOutMapper: org.squeryl.internals.OutMapper[A] = {
      final class $anon extends Object with org.squeryl.internals.OutMapper[A] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        def doMap(rs: java.sql.ResultSet): A = TypedExpressionFactory.this.zis.map(rs, $anon.this.index);
        def sample: A = TypedExpressionFactory.this.zis.sample
      };
      new $anon()
    }
  };
  abstract trait IntegralTypedExpressionFactory[A1 >: Nothing <: Any, T1 >: Nothing <: Any, A2 >: Nothing <: Any, T2 >: Nothing <: Any] extends Object with org.squeryl.dsl.TypedExpressionFactory[A1,T1] with org.squeryl.dsl.Floatifier[T1,A2,T2] { self: org.squeryl.dsl.IntegralTypedExpressionFactory[A1,T1,A2,T2] with org.squeryl.dsl.JdbcMapper[_, A1] => 
    def /*IntegralTypedExpressionFactory*/$init$(): Unit = {
      ()
    };
    def floatify(v: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.TypedExpressionConversion[A2,T2] = IntegralTypedExpressionFactory.this.floatifyer.convert(v);
    def floatifyer: org.squeryl.dsl.TypedExpressionFactory[A2,T2]
  };
  abstract trait DeOptionizer[P1 >: Nothing <: Any, A1 >: Nothing <: Any, T1 >: Nothing <: Any, A2 >: Option[A1] <: Option[A1], T2 >: Nothing <: Any] extends Object with org.squeryl.dsl.JdbcMapper[P1,A2] { self: org.squeryl.dsl.DeOptionizer[P1,A1,T1,A2,T2] with org.squeryl.dsl.TypedExpressionFactory[A2,T2] => 
    def /*DeOptionizer*/$init$(): Unit = {
      ()
    };
    def deOptionizer: org.squeryl.dsl.TypedExpressionFactory[A1,T1] with org.squeryl.dsl.JdbcMapper[P1,A1];
    def sample: Option[A1] = scala.Option.apply[A1](DeOptionizer.this.deOptionizer.sample);
    def defaultColumnLength: Int = DeOptionizer.this.deOptionizer.defaultColumnLength;
    def convertFromJdbc(v: P1): A2 = scala.Option.apply[A1](DeOptionizer.this.deOptionizer.convertFromJdbc(v));
    def convertToJdbc(v: A2): P1 = v.map[P1](((p: A1) => DeOptionizer.this.deOptionizer.convertToJdbc(p))).getOrElse[P1](null.asInstanceOf[P1]);
    def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): P1 = DeOptionizer.this.deOptionizer.extractNativeJdbcValue(rs, i);
    override def createOutMapper: org.squeryl.internals.OutMapper[A2] = {
      final class $anon extends Object with org.squeryl.internals.OutMapper[A2] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        def doMap(rs: java.sql.ResultSet): A2 = {
          val v: A1 = DeOptionizer.this.deOptionizer.thisMapper.map(rs, $anon.this.index);
          val r: Option[A1] = if (rs.wasNull())
            scala.None
          else
            scala.Option.apply[A1](v);
          r
        };
        def sample: Option[A1] = scala.Option.apply[A1](DeOptionizer.this.deOptionizer.sample)
      };
      new $anon()
    }
  };
  class ConcatOp[A1 >: Nothing <: Any, A2 >: Nothing <: Any, T1 >: Nothing <: Any, T2 >: Nothing <: Any] extends org.squeryl.dsl.ast.BinaryOperatorNode {
    <paramaccessor> private[this] val a1: org.squeryl.dsl.TypedExpression[A1,T1] = _;
    <stable> <accessor> <paramaccessor> def a1: org.squeryl.dsl.TypedExpression[A1,T1] = ConcatOp.this.a1;
    <paramaccessor> private[this] val a2: org.squeryl.dsl.TypedExpression[A2,T2] = _;
    <stable> <accessor> <paramaccessor> def a2: org.squeryl.dsl.TypedExpression[A2,T2] = ConcatOp.this.a2;
    def <init>(a1: org.squeryl.dsl.TypedExpression[A1,T1], a2: org.squeryl.dsl.TypedExpression[A2,T2]): org.squeryl.dsl.ConcatOp[A1,A2,T1,T2] = {
      ConcatOp.super.<init>(a1, a2, "||", ast.this.BinaryOperatorNode.<init>$default$4);
      ()
    };
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.databaseAdapter.writeConcatOperator(ConcatOp.this.a1, ConcatOp.this.a2, sw)
  };
  class NvlNode[A >: Nothing <: Any, T >: Nothing <: Any] extends org.squeryl.dsl.ast.BinaryOperatorNode with org.squeryl.dsl.TypedExpression[A,T] {
    <paramaccessor> private[this] val e1: org.squeryl.dsl.TypedExpression[_, _] = _;
    <paramaccessor> private[this] val e2: org.squeryl.dsl.TypedExpression[A,T] = _;
    def <init>(e1: org.squeryl.dsl.TypedExpression[_, _], e2: org.squeryl.dsl.TypedExpression[A,T]): org.squeryl.dsl.NvlNode[A,T] = {
      NvlNode.super.<init>(e1, e2, "nvl", false);
      ()
    };
    def mapper: org.squeryl.internals.OutMapper[A] = NvlNode.this.e2.mapper;
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.databaseAdapter.writeNvlCall(NvlNode.this.left, NvlNode.this.right, sw)
  }
}