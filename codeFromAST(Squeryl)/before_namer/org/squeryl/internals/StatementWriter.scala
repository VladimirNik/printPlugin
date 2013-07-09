package org.squeryl.internals {
  import org.squeryl.dsl.ast.ExpressionNode;
  import collection.mutable.{HashSet, HashMap, ArrayBuffer};
  import org.squeryl.dsl.ast.ConstantTypedExpression;
  import org.squeryl.dsl.ast.ConstantExpressionNodeList;
  abstract trait StatementParam extends scala.AnyRef;
  case class ConstantStatementParam extends StatementParam with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val p: ConstantTypedExpression[_$1, _$2] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(p: ConstantTypedExpression[_$1, _$2] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>();
      ()
    }
  };
  case class FieldStatementParam extends StatementParam with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val v: AnyRef = _;
    <caseaccessor> <paramaccessor> val fmd: FieldMetaData = _;
    def <init>(v: AnyRef, fmd: FieldMetaData) = {
      super.<init>();
      ()
    }
  };
  case class ConstantExpressionNodeListParam extends StatementParam with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val v: AnyRef = _;
    <caseaccessor> <paramaccessor> val l: ConstantExpressionNodeList[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(v: AnyRef, l: ConstantExpressionNodeList[_$3] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>();
      ()
    }
  };
  class StatementWriter extends scala.AnyRef { outer => 
    <paramaccessor> val isForDisplay: Boolean = _;
    <paramaccessor> val databaseAdapter: DatabaseAdapter = _;
    def <init>(isForDisplay: Boolean, databaseAdapter: DatabaseAdapter) = {
      super.<init>();
      ()
    };
    def <init>(databaseAdapter: DatabaseAdapter) = {
      <init>(false, databaseAdapter);
      ()
    };
    val scope = new HashSet[String]();
    protected val _paramList = new ArrayBuffer[StatementParam]();
    def surrogate: StatementWriter = {
      final class $anon extends StatementWriter {
        def <init>() = {
          super.<init>(isForDisplay, databaseAdapter);
          ()
        };
        indentWidth = outer.indentWidth;
        override def surrogate = outer.surrogate;
        override def addParam(p: StatementParam) = outer.addParam(p)
      };
      new $anon()
    };
    def params: Iterable[StatementParam] = _paramList;
    private val _stringBuilder = new StringBuilder(256);
    def statement = _stringBuilder.toString;
    def addParam(p: StatementParam) = _paramList.append(p);
    override def toString = if (_paramList.size.$eq$eq(0))
      statement
    else
      _paramList.mkString(statement.$plus("\njdbcParams:["), ",", "]");
    private val INDENT_INCREMENT = 2;
    private var indentWidth = 0;
    def indent(width: Int) = indentWidth.$plus$eq(width);
    def unindent(width: Int) = indentWidth.$minus$eq(width);
    def indent: Unit = indent(INDENT_INCREMENT);
    def unindent: Unit = unindent(INDENT_INCREMENT);
    private def _dumpToConsole(s: String) = print(s);
    private def _append(s: String) = {
      _flushPendingNextLine;
      _stringBuilder.append(s)
    };
    private def _writeIndentSpaces: Unit = _writeIndentSpaces(indentWidth);
    private def _writeIndentSpaces(c: Int) = 1.to(c).foreach(((i) => _append(" ")));
    def nextLine = {
      _append("\n");
      _writeIndentSpaces
    };
    private var _lazyPendingLine: Option[_root_.scala.Function0[Unit]] = None;
    def pushPendingNextLine = _lazyPendingLine = Some((() => nextLine));
    private def _flushPendingNextLine = if (_lazyPendingLine.$bang$eq(None))
      {
        val pl = _lazyPendingLine;
        _lazyPendingLine = None;
        val lpl = pl.get;
        lpl()
      }
    else
      ();
    def writeLines(s: _root_.scala.<repeated>[String]) = {
      val size = s.size;
      var c = 1;
      s.foreach(((l) => {
        _append(l);
        if (c.$less(size))
          nextLine
        else
          ()
      }))
    };
    def writeLinesWithSeparator(s: Iterable[String], separator: String) = {
      val size = s.size;
      var c = 1;
      s.foreach(((l) => {
        _append(l);
        if (c.$less(size))
          _append(separator)
        else
          ();
        nextLine;
        c.$plus$eq(1)
      }))
    };
    def writeNodesWithSeparator(s: Iterable[ExpressionNode], separator: String, newLineAfterSeparator: Boolean) = {
      val size = s.size;
      var c = 1;
      s.foreach(((n) => {
        n.write(this);
        if (c.$less(size))
          {
            _append(separator);
            if (newLineAfterSeparator)
              nextLine
            else
              ()
          }
        else
          ();
        c.$plus$eq(1)
      }))
    };
    def write(s: _root_.scala.<repeated>[String]) = s.foreach(((s0) => _append(s0)));
    def writeIndented(u: _root_.scala.<byname>[Unit]): Unit = writeIndented(INDENT_INCREMENT, u);
    def writeIndented(width: Int, u: _root_.scala.<byname>[Unit]) = {
      indent(width);
      _writeIndentSpaces(width);
      u;
      unindent(width)
    };
    def quoteName(s: String) = databaseAdapter.quoteName(s)
  }
}