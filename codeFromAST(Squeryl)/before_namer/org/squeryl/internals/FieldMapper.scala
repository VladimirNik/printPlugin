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
  abstract trait FieldMapper extends scala.AnyRef { outer => 
    def $init$() = {
      ()
    };
    private val registry = new HashMap[Class[_$1] forSome { 
      <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
    }, FieldAttributesBasedOnType[_$2] forSome { 
      <synthetic> type _$2 >: _root_.scala.Nothing <: _root_.scala.Any
    }]();
    implicit def thisFieldMapper = this;
    protected object PrimitiveTypeSupport extends scala.AnyRef {
      def <init>() = {
        super.<init>();
        ()
      };
      val stringTEF = {
        final class $anon extends TypedExpressionFactory[String, TString] with PrimitiveJdbcMapper[String] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = ("": String);
          val defaultColumnLength = 128;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getString(i)
        };
        new $anon()
      };
      val optionStringTEF = {
        final class $anon extends TypedExpressionFactory[Option[String], TOptionString] with DeOptionizer[String, String, TString, Option[String], TOptionString] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = stringTEF
        };
        new $anon()
      };
      val dateTEF = {
        final class $anon extends TypedExpressionFactory[Date, TDate] with PrimitiveJdbcMapper[Date] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = new Date();
          val defaultColumnLength = -1;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getDate(i)
        };
        new $anon()
      };
      val optionDateTEF = {
        final class $anon extends TypedExpressionFactory[Option[Date], TOptionDate] with DeOptionizer[Date, Date, TDate, Option[Date], TOptionDate] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = dateTEF
        };
        new $anon()
      };
      val timestampTEF = {
        final class $anon extends TypedExpressionFactory[Timestamp, TTimestamp] with PrimitiveJdbcMapper[Timestamp] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = new Timestamp(0);
          val defaultColumnLength = -1;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getTimestamp(i)
        };
        new $anon()
      };
      val optionTimestampTEF = {
        final class $anon extends TypedExpressionFactory[Option[Timestamp], TOptionTimestamp] with DeOptionizer[Timestamp, Timestamp, TTimestamp, Option[Timestamp], TOptionTimestamp] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = timestampTEF
        };
        new $anon()
      };
      val booleanTEF = {
        final class $anon extends TypedExpressionFactory[Boolean, TBoolean] with PrimitiveJdbcMapper[Boolean] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = true;
          val defaultColumnLength = 1;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getBoolean(i)
        };
        new $anon()
      };
      val optionBooleanTEF = {
        final class $anon extends TypedExpressionFactory[Option[Boolean], TOptionBoolean] with DeOptionizer[Boolean, Boolean, TBoolean, Option[Boolean], TOptionBoolean] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = booleanTEF
        };
        new $anon()
      };
      val uuidTEF = {
        final class $anon extends TypedExpressionFactory[UUID, TUUID] with PrimitiveJdbcMapper[UUID] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = java.util.UUID.fromString("00000000-0000-0000-0000-000000000000");
          val defaultColumnLength = 36;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = {
            val v = rs.getObject(i);
            v match {
              case (u @ (_: UUID)) => u
              case (s @ (_: String)) => UUID.fromString(s)
              case _ => sample
            }
          }
        };
        new $anon()
      };
      val optionUUIDTEF = {
        final class $anon extends TypedExpressionFactory[Option[UUID], TOptionUUID] with DeOptionizer[UUID, UUID, TUUID, Option[UUID], TOptionUUID] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = uuidTEF
        };
        new $anon()
      };
      val binaryTEF = {
        final class $anon extends TypedExpressionFactory[Array[Byte], TByteArray] with PrimitiveJdbcMapper[Array[Byte]] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = Array((0: Byte));
          val defaultColumnLength = 255;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getBytes(i)
        };
        new $anon()
      };
      val optionByteArrayTEF = {
        final class $anon extends TypedExpressionFactory[Option[Array[Byte]], TOptionByteArray] with DeOptionizer[Array[Byte], Array[Byte], TByteArray, Option[Array[Byte]], TOptionByteArray] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = binaryTEF
        };
        new $anon()
      };
      val intArrayTEF = {
        final class $anon extends ArrayTEF[Int, TIntArray] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = Array(0);
          def toWrappedJDBCType(element: Int): java.lang.Object = new java.lang.Integer(element);
          def fromWrappedJDBCType(elements: Array[java.lang.Object]): Array[Int] = elements.map(((i) => i.asInstanceOf[java.lang.Integer].toInt))
        };
        new $anon()
      };
      val longArrayTEF = {
        final class $anon extends ArrayTEF[Long, TLongArray] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = Array(0L);
          def toWrappedJDBCType(element: Long): java.lang.Object = new java.lang.Long(element);
          def fromWrappedJDBCType(elements: Array[java.lang.Object]): Array[Long] = elements.map(((i) => i.asInstanceOf[java.lang.Long].toLong))
        };
        new $anon()
      };
      val doubleArrayTEF = {
        final class $anon extends ArrayTEF[Double, TDoubleArray] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample: Array[Double] = Array(0.0);
          def toWrappedJDBCType(element: Double): java.lang.Object = new java.lang.Double(element);
          def fromWrappedJDBCType(elements: Array[java.lang.Object]): Array[Double] = elements.map(((i) => i.asInstanceOf[java.lang.Double].toDouble))
        };
        new $anon()
      };
      val stringArrayTEF = {
        final class $anon extends ArrayTEF[String, TStringArray] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample: Array[String] = Array("");
          def toWrappedJDBCType(element: String): java.lang.Object = new java.lang.String(element);
          def fromWrappedJDBCType(elements: Array[java.lang.Object]): Array[String] = elements.map(((i) => i.asInstanceOf[java.lang.String].toString))
        };
        new $anon()
      };
      def enumValueTEF[A >: Enumeration#Value <: Enumeration#Value](ev: Enumeration#Value) = {
        final class $anon extends JdbcMapper[Int, A] with TypedExpressionFactory[A, TEnumValue[A]] {
          def <init>() = {
            super.<init>();
            ()
          };
          val enu = Utils.enumerationForValue(ev);
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getInt(i);
          def defaultColumnLength: Int = intTEF.defaultColumnLength;
          def sample: A = ev;
          def convertToJdbc(v: A) = v.id;
          def convertFromJdbc(v: Int) = enu.values.find(((x$1) => x$1.id.$eq$eq(v))).getOrElse(DummyEnum.DummyEnumerationValue)
        };
        new $anon()
      };
      object DummyEnum extends Enumeration {
        def <init>() = {
          super.<init>();
          ()
        };
        type DummyEnum = Value;
        val DummyEnumerationValue = Value(-1, "DummyEnumerationValue")
      };
      def optionEnumValueTEF[A >: Enumeration#Value <: Enumeration#Value](ev: Option[Enumeration#Value]) = {
        final class $anon extends TypedExpressionFactory[Option[A], TOptionEnumValue[A]] with DeOptionizer[Int, A, TEnumValue[A], Option[A], TOptionEnumValue[A]] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = {
            val e = ev.getOrElse(PrimitiveTypeSupport.DummyEnum.DummyEnumerationValue);
            enumValueTEF[A](e)
          }
        };
        new $anon()
      };
      val byteTEF = {
        final class $anon extends IntegralTypedExpressionFactory[Byte, TByte, Float, TFloat] with PrimitiveJdbcMapper[Byte] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = (1: Byte);
          val defaultColumnLength = 1;
          val floatifyer = floatTEF;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getByte(i)
        };
        new $anon()
      };
      val optionByteTEF = {
        final class $anon extends IntegralTypedExpressionFactory[Option[Byte], TOptionByte, Option[Float], TOptionFloat] with DeOptionizer[Byte, Byte, TByte, Option[Byte], TOptionByte] {
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
        final class $anon extends IntegralTypedExpressionFactory[Int, TInt, Float, TFloat] with PrimitiveJdbcMapper[Int] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = 1;
          val defaultColumnLength = 4;
          val floatifyer = floatTEF;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getInt(i)
        };
        new $anon()
      };
      val optionIntTEF = {
        final class $anon extends IntegralTypedExpressionFactory[Option[Int], TOptionInt, Option[Float], TOptionFloat] with DeOptionizer[Int, Int, TInt, Option[Int], TOptionInt] {
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
        final class $anon extends IntegralTypedExpressionFactory[Long, TLong, Double, TDouble] with PrimitiveJdbcMapper[Long] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = 1L;
          val defaultColumnLength = 8;
          val floatifyer = doubleTEF;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getLong(i)
        };
        new $anon()
      };
      val optionLongTEF = {
        final class $anon extends IntegralTypedExpressionFactory[Option[Long], TOptionLong, Option[Double], TOptionDouble] with DeOptionizer[Long, Long, TLong, Option[Long], TOptionLong] {
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
        final class $anon extends FloatTypedExpressionFactory[Float, TFloat] with PrimitiveJdbcMapper[Float] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = 1.0;
          val defaultColumnLength = 4;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getFloat(i)
        };
        new $anon()
      };
      val optionFloatTEF = {
        final class $anon extends FloatTypedExpressionFactory[Option[Float], TOptionFloat] with DeOptionizer[Float, Float, TFloat, Option[Float], TOptionFloat] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = floatTEF
        };
        new $anon()
      };
      val doubleTEF = {
        final class $anon extends FloatTypedExpressionFactory[Double, TDouble] with PrimitiveJdbcMapper[Double] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = 1.0;
          val defaultColumnLength = 8;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getDouble(i)
        };
        new $anon()
      };
      val optionDoubleTEF = {
        final class $anon extends FloatTypedExpressionFactory[Option[Double], TOptionDouble] with DeOptionizer[Double, Double, TDouble, Option[Double], TOptionDouble] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = doubleTEF
        };
        new $anon()
      };
      val bigDecimalTEF = {
        final class $anon extends FloatTypedExpressionFactory[BigDecimal, TBigDecimal] with PrimitiveJdbcMapper[BigDecimal] {
          def <init>() = {
            super.<init>();
            ()
          };
          val sample = BigDecimal(1);
          val defaultColumnLength = -1;
          def extractNativeJdbcValue(rs: ResultSet, i: Int) = BigDecimal(rs.getBigDecimal(i))
        };
        new $anon()
      };
      val optionBigDecimalTEF = {
        final class $anon extends FloatTypedExpressionFactory[Option[BigDecimal], TOptionBigDecimal] with DeOptionizer[BigDecimal, BigDecimal, TBigDecimal, Option[BigDecimal], TOptionBigDecimal] {
          def <init>() = {
            super.<init>();
            ()
          };
          val deOptionizer = bigDecimalTEF
        };
        new $anon()
      }
    };
    initialize;
    protected def initialize: scala.Unit = {
      import PrimitiveTypeSupport._;
      register(byteTEF);
      register(intTEF);
      register(longTEF);
      register(floatTEF);
      register(doubleTEF);
      register(bigDecimalTEF);
      register(binaryTEF);
      register(booleanTEF);
      register(stringTEF);
      register(timestampTEF);
      register(dateTEF);
      register(uuidTEF);
      register(intArrayTEF);
      register(longArrayTEF);
      register(doubleArrayTEF);
      register(stringArrayTEF);
      val re = enumValueTEF(DummyEnum.DummyEnumerationValue);
      val z = new FieldAttributesBasedOnType[Any]({
        final class $anon extends scala.AnyRef {
          def <init>() = {
            super.<init>();
            ()
          };
          def map(rs: ResultSet, i: Int) = rs.getInt(i);
          def convertToJdbc(v: AnyRef) = v
        };
        new $anon()
      }, re.defaultColumnLength, re.sample, classOf[java.lang.Integer]);
      registry.put(z.clasz, z);
      registry.put(z.clasz.getSuperclass, z)
    };
    protected type MapperForReflection = scala.AnyRef {
      def map(rs: ResultSet, i: Int): Any;
      def convertToJdbc(v: AnyRef): AnyRef
    };
    protected def makeMapper(fa0: JdbcMapper[_$3, _$4] forSome { 
      <synthetic> type _$3 >: _root_.scala.Nothing <: _root_.scala.Any;
      <synthetic> type _$4 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = {
      final class $anon extends scala.AnyRef {
        def <init>() = {
          super.<init>();
          ()
        };
        val fa = fa0.asInstanceOf[JdbcMapper[AnyRef, AnyRef]];
        def map(rs: ResultSet, i: Int) = fa.map(rs, i);
        def convertToJdbc(v: AnyRef): AnyRef = if (v.$bang$eq(null))
          fa.convertToJdbc(v)
        else
          null
      };
      new $anon()
    };
    protected class FieldAttributesBasedOnType[A >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
      <paramaccessor> val mapper: MapperForReflection = _;
      <paramaccessor> val defaultLength: Int = _;
      <paramaccessor> val sample: A = _;
      <paramaccessor> val nativeJdbcType: Class[_$5] forSome { 
        <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
      } = _;
      def <init>(mapper: MapperForReflection, defaultLength: Int, sample: A, nativeJdbcType: Class[_$5] forSome { 
        <synthetic> type _$5 >: _root_.scala.Nothing <: _root_.scala.Any
      }) = {
        super.<init>();
        ()
      };
      val clasz: Class[_$6] forSome { 
        <synthetic> type _$6 >: _root_.scala.Nothing <: _root_.scala.Any
      } = sample.asInstanceOf[AnyRef].getClass;
      override def toString = clasz.getCanonicalName.$plus(" --> ").$plus(mapper.getClass.getCanonicalName)
    };
    def nativeJdbcValueFor(nonNativeType: Class[_$7] forSome { 
      <synthetic> type _$7 >: _root_.scala.Nothing <: _root_.scala.Any
    }, r: AnyRef) = get(nonNativeType).mapper.convertToJdbc(r);
    def isSupported(c: Class[_$8] forSome { 
      <synthetic> type _$8 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = lookup(c).$bang$eq(None).$bar$bar(c.isAssignableFrom(classOf[Some[_$9] forSome { 
  <synthetic> type _$9 >: _root_.scala.Nothing <: _root_.scala.Any
}])).$bar$bar(classOf[Product1[Any]].isAssignableFrom(c));
    def defaultColumnLength(c: Class[_$10] forSome { 
      <synthetic> type _$10 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = get(c).defaultLength;
    def nativeJdbcTypeFor(c: Class[_$11] forSome { 
      <synthetic> type _$11 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = get(c).nativeJdbcType;
    def resultSetHandlerFor(c: Class[_$12] forSome { 
      <synthetic> type _$12 >: _root_.scala.Nothing <: _root_.scala.Any
    }): _root_.scala.Function2[ResultSet, Int, AnyRef] = {
      val fa = get(c);
      ((rs: ResultSet, i: Int) => {
        val z = fa.mapper.map(rs, i);
        if (rs.wasNull)
          null
        else
          z.asInstanceOf[AnyRef]
      })
    };
    private def get(c: Class[_$13] forSome { 
      <synthetic> type _$13 >: _root_.scala.Nothing <: _root_.scala.Any
    }) = lookup(c).getOrElse(Utils.throwError("Usupported native type ".$plus(c.getCanonicalName).$plus(",").$plus(c.getName).$plus("\n").$plus(registry.mkString("\n"))));
    def sampleValueFor(c: Class[_$14] forSome { 
      <synthetic> type _$14 >: _root_.scala.Nothing <: _root_.scala.Any
    }): AnyRef = get(c).sample.asInstanceOf[AnyRef];
    def trySampleValueFor(c: Class[_$15] forSome { 
      <synthetic> type _$15 >: _root_.scala.Nothing <: _root_.scala.Any
    }): AnyRef = {
      val r = lookup(c).map(((x$2) => x$2.sample));
      r match {
        case Some((x @ (_: AnyRef))) => x
        case _ => null
      }
    };
    private[squeryl] def register[P >: _root_.scala.Nothing <: _root_.scala.Any, A >: _root_.scala.Nothing <: _root_.scala.Any](m: NonPrimitiveJdbcMapper[P, A, _$16] forSome { 
      <synthetic> type _$16 >: _root_.scala.Nothing <: _root_.scala.Any
    }): scala.Unit = {
      val z = new FieldAttributesBasedOnType(makeMapper(m), m.defaultColumnLength, m.sample, m.primitiveMapper.nativeJdbcType);
      val wasThere = registry.put(z.clasz, z);
      if (wasThere.$bang$eq(None))
        Utils.throwError("field type ".$plus(z.clasz).$plus(" already registered, handled by ").$plus(m.getClass.getCanonicalName))
      else
        ()
    };
    private[squeryl] def register[S >: _root_.scala.Nothing <: _root_.scala.Any, J >: _root_.scala.Nothing <: _root_.scala.Any](m: ArrayJdbcMapper[S, J]): scala.Unit = {
      val f = m.thisTypedExpressionFactory;
      val z = new FieldAttributesBasedOnType(makeMapper(m), m.defaultColumnLength, f.sample, m.nativeJdbcType);
      val wasThere = registry.put(z.clasz, z);
      if (wasThere.$bang$eq(None))
        Utils.throwError("field type ".$plus(z.clasz).$plus(" already registered, handled by ").$plus(m.getClass.getCanonicalName))
      else
        ()
    };
    private def register[A >: _root_.scala.Nothing <: _root_.scala.Any](pm: PrimitiveJdbcMapper[A]): scala.Unit = {
      val f = pm.thisTypedExpressionFactory;
      val z = new FieldAttributesBasedOnType(makeMapper(pm), f.defaultColumnLength, f.sample, pm.nativeJdbcType);
      val c = z.clasz;
      registry.put(c, z)
    };
    private def lookup(c: Class[_$17] forSome { 
      <synthetic> type _$17 >: _root_.scala.Nothing <: _root_.scala.Any
    }): Option[FieldAttributesBasedOnType[_$18] forSome { 
      <synthetic> type _$18 >: _root_.scala.Nothing <: _root_.scala.Any
    }] = if (c.isPrimitive.unary_$bang)
      registry.get(c)
    else
      c.getName match {
        case "int" => lookup(classOf[java.lang.Integer])
        case "long" => lookup(classOf[java.lang.Long])
        case "float" => lookup(classOf[java.lang.Float])
        case "byte" => lookup(classOf[java.lang.Byte])
        case "boolean" => lookup(classOf[java.lang.Boolean])
        case "double" => lookup(classOf[java.lang.Double])
        case "void" => None
      }
  }
}