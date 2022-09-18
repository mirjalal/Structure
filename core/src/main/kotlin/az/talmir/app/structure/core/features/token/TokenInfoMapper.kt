package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.core.storage.prefs.token.TokenInfo
import az.talmir.app.structure.shared.mappers.Mapper

internal object TokenInfoMapper : Mapper<TokenInfoResponse, TokenInfo> {
    override fun map(`in`: TokenInfoResponse): TokenInfo =
        TokenInfo(
            jwt = `in`.accessToken,
            refreshToken = `in`.refreshToken
        )
}
