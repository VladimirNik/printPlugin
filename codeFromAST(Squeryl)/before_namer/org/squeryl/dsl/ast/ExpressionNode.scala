package org.squeryl.dsl.ast {
  import scala.collection.mutable.ArrayBuffer;
  import org.squeryl.internals._;
  import org.squeryl.dsl._;
  import org.squeryl.Session;
  abstract trait ExpressionNode extends scala.AnyRef {
    def $init$() = {
      ()
    };
    var parent: Option[ExpressionNode] = None;
    def id = Integer.toHexString(System.identityHashCode(this));
    def inhibited = _inhibitedByWhen;
    def inhibitedFlagForAstDump = if (inhibited)
      "!"
    else
      "";
    def write(sw: StatementWriter) = if (inhibited.unary_$bang)
      doWrite(sw)
    else
      ();
    def doWrite(sw: StatementWriter): Unit;
    def writeToString: String = {
      val sw = new StatementWriter(Session.currentSession.databaseAdapter);
      write(sw);
      sw.statement
    };
    def children: List[ExpressionNode] = List.empty;
    override def toString = this.getClass.getName;
    private def _visitDescendants(n: ExpressionNode, parent: Option[ExpressionNode], depth: Int, visitor: _root_.scala.Function3[ExpressionNode, Option[ExpressionNode], Int, Unit]): Unit = {
      visitor(n, parent, depth);
      n.children.foreach(((child) => _visitDescendants(child, Some(n), depth.$plus(1), visitor)))
    };
    private def _filterDescendants(n: ExpressionNode, ab: ArrayBuffer[ExpressionNode], predicate: _root_.scala.Function1[ExpressionNode, Boolean]): Iterable[ExpressionNode] = {
      if (predicate(n))
        ab.append(n)
      else
        ();
      n.children.foreach(((child) => _filterDescendants(child, ab, predicate)));
      ab
    };
    def filterDescendants(predicate: _root_.scala.Function1[ExpressionNode, Boolean]) = _filterDescendants(this, new ArrayBuffer[ExpressionNode](), predicate);
    def filterDescendantsOfType[T >: _root_.scala.Nothing <: _root_.scala.Any](implicit manifest: Manifest[T]) = _filterDescendants(this, new ArrayBuffer[ExpressionNode](), ((n: ExpressionNode) => manifest.erasure.isAssignableFrom(n.getClass))).asInstanceOf[Iterable[T]];
    def visitDescendants(visitor: _root_.scala.Function3[ExpressionNode, Option[ExpressionNode], Int, Unit]) = _visitDescendants(this, None, 0, visitor);
    protected var _inhibitedByWhen = false;
    def inhibitWhen(inhibited: Boolean): this.type = {
      _inhibitedByWhen = inhibited;
      this
    };
    def $qmark: this.type = {
      if (this.isInstanceOf[ConstantTypedExpression[_$1, _$2] forSome { 
  <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any;
  <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
}].unary_$bang)
        throw new UnsupportedOperationException("the \'?\' operator (shorthand for \'p.inhibitWhen(p == None))\' can only be used on a constant query argument")
      else
        ();
      val c = this.asInstanceOf[ConstantTypedExpression[_$3, _$4] forSome { 
        <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any;
        <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
      }];
      inhibitWhen(c.value.$eq$eq(None))
    }
  };
  abstract trait ListExpressionNode extends ExpressionNode {
    def $init$() = {
      ()
    };
    def quotesElement = false;
    def isEmpty: Boolean
  };
  class EqualityExpression extends BinaryOperatorNodeLogicalBoolean {
    override <paramaccessor> val left: TypedExpression[_$5, _$6] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    override <paramaccessor> val right: TypedExpression[_$7, _$8] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(left: TypedExpression[_$5, _$6] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }, right: TypedExpression[_$7, _$8] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>(left, right, "=");
      ()
    };
    override def doWrite(sw: StatementWriter) = right match {
      case (c @ (_: ConstantTypedExpression[(_ @ <empty>), (_ @ <empty>)])) => if (c.value.$eq$eq(None))
        {
          left.write(sw);
          sw.write(" is null")
        }
      else
        super.doWrite(sw)
      case _ => super.doWrite(sw)
    }
  };
  class InclusionOperator extends BinaryOperatorNodeLogicalBoolean {
    <paramaccessor> private[this] val left: ExpressionNode = _;
    <paramaccessor> private[this] val right: RightHandSideOfIn[_$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(left: ExpressionNode, right: RightHandSideOfIn[_$9] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>(left, right, "in", true);
      ()
    };
    override def doWrite(sw: StatementWriter) = if (right.isConstantEmptyList)
      sw.write("(1 = 0)")
    else
      super.doWrite(sw)
  };
  class ExclusionOperator extends BinaryOperatorNodeLogicalBoolean {
    <paramaccessor> private[this] val left: ExpressionNode = _;
    <paramaccessor> private[this] val right: RightHandSideOfIn[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(left: ExpressionNode, right: RightHandSideOfIn[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>(left, right, "not in", true);
      ()
    }
  };
  class BinaryOperatorNodeLogicalBoolean extends BinaryOperatorNode with LogicalBoolean {
    <paramaccessor> private[this] val left: ExpressionNode = _;
    <paramaccessor> private[this] val right: ExpressionNode = _;
    <paramaccessor> private[this] val op: String = _;
    <paramaccessor> private[this] val rightArgInParent: Boolean = _;
    def <init>(left: ExpressionNode, right: ExpressionNode, op: String, rightArgInParent: Boolean = false) = {
      super.<init>(left, right, op);
      ()
    };
    override def inhibited = _inhibitedByWhen.$bar$bar(left match {
      case (_: LogicalBoolean) => left.inhibited.$amp$amp(right.inhibited)
      case _ => left.inhibited.$bar$bar(right.inhibited)
    });
    override def doWrite(sw: StatementWriter) = {
      val nonInh = children.filter(((c) => c.inhibited.unary_$bang)).iterator;
      sw.write("(");
      nonInh.next.write(sw);
      sw.write(" ");
      if (nonInh.hasNext)
        {
          sw.write(operatorToken);
          if (newLineAfterOperator)
            sw.nextLine
          else
            ();
          sw.write(" ");
          if (rightArgInParent)
            sw.write("(")
          else
            ();
          nonInh.next.write(sw);
          if (rightArgInParent)
            sw.write(")")
          else
            ()
        }
      else
        ();
      sw.write(")")
    }
  };
  class ExistsExpression extends PrefixOperatorNode with LogicalBoolean {
    <paramaccessor> val ast: ExpressionNode = _;
    <paramaccessor> val opType: String = _;
    def <init>(ast: ExpressionNode, opType: String) = {
      super.<init>(ast, opType, false);
      ()
    }
  };
  class BetweenExpression extends TernaryOperatorNode with LogicalBoolean {
    <paramaccessor> private[this] val first: ExpressionNode = _;
    <paramaccessor> private[this] val second: ExpressionNode = _;
    <paramaccessor> private[this] val third: ExpressionNode = _;
    def <init>(first: ExpressionNode, second: ExpressionNode, third: ExpressionNode) = {
      super.<init>(first, second, third, "between");
      ()
    };
    override def doWrite(sw: StatementWriter) = {
      first.write(sw);
      sw.write(" between ");
      second.write(sw);
      sw.write(" and ");
      third.write(sw)
    }
  };
  class TernaryOperatorNode extends FunctionNode with LogicalBoolean {
    <paramaccessor> val first: ExpressionNode = _;
    <paramaccessor> val second: ExpressionNode = _;
    <paramaccessor> val third: ExpressionNode = _;
    <paramaccessor> private[this] val op: String = _;
    def <init>(first: ExpressionNode, second: ExpressionNode, third: ExpressionNode, op: String) = {
      super.<init>(op, Seq(first, second, third));
      ()
    };
    override def inhibited = _inhibitedByWhen.$bar$bar(first.inhibited).$bar$bar(second.inhibited).$bar$bar(third.inhibited)
  };
  abstract trait LogicalBoolean extends ExpressionNode {
    def $init$() = {
      ()
    };
    def and(b: LogicalBoolean): LogicalBoolean = new BinaryOperatorNodeLogicalBoolean(this, b, "and");
    def or(b: LogicalBoolean): LogicalBoolean = new BinaryOperatorNodeLogicalBoolean(this, b, "or");
    def and(b: Option[LogicalBoolean]): LogicalBoolean = b.map(((x$1) => this.and(x$1))).getOrElse(this);
    def or(b: Option[LogicalBoolean]): LogicalBoolean = b.map(((x$2) => this.or(x$2))).getOrElse(this)
  };
  object TrueLogicalBoolean extends LogicalBoolean {
    def <init>() = {
      super.<init>();
      ()
    };
    override def and(b: LogicalBoolean) = b;
    override def or(b: LogicalBoolean) = this;
    override def doWrite(sw: StatementWriter): scala.Unit = sw.write("(1=1)")
  };
  object FalseLogicalBoolean extends LogicalBoolean {
    def <init>() = {
      super.<init>();
      ()
    };
    override def and(b: LogicalBoolean) = this;
    override def or(b: LogicalBoolean) = b;
    override def doWrite(sw: StatementWriter): scala.Unit = sw.write("(1=0)")
  };
  object LogicalBoolean extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    def and(conditions: Seq[LogicalBoolean]): LogicalBoolean = conditions.fold(TrueLogicalBoolean)(((x$3, x$4) => x$3.and(x$4)));
    def or(conditions: Seq[LogicalBoolean]): LogicalBoolean = conditions.fold(FalseLogicalBoolean)(((x$5, x$6) => x$5.or(x$6)))
  };
  class UpdateAssignment extends scala.AnyRef {
    <paramaccessor> val left: FieldMetaData = _;
    <paramaccessor> val right: ExpressionNode = _;
    def <init>(left: FieldMetaData, right: ExpressionNode) = {
      super.<init>();
      ()
    }
  };
  abstract trait BaseColumnAttributeAssignment extends scala.AnyRef {
    def $init$() = {
      ()
    };
    def clearColumnAttributes: Unit;
    def isIdFieldOfKeyedEntity: Boolean;
    def isIdFieldOfKeyedEntityWithoutUniquenessConstraint = isIdFieldOfKeyedEntity.$amp$amp(columnAttributes.exists(((x$7) => x$7.isInstanceOf[PrimaryKey])).$bar$bar(columnAttributes.exists(((x$8) => x$8.isInstanceOf[Unique]))).unary_$bang);
    def columnAttributes: Seq[ColumnAttribute];
    def hasAttribute[A >: _root_.scala.Nothing <: ColumnAttribute](implicit m: Manifest[A]) = findAttribute[A](m).$bang$eq(None);
    def findAttribute[A >: _root_.scala.Nothing <: ColumnAttribute](implicit m: Manifest[A]) = columnAttributes.find(((ca) => m.erasure.isAssignableFrom(ca.getClass)))
  };
  class ColumnGroupAttributeAssignment extends BaseColumnAttributeAssignment {
    <paramaccessor> private[this] val cols: Seq[FieldMetaData] = _;
    <paramaccessor> private[this] val columnAttributes_: Seq[ColumnAttribute] = _;
    def <init>(cols: Seq[FieldMetaData], columnAttributes_: Seq[ColumnAttribute]) = {
      super.<init>();
      ()
    };
    private val _columnAttributes = new ArrayBuffer[ColumnAttribute]();
    _columnAttributes.appendAll(columnAttributes_);
    def columnAttributes = _columnAttributes;
    def addAttribute(a: ColumnAttribute) = _columnAttributes.append(a);
    def clearColumnAttributes = columns.foreach(((x$9) => x$9._clearColumnAttributes));
    def columns: Seq[FieldMetaData] = cols;
    def isIdFieldOfKeyedEntity = false;
    def name: Option[String] = None
  };
  class CompositeKeyAttributeAssignment extends ColumnGroupAttributeAssignment {
    <paramaccessor> val group: CompositeKey = _;
    <paramaccessor> private[this] val _columnAttributes: Seq[ColumnAttribute] = _;
    def <init>(group: CompositeKey, _columnAttributes: Seq[ColumnAttribute]) = {
      super.<init>(group._fields, _columnAttributes);
      ()
    };
    override def isIdFieldOfKeyedEntity = {
      val fmdHead = group._fields.head;
      fmdHead.parentMetaData.viewOrTable.ked.map(((x$10) => x$10.idPropertyName.$eq$eq(group._propertyName))).getOrElse(false)
    };
    assert(group._propertyName.$bang$eq(None));
    override def name: Option[String] = group._propertyName
  };
  class ColumnAttributeAssignment extends BaseColumnAttributeAssignment {
    <paramaccessor> val left: FieldMetaData = _;
    <paramaccessor> val columnAttributes: Seq[ColumnAttribute] = _;
    def <init>(left: FieldMetaData, columnAttributes: Seq[ColumnAttribute]) = {
      super.<init>();
      ()
    };
    def clearColumnAttributes = left._clearColumnAttributes;
    def isIdFieldOfKeyedEntity = left.isIdFieldOfKeyedEntity
  };
  class DefaultValueAssignment extends BaseColumnAttributeAssignment {
    <paramaccessor> val left: FieldMetaData = _;
    <paramaccessor> val value: TypedExpression[_$11, _$12] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(left: FieldMetaData, value: TypedExpression[_$11, _$12] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>();
      ()
    };
    def isIdFieldOfKeyedEntity = left.isIdFieldOfKeyedEntity;
    def clearColumnAttributes = left._clearColumnAttributes;
    def columnAttributes = Nil
  };
  class TokenExpressionNode extends ExpressionNode {
    <paramaccessor> val token: String = _;
    def <init>(token: String) = {
      super.<init>();
      ()
    };
    def doWrite(sw: StatementWriter) = sw.write(token)
  };
  class InputOnlyConstantExpressionNode extends ConstantTypedExpression[Any, Any] {
    <paramaccessor> private[this] val v: Any = _;
    def <init>(v: Any) = {
      super.<init>(v, v.asInstanceOf[AnyRef], None);
      ()
    }
  };
  class ConstantTypedExpression[A1 >: _root_.scala.Nothing <: _root_.scala.Any, T1 >: _root_.scala.Nothing <: _root_.scala.Any] extends TypedExpression[A1, T1] {
    <paramaccessor> val value: A1 = _;
    <paramaccessor> val nativeJdbcValue: AnyRef = _;
    <paramaccessor> private[this] val i: Option[TypedExpressionFactory[A1, _$13] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(value: A1, nativeJdbcValue: AnyRef, i: Option[TypedExpressionFactory[A1, _$13] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>();
      ()
    };
    private def needsQuote = value.isInstanceOf[String];
    override def mapper: OutMapper[A1] = i.get.createOutMapper;
    override def sample = if (value.$bang$eq(null))
      value
    else
      i.get.sample;
    def jdbcClass = i.map(((x$11) => x$11.jdbcSample)).getOrElse(nativeJdbcValue).getClass;
    if (nativeJdbcValue.$bang$eq(null))
      nativeJdbcValue.getClass
    else
      mapper.jdbcClass;
    def doWrite(sw: StatementWriter) = if (sw.isForDisplay)
      sw.write(displayAsString)
    else
      {
        sw.write("?");
        sw.addParam(ConstantStatementParam(this))
      };
    def displayAsString = if (value.$eq$eq(null))
      "null"
    else
      if (needsQuote)
        "\'".$plus(value.toString).$plus("\'")
      else
        value.toString;
    override def toString = scala.Symbol("ConstantTypedExpression").$plus(":").$plus(value)
  };
  class ConstantExpressionNodeList[T >: _root_.scala.Nothing <: _root_.scala.Any] extends ExpressionNode {
    <paramaccessor> val value: Traversable[T] = _;
    <paramaccessor> private[this] val mapper: OutMapper[_$14] forSome { 
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(value: Traversable[T], mapper: OutMapper[_$14] forSome { 
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>();
      ()
    };
    def isEmpty = value.$eq$eq(Nil);
    def doWrite(sw: StatementWriter) = if (sw.isForDisplay)
      sw.write(ConstantExpressionNodeList.this.value.map(((e) => "\'".$plus(e).$plus("\'"))).mkString(","))
    else
      {
        sw.write(ConstantExpressionNodeList.this.value.toSeq.map(((z) => "?")).mkString(","));
        ConstantExpressionNodeList.this.value.foreach(((z) => sw.addParam(ConstantExpressionNodeListParam(z.asInstanceOf[AnyRef], ConstantExpressionNodeList.this))))
      }
  };
  class FunctionNode extends ExpressionNode {
    <paramaccessor> val name: String = _;
    <paramaccessor> val args: Seq[ExpressionNode] = _;
    def <init>(name: String, args: Seq[ExpressionNode]) = {
      super.<init>();
      ()
    };
    def doWrite(sw: StatementWriter) = {
      sw.write(name);
      sw.write("(");
      sw.writeNodesWithSeparator(args, ",", false);
      sw.write(")")
    };
    override def children = args.toList
  };
  class PostfixOperatorNode extends ExpressionNode {
    <paramaccessor> val token: String = _;
    <paramaccessor> val arg: ExpressionNode = _;
    def <init>(token: String, arg: ExpressionNode) = {
      super.<init>();
      ()
    };
    def doWrite(sw: StatementWriter) = {
      arg.write(sw);
      sw.write(" ");
      sw.write(token)
    };
    override def children = List(arg)
  };
  class TypeConversion extends ExpressionNode {
    <paramaccessor> private[this] val e: ExpressionNode = _;
    def <init>(e: ExpressionNode) = {
      super.<init>();
      ()
    };
    override def inhibited = e.inhibited;
    override def doWrite(sw: StatementWriter) = e.doWrite(sw);
    override def children = e.children
  };
  class BinaryOperatorNode extends ExpressionNode {
    <paramaccessor> val left: ExpressionNode = _;
    <paramaccessor> val right: ExpressionNode = _;
    <paramaccessor> val operatorToken: String = _;
    <paramaccessor> val newLineAfterOperator: Boolean = _;
    def <init>(left: ExpressionNode, right: ExpressionNode, operatorToken: String, newLineAfterOperator: Boolean = false) = {
      super.<init>();
      ()
    };
    override def children = List(left, right);
    override def inhibited = _inhibitedByWhen.$bar$bar(left.inhibited).$bar$bar(right.inhibited);
    override def toString = scala.Symbol("BinaryOperatorNode").$plus(":").$plus(operatorToken).$plus(inhibitedFlagForAstDump);
    def doWrite(sw: StatementWriter) = {
      sw.write("(");
      left.write(sw);
      sw.write(" ");
      sw.write(operatorToken);
      if (newLineAfterOperator)
        sw.nextLine
      else
        ();
      sw.write(" ");
      right.write(sw);
      sw.write(")")
    }
  };
  class PrefixOperatorNode extends ExpressionNode {
    <paramaccessor> val child: ExpressionNode = _;
    <paramaccessor> val operatorToken: String = _;
    <paramaccessor> val newLineAfterOperator: Boolean = _;
    def <init>(child: ExpressionNode, operatorToken: String, newLineAfterOperator: Boolean = false) = {
      super.<init>();
      ()
    };
    override def children = List(child);
    override def inhibited = child.inhibited;
    override def toString = scala.Symbol("PrefixOperatorNode").$plus(":").$plus(operatorToken).$plus(inhibitedFlagForAstDump);
    override def doWrite(sw: StatementWriter) = {
      sw.write("(");
      sw.write(operatorToken);
      if (newLineAfterOperator)
        sw.nextLine
      else
        ();
      child.write(sw);
      sw.write(")")
    }
  };
  class LeftOuterJoinNode extends BinaryOperatorNode {
    <paramaccessor> private[this] val left: ExpressionNode = _;
    <paramaccessor> private[this] val right: ExpressionNode = _;
    def <init>(left: ExpressionNode, right: ExpressionNode) = {
      super.<init>(left, right, "left", false);
      ()
    };
    override def doWrite(sw: StatementWriter) = ();
    override def toString = scala.Symbol("LeftOuterJoin").$plus("")
  };
  class FullOuterJoinNode extends BinaryOperatorNode {
    <paramaccessor> private[this] val left: ExpressionNode = _;
    <paramaccessor> private[this] val right: ExpressionNode = _;
    def <init>(left: ExpressionNode, right: ExpressionNode) = {
      super.<init>(left, right, "full", false);
      ()
    };
    override def toString = scala.Symbol("FullOuterJoin").$plus("")
  };
  abstract trait UniqueIdInAliaseRequired extends scala.AnyRef {
    def $init$() = {
      ()
    };
    var uniqueId: Option[Int] = None
  };
  abstract trait QueryableExpressionNode extends ExpressionNode with UniqueIdInAliaseRequired {
    def $init$() = {
      ()
    };
    private var _inhibited = false;
    override def inhibited = _inhibited;
    def inhibited_$eq(b: Boolean) = _inhibited = b;
    def isMemberOfJoinList = joinKind.$bang$eq(None);
    var joinKind: Option[scala.Tuple2[String, String]] = None;
    def isOuterJoined = joinKind.$bang$eq(None).$amp$amp(joinKind.get._2.$eq$eq("outer"));
    var joinExpression: Option[LogicalBoolean] = None;
    var outerJoinExpression: Option[OuterJoinExpression] = None;
    var isRightJoined = false;
    def isChild(q: QueryableExpressionNode): Boolean;
    def owns(aSample: AnyRef): Boolean;
    def alias: String;
    def getOrCreateSelectElement(fmd: FieldMetaData, forScope: QueryExpressionElements): SelectElement;
    def getOrCreateAllSelectElements(forScope: QueryExpressionElements): Iterable[SelectElement];
    def dumpAst = {
      val sb = new StringBuffer();
      visitDescendants(((n, parent, d: Int) => {
        val c = 4.$times(d);
        1.to(c).foreach(((i) => sb.append(' ')));
        sb.append(n);
        sb.append("\n")
      }));
      sb.toString
    }
  };
  class OrderByArg extends scala.AnyRef {
    <paramaccessor> val e: ExpressionNode = _;
    def <init>(e: ExpressionNode) = {
      super.<init>();
      ()
    };
    private var _ascending = true;
    private[squeryl] def isAscending = _ascending;
    def asc = {
      _ascending = true;
      this
    };
    def desc = {
      _ascending = false;
      this
    }
  };
  class OrderByExpression extends ExpressionNode {
    <paramaccessor> private[this] val a: OrderByArg = _;
    def <init>(a: OrderByArg) = {
      super.<init>();
      ()
    };
    private def e = a.e;
    override def inhibited = e.inhibited;
    def doWrite(sw: StatementWriter) = {
      e.write(sw);
      if (a.isAscending)
        sw.write(" Asc")
      else
        sw.write(" Desc")
    };
    override def children = List(e);
    def inverse = {
      val aCopy = new OrderByArg(a.e);
      if (aCopy.isAscending)
        aCopy.desc
      else
        aCopy.asc;
      new OrderByExpression(aCopy)
    }
  };
  class DummyExpressionHolder extends ExpressionNode {
    <paramaccessor> val renderedExpression: String = _;
    def <init>(renderedExpression: String) = {
      super.<init>();
      ()
    };
    def doWrite(sw: StatementWriter) = sw.write(renderedExpression)
  };
  class RightHandSideOfIn[A >: _root_.scala.Nothing <: _root_.scala.Any] extends ExpressionNode {
    <paramaccessor> val ast: ExpressionNode = _;
    <paramaccessor> val isIn: Option[Boolean] = _;
    def <init>(ast: ExpressionNode, isIn: Option[Boolean] = None) = {
      super.<init>();
      ()
    };
    def toIn = new RightHandSideOfIn[A](ast, Some(true));
    def toNotIn = new RightHandSideOfIn[A](ast, Some(false));
    override def children = List(ast);
    override def inhibited = super.inhibited.$bar$bar(isConstantEmptyList.$amp$amp(isIn.get.unary_$bang));
    def isConstantEmptyList = if (ast.isInstanceOf[ConstantExpressionNodeList[_$15] forSome { 
      <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
    }])
      ast.asInstanceOf[ConstantExpressionNodeList[_$16] forSome { 
  <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
}].isEmpty
    else
      false;
    override def doWrite(sw: StatementWriter) = if (isConstantEmptyList.$amp$amp(isIn.get))
      sw.write("1 = 0")
    else
      ast.doWrite(sw)
  }
}