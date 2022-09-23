package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.shared.models.Result

class FakeTokenInfoRemoteProvider(
    private val testSuiteCase: Int = 0
) : ITokenInfoRemoteProvider {
    override suspend fun getNewAccessToken(refreshRequestBody: TokenInfoRequestBody): Result<TokenInfoResponse> =
        when(testSuiteCase) {
            4 -> Result.Error(Throwable())
            else -> Result.Success(
                TokenInfoResponse(
                    accessToken = "dummy_jwt",
                    refreshToken = "dummy_refresh_token"
                )
            )
        }
}
