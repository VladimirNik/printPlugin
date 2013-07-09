package org.squeryl.dsl {
  import ast._;
  import scala.collection.mutable.ArrayBuffer;
  import org.squeryl.Schema;
  import org.squeryl.internals.{AttributeValidOnMultipleColumn, ColumnAttribute, FieldMetaData};
  abstract trait CompositeKey extends scala.AnyRef {
    def /*CompositeKey*/$init$(): Unit = {
      ()
    };
    private[this] var _members: Option[Seq[org.squeryl.dsl.ast.SelectElementReference[_, _]]] = scala.None;
    <accessor> private[squeryl] def _members: Option[Seq[org.squeryl.dsl.ast.SelectElementReference[_, _]]] = CompositeKey.this._members;
    <accessor> private[squeryl] def _members_=(x$1: Option[Seq[org.squeryl.dsl.ast.SelectElementReference[_, _]]]): Unit = CompositeKey.this._members = x$1;
    private[this] var _propertyName: Option[String] = scala.None;
    <accessor> private[squeryl] def _propertyName: Option[String] = CompositeKey.this._propertyName;
    <accessor> private[squeryl] def _propertyName_=(x$1: Option[String]): Unit = CompositeKey.this._propertyName = x$1;
    private[squeryl] def _fields: Seq[org.squeryl.internals.FieldMetaData] = if (CompositeKey.this._members.==(scala.None))
      immutable.this.List.empty[Nothing]
    else
      CompositeKey.this._members.get.map[org.squeryl.internals.FieldMetaData, Seq[org.squeryl.internals.FieldMetaData]](((x$1: org.squeryl.dsl.ast.SelectElementReference[_, _]) => x$1.selectElement.asInstanceOf[org.squeryl.dsl.ast.FieldSelectElement].fieldMetaData))(collection.this.Seq.canBuildFrom[org.squeryl.internals.FieldMetaData]);
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]];
    protected def members: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = CompositeKey.this._members.getOrElse[Iterable[org.squeryl.dsl.TypedExpression[_, _]]](CompositeKey.this.constantMembers);
    private[squeryl] def buildEquality(ck: org.squeryl.dsl.CompositeKey): org.squeryl.dsl.ast.LogicalBoolean = {
      val equalities: Iterable[org.squeryl.dsl.ast.EqualityExpression] = CompositeKey.this.members.zip[org.squeryl.dsl.TypedExpression[_, _], org.squeryl.dsl.TypedExpression[_, _], Iterable[(org.squeryl.dsl.TypedExpression[_, _], org.squeryl.dsl.TypedExpression[_, _])]](ck.members)(collection.this.Iterable.canBuildFrom[(org.squeryl.dsl.TypedExpression[_, _], org.squeryl.dsl.TypedExpression[_, _])]).map[org.squeryl.dsl.ast.EqualityExpression, Iterable[org.squeryl.dsl.ast.EqualityExpression]](((t: (org.squeryl.dsl.TypedExpression[_, _], org.squeryl.dsl.TypedExpression[_, _])) => new ast.EqualityExpression(t._1, t._2)))(collection.this.Iterable.canBuildFrom[org.squeryl.dsl.ast.EqualityExpression]);
      val head: org.squeryl.dsl.ast.EqualityExpression = equalities.head;
      val tail: Iterable[org.squeryl.dsl.ast.EqualityExpression] = equalities.tail;
      tail.foldLeft[org.squeryl.dsl.ast.LogicalBoolean]((equalities.head: org.squeryl.dsl.ast.LogicalBoolean))(((x$2: org.squeryl.dsl.ast.LogicalBoolean, x$3: org.squeryl.dsl.ast.EqualityExpression) => x$2.and(x$3)))
    };
    def is(attributes: org.squeryl.internals.AttributeValidOnMultipleColumn*): org.squeryl.dsl.ast.CompositeKeyAttributeAssignment = new ast.CompositeKeyAttributeAssignment(this, attributes)
  };
  case class CompositeKey2[A1 >: Nothing <: Any, A2 >: Nothing <: Any] extends Object with org.squeryl.dsl.CompositeKey with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val a1: A1 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a1: A1 = CompositeKey2.this.a1;
    <caseaccessor> <paramaccessor> private[this] val a2: A2 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a2: A2 = CompositeKey2.this.a2;
    def <init>(a1: A1, a2: A2): org.squeryl.dsl.CompositeKey2[A1,A2] = {
      CompositeKey2.super.<init>();
      ()
    };
    def ===(ck: org.squeryl.dsl.CompositeKey2[A1,A2]): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey2.this.buildEquality(ck);
    def ===(ck: (A1, A2)): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey2.this.buildEquality(new org.squeryl.dsl.CompositeKey2[A1,A2](ck._1, ck._2));
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = immutable.this.List.apply[org.squeryl.dsl.ast.InputOnlyConstantExpressionNode](new ast.InputOnlyConstantExpressionNode(CompositeKey2.this.a1), new ast.InputOnlyConstantExpressionNode(CompositeKey2.this.a2));
    <synthetic> def copy[A1 >: Nothing <: Any, A2 >: Nothing <: Any](a1: A1 = a1, a2: A2 = a2): org.squeryl.dsl.CompositeKey2[A1,A2] = new org.squeryl.dsl.CompositeKey2[A1,A2](a1, a2);
    <synthetic> def copy$default$1[A1 >: Nothing <: Any, A2 >: Nothing <: Any]: A1 @scala.annotation.unchecked.uncheckedVariance = CompositeKey2.this.a1;
    <synthetic> def copy$default$2[A1 >: Nothing <: Any, A2 >: Nothing <: Any]: A2 @scala.annotation.unchecked.uncheckedVariance = CompositeKey2.this.a2;
    override <synthetic> def productPrefix: String = "CompositeKey2";
    <synthetic> def productArity: Int = 2;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => CompositeKey2.this.a1
      case 1 => CompositeKey2.this.a2
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](CompositeKey2.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.dsl.CompositeKey2[_, _]]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(CompositeKey2.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(CompositeKey2.this);
    override <synthetic> def equals(x$1: Any): Boolean = CompositeKey2.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.dsl.CompositeKey2[_, _]].&&({
      <synthetic> val CompositeKey2$1: org.squeryl.dsl.CompositeKey2[A1,A2] = x$1.asInstanceOf[org.squeryl.dsl.CompositeKey2[A1,A2]];
      CompositeKey2.this.a1.==(CompositeKey2$1.a1).&&(CompositeKey2.this.a2.==(CompositeKey2$1.a2)).&&(CompositeKey2$1.canEqual(CompositeKey2.this))
    }))
  };
  <synthetic> object CompositeKey2 extends AnyRef with Serializable {
    def <init>(): org.squeryl.dsl.CompositeKey2.type = {
      CompositeKey2.super.<init>();
      ()
    };
    final override def toString(): String = "CompositeKey2";
    case <synthetic> def apply[A1 >: Nothing <: Any, A2 >: Nothing <: Any](a1: A1, a2: A2): org.squeryl.dsl.CompositeKey2[A1,A2] = new org.squeryl.dsl.CompositeKey2[A1,A2](a1, a2);
    case <synthetic> def unapply[A1 >: Nothing <: Any, A2 >: Nothing <: Any](x$0: org.squeryl.dsl.CompositeKey2[A1,A2]): Option[(A1, A2)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(A1, A2)](Tuple2.apply[A1, A2](x$0.a1, x$0.a2));
    <synthetic> private def readResolve(): Object = dsl.this.CompositeKey2
  };
  case class CompositeKey3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any] extends Object with org.squeryl.dsl.CompositeKey with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val a1: A1 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a1: A1 = CompositeKey3.this.a1;
    <caseaccessor> <paramaccessor> private[this] val a2: A2 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a2: A2 = CompositeKey3.this.a2;
    <caseaccessor> <paramaccessor> private[this] val a3: A3 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a3: A3 = CompositeKey3.this.a3;
    def <init>(a1: A1, a2: A2, a3: A3): org.squeryl.dsl.CompositeKey3[A1,A2,A3] = {
      CompositeKey3.super.<init>();
      ()
    };
    def ===(ck: org.squeryl.dsl.CompositeKey3[A1,A2,A3]): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey3.this.buildEquality(ck);
    def ===(ck: (A1, A2, A3)): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey3.this.buildEquality(new org.squeryl.dsl.CompositeKey3[A1,A2,A3](ck._1, ck._2, ck._3));
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = immutable.this.List.apply[org.squeryl.dsl.ast.InputOnlyConstantExpressionNode](new ast.InputOnlyConstantExpressionNode(CompositeKey3.this.a1), new ast.InputOnlyConstantExpressionNode(CompositeKey3.this.a2), new ast.InputOnlyConstantExpressionNode(CompositeKey3.this.a3));
    <synthetic> def copy[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any](a1: A1 = a1, a2: A2 = a2, a3: A3 = a3): org.squeryl.dsl.CompositeKey3[A1,A2,A3] = new org.squeryl.dsl.CompositeKey3[A1,A2,A3](a1, a2, a3);
    <synthetic> def copy$default$1[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any]: A1 @scala.annotation.unchecked.uncheckedVariance = CompositeKey3.this.a1;
    <synthetic> def copy$default$2[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any]: A2 @scala.annotation.unchecked.uncheckedVariance = CompositeKey3.this.a2;
    <synthetic> def copy$default$3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any]: A3 @scala.annotation.unchecked.uncheckedVariance = CompositeKey3.this.a3;
    override <synthetic> def productPrefix: String = "CompositeKey3";
    <synthetic> def productArity: Int = 3;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => CompositeKey3.this.a1
      case 1 => CompositeKey3.this.a2
      case 2 => CompositeKey3.this.a3
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](CompositeKey3.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.dsl.CompositeKey3[_, _, _]]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(CompositeKey3.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(CompositeKey3.this);
    override <synthetic> def equals(x$1: Any): Boolean = CompositeKey3.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.dsl.CompositeKey3[_, _, _]].&&({
      <synthetic> val CompositeKey3$1: org.squeryl.dsl.CompositeKey3[A1,A2,A3] = x$1.asInstanceOf[org.squeryl.dsl.CompositeKey3[A1,A2,A3]];
      CompositeKey3.this.a1.==(CompositeKey3$1.a1).&&(CompositeKey3.this.a2.==(CompositeKey3$1.a2)).&&(CompositeKey3.this.a3.==(CompositeKey3$1.a3)).&&(CompositeKey3$1.canEqual(CompositeKey3.this))
    }))
  };
  <synthetic> object CompositeKey3 extends AnyRef with Serializable {
    def <init>(): org.squeryl.dsl.CompositeKey3.type = {
      CompositeKey3.super.<init>();
      ()
    };
    final override def toString(): String = "CompositeKey3";
    case <synthetic> def apply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any](a1: A1, a2: A2, a3: A3): org.squeryl.dsl.CompositeKey3[A1,A2,A3] = new org.squeryl.dsl.CompositeKey3[A1,A2,A3](a1, a2, a3);
    case <synthetic> def unapply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any](x$0: org.squeryl.dsl.CompositeKey3[A1,A2,A3]): Option[(A1, A2, A3)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(A1, A2, A3)](Tuple3.apply[A1, A2, A3](x$0.a1, x$0.a2, x$0.a3));
    <synthetic> private def readResolve(): Object = dsl.this.CompositeKey3
  };
  case class CompositeKey4[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any] extends Object with org.squeryl.dsl.CompositeKey with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val a1: A1 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a1: A1 = CompositeKey4.this.a1;
    <caseaccessor> <paramaccessor> private[this] val a2: A2 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a2: A2 = CompositeKey4.this.a2;
    <caseaccessor> <paramaccessor> private[this] val a3: A3 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a3: A3 = CompositeKey4.this.a3;
    <caseaccessor> <paramaccessor> private[this] val a4: A4 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a4: A4 = CompositeKey4.this.a4;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4): org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4] = {
      CompositeKey4.super.<init>();
      ()
    };
    def ===(ck: org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4]): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey4.this.buildEquality(ck);
    def ===(ck: (A1, A2, A3, A4)): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey4.this.buildEquality(new org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4](ck._1, ck._2, ck._3, ck._4));
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = immutable.this.List.apply[org.squeryl.dsl.ast.InputOnlyConstantExpressionNode](new ast.InputOnlyConstantExpressionNode(CompositeKey4.this.a1), new ast.InputOnlyConstantExpressionNode(CompositeKey4.this.a2), new ast.InputOnlyConstantExpressionNode(CompositeKey4.this.a3), new ast.InputOnlyConstantExpressionNode(CompositeKey4.this.a4));
    <synthetic> def copy[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any](a1: A1 = a1, a2: A2 = a2, a3: A3 = a3, a4: A4 = a4): org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4] = new org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4](a1, a2, a3, a4);
    <synthetic> def copy$default$1[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any]: A1 @scala.annotation.unchecked.uncheckedVariance = CompositeKey4.this.a1;
    <synthetic> def copy$default$2[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any]: A2 @scala.annotation.unchecked.uncheckedVariance = CompositeKey4.this.a2;
    <synthetic> def copy$default$3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any]: A3 @scala.annotation.unchecked.uncheckedVariance = CompositeKey4.this.a3;
    <synthetic> def copy$default$4[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any]: A4 @scala.annotation.unchecked.uncheckedVariance = CompositeKey4.this.a4;
    override <synthetic> def productPrefix: String = "CompositeKey4";
    <synthetic> def productArity: Int = 4;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => CompositeKey4.this.a1
      case 1 => CompositeKey4.this.a2
      case 2 => CompositeKey4.this.a3
      case 3 => CompositeKey4.this.a4
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](CompositeKey4.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.dsl.CompositeKey4[_, _, _, _]]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(CompositeKey4.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(CompositeKey4.this);
    override <synthetic> def equals(x$1: Any): Boolean = CompositeKey4.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.dsl.CompositeKey4[_, _, _, _]].&&({
      <synthetic> val CompositeKey4$1: org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4] = x$1.asInstanceOf[org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4]];
      CompositeKey4.this.a1.==(CompositeKey4$1.a1).&&(CompositeKey4.this.a2.==(CompositeKey4$1.a2)).&&(CompositeKey4.this.a3.==(CompositeKey4$1.a3)).&&(CompositeKey4.this.a4.==(CompositeKey4$1.a4)).&&(CompositeKey4$1.canEqual(CompositeKey4.this))
    }))
  };
  <synthetic> object CompositeKey4 extends AnyRef with Serializable {
    def <init>(): org.squeryl.dsl.CompositeKey4.type = {
      CompositeKey4.super.<init>();
      ()
    };
    final override def toString(): String = "CompositeKey4";
    case <synthetic> def apply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4): org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4] = new org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4](a1, a2, a3, a4);
    case <synthetic> def unapply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any](x$0: org.squeryl.dsl.CompositeKey4[A1,A2,A3,A4]): Option[(A1, A2, A3, A4)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(A1, A2, A3, A4)](Tuple4.apply[A1, A2, A3, A4](x$0.a1, x$0.a2, x$0.a3, x$0.a4));
    <synthetic> private def readResolve(): Object = dsl.this.CompositeKey4
  };
  case class CompositeKey5[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any] extends Object with org.squeryl.dsl.CompositeKey with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val a1: A1 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a1: A1 = CompositeKey5.this.a1;
    <caseaccessor> <paramaccessor> private[this] val a2: A2 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a2: A2 = CompositeKey5.this.a2;
    <caseaccessor> <paramaccessor> private[this] val a3: A3 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a3: A3 = CompositeKey5.this.a3;
    <caseaccessor> <paramaccessor> private[this] val a4: A4 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a4: A4 = CompositeKey5.this.a4;
    <caseaccessor> <paramaccessor> private[this] val a5: A5 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a5: A5 = CompositeKey5.this.a5;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5] = {
      CompositeKey5.super.<init>();
      ()
    };
    def ===(ck: org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5]): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey5.this.buildEquality(ck);
    def ===(ck: (A1, A2, A3, A4, A5)): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey5.this.buildEquality(new org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5](ck._1, ck._2, ck._3, ck._4, ck._5));
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = immutable.this.List.apply[org.squeryl.dsl.ast.InputOnlyConstantExpressionNode](new ast.InputOnlyConstantExpressionNode(CompositeKey5.this.a1), new ast.InputOnlyConstantExpressionNode(CompositeKey5.this.a2), new ast.InputOnlyConstantExpressionNode(CompositeKey5.this.a3), new ast.InputOnlyConstantExpressionNode(CompositeKey5.this.a4), new ast.InputOnlyConstantExpressionNode(CompositeKey5.this.a5));
    <synthetic> def copy[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any](a1: A1 = a1, a2: A2 = a2, a3: A3 = a3, a4: A4 = a4, a5: A5 = a5): org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5] = new org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5](a1, a2, a3, a4, a5);
    <synthetic> def copy$default$1[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any]: A1 @scala.annotation.unchecked.uncheckedVariance = CompositeKey5.this.a1;
    <synthetic> def copy$default$2[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any]: A2 @scala.annotation.unchecked.uncheckedVariance = CompositeKey5.this.a2;
    <synthetic> def copy$default$3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any]: A3 @scala.annotation.unchecked.uncheckedVariance = CompositeKey5.this.a3;
    <synthetic> def copy$default$4[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any]: A4 @scala.annotation.unchecked.uncheckedVariance = CompositeKey5.this.a4;
    <synthetic> def copy$default$5[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any]: A5 @scala.annotation.unchecked.uncheckedVariance = CompositeKey5.this.a5;
    override <synthetic> def productPrefix: String = "CompositeKey5";
    <synthetic> def productArity: Int = 5;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => CompositeKey5.this.a1
      case 1 => CompositeKey5.this.a2
      case 2 => CompositeKey5.this.a3
      case 3 => CompositeKey5.this.a4
      case 4 => CompositeKey5.this.a5
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](CompositeKey5.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.dsl.CompositeKey5[_, _, _, _, _]]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(CompositeKey5.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(CompositeKey5.this);
    override <synthetic> def equals(x$1: Any): Boolean = CompositeKey5.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.dsl.CompositeKey5[_, _, _, _, _]].&&({
      <synthetic> val CompositeKey5$1: org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5] = x$1.asInstanceOf[org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5]];
      CompositeKey5.this.a1.==(CompositeKey5$1.a1).&&(CompositeKey5.this.a2.==(CompositeKey5$1.a2)).&&(CompositeKey5.this.a3.==(CompositeKey5$1.a3)).&&(CompositeKey5.this.a4.==(CompositeKey5$1.a4)).&&(CompositeKey5.this.a5.==(CompositeKey5$1.a5)).&&(CompositeKey5$1.canEqual(CompositeKey5.this))
    }))
  };
  <synthetic> object CompositeKey5 extends AnyRef with Serializable {
    def <init>(): org.squeryl.dsl.CompositeKey5.type = {
      CompositeKey5.super.<init>();
      ()
    };
    final override def toString(): String = "CompositeKey5";
    case <synthetic> def apply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5] = new org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5](a1, a2, a3, a4, a5);
    case <synthetic> def unapply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any](x$0: org.squeryl.dsl.CompositeKey5[A1,A2,A3,A4,A5]): Option[(A1, A2, A3, A4, A5)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(A1, A2, A3, A4, A5)](Tuple5.apply[A1, A2, A3, A4, A5](x$0.a1, x$0.a2, x$0.a3, x$0.a4, x$0.a5));
    <synthetic> private def readResolve(): Object = dsl.this.CompositeKey5
  };
  case class CompositeKey6[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any] extends Object with org.squeryl.dsl.CompositeKey with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val a1: A1 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a1: A1 = CompositeKey6.this.a1;
    <caseaccessor> <paramaccessor> private[this] val a2: A2 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a2: A2 = CompositeKey6.this.a2;
    <caseaccessor> <paramaccessor> private[this] val a3: A3 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a3: A3 = CompositeKey6.this.a3;
    <caseaccessor> <paramaccessor> private[this] val a4: A4 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a4: A4 = CompositeKey6.this.a4;
    <caseaccessor> <paramaccessor> private[this] val a5: A5 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a5: A5 = CompositeKey6.this.a5;
    <caseaccessor> <paramaccessor> private[this] val a6: A6 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a6: A6 = CompositeKey6.this.a6;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6): org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6] = {
      CompositeKey6.super.<init>();
      ()
    };
    def ===(ck: org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6]): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey6.this.buildEquality(ck);
    def ===(ck: (A1, A2, A3, A4, A5, A6)): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey6.this.buildEquality(new org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6](ck._1, ck._2, ck._3, ck._4, ck._5, ck._6));
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = immutable.this.List.apply[org.squeryl.dsl.ast.InputOnlyConstantExpressionNode](new ast.InputOnlyConstantExpressionNode(CompositeKey6.this.a1), new ast.InputOnlyConstantExpressionNode(CompositeKey6.this.a2), new ast.InputOnlyConstantExpressionNode(CompositeKey6.this.a3), new ast.InputOnlyConstantExpressionNode(CompositeKey6.this.a4), new ast.InputOnlyConstantExpressionNode(CompositeKey6.this.a5), new ast.InputOnlyConstantExpressionNode(CompositeKey6.this.a6));
    <synthetic> def copy[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any](a1: A1 = a1, a2: A2 = a2, a3: A3 = a3, a4: A4 = a4, a5: A5 = a5, a6: A6 = a6): org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6] = new org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6](a1, a2, a3, a4, a5, a6);
    <synthetic> def copy$default$1[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any]: A1 @scala.annotation.unchecked.uncheckedVariance = CompositeKey6.this.a1;
    <synthetic> def copy$default$2[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any]: A2 @scala.annotation.unchecked.uncheckedVariance = CompositeKey6.this.a2;
    <synthetic> def copy$default$3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any]: A3 @scala.annotation.unchecked.uncheckedVariance = CompositeKey6.this.a3;
    <synthetic> def copy$default$4[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any]: A4 @scala.annotation.unchecked.uncheckedVariance = CompositeKey6.this.a4;
    <synthetic> def copy$default$5[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any]: A5 @scala.annotation.unchecked.uncheckedVariance = CompositeKey6.this.a5;
    <synthetic> def copy$default$6[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any]: A6 @scala.annotation.unchecked.uncheckedVariance = CompositeKey6.this.a6;
    override <synthetic> def productPrefix: String = "CompositeKey6";
    <synthetic> def productArity: Int = 6;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => CompositeKey6.this.a1
      case 1 => CompositeKey6.this.a2
      case 2 => CompositeKey6.this.a3
      case 3 => CompositeKey6.this.a4
      case 4 => CompositeKey6.this.a5
      case 5 => CompositeKey6.this.a6
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](CompositeKey6.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.dsl.CompositeKey6[_, _, _, _, _, _]]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(CompositeKey6.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(CompositeKey6.this);
    override <synthetic> def equals(x$1: Any): Boolean = CompositeKey6.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.dsl.CompositeKey6[_, _, _, _, _, _]].&&({
      <synthetic> val CompositeKey6$1: org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6] = x$1.asInstanceOf[org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6]];
      CompositeKey6.this.a1.==(CompositeKey6$1.a1).&&(CompositeKey6.this.a2.==(CompositeKey6$1.a2)).&&(CompositeKey6.this.a3.==(CompositeKey6$1.a3)).&&(CompositeKey6.this.a4.==(CompositeKey6$1.a4)).&&(CompositeKey6.this.a5.==(CompositeKey6$1.a5)).&&(CompositeKey6.this.a6.==(CompositeKey6$1.a6)).&&(CompositeKey6$1.canEqual(CompositeKey6.this))
    }))
  };
  <synthetic> object CompositeKey6 extends AnyRef with Serializable {
    def <init>(): org.squeryl.dsl.CompositeKey6.type = {
      CompositeKey6.super.<init>();
      ()
    };
    final override def toString(): String = "CompositeKey6";
    case <synthetic> def apply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6): org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6] = new org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6](a1, a2, a3, a4, a5, a6);
    case <synthetic> def unapply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any](x$0: org.squeryl.dsl.CompositeKey6[A1,A2,A3,A4,A5,A6]): Option[(A1, A2, A3, A4, A5, A6)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(A1, A2, A3, A4, A5, A6)](Tuple6.apply[A1, A2, A3, A4, A5, A6](x$0.a1, x$0.a2, x$0.a3, x$0.a4, x$0.a5, x$0.a6));
    <synthetic> private def readResolve(): Object = dsl.this.CompositeKey6
  };
  case class CompositeKey7[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any] extends Object with org.squeryl.dsl.CompositeKey with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val a1: A1 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a1: A1 = CompositeKey7.this.a1;
    <caseaccessor> <paramaccessor> private[this] val a2: A2 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a2: A2 = CompositeKey7.this.a2;
    <caseaccessor> <paramaccessor> private[this] val a3: A3 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a3: A3 = CompositeKey7.this.a3;
    <caseaccessor> <paramaccessor> private[this] val a4: A4 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a4: A4 = CompositeKey7.this.a4;
    <caseaccessor> <paramaccessor> private[this] val a5: A5 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a5: A5 = CompositeKey7.this.a5;
    <caseaccessor> <paramaccessor> private[this] val a6: A6 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a6: A6 = CompositeKey7.this.a6;
    <caseaccessor> <paramaccessor> private[this] val a7: A7 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a7: A7 = CompositeKey7.this.a7;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7): org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7] = {
      CompositeKey7.super.<init>();
      ()
    };
    def ===(ck: org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7]): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey7.this.buildEquality(ck);
    def ===(ck: (A1, A2, A3, A4, A5, A6, A7)): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey7.this.buildEquality(new org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7](ck._1, ck._2, ck._3, ck._4, ck._5, ck._6, ck._7));
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = immutable.this.List.apply[org.squeryl.dsl.ast.InputOnlyConstantExpressionNode](new ast.InputOnlyConstantExpressionNode(CompositeKey7.this.a1), new ast.InputOnlyConstantExpressionNode(CompositeKey7.this.a2), new ast.InputOnlyConstantExpressionNode(CompositeKey7.this.a3), new ast.InputOnlyConstantExpressionNode(CompositeKey7.this.a4), new ast.InputOnlyConstantExpressionNode(CompositeKey7.this.a5), new ast.InputOnlyConstantExpressionNode(CompositeKey7.this.a6), new ast.InputOnlyConstantExpressionNode(CompositeKey7.this.a7));
    <synthetic> def copy[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any](a1: A1 = a1, a2: A2 = a2, a3: A3 = a3, a4: A4 = a4, a5: A5 = a5, a6: A6 = a6, a7: A7 = a7): org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7] = new org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7](a1, a2, a3, a4, a5, a6, a7);
    <synthetic> def copy$default$1[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any]: A1 @scala.annotation.unchecked.uncheckedVariance = CompositeKey7.this.a1;
    <synthetic> def copy$default$2[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any]: A2 @scala.annotation.unchecked.uncheckedVariance = CompositeKey7.this.a2;
    <synthetic> def copy$default$3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any]: A3 @scala.annotation.unchecked.uncheckedVariance = CompositeKey7.this.a3;
    <synthetic> def copy$default$4[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any]: A4 @scala.annotation.unchecked.uncheckedVariance = CompositeKey7.this.a4;
    <synthetic> def copy$default$5[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any]: A5 @scala.annotation.unchecked.uncheckedVariance = CompositeKey7.this.a5;
    <synthetic> def copy$default$6[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any]: A6 @scala.annotation.unchecked.uncheckedVariance = CompositeKey7.this.a6;
    <synthetic> def copy$default$7[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any]: A7 @scala.annotation.unchecked.uncheckedVariance = CompositeKey7.this.a7;
    override <synthetic> def productPrefix: String = "CompositeKey7";
    <synthetic> def productArity: Int = 7;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => CompositeKey7.this.a1
      case 1 => CompositeKey7.this.a2
      case 2 => CompositeKey7.this.a3
      case 3 => CompositeKey7.this.a4
      case 4 => CompositeKey7.this.a5
      case 5 => CompositeKey7.this.a6
      case 6 => CompositeKey7.this.a7
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](CompositeKey7.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.dsl.CompositeKey7[_, _, _, _, _, _, _]]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(CompositeKey7.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(CompositeKey7.this);
    override <synthetic> def equals(x$1: Any): Boolean = CompositeKey7.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.dsl.CompositeKey7[_, _, _, _, _, _, _]].&&({
      <synthetic> val CompositeKey7$1: org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7] = x$1.asInstanceOf[org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7]];
      CompositeKey7.this.a1.==(CompositeKey7$1.a1).&&(CompositeKey7.this.a2.==(CompositeKey7$1.a2)).&&(CompositeKey7.this.a3.==(CompositeKey7$1.a3)).&&(CompositeKey7.this.a4.==(CompositeKey7$1.a4)).&&(CompositeKey7.this.a5.==(CompositeKey7$1.a5)).&&(CompositeKey7.this.a6.==(CompositeKey7$1.a6)).&&(CompositeKey7.this.a7.==(CompositeKey7$1.a7)).&&(CompositeKey7$1.canEqual(CompositeKey7.this))
    }))
  };
  <synthetic> object CompositeKey7 extends AnyRef with Serializable {
    def <init>(): org.squeryl.dsl.CompositeKey7.type = {
      CompositeKey7.super.<init>();
      ()
    };
    final override def toString(): String = "CompositeKey7";
    case <synthetic> def apply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7): org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7] = new org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7](a1, a2, a3, a4, a5, a6, a7);
    case <synthetic> def unapply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any](x$0: org.squeryl.dsl.CompositeKey7[A1,A2,A3,A4,A5,A6,A7]): Option[(A1, A2, A3, A4, A5, A6, A7)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(A1, A2, A3, A4, A5, A6, A7)](Tuple7.apply[A1, A2, A3, A4, A5, A6, A7](x$0.a1, x$0.a2, x$0.a3, x$0.a4, x$0.a5, x$0.a6, x$0.a7));
    <synthetic> private def readResolve(): Object = dsl.this.CompositeKey7
  };
  case class CompositeKey8[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any] extends Object with org.squeryl.dsl.CompositeKey with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val a1: A1 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a1: A1 = CompositeKey8.this.a1;
    <caseaccessor> <paramaccessor> private[this] val a2: A2 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a2: A2 = CompositeKey8.this.a2;
    <caseaccessor> <paramaccessor> private[this] val a3: A3 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a3: A3 = CompositeKey8.this.a3;
    <caseaccessor> <paramaccessor> private[this] val a4: A4 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a4: A4 = CompositeKey8.this.a4;
    <caseaccessor> <paramaccessor> private[this] val a5: A5 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a5: A5 = CompositeKey8.this.a5;
    <caseaccessor> <paramaccessor> private[this] val a6: A6 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a6: A6 = CompositeKey8.this.a6;
    <caseaccessor> <paramaccessor> private[this] val a7: A7 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a7: A7 = CompositeKey8.this.a7;
    <caseaccessor> <paramaccessor> private[this] val a8: A8 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a8: A8 = CompositeKey8.this.a8;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8): org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8] = {
      CompositeKey8.super.<init>();
      ()
    };
    def ===(ck: org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8]): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey8.this.buildEquality(ck);
    def ===(ck: (A1, A2, A3, A4, A5, A6, A7, A8)): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey8.this.buildEquality(new org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8](ck._1, ck._2, ck._3, ck._4, ck._5, ck._6, ck._7, ck._8));
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = immutable.this.List.apply[org.squeryl.dsl.ast.InputOnlyConstantExpressionNode](new ast.InputOnlyConstantExpressionNode(CompositeKey8.this.a1), new ast.InputOnlyConstantExpressionNode(CompositeKey8.this.a2), new ast.InputOnlyConstantExpressionNode(CompositeKey8.this.a3), new ast.InputOnlyConstantExpressionNode(CompositeKey8.this.a4), new ast.InputOnlyConstantExpressionNode(CompositeKey8.this.a5), new ast.InputOnlyConstantExpressionNode(CompositeKey8.this.a6), new ast.InputOnlyConstantExpressionNode(CompositeKey8.this.a7), new ast.InputOnlyConstantExpressionNode(CompositeKey8.this.a8));
    <synthetic> def copy[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any](a1: A1 = a1, a2: A2 = a2, a3: A3 = a3, a4: A4 = a4, a5: A5 = a5, a6: A6 = a6, a7: A7 = a7, a8: A8 = a8): org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8] = new org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8](a1, a2, a3, a4, a5, a6, a7, a8);
    <synthetic> def copy$default$1[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any]: A1 @scala.annotation.unchecked.uncheckedVariance = CompositeKey8.this.a1;
    <synthetic> def copy$default$2[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any]: A2 @scala.annotation.unchecked.uncheckedVariance = CompositeKey8.this.a2;
    <synthetic> def copy$default$3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any]: A3 @scala.annotation.unchecked.uncheckedVariance = CompositeKey8.this.a3;
    <synthetic> def copy$default$4[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any]: A4 @scala.annotation.unchecked.uncheckedVariance = CompositeKey8.this.a4;
    <synthetic> def copy$default$5[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any]: A5 @scala.annotation.unchecked.uncheckedVariance = CompositeKey8.this.a5;
    <synthetic> def copy$default$6[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any]: A6 @scala.annotation.unchecked.uncheckedVariance = CompositeKey8.this.a6;
    <synthetic> def copy$default$7[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any]: A7 @scala.annotation.unchecked.uncheckedVariance = CompositeKey8.this.a7;
    <synthetic> def copy$default$8[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any]: A8 @scala.annotation.unchecked.uncheckedVariance = CompositeKey8.this.a8;
    override <synthetic> def productPrefix: String = "CompositeKey8";
    <synthetic> def productArity: Int = 8;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => CompositeKey8.this.a1
      case 1 => CompositeKey8.this.a2
      case 2 => CompositeKey8.this.a3
      case 3 => CompositeKey8.this.a4
      case 4 => CompositeKey8.this.a5
      case 5 => CompositeKey8.this.a6
      case 6 => CompositeKey8.this.a7
      case 7 => CompositeKey8.this.a8
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](CompositeKey8.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.dsl.CompositeKey8[_, _, _, _, _, _, _, _]]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(CompositeKey8.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(CompositeKey8.this);
    override <synthetic> def equals(x$1: Any): Boolean = CompositeKey8.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.dsl.CompositeKey8[_, _, _, _, _, _, _, _]].&&({
      <synthetic> val CompositeKey8$1: org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8] = x$1.asInstanceOf[org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8]];
      CompositeKey8.this.a1.==(CompositeKey8$1.a1).&&(CompositeKey8.this.a2.==(CompositeKey8$1.a2)).&&(CompositeKey8.this.a3.==(CompositeKey8$1.a3)).&&(CompositeKey8.this.a4.==(CompositeKey8$1.a4)).&&(CompositeKey8.this.a5.==(CompositeKey8$1.a5)).&&(CompositeKey8.this.a6.==(CompositeKey8$1.a6)).&&(CompositeKey8.this.a7.==(CompositeKey8$1.a7)).&&(CompositeKey8.this.a8.==(CompositeKey8$1.a8)).&&(CompositeKey8$1.canEqual(CompositeKey8.this))
    }))
  };
  <synthetic> object CompositeKey8 extends AnyRef with Serializable {
    def <init>(): org.squeryl.dsl.CompositeKey8.type = {
      CompositeKey8.super.<init>();
      ()
    };
    final override def toString(): String = "CompositeKey8";
    case <synthetic> def apply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8): org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8] = new org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8](a1, a2, a3, a4, a5, a6, a7, a8);
    case <synthetic> def unapply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any](x$0: org.squeryl.dsl.CompositeKey8[A1,A2,A3,A4,A5,A6,A7,A8]): Option[(A1, A2, A3, A4, A5, A6, A7, A8)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(A1, A2, A3, A4, A5, A6, A7, A8)](Tuple8.apply[A1, A2, A3, A4, A5, A6, A7, A8](x$0.a1, x$0.a2, x$0.a3, x$0.a4, x$0.a5, x$0.a6, x$0.a7, x$0.a8));
    <synthetic> private def readResolve(): Object = dsl.this.CompositeKey8
  };
  case class CompositeKey9[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any] extends Object with org.squeryl.dsl.CompositeKey with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val a1: A1 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a1: A1 = CompositeKey9.this.a1;
    <caseaccessor> <paramaccessor> private[this] val a2: A2 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a2: A2 = CompositeKey9.this.a2;
    <caseaccessor> <paramaccessor> private[this] val a3: A3 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a3: A3 = CompositeKey9.this.a3;
    <caseaccessor> <paramaccessor> private[this] val a4: A4 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a4: A4 = CompositeKey9.this.a4;
    <caseaccessor> <paramaccessor> private[this] val a5: A5 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a5: A5 = CompositeKey9.this.a5;
    <caseaccessor> <paramaccessor> private[this] val a6: A6 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a6: A6 = CompositeKey9.this.a6;
    <caseaccessor> <paramaccessor> private[this] val a7: A7 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a7: A7 = CompositeKey9.this.a7;
    <caseaccessor> <paramaccessor> private[this] val a8: A8 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a8: A8 = CompositeKey9.this.a8;
    <caseaccessor> <paramaccessor> private[this] val a9: A9 = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def a9: A9 = CompositeKey9.this.a9;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8, a9: A9): org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9] = {
      CompositeKey9.super.<init>();
      ()
    };
    def ===(ck: org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9]): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey9.this.buildEquality(ck);
    def ===(ck: (A1, A2, A3, A4, A5, A6, A7, A8, A9)): org.squeryl.dsl.ast.LogicalBoolean = CompositeKey9.this.buildEquality(new org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9](ck._1, ck._2, ck._3, ck._4, ck._5, ck._6, ck._7, ck._8, ck._9));
    protected def constantMembers: Iterable[org.squeryl.dsl.TypedExpression[_, _]] = immutable.this.List.apply[org.squeryl.dsl.ast.InputOnlyConstantExpressionNode](new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a1), new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a2), new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a3), new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a4), new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a5), new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a6), new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a7), new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a8), new ast.InputOnlyConstantExpressionNode(CompositeKey9.this.a9));
    <synthetic> def copy[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any](a1: A1 = a1, a2: A2 = a2, a3: A3 = a3, a4: A4 = a4, a5: A5 = a5, a6: A6 = a6, a7: A7 = a7, a8: A8 = a8, a9: A9 = a9): org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9] = new org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9](a1, a2, a3, a4, a5, a6, a7, a8, a9);
    <synthetic> def copy$default$1[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A1 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a1;
    <synthetic> def copy$default$2[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A2 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a2;
    <synthetic> def copy$default$3[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A3 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a3;
    <synthetic> def copy$default$4[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A4 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a4;
    <synthetic> def copy$default$5[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A5 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a5;
    <synthetic> def copy$default$6[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A6 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a6;
    <synthetic> def copy$default$7[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A7 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a7;
    <synthetic> def copy$default$8[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A8 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a8;
    <synthetic> def copy$default$9[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any]: A9 @scala.annotation.unchecked.uncheckedVariance = CompositeKey9.this.a9;
    override <synthetic> def productPrefix: String = "CompositeKey9";
    <synthetic> def productArity: Int = 9;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => CompositeKey9.this.a1
      case 1 => CompositeKey9.this.a2
      case 2 => CompositeKey9.this.a3
      case 3 => CompositeKey9.this.a4
      case 4 => CompositeKey9.this.a5
      case 5 => CompositeKey9.this.a6
      case 6 => CompositeKey9.this.a7
      case 7 => CompositeKey9.this.a8
      case 8 => CompositeKey9.this.a9
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](CompositeKey9.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.dsl.CompositeKey9[_, _, _, _, _, _, _, _, _]]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(CompositeKey9.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(CompositeKey9.this);
    override <synthetic> def equals(x$1: Any): Boolean = CompositeKey9.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.dsl.CompositeKey9[_, _, _, _, _, _, _, _, _]].&&({
      <synthetic> val CompositeKey9$1: org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9] = x$1.asInstanceOf[org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9]];
      CompositeKey9.this.a1.==(CompositeKey9$1.a1).&&(CompositeKey9.this.a2.==(CompositeKey9$1.a2)).&&(CompositeKey9.this.a3.==(CompositeKey9$1.a3)).&&(CompositeKey9.this.a4.==(CompositeKey9$1.a4)).&&(CompositeKey9.this.a5.==(CompositeKey9$1.a5)).&&(CompositeKey9.this.a6.==(CompositeKey9$1.a6)).&&(CompositeKey9.this.a7.==(CompositeKey9$1.a7)).&&(CompositeKey9.this.a8.==(CompositeKey9$1.a8)).&&(CompositeKey9.this.a9.==(CompositeKey9$1.a9)).&&(CompositeKey9$1.canEqual(CompositeKey9.this))
    }))
  };
  <synthetic> object CompositeKey9 extends AnyRef with Serializable {
    def <init>(): org.squeryl.dsl.CompositeKey9.type = {
      CompositeKey9.super.<init>();
      ()
    };
    final override def toString(): String = "CompositeKey9";
    case <synthetic> def apply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any](a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8, a9: A9): org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9] = new org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9](a1, a2, a3, a4, a5, a6, a7, a8, a9);
    case <synthetic> def unapply[A1 >: Nothing <: Any, A2 >: Nothing <: Any, A3 >: Nothing <: Any, A4 >: Nothing <: Any, A5 >: Nothing <: Any, A6 >: Nothing <: Any, A7 >: Nothing <: Any, A8 >: Nothing <: Any, A9 >: Nothing <: Any](x$0: org.squeryl.dsl.CompositeKey9[A1,A2,A3,A4,A5,A6,A7,A8,A9]): Option[(A1, A2, A3, A4, A5, A6, A7, A8, A9)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(A1, A2, A3, A4, A5, A6, A7, A8, A9)](Tuple9.apply[A1, A2, A3, A4, A5, A6, A7, A8, A9](x$0.a1, x$0.a2, x$0.a3, x$0.a4, x$0.a5, x$0.a6, x$0.a7, x$0.a8, x$0.a9));
    <synthetic> private def readResolve(): Object = dsl.this.CompositeKey9
  }
}