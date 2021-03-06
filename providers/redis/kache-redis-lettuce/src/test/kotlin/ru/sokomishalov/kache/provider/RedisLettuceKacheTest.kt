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
package ru.sokomishalov.kache.provider

import org.junit.AfterClass
import org.junit.ClassRule
import ru.sokomishalov.kache.core.Kache
import ru.sokomishalov.kache.core.serialization.jackson.JacksonSerializer
import ru.sokomishalov.kache.tck.KacheTck

/**
 * @author sokomishalov
 */
class RedisLettuceKacheTest : KacheTck() {

    companion object {
        @get:ClassRule
        val redis: RedisTestContainer = createDefaultRedisContainer()

        @AfterClass
        @JvmStatic
        fun stop() = redis.stop()
    }

    override val kache: Kache by lazy {
        redis.start()
        RedisLettuceKache(serializer = JacksonSerializer(), client = redis.createRedisClient())
    }
}