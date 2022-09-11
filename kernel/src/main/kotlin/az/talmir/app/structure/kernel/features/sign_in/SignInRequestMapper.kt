package az.talmir.app.structure.kernel.features.sign_in

import az.talmir.app.structure.core.features.auth.sign_in_models.SignInRequestBody
import az.talmir.app.structure.kernel.mappers.Presentation2RemoteMapper

internal object SignInRequestMapper : Presentation2RemoteMapper<SignInRequest, SignInRequestBody>() {
    override fun map(`in`: SignInRequest): SignInRequestBody =
        SignInRequestBody(
            username = `in`.username,
            password = `in`.password
        )
}
