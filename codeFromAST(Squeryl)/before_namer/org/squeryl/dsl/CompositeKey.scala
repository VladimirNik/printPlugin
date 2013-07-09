package org.squeryl.dsl {
  import ast._;
  import collection.mutable.ArrayBuffer;
  import org.squeryl.Schema;
  import org.squeryl.internals.{AttributeValidOnMultipleColumn, ColumnAttribute, FieldMetaData};
  abstract trait CompositeKey extends scala.AnyRef {
    def $init$() = {
      ()
    };
    private[squeryl] var _members: Option[Seq[SelectElementReference[_$1, _$2] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }]] = None;
    private[squeryl] var _propertyName: Option[String] = None;
    private[squeryl] def _fields: Seq[FieldMetaData] = if (_members.$eq$eq(None))
      List.empty
    else
      _members.get.map(((x$1) => x$1.selectElement.asInstanceOf[FieldSelectElement].fieldMetaData));
    protected def constantMembers: Iterable[TypedExpression[_$3, _$4] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }];
    protected def members: Iterable[TypedExpression[_$5, _$6] forSome { 
      <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _members.getOrElse(constantMembers);
    private[squeryl] def buildEquality(ck: CompositeKey): LogicalBoolean = {
      val equalities = members.zip(ck.members).map(((t) => new EqualityExpression(t._1, t._2)));
      val head = equalities.head;
      val tail = equalities.tail;
      tail.foldLeft((equalities.head: LogicalBoolean))(((x$2, x$3) => x$2.and(x$3)))
    };
    def is(attributes: _root_.scala.<repeated>[AttributeValidOnMultipleColumn]) = new CompositeKeyAttributeAssignment(this, attributes)
  };
  case class CompositeKey2[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any] extends CompositeKey with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val a1: A1 = _;
    <caseaccessor> <paramaccessor> val a2: A2 = _;
    def <init>(a1: A1, a2: A2) = {
      super.<init>();
      ()
    };
    def $eq$eq$eq(ck: CompositeKey2[A1, A2]) = buildEquality(ck);
    def $eq$eq$eq(ck: Tuple2[A1, A2]) = buildEquality(new CompositeKey2(ck._1, ck._2));
    protected def constantMembers: Iterable[TypedExpression[_$7, _$8] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = List(new InputOnlyConstantExpressionNode(a1), new InputOnlyConstantExpressionNode(a2))
  };
  case class CompositeKey3[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any] extends CompositeKey with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val a1: A1 = _;
    <caseaccessor> <paramaccessor> val a2: A2 = _;
    <caseaccessor> <paramaccessor> val a3: A3 = _;
    def <init>(a1: A1, a2: A2, a3: A3) = {
      super.<init>();
      ()
    };
    def $eq$eq$eq(ck: CompositeKey3[A1, A2, A3]) = buildEquality(ck);
    def $eq$eq$eq(ck: Tuple3[A1, A2, A3]) = buildEquality(new CompositeKey3(ck._1, ck._2, ck._3));
    protected def constantMembers: Iterable[TypedExpression[_$9, _$10] forSome { 
      <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = List(new InputOnlyConstantExpressionNode(a1), new InputOnlyConstantExpressionNode(a2), new InputOnlyConstantExpressionNode(a3))
  };
  case class CompositeKey4[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any] extends CompositeKey with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val a1: A1 = _;
    <caseaccessor> <paramaccessor> val a2: A2 = _;
    <caseaccessor> <paramaccessor> val a3: A3 = _;
    <caseaccessor> <paramaccessor> val a4: A4 = _;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4) = {
      super.<init>();
      ()
    };
    def $eq$eq$eq(ck: CompositeKey4[A1, A2, A3, A4]) = buildEquality(ck);
    def $eq$eq$eq(ck: Tuple4[A1, A2, A3, A4]) = buildEquality(new CompositeKey4(ck._1, ck._2, ck._3, ck._4));
    protected def constantMembers: Iterable[TypedExpression[_$11, _$12] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = List(new InputOnlyConstantExpressionNode(a1), new InputOnlyConstantExpressionNode(a2), new InputOnlyConstantExpressionNode(a3), new InputOnlyConstantExpressionNode(a4))
  };
  case class CompositeKey5[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any] extends CompositeKey with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val a1: A1 = _;
    <caseaccessor> <paramaccessor> val a2: A2 = _;
    <caseaccessor> <paramaccessor> val a3: A3 = _;
    <caseaccessor> <paramaccessor> val a4: A4 = _;
    <caseaccessor> <paramaccessor> val a5: A5 = _;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5) = {
      super.<init>();
      ()
    };
    def $eq$eq$eq(ck: CompositeKey5[A1, A2, A3, A4, A5]) = buildEquality(ck);
    def $eq$eq$eq(ck: Tuple5[A1, A2, A3, A4, A5]) = buildEquality(new CompositeKey5(ck._1, ck._2, ck._3, ck._4, ck._5));
    protected def constantMembers: Iterable[TypedExpression[_$13, _$14] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = List(new InputOnlyConstantExpressionNode(a1), new InputOnlyConstantExpressionNode(a2), new InputOnlyConstantExpressionNode(a3), new InputOnlyConstantExpressionNode(a4), new InputOnlyConstantExpressionNode(a5))
  };
  case class CompositeKey6[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any] extends CompositeKey with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val a1: A1 = _;
    <caseaccessor> <paramaccessor> val a2: A2 = _;
    <caseaccessor> <paramaccessor> val a3: A3 = _;
    <caseaccessor> <paramaccessor> val a4: A4 = _;
    <caseaccessor> <paramaccessor> val a5: A5 = _;
    <caseaccessor> <paramaccessor> val a6: A6 = _;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6) = {
      super.<init>();
      ()
    };
    def $eq$eq$eq(ck: CompositeKey6[A1, A2, A3, A4, A5, A6]) = buildEquality(ck);
    def $eq$eq$eq(ck: Tuple6[A1, A2, A3, A4, A5, A6]) = buildEquality(new CompositeKey6(ck._1, ck._2, ck._3, ck._4, ck._5, ck._6));
    protected def constantMembers: Iterable[TypedExpression[_$15, _$16] forSome { 
      <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = List(new InputOnlyConstantExpressionNode(a1), new InputOnlyConstantExpressionNode(a2), new InputOnlyConstantExpressionNode(a3), new InputOnlyConstantExpressionNode(a4), new InputOnlyConstantExpressionNode(a5), new InputOnlyConstantExpressionNode(a6))
  };
  case class CompositeKey7[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any] extends CompositeKey with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val a1: A1 = _;
    <caseaccessor> <paramaccessor> val a2: A2 = _;
    <caseaccessor> <paramaccessor> val a3: A3 = _;
    <caseaccessor> <paramaccessor> val a4: A4 = _;
    <caseaccessor> <paramaccessor> val a5: A5 = _;
    <caseaccessor> <paramaccessor> val a6: A6 = _;
    <caseaccessor> <paramaccessor> val a7: A7 = _;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7) = {
      super.<init>();
      ()
    };
    def $eq$eq$eq(ck: CompositeKey7[A1, A2, A3, A4, A5, A6, A7]) = buildEquality(ck);
    def $eq$eq$eq(ck: Tuple7[A1, A2, A3, A4, A5, A6, A7]) = buildEquality(new CompositeKey7(ck._1, ck._2, ck._3, ck._4, ck._5, ck._6, ck._7));
    protected def constantMembers: Iterable[TypedExpression[_$17, _$18] forSome { 
      <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = List(new InputOnlyConstantExpressionNode(a1), new InputOnlyConstantExpressionNode(a2), new InputOnlyConstantExpressionNode(a3), new InputOnlyConstantExpressionNode(a4), new InputOnlyConstantExpressionNode(a5), new InputOnlyConstantExpressionNode(a6), new InputOnlyConstantExpressionNode(a7))
  };
  case class CompositeKey8[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any, A8 >: _root_.scala.Nothing <: _root_.scala.Any] extends CompositeKey with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val a1: A1 = _;
    <caseaccessor> <paramaccessor> val a2: A2 = _;
    <caseaccessor> <paramaccessor> val a3: A3 = _;
    <caseaccessor> <paramaccessor> val a4: A4 = _;
    <caseaccessor> <paramaccessor> val a5: A5 = _;
    <caseaccessor> <paramaccessor> val a6: A6 = _;
    <caseaccessor> <paramaccessor> val a7: A7 = _;
    <caseaccessor> <paramaccessor> val a8: A8 = _;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8) = {
      super.<init>();
      ()
    };
    def $eq$eq$eq(ck: CompositeKey8[A1, A2, A3, A4, A5, A6, A7, A8]) = buildEquality(ck);
    def $eq$eq$eq(ck: Tuple8[A1, A2, A3, A4, A5, A6, A7, A8]) = buildEquality(new CompositeKey8(ck._1, ck._2, ck._3, ck._4, ck._5, ck._6, ck._7, ck._8));
    protected def constantMembers: Iterable[TypedExpression[_$19, _$20] forSome { 
      <synthetic> type _$19 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$20 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = List(new InputOnlyConstantExpressionNode(a1), new InputOnlyConstantExpressionNode(a2), new InputOnlyConstantExpressionNode(a3), new InputOnlyConstantExpressionNode(a4), new InputOnlyConstantExpressionNode(a5), new InputOnlyConstantExpressionNode(a6), new InputOnlyConstantExpressionNode(a7), new InputOnlyConstantExpressionNode(a8))
  };
  case class CompositeKey9[A1 >: _root_.scala.Nothing <: _root_.scala.Any, A2 >: _root_.scala.Nothing <: _root_.scala.Any, A3 >: _root_.scala.Nothing <: _root_.scala.Any, A4 >: _root_.scala.Nothing <: _root_.scala.Any, A5 >: _root_.scala.Nothing <: _root_.scala.Any, A6 >: _root_.scala.Nothing <: _root_.scala.Any, A7 >: _root_.scala.Nothing <: _root_.scala.Any, A8 >: _root_.scala.Nothing <: _root_.scala.Any, A9 >: _root_.scala.Nothing <: _root_.scala.Any] extends CompositeKey with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val a1: A1 = _;
    <caseaccessor> <paramaccessor> val a2: A2 = _;
    <caseaccessor> <paramaccessor> val a3: A3 = _;
    <caseaccessor> <paramaccessor> val a4: A4 = _;
    <caseaccessor> <paramaccessor> val a5: A5 = _;
    <caseaccessor> <paramaccessor> val a6: A6 = _;
    <caseaccessor> <paramaccessor> val a7: A7 = _;
    <caseaccessor> <paramaccessor> val a8: A8 = _;
    <caseaccessor> <paramaccessor> val a9: A9 = _;
    def <init>(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8, a9: A9) = {
      super.<init>();
      ()
    };
    def $eq$eq$eq(ck: CompositeKey9[A1, A2, A3, A4, A5, A6, A7, A8, A9]) = buildEquality(ck);
    def $eq$eq$eq(ck: Tuple9[A1, A2, A3, A4, A5, A6, A7, A8, A9]) = buildEquality(new CompositeKey9(ck._1, ck._2, ck._3, ck._4, ck._5, ck._6, ck._7, ck._8, ck._9));
    protected def constantMembers: Iterable[TypedExpression[_$21, _$22] forSome { 
      <synthetic> type _$21 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$22 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = List(new InputOnlyConstantExpressionNode(a1), new InputOnlyConstantExpressionNode(a2), new InputOnlyConstantExpressionNode(a3), new InputOnlyConstantExpressionNode(a4), new InputOnlyConstantExpressionNode(a5), new InputOnlyConstantExpressionNode(a6), new InputOnlyConstantExpressionNode(a7), new InputOnlyConstantExpressionNode(a8), new InputOnlyConstantExpressionNode(a9))
  }
}