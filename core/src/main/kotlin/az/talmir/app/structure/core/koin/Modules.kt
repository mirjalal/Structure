package az.talmir.app.structure.core.koin

import az.talmir.app.structure.core.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.userAgent
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import java.util.concurrent.TimeUnit
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val commonModule = module {
    single {
        Json(DefaultJson) {
            encodeDefaults = false
            ignoreUnknownKeys = true
        }
    }

    single {
        HttpClient(OkHttp) {
            developmentMode = BuildConfig.DEBUG

            install(ContentNegotiation) {
                json(get())
            }

            defaultRequest {
                url("https://example.com/")
                userAgent("Mobile-App-User-Agent")
                accept(ContentType.Application.Json)
                contentType(ContentType.Application.Json)
            }

            engine {
                // this: https://api.ktor.io/ktor-client/ktor-client-okhttp/ktor-client-okhttp/io.ktor.client.engine.okhttp/-ok-http-config/index.html
                config {
                    callTimeout(15_000, TimeUnit.MILLISECONDS)
                    connectTimeout(50_000, TimeUnit.MILLISECONDS)
                    //followRedirects(true)
                }

                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY
                            else
                                HttpLoggingInterceptor.Level.NONE
                    }
                )
            }
        }
    }
}