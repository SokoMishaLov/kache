package ru.sokomishalov.kache.provider

import com.mongodb.ConnectionString
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.testcontainers.containers.GenericContainer

/**
 * @author sokomishalov
 */

class MongoTestContainer : GenericContainer<MongoTestContainer>("mvertes/alpine-mongo")

fun createDefaultMongoContainer(): MongoTestContainer {
    return MongoTestContainer().apply {
        withReuse(true)
        withExposedPorts(27017)
    }
}

fun MongoTestContainer.createReactiveMongoClient(): MongoClient {
    return MongoClients.create(ConnectionString("mongodb://${containerIpAddress}:${firstMappedPort}"))
}