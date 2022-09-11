package az.talmir.app.structure.kernel.features.sign_in

import az.talmir.app.structure.core.features.auth.AuthRepository
import az.talmir.app.structure.kernel.helpers.toPresentation
import az.talmir.app.structure.kernel.helpers.toRemote
import az.talmir.app.structure.shared.models.Result

class SignInUseCase(
    private val authRepository: AuthRepository
) {
    suspend fun authenticateUserWith(credentials: SignInRequest): Result<SignInResponse> =
        authRepository
            .signInWith(credentials.toRemote(SignInRequestMapper))
            .toPresentation(SignInResponseMapper)
}
