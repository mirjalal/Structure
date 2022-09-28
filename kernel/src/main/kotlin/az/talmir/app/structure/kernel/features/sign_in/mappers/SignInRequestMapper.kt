package az.talmir.app.structure.kernel.features.sign_in.mappers

import az.talmir.app.structure.core.features.auth.sign_in_models.SignInRemoteRequestBody
import az.talmir.app.structure.kernel.features.sign_in.models.SignInRequest
import az.talmir.app.structure.kernel.mappers.Presentation2RemoteMapper

internal object SignInRequestMapper : Presentation2RemoteMapper<SignInRequest, SignInRemoteRequestBody>() {
    override fun map(`in`: SignInRequest): SignInRemoteRequestBody =
        SignInRemoteRequestBody(
            username = `in`.username,
            password = `in`.password
        )
}
