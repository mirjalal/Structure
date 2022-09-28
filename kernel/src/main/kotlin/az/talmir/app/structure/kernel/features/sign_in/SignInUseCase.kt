package az.talmir.app.structure.kernel.features.sign_in

import az.talmir.app.structure.core.features.auth.AuthRepository
import az.talmir.app.structure.kernel.features.sign_in.mappers.SignInRequestMapper
import az.talmir.app.structure.kernel.features.sign_in.mappers.SignInResponseMapper
import az.talmir.app.structure.kernel.features.sign_in.models.SignInRequest
import az.talmir.app.structure.kernel.features.sign_in.models.SignInResponse
import az.talmir.app.structure.kernel.helpers.toPresentation
import az.talmir.app.structure.kernel.helpers.toRemote
import az.talmir.app.structure.shared.models.Result
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Single

@Single
@Scope(name = "auth_scope")
class SignInUseCase(
    private val authRepository: AuthRepository
) {
    suspend fun authenticateUserWith(credentials: SignInRequest): Result<SignInResponse> =
        authRepository
            .signInWith(credentials.toRemote(SignInRequestMapper))
            .toPresentation(SignInResponseMapper)
}
