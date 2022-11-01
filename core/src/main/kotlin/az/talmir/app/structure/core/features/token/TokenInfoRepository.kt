package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.core.storage.prefs.token.TokenInfoLocalResponse
import az.talmir.app.structure.core.storage.prefs.token.TokenInfoLocalService
import az.talmir.app.structure.shared.models.Result
import kotlinx.datetime.Clock.System
import org.koin.core.annotation.Single

@Single
class TokenInfoRepository(
    private val tokenInfoLocalReaderService: TokenInfoLocalService,
    private val tokenInfoLocalWriterService: TokenInfoLocalService,
    private val tokenInfoRemoteProvider: ITokenInfoRemoteProvider
) {
    private lateinit var mTokenInfo: TokenInfoLocalResponse

    suspend fun getTokenInfo(): TokenInfoResponse? {
        if (!this::mTokenInfo.isInitialized)
            tokenInfoLocalReaderService.getTokenInfo()?.also {
                mTokenInfo = it
            }

        return execJwtLogic()
    }

    private suspend fun execJwtLogic(): TokenInfoResponse? {
        val epochMilliseconds = System.now().toEpochMilliseconds()
        if (mTokenInfo.jwtExpireAt > epochMilliseconds)
            return TokenInfoLocalResponseMapper.map(mTokenInfo)
        else {
            if (mTokenInfo.canClaimNewJwtAt(epochMilliseconds)) {
                val res = tokenInfoRemoteProvider.getNewAccessToken(
                    TokenInfoRequestBody(
                        refreshToken = mTokenInfo.refreshToken!!
                    )
                )
                if (res is Result.Success) {
                    TokenInfoMapper.map(res.data).also {
                        tokenInfoLocalWriterService.setToken(it)
                        mTokenInfo = it
                    }
                    return res.data
                }
            }

            return null
        }
    }
}
