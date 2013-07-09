package org.squeryl {
  import annotations.Transient;
  import java.sql.SQLException;
  @new scala.annotation.implicitNotFound(msg = "The method requires an implicit org.squeryl.KeyedEntityDef[${A}, ${K}] in scope, or that it extends the trait KeyedEntity[${K}]") abstract trait KeyedEntityDef[A >: _root_.scala.Nothing <: _root_.scala.Any, K >: _root_.scala.Nothing <: _root_.scala.Any] extends OptionalKeyedEntityDef[A, K] {
    def $init$() = {
      ()
    };
    def getId(a: A): K;
    def isPersisted(a: A): Boolean;
    def idPropertyName: String;
    def optimisticCounterPropertyName: Option[String] = None;
    private[squeryl] def isOptimistic = optimisticCounterPropertyName.isDefined;
    final def keyedEntityDef = Some(this)
  };
  abstract trait OptionalKeyedEntityDef[A >: _root_.scala.Nothing <: _root_.scala.Any, K >: _root_.scala.Nothing <: _root_.scala.Any] extends scala.AnyRef {
    def keyedEntityDef: Option[KeyedEntityDef[A, K]]
  };
  abstract trait KeyedEntity[K >: _root_.scala.Nothing <: _root_.scala.Any] extends PersistenceStatus {
    def $init$() = {
      ()
    };
    def id: K;
    override def hashCode = if (isPersisted)
      id.hashCode
    else
      super.hashCode;
    override def equals(z: Any): Boolean = {
      if (z.$eq$eq(null))
        return false
      else
        ();
      val ar = z.asInstanceOf[AnyRef];
      if (ar.getClass.isAssignableFrom(this.getClass).unary_$bang)
        false
      else
        if (isPersisted)
          id.$eq$eq(ar.asInstanceOf[KeyedEntity[K]].id)
        else
          super.equals(z)
    }
  };
  abstract trait PersistenceStatus extends scala.AnyRef {
    def $init$() = {
      ()
    };
    @new transient() @new Transient() private[squeryl] var _isPersisted = false;
    def isPersisted: Boolean = _isPersisted
  };
  abstract trait IndirectKeyedEntity[K >: _root_.scala.Nothing <: _root_.scala.Any, T >: _root_.scala.Nothing <: _root_.scala.Any] extends KeyedEntity[K] {
    def idField: T
  };
  abstract trait Optimistic extends scala.AnyRef { self: KeyedEntity[_$1] forSome { 
    <synthetic> type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
  } => 
    def $init$() = {
      ()
    };
    protected val occVersionNumber = 0
  };
  object SquerylSQLException extends scala.AnyRef {
    def <init>() = {
      super.<init>();
      ()
    };
    def apply(message: String, cause: SQLException) = new SquerylSQLException(message, Some(cause));
    def apply(message: String) = new SquerylSQLException(message, None)
  };
  class SquerylSQLException extends RuntimeException {
    <paramaccessor> private[this] val message: String = _;
    <paramaccessor> private[this] val cause: Option[SQLException] = _;
    def <init>(message: String, cause: Option[SQLException]) = {
      super.<init>(message, cause.orNull);
      ()
    };
    override def getCause: SQLException = cause.orNull
  };
  class StaleUpdateException extends RuntimeException {
    <paramaccessor> private[this] val message: String = _;
    def <init>(message: String) = {
      super.<init>(message);
      ()
    }
  };
  abstract trait EntityMember extends scala.AnyRef {
    def entityRoot[B >: _root_.scala.Nothing <: _root_.scala.Any]: Query[B]
  };
  abstract trait ReferentialAction extends scala.AnyRef {
    def event: String;
    def action: String
  };
  class ForeignKeyDeclaration extends scala.AnyRef {
    <paramaccessor> val idWithinSchema: Int = _;
    <paramaccessor> val foreignKeyColumnName: String = _;
    <paramaccessor> val referencedPrimaryKey: String = _;
    def <init>(idWithinSchema: Int, foreignKeyColumnName: String, referencedPrimaryKey: String) = {
      super.<init>();
      ()
    };
    private var _referentialActions: Option[scala.Tuple2[Option[ReferentialAction], Option[ReferentialAction]]] = None;
    private[squeryl] def _isActive = _referentialActions.$bang$eq(None);
    private[squeryl] def _referentialAction1: Option[ReferentialAction] = _referentialActions.get._1;
    private[squeryl] def _referentialAction2: Option[ReferentialAction] = _referentialActions.get._2;
    def unConstrainReference()(implicit ev: Schema) = _referentialActions = None;
    def constrainReference()(implicit ev: Schema) = _referentialActions = Some(scala.Tuple2(None, None));
    def constrainReference(a1: ReferentialAction)(implicit ev: Schema) = _referentialActions = Some(scala.Tuple2(Some(a1), None));
    def constrainReference(a1: ReferentialAction, a2: ReferentialAction)(implicit ev: Schema) = _referentialActions = Some(scala.Tuple2(Some(a1), Some(a2)))
  }
}