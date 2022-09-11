package az.talmir.app.structure.core.features.auth.sign_in_models

import az.talmir.app.structure.core.helpers.Remote
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestBody(val username: String, val password: String) : Remote()
