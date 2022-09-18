package az.talmir.app.structure.core.features.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenInfoRequestBody(val refreshToken: String)
