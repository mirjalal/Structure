package az.talmir.app.structure.core.storage.prefs.token

import kotlinx.datetime.Clock.System.now

class FakeTokenInfoLocalService(
    private var testSuiteCase: Int = 0
) : TokenInfoLocalService() {
    // we initialize prop here, because we believe that user previously logged in to use token data.
    private var mTokenInfo: TokenInfo = TokenInfo(jwt = "jwt_string", refreshTokenExpireAt = 0L)

    override suspend fun getTokenInfo(): TokenInfo =
        when (testSuiteCase) {
            1 -> mTokenInfo.copy(jwtExpireAt = now().toEpochMilliseconds() - 5_000L)
            2 -> mTokenInfo.copy(
                jwtExpireAt = now().toEpochMilliseconds() - 1_000L,
                refreshToken = "refresh_token_string",
                refreshTokenExpireAt = now().toEpochMilliseconds() + 5_000L
            )
            3, 4 -> {
                mTokenInfo.copy(
                    jwtExpireAt = now().toEpochMilliseconds() - 1_000L,
                    refreshToken = "refresh_token_string",
                    refreshTokenExpireAt = now().toEpochMilliseconds() - 5_000L
                )
            }
            else -> mTokenInfo
        }

    override suspend fun setToken(data: TokenInfo) {
        mTokenInfo = data
    }
}
