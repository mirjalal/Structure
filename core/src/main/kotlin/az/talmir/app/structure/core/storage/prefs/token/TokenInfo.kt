package az.talmir.app.structure.core.storage.prefs.token

import az.talmir.app.structure.core.helpers.Local
import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(val jwt: String, val refreshToken: String? = null) : Local()
