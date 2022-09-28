package az.talmir.app.structure.core.storage.prefs.token

import az.talmir.app.structure.core.helpers.Local
import kotlinx.datetime.Clock.System
import kotlinx.serialization.Serializable

private const val JWT_EXPIRE_MILLIS = 15 * 60 * 1_000L
private const val REFRESH_TOKEN_EXPIRE_MILLIS = 86_400L * 1_000L

@Serializable
data class TokenInfo(
    val jwt: String,
    val jwtExpireAt: Long = System.now().toEpochMilliseconds() + JWT_EXPIRE_MILLIS,
    val refreshToken: String? = null,
    val refreshTokenExpireAt: Long = jwtExpireAt - JWT_EXPIRE_MILLIS + REFRESH_TOKEN_EXPIRE_MILLIS
) : Local() {
    fun canClaimNewJwtAt(millis: Long): Boolean =
        refreshToken != null && refreshTokenExpireAt > millis
}
