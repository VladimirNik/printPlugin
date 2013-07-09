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
  sealed abstract trait TOptionBigDecimal extends TNumeric;
  sealed abstract trait TBigDecimal extends TOptionBigDecimal with TNonOption;
  sealed abstract trait TOptionDouble extends TOptionBigDecimal;
  sealed abstract trait TDouble extends TOptionDouble with TBigDecimal with TNonOption;
  sealed abstract trait TOptionLong extends TOptionDouble;
  sealed abstract trait TLong extends TOptionLong with TDouble with TNonOption;
  sealed abstract trait TOptionFloat extends TOptionDouble;
  sealed abstract trait TFloat extends TOptionFloat with TDouble with TNonOption;
  sealed abstract trait TOptionInt extends TOptionLong with TOptionFloat;
  sealed abstract trait TInt extends TOptionInt with TLong with TNonOption with TFloat;
  sealed abstract trait TOptionByte extends TOptionInt;
  sealed abstract trait TByte extends TOptionByte with TInt with TNonOption;
  sealed abstract trait TOption extends TOptionByte with TOptionInt with TOptionFloat with TOptionLong with TOptionDouble with TOptionBigDecimal with TOptionDate with TOptionString with TOptionTimestamp;
  sealed abstract trait TNumericLowerTypeBound extends TByte with TInt with TFloat with TLong with TDouble with TBigDecimal;
  sealed abstract trait TNonOption extends scala.AnyRef;
  sealed abstract trait TOptionLowerBound extends TOptionByte with TOptionInt with TOptionFloat with TOptionLong with TOptionDouble with TOptionBigDecimal with TOptionDate with TOptionString with TOptionTimestamp;
  sealed abstract trait TEnumValue[A >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef;
  sealed abstract trait TOptionEnumValue[A >: _root_.scala.Nothing <: _root_.scala.Any] extends TEnumValue[A];
  sealed abstract trait TString extends TOptionString with TNonOption;
  sealed abstract trait TDate extends TOptionDate with TNonOption;
  sealed abstract trait TTimestamp extends TOptionTimestamp with TNonOption;
  sealed abstract trait TByteArray extends TOptionByteArray with TNonOption;
  sealed abstract trait TIntArray extends TOptionIntArray with TNonOption;
  sealed abstract trait TLongArray extends TOptionLongArray with TNonOption;
  sealed abstract trait TDoubleArray extends TOptionDoubleArray with TNonOption;
  sealed abstract trait TStringArray extends TOptionStringArray with TNonOption;
  sealed abstract trait TOptionString extends scala.AnyRef;
  sealed abstract trait TOptionDate extends scala.AnyRef;
  sealed abstract trait TOptionTimestamp extends scala.AnyRef;
  sealed abstract trait TOptionByteArray extends scala.AnyRef;
  sealed abstract trait TOptionIntArray extends scala.AnyRef;
  sealed abstract trait TOptionLongArray extends scala.AnyRef;
  sealed abstract trait TOptionDoubleArray extends scala.AnyRef;
  sealed abstract trait TOptionStringArray extends scala.AnyRef;
  sealed abstract trait TBoolean extends TOptionBoolean with TNonOption;
  sealed abstract trait TOptionBoolean extends scala.AnyRef;
  sealed abstract trait TUUID extends TOptionUUID with TNonOption;
  sealed abstract trait TOptionUUID extends scala.AnyRef;
  @new scala.annotation.implicitNotFound("The left side of the comparison (===, <>, between, ...) is not compatible with the right side.") sealed class CanCompare[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  abstract trait TypedExpression[A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any] extends ExpressionNode { outer => 
    def $init$() = {
      ()
    };
    def plus[T3 >: T1 <: TNumeric, T2 >: _root_.scala.Nothing <: T3, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2])(implicit f: TypedExpressionFactory[A3, T3]): TypedExpression[A3, T3] = f.convert(new BinaryOperatorNode(this, e, "+"));
    def times[T3 >: T1 <: TNumeric, T2 >: _root_.scala.Nothing <: T3, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2])(implicit f: TypedExpressionFactory[A3, T3]): TypedExpression[A3, T3] = f.convert(new BinaryOperatorNode(this, e, "*"));
    def minus[T3 >: T1 <: TNumeric, T2 >: _root_.scala.Nothing <: T3, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2])(implicit f: TypedExpressionFactory[A3, T3]): TypedExpression[A3, T3] = f.convert(new BinaryOperatorNode(this, e, "-"));
    def div[T3 >: T1 <: TNumeric, T2 >: _root_.scala.Nothing <: T3, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2])(implicit f: TypedExpressionFactory[A3, T3], tf: Floatifier[T3, A4, T4]): TypedExpression[A4, T4] = tf.floatify(new BinaryOperatorNode(this, e, "/"));
    def $plus[T3 >: T1 <: TNumeric, T2 >: _root_.scala.Nothing <: T3, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2])(implicit f: TypedExpressionFactory[A3, T3]): TypedExpression[A3, T3] = plus(e);
    def $times[T3 >: T1 <: TNumeric, T2 >: _root_.scala.Nothing <: T3, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2])(implicit f: TypedExpressionFactory[A3, T3]): TypedExpression[A3, T3] = times(e);
    def $minus[T3 >: T1 <: TNumeric, T2 >: _root_.scala.Nothing <: T3, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2])(implicit f: TypedExpressionFactory[A3, T3]): TypedExpression[A3, T3] = minus(e);
    def $div[T3 >: T1 <: TNumeric, T2 >: _root_.scala.Nothing <: T3, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, T4 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2])(implicit f: TypedExpressionFactory[A3, T3], tf: Floatifier[T3, A4, T4]): TypedExpression[A4, T4] = tf.floatify(new BinaryOperatorNode(this, e, "/"));
    def $eq$eq$eq[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = new EqualityExpression(this, b);
    def $less$greater[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = new BinaryOperatorNodeLogicalBoolean(this, b, "<>");
    def $eq$eq$eq[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[Measures[A2]])(implicit tef: TypedExpressionFactory[A2, T2], ev: CanCompare[T1, T2]) = new BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, "=");
    def $less$greater[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[Measures[A2]])(implicit tef: TypedExpressionFactory[A2, T2], ev: CanCompare[T1, T2]) = new BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, "=");
    def gt[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = new BinaryOperatorNodeLogicalBoolean(this, b, ">");
    def lt[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = new BinaryOperatorNodeLogicalBoolean(this, b, "<");
    def gte[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = new BinaryOperatorNodeLogicalBoolean(this, b, ">=");
    def lte[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = new BinaryOperatorNodeLogicalBoolean(this, b, "<=");
    def gt[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = new BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, ">");
    def gte[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = new BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, ">=");
    def lt[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = new BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, "<");
    def lte[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = new BinaryOperatorNodeLogicalBoolean(this, q.copy(false).ast, "<=");
    def $greater[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = gt(q);
    def $greater$eq[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = gte(q);
    def $less[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = lt(q);
    def $less$eq[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = lte(q);
    def $greater[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = gt(b);
    def $less[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = lt(b);
    def $greater$eq[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = gte(b);
    def $less$eq[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](b: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = lte(b);
    def isNull = {
      final class $anon extends PostfixOperatorNode with LogicalBoolean {
        def <init>() = {
          super.<init>("is null", this);
          ()
        }
      };
      new $anon()
    };
    def isNotNull = {
      final class $anon extends PostfixOperatorNode with LogicalBoolean {
        def <init>() = {
          super.<init>("is not null", this);
          ()
        }
      };
      new $anon()
    };
    def between[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, T3 >: _root_.scala.Nothing <: _root_.scala.Any](b1: TypedExpression[A2, T2], b2: TypedExpression[A3, T3])(implicit ev1: CanCompare[T1, T2], ev2: CanCompare[T2, T3]) = new BetweenExpression(this, b1, b2);
    def like[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: TOptionString](s: TypedExpression[A2, T2])(implicit ev: CanCompare[T1, T2]) = new BinaryOperatorNodeLogicalBoolean(this, s, "like");
    def $bar$bar[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](e: TypedExpression[A2, T2]) = new ConcatOp[A1, A2, T1, T2](this, e);
    def regex(pattern: String) = {
      final class $anon extends FunctionNode with LogicalBoolean {
        def <init>() = {
          super.<init>(pattern, Seq(this));
          ()
        };
        override def doWrite(sw: StatementWriter) = Session.currentSession.databaseAdapter.writeRegexExpression(outer, pattern, sw)
      };
      new $anon()
    };
    def is(columnAttributes: _root_.scala.<repeated>[AttributeValidOnNumericalColumn])(implicit restrictUsageWithinSchema: Schema) = new ColumnAttributeAssignment(_fieldMetaData, columnAttributes);
    def in[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](t: Traversable[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = new InclusionOperator(this, new RightHandSideOfIn(new ConstantExpressionNodeList(t, mapper)).toIn);
    def in[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = new InclusionOperator(this, new RightHandSideOfIn(q.copy(false).ast));
    def notIn[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](t: Traversable[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = new ExclusionOperator(this, new RightHandSideOfIn(new ConstantExpressionNodeList(t, mapper)).toNotIn);
    def notIn[A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any](q: Query[A2])(implicit cc: CanCompare[T1, T2]): LogicalBoolean = new ExclusionOperator(this, new RightHandSideOfIn(q.copy(false).ast));
    def $tilde = this;
    def sample: A1 = mapper.sample;
    def mapper: OutMapper[A1];
    def $colon$eq[B >: _root_.scala.Nothing <: _root_.scala.Any](b: B)(implicit evidence$1: _root_.scala.Function1[B, TypedExpression[A1, T1]]) = new UpdateAssignment(_fieldMetaData, (b: TypedExpression[A1, T1]));
    def $colon$eq(q: Query[Measures[A1]]) = new UpdateAssignment(_fieldMetaData, q.ast);
    def defaultsTo[B >: _root_.scala.Nothing <: _root_.scala.Any](b: B)(implicit evidence$2: _root_.scala.Function1[B, TypedExpression[A1, T1]]) = new DefaultValueAssignment(_fieldMetaData, (b: TypedExpression[A1, T1]));
    private[squeryl] def _fieldMetaData = {
      val ser = try {
        this.asInstanceOf[SelectElementReference[_$1, _$2] forSome { 
          <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any;
          <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
        }]
      } catch {
        case (e @ (_: ClassCastException)) => throw new RuntimeException("left side of assignment \'".$plus(Utils.failSafeString(this.toString)).$plus("\' is invalid, make sure statement uses *only* closure argument."), e)
      };
      val fmd = try {
        ser.selectElement.asInstanceOf[FieldSelectElement].fieldMetaData
      } catch {
        case (e @ (_: ClassCastException)) => throw new RuntimeException("left side of assignment \'".$plus(Utils.failSafeString(this.toString)).$plus("\' is invalid, make sure statement uses *only* closure argument."), e)
      };
      fmd
    }
  };
  class TypedExpressionConversion[A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any] extends TypedExpression[A1, T1] {
    <paramaccessor> val e: ExpressionNode = _;
    <paramaccessor> private[this] val bf: TypedExpressionFactory[A1, T1] = _;
    def <init>(e: ExpressionNode, bf: TypedExpressionFactory[A1, T1]) = {
      super.<init>();
      ()
    };
    def mapper: OutMapper[A1] = bf.createOutMapper;
    override def inhibited = e.inhibited;
    override def doWrite(sw: StatementWriter) = e.doWrite(sw);
    override def children = e.children
  };
  abstract trait Floatifier[T1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    def floatify(v: ExpressionNode): TypedExpressionConversion[A2, T2]
  };
  abstract trait IdentityFloatifier[A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any] extends Floatifier[T1, A1, T1];
  abstract trait FloatTypedExpressionFactory[A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any] extends TypedExpressionFactory[A1, T1] with IdentityFloatifier[A1, T1] { self: JdbcMapper[_$3, A1] forSome { 
    <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    def floatify(v: ExpressionNode): TypedExpressionConversion[A1, T1] = convert(v)
  };
  abstract trait JdbcMapper[P >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef { self: TypedExpressionFactory[A, _$4] forSome { 
    <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    def thisTypedExpressionFactory: TypedExpressionFactory[A, _$5] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
    } = this;
    def extractNativeJdbcValue(rs: ResultSet, i: Int): P;
    def convertFromJdbc(v: P): A;
    def convertToJdbc(v: A): P;
    def defaultColumnLength: Int;
    def map(rs: ResultSet, i: Int): A = convertFromJdbc(extractNativeJdbcValue(rs, i))
  };
  abstract trait ArrayJdbcMapper[P >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any] extends JdbcMapper[P, A] { self: TypedExpressionFactory[A, _$6] forSome { 
    <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    def nativeJdbcType = sample.asInstanceOf[AnyRef].getClass
  };
  abstract trait PrimitiveJdbcMapper[A >: _root_.scala.Nothing <: _root_.scala.Any] extends JdbcMapper[A, A] { self: TypedExpressionFactory[A, _$7] forSome { 
    <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    def extractNativeJdbcValue(rs: ResultSet, i: Int): A;
    def convertFromJdbc(v: A) = v;
    def convertToJdbc(v: A) = v;
    def nativeJdbcType = sample.asInstanceOf[AnyRef].getClass
  };
  abstract class NonPrimitiveJdbcMapper[P >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any, T >: _root_.scala.Nothing <: _root_.scala.Any] extends JdbcMapper[P, A] with TypedExpressionFactory[A, T] { self: TypedExpressionFactory[A, T] => 
    <paramaccessor> val primitiveMapper: PrimitiveJdbcMapper[P] = _;
    <paramaccessor> val fieldMapper: FieldMapper = _;
    def <init>(primitiveMapper: PrimitiveJdbcMapper[P], fieldMapper: FieldMapper) = {
      super.<init>();
      ()
    };
    def extractNativeJdbcValue(rs: ResultSet, i: Int): P = primitiveMapper.extractNativeJdbcValue(rs, i);
    def defaultColumnLength: Int = primitiveMapper.defaultColumnLength;
    def sample: A = convertFromJdbc(primitiveMapper.thisTypedExpressionFactory.sample);
    def createFromNativeJdbcValue(v: P) = create(convertFromJdbc(v));
    fieldMapper.register(this)
  };
  abstract trait TypedExpressionFactory[A >: _root_.scala.Nothing <: _root_.scala.Any, T >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef { self: JdbcMapper[_$8, A] forSome { 
    <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    def thisAnyRefMapper = this.asInstanceOf[JdbcMapper[AnyRef, A]];
    def create(a: A): TypedExpression[A, T] = FieldReferenceLinker.takeLastAccessedFieldReference match {
      case None => createConstant(a)
      case Some((n @ (_: SelectElement))) => new SelectElementReference[A, T](n, createOutMapper)
    };
    def createConstant(a: A) = new ConstantTypedExpression[A, T](a, thisAnyRefMapper.convertToJdbc(a), Some(this));
    def jdbcSample = thisAnyRefMapper.convertToJdbc(sample);
    def convert(v: ExpressionNode) = new TypedExpressionConversion[A, T](v, this);
    def sample: A;
    def defaultColumnLength: Int;
    def thisMapper: JdbcMapper[_$9, A] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    } = this;
    private def zis = this;
    def createOutMapper: OutMapper[A] = {
      final class $anon extends OutMapper[A] {
        def <init>() = {
          super.<init>();
          ()
        };
        def doMap(rs: ResultSet): A = zis.map(rs, index);
        def sample: A = zis.sample
      };
      new $anon()
    }
  };
  abstract trait IntegralTypedExpressionFactory[A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any] extends TypedExpressionFactory[A1, T1] with Floatifier[T1, A2, T2] { self: JdbcMapper[_$10, A1] forSome { 
    <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    def floatify(v: ExpressionNode): TypedExpressionConversion[A2, T2] = floatifyer.convert(v);
    def floatifyer: TypedExpressionFactory[A2, T2]
  };
  abstract trait DeOptionizer[P1 >: _root_.scala.Nothing <: _root_.scala.Any, A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: Option[A1] <: Option[A1], T2 >: _root_.scala.Nothing <: _root_.scala.Any] extends JdbcMapper[P1, A2] { self: TypedExpressionFactory[A2, T2] => 
    def $init$() = {
      ()
    };
    def deOptionizer: TypedExpressionFactory[A1, T1] with JdbcMapper[P1, A1];
    def sample = Option(deOptionizer.sample);
    def defaultColumnLength: Int = deOptionizer.defaultColumnLength;
    def convertFromJdbc(v: P1): A2 = Option(deOptionizer.convertFromJdbc(v));
    def convertToJdbc(v: A2): P1 = v.map(((p) => deOptionizer.convertToJdbc(p))).getOrElse(null.asInstanceOf[P1]);
    def extractNativeJdbcValue(rs: ResultSet, i: Int) = deOptionizer.extractNativeJdbcValue(rs, i);
    override def createOutMapper: OutMapper[A2] = {
      final class $anon extends OutMapper[A2] {
        def <init>() = {
          super.<init>();
          ()
        };
        def doMap(rs: ResultSet): A2 = {
          val v = deOptionizer.thisMapper.map(rs, index);
          val r = if (rs.wasNull)
            None
          else
            Option(v);
          r
        };
        def sample = Option(deOptionizer.sample)
      };
      new $anon()
    }
  };
  class ConcatOp[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any, T2 >: _root_.scala.Nothing <: _root_.scala.Any] extends BinaryOperatorNode {
    <paramaccessor> val a1: TypedExpression[A1, T1] = _;
    <paramaccessor> val a2: TypedExpression[A2, T2] = _;
    def <init>(a1: TypedExpression[A1, T1], a2: TypedExpression[A2, T2]) = {
      super.<init>(a1, a2, "||");
      ()
    };
    override def doWrite(sw: StatementWriter) = sw.databaseAdapter.writeConcatOperator(a1, a2, sw)
  };
  class NvlNode[A >: _root_.scala.Nothing <: _root_.scala.Any, T >: _root_.scala.Nothing <: _root_.scala.Any] extends BinaryOperatorNode with TypedExpression[A, T] {
    <paramaccessor> private[this] val e1: TypedExpression[_$11, _$12] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    <paramaccessor> private[this] val e2: TypedExpression[A, T] = _;
    def <init>(e1: TypedExpression[_$11, _$12] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }, e2: TypedExpression[A, T]) = {
      super.<init>(e1, e2, "nvl", false);
      ()
    };
    def mapper = e2.mapper;
    override def doWrite(sw: StatementWriter) = sw.databaseAdapter.writeNvlCall(left, right, sw)
  }
}