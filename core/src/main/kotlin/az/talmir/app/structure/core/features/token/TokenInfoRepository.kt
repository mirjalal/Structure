package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.core.storage.prefs.token.TokenInfo
import az.talmir.app.structure.core.storage.prefs.token.TokenInfoLocalService
import az.talmir.app.structure.shared.models.Result
import kotlinx.datetime.Clock.System

class TokenInfoRepository(
    private val tokenInfoLocalReaderService: TokenInfoLocalService,
    private val tokenInfoLocalWriterService: TokenInfoLocalService,
    private val tokenInfoRemoteProvider: ITokenInfoRemoteProvider
) {
    private lateinit var mTokenInfo: TokenInfo

    suspend fun getJwt(): TokenInfo? {
        if (!this::mTokenInfo.isInitialized)
            tokenInfoLocalReaderService.getTokenInfo()?.also {
                mTokenInfo = it
            }

        return execJwtLogic()
    }

    private suspend fun execJwtLogic(): TokenInfo? {
        val epochMilliseconds = System.now().toEpochMilliseconds()
        if (mTokenInfo.jwtExpireAt > epochMilliseconds)
            return mTokenInfo
        else {
            if (mTokenInfo.refreshToken != null) {
                if (mTokenInfo.refreshTokenExpireAt > epochMilliseconds) {
                    val res = tokenInfoRemoteProvider.getNewAccessToken(
                        TokenInfoRequestBody(
                            refreshToken = mTokenInfo.refreshToken!!
                        )
                    )
                    if (res is Result.Success) {
                        return TokenInfoMapper.map(res.data).also {
                            tokenInfoLocalWriterService.setToken(it)
                            mTokenInfo = it
                        }
                    }
                }
            }

            return null
        }
    }
}
