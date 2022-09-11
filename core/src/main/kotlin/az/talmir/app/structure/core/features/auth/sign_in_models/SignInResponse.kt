@file:OptIn(
    ExperimentalSerializationApi::class
)

package az.talmir.app.structure.core.features.auth.sign_in_models

import az.talmir.app.structure.core.helpers.Remote
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val access_token: String,
    @EncodeDefault(EncodeDefault.Mode.NEVER)
    val refresh_token: String? = null
) : Remote()
