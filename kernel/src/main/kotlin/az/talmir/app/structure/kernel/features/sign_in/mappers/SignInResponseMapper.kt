package az.talmir.app.structure.kernel.features.sign_in.mappers

import az.talmir.app.structure.core.features.auth.sign_in_models.SignInRemoteResponse
import az.talmir.app.structure.kernel.mappers.Remote2PresentationMapper
import az.talmir.app.structure.kernel.features.sign_in.models.SignInResponse

internal object SignInResponseMapper : Remote2PresentationMapper<SignInRemoteResponse, SignInResponse>() {
    override fun map(`in`: SignInRemoteResponse): SignInResponse =
        SignInResponse(
            jwt = `in`.access_token,
            refreshToken = `in`.refresh_token
        )
}
