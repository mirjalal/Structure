package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.core.storage.prefs.token.FakeTokenInfoLocalService
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class TokenInfoRepositoryTest {

    private lateinit var tokenInfoLocalService: FakeTokenInfoLocalService
    private lateinit var tokenInfoRemoteProvider: FakeTokenInfoRemoteProvider
    private lateinit var tokenInfoRepository: TokenInfoRepository

    private fun setup(testCase: Int) {
        tokenInfoRemoteProvider = FakeTokenInfoRemoteProvider(testSuiteCase = testCase)
        tokenInfoLocalService = FakeTokenInfoLocalService(testSuiteCase = testCase)
        tokenInfoRepository = TokenInfoRepository(
            tokenInfoLocalReaderService = tokenInfoLocalService,
            tokenInfoLocalWriterService = tokenInfoLocalService,
            tokenInfoRemoteProvider = tokenInfoRemoteProvider
        )
    }

    @Test
    fun `test#1 jwt is valid & refresh token is not applicable returns not null`() = runTest {
        setup(0)
        val jwt = tokenInfoRepository.getJwt()
        assertNotNull(jwt)
    }

    @Test
    fun `test#2 jwt is invalid & refresh token is not applicable returns null`() = runTest {
        setup(1)
        val jwt = tokenInfoRepository.getJwt()
        assertNull(jwt)
    }

    @Test
    fun `test#3 jwt is invalid, but refresh token is valid returns not null`() = runTest {
        setup(2)
        val jwt = tokenInfoRepository.getJwt()
        assertNotNull(jwt)
    }

    @Test
    fun `test#4 jwt & refresh token are invalid returns null`() = runTest {
        setup(3)
        val jwt = tokenInfoRepository.getJwt()
        assertNull(jwt)
    }

    @Test
    fun `test#5 jwt, refresh token & remote token fetch are invalid returns null`() = runTest {
        setup(4)
        val jwt = tokenInfoRepository.getJwt()
        assertNull(jwt)
    }
}
