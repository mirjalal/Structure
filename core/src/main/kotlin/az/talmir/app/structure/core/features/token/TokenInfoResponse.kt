package az.talmir.app.structure.core.features.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenInfoResponse(val accessToken: String, val refreshToken: String?)
