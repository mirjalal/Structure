package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.shared.models.Result

interface ITokenInfoRemoteProvider {
    suspend fun getNewAccessToken(refreshRequestBody: TokenInfoRequestBody): Result<TokenInfoResponse>
}
