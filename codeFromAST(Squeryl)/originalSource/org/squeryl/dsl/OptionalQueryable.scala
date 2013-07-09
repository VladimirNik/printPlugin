/*******************************************************************************
 * Copyright 2010 Maxime Lévesque
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************** */
package org.squeryl.dsl

import org.squeryl.Queryable
import org.squeryl.internals.ResultSetMapper
import java.sql.ResultSet


class OptionalQueryable[A](val queryable: Queryable[A]) extends Queryable[Option[A]] {


  def name = queryable.name

  def inhibitWhen(b: Boolean) = {
    inhibited = b
    this
  }

  private[squeryl] def give(resultSetMapper: ResultSetMapper, rs: ResultSet)  =
    if(inhibited)
      None
    else
      Some(queryable.give(resultSetMapper, rs))
}
