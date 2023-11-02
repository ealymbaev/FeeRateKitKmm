package io.horizontalsystems.feeratekitkmm

import com.ionspin.kotlin.bignum.serialization.kotlinx.humanReadableSerializerModule
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MarketApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
                serializersModule = humanReadableSerializerModule
            })
        }
    }

    suspend fun getTopCoins(): List<Coin> {
        return httpClient.get("https://api-dev.blocksdecoded.com/v1/coins?limit=5&order_by_rank=true&fields=uid,name,code,price").body()
    }
}
