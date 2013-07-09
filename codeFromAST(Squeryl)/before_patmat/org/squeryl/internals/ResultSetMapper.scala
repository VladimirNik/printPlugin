package org.squeryl.internals {
  import java.sql.ResultSet;
  import scala.collection.mutable.ArrayBuffer;
  import org.squeryl.dsl.ast.SelectElement;
  abstract trait ResultSetUtils extends scala.AnyRef {
    def /*ResultSetUtils*/$init$(): Unit = {
      ()
    };
    def dumpRow(rs: java.sql.ResultSet): String = {
      val md: java.sql.ResultSetMetaData = rs.getMetaData();
      scala.this.Predef.intWrapper(1).to(md.getColumnCount()).map[String, scala.collection.immutable.IndexedSeq[String]](((i: Int) => "#".+(i).+("->").+(rs.getObject(i)).+(":").+(ResultSetUtils.this._simpleClassName(md.getColumnClassName(i)))))(immutable.this.IndexedSeq.canBuildFrom[String]).mkString("ResultSetRow:[", ",", "]")
    };
    private def _simpleClassName(className: String): String = {
      val idx: Int = className.lastIndexOf(".");
      if (idx.<(0))
        className
      else
        className.substring(idx.+(1), className.length())
    };
    def dumpRowValues(rs: java.sql.ResultSet): String = {
      val md: java.sql.ResultSetMetaData = rs.getMetaData();
      scala.this.Predef.intWrapper(1).to(md.getColumnCount()).map[String, scala.collection.immutable.IndexedSeq[String]](((i: Int) => "".+(rs.getObject(i))))(immutable.this.IndexedSeq.canBuildFrom[String]).mkString("[", ",", "]")
    }
  };
  object ResultSetUtils extends Object with org.squeryl.internals.ResultSetUtils {
    def <init>(): org.squeryl.internals.ResultSetUtils.type = {
      ResultSetUtils.super.<init>();
      ()
    }
  };
  abstract trait OutMapper[T >: Nothing <: Any] extends Object with org.squeryl.internals.ResultSetUtils {
    def /*OutMapper*/$init$(): Unit = {
      ()
    };
    override def toString: String = Utils.failSafeString("$OM(".+(OutMapper.this.index).+(",").+(OutMapper.this.jdbcClass.getSimpleName()).+(")").+(if (OutMapper.this.isActive)
      "*"
    else
      ""));
    private[this] var index: Int = -1;
    <accessor> def index: Int = OutMapper.this.index;
    <accessor> def index_=(x$1: Int): Unit = OutMapper.this.index = x$1;
    private[this] var isActive: Boolean = false;
    <accessor> def isActive: Boolean = OutMapper.this.isActive;
    <accessor> def isActive_=(x$1: Boolean): Unit = OutMapper.this.isActive = x$1;
    def jdbcClass: Class[_ <: AnyRef] = OutMapper.this.sample match {
      case (x: Any)Some[Any]((x @ (_: AnyRef))) => x.getClass()
      case (x @ (_: AnyRef)) => x.getClass()
    };
    def map(rs: java.sql.ResultSet): T = if (OutMapper.this.isActive)
      try {
        OutMapper.this.doMap(rs)
      } catch {
        case (e @ (_: Exception)) => throw new scala.`package`.RuntimeException("Exception while mapping column with OutMapper:\n".+(this).+("\nand resultSet :\n").+(Utils.failSafeString(OutMapper.this.dumpRow(rs))), e)
      }
    else
      OutMapper.this.sample;
    def isNull(rs: java.sql.ResultSet): Boolean = rs.getObject(OutMapper.this.index).==(null);
    def doMap(rs: java.sql.ResultSet): T;
    def sample: T;
    def typeOfExpressionToString: String = OutMapper.this.sample.asInstanceOf[Object].getClass().getName()
  };
  object NoOpOutMapper extends Object with org.squeryl.internals.OutMapper[Any] {
    def <init>(): org.squeryl.internals.NoOpOutMapper.type = {
      NoOpOutMapper.super.<init>();
      ()
    };
    def doMap(rs: java.sql.ResultSet): Nothing = NoOpOutMapper.this.sample;
    def sample: Nothing = throw new scala.`package`.UnsupportedOperationException(" cannot use NoOpOutMapper");
    override def typeOfExpressionToString: String = "NoOpOutMapper"
  };
  class ColumnToFieldMapper extends scala.AnyRef {
    <paramaccessor> private[this] val index: Int = _;
    <stable> <accessor> <paramaccessor> def index: Int = ColumnToFieldMapper.this.index;
    <paramaccessor> private[this] val fieldMetaData: org.squeryl.internals.FieldMetaData = _;
    <stable> <accessor> <paramaccessor> def fieldMetaData: org.squeryl.internals.FieldMetaData = ColumnToFieldMapper.this.fieldMetaData;
    <paramaccessor> private[this] val selectElement: org.squeryl.dsl.ast.SelectElement = _;
    def <init>(index: Int, fieldMetaData: org.squeryl.internals.FieldMetaData, selectElement: org.squeryl.dsl.ast.SelectElement): org.squeryl.internals.ColumnToFieldMapper = {
      ColumnToFieldMapper.super.<init>();
      ()
    };
    if (ColumnToFieldMapper.this.index.<=(0))
      org.squeryl.internals.Utils.throwError("invalid Jdbc index ".+(ColumnToFieldMapper.this.index))
    else
      ();
    def map(obj: AnyRef, rs: java.sql.ResultSet): Unit = if (ColumnToFieldMapper.this.selectElement.isActive)
      ColumnToFieldMapper.this.fieldMetaData.setFromResultSet(obj, rs, ColumnToFieldMapper.this.index)
    else
      ();
    override def toString: String = "$(".+(ColumnToFieldMapper.this.index).+("->").+(ColumnToFieldMapper.this.fieldMetaData).+(")")
  };
  class ColumnToTupleMapper extends scala.AnyRef {
    <paramaccessor> private[this] val outMappers: Array[org.squeryl.internals.OutMapper[_]] = _;
    <stable> <accessor> <paramaccessor> def outMappers: Array[org.squeryl.internals.OutMapper[_]] = ColumnToTupleMapper.this.outMappers;
    def <init>(outMappers: Array[org.squeryl.internals.OutMapper[_]]): org.squeryl.internals.ColumnToTupleMapper = {
      ColumnToTupleMapper.super.<init>();
      ()
    };
    override def toString: String = scala.this.Predef.refArrayOps[org.squeryl.internals.OutMapper[_]](ColumnToTupleMapper.this.outMappers).mkString("(", ",", ")");
    def typeOfExpressionToString(idx: Int): String = ColumnToTupleMapper.this.outMappers.apply(idx).typeOfExpressionToString;
    def activate(i: Int, jdbcIndex: Int): Unit = {
      val m: org.squeryl.internals.OutMapper[_] = ColumnToTupleMapper.this.outMappers.apply(i);
      m.isActive_=(true);
      m.index_=(jdbcIndex)
    };
    def isActive(i: Int): Boolean = ColumnToTupleMapper.this.outMappers.apply(i).isActive;
    def isNull(i: Int, rs: java.sql.ResultSet): Boolean = ColumnToTupleMapper.this.outMappers.apply(i).isNull(rs);
    def mapToTuple[T >: Nothing <: Any](rs: java.sql.ResultSet): T = {
      val size: Int = scala.this.Predef.refArrayOps[org.squeryl.internals.OutMapper[_]](ColumnToTupleMapper.this.outMappers).size;
      val m: Array[org.squeryl.internals.OutMapper[_]] = ColumnToTupleMapper.this.outMappers;
      val res: Any = size match {
        case 1 => m.apply(0).map(rs)
        case 2 => scala.Tuple2.apply[_$1, _$1](m.apply(0).map(rs), m.apply(1).map(rs))
        case 3 => scala.Tuple3.apply[_$1, _$1, _$1](m.apply(0).map(rs), m.apply(1).map(rs), m.apply(2).map(rs))
        case 4 => scala.Tuple4.apply[_$1, _$1, _$1, _$1](m.apply(0).map(rs), m.apply(1).map(rs), m.apply(2).map(rs), m.apply(3).map(rs))
        case 5 => scala.Tuple5.apply[_$1, _$1, _$1, _$1, _$1](m.apply(0).map(rs), m.apply(1).map(rs), m.apply(2).map(rs), m.apply(3).map(rs), m.apply(4).map(rs))
        case 6 => scala.Tuple6.apply[_$1, _$1, _$1, _$1, _$1, _$1](m.apply(0).map(rs), m.apply(1).map(rs), m.apply(2).map(rs), m.apply(3).map(rs), m.apply(4).map(rs), m.apply(5).map(rs))
        case 7 => scala.Tuple7.apply[_$1, _$1, _$1, _$1, _$1, _$1, _$1](m.apply(0).map(rs), m.apply(1).map(rs), m.apply(2).map(rs), m.apply(3).map(rs), m.apply(4).map(rs), m.apply(5).map(rs), m.apply(6).map(rs))
        case (z @ (_: Any)) => org.squeryl.internals.Utils.throwError("tuples of size ".+(size).+(" and greater are not supported"))
      };
      res.asInstanceOf[T]
    }
  };
  class ResultSetMapper extends Object with org.squeryl.internals.ResultSetUtils {
    def <init>(): org.squeryl.internals.ResultSetMapper = {
      ResultSetMapper.super.<init>();
      ()
    };
    private[this] val _yieldValuePushers: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.YieldValuePusher] = new scala.collection.mutable.ArrayBuffer[org.squeryl.internals.YieldValuePusher]();
    <stable> <accessor> private def _yieldValuePushers: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.YieldValuePusher] = ResultSetMapper.this._yieldValuePushers;
    private[this] val _fieldMapper: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.ColumnToFieldMapper] = new scala.collection.mutable.ArrayBuffer[org.squeryl.internals.ColumnToFieldMapper]();
    <stable> <accessor> private def _fieldMapper: scala.collection.mutable.ArrayBuffer[org.squeryl.internals.ColumnToFieldMapper] = ResultSetMapper.this._fieldMapper;
    private[this] var groupKeysMapper: Option[org.squeryl.internals.ColumnToTupleMapper] = scala.None;
    <accessor> def groupKeysMapper: Option[org.squeryl.internals.ColumnToTupleMapper] = ResultSetMapper.this.groupKeysMapper;
    <accessor> def groupKeysMapper_=(x$1: Option[org.squeryl.internals.ColumnToTupleMapper]): Unit = ResultSetMapper.this.groupKeysMapper = x$1;
    private[this] var groupMeasuresMapper: Option[org.squeryl.internals.ColumnToTupleMapper] = scala.None;
    <accessor> def groupMeasuresMapper: Option[org.squeryl.internals.ColumnToTupleMapper] = ResultSetMapper.this.groupMeasuresMapper;
    <accessor> def groupMeasuresMapper_=(x$1: Option[org.squeryl.internals.ColumnToTupleMapper]): Unit = ResultSetMapper.this.groupMeasuresMapper = x$1;
    private[this] var isActive: Boolean = false;
    <accessor> def isActive: Boolean = ResultSetMapper.this.isActive;
    <accessor> def isActive_=(x$1: Boolean): Unit = ResultSetMapper.this.isActive = x$1;
    override def toString: String = scala.this.Predef.any2stringadd(scala.Symbol.apply("ResultSetMapper")).+(":").+(java.this.lang.Integer.toHexString(java.this.lang.System.identityHashCode(this))).+(ResultSetMapper.this._fieldMapper.mkString("(", ",", ")")).+("-").+(ResultSetMapper.this.groupKeysMapper.getOrElse[Object]("")).+("-").+(ResultSetMapper.this.groupMeasuresMapper.getOrElse[Object]("")).+(if (ResultSetMapper.this.isActive)
      "*"
    else
      "");
    def addColumnMapper(cm: org.squeryl.internals.ColumnToFieldMapper): Unit = ResultSetMapper.this._fieldMapper.append(cm);
    def addYieldValuePusher(yvp: org.squeryl.internals.YieldValuePusher): Unit = ResultSetMapper.this._yieldValuePushers.append(yvp);
    def pushYieldedValues(resultSet: java.sql.ResultSet): Unit = {
      if (ResultSetMapper.this.isActive.unary_!)
        return ()
      else
        ();
      ResultSetMapper.this._yieldValuePushers.foreach[Unit](((yvp: org.squeryl.internals.YieldValuePusher) => yvp.push(resultSet)))
    };
    lazy private[this] var _firstNonOption: Option[org.squeryl.internals.ColumnToFieldMapper] = ResultSetMapper.this._fieldMapper.find(((x$1: org.squeryl.internals.ColumnToFieldMapper) => x$1.fieldMetaData.isOption.unary_!));
    def isNoneInOuterJoin(rs: java.sql.ResultSet): Boolean = {
      if (ResultSetMapper.this._firstNonOption.!=(scala.None))
        return rs.getObject(ResultSetMapper.this._firstNonOption.get.index).==(null)
      else
        ();
      ResultSetMapper.this._fieldMapper.foreach[Unit](((c2fm: org.squeryl.internals.ColumnToFieldMapper) => {
        scala.this.Predef.assert(c2fm.fieldMetaData.isOption);
        if (rs.getObject(c2fm.index).!=(null))
          return false
        else
          ()
      }));
      immutable.this.List.apply[Option[org.squeryl.internals.ColumnToTupleMapper]](ResultSetMapper.this.groupKeysMapper, ResultSetMapper.this.groupMeasuresMapper).filter(((x$2: Option[org.squeryl.internals.ColumnToTupleMapper]) => x$2.!=(scala.None))).map[org.squeryl.internals.ColumnToTupleMapper, List[org.squeryl.internals.ColumnToTupleMapper]](((x$3: Option[org.squeryl.internals.ColumnToTupleMapper]) => x$3.get))(immutable.this.List.canBuildFrom[org.squeryl.internals.ColumnToTupleMapper]).foreach[Unit](((col2TupleMapper: org.squeryl.internals.ColumnToTupleMapper) => scala.this.Predef.refArrayOps[org.squeryl.internals.OutMapper[_]](col2TupleMapper.outMappers).foreach[Unit](((outMapper: org.squeryl.internals.OutMapper[_]) => if (outMapper.isActive.&&(rs.getObject(outMapper.index).!=(null)))
        return false
      else
        ()))));
      true
    };
    def map(o: AnyRef, resultSet: java.sql.ResultSet): Unit = {
      if (ResultSetMapper.this.isActive.unary_!)
        return ()
      else
        ();
      try {
        ResultSetMapper.this._fieldMapper.foreach[Unit](((fm: org.squeryl.internals.ColumnToFieldMapper) => fm.map(o, resultSet)))
      } catch {
        case (e @ (_: Exception)) => throw new scala.`package`.RuntimeException("could not map row :\n".+(ResultSetMapper.this.dumpRow(resultSet)).+("\n with mapper :\n").+(this), e)
      }
    }
  };
  class YieldValuePusher extends scala.AnyRef {
    <paramaccessor> private[this] val index: Int = _;
    <stable> <accessor> <paramaccessor> def index: Int = YieldValuePusher.this.index;
    <paramaccessor> private[this] val selectElement: org.squeryl.dsl.ast.SelectElement = _;
    <stable> <accessor> <paramaccessor> def selectElement: org.squeryl.dsl.ast.SelectElement = YieldValuePusher.this.selectElement;
    <paramaccessor> private[this] val mapper: org.squeryl.internals.OutMapper[_] = _;
    def <init>(index: Int, selectElement: org.squeryl.dsl.ast.SelectElement, mapper: org.squeryl.internals.OutMapper[_]): org.squeryl.internals.YieldValuePusher = {
      YieldValuePusher.super.<init>();
      ()
    };
    YieldValuePusher.this.mapper.index_=(YieldValuePusher.this.index);
    YieldValuePusher.this.mapper.isActive_=(true);
    def push(rs: java.sql.ResultSet): Unit = if (YieldValuePusher.this.selectElement.isActive)
      {
        val v: Any = YieldValuePusher.this.mapper.map(rs);
        FieldReferenceLinker.pushYieldValue(v.asInstanceOf[AnyRef])
      }
    else
      ();
    override def toString: String = "$(".+(YieldValuePusher.this.index).+("->&(").+(YieldValuePusher.this.selectElement.writeToString).+(")").+(if (YieldValuePusher.this.mapper.isActive)
      "*"
    else
      "")
  }
}