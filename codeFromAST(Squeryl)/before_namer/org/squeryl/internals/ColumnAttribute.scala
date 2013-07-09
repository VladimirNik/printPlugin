package org.squeryl.internals {
  abstract trait ColumnAttribute extends scala.AnyRef;
  abstract trait MultipleColumnAttribute extends scala.AnyRef;
  abstract trait AttributeValidOnMultipleColumn extends ColumnAttribute;
  abstract trait AttributeValidOnNumericalColumn extends ColumnAttribute;
  abstract trait AttributeValidOnNonNumericalColumn extends ColumnAttribute;
  case class Unique extends ColumnAttribute with MultipleColumnAttribute with AttributeValidOnNonNumericalColumn with AttributeValidOnNumericalColumn with AttributeValidOnMultipleColumn with scala.Product with scala.Serializable {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  case class AutoIncremented extends ColumnAttribute with AttributeValidOnNumericalColumn with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> var nameOfSequence: Option[String] = _;
    def <init>(nameOfSequence: Option[String]) = {
      super.<init>();
      ()
    };
    override def hashCode = this.getClass.hashCode;
    override def equals(any: Any) = any.isInstanceOf[AutoIncremented]
  };
  case class Indexed extends ColumnAttribute with MultipleColumnAttribute with AttributeValidOnNonNumericalColumn with AttributeValidOnNumericalColumn with AttributeValidOnMultipleColumn with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val nameOfIndex: Option[String] = _;
    def <init>(nameOfIndex: Option[String]) = {
      super.<init>();
      ()
    }
  };
  case class PrimaryKey extends ColumnAttribute with AttributeValidOnNonNumericalColumn with AttributeValidOnNumericalColumn with AttributeValidOnMultipleColumn with scala.Product with scala.Serializable {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  case class DBType extends ColumnAttribute with AttributeValidOnNonNumericalColumn with AttributeValidOnNumericalColumn with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val declaration: String = _;
    def <init>(declaration: String) = {
      super.<init>();
      ()
    }
  };
  case class Uninsertable extends ColumnAttribute with AttributeValidOnNumericalColumn with AttributeValidOnNonNumericalColumn with scala.Product with scala.Serializable {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  case class Unupdatable extends ColumnAttribute with AttributeValidOnNumericalColumn with AttributeValidOnNonNumericalColumn with scala.Product with scala.Serializable {
    def <init>() = {
      super.<init>();
      ()
    }
  };
  case class Named extends ColumnAttribute with AttributeValidOnNumericalColumn with AttributeValidOnNonNumericalColumn with scala.Product with scala.Serializable {
    <caseaccessor> <paramaccessor> val name: String = _;
    def <init>(name: String) = {
      super.<init>();
      ()
    }
  };
  case class IsTransient extends ColumnAttribute with AttributeValidOnNumericalColumn with AttributeValidOnNonNumericalColumn with scala.Product with scala.Serializable {
    def <init>() = {
      super.<init>();
      ()
    }
  }
}