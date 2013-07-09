package org.squeryl.internals {
  import java.sql.ResultSet;
  import collection.mutable.ArrayBuffer;
  import org.squeryl.dsl.ast.SelectElement;
  abstract trait ResultSetUtils extends scala.AnyRef {
    def $init$() = {
      ()
    };
    def dumpRow(rs: ResultSet) = {
      val md = rs.getMetaData;
      1.to(md.getColumnCount).map(((i) => "#".$plus(i).$plus("->").$plus(rs.getObject(i)).$plus(":").$plus(_simpleClassName(md.getColumnClassName(i))))).mkString("ResultSetRow:[", ",", "]")
    };
    private def _simpleClassName(className: String) = {
      val idx = className.lastIndexOf(".");
      if (idx.$less(0))
        className
      else
        className.substring(idx.$plus(1), className.length)
    };
    def dumpRowValues(rs: ResultSet) = {
      val md = rs.getMetaData;
      1.to(md.getColumnCount).map(((i) => "".$plus(rs.getObject(i)))).mkString("[", ",", "]")
    }
  };
  object ResultSetUtils extends ResultSetUtils {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  abstract trait OutMapper[T >: _root_.scala.Nothing <: _root_.scala.Any] extends ResultSetUtils {
    def $init$() = {
      ()
    };
    override def toString = Utils.failSafeString("$OM(".$plus(index).$plus(",").$plus(jdbcClass.getSimpleName).$plus(")").$plus(if (isActive)
      "*"
    else
      ""));
    var index: Int = -1;
    var isActive = false;
    def jdbcClass = sample match {
      case Some((x @ (_: AnyRef))) => x.getClass
      case (x @ (_: AnyRef)) => x.getClass
    };
    def map(rs: ResultSet): T = if (isActive)
      try {
        doMap(rs)
      } catch {
        case (e @ (_: Exception)) => throw new RuntimeException("Exception while mapping column with OutMapper:\n".$plus(this).$plus("\nand resultSet :\n").$plus(Utils.failSafeString(dumpRow(rs))), e)
      }
    else
      sample;
    def isNull(rs: ResultSet) = rs.getObject(index).$eq$eq(null);
    def doMap(rs: ResultSet): T;
    def sample: T;
    def typeOfExpressionToString = sample.asInstanceOf[Object].getClass.getName
  };
  object NoOpOutMapper extends OutMapper[Any] {
    def <init>() = {
      super.<init>();
      ()
    };
    def doMap(rs: ResultSet) = sample;
    def sample = throw new UnsupportedOperationException(" cannot use NoOpOutMapper");
    override def typeOfExpressionToString = "NoOpOutMapper"
  };
  class ColumnToFieldMapper extends scala.AnyRef {
    <paramaccessor> val index: Int = _;
    <paramaccessor> val fieldMetaData: FieldMetaData = _;
    <paramaccessor> private[this] val selectElement: SelectElement = _;
    def <init>(index: Int, fieldMetaData: FieldMetaData, selectElement: SelectElement) = {
      super.<init>();
      ()
    };
    if (index.$less$eq(0))
      org.squeryl.internals.Utils.throwError("invalid Jdbc index ".$plus(index))
    else
      ();
    def map(obj: AnyRef, rs: ResultSet): Unit = if (selectElement.isActive)
      fieldMetaData.setFromResultSet(obj, rs, index)
    else
      ();
    override def toString = "$(".$plus(index).$plus("->").$plus(fieldMetaData).$plus(")")
  };
  class ColumnToTupleMapper extends scala.AnyRef {
    <paramaccessor> val outMappers: Array[OutMapper[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = _;
    def <init>(outMappers: Array[OutMapper[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }]) = {
      super.<init>();
      ()
    };
    override def toString = outMappers.mkString("(", ",", ")");
    def typeOfExpressionToString(idx: Int) = outMappers.apply(idx).typeOfExpressionToString;
    def activate(i: Int, jdbcIndex: Int) = {
      val m = outMappers.apply(i);
      m.isActive = true;
      m.index = jdbcIndex
    };
    def isActive(i: Int) = outMappers.apply(i).isActive;
    def isNull(i: Int, rs: ResultSet) = outMappers.apply(i).isNull(rs);
    def mapToTuple[T >: _root_.scala.Nothing <: _root_.scala.Any](rs: ResultSet): T = {
      val size = outMappers.size;
      val m = outMappers;
      val res = size match {
        case 1 => m(0).map(rs)
        case 2 => scala.Tuple2(m(0).map(rs), m(1).map(rs))
        case 3 => scala.Tuple3(m(0).map(rs), m(1).map(rs), m(2).map(rs))
        case 4 => scala.Tuple4(m(0).map(rs), m(1).map(rs), m(2).map(rs), m(3).map(rs))
        case 5 => scala.Tuple5(m(0).map(rs), m(1).map(rs), m(2).map(rs), m(3).map(rs), m(4).map(rs))
        case 6 => scala.Tuple6(m(0).map(rs), m(1).map(rs), m(2).map(rs), m(3).map(rs), m(4).map(rs), m(5).map(rs))
        case 7 => scala.Tuple7(m(0).map(rs), m(1).map(rs), m(2).map(rs), m(3).map(rs), m(4).map(rs), m(5).map(rs), m(6).map(rs))
        case (z @ (_: Any)) => org.squeryl.internals.Utils.throwError("tuples of size ".$plus(size).$plus(" and greater are not supported"))
      };
      res.asInstanceOf[T]
    }
  };
  class ResultSetMapper extends ResultSetUtils {
    def <init>() = {
      super.<init>();
      ()
    };
    private val _yieldValuePushers = new ArrayBuffer[YieldValuePusher]();
    private val _fieldMapper = new ArrayBuffer[ColumnToFieldMapper]();
    var groupKeysMapper: Option[ColumnToTupleMapper] = None;
    var groupMeasuresMapper: Option[ColumnToTupleMapper] = None;
    var isActive = false;
    override def toString = scala.Symbol("ResultSetMapper").$plus(":").$plus(Integer.toHexString(System.identityHashCode(this))).$plus(_fieldMapper.mkString("(", ",", ")")).$plus("-").$plus(groupKeysMapper.getOrElse("")).$plus("-").$plus(groupMeasuresMapper.getOrElse("")).$plus(if (isActive)
      "*"
    else
      "");
    def addColumnMapper(cm: ColumnToFieldMapper) = _fieldMapper.append(cm);
    def addYieldValuePusher(yvp: YieldValuePusher) = _yieldValuePushers.append(yvp);
    def pushYieldedValues(resultSet: ResultSet): Unit = {
      if (isActive.unary_$bang)
        return ()
      else
        ();
      _yieldValuePushers.foreach(((yvp) => yvp.push(resultSet)))
    };
    lazy private val _firstNonOption: Option[ColumnToFieldMapper] = _fieldMapper.find(((x$1) => x$1.fieldMetaData.isOption.unary_$bang));
    def isNoneInOuterJoin(rs: ResultSet): Boolean = {
      if (_firstNonOption.$bang$eq(None))
        return rs.getObject(_firstNonOption.get.index).$eq$eq(null)
      else
        ();
      _fieldMapper.foreach(((c2fm) => {
        assert(c2fm.fieldMetaData.isOption);
        if (rs.getObject(c2fm.index).$bang$eq(null))
          return false
        else
          ()
      }));
      List(groupKeysMapper, groupMeasuresMapper).filter(((x$2) => x$2.$bang$eq(None))).map(((x$3) => x$3.get)).foreach(((col2TupleMapper) => col2TupleMapper.outMappers.foreach(((outMapper) => if (outMapper.isActive.$amp$amp(rs.getObject(outMapper.index).$bang$eq(null)))
        return false
      else
        ()))));
      true
    };
    def map(o: AnyRef, resultSet: ResultSet): Unit = {
      if (isActive.unary_$bang)
        return ()
      else
        ();
      try {
        _fieldMapper.foreach(((fm) => fm.map(o, resultSet)))
      } catch {
        case (e @ (_: Exception)) => throw new RuntimeException("could not map row :\n".$plus(dumpRow(resultSet)).$plus("\n with mapper :\n").$plus(this), e)
      }
    }
  };
  class YieldValuePusher extends scala.AnyRef {
    <paramaccessor> val index: Int = _;
    <paramaccessor> val selectElement: SelectElement = _;
    <paramaccessor> private[this] val mapper: OutMapper[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    } = _;
    def <init>(index: Int, selectElement: SelectElement, mapper: OutMapper[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      super.<init>();
      ()
    };
    mapper.index = index;
    mapper.isActive = true;
    def push(rs: ResultSet) = if (selectElement.isActive)
      {
        val v = mapper.map(rs);
        FieldReferenceLinker.pushYieldValue(v.asInstanceOf[AnyRef])
      }
    else
      ();
    override def toString = "$(".$plus(index).$plus("->&(").$plus(selectElement.writeToString).$plus(")").$plus(if (mapper.isActive)
      "*"
    else
      "")
  }
}