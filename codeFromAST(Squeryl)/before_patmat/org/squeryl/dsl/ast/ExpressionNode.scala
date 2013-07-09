package org.squeryl.dsl.ast {
  import scala.collection.mutable.ArrayBuffer;
  import org.squeryl.internals._;
  import org.squeryl.dsl._;
  import org.squeryl.Session;
  abstract trait ExpressionNode extends scala.AnyRef {
    def /*ExpressionNode*/$init$(): Unit = {
      ()
    };
    private[this] var parent: Option[org.squeryl.dsl.ast.ExpressionNode] = scala.None;
    <accessor> def parent: Option[org.squeryl.dsl.ast.ExpressionNode] = ExpressionNode.this.parent;
    <accessor> def parent_=(x$1: Option[org.squeryl.dsl.ast.ExpressionNode]): Unit = ExpressionNode.this.parent = x$1;
    def id: String = java.this.lang.Integer.toHexString(java.this.lang.System.identityHashCode(this));
    def inhibited: Boolean = ExpressionNode.this._inhibitedByWhen;
    def inhibitedFlagForAstDump: String = if (ExpressionNode.this.inhibited)
      "!"
    else
      "";
    def write(sw: org.squeryl.internals.StatementWriter): Unit = if (ExpressionNode.this.inhibited.unary_!)
      ExpressionNode.this.doWrite(sw)
    else
      ();
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit;
    def writeToString: String = {
      val sw: org.squeryl.internals.StatementWriter = new org.squeryl.internals.StatementWriter(org.squeryl.Session.currentSession.databaseAdapter);
      ExpressionNode.this.write(sw);
      sw.statement
    };
    def children: List[org.squeryl.dsl.ast.ExpressionNode] = immutable.this.List.empty[Nothing];
    override def toString: String = this.getClass().getName();
    private def _visitDescendants(n: org.squeryl.dsl.ast.ExpressionNode, parent: Option[org.squeryl.dsl.ast.ExpressionNode], depth: Int, visitor: (org.squeryl.dsl.ast.ExpressionNode, Option[org.squeryl.dsl.ast.ExpressionNode], Int) => Unit): Unit = {
      visitor.apply(n, parent, depth);
      n.children.foreach[Unit](((child: org.squeryl.dsl.ast.ExpressionNode) => ExpressionNode.this._visitDescendants(child, scala.Some.apply[org.squeryl.dsl.ast.ExpressionNode](n), depth.+(1), visitor)))
    };
    private def _filterDescendants(n: org.squeryl.dsl.ast.ExpressionNode, ab: scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.ExpressionNode], predicate: org.squeryl.dsl.ast.ExpressionNode => Boolean): Iterable[org.squeryl.dsl.ast.ExpressionNode] = {
      if (predicate.apply(n))
        ab.append(n)
      else
        ();
      n.children.foreach[Iterable[org.squeryl.dsl.ast.ExpressionNode]](((child: org.squeryl.dsl.ast.ExpressionNode) => ExpressionNode.this._filterDescendants(child, ab, predicate)));
      ab
    };
    def filterDescendants(predicate: org.squeryl.dsl.ast.ExpressionNode => Boolean): Iterable[org.squeryl.dsl.ast.ExpressionNode] = ExpressionNode.this._filterDescendants(this, new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.ExpressionNode](), predicate);
    def filterDescendantsOfType[T >: Nothing <: Any](implicit manifest: Manifest[T]): Iterable[T] = ExpressionNode.this._filterDescendants(this, new scala.collection.mutable.ArrayBuffer[org.squeryl.dsl.ast.ExpressionNode](), ((n: org.squeryl.dsl.ast.ExpressionNode) => manifest.erasure.isAssignableFrom(n.getClass()))).asInstanceOf[Iterable[T]];
    def visitDescendants(visitor: (org.squeryl.dsl.ast.ExpressionNode, Option[org.squeryl.dsl.ast.ExpressionNode], Int) => Unit): Unit = ExpressionNode.this._visitDescendants(this, scala.None, 0, visitor);
    private[this] var _inhibitedByWhen: Boolean = false;
    <accessor> protected def _inhibitedByWhen: Boolean = ExpressionNode.this._inhibitedByWhen;
    <accessor> protected def _inhibitedByWhen_=(x$1: Boolean): Unit = ExpressionNode.this._inhibitedByWhen = x$1;
    def inhibitWhen(inhibited: Boolean): org.squeryl.dsl.ast.ExpressionNode = {
      ExpressionNode.this._inhibitedByWhen_=(inhibited);
      this
    };
    def ?: org.squeryl.dsl.ast.ExpressionNode = {
      if (this.isInstanceOf[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]].unary_!)
        throw new scala.`package`.UnsupportedOperationException("the \'?\' operator (shorthand for \'p.inhibitWhen(p == None))\' can only be used on a constant query argument")
      else
        ();
      val c: org.squeryl.dsl.ast.ConstantTypedExpression[_, _] = this.asInstanceOf[org.squeryl.dsl.ast.ConstantTypedExpression[_, _]];
      ExpressionNode.this.inhibitWhen(c.value.==(scala.None))
    }
  };
  abstract trait ListExpressionNode extends Object with org.squeryl.dsl.ast.ExpressionNode {
    def /*ListExpressionNode*/$init$(): Unit = {
      ()
    };
    def quotesElement: Boolean = false;
    def isEmpty: Boolean
  };
  class EqualityExpression extends BinaryOperatorNodeLogicalBoolean {
    <paramaccessor> private[this] val left: org.squeryl.dsl.TypedExpression[_, _] = _;
    override <stable> <accessor> <paramaccessor> def left: org.squeryl.dsl.TypedExpression[_, _] = EqualityExpression.this.left;
    <paramaccessor> private[this] val right: org.squeryl.dsl.TypedExpression[_, _] = _;
    override <stable> <accessor> <paramaccessor> def right: org.squeryl.dsl.TypedExpression[_, _] = EqualityExpression.this.right;
    def <init>(left: org.squeryl.dsl.TypedExpression[_, _], right: org.squeryl.dsl.TypedExpression[_, _]): org.squeryl.dsl.ast.EqualityExpression = {
      EqualityExpression.super.<init>(left, right, "=", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
      ()
    };
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = EqualityExpression.this.right match {
      case (c @ (_: org.squeryl.dsl.ast.ConstantTypedExpression[_,_])) => if (c.value.==(scala.None))
        {
          EqualityExpression.this.left.write(sw);
          sw.write(" is null")
        }
      else
        EqualityExpression.super.doWrite(sw)
      case _ => EqualityExpression.super.doWrite(sw)
    }
  };
  class InclusionOperator extends BinaryOperatorNodeLogicalBoolean {
    <paramaccessor> private[this] val left: org.squeryl.dsl.ast.ExpressionNode = _;
    <paramaccessor> private[this] val right: org.squeryl.dsl.ast.RightHandSideOfIn[_] = _;
    def <init>(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.RightHandSideOfIn[_]): org.squeryl.dsl.ast.InclusionOperator = {
      InclusionOperator.super.<init>(left, right, "in", true);
      ()
    };
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = if (InclusionOperator.this.right.isConstantEmptyList)
      sw.write("(1 = 0)")
    else
      InclusionOperator.super.doWrite(sw)
  };
  class ExclusionOperator extends BinaryOperatorNodeLogicalBoolean {
    <paramaccessor> private[this] val left: org.squeryl.dsl.ast.ExpressionNode = _;
    <paramaccessor> private[this] val right: org.squeryl.dsl.ast.RightHandSideOfIn[_] = _;
    def <init>(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.RightHandSideOfIn[_]): org.squeryl.dsl.ast.ExclusionOperator = {
      ExclusionOperator.super.<init>(left, right, "not in", true);
      ()
    }
  };
  class BinaryOperatorNodeLogicalBoolean extends BinaryOperatorNode with org.squeryl.dsl.ast.LogicalBoolean {
    <paramaccessor> private[this] val left: org.squeryl.dsl.ast.ExpressionNode = _;
    <paramaccessor> private[this] val right: org.squeryl.dsl.ast.ExpressionNode = _;
    <paramaccessor> private[this] val op: String = _;
    <paramaccessor> private[this] val rightArgInParent: Boolean = _;
    def <init>(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode, op: String, rightArgInParent: Boolean = false): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean = {
      BinaryOperatorNodeLogicalBoolean.super.<init>(left, right, op, ast.this.BinaryOperatorNode.<init>$default$4);
      ()
    };
    override def inhibited: Boolean = BinaryOperatorNodeLogicalBoolean.this._inhibitedByWhen.||(BinaryOperatorNodeLogicalBoolean.this.left match {
      case (_: org.squeryl.dsl.ast.LogicalBoolean) => BinaryOperatorNodeLogicalBoolean.this.left.inhibited.&&(BinaryOperatorNodeLogicalBoolean.this.right.inhibited)
      case _ => BinaryOperatorNodeLogicalBoolean.this.left.inhibited.||(BinaryOperatorNodeLogicalBoolean.this.right.inhibited)
    });
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      val nonInh: Iterator[org.squeryl.dsl.ast.ExpressionNode] = BinaryOperatorNodeLogicalBoolean.this.children.filter(((c: org.squeryl.dsl.ast.ExpressionNode) => c.inhibited.unary_!)).iterator;
      sw.write("(");
      nonInh.next().write(sw);
      sw.write(" ");
      if (nonInh.hasNext)
        {
          sw.write(BinaryOperatorNodeLogicalBoolean.this.operatorToken);
          if (BinaryOperatorNodeLogicalBoolean.this.newLineAfterOperator)
            sw.nextLine
          else
            ();
          sw.write(" ");
          if (BinaryOperatorNodeLogicalBoolean.this.rightArgInParent)
            sw.write("(")
          else
            ();
          nonInh.next().write(sw);
          if (BinaryOperatorNodeLogicalBoolean.this.rightArgInParent)
            sw.write(")")
          else
            ()
        }
      else
        ();
      sw.write(")")
    }
  };
  <synthetic> object BinaryOperatorNodeLogicalBoolean extends AnyRef {
    def <init>(): org.squeryl.dsl.ast.BinaryOperatorNodeLogicalBoolean.type = {
      BinaryOperatorNodeLogicalBoolean.super.<init>();
      ()
    };
    <synthetic> def <init>$default$4: Boolean @scala.annotation.unchecked.uncheckedVariance = false
  };
  class ExistsExpression extends PrefixOperatorNode with org.squeryl.dsl.ast.LogicalBoolean {
    <paramaccessor> private[this] val ast: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def ast: org.squeryl.dsl.ast.ExpressionNode = ExistsExpression.this.ast;
    <paramaccessor> private[this] val opType: String = _;
    <stable> <accessor> <paramaccessor> def opType: String = ExistsExpression.this.opType;
    def <init>(ast: org.squeryl.dsl.ast.ExpressionNode, opType: String): org.squeryl.dsl.ast.ExistsExpression = {
      ExistsExpression.super.<init>(ast, opType, false);
      ()
    }
  };
  class BetweenExpression extends TernaryOperatorNode with org.squeryl.dsl.ast.LogicalBoolean {
    <paramaccessor> private[this] val first: org.squeryl.dsl.ast.ExpressionNode = _;
    <paramaccessor> private[this] val second: org.squeryl.dsl.ast.ExpressionNode = _;
    <paramaccessor> private[this] val third: org.squeryl.dsl.ast.ExpressionNode = _;
    def <init>(first: org.squeryl.dsl.ast.ExpressionNode, second: org.squeryl.dsl.ast.ExpressionNode, third: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.BetweenExpression = {
      BetweenExpression.super.<init>(first, second, third, "between");
      ()
    };
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      BetweenExpression.this.first.write(sw);
      sw.write(" between ");
      BetweenExpression.this.second.write(sw);
      sw.write(" and ");
      BetweenExpression.this.third.write(sw)
    }
  };
  class TernaryOperatorNode extends FunctionNode with org.squeryl.dsl.ast.LogicalBoolean {
    <paramaccessor> private[this] val first: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def first: org.squeryl.dsl.ast.ExpressionNode = TernaryOperatorNode.this.first;
    <paramaccessor> private[this] val second: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def second: org.squeryl.dsl.ast.ExpressionNode = TernaryOperatorNode.this.second;
    <paramaccessor> private[this] val third: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def third: org.squeryl.dsl.ast.ExpressionNode = TernaryOperatorNode.this.third;
    <paramaccessor> private[this] val op: String = _;
    def <init>(first: org.squeryl.dsl.ast.ExpressionNode, second: org.squeryl.dsl.ast.ExpressionNode, third: org.squeryl.dsl.ast.ExpressionNode, op: String): org.squeryl.dsl.ast.TernaryOperatorNode = {
      TernaryOperatorNode.super.<init>(op, collection.this.Seq.apply[org.squeryl.dsl.ast.ExpressionNode](first, second, third));
      ()
    };
    override def inhibited: Boolean = TernaryOperatorNode.this._inhibitedByWhen.||(TernaryOperatorNode.this.first.inhibited).||(TernaryOperatorNode.this.second.inhibited).||(TernaryOperatorNode.this.third.inhibited)
  };
  abstract trait LogicalBoolean extends Object with org.squeryl.dsl.ast.ExpressionNode {
    def /*LogicalBoolean*/$init$(): Unit = {
      ()
    };
    def and(b: org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.ast.LogicalBoolean = new BinaryOperatorNodeLogicalBoolean(this, b, "and", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def or(b: org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.ast.LogicalBoolean = new BinaryOperatorNodeLogicalBoolean(this, b, "or", ast.this.BinaryOperatorNodeLogicalBoolean.<init>$default$4);
    def and(b: Option[org.squeryl.dsl.ast.LogicalBoolean]): org.squeryl.dsl.ast.LogicalBoolean = b.map[org.squeryl.dsl.ast.LogicalBoolean](((x$1: org.squeryl.dsl.ast.LogicalBoolean) => this.and(x$1))).getOrElse[org.squeryl.dsl.ast.LogicalBoolean](this);
    def or(b: Option[org.squeryl.dsl.ast.LogicalBoolean]): org.squeryl.dsl.ast.LogicalBoolean = b.map[org.squeryl.dsl.ast.LogicalBoolean](((x$2: org.squeryl.dsl.ast.LogicalBoolean) => this.or(x$2))).getOrElse[org.squeryl.dsl.ast.LogicalBoolean](this)
  };
  object TrueLogicalBoolean extends Object with org.squeryl.dsl.ast.LogicalBoolean {
    def <init>(): org.squeryl.dsl.ast.TrueLogicalBoolean.type = {
      TrueLogicalBoolean.super.<init>();
      ()
    };
    override def and(b: org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.ast.LogicalBoolean = b;
    override def or(b: org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.ast.TrueLogicalBoolean.type = this;
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.write("(1=1)")
  };
  object FalseLogicalBoolean extends Object with org.squeryl.dsl.ast.LogicalBoolean {
    def <init>(): org.squeryl.dsl.ast.FalseLogicalBoolean.type = {
      FalseLogicalBoolean.super.<init>();
      ()
    };
    override def and(b: org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.ast.FalseLogicalBoolean.type = this;
    override def or(b: org.squeryl.dsl.ast.LogicalBoolean): org.squeryl.dsl.ast.LogicalBoolean = b;
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.write("(1=0)")
  };
  object LogicalBoolean extends scala.AnyRef {
    def <init>(): org.squeryl.dsl.ast.LogicalBoolean.type = {
      LogicalBoolean.super.<init>();
      ()
    };
    def and(conditions: Seq[org.squeryl.dsl.ast.LogicalBoolean]): org.squeryl.dsl.ast.LogicalBoolean = conditions.fold[org.squeryl.dsl.ast.LogicalBoolean](TrueLogicalBoolean)(((x$3: org.squeryl.dsl.ast.LogicalBoolean, x$4: org.squeryl.dsl.ast.LogicalBoolean) => x$3.and(x$4)));
    def or(conditions: Seq[org.squeryl.dsl.ast.LogicalBoolean]): org.squeryl.dsl.ast.LogicalBoolean = conditions.fold[org.squeryl.dsl.ast.LogicalBoolean](FalseLogicalBoolean)(((x$5: org.squeryl.dsl.ast.LogicalBoolean, x$6: org.squeryl.dsl.ast.LogicalBoolean) => x$5.or(x$6)))
  };
  class UpdateAssignment extends scala.AnyRef {
    <paramaccessor> private[this] val left: org.squeryl.internals.FieldMetaData = _;
    <stable> <accessor> <paramaccessor> def left: org.squeryl.internals.FieldMetaData = UpdateAssignment.this.left;
    <paramaccessor> private[this] val right: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def right: org.squeryl.dsl.ast.ExpressionNode = UpdateAssignment.this.right;
    def <init>(left: org.squeryl.internals.FieldMetaData, right: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.UpdateAssignment = {
      UpdateAssignment.super.<init>();
      ()
    }
  };
  abstract trait BaseColumnAttributeAssignment extends scala.AnyRef {
    def /*BaseColumnAttributeAssignment*/$init$(): Unit = {
      ()
    };
    def clearColumnAttributes: Unit;
    def isIdFieldOfKeyedEntity: Boolean;
    def isIdFieldOfKeyedEntityWithoutUniquenessConstraint: Boolean = BaseColumnAttributeAssignment.this.isIdFieldOfKeyedEntity.&&(BaseColumnAttributeAssignment.this.columnAttributes.exists(((x$7: org.squeryl.internals.ColumnAttribute) => x$7.isInstanceOf[org.squeryl.internals.PrimaryKey])).||(BaseColumnAttributeAssignment.this.columnAttributes.exists(((x$8: org.squeryl.internals.ColumnAttribute) => x$8.isInstanceOf[org.squeryl.internals.Unique]))).unary_!);
    def columnAttributes: Seq[org.squeryl.internals.ColumnAttribute];
    def hasAttribute[A >: Nothing <: org.squeryl.internals.ColumnAttribute](implicit m: Manifest[A]): Boolean = BaseColumnAttributeAssignment.this.findAttribute[A](m).!=(scala.None);
    def findAttribute[A >: Nothing <: org.squeryl.internals.ColumnAttribute](implicit m: Manifest[A]): Option[org.squeryl.internals.ColumnAttribute] = BaseColumnAttributeAssignment.this.columnAttributes.find(((ca: org.squeryl.internals.ColumnAttribute) => m.erasure.isAssignableFrom(ca.getClass())))
  };
  class ColumnGroupAttributeAssignment extends Object with org.squeryl.dsl.ast.BaseColumnAttributeAssignment {
    <paramaccessor> private[this] val cols: Seq[org.squeryl.internals.FieldMetaData] = _;
    <paramaccessor> private[this] val columnAttributes_: Seq[org.squeryl.internals.ColumnAttribute] = _;
    def <init>(cols: Seq[org.squeryl.internals.FieldMetaData], columnAttributes_: Seq[org.squeryl.internals.ColumnAttribute]): org.squeryl.dsl.ast.ColumnGroupAttributeAssignment = {
      ColumnGroupAttributeAssignment.super.<init>();
      ()
    };
    private[this] val _columnAttributes: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.ColumnAttribute] = new scala.collection.mutable.ArrayBuffer[org.squeryl.internals.ColumnAttribute]();
    <stable> <accessor> private def _columnAttributes: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.ColumnAttribute] = ColumnGroupAttributeAssignment.this._columnAttributes;
    ColumnGroupAttributeAssignment.this._columnAttributes.appendAll(ColumnGroupAttributeAssignment.this.columnAttributes_);
    def columnAttributes: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.ColumnAttribute] = ColumnGroupAttributeAssignment.this._columnAttributes;
    def addAttribute(a: org.squeryl.internals.ColumnAttribute): Unit = ColumnGroupAttributeAssignment.this._columnAttributes.append(a);
    def clearColumnAttributes: Unit = ColumnGroupAttributeAssignment.this.columns.foreach[Unit](((x$9: org.squeryl.internals.FieldMetaData) => x$9._clearColumnAttributes));
    def columns: Seq[org.squeryl.internals.FieldMetaData] = ColumnGroupAttributeAssignment.this.cols;
    def isIdFieldOfKeyedEntity: Boolean = false;
    def name: Option[String] = scala.None
  };
  class CompositeKeyAttributeAssignment extends ColumnGroupAttributeAssignment {
    <paramaccessor> private[this] val group: org.squeryl.dsl.CompositeKey = _;
    <stable> <accessor> <paramaccessor> def group: org.squeryl.dsl.CompositeKey = CompositeKeyAttributeAssignment.this.group;
    <paramaccessor> private[this] val _columnAttributes: Seq[org.squeryl.internals.ColumnAttribute] = _;
    def <init>(group: org.squeryl.dsl.CompositeKey, _columnAttributes: Seq[org.squeryl.internals.ColumnAttribute]): org.squeryl.dsl.ast.CompositeKeyAttributeAssignment = {
      CompositeKeyAttributeAssignment.super.<init>(group._fields, _columnAttributes);
      ()
    };
    override def isIdFieldOfKeyedEntity: Boolean = {
      val fmdHead: org.squeryl.internals.FieldMetaData = CompositeKeyAttributeAssignment.this.group._fields.head;
      fmdHead.parentMetaData.viewOrTable.ked.map[Boolean](((x$10: org.squeryl.KeyedEntityDef[_$1, _]) => x$10.idPropertyName.==(CompositeKeyAttributeAssignment.this.group._propertyName))).getOrElse[Boolean](false)
    };
    scala.this.Predef.assert(CompositeKeyAttributeAssignment.this.group._propertyName.!=(scala.None));
    override def name: Option[String] = CompositeKeyAttributeAssignment.this.group._propertyName
  };
  class ColumnAttributeAssignment extends Object with org.squeryl.dsl.ast.BaseColumnAttributeAssignment {
    <paramaccessor> private[this] val left: org.squeryl.internals.FieldMetaData = _;
    <stable> <accessor> <paramaccessor> def left: org.squeryl.internals.FieldMetaData = ColumnAttributeAssignment.this.left;
    <paramaccessor> private[this] val columnAttributes: Seq[org.squeryl.internals.ColumnAttribute] = _;
    <stable> <accessor> <paramaccessor> def columnAttributes: Seq[org.squeryl.internals.ColumnAttribute] = ColumnAttributeAssignment.this.columnAttributes;
    def <init>(left: org.squeryl.internals.FieldMetaData, columnAttributes: Seq[org.squeryl.internals.ColumnAttribute]): org.squeryl.dsl.ast.ColumnAttributeAssignment = {
      ColumnAttributeAssignment.super.<init>();
      ()
    };
    def clearColumnAttributes: Unit = ColumnAttributeAssignment.this.left._clearColumnAttributes;
    def isIdFieldOfKeyedEntity: Boolean = ColumnAttributeAssignment.this.left.isIdFieldOfKeyedEntity
  };
  class DefaultValueAssignment extends Object with org.squeryl.dsl.ast.BaseColumnAttributeAssignment {
    <paramaccessor> private[this] val left: org.squeryl.internals.FieldMetaData = _;
    <stable> <accessor> <paramaccessor> def left: org.squeryl.internals.FieldMetaData = DefaultValueAssignment.this.left;
    <paramaccessor> private[this] val value: org.squeryl.dsl.TypedExpression[_, _] = _;
    <stable> <accessor> <paramaccessor> def value: org.squeryl.dsl.TypedExpression[_, _] = DefaultValueAssignment.this.value;
    def <init>(left: org.squeryl.internals.FieldMetaData, value: org.squeryl.dsl.TypedExpression[_, _]): org.squeryl.dsl.ast.DefaultValueAssignment = {
      DefaultValueAssignment.super.<init>();
      ()
    };
    def isIdFieldOfKeyedEntity: Boolean = DefaultValueAssignment.this.left.isIdFieldOfKeyedEntity;
    def clearColumnAttributes: Unit = DefaultValueAssignment.this.left._clearColumnAttributes;
    def columnAttributes: scala.collection.immutable.Nil.type = immutable.this.Nil
  };
  class TokenExpressionNode extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val token: String = _;
    <stable> <accessor> <paramaccessor> def token: String = TokenExpressionNode.this.token;
    def <init>(token: String): org.squeryl.dsl.ast.TokenExpressionNode = {
      TokenExpressionNode.super.<init>();
      ()
    };
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.write(TokenExpressionNode.this.token)
  };
  class InputOnlyConstantExpressionNode extends org.squeryl.dsl.ast.ConstantTypedExpression[Any,Any] {
    <paramaccessor> private[this] val v: Any = _;
    def <init>(v: Any): org.squeryl.dsl.ast.InputOnlyConstantExpressionNode = {
      InputOnlyConstantExpressionNode.super.<init>(v, v.asInstanceOf[AnyRef], scala.None);
      ()
    }
  };
  class ConstantTypedExpression[A1 >: Nothing <: Any, T1 >: Nothing <: Any] extends Object with org.squeryl.dsl.TypedExpression[A1,T1] {
    <paramaccessor> private[this] val value: A1 = _;
    <stable> <accessor> <paramaccessor> def value: A1 = ConstantTypedExpression.this.value;
    <paramaccessor> private[this] val nativeJdbcValue: AnyRef = _;
    <stable> <accessor> <paramaccessor> def nativeJdbcValue: AnyRef = ConstantTypedExpression.this.nativeJdbcValue;
    <paramaccessor> private[this] val i: Option[org.squeryl.dsl.TypedExpressionFactory[A1, _]] = _;
    def <init>(value: A1, nativeJdbcValue: AnyRef, i: Option[org.squeryl.dsl.TypedExpressionFactory[A1, _]]): org.squeryl.dsl.ast.ConstantTypedExpression[A1,T1] = {
      ConstantTypedExpression.super.<init>();
      ()
    };
    private def needsQuote: Boolean = ConstantTypedExpression.this.value.isInstanceOf[String];
    override def mapper: org.squeryl.internals.OutMapper[A1] = ConstantTypedExpression.this.i.get.createOutMapper;
    override def sample: A1 = if (ConstantTypedExpression.this.value.!=(null))
      ConstantTypedExpression.this.value
    else
      ConstantTypedExpression.this.i.get.sample;
    def jdbcClass: Class[_ <: AnyRef] = ConstantTypedExpression.this.i.map[AnyRef](((x$11: org.squeryl.dsl.TypedExpressionFactory[A1, _]) => x$11.jdbcSample)).getOrElse[AnyRef](ConstantTypedExpression.this.nativeJdbcValue).getClass();
    if (ConstantTypedExpression.this.nativeJdbcValue.!=(null))
      ConstantTypedExpression.this.nativeJdbcValue.getClass()
    else
      ConstantTypedExpression.this.mapper.jdbcClass;
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = if (sw.isForDisplay)
      sw.write(ConstantTypedExpression.this.displayAsString)
    else
      {
        sw.write("?");
        sw.addParam(org.squeryl.internals.ConstantStatementParam.apply(this))
      };
    def displayAsString: String = if (ConstantTypedExpression.this.value.==(null))
      "null"
    else
      if (ConstantTypedExpression.this.needsQuote)
        "\'".+(ConstantTypedExpression.this.value.toString()).+("\'")
      else
        ConstantTypedExpression.this.value.toString();
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("ConstantTypedExpression")).+(":").+(ConstantTypedExpression.this.value)
  };
  class ConstantExpressionNodeList[T >: Nothing <: Any] extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val value: Traversable[T] = _;
    <stable> <accessor> <paramaccessor> def value: Traversable[T] = ConstantExpressionNodeList.this.value;
    <paramaccessor> private[this] val mapper: org.squeryl.internals.OutMapper[_] = _;
    def <init>(value: Traversable[T], mapper: org.squeryl.internals.OutMapper[_]): org.squeryl.dsl.ast.ConstantExpressionNodeList[T] = {
      ConstantExpressionNodeList.super.<init>();
      ()
    };
    def isEmpty: Boolean = ConstantExpressionNodeList.this.value.==(immutable.this.Nil);
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = if (sw.isForDisplay)
      sw.write(ConstantExpressionNodeList.this.value.map[String, Traversable[String]](((e: T) => "\'".+(e).+("\'")))(collection.this.Traversable.canBuildFrom[String]).mkString(","))
    else
      {
        sw.write(ConstantExpressionNodeList.this.value.toSeq.map[String, Seq[String]](((z: T) => "?"))(collection.this.Seq.canBuildFrom[String]).mkString(","));
        ConstantExpressionNodeList.this.value.foreach[Unit](((z: T) => sw.addParam(org.squeryl.internals.ConstantExpressionNodeListParam.apply(z.asInstanceOf[AnyRef], ConstantExpressionNodeList.this))))
      }
  };
  class FunctionNode extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val name: String = _;
    <stable> <accessor> <paramaccessor> def name: String = FunctionNode.this.name;
    <paramaccessor> private[this] val args: Seq[org.squeryl.dsl.ast.ExpressionNode] = _;
    <stable> <accessor> <paramaccessor> def args: Seq[org.squeryl.dsl.ast.ExpressionNode] = FunctionNode.this.args;
    def <init>(name: String, args: Seq[org.squeryl.dsl.ast.ExpressionNode]): org.squeryl.dsl.ast.FunctionNode = {
      FunctionNode.super.<init>();
      ()
    };
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write(FunctionNode.this.name);
      sw.write("(");
      sw.writeNodesWithSeparator(FunctionNode.this.args, ",", false);
      sw.write(")")
    };
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = FunctionNode.this.args.toList
  };
  class PostfixOperatorNode extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val token: String = _;
    <stable> <accessor> <paramaccessor> def token: String = PostfixOperatorNode.this.token;
    <paramaccessor> private[this] val arg: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def arg: org.squeryl.dsl.ast.ExpressionNode = PostfixOperatorNode.this.arg;
    def <init>(token: String, arg: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.PostfixOperatorNode = {
      PostfixOperatorNode.super.<init>();
      ()
    };
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      PostfixOperatorNode.this.arg.write(sw);
      sw.write(" ");
      sw.write(PostfixOperatorNode.this.token)
    };
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = immutable.this.List.apply[org.squeryl.dsl.ast.ExpressionNode](PostfixOperatorNode.this.arg)
  };
  class TypeConversion extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val e: org.squeryl.dsl.ast.ExpressionNode = _;
    def <init>(e: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.TypeConversion = {
      TypeConversion.super.<init>();
      ()
    };
    override def inhibited: Boolean = TypeConversion.this.e.inhibited;
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = TypeConversion.this.e.doWrite(sw);
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = TypeConversion.this.e.children
  };
  class BinaryOperatorNode extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val left: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def left: org.squeryl.dsl.ast.ExpressionNode = BinaryOperatorNode.this.left;
    <paramaccessor> private[this] val right: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def right: org.squeryl.dsl.ast.ExpressionNode = BinaryOperatorNode.this.right;
    <paramaccessor> private[this] val operatorToken: String = _;
    <stable> <accessor> <paramaccessor> def operatorToken: String = BinaryOperatorNode.this.operatorToken;
    <paramaccessor> private[this] val newLineAfterOperator: Boolean = _;
    <stable> <accessor> <paramaccessor> def newLineAfterOperator: Boolean = BinaryOperatorNode.this.newLineAfterOperator;
    def <init>(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode, operatorToken: String, newLineAfterOperator: Boolean = false): org.squeryl.dsl.ast.BinaryOperatorNode = {
      BinaryOperatorNode.super.<init>();
      ()
    };
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = immutable.this.List.apply[org.squeryl.dsl.ast.ExpressionNode](BinaryOperatorNode.this.left, BinaryOperatorNode.this.right);
    override def inhibited: Boolean = BinaryOperatorNode.this._inhibitedByWhen.||(BinaryOperatorNode.this.left.inhibited).||(BinaryOperatorNode.this.right.inhibited);
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("BinaryOperatorNode")).+(":").+(BinaryOperatorNode.this.operatorToken).+(BinaryOperatorNode.this.inhibitedFlagForAstDump);
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write("(");
      BinaryOperatorNode.this.left.write(sw);
      sw.write(" ");
      sw.write(BinaryOperatorNode.this.operatorToken);
      if (BinaryOperatorNode.this.newLineAfterOperator)
        sw.nextLine
      else
        ();
      sw.write(" ");
      BinaryOperatorNode.this.right.write(sw);
      sw.write(")")
    }
  };
  <synthetic> object BinaryOperatorNode extends AnyRef {
    def <init>(): org.squeryl.dsl.ast.BinaryOperatorNode.type = {
      BinaryOperatorNode.super.<init>();
      ()
    };
    <synthetic> def <init>$default$4: Boolean @scala.annotation.unchecked.uncheckedVariance = false
  };
  class PrefixOperatorNode extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val child: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def child: org.squeryl.dsl.ast.ExpressionNode = PrefixOperatorNode.this.child;
    <paramaccessor> private[this] val operatorToken: String = _;
    <stable> <accessor> <paramaccessor> def operatorToken: String = PrefixOperatorNode.this.operatorToken;
    <paramaccessor> private[this] val newLineAfterOperator: Boolean = _;
    <stable> <accessor> <paramaccessor> def newLineAfterOperator: Boolean = PrefixOperatorNode.this.newLineAfterOperator;
    def <init>(child: org.squeryl.dsl.ast.ExpressionNode, operatorToken: String, newLineAfterOperator: Boolean = false): org.squeryl.dsl.ast.PrefixOperatorNode = {
      PrefixOperatorNode.super.<init>();
      ()
    };
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = immutable.this.List.apply[org.squeryl.dsl.ast.ExpressionNode](PrefixOperatorNode.this.child);
    override def inhibited: Boolean = PrefixOperatorNode.this.child.inhibited;
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("PrefixOperatorNode")).+(":").+(PrefixOperatorNode.this.operatorToken).+(PrefixOperatorNode.this.inhibitedFlagForAstDump);
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      sw.write("(");
      sw.write(PrefixOperatorNode.this.operatorToken);
      if (PrefixOperatorNode.this.newLineAfterOperator)
        sw.nextLine
      else
        ();
      PrefixOperatorNode.this.child.write(sw);
      sw.write(")")
    }
  };
  <synthetic> object PrefixOperatorNode extends AnyRef {
    def <init>(): org.squeryl.dsl.ast.PrefixOperatorNode.type = {
      PrefixOperatorNode.super.<init>();
      ()
    };
    <synthetic> def <init>$default$3: Boolean @scala.annotation.unchecked.uncheckedVariance = false
  };
  class LeftOuterJoinNode extends BinaryOperatorNode {
    <paramaccessor> private[this] val left: org.squeryl.dsl.ast.ExpressionNode = _;
    <paramaccessor> private[this] val right: org.squeryl.dsl.ast.ExpressionNode = _;
    def <init>(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.LeftOuterJoinNode = {
      LeftOuterJoinNode.super.<init>(left, right, "left", false);
      ()
    };
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = ();
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("LeftOuterJoin")).+("")
  };
  class FullOuterJoinNode extends BinaryOperatorNode {
    <paramaccessor> private[this] val left: org.squeryl.dsl.ast.ExpressionNode = _;
    <paramaccessor> private[this] val right: org.squeryl.dsl.ast.ExpressionNode = _;
    def <init>(left: org.squeryl.dsl.ast.ExpressionNode, right: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.FullOuterJoinNode = {
      FullOuterJoinNode.super.<init>(left, right, "full", false);
      ()
    };
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("FullOuterJoin")).+("")
  };
  abstract trait UniqueIdInAliaseRequired extends scala.AnyRef {
    def /*UniqueIdInAliaseRequired*/$init$(): Unit = {
      ()
    };
    private[this] var uniqueId: Option[Int] = scala.None;
    <accessor> def uniqueId: Option[Int] = UniqueIdInAliaseRequired.this.uniqueId;
    <accessor> def uniqueId_=(x$1: Option[Int]): Unit = UniqueIdInAliaseRequired.this.uniqueId = x$1
  };
  abstract trait QueryableExpressionNode extends Object with org.squeryl.dsl.ast.ExpressionNode with org.squeryl.dsl.ast.UniqueIdInAliaseRequired {
    def /*QueryableExpressionNode*/$init$(): Unit = {
      ()
    };
    private[this] var _inhibited: Boolean = false;
    <accessor> private def _inhibited: Boolean = QueryableExpressionNode.this._inhibited;
    <accessor> private def _inhibited_=(x$1: Boolean): Unit = QueryableExpressionNode.this._inhibited = x$1;
    override def inhibited: Boolean = QueryableExpressionNode.this._inhibited;
    def inhibited_=(b: Boolean): Unit = QueryableExpressionNode.this._inhibited_=(b);
    def isMemberOfJoinList: Boolean = QueryableExpressionNode.this.joinKind.!=(scala.None);
    private[this] var joinKind: Option[(String, String)] = scala.None;
    <accessor> def joinKind: Option[(String, String)] = QueryableExpressionNode.this.joinKind;
    <accessor> def joinKind_=(x$1: Option[(String, String)]): Unit = QueryableExpressionNode.this.joinKind = x$1;
    def isOuterJoined: Boolean = QueryableExpressionNode.this.joinKind.!=(scala.None).&&(QueryableExpressionNode.this.joinKind.get._2.==("outer"));
    private[this] var joinExpression: Option[org.squeryl.dsl.ast.LogicalBoolean] = scala.None;
    <accessor> def joinExpression: Option[org.squeryl.dsl.ast.LogicalBoolean] = QueryableExpressionNode.this.joinExpression;
    <accessor> def joinExpression_=(x$1: Option[org.squeryl.dsl.ast.LogicalBoolean]): Unit = QueryableExpressionNode.this.joinExpression = x$1;
    private[this] var outerJoinExpression: Option[org.squeryl.dsl.ast.OuterJoinExpression] = scala.None;
    <accessor> def outerJoinExpression: Option[org.squeryl.dsl.ast.OuterJoinExpression] = QueryableExpressionNode.this.outerJoinExpression;
    <accessor> def outerJoinExpression_=(x$1: Option[org.squeryl.dsl.ast.OuterJoinExpression]): Unit = QueryableExpressionNode.this.outerJoinExpression = x$1;
    private[this] var isRightJoined: Boolean = false;
    <accessor> def isRightJoined: Boolean = QueryableExpressionNode.this.isRightJoined;
    <accessor> def isRightJoined_=(x$1: Boolean): Unit = QueryableExpressionNode.this.isRightJoined = x$1;
    def isChild(q: org.squeryl.dsl.ast.QueryableExpressionNode): Boolean;
    def owns(aSample: AnyRef): Boolean;
    def alias: String;
    def getOrCreateSelectElement(fmd: org.squeryl.internals.FieldMetaData, forScope: org.squeryl.dsl.ast.QueryExpressionElements): org.squeryl.dsl.ast.SelectElement;
    def getOrCreateAllSelectElements(forScope: org.squeryl.dsl.ast.QueryExpressionElements): Iterable[org.squeryl.dsl.ast.SelectElement];
    def dumpAst: String = {
      val sb: StringBuffer = new java.this.lang.StringBuffer();
      QueryableExpressionNode.this.visitDescendants(((n: org.squeryl.dsl.ast.ExpressionNode, parent: Option[org.squeryl.dsl.ast.ExpressionNode], d: Int) => {
        val c: Int = 4.*(d);
        scala.this.Predef.intWrapper(1).to(c).foreach[StringBuffer](((i: Int) => sb.append(' ')));
        sb.append(n);
        {
          sb.append("\n");
          ()
        }
      }));
      sb.toString()
    }
  };
  class OrderByArg extends scala.AnyRef {
    <paramaccessor> private[this] val e: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def e: org.squeryl.dsl.ast.ExpressionNode = OrderByArg.this.e;
    def <init>(e: org.squeryl.dsl.ast.ExpressionNode): org.squeryl.dsl.ast.OrderByArg = {
      OrderByArg.super.<init>();
      ()
    };
    private[this] var _ascending: Boolean = true;
    <accessor> private def _ascending: Boolean = OrderByArg.this._ascending;
    <accessor> private def _ascending_=(x$1: Boolean): Unit = OrderByArg.this._ascending = x$1;
    private[squeryl] def isAscending: Boolean = OrderByArg.this._ascending;
    def asc: org.squeryl.dsl.ast.OrderByArg = {
      OrderByArg.this._ascending_=(true);
      this
    };
    def desc: org.squeryl.dsl.ast.OrderByArg = {
      OrderByArg.this._ascending_=(false);
      this
    }
  };
  class OrderByExpression extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val a: org.squeryl.dsl.ast.OrderByArg = _;
    def <init>(a: org.squeryl.dsl.ast.OrderByArg): org.squeryl.dsl.ast.OrderByExpression = {
      OrderByExpression.super.<init>();
      ()
    };
    private def e: org.squeryl.dsl.ast.ExpressionNode = OrderByExpression.this.a.e;
    override def inhibited: Boolean = OrderByExpression.this.e.inhibited;
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = {
      OrderByExpression.this.e.write(sw);
      if (OrderByExpression.this.a.isAscending)
        sw.write(" Asc")
      else
        sw.write(" Desc")
    };
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = immutable.this.List.apply[org.squeryl.dsl.ast.ExpressionNode](OrderByExpression.this.e);
    def inverse: org.squeryl.dsl.ast.OrderByExpression = {
      val aCopy: org.squeryl.dsl.ast.OrderByArg = new OrderByArg(OrderByExpression.this.a.e);
      if (aCopy.isAscending)
        aCopy.desc
      else
        aCopy.asc;
      new OrderByExpression(aCopy)
    }
  };
  class DummyExpressionHolder extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val renderedExpression: String = _;
    <stable> <accessor> <paramaccessor> def renderedExpression: String = DummyExpressionHolder.this.renderedExpression;
    def <init>(renderedExpression: String): org.squeryl.dsl.ast.DummyExpressionHolder = {
      DummyExpressionHolder.super.<init>();
      ()
    };
    def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = sw.write(DummyExpressionHolder.this.renderedExpression)
  };
  class RightHandSideOfIn[A >: Nothing <: Any] extends Object with org.squeryl.dsl.ast.ExpressionNode {
    <paramaccessor> private[this] val ast: org.squeryl.dsl.ast.ExpressionNode = _;
    <stable> <accessor> <paramaccessor> def ast: org.squeryl.dsl.ast.ExpressionNode = RightHandSideOfIn.this.ast;
    <paramaccessor> private[this] val isIn: Option[Boolean] = _;
    <stable> <accessor> <paramaccessor> def isIn: Option[Boolean] = RightHandSideOfIn.this.isIn;
    def <init>(ast: org.squeryl.dsl.ast.ExpressionNode, isIn: Option[Boolean] = scala.None): org.squeryl.dsl.ast.RightHandSideOfIn[A] = {
      RightHandSideOfIn.super.<init>();
      ()
    };
    def toIn: org.squeryl.dsl.ast.RightHandSideOfIn[A] = new org.squeryl.dsl.ast.RightHandSideOfIn[A](RightHandSideOfIn.this.ast, scala.Some.apply[Boolean](true));
    def toNotIn: org.squeryl.dsl.ast.RightHandSideOfIn[A] = new org.squeryl.dsl.ast.RightHandSideOfIn[A](RightHandSideOfIn.this.ast, scala.Some.apply[Boolean](false));
    override def children: List[org.squeryl.dsl.ast.ExpressionNode] = immutable.this.List.apply[org.squeryl.dsl.ast.ExpressionNode](RightHandSideOfIn.this.ast);
    override def inhibited: Boolean = RightHandSideOfIn.super.inhibited.||(RightHandSideOfIn.this.isConstantEmptyList.&&(RightHandSideOfIn.this.isIn.get.unary_!));
    def isConstantEmptyList: Boolean = if (RightHandSideOfIn.this.ast.isInstanceOf[org.squeryl.dsl.ast.ConstantExpressionNodeList[_]])
      RightHandSideOfIn.this.ast.asInstanceOf[org.squeryl.dsl.ast.ConstantExpressionNodeList[_]].isEmpty
    else
      false;
    override def doWrite(sw: org.squeryl.internals.StatementWriter): Unit = if (RightHandSideOfIn.this.isConstantEmptyList.&&(RightHandSideOfIn.this.isIn.get))
      sw.write("1 = 0")
    else
      RightHandSideOfIn.this.ast.doWrite(sw)
  };
  <synthetic> object RightHandSideOfIn extends AnyRef {
    def <init>(): org.squeryl.dsl.ast.RightHandSideOfIn.type = {
      RightHandSideOfIn.super.<init>();
      ()
    };
    <synthetic> def <init>$default$2[A >: Nothing <: Any]: Option[Boolean] @scala.annotation.unchecked.uncheckedVariance = scala.None
  }
}