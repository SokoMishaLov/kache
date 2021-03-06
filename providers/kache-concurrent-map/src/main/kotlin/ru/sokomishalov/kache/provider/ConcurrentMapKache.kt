/**
 * Copyright (c) 2019-present Mikhael Sokolov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("UNCHECKED_CAST")

package ru.sokomishalov.kache.provider

import ru.sokomishalov.kache.core.Kache
import ru.sokomishalov.kache.core.model.GlobString
import ru.sokomishalov.kache.core.model.globToRegex
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * @author sokomishalov
 */
class ConcurrentMapKache(
        private val map: ConcurrentMap<String, Any> = ConcurrentHashMap()
) : Kache {

    override suspend fun getRaw(key: String): ByteArray? {
        return map[key] as ByteArray?
    }

    override suspend fun putRaw(key: String, value: ByteArray) {
        map[key] = value
    }

    override suspend fun delete(key: String) {
        map.remove(key)
    }

    override suspend fun findKeys(glob: GlobString): List<String> {
        return map
                .keys
                .map { it }
                .filter { glob.globToRegex().matches(it) }
    }

    override suspend fun <T> getOne(key: String, clazz: Class<T>): T? {
        return map[key] as T?
    }

    override suspend fun <T> getList(key: String, elementClass: Class<T>): List<T> {
        return map[key] as List<T>? ?: emptyList()
    }

    override suspend fun <T> getMap(key: String, valueClass: Class<T>): Map<String, T> {
        return map[key] as Map<String, T>? ?: emptyMap()
    }

    override suspend fun <T> put(key: String, value: T) {
        map[key] = value
    }

    override suspend fun exists(key: String): Boolean {
        return key in map.keys
    }

    override suspend fun deleteAll() {
        map.clear()
    }
}