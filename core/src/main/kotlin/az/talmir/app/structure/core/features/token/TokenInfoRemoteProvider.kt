package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.core.bridges.ApiBridge
import az.talmir.app.structure.shared.models.Result
import io.ktor.client.HttpClient

class TokenInfoRemoteProvider(
    private val httpClient: HttpClient,
    private val apiBridge: ApiBridge
) : ITokenInfoRemoteProvider {
    override suspend fun getNewAccessToken(refreshRequestBody: TokenInfoRequestBody): Result<TokenInfoResponse> =
        apiBridge.buildApiCall {
            httpClient.getNewAccessToken(refreshRequestBody, it)
        }
}
