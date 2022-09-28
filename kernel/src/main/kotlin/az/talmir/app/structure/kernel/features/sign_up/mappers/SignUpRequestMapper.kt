package az.talmir.app.structure.kernel.features.sign_up.mappers

import az.talmir.app.structure.core.features.auth.sign_up_models.SignUpRemoteRequestBody
import az.talmir.app.structure.kernel.features.sign_up.models.SignUpRequest
import az.talmir.app.structure.kernel.mappers.Presentation2RemoteMapper

internal object SignUpRequestMapper : Presentation2RemoteMapper<SignUpRequest, SignUpRemoteRequestBody>() {
    override fun map(`in`: SignUpRequest): SignUpRemoteRequestBody =
        SignUpRemoteRequestBody(
            username = `in`.username,
            password = `in`.password,
            name = `in`.name,
            surname = `in`.surname
        )
}
