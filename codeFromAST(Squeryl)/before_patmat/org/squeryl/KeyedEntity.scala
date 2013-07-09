package org.squeryl {
  import annotations.Transient;
  import java.sql.SQLException;
  @scala.annotation.implicitNotFound("The method requires an implicit org.squeryl.KeyedEntityDef[${A}, ${K}] in scope, or that it extends the trait KeyedEntity[${K}]") abstract trait KeyedEntityDef[A >: Nothing <: Any, K >: Nothing <: Any] extends Object with org.squeryl.OptionalKeyedEntityDef[A,K] {
    def /*KeyedEntityDef*/$init$(): Unit = {
      ()
    };
    def getId(a: A): K;
    def isPersisted(a: A): Boolean;
    def idPropertyName: String;
    def optimisticCounterPropertyName: Option[String] = scala.None;
    private[squeryl] def isOptimistic: Boolean = KeyedEntityDef.this.optimisticCounterPropertyName.isDefined;
    final def keyedEntityDef: Some[org.squeryl.KeyedEntityDef[A,K]] = scala.Some.apply[org.squeryl.KeyedEntityDef[A,K]](this)
  };
  abstract trait OptionalKeyedEntityDef[A >: Nothing <: Any, K >: Nothing <: Any] extends scala.AnyRef {
    def keyedEntityDef: Option[org.squeryl.KeyedEntityDef[A,K]]
  };
  abstract trait KeyedEntity[K >: Nothing <: Any] extends Object with org.squeryl.PersistenceStatus {
    def /*KeyedEntity*/$init$(): Unit = {
      ()
    };
    def id: K;
    override def hashCode: Int = if (KeyedEntity.this.isPersisted)
      KeyedEntity.this.id.hashCode()
    else
      KeyedEntity.super.hashCode();
    override def equals(z: Any): Boolean = {
      if (z.==(null))
        return false
      else
        ();
      val ar: AnyRef = z.asInstanceOf[AnyRef];
      if (ar.getClass().isAssignableFrom(this.getClass()).unary_!)
        false
      else
        if (KeyedEntity.this.isPersisted)
          KeyedEntity.this.id.==(ar.asInstanceOf[org.squeryl.KeyedEntity[K]].id)
        else
          KeyedEntity.super.equals(z)
    }
  };
  abstract trait PersistenceStatus extends scala.AnyRef {
    def /*PersistenceStatus*/$init$(): Unit = {
      ()
    };
    @transient @org.squeryl.annotations.Transient private[this] var _isPersisted: Boolean = false;
    <accessor> private[squeryl] def _isPersisted: Boolean = PersistenceStatus.this._isPersisted;
    <accessor> private[squeryl] def _isPersisted_=(x$1: Boolean): Unit = PersistenceStatus.this._isPersisted = x$1;
    def isPersisted: Boolean = PersistenceStatus.this._isPersisted
  };
  abstract trait IndirectKeyedEntity[K >: Nothing <: Any, T >: Nothing <: Any] extends Object with org.squeryl.KeyedEntity[K] {
    def idField: T
  };
  abstract trait Optimistic extends scala.AnyRef { self: org.squeryl.Optimistic with org.squeryl.KeyedEntity[_] => 
    def /*Optimistic*/$init$(): Unit = {
      ()
    };
    private[this] val occVersionNumber: Int = 0;
    <stable> <accessor> protected def occVersionNumber: Int = Optimistic.this.occVersionNumber
  };
  object SquerylSQLException extends scala.AnyRef with Serializable {
    def <init>(): org.squeryl.SquerylSQLException.type = {
      SquerylSQLException.super.<init>();
      ()
    };
    def apply(message: String, cause: java.sql.SQLException): org.squeryl.SquerylSQLException = new SquerylSQLException(message, scala.Some.apply[java.sql.SQLException](cause));
    def apply(message: String): org.squeryl.SquerylSQLException = new SquerylSQLException(message, scala.None);
    <synthetic> private def readResolve(): Object = squeryl.this.SquerylSQLException
  };
  class SquerylSQLException extends scala.`package`.RuntimeException {
    <paramaccessor> private[this] val message: String = _;
    <paramaccessor> private[this] val cause: Option[java.sql.SQLException] = _;
    def <init>(message: String, cause: Option[java.sql.SQLException]): org.squeryl.SquerylSQLException = {
      SquerylSQLException.super.<init>(message, cause.orNull[Throwable](scala.this.Predef.conforms[Null]));
      ()
    };
    override def getCause: java.sql.SQLException = SquerylSQLException.this.cause.orNull[java.sql.SQLException](scala.this.Predef.conforms[Null])
  };
  class StaleUpdateException extends scala.`package`.RuntimeException {
    <paramaccessor> private[this] val message: String = _;
    def <init>(message: String): org.squeryl.StaleUpdateException = {
      StaleUpdateException.super.<init>(message);
      ()
    }
  };
  abstract trait EntityMember extends scala.AnyRef {
    def entityRoot[B >: Nothing <: Any]: org.squeryl.Query[B]
  };
  abstract trait ReferentialAction extends scala.AnyRef {
    def event: String;
    def action: String
  };
  class ForeignKeyDeclaration extends scala.AnyRef {
    <paramaccessor> private[this] val idWithinSchema: Int = _;
    <stable> <accessor> <paramaccessor> def idWithinSchema: Int = ForeignKeyDeclaration.this.idWithinSchema;
    <paramaccessor> private[this] val foreignKeyColumnName: String = _;
    <stable> <accessor> <paramaccessor> def foreignKeyColumnName: String = ForeignKeyDeclaration.this.foreignKeyColumnName;
    <paramaccessor> private[this] val referencedPrimaryKey: String = _;
    <stable> <accessor> <paramaccessor> def referencedPrimaryKey: String = ForeignKeyDeclaration.this.referencedPrimaryKey;
    def <init>(idWithinSchema: Int, foreignKeyColumnName: String, referencedPrimaryKey: String): org.squeryl.ForeignKeyDeclaration = {
      ForeignKeyDeclaration.super.<init>();
      ()
    };
    private[this] var _referentialActions: Option[(Option[org.squeryl.ReferentialAction], Option[org.squeryl.ReferentialAction])] = scala.None;
    <accessor> private def _referentialActions: Option[(Option[org.squeryl.ReferentialAction], Option[org.squeryl.ReferentialAction])] = ForeignKeyDeclaration.this._referentialActions;
    <accessor> private def _referentialActions_=(x$1: Option[(Option[org.squeryl.ReferentialAction], Option[org.squeryl.ReferentialAction])]): Unit = ForeignKeyDeclaration.this._referentialActions = x$1;
    private[squeryl] def _isActive: Boolean = ForeignKeyDeclaration.this._referentialActions.!=(scala.None);
    private[squeryl] def _referentialAction1: Option[org.squeryl.ReferentialAction] = ForeignKeyDeclaration.this._referentialActions.get._1;
    private[squeryl] def _referentialAction2: Option[org.squeryl.ReferentialAction] = ForeignKeyDeclaration.this._referentialActions.get._2;
    def unConstrainReference()(implicit ev: org.squeryl.Schema): Unit = ForeignKeyDeclaration.this._referentialActions_=(scala.None);
    def constrainReference()(implicit ev: org.squeryl.Schema): Unit = ForeignKeyDeclaration.this._referentialActions_=(scala.Some.apply[(None.type, None.type)](scala.Tuple2.apply[None.type, None.type](scala.None, scala.None)));
    def constrainReference(a1: org.squeryl.ReferentialAction)(implicit ev: org.squeryl.Schema): Unit = ForeignKeyDeclaration.this._referentialActions_=(scala.Some.apply[(Some[org.squeryl.ReferentialAction], None.type)](scala.Tuple2.apply[Some[org.squeryl.ReferentialAction], None.type](scala.Some.apply[org.squeryl.ReferentialAction](a1), scala.None)));
    def constrainReference(a1: org.squeryl.ReferentialAction, a2: org.squeryl.ReferentialAction)(implicit ev: org.squeryl.Schema): Unit = ForeignKeyDeclaration.this._referentialActions_=(scala.Some.apply[(Some[org.squeryl.ReferentialAction], Some[org.squeryl.ReferentialAction])](scala.Tuple2.apply[Some[org.squeryl.ReferentialAction], Some[org.squeryl.ReferentialAction]](scala.Some.apply[org.squeryl.ReferentialAction](a1), scala.Some.apply[org.squeryl.ReferentialAction](a2))))
  }
}