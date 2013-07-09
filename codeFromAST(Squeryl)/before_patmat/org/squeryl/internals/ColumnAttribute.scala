package org.squeryl.internals {
  abstract trait ColumnAttribute extends scala.AnyRef;
  abstract trait MultipleColumnAttribute extends scala.AnyRef;
  abstract trait AttributeValidOnMultipleColumn extends Object with org.squeryl.internals.ColumnAttribute;
  abstract trait AttributeValidOnNumericalColumn extends Object with org.squeryl.internals.ColumnAttribute;
  abstract trait AttributeValidOnNonNumericalColumn extends Object with org.squeryl.internals.ColumnAttribute;
  case class Unique extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.MultipleColumnAttribute with org.squeryl.internals.AttributeValidOnNonNumericalColumn with org.squeryl.internals.AttributeValidOnNumericalColumn with org.squeryl.internals.AttributeValidOnMultipleColumn with Product with Serializable {
    def <init>(): org.squeryl.internals.Unique = {
      Unique.super.<init>();
      ()
    };
    <synthetic> def copy(): org.squeryl.internals.Unique = new internals.this.Unique();
    override <synthetic> def productPrefix: String = "Unique";
    <synthetic> def productArity: Int = 0;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](Unique.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.Unique]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(Unique.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(Unique.this);
    override <synthetic> def equals(x$1: Any): Boolean = x$1.isInstanceOf[org.squeryl.internals.Unique].&&(x$1.asInstanceOf[org.squeryl.internals.Unique].canEqual(Unique.this))
  };
  <synthetic> object Unique extends scala.runtime.AbstractFunction0[org.squeryl.internals.Unique] with Serializable {
    def <init>(): org.squeryl.internals.Unique.type = {
      Unique.super.<init>();
      ()
    };
    final override def toString(): String = "Unique";
    case <synthetic> def apply(): org.squeryl.internals.Unique = new internals.this.Unique();
    case <synthetic> def unapply(x$0: org.squeryl.internals.Unique): Boolean = if (x$0.==(null))
      false
    else
      true;
    <synthetic> private def readResolve(): Object = internals.this.Unique
  };
  case class AutoIncremented extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.AttributeValidOnNumericalColumn with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] var nameOfSequence: Option[String] = _;
    <caseaccessor> <accessor> <paramaccessor> def nameOfSequence: Option[String] = AutoIncremented.this.nameOfSequence;
    <accessor> <paramaccessor> def nameOfSequence_=(x$1: Option[String]): Unit = AutoIncremented.this.nameOfSequence = x$1;
    def <init>(nameOfSequence: Option[String]): org.squeryl.internals.AutoIncremented = {
      AutoIncremented.super.<init>();
      ()
    };
    override def hashCode: Int = this.getClass().hashCode();
    override def equals(any: Any): Boolean = any.isInstanceOf[org.squeryl.internals.AutoIncremented];
    <synthetic> def copy(nameOfSequence: Option[String] = nameOfSequence): org.squeryl.internals.AutoIncremented = new internals.this.AutoIncremented(nameOfSequence);
    <synthetic> def copy$default$1: Option[String] @scala.annotation.unchecked.uncheckedVariance = AutoIncremented.this.nameOfSequence;
    override <synthetic> def productPrefix: String = "AutoIncremented";
    <synthetic> def productArity: Int = 1;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => AutoIncremented.this.nameOfSequence
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](AutoIncremented.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.AutoIncremented]();
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(AutoIncremented.this)
  };
  <synthetic> object AutoIncremented extends scala.runtime.AbstractFunction1[Option[String],org.squeryl.internals.AutoIncremented] with Serializable {
    def <init>(): org.squeryl.internals.AutoIncremented.type = {
      AutoIncremented.super.<init>();
      ()
    };
    final override def toString(): String = "AutoIncremented";
    case <synthetic> def apply(nameOfSequence: Option[String]): org.squeryl.internals.AutoIncremented = new internals.this.AutoIncremented(nameOfSequence);
    case <synthetic> def unapply(x$0: org.squeryl.internals.AutoIncremented): Option[Option[String]] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[Option[String]](x$0.nameOfSequence);
    <synthetic> private def readResolve(): Object = internals.this.AutoIncremented
  };
  case class Indexed extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.MultipleColumnAttribute with org.squeryl.internals.AttributeValidOnNonNumericalColumn with org.squeryl.internals.AttributeValidOnNumericalColumn with org.squeryl.internals.AttributeValidOnMultipleColumn with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val nameOfIndex: Option[String] = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def nameOfIndex: Option[String] = Indexed.this.nameOfIndex;
    def <init>(nameOfIndex: Option[String]): org.squeryl.internals.Indexed = {
      Indexed.super.<init>();
      ()
    };
    <synthetic> def copy(nameOfIndex: Option[String] = nameOfIndex): org.squeryl.internals.Indexed = new internals.this.Indexed(nameOfIndex);
    <synthetic> def copy$default$1: Option[String] @scala.annotation.unchecked.uncheckedVariance = Indexed.this.nameOfIndex;
    override <synthetic> def productPrefix: String = "Indexed";
    <synthetic> def productArity: Int = 1;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => Indexed.this.nameOfIndex
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](Indexed.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.Indexed]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(Indexed.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(Indexed.this);
    override <synthetic> def equals(x$1: Any): Boolean = Indexed.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.internals.Indexed].&&({
      <synthetic> val Indexed$1: org.squeryl.internals.Indexed = x$1.asInstanceOf[org.squeryl.internals.Indexed];
      Indexed.this.nameOfIndex.==(Indexed$1.nameOfIndex).&&(Indexed$1.canEqual(Indexed.this))
    }))
  };
  <synthetic> object Indexed extends scala.runtime.AbstractFunction1[Option[String],org.squeryl.internals.Indexed] with Serializable {
    def <init>(): org.squeryl.internals.Indexed.type = {
      Indexed.super.<init>();
      ()
    };
    final override def toString(): String = "Indexed";
    case <synthetic> def apply(nameOfIndex: Option[String]): org.squeryl.internals.Indexed = new internals.this.Indexed(nameOfIndex);
    case <synthetic> def unapply(x$0: org.squeryl.internals.Indexed): Option[Option[String]] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[Option[String]](x$0.nameOfIndex);
    <synthetic> private def readResolve(): Object = internals.this.Indexed
  };
  case class PrimaryKey extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.AttributeValidOnNonNumericalColumn with org.squeryl.internals.AttributeValidOnNumericalColumn with org.squeryl.internals.AttributeValidOnMultipleColumn with Product with Serializable {
    def <init>(): org.squeryl.internals.PrimaryKey = {
      PrimaryKey.super.<init>();
      ()
    };
    <synthetic> def copy(): org.squeryl.internals.PrimaryKey = new internals.this.PrimaryKey();
    override <synthetic> def productPrefix: String = "PrimaryKey";
    <synthetic> def productArity: Int = 0;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](PrimaryKey.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.PrimaryKey]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(PrimaryKey.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(PrimaryKey.this);
    override <synthetic> def equals(x$1: Any): Boolean = x$1.isInstanceOf[org.squeryl.internals.PrimaryKey].&&(x$1.asInstanceOf[org.squeryl.internals.PrimaryKey].canEqual(PrimaryKey.this))
  };
  <synthetic> object PrimaryKey extends scala.runtime.AbstractFunction0[org.squeryl.internals.PrimaryKey] with Serializable {
    def <init>(): org.squeryl.internals.PrimaryKey.type = {
      PrimaryKey.super.<init>();
      ()
    };
    final override def toString(): String = "PrimaryKey";
    case <synthetic> def apply(): org.squeryl.internals.PrimaryKey = new internals.this.PrimaryKey();
    case <synthetic> def unapply(x$0: org.squeryl.internals.PrimaryKey): Boolean = if (x$0.==(null))
      false
    else
      true;
    <synthetic> private def readResolve(): Object = internals.this.PrimaryKey
  };
  case class DBType extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.AttributeValidOnNonNumericalColumn with org.squeryl.internals.AttributeValidOnNumericalColumn with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val declaration: String = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def declaration: String = DBType.this.declaration;
    def <init>(declaration: String): org.squeryl.internals.DBType = {
      DBType.super.<init>();
      ()
    };
    <synthetic> def copy(declaration: String = declaration): org.squeryl.internals.DBType = new internals.this.DBType(declaration);
    <synthetic> def copy$default$1: String @scala.annotation.unchecked.uncheckedVariance = DBType.this.declaration;
    override <synthetic> def productPrefix: String = "DBType";
    <synthetic> def productArity: Int = 1;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => DBType.this.declaration
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](DBType.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.DBType]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(DBType.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(DBType.this);
    override <synthetic> def equals(x$1: Any): Boolean = DBType.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.internals.DBType].&&({
      <synthetic> val DBType$1: org.squeryl.internals.DBType = x$1.asInstanceOf[org.squeryl.internals.DBType];
      DBType.this.declaration.==(DBType$1.declaration).&&(DBType$1.canEqual(DBType.this))
    }))
  };
  <synthetic> object DBType extends scala.runtime.AbstractFunction1[String,org.squeryl.internals.DBType] with Serializable {
    def <init>(): org.squeryl.internals.DBType.type = {
      DBType.super.<init>();
      ()
    };
    final override def toString(): String = "DBType";
    case <synthetic> def apply(declaration: String): org.squeryl.internals.DBType = new internals.this.DBType(declaration);
    case <synthetic> def unapply(x$0: org.squeryl.internals.DBType): Option[String] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[String](x$0.declaration);
    <synthetic> private def readResolve(): Object = internals.this.DBType
  };
  case class Uninsertable extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.AttributeValidOnNumericalColumn with org.squeryl.internals.AttributeValidOnNonNumericalColumn with Product with Serializable {
    def <init>(): org.squeryl.internals.Uninsertable = {
      Uninsertable.super.<init>();
      ()
    };
    <synthetic> def copy(): org.squeryl.internals.Uninsertable = new internals.this.Uninsertable();
    override <synthetic> def productPrefix: String = "Uninsertable";
    <synthetic> def productArity: Int = 0;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](Uninsertable.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.Uninsertable]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(Uninsertable.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(Uninsertable.this);
    override <synthetic> def equals(x$1: Any): Boolean = x$1.isInstanceOf[org.squeryl.internals.Uninsertable].&&(x$1.asInstanceOf[org.squeryl.internals.Uninsertable].canEqual(Uninsertable.this))
  };
  <synthetic> object Uninsertable extends scala.runtime.AbstractFunction0[org.squeryl.internals.Uninsertable] with Serializable {
    def <init>(): org.squeryl.internals.Uninsertable.type = {
      Uninsertable.super.<init>();
      ()
    };
    final override def toString(): String = "Uninsertable";
    case <synthetic> def apply(): org.squeryl.internals.Uninsertable = new internals.this.Uninsertable();
    case <synthetic> def unapply(x$0: org.squeryl.internals.Uninsertable): Boolean = if (x$0.==(null))
      false
    else
      true;
    <synthetic> private def readResolve(): Object = internals.this.Uninsertable
  };
  case class Unupdatable extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.AttributeValidOnNumericalColumn with org.squeryl.internals.AttributeValidOnNonNumericalColumn with Product with Serializable {
    def <init>(): org.squeryl.internals.Unupdatable = {
      Unupdatable.super.<init>();
      ()
    };
    <synthetic> def copy(): org.squeryl.internals.Unupdatable = new internals.this.Unupdatable();
    override <synthetic> def productPrefix: String = "Unupdatable";
    <synthetic> def productArity: Int = 0;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](Unupdatable.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.Unupdatable]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(Unupdatable.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(Unupdatable.this);
    override <synthetic> def equals(x$1: Any): Boolean = x$1.isInstanceOf[org.squeryl.internals.Unupdatable].&&(x$1.asInstanceOf[org.squeryl.internals.Unupdatable].canEqual(Unupdatable.this))
  };
  <synthetic> object Unupdatable extends scala.runtime.AbstractFunction0[org.squeryl.internals.Unupdatable] with Serializable {
    def <init>(): org.squeryl.internals.Unupdatable.type = {
      Unupdatable.super.<init>();
      ()
    };
    final override def toString(): String = "Unupdatable";
    case <synthetic> def apply(): org.squeryl.internals.Unupdatable = new internals.this.Unupdatable();
    case <synthetic> def unapply(x$0: org.squeryl.internals.Unupdatable): Boolean = if (x$0.==(null))
      false
    else
      true;
    <synthetic> private def readResolve(): Object = internals.this.Unupdatable
  };
  case class Named extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.AttributeValidOnNumericalColumn with org.squeryl.internals.AttributeValidOnNonNumericalColumn with Product with Serializable {
    <caseaccessor> <paramaccessor> private[this] val name: String = _;
    <stable> <caseaccessor> <accessor> <paramaccessor> def name: String = Named.this.name;
    def <init>(name: String): org.squeryl.internals.Named = {
      Named.super.<init>();
      ()
    };
    <synthetic> def copy(name: String = name): org.squeryl.internals.Named = new internals.this.Named(name);
    <synthetic> def copy$default$1: String @scala.annotation.unchecked.uncheckedVariance = Named.this.name;
    override <synthetic> def productPrefix: String = "Named";
    <synthetic> def productArity: Int = 1;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case 0 => Named.this.name
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](Named.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.Named]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(Named.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(Named.this);
    override <synthetic> def equals(x$1: Any): Boolean = Named.this.eq(x$1.asInstanceOf[Object]).||(x$1.isInstanceOf[org.squeryl.internals.Named].&&({
      <synthetic> val Named$1: org.squeryl.internals.Named = x$1.asInstanceOf[org.squeryl.internals.Named];
      Named.this.name.==(Named$1.name).&&(Named$1.canEqual(Named.this))
    }))
  };
  <synthetic> object Named extends scala.runtime.AbstractFunction1[String,org.squeryl.internals.Named] with Serializable {
    def <init>(): org.squeryl.internals.Named.type = {
      Named.super.<init>();
      ()
    };
    final override def toString(): String = "Named";
    case <synthetic> def apply(name: String): org.squeryl.internals.Named = new internals.this.Named(name);
    case <synthetic> def unapply(x$0: org.squeryl.internals.Named): Option[String] = if (x$0.==(null))
      scala.this.None
    else
      Some.apply[String](x$0.name);
    <synthetic> private def readResolve(): Object = internals.this.Named
  };
  case class IsTransient extends Object with org.squeryl.internals.ColumnAttribute with org.squeryl.internals.AttributeValidOnNumericalColumn with org.squeryl.internals.AttributeValidOnNonNumericalColumn with Product with Serializable {
    def <init>(): org.squeryl.internals.IsTransient = {
      IsTransient.super.<init>();
      ()
    };
    <synthetic> def copy(): org.squeryl.internals.IsTransient = new internals.this.IsTransient();
    override <synthetic> def productPrefix: String = "IsTransient";
    <synthetic> def productArity: Int = 0;
    <synthetic> def productElement(x$1: Int): Any = x$1 match {
      case _ => throw new IndexOutOfBoundsException(x$1.toString())
    };
    override <synthetic> def productIterator: Iterator[Any] = runtime.this.ScalaRunTime.typedProductIterator[Any](IsTransient.this);
    <synthetic> def canEqual(x$1: Any): Boolean = x$1.$isInstanceOf[org.squeryl.internals.IsTransient]();
    override <synthetic> def hashCode(): Int = ScalaRunTime.this._hashCode(IsTransient.this);
    override <synthetic> def toString(): String = ScalaRunTime.this._toString(IsTransient.this);
    override <synthetic> def equals(x$1: Any): Boolean = x$1.isInstanceOf[org.squeryl.internals.IsTransient].&&(x$1.asInstanceOf[org.squeryl.internals.IsTransient].canEqual(IsTransient.this))
  };
  <synthetic> object IsTransient extends scala.runtime.AbstractFunction0[org.squeryl.internals.IsTransient] with Serializable {
    def <init>(): org.squeryl.internals.IsTransient.type = {
      IsTransient.super.<init>();
      ()
    };
    final override def toString(): String = "IsTransient";
    case <synthetic> def apply(): org.squeryl.internals.IsTransient = new internals.this.IsTransient();
    case <synthetic> def unapply(x$0: org.squeryl.internals.IsTransient): Boolean = if (x$0.==(null))
      false
    else
      true;
    <synthetic> private def readResolve(): Object = internals.this.IsTransient
  }
}