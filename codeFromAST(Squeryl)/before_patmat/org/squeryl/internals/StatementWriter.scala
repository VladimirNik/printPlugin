package org.squeryl.internals {
  import org.squeryl.dsl.ast.ExpressionNode;
  import scala.collection.mutable.{HashSet, HashMap, ArrayBuffer};
  import org.squeryl.dsl.ast.ConstantTypedExpression;
  import org.squeryl.dsl.ast.ConstantExpressionNodeList;
  abstract trait StatementParam extends scala.AnyRef;
  case class ConstantStatementParam extends Object with org.squeryl.internals.StatementParam with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val p: org.squeryl.dsl.ast.ConstantTypedExpression[_, _] = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def p: org.squeryl.dsl.ast.ConstantTypedExpression[_, _] = ConstantStatementParam.this.p;
    def <init>(p: org.squeryl.dsl.ast.ConstantTypedExpression[_, _]): org.squeryl.internals.ConstantStatementParam = {
      ConstantStatementParam.super.<init>();
      ()
    };
    <synthetic> def copy(p: org.squeryl.dsl.ast.ConstantTypedExpression[_, _] = p): org.squeryl.internals.ConstantStatementParam = new internals.this.ConstantStatementParam(p);
    <synthetic> def copy$default$1: org.squeryl.dsl.ast.ConstantTypedExpression[_$1,_$2] @scala.annotation.unchecked.uncheckedVariance forSome { type _$1; type _$2 } = ConstantStatementParam.this.p;
    override <synthetic> def productPrefix: String = "ConstantStatementParam";
    <synthetic> def productArity: Int = 1;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => ConstantStatementParam.this.p
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](ConstantStatementParam.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.ConstantStatementParam]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(ConstantStatementParam.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(ConstantStatementParam.this);
    override <synthetic> def equals(x$1: Any): Boolean = ConstantStatementParam.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.internals.ConstantStatementParam].&&({
      <synthetic> val ConstantStatementParam$1: org.squeryl.internals.ConstantStatementParam = x$1.asInstanceOf[org.squeryl.internals.ConstantStatementParam];
      ConstantStatementParam.this.p.==(ConstantStatementParam$1.p).&&(ConstantStatementParam$1.canEqual(ConstantStatementParam.this))
    }))
  };
  <synthetic> object ConstantStatementParam extends scala.runtime.AbstractFunction1[org.squeryl.dsl.ast.ConstantTypedExpression[_, _],org.squeryl.internals.ConstantStatementParam] with Serializable {
    def <init>(): org.squeryl.internals.ConstantStatementParam.type = {
      ConstantStatementParam.super.<init>();
      ()
    };
    final override def toString(): String = "ConstantStatementParam";
    case <synthetic> def apply(p: org.squeryl.dsl.ast.ConstantTypedExpression[_, _]): org.squeryl.internals.ConstantStatementParam = new internals.this.ConstantStatementParam(p);
    case <synthetic> def unapply(x$0: org.squeryl.internals.ConstantStatementParam): Option[org.squeryl.dsl.ast.ConstantTypedExpression[_$1,_$2]] forSome { type _$1; type _$2 } = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[org.squeryl.dsl.ast.ConstantTypedExpression[_$1,_$2]](x$0.p);
    <synthetic> private def readResolve(): Object = internals.this.ConstantStatementParam
  };
  case class FieldStatementParam extends Object with org.squeryl.internals.StatementParam with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val v: AnyRef = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def v: AnyRef = FieldStatementParam.this.v;
    <caseaccessor> <paramaccessor> private[this] val fmd: org.squeryl.internals.FieldMetaData = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def fmd: org.squeryl.internals.FieldMetaData = FieldStatementParam.this.fmd;
    def <init>(v: AnyRef, fmd: org.squeryl.internals.FieldMetaData): org.squeryl.internals.FieldStatementParam = {
      FieldStatementParam.super.<init>();
      ()
    };
    <synthetic> def copy(v: AnyRef = v, fmd: org.squeryl.internals.FieldMetaData = fmd): org.squeryl.internals.FieldStatementParam = new internals.this.FieldStatementParam(v, fmd);
    <synthetic> def copy$default$1: AnyRef @scala.annotation.unchecked.uncheckedVariance = FieldStatementParam.this.v;
    <synthetic> def copy$default$2: org.squeryl.internals.FieldMetaData @scala.annotation.unchecked.uncheckedVariance = FieldStatementParam.this.fmd;
    override <synthetic> def productPrefix: String = "FieldStatementParam";
    <synthetic> def productArity: Int = 2;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => FieldStatementParam.this.v
      case 1 => FieldStatementParam.this.fmd
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](FieldStatementParam.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.FieldStatementParam]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(FieldStatementParam.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(FieldStatementParam.this);
    override <synthetic> def equals(x$1: Any): Boolean = FieldStatementParam.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.internals.FieldStatementParam].&&({
      <synthetic> val FieldStatementParam$1: org.squeryl.internals.FieldStatementParam = x$1.asInstanceOf[org.squeryl.internals.FieldStatementParam];
      FieldStatementParam.this.v.==(FieldStatementParam$1.v).&&(FieldStatementParam.this.fmd.==(FieldStatementParam$1.fmd)).&&(FieldStatementParam$1.canEqual(FieldStatementParam.this))
    }))
  };
  <synthetic> object FieldStatementParam extends scala.runtime.AbstractFunction2[AnyRef,org.squeryl.internals.FieldMetaData,org.squeryl.internals.FieldStatementParam] with Serializable {
    def <init>(): org.squeryl.internals.FieldStatementParam.type = {
      FieldStatementParam.super.<init>();
      ()
    };
    final override def toString(): String = "FieldStatementParam";
    case <synthetic> def apply(v: AnyRef, fmd: org.squeryl.internals.FieldMetaData): org.squeryl.internals.FieldStatementParam = new internals.this.FieldStatementParam(v, fmd);
    case <synthetic> def unapply(x$0: org.squeryl.internals.FieldStatementParam): Option[(AnyRef, org.squeryl.internals.FieldMetaData)] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(AnyRef, org.squeryl.internals.FieldMetaData)](Tuple2.apply[AnyRef, org.squeryl.internals.FieldMetaData](x$0.v, x$0.fmd));
    <synthetic> private def readResolve(): Object = internals.this.FieldStatementParam
  };
  case class ConstantExpressionNodeListParam extends Object with org.squeryl.internals.StatementParam with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val v: AnyRef = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def v: AnyRef = ConstantExpressionNodeListParam.this.v;
    <caseaccessor> <paramaccessor> private[this] val l: org.squeryl.dsl.ast.ConstantExpressionNodeList[_] = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def l: org.squeryl.dsl.ast.ConstantExpressionNodeList[_] = ConstantExpressionNodeListParam.this.l;
    def <init>(v: AnyRef, l: org.squeryl.dsl.ast.ConstantExpressionNodeList[_]): org.squeryl.internals.ConstantExpressionNodeListParam = {
      ConstantExpressionNodeListParam.super.<init>();
      ()
    };
    <synthetic> def copy(v: AnyRef = v, l: org.squeryl.dsl.ast.ConstantExpressionNodeList[_] = l): org.squeryl.internals.ConstantExpressionNodeListParam = new internals.this.ConstantExpressionNodeListParam(v, l);
    <synthetic> def copy$default$1: AnyRef @scala.annotation.unchecked.uncheckedVariance = ConstantExpressionNodeListParam.this.v;
    <synthetic> def copy$default$2: org.squeryl.dsl.ast.ConstantExpressionNodeList[_$3] @scala.annotation.unchecked.uncheckedVariance forSome { type _$3 } = ConstantExpressionNodeListParam.this.l;
    override <synthetic> def productPrefix: String = "ConstantExpressionNodeListParam";
    <synthetic> def productArity: Int = 2;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => ConstantExpressionNodeListParam.this.v
      case 1 => ConstantExpressionNodeListParam.this.l
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](ConstantExpressionNodeListParam.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.ConstantExpressionNodeListParam]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(ConstantExpressionNodeListParam.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(ConstantExpressionNodeListParam.this);
    override <synthetic> def equals(x$1: Any): Boolean = ConstantExpressionNodeListParam.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.internals.ConstantExpressionNodeListParam].&&({
      <synthetic> val ConstantExpressionNodeListParam$1: org.squeryl.internals.ConstantExpressionNodeListParam = x$1.asInstanceOf[org.squeryl.internals.ConstantExpressionNodeListParam];
      ConstantExpressionNodeListParam.this.v.==(ConstantExpressionNodeListParam$1.v).&&(ConstantExpressionNodeListParam.this.l.==(ConstantExpressionNodeListParam$1.l)).&&(ConstantExpressionNodeListParam$1.canEqual(ConstantExpressionNodeListParam.this))
    }))
  };
  <synthetic> object ConstantExpressionNodeListParam extends scala.runtime.AbstractFunction2[AnyRef,org.squeryl.dsl.ast.ConstantExpressionNodeList[_],org.squeryl.internals.ConstantExpressionNodeListParam] with Serializable {
    def <init>(): org.squeryl.internals.ConstantExpressionNodeListParam.type = {
      ConstantExpressionNodeListParam.super.<init>();
      ()
    };
    final override def toString(): String = "ConstantExpressionNodeListParam";
    case <synthetic> def apply(v: AnyRef, l: org.squeryl.dsl.ast.ConstantExpressionNodeList[_]): org.squeryl.internals.ConstantExpressionNodeListParam = new internals.this.ConstantExpressionNodeListParam(v, l);
    case <synthetic> def unapply(x$0: org.squeryl.internals.ConstantExpressionNodeListParam): Option[(Object, org.squeryl.dsl.ast.ConstantExpressionNodeList[_$3])] forSome { type _$3 } = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[(AnyRef, org.squeryl.dsl.ast.ConstantExpressionNodeList[_$3])](Tuple2.apply[AnyRef, org.squeryl.dsl.ast.ConstantExpressionNodeList[_$3]](x$0.v, x$0.l));
    <synthetic> private def readResolve(): Object = internals.this.ConstantExpressionNodeListParam
  };
  class StatementWriter extends scala.AnyRef { outer: org.squeryl.internals.StatementWriter => 
    <paramaccessor> private[this] val isForDisplay: Boolean = _;
    <stable> <accessor> <paramaccessor> def isForDisplay: Boolean = StatementWriter.this.isForDisplay;
    <paramaccessor> private[this] val databaseAdapter: org.squeryl.internals.DatabaseAdapter = _;
    <stable> <accessor> <paramaccessor> def databaseAdapter: org.squeryl.internals.DatabaseAdapter = StatementWriter.this.databaseAdapter;
    def <init>(isForDisplay: Boolean, databaseAdapter: org.squeryl.internals.DatabaseAdapter): org.squeryl.internals.StatementWriter = {
      StatementWriter.super.<init>();
      ()
    };
    def <init>(databaseAdapter: org.squeryl.internals.DatabaseAdapter): org.squeryl.internals.StatementWriter = {
      StatementWriter.this.<init>(false, databaseAdapter);
      ()
    };
    private[this] val scope: scala.collection.mutable.HashSet[String] = new scala.collection.mutable.HashSet[String]();
    <stable> <accessor> def scope: scala.collection.mutable.HashSet[String] = StatementWriter.this.scope;
    private[this] val _paramList: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.StatementParam] = new scala.collection.mutable.ArrayBuffer[org.squeryl.internals.StatementParam]();
    <stable> <accessor> protected def _paramList: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.StatementParam] = StatementWriter.this._paramList;
    def surrogate: org.squeryl.internals.StatementWriter = {
      final class $anon extends StatementWriter {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(StatementWriter.this.isForDisplay, StatementWriter.this.databaseAdapter);
          ()
        };
        $anon.this.indentWidth_=(StatementWriter.this.indentWidth);
        override def surrogate: org.squeryl.internals.StatementWriter = StatementWriter.this.surrogate;
        override def addParam(p: org.squeryl.internals.StatementParam): Unit = StatementWriter.this.addParam(p)
      };
      new $anon()
    };
    def params: Iterable[org.squeryl.internals.StatementParam] = StatementWriter.this._paramList;
    private[this] val _stringBuilder: StringBuilder = new scala.`package`.StringBuilder(256);
    <stable> <accessor> private def _stringBuilder: StringBuilder = StatementWriter.this._stringBuilder;
    def statement: String = StatementWriter.this._stringBuilder.toString();
    def addParam(p: org.squeryl.internals.StatementParam): Unit = StatementWriter.this._paramList.append(p);
    override def toString: String = if (StatementWriter.this._paramList.size.==(0))
      StatementWriter.this.statement
    else
      StatementWriter.this._paramList.mkString(StatementWriter.this.statement.+("\njdbcParams:["), ",", "]");
    private[this] val INDENT_INCREMENT: Int = 2;
    <stable> <accessor> private def INDENT_INCREMENT: Int = StatementWriter.this.INDENT_INCREMENT;
    private[this] var indentWidth: Int = 0;
    <accessor> private def indentWidth: Int = StatementWriter.this.indentWidth;
    <accessor> private def indentWidth_=(x$1: Int): Unit = StatementWriter.this.indentWidth = x$1;
    def indent(width: Int): Unit = StatementWriter.this.indentWidth_=(StatementWriter.this.indentWidth.+(width));
    def unindent(width: Int): Unit = StatementWriter.this.indentWidth_=(StatementWriter.this.indentWidth.-(width));
    def indent: Unit = StatementWriter.this.indent(StatementWriter.this.INDENT_INCREMENT);
    def unindent: Unit = StatementWriter.this.unindent(StatementWriter.this.INDENT_INCREMENT);
    private def _dumpToConsole(s: String): Unit = scala.this.Predef.print(s);
    private def _append(s: String): StringBuilder = {
      StatementWriter.this._flushPendingNextLine;
      StatementWriter.this._stringBuilder.append(s)
    };
    private def _writeIndentSpaces: Unit = StatementWriter.this._writeIndentSpaces(StatementWriter.this.indentWidth);
    private def _writeIndentSpaces(c: Int): Unit = scala.this.Predef.intWrapper(1).to(c).foreach[StringBuilder](((i: Int) => StatementWriter.this._append(" ")));
    def nextLine: Unit = {
      StatementWriter.this._append("\n");
      StatementWriter.this._writeIndentSpaces
    };
    private[this] var _lazyPendingLine: Option[() => Unit] = scala.None;
    <accessor> private def _lazyPendingLine: Option[() => Unit] = StatementWriter.this._lazyPendingLine;
    <accessor> private def _lazyPendingLine_=(x$1: Option[() => Unit]): Unit = StatementWriter.this._lazyPendingLine = x$1;
    def pushPendingNextLine: Unit = StatementWriter.this._lazyPendingLine_=(scala.Some.apply[() => Unit]((() => StatementWriter.this.nextLine)));
    private def _flushPendingNextLine: Unit = if (StatementWriter.this._lazyPendingLine.!=(scala.None))
      {
        val pl: Option[() => Unit] = StatementWriter.this._lazyPendingLine;
        StatementWriter.this._lazyPendingLine_=(scala.None);
        val lpl: () => Unit = pl.get;
        lpl.apply()
      }
    else
      ();
    def writeLines(s: String*): Unit = {
      val size: Int = s.size;
      var c: Int = 1;
      s.foreach[Unit](((l: String) => {
        StatementWriter.this._append(l);
        if (c.<(size))
          StatementWriter.this.nextLine
        else
          ()
      }))
    };
    def writeLinesWithSeparator(s: Iterable[String], separator: String): Unit = {
      val size: Int = s.size;
      var c: Int = 1;
      s.foreach[Unit](((l: String) => {
        StatementWriter.this._append(l);
        if (c.<(size))
          StatementWriter.this._append(separator)
        else
          ();
        StatementWriter.this.nextLine;
        c = c.+(1)
      }))
    };
    def writeNodesWithSeparator(s: Iterable[org.squeryl.dsl.ast.ExpressionNode], separator: String, newLineAfterSeparator: Boolean): Unit = {
      val size: Int = s.size;
      var c: Int = 1;
      s.foreach[Unit](((n: org.squeryl.dsl.ast.ExpressionNode) => {
        n.write(this);
        if (c.<(size))
          {
            StatementWriter.this._append(separator);
            if (newLineAfterSeparator)
              StatementWriter.this.nextLine
            else
              ()
          }
        else
          ();
        c = c.+(1)
      }))
    };
    def write(s: String*): Unit = s.foreach[StringBuilder](((s0: String) => StatementWriter.this._append(s0)));
    def writeIndented(u: => Unit): Unit = StatementWriter.this.writeIndented(StatementWriter.this.INDENT_INCREMENT, u);
    def writeIndented(width: Int, u: => Unit): Unit = {
      StatementWriter.this.indent(width);
      StatementWriter.this._writeIndentSpaces(width);
      u;
      StatementWriter.this.unindent(width)
    };
    def quoteName(s: String): String = StatementWriter.this.databaseAdapter.quoteName(s)
  }
}