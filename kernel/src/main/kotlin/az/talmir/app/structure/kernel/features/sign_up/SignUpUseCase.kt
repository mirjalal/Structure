package az.talmir.app.structure.kernel.features.sign_up

import az.talmir.app.structure.core.features.auth.AuthRepository
import az.talmir.app.structure.kernel.features.sign_up.mappers.SignUpRequestMapper
import az.talmir.app.structure.kernel.features.sign_up.mappers.SignUpResponseMapper
import az.talmir.app.structure.kernel.features.sign_up.models.SignUpRequest
import az.talmir.app.structure.kernel.features.sign_up.models.SignUpResponse
import az.talmir.app.structure.kernel.helpers.toPresentation
import az.talmir.app.structure.kernel.helpers.toRemote
import az.talmir.app.structure.shared.models.Result
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Single

@Single
@Scope(name = "auth_scope")
class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    suspend fun registerUserWith(credentials: SignUpRequest): Result<SignUpResponse> =
        authRepository
            .registerUserWith(credentials.toRemote(SignUpRequestMapper))
            .toPresentation(SignUpResponseMapper)
}
