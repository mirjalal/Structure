package az.talmir.app.structure.core.koin

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import az.talmir.app.structure.core.BuildConfig
import az.talmir.app.structure.core.bridges.ApiBridge
import az.talmir.app.structure.core.bridges.TokenizedApiBridge
import az.talmir.app.structure.core.features.token.ITokenInfoRemoteProvider
import az.talmir.app.structure.core.features.token.TokenInfoRemoteProvider
import az.talmir.app.structure.core.features.token.TokenInfoRepository
import az.talmir.app.structure.core.storage.prefs.language.LangReaderService
import az.talmir.app.structure.core.storage.prefs.language.LangStorage
import az.talmir.app.structure.core.storage.prefs.language.LangWriterService
import az.talmir.app.structure.core.storage.prefs.token.TokenInfoLocalService
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
import org.koin.core.module.dsl.scopedOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val koinCoreModule  = module {
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
    }

    single {
        EncryptedSharedPreferences.create(
            "sh_p_data",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            get(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    singleOf(::ApiBridge)
    singleOf(::LangReaderService) bind LangStorage::class
    singleOf(::LangWriterService) bind LangStorage::class

    singleOf(::TokenInfoReaderService) bind TokenInfoLocalService::class
    singleOf(::TokenInfoWriterService) bind TokenInfoLocalService::class
    singleOf(::TokenInfoRemoteProvider) bind ITokenInfoRemoteProvider::class

    single {
        TokenInfoRepository(
            TokenInfoReaderService(),
            TokenInfoWriterService(),
            TokenInfoRemoteProvider(get(), get())
        )
    }

    scope(qualifier = named("main_scope_qualifier")) {
        scopedOf(::TokenizedApiBridge)

    }
}
