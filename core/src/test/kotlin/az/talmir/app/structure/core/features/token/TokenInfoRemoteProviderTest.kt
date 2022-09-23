package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.shared.models.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TokenInfoRemoteProviderTest {
    @Test
    fun `test getNewAccessToken response is Success`() = runTest {
        val input = TokenInfoRequestBody(refreshToken = "a_refresh_token")
        val given = TokenInfoResponse(accessToken = "dummy_jwt", refreshToken = "dummy_refresh_token")
        val expected = FakeTokenInfoRemoteProvider().getNewAccessToken(input) as Result.Success
        Assert.assertEquals(expected.data, given)
    }

    @Test
    fun `test getNewAccessToken response is not Success`() = runTest {
        val input = TokenInfoRequestBody(refreshToken = "a_refresh_token_1")
        val expected = FakeTokenInfoRemoteProvider(testSuiteCase = 4).getNewAccessToken(input) !is Result.Success
        Assert.assertTrue(expected)
    }
}
