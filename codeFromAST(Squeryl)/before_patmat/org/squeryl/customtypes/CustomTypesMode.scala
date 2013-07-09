package org.squeryl.customtypes {
  import java.util.{Date, UUID};
  import org.squeryl.dsl._;
  import java.sql.Timestamp;
  import org.squeryl.internals.{OutMapper, FieldReferenceLinker, FieldMapper};
  import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
  import java.sql.ResultSet;
  abstract trait CustomType[T >: Nothing <: Any] extends AnyRef with Product1[T] {
    def /*CustomType*/$init$(): Unit = {
      ()
    };
    def value: T;
    def _1: T = CustomType.this.value;
    def canEqual(a: Any): Boolean = false
  };
  abstract trait CustomTypesMode extends Object with org.squeryl.dsl.QueryDsl with org.squeryl.internals.FieldMapper {
    def /*CustomTypesMode*/$init$(): Unit = {
      ()
    };
    private[this] val ps: CustomTypesMode.this.PrimitiveTypeSupport.type = CustomTypesMode.this.PrimitiveTypeSupport;
    <stable> <accessor> private def ps: CustomTypesMode.this.PrimitiveTypeSupport.type = CustomTypesMode.this.ps;
    private[this] val stringTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.stringTEF, this);
          ()
        };
        def convertFromJdbc(v: String): org.squeryl.customtypes.StringField = new StringField(v);
        def convertToJdbc(v: org.squeryl.customtypes.StringField): String = v.value
      };
      new $anon()
    };
    <stable> <accessor> def stringTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString] = CustomTypesMode.this.stringTEF;
    private[this] val optionStringTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.StringField],org.squeryl.dsl.TOptionString] with org.squeryl.dsl.DeOptionizer[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString,Option[org.squeryl.customtypes.StringField],org.squeryl.dsl.TOptionString]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString]} = {
      final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.StringField],org.squeryl.dsl.TOptionString] with org.squeryl.dsl.DeOptionizer[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString,Option[org.squeryl.customtypes.StringField],org.squeryl.dsl.TOptionString] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString] = CustomTypesMode.this.stringTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString] = $anon.this.deOptionizer
      };
      new $anon()
    };
    <stable> <accessor> def optionStringTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.StringField],org.squeryl.dsl.TOptionString] with org.squeryl.dsl.DeOptionizer[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString,Option[org.squeryl.customtypes.StringField],org.squeryl.dsl.TOptionString]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[String,org.squeryl.customtypes.StringField,org.squeryl.dsl.TString]} = CustomTypesMode.this.optionStringTEF;
    private[this] val dateTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.dateTEF, this);
          ()
        };
        def convertFromJdbc(v: java.util.Date): org.squeryl.customtypes.DateField = new DateField(v);
        def convertToJdbc(v: org.squeryl.customtypes.DateField): java.util.Date = v.value
      };
      new $anon()
    };
    <stable> <accessor> def dateTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate] = CustomTypesMode.this.dateTEF;
    private[this] val optionDateTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.DateField],org.squeryl.dsl.TOptionDate] with org.squeryl.dsl.DeOptionizer[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate,Option[org.squeryl.customtypes.DateField],org.squeryl.dsl.TOptionDate]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate]} = {
      final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.DateField],org.squeryl.dsl.TOptionDate] with org.squeryl.dsl.DeOptionizer[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate,Option[org.squeryl.customtypes.DateField],org.squeryl.dsl.TOptionDate] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate] = CustomTypesMode.this.dateTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate] = $anon.this.deOptionizer
      };
      new $anon()
    };
    <stable> <accessor> def optionDateTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.DateField],org.squeryl.dsl.TOptionDate] with org.squeryl.dsl.DeOptionizer[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate,Option[org.squeryl.customtypes.DateField],org.squeryl.dsl.TOptionDate]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.Date,org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate]} = CustomTypesMode.this.optionDateTEF;
    private[this] val timestampTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.timestampTEF, this);
          ()
        };
        def convertFromJdbc(v: java.sql.Timestamp): org.squeryl.customtypes.TimestampField = new TimestampField(v);
        def convertToJdbc(v: org.squeryl.customtypes.TimestampField): java.sql.Timestamp = v.value
      };
      new $anon()
    };
    <stable> <accessor> def timestampTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp] = CustomTypesMode.this.timestampTEF;
    private[this] val optionTimestampTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.TimestampField],org.squeryl.dsl.TOptionTimestamp] with org.squeryl.dsl.DeOptionizer[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp,Option[org.squeryl.customtypes.TimestampField],org.squeryl.dsl.TOptionTimestamp]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp]} = {
      final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.TimestampField],org.squeryl.dsl.TOptionTimestamp] with org.squeryl.dsl.DeOptionizer[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp,Option[org.squeryl.customtypes.TimestampField],org.squeryl.dsl.TOptionTimestamp] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp] = CustomTypesMode.this.timestampTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp] = $anon.this.deOptionizer
      };
      new $anon()
    };
    <stable> <accessor> def optionTimestampTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.TimestampField],org.squeryl.dsl.TOptionTimestamp] with org.squeryl.dsl.DeOptionizer[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp,Option[org.squeryl.customtypes.TimestampField],org.squeryl.dsl.TOptionTimestamp]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.sql.Timestamp,org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp]} = CustomTypesMode.this.optionTimestampTEF;
    private[this] val booleanTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.booleanTEF, this);
          ()
        };
        def convertFromJdbc(v: Boolean): org.squeryl.customtypes.BooleanField = new BooleanField(v);
        def convertToJdbc(v: org.squeryl.customtypes.BooleanField): Boolean = v.value
      };
      new $anon()
    };
    <stable> <accessor> def booleanTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean] = CustomTypesMode.this.booleanTEF;
    private[this] val optionBooleanTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.BooleanField],org.squeryl.dsl.TOptionBoolean] with org.squeryl.dsl.DeOptionizer[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean,Option[org.squeryl.customtypes.BooleanField],org.squeryl.dsl.TOptionBoolean]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean]} = {
      final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.BooleanField],org.squeryl.dsl.TOptionBoolean] with org.squeryl.dsl.DeOptionizer[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean,Option[org.squeryl.customtypes.BooleanField],org.squeryl.dsl.TOptionBoolean] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean] = CustomTypesMode.this.booleanTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean] = $anon.this.deOptionizer
      };
      new $anon()
    };
    <stable> <accessor> def optionBooleanTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.BooleanField],org.squeryl.dsl.TOptionBoolean] with org.squeryl.dsl.DeOptionizer[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean,Option[org.squeryl.customtypes.BooleanField],org.squeryl.dsl.TOptionBoolean]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Boolean,org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean]} = CustomTypesMode.this.optionBooleanTEF;
    private[this] val uuidTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.uuidTEF, this);
          ()
        };
        def convertFromJdbc(v: java.util.UUID): org.squeryl.customtypes.UuidField = new UuidField(v);
        def convertToJdbc(v: org.squeryl.customtypes.UuidField): java.util.UUID = v.value
      };
      new $anon()
    };
    <stable> <accessor> def uuidTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID] = CustomTypesMode.this.uuidTEF;
    private[this] val optionUUIDTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.UuidField],org.squeryl.dsl.TOptionUUID] with org.squeryl.dsl.DeOptionizer[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID,Option[org.squeryl.customtypes.UuidField],org.squeryl.dsl.TOptionUUID]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID]} = {
      final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.UuidField],org.squeryl.dsl.TOptionUUID] with org.squeryl.dsl.DeOptionizer[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID,Option[org.squeryl.customtypes.UuidField],org.squeryl.dsl.TOptionUUID] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID] = CustomTypesMode.this.uuidTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID] = $anon.this.deOptionizer
      };
      new $anon()
    };
    <stable> <accessor> def optionUUIDTEF: org.squeryl.dsl.TypedExpressionFactory[Option[org.squeryl.customtypes.UuidField],org.squeryl.dsl.TOptionUUID] with org.squeryl.dsl.DeOptionizer[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID,Option[org.squeryl.customtypes.UuidField],org.squeryl.dsl.TOptionUUID]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[java.util.UUID,org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID]} = CustomTypesMode.this.optionUUIDTEF;
    private[this] val byteTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.byteTEF, this);
          ()
        };
        def convertFromJdbc(v: Byte): org.squeryl.customtypes.ByteField = new ByteField(v);
        def convertToJdbc(v: org.squeryl.customtypes.ByteField): Byte = v.value
      };
      new $anon()
    };
    <stable> <accessor> def byteTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte] = CustomTypesMode.this.byteTEF;
    private[this] val optionByteTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.ByteField],org.squeryl.dsl.TOptionByte,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte,Option[org.squeryl.customtypes.ByteField],org.squeryl.dsl.TOptionByte]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte]; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]}} = {
      final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.ByteField],org.squeryl.dsl.TOptionByte,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte,Option[org.squeryl.customtypes.ByteField],org.squeryl.dsl.TOptionByte] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte] = CustomTypesMode.this.byteTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte] = $anon.this.deOptionizer;
        private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = CustomTypesMode.this.optionFloatTEF;
        <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = $anon.this.floatifyer
      };
      new $anon()
    };
    <stable> <accessor> def optionByteTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.ByteField],org.squeryl.dsl.TOptionByte,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte,Option[org.squeryl.customtypes.ByteField],org.squeryl.dsl.TOptionByte]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Byte,org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte]; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]}} = CustomTypesMode.this.optionByteTEF;
    private[this] val intTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.intTEF, this);
          ()
        };
        private[this] val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] = CustomTypesMode.this.floatTEF;
        <stable> <accessor> def floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] = $anon.this.floatifyer;
        def convertFromJdbc(v: Int): org.squeryl.customtypes.IntField = new IntField(v);
        def convertToJdbc(v: org.squeryl.customtypes.IntField): Int = v.value
      };
      new $anon()
    };
    <stable> <accessor> def intTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = CustomTypesMode.this.intTEF;
    private[this] val optionIntTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.IntField],org.squeryl.dsl.TOptionInt,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,Option[org.squeryl.customtypes.IntField],org.squeryl.dsl.TOptionInt]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]}} = {
      final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.IntField],org.squeryl.dsl.TOptionInt,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,Option[org.squeryl.customtypes.IntField],org.squeryl.dsl.TOptionInt] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = CustomTypesMode.this.intTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = $anon.this.deOptionizer;
        private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = CustomTypesMode.this.optionFloatTEF;
        <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = $anon.this.floatifyer
      };
      new $anon()
    };
    <stable> <accessor> def optionIntTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.IntField],org.squeryl.dsl.TOptionInt,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,Option[org.squeryl.customtypes.IntField],org.squeryl.dsl.TOptionInt]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Int,org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]}} = CustomTypesMode.this.optionIntTEF;
    private[this] val longTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]} = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.longTEF, this);
          ()
        };
        private[this] val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] = CustomTypesMode.this.doubleTEF;
        <stable> <accessor> def floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] = $anon.this.floatifyer;
        def convertFromJdbc(v: Long): org.squeryl.customtypes.LongField = new LongField(v);
        def convertToJdbc(v: org.squeryl.customtypes.LongField): Long = v.value
      };
      new $anon()
    };
    <stable> <accessor> def longTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]} = CustomTypesMode.this.longTEF;
    private[this] val optionLongTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.LongField],org.squeryl.dsl.TOptionLong,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,Option[org.squeryl.customtypes.LongField],org.squeryl.dsl.TOptionLong]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]}} = {
      final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.LongField],org.squeryl.dsl.TOptionLong,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,Option[org.squeryl.customtypes.LongField],org.squeryl.dsl.TOptionLong] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]} = CustomTypesMode.this.longTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]} = $anon.this.deOptionizer;
        private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]} = CustomTypesMode.this.optionDoubleTEF;
        <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]} = $anon.this.floatifyer
      };
      new $anon()
    };
    <stable> <accessor> def optionLongTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[org.squeryl.customtypes.LongField],org.squeryl.dsl.TOptionLong,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,Option[org.squeryl.customtypes.LongField],org.squeryl.dsl.TOptionLong]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Long,org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong] with org.squeryl.dsl.IntegralTypedExpressionFactory[org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]{val floatifyer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]}} = CustomTypesMode.this.optionLongTEF;
    private[this] val floatTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.floatTEF, this);
          ()
        };
        def convertFromJdbc(v: Float): org.squeryl.customtypes.FloatField = new FloatField(v);
        def convertToJdbc(v: org.squeryl.customtypes.FloatField): Float = v.value
      };
      new $anon()
    };
    <stable> <accessor> def floatTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] = CustomTypesMode.this.floatTEF;
    private[this] val optionFloatTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = {
      final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] = CustomTypesMode.this.floatTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] = $anon.this.deOptionizer
      };
      new $anon()
    };
    <stable> <accessor> def optionFloatTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat,Option[org.squeryl.customtypes.FloatField],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Float,org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat]} = CustomTypesMode.this.optionFloatTEF;
    private[this] val doubleTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.doubleTEF, this);
          ()
        };
        def convertFromJdbc(v: Double): org.squeryl.customtypes.DoubleField = new DoubleField(v);
        def convertToJdbc(v: org.squeryl.customtypes.DoubleField): Double = v.value
      };
      new $anon()
    };
    <stable> <accessor> def doubleTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] = CustomTypesMode.this.doubleTEF;
    private[this] val optionDoubleTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]} = {
      final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] = CustomTypesMode.this.doubleTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] = $anon.this.deOptionizer
      };
      new $anon()
    };
    <stable> <accessor> def optionDoubleTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble,Option[org.squeryl.customtypes.DoubleField],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[Double,org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble]} = CustomTypesMode.this.optionDoubleTEF;
    private[this] val bigDecimalTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] = {
      final class $anon extends org.squeryl.dsl.NonPrimitiveJdbcMapper[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>(CustomTypesMode.this.ps.bigDecimalTEF, this);
          ()
        };
        def convertFromJdbc(v: BigDecimal): org.squeryl.customtypes.BigDecimalField = new BigDecimalField(v);
        def convertToJdbc(v: org.squeryl.customtypes.BigDecimalField): BigDecimal = v.value
      };
      new $anon()
    };
    <stable> <accessor> def bigDecimalTEF: org.squeryl.dsl.NonPrimitiveJdbcMapper[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] = CustomTypesMode.this.bigDecimalTEF;
    private[this] val optionBigDecimalTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.BigDecimalField],org.squeryl.dsl.TOptionBigDecimal] with org.squeryl.dsl.DeOptionizer[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal,Option[org.squeryl.customtypes.BigDecimalField],org.squeryl.dsl.TOptionBigDecimal]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal]} = {
      final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.BigDecimalField],org.squeryl.dsl.TOptionBigDecimal] with org.squeryl.dsl.DeOptionizer[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal,Option[org.squeryl.customtypes.BigDecimalField],org.squeryl.dsl.TOptionBigDecimal] {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] = CustomTypesMode.this.bigDecimalTEF;
        <stable> <accessor> def deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] = $anon.this.deOptionizer
      };
      new $anon()
    };
    <stable> <accessor> def optionBigDecimalTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[org.squeryl.customtypes.BigDecimalField],org.squeryl.dsl.TOptionBigDecimal] with org.squeryl.dsl.DeOptionizer[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal,Option[org.squeryl.customtypes.BigDecimalField],org.squeryl.dsl.TOptionBigDecimal]{val deOptionizer: org.squeryl.dsl.NonPrimitiveJdbcMapper[BigDecimal,org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.FloatTypedExpressionFactory[org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal]} = CustomTypesMode.this.optionBigDecimalTEF;
    implicit def stringToTE(s: String): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.StringField,org.squeryl.dsl.TString] = CustomTypesMode.this.stringTEF.createFromNativeJdbcValue(s);
    implicit def optionStringToTE(s: Option[String]): Option[org.squeryl.customtypes.StringField] = s.map[org.squeryl.customtypes.StringField](((x$1: String) => new StringField(x$1)));
    implicit def dateToTE(s: java.util.Date): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.DateField,org.squeryl.dsl.TDate] = CustomTypesMode.this.dateTEF.createFromNativeJdbcValue(s);
    implicit def optionDateToTE(s: Option[java.util.Date]): Option[org.squeryl.customtypes.DateField] = s.map[org.squeryl.customtypes.DateField](((x$2: java.util.Date) => new DateField(x$2)));
    implicit def timestampToTE(s: java.sql.Timestamp): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.TimestampField,org.squeryl.dsl.TTimestamp] = CustomTypesMode.this.timestampTEF.createFromNativeJdbcValue(s);
    implicit def optionTimestampToTE(s: Option[java.sql.Timestamp]): Option[org.squeryl.customtypes.TimestampField] = s.map[org.squeryl.customtypes.TimestampField](((x$3: java.sql.Timestamp) => new TimestampField(x$3)));
    implicit def booleanToTE(s: Boolean): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.BooleanField,org.squeryl.dsl.TBoolean] = CustomTypesMode.this.booleanTEF.createFromNativeJdbcValue(s);
    implicit def optionBooleanToTE(s: Option[Boolean]): Option[org.squeryl.customtypes.BooleanField] = s.map[org.squeryl.customtypes.BooleanField](((x$4: Boolean) => new BooleanField(x$4)));
    implicit def uuidToTE(s: java.util.UUID): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.UuidField,org.squeryl.dsl.TUUID] = CustomTypesMode.this.uuidTEF.createFromNativeJdbcValue(s);
    implicit def optionUUIDToTE(s: Option[java.util.UUID]): Option[org.squeryl.customtypes.UuidField] = s.map[org.squeryl.customtypes.UuidField](((x$5: java.util.UUID) => new UuidField(x$5)));
    implicit def byteToTE(f: Byte): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.ByteField,org.squeryl.dsl.TByte] = CustomTypesMode.this.byteTEF.createFromNativeJdbcValue(f);
    implicit def optionByteToTE(f: Option[Byte]): Option[org.squeryl.customtypes.ByteField] = f.map[org.squeryl.customtypes.ByteField](((x$6: Byte) => new ByteField(x$6)));
    implicit def intToTE(f: org.squeryl.customtypes.IntField): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.IntField,org.squeryl.dsl.TInt] = CustomTypesMode.this.intTEF.create(f);
    implicit def optionIntToTE(f: Option[org.squeryl.customtypes.IntField]): org.squeryl.dsl.TypedExpression[Option[org.squeryl.customtypes.IntField],org.squeryl.dsl.TOptionInt] = CustomTypesMode.this.optionIntTEF.create(f);
    implicit def longToTE(f: Long): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.LongField,org.squeryl.dsl.TLong] = CustomTypesMode.this.longTEF.createFromNativeJdbcValue(f);
    implicit def optionLongToTE(f: Option[Long]): Option[org.squeryl.customtypes.LongField] = f.map[org.squeryl.customtypes.LongField](((x$7: Long) => new LongField(x$7)));
    implicit def floatToTE(f: Float): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.FloatField,org.squeryl.dsl.TFloat] = CustomTypesMode.this.floatTEF.createFromNativeJdbcValue(f);
    implicit def optionFloatToTE(f: Option[Float]): Option[org.squeryl.customtypes.FloatField] = f.map[org.squeryl.customtypes.FloatField](((x$8: Float) => new FloatField(x$8)));
    implicit def doubleToTE(f: Double): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.DoubleField,org.squeryl.dsl.TDouble] = CustomTypesMode.this.doubleTEF.createFromNativeJdbcValue(f);
    implicit def optionDoubleToTE(f: Option[Double]): Option[org.squeryl.customtypes.DoubleField] = f.map[org.squeryl.customtypes.DoubleField](((x$9: Double) => new DoubleField(x$9)));
    implicit def bigDecimalToTE(f: BigDecimal): org.squeryl.dsl.TypedExpression[org.squeryl.customtypes.BigDecimalField,org.squeryl.dsl.TBigDecimal] = CustomTypesMode.this.bigDecimalTEF.createFromNativeJdbcValue(f);
    implicit def optionBigDecimalToTE(f: Option[BigDecimal]): Option[org.squeryl.customtypes.BigDecimalField] = f.map[org.squeryl.customtypes.BigDecimalField](((x$10: BigDecimal) => new BigDecimalField(x$10)))
  };
  object CustomTypesMode extends Object with org.squeryl.customtypes.CustomTypesMode {
    def <init>(): org.squeryl.customtypes.CustomTypesMode.type = {
      CustomTypesMode.super.<init>();
      ()
    }
  };
  class ByteField extends AnyRef with org.squeryl.customtypes.CustomType[Byte] {
    <paramaccessor> private[this] val value: Byte = _;
    <stable> <accessor> <paramaccessor> def value: Byte = ByteField.this.value;
    def <init>(value: Byte): org.squeryl.customtypes.ByteField = {
      ByteField.super.<init>();
      ()
    }
  };
  class IntField extends AnyRef with org.squeryl.customtypes.CustomType[Int] {
    <paramaccessor> private[this] val value: Int = _;
    <stable> <accessor> <paramaccessor> def value: Int = IntField.this.value;
    def <init>(value: Int): org.squeryl.customtypes.IntField = {
      IntField.super.<init>();
      ()
    }
  };
  class StringField extends AnyRef with org.squeryl.customtypes.CustomType[String] {
    <paramaccessor> private[this] val value: String = _;
    <stable> <accessor> <paramaccessor> def value: String = StringField.this.value;
    def <init>(value: String): org.squeryl.customtypes.StringField = {
      StringField.super.<init>();
      ()
    }
  };
  class DoubleField extends AnyRef with org.squeryl.customtypes.CustomType[Double] {
    <paramaccessor> private[this] val value: Double = _;
    <stable> <accessor> <paramaccessor> def value: Double = DoubleField.this.value;
    def <init>(value: Double): org.squeryl.customtypes.DoubleField = {
      DoubleField.super.<init>();
      ()
    }
  };
  class BigDecimalField extends AnyRef with org.squeryl.customtypes.CustomType[BigDecimal] {
    <paramaccessor> private[this] val value: BigDecimal = _;
    <stable> <accessor> <paramaccessor> def value: BigDecimal = BigDecimalField.this.value;
    def <init>(value: BigDecimal): org.squeryl.customtypes.BigDecimalField = {
      BigDecimalField.super.<init>();
      ()
    }
  };
  class FloatField extends AnyRef with org.squeryl.customtypes.CustomType[Float] {
    <paramaccessor> private[this] val value: Float = _;
    <stable> <accessor> <paramaccessor> def value: Float = FloatField.this.value;
    def <init>(value: Float): org.squeryl.customtypes.FloatField = {
      FloatField.super.<init>();
      ()
    }
  };
  class LongField extends AnyRef with org.squeryl.customtypes.CustomType[Long] {
    <paramaccessor> private[this] val value: Long = _;
    <stable> <accessor> <paramaccessor> def value: Long = LongField.this.value;
    def <init>(value: Long): org.squeryl.customtypes.LongField = {
      LongField.super.<init>();
      ()
    }
  };
  class BooleanField extends AnyRef with org.squeryl.customtypes.CustomType[Boolean] {
    <paramaccessor> private[this] val value: Boolean = _;
    <stable> <accessor> <paramaccessor> def value: Boolean = BooleanField.this.value;
    def <init>(value: Boolean): org.squeryl.customtypes.BooleanField = {
      BooleanField.super.<init>();
      ()
    }
  };
  class DateField extends AnyRef with org.squeryl.customtypes.CustomType[java.util.Date] {
    <paramaccessor> private[this] val value: java.util.Date = _;
    <stable> <accessor> <paramaccessor> def value: java.util.Date = DateField.this.value;
    def <init>(value: java.util.Date): org.squeryl.customtypes.DateField = {
      DateField.super.<init>();
      ()
    }
  };
  class TimestampField extends AnyRef with org.squeryl.customtypes.CustomType[java.sql.Timestamp] {
    <paramaccessor> private[this] val value: java.sql.Timestamp = _;
    <stable> <accessor> <paramaccessor> def value: java.sql.Timestamp = TimestampField.this.value;
    def <init>(value: java.sql.Timestamp): org.squeryl.customtypes.TimestampField = {
      TimestampField.super.<init>();
      ()
    }
  };
  class BinaryField extends AnyRef with org.squeryl.customtypes.CustomType[Array[Byte]] {
    <paramaccessor> private[this] val value: Array[Byte] = _;
    <stable> <accessor> <paramaccessor> def value: Array[Byte] = BinaryField.this.value;
    def <init>(value: Array[Byte]): org.squeryl.customtypes.BinaryField = {
      BinaryField.super.<init>();
      ()
    }
  };
  class UuidField extends AnyRef with org.squeryl.customtypes.CustomType[java.util.UUID] {
    <paramaccessor> private[this] val value: java.util.UUID = _;
    <stable> <accessor> <paramaccessor> def value: java.util.UUID = UuidField.this.value;
    def <init>(value: java.util.UUID): org.squeryl.customtypes.UuidField = {
      UuidField.super.<init>();
      ()
    }
  }
}