package org.squeryl.internals {
  import java.sql.ResultSet;
  import org.squeryl.Session;
  import org.squeryl.dsl.TypedExpressionFactory;
  import org.squeryl.dsl.ArrayJdbcMapper;
  abstract class ArrayTEF[P >: _root_.scala.Nothing <: _root_.scala.Any, TE >: _root_.scala.Nothing <: _root_.scala.Any] extends TypedExpressionFactory[Array[P], TE] with ArrayJdbcMapper[java.sql.Array, Array[P]] {
    def <init>() = {
      super.<init>();
      ()
    };
    def sample: Array[P];
    def toWrappedJDBCType(element: P): java.lang.Object;
    def fromWrappedJDBCType(element: Array[java.lang.Object]): Array[P];
    val defaultColumnLength = 1;
    def extractNativeJdbcValue(rs: ResultSet, i: Int) = rs.getArray(i);
    def convertToJdbc(v: Array[P]): java.sql.Array = {
      val content: Array[java.lang.Object] = v.map(((x$1) => toWrappedJDBCType(x$1)));
      val s = Session.currentSession;
      val con = s.connection;
      var rv: java.sql.Array = null;
      try {
        val typ = s.databaseAdapter.arrayCreationType(sample(0).asInstanceOf[scala.AnyRef {
  def getClass: Class[_$1] forSome { 
    <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
  }
}].getClass);
        rv = con.createArrayOf(typ, content)
      } catch {
        case (e @ (_: Exception)) => s.log("Cannot create JDBC array: ".$plus(e.getMessage))
      };
      rv
    };
    def convertFromJdbc(v: java.sql.Array): Array[P] = {
      val s = Session.currentSession;
      var rv: Array[P] = sample.take(0);
      try {
        val obj = v.getArray();
        rv = fromWrappedJDBCType(obj.asInstanceOf[Array[java.lang.Object]])
      } catch {
        case (e @ (_: Exception)) => s.log("Cannot obtain array from JDBC: ".$plus(e.getMessage))
      };
      rv
    }
  }
}