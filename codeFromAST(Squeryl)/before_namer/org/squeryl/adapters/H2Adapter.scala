package org.squeryl.adapters {
  import org.squeryl.{Session, Schema};
  import java.sql.{SQLException, ResultSet};
  import org.squeryl.internals.{StatementWriter, FieldMetaData, DatabaseAdapter};
  class H2Adapter extends DatabaseAdapter {
    def <init>() = {
      super.<init>();
      ()
    };
    override def uuidTypeDeclaration = "uuid";
    override def isFullOuterJoinSupported = false;
    override def writeColumnDeclaration(fmd: FieldMetaData, isPrimaryKey: Boolean, schema: Schema): String = {
      var res = "  ".$plus(fmd.columnName).$plus(" ").$plus(databaseTypeFor(fmd));
      fmd.defaultValue.foreach(((d) => {
        val v = convertToJdbcValue(d.value.asInstanceOf[AnyRef]);
        if (v.isInstanceOf[String])
          res.$plus$eq(" default \'".$plus(v).$plus("\'"))
        else
          res.$plus$eq(" default ".$plus(v))
      }));
      if (fmd.isOption.unary_$bang)
        res.$plus$eq(" not null")
      else
        ();
      if (isPrimaryKey)
        res.$plus$eq(" primary key")
      else
        ();
      if (supportsAutoIncrementInColumnDeclaration.$amp$amp(fmd.isAutoIncremented))
        res.$plus$eq(" auto_increment")
      else
        ();
      res
    };
    override def isTableDoesNotExistException(e: SQLException): Boolean = e.getErrorCode.$eq$eq(42102)
  }
}