package az.talmir.app.structure.core

import az.talmir.app.structure.core.features.auth.sign_in_models.SignInResponse
import az.talmir.app.structure.core.koin.commonModule
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class JsonUnitTest : KoinTest {
    private val json by inject<Json>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(commonModule)
    }

    @Test
    fun `test string parsing to AuthRemoteResponse without refresh_token returns true`() {
        val strJson = """{"access_token":"blahblah"}"""
        val obj = json.decodeFromString<SignInResponse>(strJson)
        assertEquals(SignInResponse(access_token = "blahblah"), obj)
    }

    @Test
    fun `test string parsing to AuthRemoteResponse with refresh_token returns true`() {
        val strJson = """{"access_token":"blahblah", "refresh_token":"qweqwe"}"""
        val obj = json.decodeFromString<SignInResponse>(strJson)
        assertEquals(SignInResponse(access_token = "blahblah", refresh_token = "qweqwe"), obj)
    }

    @Test
    fun `test AuthRemoteResponse parsing with nullable prop value returns true`() {
        val obj = SignInResponse(access_token = "blah_blah", refresh_token = null)
        val strJson = json.encodeToString(obj)
        assertEquals("""{"access_token":"blah_blah"}""", strJson)
    }

    @Test
    fun `test AuthRemoteResponse parsing without nullable prop value returns true`() {
        val obj = SignInResponse(access_token = "blah_blah", refresh_token = "qwe_qwe")
        val strJson = json.encodeToString(obj)
        assertEquals("""{"access_token":"blah_blah","refresh_token":"qwe_qwe"}""", strJson)
    }

    @Test
    fun `test parsing unknown keys returns true`() {
        val strJson = """{"access_token":"blahblah", "some_new_key":123}"""
        val obj = json.decodeFromString<SignInResponse>(strJson)
        assertEquals(SignInResponse(access_token = "blahblah", refresh_token = null), obj)
    }
}
