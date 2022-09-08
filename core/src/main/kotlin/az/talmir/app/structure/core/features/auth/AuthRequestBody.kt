package az.talmir.app.structure.core.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestBody(val username: String, val password: String)
