package org.squeryl.internals {
  import scala.collection.mutable.HashMap;
  import java.sql.ResultSet;
  import java.sql.Timestamp;
  import java.util.Date;
  import java.util.UUID;
  import org.squeryl.dsl._;
  import java.util.regex.Pattern;
  import org.squeryl.Session;
  import org.squeryl.dsl.ArrayJdbcMapper;
  abstract trait FieldMapper extends scala.AnyRef { outer: org.squeryl.internals.FieldMapper => 
    def /*FieldMapper*/$init$(): Unit = {
      ()
    };
    private[this] val registry: scala.collection.mutable.HashMap[Class[_],FieldMapper.this.FieldAttributesBasedOnType[_]] = new scala.collection.mutable.HashMap[Class[_],FieldMapper.this.FieldAttributesBasedOnType[_]]();
    <stable> <accessor> private def registry: scala.collection.mutable.HashMap[Class[_],FieldMapper.this.FieldAttributesBasedOnType[_]] = FieldMapper.this.registry;
    implicit def thisFieldMapper: org.squeryl.internals.FieldMapper = this;
    protected object PrimitiveTypeSupport extends scala.AnyRef {
      def <init>(): FieldMapper.this.PrimitiveTypeSupport.type = {
        PrimitiveTypeSupport.super.<init>();
        ()
      };
      private[this] val stringTEF: org.squeryl.dsl.TypedExpressionFactory[String,org.squeryl.dsl.TString] with org.squeryl.dsl.PrimitiveJdbcMapper[String]{val sample: String; val defaultColumnLength: Int} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[String,org.squeryl.dsl.TString] with org.squeryl.dsl.PrimitiveJdbcMapper[String] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: String = ("": String);
          <stable> <accessor> def sample: String = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 128;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): String = rs.getString(i)
        };
        new $anon()
      };
      <stable> <accessor> def stringTEF: org.squeryl.dsl.TypedExpressionFactory[String,org.squeryl.dsl.TString] with org.squeryl.dsl.PrimitiveJdbcMapper[String]{val sample: String; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.stringTEF;
      private[this] val optionStringTEF: org.squeryl.dsl.TypedExpressionFactory[Option[String],org.squeryl.dsl.TOptionString] with org.squeryl.dsl.DeOptionizer[String,String,org.squeryl.dsl.TString,Option[String],org.squeryl.dsl.TOptionString]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[String,org.squeryl.dsl.TString] with org.squeryl.dsl.PrimitiveJdbcMapper[String]{val sample: String; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[String],org.squeryl.dsl.TOptionString] with org.squeryl.dsl.DeOptionizer[String,String,org.squeryl.dsl.TString,Option[String],org.squeryl.dsl.TOptionString] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[String,org.squeryl.dsl.TString] with org.squeryl.dsl.PrimitiveJdbcMapper[String]{val sample: String; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.stringTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.TypedExpressionFactory[String,org.squeryl.dsl.TString] with org.squeryl.dsl.PrimitiveJdbcMapper[String]{val sample: String; val defaultColumnLength: Int} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionStringTEF: org.squeryl.dsl.TypedExpressionFactory[Option[String],org.squeryl.dsl.TOptionString] with org.squeryl.dsl.DeOptionizer[String,String,org.squeryl.dsl.TString,Option[String],org.squeryl.dsl.TOptionString]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[String,org.squeryl.dsl.TString] with org.squeryl.dsl.PrimitiveJdbcMapper[String]{val sample: String; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionStringTEF;
      private[this] val dateTEF: org.squeryl.dsl.TypedExpressionFactory[java.util.Date,org.squeryl.dsl.TDate] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.Date]{val sample: java.util.Date; val defaultColumnLength: Int; def extractNativeJdbcValue(rs: java.sql.ResultSet,i: Int): java.sql.Date} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[java.util.Date,org.squeryl.dsl.TDate] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.Date] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: java.util.Date = new java.util.Date();
          <stable> <accessor> def sample: java.util.Date = $anon.this.sample;
          private[this] val defaultColumnLength: Int = -1;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): java.sql.Date = rs.getDate(i)
        };
        new $anon()
      };
      <stable> <accessor> def dateTEF: org.squeryl.dsl.TypedExpressionFactory[java.util.Date,org.squeryl.dsl.TDate] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.Date]{val sample: java.util.Date; val defaultColumnLength: Int; def extractNativeJdbcValue(rs: java.sql.ResultSet,i: Int): java.sql.Date} = PrimitiveTypeSupport.this.dateTEF;
      private[this] val optionDateTEF: org.squeryl.dsl.TypedExpressionFactory[Option[java.util.Date],org.squeryl.dsl.TOptionDate] with org.squeryl.dsl.DeOptionizer[java.util.Date,java.util.Date,org.squeryl.dsl.TDate,Option[java.util.Date],org.squeryl.dsl.TOptionDate]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.util.Date,org.squeryl.dsl.TDate] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.Date]{val sample: java.util.Date; val defaultColumnLength: Int; def extractNativeJdbcValue(rs: java.sql.ResultSet,i: Int): java.sql.Date}} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[java.util.Date],org.squeryl.dsl.TOptionDate] with org.squeryl.dsl.DeOptionizer[java.util.Date,java.util.Date,org.squeryl.dsl.TDate,Option[java.util.Date],org.squeryl.dsl.TOptionDate] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.util.Date,org.squeryl.dsl.TDate] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.Date]{val sample: java.util.Date; val defaultColumnLength: Int; def extractNativeJdbcValue(rs: java.sql.ResultSet,i: Int): java.sql.Date} = PrimitiveTypeSupport.this.dateTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.util.Date,org.squeryl.dsl.TDate] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.Date]{val sample: java.util.Date; val defaultColumnLength: Int; def extractNativeJdbcValue(rs: java.sql.ResultSet,i: Int): java.sql.Date} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionDateTEF: org.squeryl.dsl.TypedExpressionFactory[Option[java.util.Date],org.squeryl.dsl.TOptionDate] with org.squeryl.dsl.DeOptionizer[java.util.Date,java.util.Date,org.squeryl.dsl.TDate,Option[java.util.Date],org.squeryl.dsl.TOptionDate]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.util.Date,org.squeryl.dsl.TDate] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.Date]{val sample: java.util.Date; val defaultColumnLength: Int; def extractNativeJdbcValue(rs: java.sql.ResultSet,i: Int): java.sql.Date}} = PrimitiveTypeSupport.this.optionDateTEF;
      private[this] val timestampTEF: org.squeryl.dsl.TypedExpressionFactory[java.sql.Timestamp,org.squeryl.dsl.TTimestamp] with org.squeryl.dsl.PrimitiveJdbcMapper[java.sql.Timestamp]{val sample: java.sql.Timestamp; val defaultColumnLength: Int} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[java.sql.Timestamp,org.squeryl.dsl.TTimestamp] with org.squeryl.dsl.PrimitiveJdbcMapper[java.sql.Timestamp] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: java.sql.Timestamp = new java.sql.Timestamp(0L);
          <stable> <accessor> def sample: java.sql.Timestamp = $anon.this.sample;
          private[this] val defaultColumnLength: Int = -1;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): java.sql.Timestamp = rs.getTimestamp(i)
        };
        new $anon()
      };
      <stable> <accessor> def timestampTEF: org.squeryl.dsl.TypedExpressionFactory[java.sql.Timestamp,org.squeryl.dsl.TTimestamp] with org.squeryl.dsl.PrimitiveJdbcMapper[java.sql.Timestamp]{val sample: java.sql.Timestamp; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.timestampTEF;
      private[this] val optionTimestampTEF: org.squeryl.dsl.TypedExpressionFactory[Option[java.sql.Timestamp],org.squeryl.dsl.TOptionTimestamp] with org.squeryl.dsl.DeOptionizer[java.sql.Timestamp,java.sql.Timestamp,org.squeryl.dsl.TTimestamp,Option[java.sql.Timestamp],org.squeryl.dsl.TOptionTimestamp]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.sql.Timestamp,org.squeryl.dsl.TTimestamp] with org.squeryl.dsl.PrimitiveJdbcMapper[java.sql.Timestamp]{val sample: java.sql.Timestamp; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[java.sql.Timestamp],org.squeryl.dsl.TOptionTimestamp] with org.squeryl.dsl.DeOptionizer[java.sql.Timestamp,java.sql.Timestamp,org.squeryl.dsl.TTimestamp,Option[java.sql.Timestamp],org.squeryl.dsl.TOptionTimestamp] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.sql.Timestamp,org.squeryl.dsl.TTimestamp] with org.squeryl.dsl.PrimitiveJdbcMapper[java.sql.Timestamp]{val sample: java.sql.Timestamp; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.timestampTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.sql.Timestamp,org.squeryl.dsl.TTimestamp] with org.squeryl.dsl.PrimitiveJdbcMapper[java.sql.Timestamp]{val sample: java.sql.Timestamp; val defaultColumnLength: Int} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionTimestampTEF: org.squeryl.dsl.TypedExpressionFactory[Option[java.sql.Timestamp],org.squeryl.dsl.TOptionTimestamp] with org.squeryl.dsl.DeOptionizer[java.sql.Timestamp,java.sql.Timestamp,org.squeryl.dsl.TTimestamp,Option[java.sql.Timestamp],org.squeryl.dsl.TOptionTimestamp]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.sql.Timestamp,org.squeryl.dsl.TTimestamp] with org.squeryl.dsl.PrimitiveJdbcMapper[java.sql.Timestamp]{val sample: java.sql.Timestamp; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionTimestampTEF;
      private[this] val booleanTEF: org.squeryl.dsl.TypedExpressionFactory[Boolean,org.squeryl.dsl.TBoolean] with org.squeryl.dsl.PrimitiveJdbcMapper[Boolean]{val sample: Boolean; val defaultColumnLength: Int} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Boolean,org.squeryl.dsl.TBoolean] with org.squeryl.dsl.PrimitiveJdbcMapper[Boolean] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Boolean = true;
          <stable> <accessor> def sample: Boolean = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 1;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): Boolean = rs.getBoolean(i)
        };
        new $anon()
      };
      <stable> <accessor> def booleanTEF: org.squeryl.dsl.TypedExpressionFactory[Boolean,org.squeryl.dsl.TBoolean] with org.squeryl.dsl.PrimitiveJdbcMapper[Boolean]{val sample: Boolean; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.booleanTEF;
      private[this] val optionBooleanTEF: org.squeryl.dsl.TypedExpressionFactory[Option[Boolean],org.squeryl.dsl.TOptionBoolean] with org.squeryl.dsl.DeOptionizer[Boolean,Boolean,org.squeryl.dsl.TBoolean,Option[Boolean],org.squeryl.dsl.TOptionBoolean]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[Boolean,org.squeryl.dsl.TBoolean] with org.squeryl.dsl.PrimitiveJdbcMapper[Boolean]{val sample: Boolean; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[Boolean],org.squeryl.dsl.TOptionBoolean] with org.squeryl.dsl.DeOptionizer[Boolean,Boolean,org.squeryl.dsl.TBoolean,Option[Boolean],org.squeryl.dsl.TOptionBoolean] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[Boolean,org.squeryl.dsl.TBoolean] with org.squeryl.dsl.PrimitiveJdbcMapper[Boolean]{val sample: Boolean; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.booleanTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.TypedExpressionFactory[Boolean,org.squeryl.dsl.TBoolean] with org.squeryl.dsl.PrimitiveJdbcMapper[Boolean]{val sample: Boolean; val defaultColumnLength: Int} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionBooleanTEF: org.squeryl.dsl.TypedExpressionFactory[Option[Boolean],org.squeryl.dsl.TOptionBoolean] with org.squeryl.dsl.DeOptionizer[Boolean,Boolean,org.squeryl.dsl.TBoolean,Option[Boolean],org.squeryl.dsl.TOptionBoolean]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[Boolean,org.squeryl.dsl.TBoolean] with org.squeryl.dsl.PrimitiveJdbcMapper[Boolean]{val sample: Boolean; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionBooleanTEF;
      private[this] val uuidTEF: org.squeryl.dsl.TypedExpressionFactory[java.util.UUID,org.squeryl.dsl.TUUID] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.UUID]{val sample: java.util.UUID; val defaultColumnLength: Int} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[java.util.UUID,org.squeryl.dsl.TUUID] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.UUID] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: java.util.UUID = java.util.UUID.fromString("00000000-0000-0000-0000-000000000000");
          <stable> <accessor> def sample: java.util.UUID = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 36;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): java.util.UUID = {
            val v: Object = rs.getObject(i);
            v match {
              case (u @ (_: java.util.UUID)) => u
              case (s @ (_: String)) => java.util.UUID.fromString(s)
              case _ => $anon.this.sample
            }
          }
        };
        new $anon()
      };
      <stable> <accessor> def uuidTEF: org.squeryl.dsl.TypedExpressionFactory[java.util.UUID,org.squeryl.dsl.TUUID] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.UUID]{val sample: java.util.UUID; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.uuidTEF;
      private[this] val optionUUIDTEF: org.squeryl.dsl.TypedExpressionFactory[Option[java.util.UUID],org.squeryl.dsl.TOptionUUID] with org.squeryl.dsl.DeOptionizer[java.util.UUID,java.util.UUID,org.squeryl.dsl.TUUID,Option[java.util.UUID],org.squeryl.dsl.TOptionUUID]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.util.UUID,org.squeryl.dsl.TUUID] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.UUID]{val sample: java.util.UUID; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[java.util.UUID],org.squeryl.dsl.TOptionUUID] with org.squeryl.dsl.DeOptionizer[java.util.UUID,java.util.UUID,org.squeryl.dsl.TUUID,Option[java.util.UUID],org.squeryl.dsl.TOptionUUID] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.util.UUID,org.squeryl.dsl.TUUID] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.UUID]{val sample: java.util.UUID; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.uuidTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.util.UUID,org.squeryl.dsl.TUUID] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.UUID]{val sample: java.util.UUID; val defaultColumnLength: Int} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionUUIDTEF: org.squeryl.dsl.TypedExpressionFactory[Option[java.util.UUID],org.squeryl.dsl.TOptionUUID] with org.squeryl.dsl.DeOptionizer[java.util.UUID,java.util.UUID,org.squeryl.dsl.TUUID,Option[java.util.UUID],org.squeryl.dsl.TOptionUUID]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[java.util.UUID,org.squeryl.dsl.TUUID] with org.squeryl.dsl.PrimitiveJdbcMapper[java.util.UUID]{val sample: java.util.UUID; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionUUIDTEF;
      private[this] val binaryTEF: org.squeryl.dsl.TypedExpressionFactory[Array[Byte],org.squeryl.dsl.TByteArray] with org.squeryl.dsl.PrimitiveJdbcMapper[Array[Byte]]{val sample: Array[Byte]; val defaultColumnLength: Int} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Array[Byte],org.squeryl.dsl.TByteArray] with org.squeryl.dsl.PrimitiveJdbcMapper[Array[Byte]] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Array[Byte] = scala.Array.apply((0: Byte));
          <stable> <accessor> def sample: Array[Byte] = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 255;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): Array[Byte] = rs.getBytes(i)
        };
        new $anon()
      };
      <stable> <accessor> def binaryTEF: org.squeryl.dsl.TypedExpressionFactory[Array[Byte],org.squeryl.dsl.TByteArray] with org.squeryl.dsl.PrimitiveJdbcMapper[Array[Byte]]{val sample: Array[Byte]; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.binaryTEF;
      private[this] val optionByteArrayTEF: org.squeryl.dsl.TypedExpressionFactory[Option[Array[Byte]],org.squeryl.dsl.TOptionByteArray] with org.squeryl.dsl.DeOptionizer[Array[Byte],Array[Byte],org.squeryl.dsl.TByteArray,Option[Array[Byte]],org.squeryl.dsl.TOptionByteArray]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[Array[Byte],org.squeryl.dsl.TByteArray] with org.squeryl.dsl.PrimitiveJdbcMapper[Array[Byte]]{val sample: Array[Byte]; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[Array[Byte]],org.squeryl.dsl.TOptionByteArray] with org.squeryl.dsl.DeOptionizer[Array[Byte],Array[Byte],org.squeryl.dsl.TByteArray,Option[Array[Byte]],org.squeryl.dsl.TOptionByteArray] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[Array[Byte],org.squeryl.dsl.TByteArray] with org.squeryl.dsl.PrimitiveJdbcMapper[Array[Byte]]{val sample: Array[Byte]; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.binaryTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.TypedExpressionFactory[Array[Byte],org.squeryl.dsl.TByteArray] with org.squeryl.dsl.PrimitiveJdbcMapper[Array[Byte]]{val sample: Array[Byte]; val defaultColumnLength: Int} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionByteArrayTEF: org.squeryl.dsl.TypedExpressionFactory[Option[Array[Byte]],org.squeryl.dsl.TOptionByteArray] with org.squeryl.dsl.DeOptionizer[Array[Byte],Array[Byte],org.squeryl.dsl.TByteArray,Option[Array[Byte]],org.squeryl.dsl.TOptionByteArray]{val deOptionizer: org.squeryl.dsl.TypedExpressionFactory[Array[Byte],org.squeryl.dsl.TByteArray] with org.squeryl.dsl.PrimitiveJdbcMapper[Array[Byte]]{val sample: Array[Byte]; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionByteArrayTEF;
      private[this] val intArrayTEF: org.squeryl.internals.ArrayTEF[Int,org.squeryl.dsl.TIntArray]{val sample: Array[Int]} = {
        final class $anon extends org.squeryl.internals.ArrayTEF[Int,org.squeryl.dsl.TIntArray] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Array[Int] = scala.Array.apply(0);
          <stable> <accessor> def sample: Array[Int] = $anon.this.sample;
          def toWrappedJDBCType(element: Int): Object = new java.lang.Integer(element);
          def fromWrappedJDBCType(elements: Array[Object]): Array[Int] = scala.this.Predef.refArrayOps[Object](elements).map[Int, Array[Int]](((i: Object) => scala.this.Predef.Integer2int(i.asInstanceOf[Integer]).toInt))(scala.this.Array.canBuildFrom[Int](ClassTag.Int))
        };
        new $anon()
      };
      <stable> <accessor> def intArrayTEF: org.squeryl.internals.ArrayTEF[Int,org.squeryl.dsl.TIntArray]{val sample: Array[Int]} = PrimitiveTypeSupport.this.intArrayTEF;
      private[this] val longArrayTEF: org.squeryl.internals.ArrayTEF[Long,org.squeryl.dsl.TLongArray]{val sample: Array[Long]} = {
        final class $anon extends org.squeryl.internals.ArrayTEF[Long,org.squeryl.dsl.TLongArray] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Array[Long] = scala.Array.apply(0L);
          <stable> <accessor> def sample: Array[Long] = $anon.this.sample;
          def toWrappedJDBCType(element: Long): Object = new java.lang.Long(element);
          def fromWrappedJDBCType(elements: Array[Object]): Array[Long] = scala.this.Predef.refArrayOps[Object](elements).map[Long, Array[Long]](((i: Object) => scala.this.Predef.Long2long(i.asInstanceOf[Long]).toLong))(scala.this.Array.canBuildFrom[Long](ClassTag.Long))
        };
        new $anon()
      };
      <stable> <accessor> def longArrayTEF: org.squeryl.internals.ArrayTEF[Long,org.squeryl.dsl.TLongArray]{val sample: Array[Long]} = PrimitiveTypeSupport.this.longArrayTEF;
      private[this] val doubleArrayTEF: org.squeryl.internals.ArrayTEF[Double,org.squeryl.dsl.TDoubleArray]{val sample: Array[Double]} = {
        final class $anon extends org.squeryl.internals.ArrayTEF[Double,org.squeryl.dsl.TDoubleArray] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Array[Double] = scala.Array.apply(0.0);
          <stable> <accessor> def sample: Array[Double] = $anon.this.sample;
          def toWrappedJDBCType(element: Double): Object = new java.lang.Double(element);
          def fromWrappedJDBCType(elements: Array[Object]): Array[Double] = scala.this.Predef.refArrayOps[Object](elements).map[Double, Array[Double]](((i: Object) => scala.this.Predef.Double2double(i.asInstanceOf[Double]).toDouble))(scala.this.Array.canBuildFrom[Double](ClassTag.Double))
        };
        new $anon()
      };
      <stable> <accessor> def doubleArrayTEF: org.squeryl.internals.ArrayTEF[Double,org.squeryl.dsl.TDoubleArray]{val sample: Array[Double]} = PrimitiveTypeSupport.this.doubleArrayTEF;
      private[this] val stringArrayTEF: org.squeryl.internals.ArrayTEF[String,org.squeryl.dsl.TStringArray]{val sample: Array[String]} = {
        final class $anon extends org.squeryl.internals.ArrayTEF[String,org.squeryl.dsl.TStringArray] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Array[String] = scala.Array.apply[String]("")(ClassTag.apply[String](classOf[java.lang.String]));
          <stable> <accessor> def sample: Array[String] = $anon.this.sample;
          def toWrappedJDBCType(element: String): Object = new java.lang.String(element);
          def fromWrappedJDBCType(elements: Array[Object]): Array[String] = scala.this.Predef.refArrayOps[Object](elements).map[String, Array[String]](((i: Object) => i.asInstanceOf[String].toString()))(scala.this.Array.canBuildFrom[String](ClassTag.apply[String](classOf[java.lang.String])))
        };
        new $anon()
      };
      <stable> <accessor> def stringArrayTEF: org.squeryl.internals.ArrayTEF[String,org.squeryl.dsl.TStringArray]{val sample: Array[String]} = PrimitiveTypeSupport.this.stringArrayTEF;
      def enumValueTEF[A >: Enumeration#Value <: Enumeration#Value](ev: Enumeration#Value): org.squeryl.dsl.JdbcMapper[Int,A] with org.squeryl.dsl.TypedExpressionFactory[A,org.squeryl.dsl.TEnumValue[A]]{val enu: Enumeration} = {
        final class $anon extends Object with org.squeryl.dsl.JdbcMapper[Int,A] with org.squeryl.dsl.TypedExpressionFactory[A,org.squeryl.dsl.TEnumValue[A]] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val enu: Enumeration = Utils.enumerationForValue(ev);
          <stable> <accessor> def enu: Enumeration = $anon.this.enu;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): Int = rs.getInt(i);
          def defaultColumnLength: Int = PrimitiveTypeSupport.this.intTEF.defaultColumnLength;
          def sample: A = ev;
          def convertToJdbc(v: A): Int = v.id;
          def convertFromJdbc(v: Int): Enumeration#Value = $anon.this.enu.values.find(((x$1: this.enu.Value) => x$1.id.==(v))).getOrElse[Enumeration#Value](PrimitiveTypeSupport.this.DummyEnum.DummyEnumerationValue)
        };
        new $anon()
      };
      object DummyEnum extends scala.Enumeration {
        def <init>(): org.squeryl.internals.FieldMapper.PrimitiveTypeSupport.DummyEnum.type = {
          DummyEnum.super.<init>();
          ()
        };
        type DummyEnum = org.squeryl.internals.FieldMapper.PrimitiveTypeSupport.DummyEnum.Value;
        private[this] val DummyEnumerationValue: org.squeryl.internals.FieldMapper.PrimitiveTypeSupport.DummyEnum.Value = DummyEnum.this.Value(-1, "DummyEnumerationValue");
        <stable> <accessor> def DummyEnumerationValue: org.squeryl.internals.FieldMapper.PrimitiveTypeSupport.DummyEnum.Value = DummyEnum.this.DummyEnumerationValue
      };
      def optionEnumValueTEF[A >: Enumeration#Value <: Enumeration#Value](ev: Option[Enumeration#Value]): org.squeryl.dsl.TypedExpressionFactory[Option[A],org.squeryl.dsl.TOptionEnumValue[A]] with org.squeryl.dsl.DeOptionizer[Int,A,org.squeryl.dsl.TEnumValue[A],Option[A],org.squeryl.dsl.TOptionEnumValue[A]]{val deOptionizer: org.squeryl.dsl.JdbcMapper[Int,A] with org.squeryl.dsl.TypedExpressionFactory[A,org.squeryl.dsl.TEnumValue[A]]{val enu: Enumeration}} = {
        final class $anon extends Object with org.squeryl.dsl.TypedExpressionFactory[Option[A],org.squeryl.dsl.TOptionEnumValue[A]] with org.squeryl.dsl.DeOptionizer[Int,A,org.squeryl.dsl.TEnumValue[A],Option[A],org.squeryl.dsl.TOptionEnumValue[A]] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.JdbcMapper[Int,A] with org.squeryl.dsl.TypedExpressionFactory[A,org.squeryl.dsl.TEnumValue[A]]{val enu: Enumeration} = {
            val e: Enumeration#Value = ev.getOrElse[Enumeration#Value](FieldMapper.this.PrimitiveTypeSupport.DummyEnum.DummyEnumerationValue);
            PrimitiveTypeSupport.this.enumValueTEF[A](e)
          };
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.JdbcMapper[Int,A] with org.squeryl.dsl.TypedExpressionFactory[A,org.squeryl.dsl.TEnumValue[A]]{val enu: Enumeration} = $anon.this.deOptionizer
        };
        new $anon()
      };
      private[this] val byteTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Byte,org.squeryl.dsl.TByte,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Byte]{val sample: Byte; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Byte,org.squeryl.dsl.TByte,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Byte] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Byte = (1: Byte);
          <stable> <accessor> def sample: Byte = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 1;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.floatTEF;
          <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int} = $anon.this.floatifyer;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): Byte = rs.getByte(i)
        };
        new $anon()
      };
      <stable> <accessor> def byteTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Byte,org.squeryl.dsl.TByte,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Byte]{val sample: Byte; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.byteTEF;
      private[this] val optionByteTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Byte],org.squeryl.dsl.TOptionByte,Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Byte,Byte,org.squeryl.dsl.TByte,Option[Byte],org.squeryl.dsl.TOptionByte]{val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Byte,org.squeryl.dsl.TByte,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Byte]{val sample: Byte; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}}} = {
        final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Byte],org.squeryl.dsl.TOptionByte,Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Byte,Byte,org.squeryl.dsl.TByte,Option[Byte],org.squeryl.dsl.TOptionByte] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Byte,org.squeryl.dsl.TByte,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Byte]{val sample: Byte; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.byteTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Byte,org.squeryl.dsl.TByte,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Byte]{val sample: Byte; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = $anon.this.deOptionizer;
          private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionFloatTEF;
          <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = $anon.this.floatifyer
        };
        new $anon()
      };
      <stable> <accessor> def optionByteTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Byte],org.squeryl.dsl.TOptionByte,Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Byte,Byte,org.squeryl.dsl.TByte,Option[Byte],org.squeryl.dsl.TOptionByte]{val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Byte,org.squeryl.dsl.TByte,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Byte]{val sample: Byte; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}}} = PrimitiveTypeSupport.this.optionByteTEF;
      private[this] val intTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Int,org.squeryl.dsl.TInt,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Int]{val sample: Int; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Int,org.squeryl.dsl.TInt,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Int] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Int = 1;
          <stable> <accessor> def sample: Int = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 4;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.floatTEF;
          <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int} = $anon.this.floatifyer;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): Int = rs.getInt(i)
        };
        new $anon()
      };
      <stable> <accessor> def intTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Int,org.squeryl.dsl.TInt,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Int]{val sample: Int; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.intTEF;
      private[this] val optionIntTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Int],org.squeryl.dsl.TOptionInt,Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Int,Int,org.squeryl.dsl.TInt,Option[Int],org.squeryl.dsl.TOptionInt]{val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Int,org.squeryl.dsl.TInt,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Int]{val sample: Int; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}}} = {
        final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Int],org.squeryl.dsl.TOptionInt,Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Int,Int,org.squeryl.dsl.TInt,Option[Int],org.squeryl.dsl.TOptionInt] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Int,org.squeryl.dsl.TInt,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Int]{val sample: Int; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.intTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Int,org.squeryl.dsl.TInt,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Int]{val sample: Int; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = $anon.this.deOptionizer;
          private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionFloatTEF;
          <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = $anon.this.floatifyer
        };
        new $anon()
      };
      <stable> <accessor> def optionIntTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Int],org.squeryl.dsl.TOptionInt,Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Int,Int,org.squeryl.dsl.TInt,Option[Int],org.squeryl.dsl.TOptionInt]{val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Int,org.squeryl.dsl.TInt,Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Int]{val sample: Int; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}}} = PrimitiveTypeSupport.this.optionIntTEF;
      private[this] val longTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Long,org.squeryl.dsl.TLong,Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Long]{val sample: Long; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Long,org.squeryl.dsl.TLong,Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Long] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Long = 1L;
          <stable> <accessor> def sample: Long = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 8;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.doubleTEF;
          <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int} = $anon.this.floatifyer;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): Long = rs.getLong(i)
        };
        new $anon()
      };
      <stable> <accessor> def longTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Long,org.squeryl.dsl.TLong,Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Long]{val sample: Long; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.longTEF;
      private[this] val optionLongTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Long],org.squeryl.dsl.TOptionLong,Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Long,Long,org.squeryl.dsl.TLong,Option[Long],org.squeryl.dsl.TOptionLong]{val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Long,org.squeryl.dsl.TLong,Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Long]{val sample: Long; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,Double,org.squeryl.dsl.TDouble,Option[Double],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}}} = {
        final class $anon extends Object with org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Long],org.squeryl.dsl.TOptionLong,Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Long,Long,org.squeryl.dsl.TLong,Option[Long],org.squeryl.dsl.TOptionLong] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Long,org.squeryl.dsl.TLong,Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Long]{val sample: Long; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.longTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Long,org.squeryl.dsl.TLong,Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Long]{val sample: Long; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}} = $anon.this.deOptionizer;
          private[this] val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,Double,org.squeryl.dsl.TDouble,Option[Double],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionDoubleTEF;
          <stable> <accessor> def floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,Double,org.squeryl.dsl.TDouble,Option[Double],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}} = $anon.this.floatifyer
        };
        new $anon()
      };
      <stable> <accessor> def optionLongTEF: org.squeryl.dsl.IntegralTypedExpressionFactory[Option[Long],org.squeryl.dsl.TOptionLong,Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Long,Long,org.squeryl.dsl.TLong,Option[Long],org.squeryl.dsl.TOptionLong]{val deOptionizer: org.squeryl.dsl.IntegralTypedExpressionFactory[Long,org.squeryl.dsl.TLong,Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Long]{val sample: Long; val defaultColumnLength: Int; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}}; val floatifyer: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,Double,org.squeryl.dsl.TDouble,Option[Double],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}}} = PrimitiveTypeSupport.this.optionLongTEF;
      private[this] val floatTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int} = {
        final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Float = 1.0;
          <stable> <accessor> def sample: Float = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 4;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): Float = rs.getFloat(i)
        };
        new $anon()
      };
      <stable> <accessor> def floatTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.floatTEF;
      private[this] val optionFloatTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.floatTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionFloatTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Float],org.squeryl.dsl.TOptionFloat] with org.squeryl.dsl.DeOptionizer[Float,Float,org.squeryl.dsl.TFloat,Option[Float],org.squeryl.dsl.TOptionFloat]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Float,org.squeryl.dsl.TFloat] with org.squeryl.dsl.PrimitiveJdbcMapper[Float]{val sample: Float; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionFloatTEF;
      private[this] val doubleTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int} = {
        final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: Double = 1.0;
          <stable> <accessor> def sample: Double = $anon.this.sample;
          private[this] val defaultColumnLength: Int = 8;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): Double = rs.getDouble(i)
        };
        new $anon()
      };
      <stable> <accessor> def doubleTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.doubleTEF;
      private[this] val optionDoubleTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,Double,org.squeryl.dsl.TDouble,Option[Double],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,Double,org.squeryl.dsl.TDouble,Option[Double],org.squeryl.dsl.TOptionDouble] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.doubleTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionDoubleTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[Double],org.squeryl.dsl.TOptionDouble] with org.squeryl.dsl.DeOptionizer[Double,Double,org.squeryl.dsl.TDouble,Option[Double],org.squeryl.dsl.TOptionDouble]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[Double,org.squeryl.dsl.TDouble] with org.squeryl.dsl.PrimitiveJdbcMapper[Double]{val sample: Double; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionDoubleTEF;
      private[this] val bigDecimalTEF: org.squeryl.dsl.FloatTypedExpressionFactory[BigDecimal,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.PrimitiveJdbcMapper[BigDecimal]{val sample: scala.math.BigDecimal; val defaultColumnLength: Int} = {
        final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[BigDecimal,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.PrimitiveJdbcMapper[BigDecimal] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val sample: scala.math.BigDecimal = scala.`package`.BigDecimal.apply(1);
          <stable> <accessor> def sample: scala.math.BigDecimal = $anon.this.sample;
          private[this] val defaultColumnLength: Int = -1;
          <stable> <accessor> def defaultColumnLength: Int = $anon.this.defaultColumnLength;
          def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): scala.math.BigDecimal = scala.`package`.BigDecimal.apply(rs.getBigDecimal(i))
        };
        new $anon()
      };
      <stable> <accessor> def bigDecimalTEF: org.squeryl.dsl.FloatTypedExpressionFactory[BigDecimal,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.PrimitiveJdbcMapper[BigDecimal]{val sample: scala.math.BigDecimal; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.bigDecimalTEF;
      private[this] val optionBigDecimalTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[BigDecimal],org.squeryl.dsl.TOptionBigDecimal] with org.squeryl.dsl.DeOptionizer[BigDecimal,BigDecimal,org.squeryl.dsl.TBigDecimal,Option[BigDecimal],org.squeryl.dsl.TOptionBigDecimal]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[BigDecimal,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.PrimitiveJdbcMapper[BigDecimal]{val sample: scala.math.BigDecimal; val defaultColumnLength: Int}} = {
        final class $anon extends Object with org.squeryl.dsl.FloatTypedExpressionFactory[Option[BigDecimal],org.squeryl.dsl.TOptionBigDecimal] with org.squeryl.dsl.DeOptionizer[BigDecimal,BigDecimal,org.squeryl.dsl.TBigDecimal,Option[BigDecimal],org.squeryl.dsl.TOptionBigDecimal] {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          private[this] val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[BigDecimal,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.PrimitiveJdbcMapper[BigDecimal]{val sample: scala.math.BigDecimal; val defaultColumnLength: Int} = PrimitiveTypeSupport.this.bigDecimalTEF;
          <stable> <accessor> def deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[BigDecimal,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.PrimitiveJdbcMapper[BigDecimal]{val sample: scala.math.BigDecimal; val defaultColumnLength: Int} = $anon.this.deOptionizer
        };
        new $anon()
      };
      <stable> <accessor> def optionBigDecimalTEF: org.squeryl.dsl.FloatTypedExpressionFactory[Option[BigDecimal],org.squeryl.dsl.TOptionBigDecimal] with org.squeryl.dsl.DeOptionizer[BigDecimal,BigDecimal,org.squeryl.dsl.TBigDecimal,Option[BigDecimal],org.squeryl.dsl.TOptionBigDecimal]{val deOptionizer: org.squeryl.dsl.FloatTypedExpressionFactory[BigDecimal,org.squeryl.dsl.TBigDecimal] with org.squeryl.dsl.PrimitiveJdbcMapper[BigDecimal]{val sample: scala.math.BigDecimal; val defaultColumnLength: Int}} = PrimitiveTypeSupport.this.optionBigDecimalTEF
    };
    FieldMapper.this.initialize;
    protected def initialize: Unit = {
      import FieldMapper.this.PrimitiveTypeSupport._;
      FieldMapper.this.register[Byte](FieldMapper.this.PrimitiveTypeSupport.byteTEF);
      FieldMapper.this.register[Int](FieldMapper.this.PrimitiveTypeSupport.intTEF);
      FieldMapper.this.register[Long](FieldMapper.this.PrimitiveTypeSupport.longTEF);
      FieldMapper.this.register[Float](FieldMapper.this.PrimitiveTypeSupport.floatTEF);
      FieldMapper.this.register[Double](FieldMapper.this.PrimitiveTypeSupport.doubleTEF);
      FieldMapper.this.register[BigDecimal](FieldMapper.this.PrimitiveTypeSupport.bigDecimalTEF);
      FieldMapper.this.register[Array[Byte]](FieldMapper.this.PrimitiveTypeSupport.binaryTEF);
      FieldMapper.this.register[Boolean](FieldMapper.this.PrimitiveTypeSupport.booleanTEF);
      FieldMapper.this.register[String](FieldMapper.this.PrimitiveTypeSupport.stringTEF);
      FieldMapper.this.register[java.sql.Timestamp](FieldMapper.this.PrimitiveTypeSupport.timestampTEF);
      FieldMapper.this.register[java.util.Date](FieldMapper.this.PrimitiveTypeSupport.dateTEF);
      FieldMapper.this.register[java.util.UUID](FieldMapper.this.PrimitiveTypeSupport.uuidTEF);
      FieldMapper.this.register[java.sql.Array, Array[Int]](FieldMapper.this.PrimitiveTypeSupport.intArrayTEF);
      FieldMapper.this.register[java.sql.Array, Array[Long]](FieldMapper.this.PrimitiveTypeSupport.longArrayTEF);
      FieldMapper.this.register[java.sql.Array, Array[Double]](FieldMapper.this.PrimitiveTypeSupport.doubleArrayTEF);
      FieldMapper.this.register[java.sql.Array, Array[String]](FieldMapper.this.PrimitiveTypeSupport.stringArrayTEF);
      val re: org.squeryl.dsl.JdbcMapper[Int,Enumeration#Value] with org.squeryl.dsl.TypedExpressionFactory[Enumeration#Value,org.squeryl.dsl.TEnumValue[Enumeration#Value]]{val enu: Enumeration} = FieldMapper.this.PrimitiveTypeSupport.enumValueTEF[Enumeration#Value](FieldMapper.this.PrimitiveTypeSupport.DummyEnum.DummyEnumerationValue);
      val z: FieldMapper.this.FieldAttributesBasedOnType[Any] = new FieldMapper.this.FieldAttributesBasedOnType[Any]({
        final class $anon extends scala.AnyRef {
          def <init>(): anonymous class $anon = {
            $anon.super.<init>();
            ()
          };
          def map(rs: java.sql.ResultSet, i: Int): Int = rs.getInt(i);
          def convertToJdbc(v: AnyRef): AnyRef = v
        };
        new $anon()
      }, re.defaultColumnLength, re.sample, classOf[java.lang.Integer]);
      FieldMapper.this.registry.put(z.clasz, z);
      {
        FieldMapper.this.registry.put(z.clasz.getSuperclass(), z);
        ()
      }
    };
    protected type MapperForReflection = AnyRef{def map(rs: java.sql.ResultSet,i: Int): Any; def convertToJdbc(v: AnyRef): AnyRef};
    protected def makeMapper(fa0: org.squeryl.dsl.JdbcMapper[_, _]): Object{val fa: org.squeryl.dsl.JdbcMapper[AnyRef,AnyRef]; def map(rs: java.sql.ResultSet,i: Int): AnyRef; def convertToJdbc(v: AnyRef): AnyRef} = {
      final class $anon extends scala.AnyRef {
        def <init>(): anonymous class $anon = {
          $anon.super.<init>();
          ()
        };
        private[this] val fa: org.squeryl.dsl.JdbcMapper[AnyRef,AnyRef] = fa0.asInstanceOf[org.squeryl.dsl.JdbcMapper[AnyRef,AnyRef]];
        <stable> <accessor> def fa: org.squeryl.dsl.JdbcMapper[AnyRef,AnyRef] = $anon.this.fa;
        def map(rs: java.sql.ResultSet, i: Int): AnyRef = $anon.this.fa.map(rs, i);
        def convertToJdbc(v: AnyRef): AnyRef = if (v.!=(null))
          $anon.this.fa.convertToJdbc(v)
        else
          null
      };
      new $anon()
    };
    protected class FieldAttributesBasedOnType[A >: Nothing <: Any] extends scala.AnyRef {
      <paramaccessor> private[this] val mapper: FieldMapper.this.MapperForReflection = _;
      <stable> <accessor> <paramaccessor> def mapper: FieldMapper.this.MapperForReflection = FieldAttributesBasedOnType.this.mapper;
      <paramaccessor> private[this] val defaultLength: Int = _;
      <stable> <accessor> <paramaccessor> def defaultLength: Int = FieldAttributesBasedOnType.this.defaultLength;
      <paramaccessor> private[this] val sample: A = _;
      <stable> <accessor> <paramaccessor> def sample: A = FieldAttributesBasedOnType.this.sample;
      <paramaccessor> private[this] val nativeJdbcType: Class[_] = _;
      <stable> <accessor> <paramaccessor> def nativeJdbcType: Class[_] = FieldAttributesBasedOnType.this.nativeJdbcType;
      def <init>(mapper: FieldMapper.this.MapperForReflection, defaultLength: Int, sample: A, nativeJdbcType: Class[_]): FieldMapper.this.FieldAttributesBasedOnType[A] = {
        FieldAttributesBasedOnType.super.<init>();
        ()
      };
      private[this] val clasz: Class[_] = FieldAttributesBasedOnType.this.sample.asInstanceOf[AnyRef].getClass();
      <stable> <accessor> def clasz: Class[_] = FieldAttributesBasedOnType.this.clasz;
      override def toString: String = FieldAttributesBasedOnType.this.clasz.getCanonicalName().+(" --> ").+(FieldAttributesBasedOnType.this.mapper.getClass().getCanonicalName())
    };
    def nativeJdbcValueFor(nonNativeType: Class[_], r: AnyRef): AnyRef = FieldMapper.this.get(nonNativeType).mapper.convertToJdbc(r);
    def isSupported(c: Class[_]): Boolean = FieldMapper.this.lookup(c).!=(scala.None).||(c.isAssignableFrom(classOf[scala.Some])).||(classOf[scala.Product1].isAssignableFrom(c));
    def defaultColumnLength(c: Class[_]): Int = FieldMapper.this.get(c).defaultLength;
    def nativeJdbcTypeFor(c: Class[_]): Class[_] = FieldMapper.this.get(c).nativeJdbcType;
    def resultSetHandlerFor(c: Class[_]): (java.sql.ResultSet, Int) => AnyRef = {
      val fa: FieldMapper.this.FieldAttributesBasedOnType[_] = FieldMapper.this.get(c);
      ((rs: java.sql.ResultSet, i: Int) => {
        val z: Any = fa.mapper.map(rs, i);
        if (rs.wasNull())
          null
        else
          z.asInstanceOf[AnyRef]
      })
    };
    private def get(c: Class[_]): FieldMapper.this.FieldAttributesBasedOnType[_] = FieldMapper.this.lookup(c).getOrElse[FieldMapper.this.FieldAttributesBasedOnType[_]](Utils.throwError("Usupported native type ".+(c.getCanonicalName()).+(",").+(c.getName()).+("\n").+(FieldMapper.this.registry.mkString("\n"))));
    def sampleValueFor(c: Class[_]): AnyRef = FieldMapper.this.get(c).sample.asInstanceOf[AnyRef];
    def trySampleValueFor(c: Class[_]): AnyRef = {
      val r: Option[Any] = FieldMapper.this.lookup(c).map[Any](((x$2: FieldMapper.this.FieldAttributesBasedOnType[_]) => x$2.sample));
      r match {
        case (x: Any)Some[Any]((x @ (_: AnyRef))) => x
        case _ => null
      }
    };
    private[squeryl] def register[P >: Nothing <: Any, A >: Nothing <: Any](m: org.squeryl.dsl.NonPrimitiveJdbcMapper[P, A, _]): Unit = {
      val z: FieldMapper.this.FieldAttributesBasedOnType[A] = new FieldMapper.this.FieldAttributesBasedOnType[A](FieldMapper.this.makeMapper(m), m.defaultColumnLength, m.sample, m.primitiveMapper.nativeJdbcType);
      val wasThere: Option[FieldMapper.this.FieldAttributesBasedOnType[_]] = FieldMapper.this.registry.put(z.clasz, z);
      if (wasThere.!=(scala.None))
        Utils.throwError("field type ".+(z.clasz).+(" already registered, handled by ").+(m.getClass().getCanonicalName()))
      else
        ()
    };
    private[squeryl] def register[S >: Nothing <: Any, J >: Nothing <: Any](m: org.squeryl.dsl.ArrayJdbcMapper[S,J]): Unit = {
      val f: org.squeryl.dsl.TypedExpressionFactory[J, _] = m.thisTypedExpressionFactory;
      val z: FieldMapper.this.FieldAttributesBasedOnType[J] = new FieldMapper.this.FieldAttributesBasedOnType[J](FieldMapper.this.makeMapper(m), m.defaultColumnLength, f.sample, m.nativeJdbcType);
      val wasThere: Option[FieldMapper.this.FieldAttributesBasedOnType[_]] = FieldMapper.this.registry.put(z.clasz, z);
      if (wasThere.!=(scala.None))
        Utils.throwError("field type ".+(z.clasz).+(" already registered, handled by ").+(m.getClass().getCanonicalName()))
      else
        ()
    };
    private def register[A >: Nothing <: Any](pm: org.squeryl.dsl.PrimitiveJdbcMapper[A]): Unit = {
      val f: org.squeryl.dsl.TypedExpressionFactory[A, _] = pm.thisTypedExpressionFactory;
      val z: FieldMapper.this.FieldAttributesBasedOnType[A] = new FieldMapper.this.FieldAttributesBasedOnType[A](FieldMapper.this.makeMapper(pm), f.defaultColumnLength, f.sample, pm.nativeJdbcType);
      val c: Class[_] = z.clasz;
      {
        FieldMapper.this.registry.put(c, z);
        ()
      }
    };
    private def lookup(c: Class[_]): Option[FieldMapper.this.FieldAttributesBasedOnType[_]] = if (c.isPrimitive().unary_!)
      FieldMapper.this.registry.get(c)
    else
      c.getName() match {
        case "int" => FieldMapper.this.lookup(classOf[java.lang.Integer])
        case "long" => FieldMapper.this.lookup(classOf[java.lang.Long])
        case "float" => FieldMapper.this.lookup(classOf[java.lang.Float])
        case "byte" => FieldMapper.this.lookup(classOf[java.lang.Byte])
        case "boolean" => FieldMapper.this.lookup(classOf[java.lang.Boolean])
        case "double" => FieldMapper.this.lookup(classOf[java.lang.Double])
        case "void" => scala.None
      }
  }
}