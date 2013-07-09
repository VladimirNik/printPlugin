package org.squeryl.adapters {
  import org.squeryl.{Session, Schema};
  import java.sql.{SQLException, ResultSet};
  import org.squeryl.internals.{StatementWriter, FieldMetaData, DatabaseAdapter};
  class H2Adapter extends Object with org.squeryl.internals.DatabaseAdapter {
    def <init>(): org.squeryl.adapters.H2Adapter = {
      H2Adapter.super.<init>();
      ()
    };
    override def uuidTypeDeclaration: String = "uuid";
    override def isFullOuterJoinSupported: Boolean = false;
    override def writeColumnDeclaration(fmd: org.squeryl.internals.FieldMetaData, isPrimaryKey: Boolean, schema: org.squeryl.Schema): String = {
      var res: String = "  ".+(fmd.columnName).+(" ").+(H2Adapter.this.databaseTypeFor(fmd));
      fmd.defaultValue.foreach[Unit](((d: org.squeryl.dsl.ast.ConstantTypedExpression[_, _]) => {
        val v: AnyRef = H2Adapter.this.convertToJdbcValue(d.value.asInstanceOf[AnyRef]);
        if (v.isInstanceOf[String])
          res = res.+(" default \'".+(v).+("\'"))
        else
          res = res.+(" default ".+(v))
      }));
      if (fmd.isOption.unary_!)
        res = res.+(" not null")
      else
        ();
      if (isPrimaryKey)
        res = res.+(" primary key")
      else
        ();
      if (H2Adapter.this.supportsAutoIncrementInColumnDeclaration.&&(fmd.isAutoIncremented))
        res = res.+(" auto_increment")
      else
        ();
      res
    };
    override def isTableDoesNotExistException(e: java.sql.SQLException): Boolean = e.getErrorCode().==(42102)
  }
}