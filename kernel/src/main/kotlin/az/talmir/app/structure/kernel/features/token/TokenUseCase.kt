package az.talmir.app.structure.kernel.features.token

import az.talmir.app.structure.core.features.token.TokenInfoRepository
import az.talmir.app.structure.core.features.token.TokenInfoResponse
import org.koin.core.annotation.Factory

@Factory
class TokenUseCase(
    private val tokenInfoRepository: TokenInfoRepository
) {
    suspend fun getTokenInfo(): TokenInfoResponse? =
        tokenInfoRepository.getTokenInfo()

    suspend fun isTokenValid(): Boolean = getTokenInfo()?.isValid() ?: false
}
