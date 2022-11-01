package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.core.storage.prefs.token.TokenInfoLocalResponse
import az.talmir.app.structure.shared.mappers.Mapper

internal object TokenInfoMapper : Mapper<TokenInfoResponse, TokenInfoLocalResponse> {
    override fun map(`in`: TokenInfoResponse): TokenInfoLocalResponse =
        TokenInfoLocalResponse(
            jwt = `in`.accessToken,
            refreshToken = `in`.refreshToken
        )
}

internal object TokenInfoLocalResponseMapper : Mapper<TokenInfoLocalResponse, TokenInfoResponse> {
    override fun map(`in`: TokenInfoLocalResponse): TokenInfoResponse =
        TokenInfoResponse(
            accessToken = `in`.jwt,
            jwtExpireAt = `in`.jwtExpireAt,
            refreshToken = `in`.refreshToken,
            refreshTokenExpireAt = `in`.refreshTokenExpireAt
        )
}
