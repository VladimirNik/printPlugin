package org.squeryl.customtypes {
  import java.util.{Date, UUID};
  import org.squeryl.dsl._;
  import java.sql.Timestamp;
  import org.squeryl.internals.{OutMapper, FieldReferenceLinker, FieldMapper};
  import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
  import java.sql.ResultSet;
  abstract trait CustomType[T >: _root_.scala.Nothing <: _root_.scala.Any] extends Product1[T] {
    def $init$() = {
      ()
    };
    def value: T;
    def _1 = value;
    def canEqual(a: Any) = false
  };
  abstract trait CustomTypesMode extends QueryDsl with FieldMapper {
    def $init$() = {
      ()
    };
    private val ps = PrimitiveTypeSupport;
    val stringTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[String, StringField, TString] {
        def <init>() = {
          super.<init>(ps.stringTEF, this);
          ()
        };
        def convertFromJdbc(v: String) = new StringField(v);
        def convertToJdbc(v: StringField) = v.value
      };
      new $anon()
    };
    val optionStringTEF = {
      final class $anon extends TypedExpressionFactory[Option[StringField], TOptionString] with DeOptionizer[String, StringField, TString, Option[StringField], TOptionString] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = stringTEF
      };
      new $anon()
    };
    val dateTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[Date, DateField, TDate] {
        def <init>() = {
          super.<init>(ps.dateTEF, this);
          ()
        };
        def convertFromJdbc(v: Date) = new DateField(v);
        def convertToJdbc(v: DateField) = v.value
      };
      new $anon()
    };
    val optionDateTEF = {
      final class $anon extends TypedExpressionFactory[Option[DateField], TOptionDate] with DeOptionizer[Date, DateField, TDate, Option[DateField], TOptionDate] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = dateTEF
      };
      new $anon()
    };
    val timestampTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[Timestamp, TimestampField, TTimestamp] {
        def <init>() = {
          super.<init>(ps.timestampTEF, this);
          ()
        };
        def convertFromJdbc(v: Timestamp) = new TimestampField(v);
        def convertToJdbc(v: TimestampField) = v.value
      };
      new $anon()
    };
    val optionTimestampTEF = {
      final class $anon extends TypedExpressionFactory[Option[TimestampField], TOptionTimestamp] with DeOptionizer[Timestamp, TimestampField, TTimestamp, Option[TimestampField], TOptionTimestamp] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = timestampTEF
      };
      new $anon()
    };
    val booleanTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[Boolean, BooleanField, TBoolean] {
        def <init>() = {
          super.<init>(ps.booleanTEF, this);
          ()
        };
        def convertFromJdbc(v: Boolean) = new BooleanField(v);
        def convertToJdbc(v: BooleanField) = v.value
      };
      new $anon()
    };
    val optionBooleanTEF = {
      final class $anon extends TypedExpressionFactory[Option[BooleanField], TOptionBoolean] with DeOptionizer[Boolean, BooleanField, TBoolean, Option[BooleanField], TOptionBoolean] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = booleanTEF
      };
      new $anon()
    };
    val uuidTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[UUID, UuidField, TUUID] {
        def <init>() = {
          super.<init>(ps.uuidTEF, this);
          ()
        };
        def convertFromJdbc(v: UUID) = new UuidField(v);
        def convertToJdbc(v: UuidField) = v.value
      };
      new $anon()
    };
    val optionUUIDTEF = {
      final class $anon extends TypedExpressionFactory[Option[UuidField], TOptionUUID] with DeOptionizer[UUID, UuidField, TUUID, Option[UuidField], TOptionUUID] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = uuidTEF
      };
      new $anon()
    };
    val byteTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[Byte, ByteField, TByte] {
        def <init>() = {
          super.<init>(ps.byteTEF, this);
          ()
        };
        def convertFromJdbc(v: Byte) = new ByteField(v);
        def convertToJdbc(v: ByteField) = v.value
      };
      new $anon()
    };
    val optionByteTEF = {
      final class $anon extends IntegralTypedExpressionFactory[Option[ByteField], TOptionByte, Option[FloatField], TOptionFloat] with DeOptionizer[Byte, ByteField, TByte, Option[ByteField], TOptionByte] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = byteTEF;
        val floatifyer = optionFloatTEF
      };
      new $anon()
    };
    val intTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[Int, IntField, TInt] with IntegralTypedExpressionFactory[IntField, TInt, FloatField, TFloat] {
        def <init>() = {
          super.<init>(ps.intTEF, this);
          ()
        };
        val floatifyer = floatTEF;
        def convertFromJdbc(v: Int) = new IntField(v);
        def convertToJdbc(v: IntField) = v.value
      };
      new $anon()
    };
    val optionIntTEF = {
      final class $anon extends IntegralTypedExpressionFactory[Option[IntField], TOptionInt, Option[FloatField], TOptionFloat] with DeOptionizer[Int, IntField, TInt, Option[IntField], TOptionInt] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = intTEF;
        val floatifyer = optionFloatTEF
      };
      new $anon()
    };
    val longTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[Long, LongField, TLong] with IntegralTypedExpressionFactory[LongField, TLong, DoubleField, TDouble] {
        def <init>() = {
          super.<init>(ps.longTEF, this);
          ()
        };
        val floatifyer = doubleTEF;
        def convertFromJdbc(v: Long) = new LongField(v);
        def convertToJdbc(v: LongField) = v.value
      };
      new $anon()
    };
    val optionLongTEF = {
      final class $anon extends IntegralTypedExpressionFactory[Option[LongField], TOptionLong, Option[DoubleField], TOptionDouble] with DeOptionizer[Long, LongField, TLong, Option[LongField], TOptionLong] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = longTEF;
        val floatifyer = optionDoubleTEF
      };
      new $anon()
    };
    val floatTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[Float, FloatField, TFloat] with FloatTypedExpressionFactory[FloatField, TFloat] {
        def <init>() = {
          super.<init>(ps.floatTEF, this);
          ()
        };
        def convertFromJdbc(v: Float) = new FloatField(v);
        def convertToJdbc(v: FloatField) = v.value
      };
      new $anon()
    };
    val optionFloatTEF = {
      final class $anon extends FloatTypedExpressionFactory[Option[FloatField], TOptionFloat] with DeOptionizer[Float, FloatField, TFloat, Option[FloatField], TOptionFloat] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = floatTEF
      };
      new $anon()
    };
    val doubleTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[Double, DoubleField, TDouble] with FloatTypedExpressionFactory[DoubleField, TDouble] {
        def <init>() = {
          super.<init>(ps.doubleTEF, this);
          ()
        };
        def convertFromJdbc(v: Double) = new DoubleField(v);
        def convertToJdbc(v: DoubleField) = v.value
      };
      new $anon()
    };
    val optionDoubleTEF = {
      final class $anon extends FloatTypedExpressionFactory[Option[DoubleField], TOptionDouble] with DeOptionizer[Double, DoubleField, TDouble, Option[DoubleField], TOptionDouble] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = doubleTEF
      };
      new $anon()
    };
    val bigDecimalTEF = {
      final class $anon extends NonPrimitiveJdbcMapper[BigDecimal, BigDecimalField, TBigDecimal] with FloatTypedExpressionFactory[BigDecimalField, TBigDecimal] {
        def <init>() = {
          super.<init>(ps.bigDecimalTEF, this);
          ()
        };
        def convertFromJdbc(v: BigDecimal) = new BigDecimalField(v);
        def convertToJdbc(v: BigDecimalField) = v.value
      };
      new $anon()
    };
    val optionBigDecimalTEF = {
      final class $anon extends FloatTypedExpressionFactory[Option[BigDecimalField], TOptionBigDecimal] with DeOptionizer[BigDecimal, BigDecimalField, TBigDecimal, Option[BigDecimalField], TOptionBigDecimal] {
        def <init>() = {
          super.<init>();
          ()
        };
        val deOptionizer = bigDecimalTEF
      };
      new $anon()
    };
    implicit def stringToTE(s: String) = stringTEF.createFromNativeJdbcValue(s);
    implicit def optionStringToTE(s: Option[String]) = s.map(((x$1) => new StringField(x$1)));
    implicit def dateToTE(s: Date) = dateTEF.createFromNativeJdbcValue(s);
    implicit def optionDateToTE(s: Option[Date]) = s.map(((x$2) => new DateField(x$2)));
    implicit def timestampToTE(s: Timestamp) = timestampTEF.createFromNativeJdbcValue(s);
    implicit def optionTimestampToTE(s: Option[Timestamp]) = s.map(((x$3) => new TimestampField(x$3)));
    implicit def booleanToTE(s: Boolean) = booleanTEF.createFromNativeJdbcValue(s);
    implicit def optionBooleanToTE(s: Option[Boolean]) = s.map(((x$4) => new BooleanField(x$4)));
    implicit def uuidToTE(s: UUID) = uuidTEF.createFromNativeJdbcValue(s);
    implicit def optionUUIDToTE(s: Option[UUID]) = s.map(((x$5) => new UuidField(x$5)));
    implicit def byteToTE(f: Byte) = byteTEF.createFromNativeJdbcValue(f);
    implicit def optionByteToTE(f: Option[Byte]) = f.map(((x$6) => new ByteField(x$6)));
    implicit def intToTE(f: IntField) = intTEF.create(f);
    implicit def optionIntToTE(f: Option[IntField]) = optionIntTEF.create(f);
    implicit def longToTE(f: Long) = longTEF.createFromNativeJdbcValue(f);
    implicit def optionLongToTE(f: Option[Long]) = f.map(((x$7) => new LongField(x$7)));
    implicit def floatToTE(f: Float) = floatTEF.createFromNativeJdbcValue(f);
    implicit def optionFloatToTE(f: Option[Float]) = f.map(((x$8) => new FloatField(x$8)));
    implicit def doubleToTE(f: Double) = doubleTEF.createFromNativeJdbcValue(f);
    implicit def optionDoubleToTE(f: Option[Double]) = f.map(((x$9) => new DoubleField(x$9)));
    implicit def bigDecimalToTE(f: BigDecimal) = bigDecimalTEF.createFromNativeJdbcValue(f);
    implicit def optionBigDecimalToTE(f: Option[BigDecimal]) = f.map(((x$10) => new BigDecimalField(x$10)))
  };
  object CustomTypesMode extends CustomTypesMode {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  class ByteField extends CustomType[Byte] {
    <paramaccessor> val value: Byte = _;
    def <init>(value: Byte) = {
      super.<init>();
      ()
    }
  };
  class IntField extends CustomType[Int] {
    <paramaccessor> val value: Int = _;
    def <init>(value: Int) = {
      super.<init>();
      ()
    }
  };
  class StringField extends CustomType[String] {
    <paramaccessor> val value: String = _;
    def <init>(value: String) = {
      super.<init>();
      ()
    }
  };
  class DoubleField extends CustomType[Double] {
    <paramaccessor> val value: Double = _;
    def <init>(value: Double) = {
      super.<init>();
      ()
    }
  };
  class BigDecimalField extends CustomType[BigDecimal] {
    <paramaccessor> val value: BigDecimal = _;
    def <init>(value: BigDecimal) = {
      super.<init>();
      ()
    }
  };
  class FloatField extends CustomType[Float] {
    <paramaccessor> val value: Float = _;
    def <init>(value: Float) = {
      super.<init>();
      ()
    }
  };
  class LongField extends CustomType[Long] {
    <paramaccessor> val value: Long = _;
    def <init>(value: Long) = {
      super.<init>();
      ()
    }
  };
  class BooleanField extends CustomType[Boolean] {
    <paramaccessor> val value: Boolean = _;
    def <init>(value: Boolean) = {
      super.<init>();
      ()
    }
  };
  class DateField extends CustomType[Date] {
    <paramaccessor> val value: Date = _;
    def <init>(value: Date) = {
      super.<init>();
      ()
    }
  };
  class TimestampField extends CustomType[Timestamp] {
    <paramaccessor> val value: Timestamp = _;
    def <init>(value: Timestamp) = {
      super.<init>();
      ()
    }
  };
  class BinaryField extends CustomType[Array[Byte]] {
    <paramaccessor> val value: Array[Byte] = _;
    def <init>(value: Array[Byte]) = {
      super.<init>();
      ()
    }
  };
  class UuidField extends CustomType[UUID] {
    <paramaccessor> val value: UUID = _;
    def <init>(value: UUID) = {
      super.<init>();
      ()
    }
  }
}