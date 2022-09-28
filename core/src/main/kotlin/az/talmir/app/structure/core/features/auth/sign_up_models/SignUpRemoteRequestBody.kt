package az.talmir.app.structure.core.features.auth.sign_up_models

import az.talmir.app.structure.core.helpers.Remote

@kotlinx.serialization.Serializable
data class SignUpRemoteRequestBody(
    val username: String,
    val password: String,
    val name: String,
    val surname: String
) : Remote()