package az.talmir.app.structure.kernel.features.sign_up.mappers

import az.talmir.app.structure.core.features.auth.sign_up_models.SignUpRemoteResponse
import az.talmir.app.structure.kernel.features.sign_up.models.SignUpResponse
import az.talmir.app.structure.kernel.mappers.Remote2PresentationMapper

internal object SignUpResponseMapper : Remote2PresentationMapper<SignUpRemoteResponse, SignUpResponse>() {
    override fun map(`in`: SignUpRemoteResponse): SignUpResponse =
        SignUpResponse()
}
