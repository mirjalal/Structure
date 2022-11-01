package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.shared.models.Result
import kotlinx.datetime.Clock

class FakeTokenInfoRemoteProvider(
    private val testSuiteCase: Int = 0
) : ITokenInfoRemoteProvider {
    override suspend fun getNewAccessToken(refreshRequestBody: TokenInfoRequestBody): Result<TokenInfoResponse> =
        when(testSuiteCase) {
            4 -> Result.Error(Throwable())
            else -> Result.Success(
                TokenInfoResponse(
                    accessToken = "dummy_jwt",
                    jwtExpireAt = Clock.System.now().toEpochMilliseconds(),
                    refreshToken = "dummy_refresh_token",
                    refreshTokenExpireAt = Clock.System.now().toEpochMilliseconds()
                )
            )
        }
}
