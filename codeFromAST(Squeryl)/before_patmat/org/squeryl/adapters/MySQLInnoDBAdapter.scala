package org.squeryl.adapters {
  class MySQLInnoDBAdapter extends MySQLAdapter {
    def <init>(): org.squeryl.adapters.MySQLInnoDBAdapter = {
      MySQLInnoDBAdapter.super.<init>();
      ()
    };
    override def supportsForeignKeyConstraints: Boolean = true
  }
}