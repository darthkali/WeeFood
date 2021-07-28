package de.darthkali.weefood.datasource.network

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

actual class KtorClientFactory {
    actual fun build(): HttpClient {
        return HttpClient(Ios) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                        useAlternativeNames = false // to avoid kotlin.native.concurrent.InvalidMutabilityException: mutation attempt of frozen kotlin.collections.HashMap@81b47788
                    }
                )
            }
        }
    }
}