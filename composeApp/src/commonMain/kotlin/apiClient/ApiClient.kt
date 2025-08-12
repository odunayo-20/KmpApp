package apiClient

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


//val httpClient = HttpClient {
//    install(ContentNegotiation){
//        json(json {
//            prettyPrint = true
//            ignoreUnkownKeys = true
//        })
//    }
//}


val httpClient = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(Json { // This 'json' comes from ktor-serialization-kotlinx-json
            prettyPrint = true
            isLenient = true // For more flexibility with incoming JSON
            ignoreUnknownKeys = true // Good for API evolution
        })
    }
}