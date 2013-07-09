package org.squeryl.adapters {
  class MySQLInnoDBAdapter extends MySQLAdapter {
    def <init>() = {
      super.<init>();
      ()
    };
    override def supportsForeignKeyConstraints = true
  }
}