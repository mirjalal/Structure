package az.talmir.app.structure.core.koin

import az.talmir.app.structure.core.BuildConfig
import az.talmir.app.structure.core.features.token.TokenInfoRemoteProvider
import az.talmir.app.structure.core.features.token.TokenInfoRepository
import az.talmir.app.structure.core.storage.prefs.token.TokenInfoReaderService
import az.talmir.app.structure.core.storage.prefs.token.TokenInfoWriterService
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
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("az.talmir.app.structure.core")
class KoinCoreModule {
    @Single
    fun provideJson(): Json =
        Json(DefaultJson) {
            encodeDefaults = false
            ignoreUnknownKeys = true
        }

    @Single
    fun provideHttpClient(json: Json): HttpClient =
        HttpClient(OkHttp) {
            developmentMode = BuildConfig.DEBUG

            install(ContentNegotiation) {
                json(json)
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
                    callTimeout(1_000, TimeUnit.MILLISECONDS)
                    connectTimeout(5_000, TimeUnit.MILLISECONDS)
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

    @Single
    fun provideTokenInfoRepository(
        tokenInfoLocalReaderService: TokenInfoReaderService,
        tokenInfoLocalWriterService: TokenInfoWriterService,
        tokenInfoRemoteProvider: TokenInfoRemoteProvider
    ): TokenInfoRepository =
        TokenInfoRepository(
            tokenInfoLocalReaderService = tokenInfoLocalReaderService,
            tokenInfoLocalWriterService = tokenInfoLocalWriterService,
            tokenInfoRemoteProvider = tokenInfoRemoteProvider
        )
}
