package az.talmir.app.structure.core.features.auth

import az.talmir.app.structure.core.bridges.ApiBridge
import az.talmir.app.structure.core.features.auth.sign_in_models.SignInRequestBody
import az.talmir.app.structure.core.features.auth.sign_in_models.SignInResponse
import az.talmir.app.structure.shared.models.Result
import io.ktor.client.HttpClient

class AuthRepository(
    private val httpClient: HttpClient,
    private val apiBridge: ApiBridge
) {
    suspend fun signInWith(credentials: SignInRequestBody): Result<SignInResponse> =
        apiBridge.buildApiCall {
            httpClient.signIn(credentials, it)
        }
}
