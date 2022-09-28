package az.talmir.app.structure.core

import az.talmir.app.structure.core.features.auth.sign_in_models.SignInRemoteRequestBody
import az.talmir.app.structure.core.koin.KoinCoreModule
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.inject

class KoinUnitTest : KoinTest {
    private val json by inject<Json>()
    private val httpClient by inject<HttpClient>()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(KoinCoreModule().module)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test Json initialized`() {
        val strJson = """{"username":"uname","password":"pwd"}"""
        val obj = json.decodeFromString<SignInRemoteRequestBody>(strJson)
        assertEquals(SignInRemoteRequestBody(username = "uname", password = "pwd"), obj)
        assertEquals("true", json.encodeToString(true))
    }

    @Test
    fun `test HttpClient is OK`() {
        runBlocking {
            assertNotNull(httpClient.get("https://jsonplaceholder.typicode.com/todos/1").body())
        }
    }
}
