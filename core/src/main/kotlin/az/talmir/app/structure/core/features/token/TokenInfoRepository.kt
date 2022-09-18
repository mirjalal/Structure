package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.core.storage.prefs.token.TokenInfo
import az.talmir.app.structure.core.storage.prefs.token.TokenInfoReaderService
import az.talmir.app.structure.core.storage.prefs.token.TokenInfoWriterService
import az.talmir.app.structure.shared.models.Result
import kotlinx.datetime.Clock.System

class TokenInfoRepository(
    private val tokenInfoLocalReaderService: TokenInfoReaderService,
    private val tokenInfoLocalWriterService: TokenInfoWriterService,
    private val tokenInfoRemoteProvider: TokenInfoRemoteProvider
) {
    private lateinit var tokenInfo: TokenInfo

    suspend fun getJwt(): TokenInfo? {
        if (!this::tokenInfo.isInitialized)
            tokenInfoLocalReaderService.getTokenInfo()?.also { tokenInfo = it }

        return execJwtLogic()
    }

    private suspend fun execJwtLogic(): TokenInfo? =
        if (tokenInfo.jwtExpireAt < System.now().epochSeconds) {
            tokenInfo
        } else {
            if (tokenInfo.refreshToken != null) {
                if (tokenInfo.refreshTokenExpireAt < System.now().epochSeconds) {
                    // todo:
                    //  1. get new jwt (done)
                    //  2. save to shared prefs (done)
                    //  3. set it to [tokenInfo] (done)
                    //  4. return [tokenInfo] or null (done)
                    val res = tokenInfoRemoteProvider.getNewAccessToken(TokenInfoRequestBody(refreshToken = tokenInfo.refreshToken!!))
                    if (res is Result.Success)
                        TokenInfoMapper.map(res.data).also { ti ->
                            tokenInfoLocalWriterService.setToken(ti)
                            tokenInfo = ti
                       }
                }
            }

            null
        }
}
