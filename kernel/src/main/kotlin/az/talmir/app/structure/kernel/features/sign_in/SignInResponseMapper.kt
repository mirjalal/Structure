package az.talmir.app.structure.kernel.features.sign_in

import az.talmir.app.structure.core.features.auth.sign_in_models.SignInResponse
import az.talmir.app.structure.kernel.mappers.Remote2PresentationMapper
import az.talmir.app.structure.kernel.features.sign_in.SignInResponse as SignInResponse1

internal object SignInResponseMapper : Remote2PresentationMapper<SignInResponse, SignInResponse1>() {
    override fun map(`in`: SignInResponse): SignInResponse1 =
        SignInResponse1(
            jwt = `in`.access_token,
            refreshToken = `in`.refresh_token
        )
}
