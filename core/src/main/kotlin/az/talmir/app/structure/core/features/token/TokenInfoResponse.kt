package az.talmir.app.structure.core.features.token

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable

@Serializable
data class TokenInfoResponse(
    val accessToken: String,
    val jwtExpireAt: Long,
    val refreshToken: String?,
    val refreshTokenExpireAt: Long,
) {
    fun isValid(): Boolean {
        val now = Clock.System.now().toEpochMilliseconds()
        return if (refreshToken == null)
            jwtExpireAt >= now
        else
            now in (jwtExpireAt + 1) until refreshTokenExpireAt
    }
}
