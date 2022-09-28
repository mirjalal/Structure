package az.talmir.app.structure.core.features.auth

import az.talmir.app.structure.core.bridges.ApiBridge
import az.talmir.app.structure.core.features.auth.sign_in_models.SignInRemoteRequestBody
import az.talmir.app.structure.core.features.auth.sign_in_models.SignInRemoteResponse
import az.talmir.app.structure.core.features.auth.sign_up_models.SignUpRemoteRequestBody
import az.talmir.app.structure.core.features.auth.sign_up_models.SignUpRemoteResponse
import az.talmir.app.structure.shared.models.Result
import io.ktor.client.HttpClient
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Single

@Single
@Scope(name = "auth_scope")
class AuthRepository(
    private val httpClient: HttpClient,
    private val apiBridge: ApiBridge
) {
    suspend fun signInWith(credentials: SignInRemoteRequestBody): Result<SignInRemoteResponse> =
        apiBridge.buildApiCall {
            httpClient.signIn(credentials, it)
        }

    suspend fun registerUserWith(credentials: SignUpRemoteRequestBody): Result<SignUpRemoteResponse> =
        apiBridge.buildApiCall {
            httpClient.signUp(credentials, it)
        }
}
