package org.squeryl.internals {
  import java.sql.ResultSet;
  import org.squeryl.Session;
  import org.squeryl.dsl.TypedExpressionFactory;
  import org.squeryl.dsl.ArrayJdbcMapper;
  abstract class ArrayTEF[P >: Nothing <: Any, TE >: Nothing <: Any] extends Object with org.squeryl.dsl.TypedExpressionFactory[Array[P],TE] with org.squeryl.dsl.ArrayJdbcMapper[java.sql.Array,Array[P]] {
    def <init>(): org.squeryl.internals.ArrayTEF[P,TE] = {
      ArrayTEF.super.<init>();
      ()
    };
    def sample: Array[P];
    def toWrappedJDBCType(element: P): Object;
    def fromWrappedJDBCType(element: Array[Object]): Array[P];
    private[this] val defaultColumnLength: Int = 1;
    <stable> <accessor> def defaultColumnLength: Int = ArrayTEF.this.defaultColumnLength;
    def extractNativeJdbcValue(rs: java.sql.ResultSet, i: Int): java.sql.Array = rs.getArray(i);
    def convertToJdbc(v: Array[P]): java.sql.Array = {
      val content: Array[Object] = scala.this.Predef.genericArrayOps[P](v).map[Object, Array[Object]](((x$1: P) => ArrayTEF.this.toWrappedJDBCType(x$1)))(scala.this.Array.canBuildFrom[Object](ClassTag.Object));
      val s: org.squeryl.AbstractSession = org.squeryl.Session.currentSession;
      val con: java.sql.Connection = s.connection;
      var rv: java.sql.Array = null;
      try {
        val typ: String = s.databaseAdapter.arrayCreationType(ArrayTEF.this.sample.apply(0).asInstanceOf[AnyRef{def getClass(): Class[_]}].getClass());
        rv = con.createArrayOf(typ, content)
      } catch {
        case (e @ (_: Exception)) => s.log("Cannot create JDBC array: ".+(e.getMessage()))
      };
      rv
    };
    def convertFromJdbc(v: java.sql.Array): Array[P] = {
      val s: org.squeryl.AbstractSession = org.squeryl.Session.currentSession;
      var rv: Array[P] = scala.this.Predef.genericArrayOps[P](ArrayTEF.this.sample).take(0);
      try {
        val obj: Object = v.getArray();
        rv = ArrayTEF.this.fromWrappedJDBCType(obj.asInstanceOf[Array[Object]])
      } catch {
        case (e @ (_: Exception)) => s.log("Cannot obtain array from JDBC: ".+(e.getMessage()))
      };
      rv
    }
  }
}