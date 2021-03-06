/*
 * Copyright (c) 2002-2020 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.cypher.internal.util

object FreshIdNameGenerator extends PrefixNameGenerator("FRESHID")

object AggregationNameGenerator extends PrefixNameGenerator("AGGREGATION")

object NodeNameGenerator extends PrefixNameGenerator("NODE")

object RelNameGenerator extends PrefixNameGenerator("REL")

object UnNamedNameGenerator extends PrefixNameGenerator("UNNAMED") {
  implicit class NameString(name: String) {
    def isNamed: Boolean = UnNamedNameGenerator.isNamed(name)
    def unnamed: Boolean = UnNamedNameGenerator.notNamed(name)
  }
}

object PrefixNameGenerator {
  def namePrefix(prefix: String) = s"  $prefix"
}

case class PrefixNameGenerator(generatorName: String) {
  val prefix = s"  $generatorName"

  def name(position: InputPosition): String = s"$prefix${position.toOffsetString}"

  def isNamed(x: String): Boolean = !notNamed(x)
  def notNamed(x: String): Boolean = x.startsWith(prefix)
}
