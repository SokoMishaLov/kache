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
package ru.sokomishalov.kache.core.serialization.kryo

import ru.sokomishalov.kache.core.Serializer
import ru.sokomishalov.kache.core.serialization.SerializerTck
import ru.sokomishalov.kache.core.serialization.kryo.KryoSerializer.Companion.DEFAULT_KRYO

class KryoSerializerTest : SerializerTck() {
    override val serializer: Serializer = KryoSerializer(kryo = DEFAULT_KRYO.apply {
        register(DummyModel::class.java)
    })
}