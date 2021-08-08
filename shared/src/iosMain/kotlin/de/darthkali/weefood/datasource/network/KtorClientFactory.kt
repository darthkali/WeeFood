package de.darthkali.weefood.datasource.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.ios.Ios
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

//TODO JavaDoc einfügen
actual class KtorClientFactory {
    actual fun build(): HttpClient {
        return HttpClient(Ios) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                        useAlternativeNames =
                            false // to avoid kotlin.native.concurrent.InvalidMutabilityException on iOs
                    }
                )
            }
        }
    }
}