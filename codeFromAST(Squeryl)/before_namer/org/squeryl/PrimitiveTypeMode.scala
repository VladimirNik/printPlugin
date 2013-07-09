package org.squeryl {
  import dsl.ast._;
  import dsl._;
  import internals.FieldReferenceLinker;
  import java.util.{Date, UUID};
  import java.sql.Timestamp;
  import java.sql.ResultSet;
  import org.squeryl.internals.Utils;
  import org.squeryl.internals.FieldMapper;
  @new deprecated("the PrimitiveTypeMode companion object is deprecated, you should define a mix in the trait for your application. See : http://squeryl.org/0.9.6.html", "0.9.6") object PrimitiveTypeMode extends PrimitiveTypeMode {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  private[squeryl] object InternalFieldMapper extends PrimitiveTypeMode {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  abstract trait PrimitiveTypeMode extends QueryDsl with FieldMapper {
    def $init$() = {
      ()
    };
    implicit val stringTEF = PrimitiveTypeSupport.stringTEF;
    implicit val optionStringTEF = PrimitiveTypeSupport.optionStringTEF;
    implicit val dateTEF = PrimitiveTypeSupport.dateTEF;
    implicit val optionDateTEF = PrimitiveTypeSupport.optionDateTEF;
    implicit val timestampTEF = PrimitiveTypeSupport.timestampTEF;
    implicit val optionTimestampTEF = PrimitiveTypeSupport.optionTimestampTEF;
    implicit val doubleArrayTEF = PrimitiveTypeSupport.doubleArrayTEF;
    implicit val intArrayTEF = PrimitiveTypeSupport.intArrayTEF;
    implicit val longArrayTEF = PrimitiveTypeSupport.longArrayTEF;
    implicit val stringArrayTEF = PrimitiveTypeSupport.stringArrayTEF;
    implicit val byteTEF = PrimitiveTypeSupport.byteTEF;
    implicit val optionByteTEF = PrimitiveTypeSupport.optionByteTEF;
    implicit val intTEF = PrimitiveTypeSupport.intTEF;
    implicit val optionIntTEF = PrimitiveTypeSupport.optionIntTEF;
    implicit val longTEF = PrimitiveTypeSupport.longTEF;
    implicit val optionLongTEF = PrimitiveTypeSupport.optionLongTEF;
    implicit val floatTEF = PrimitiveTypeSupport.floatTEF;
    implicit val optionFloatTEF = PrimitiveTypeSupport.optionFloatTEF;
    implicit val doubleTEF = PrimitiveTypeSupport.doubleTEF;
    implicit val optionDoubleTEF = PrimitiveTypeSupport.optionDoubleTEF;
    implicit val bigDecimalTEF = PrimitiveTypeSupport.bigDecimalTEF;
    implicit val optionBigDecimalTEF = PrimitiveTypeSupport.optionBigDecimalTEF;
    implicit def stringToTE(s: String) = stringTEF.create(s);
    implicit def optionStringToTE(s: Option[String]) = optionStringTEF.create(s);
    implicit def dateToTE(s: Date) = dateTEF.create(s);
    implicit def optionDateToTE(s: Option[Date]) = optionDateTEF.create(s);
    implicit def timestampToTE(s: Timestamp) = timestampTEF.create(s);
    implicit def optionTimestampToTE(s: Option[Timestamp]) = optionTimestampTEF.create(s);
    implicit def booleanToTE(s: Boolean) = PrimitiveTypeSupport.booleanTEF.create(s);
    implicit def optionBooleanToTE(s: Option[Boolean]) = PrimitiveTypeSupport.optionBooleanTEF.create(s);
    implicit def uuidToTE(s: UUID) = PrimitiveTypeSupport.uuidTEF.create(s);
    implicit def optionUUIDToTE(s: Option[UUID]) = PrimitiveTypeSupport.optionUUIDTEF.create(s);
    implicit def binaryToTE(s: Array[Byte]) = PrimitiveTypeSupport.binaryTEF.create(s);
    implicit def optionByteArrayToTE(s: Option[Array[Byte]]) = PrimitiveTypeSupport.optionByteArrayTEF.create(s);
    implicit def enumValueToTE[A >: _root_.scala.Nothing <: Enumeration#Value](e: A) = PrimitiveTypeSupport.enumValueTEF(e).create(e);
    implicit def optionEnumcValueToTE[A >: _root_.scala.Nothing <: Enumeration#Value](e: Option[A]) = PrimitiveTypeSupport.optionEnumValueTEF(e).create(e);
    implicit def byteToTE(f: Byte) = byteTEF.create(f);
    implicit def optionByteToTE(f: Option[Byte]) = optionByteTEF.create(f);
    implicit def intToTE(f: Int) = intTEF.create(f);
    implicit def optionIntToTE(f: Option[Int]) = optionIntTEF.create(f);
    implicit def longToTE(f: Long) = longTEF.create(f);
    implicit def optionLongToTE(f: Option[Long]) = optionLongTEF.create(f);
    implicit def floatToTE(f: Float) = floatTEF.create(f);
    implicit def optionFloatToTE(f: Option[Float]) = optionFloatTEF.create(f);
    implicit def doubleToTE(f: Double) = doubleTEF.create(f);
    implicit def optionDoubleToTE(f: Option[Double]) = optionDoubleTEF.create(f);
    implicit def bigDecimalToTE(f: BigDecimal) = bigDecimalTEF.create(f);
    implicit def optionBigDecimalToTE(f: Option[BigDecimal]) = optionBigDecimalTEF.create(f);
    implicit def doubleArrayToTE(f: Array[Double]) = doubleArrayTEF.create(f);
    implicit def intArrayToTE(f: Array[Int]) = intArrayTEF.create(f);
    implicit def longArrayToTE(f: Array[Long]) = longArrayTEF.create(f);
    implicit def stringArrayToTE(f: Array[String]) = stringArrayTEF.create(f)
  }
}