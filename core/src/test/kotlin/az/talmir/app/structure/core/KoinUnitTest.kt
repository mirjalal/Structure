package az.talmir.app.structure.core

import az.talmir.app.structure.core.features.auth.AuthRequestBody
import az.talmir.app.structure.core.koin.commonModule
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.test.assertNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class KoinUnitTest : KoinTest {
    private val json by inject<Json>()
    private val httpClient by inject<HttpClient>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(commonModule)
    }

    @Test
    fun `test Json initialized`() {
        val strJson = """{"username":"uname","password":"pwd"}"""
        val obj = json.decodeFromString<AuthRequestBody>(strJson)
        assertEquals(AuthRequestBody(username = "uname", password = "pwd"), obj)
        assertEquals("true", json.encodeToString(true))
    }

    @Test
    fun `test HttpClient is OK`() {
        runBlocking {
            assertNotNull(httpClient.get("https://api.ipify.org/?format=json").body())
        }
    }
}
