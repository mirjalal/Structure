package az.talmir.app.structure.core.features.auth

import az.talmir.app.structure.core.features.auth.sign_in_models.SignInRemoteRequestBody
import az.talmir.app.structure.core.features.auth.sign_up_models.SignUpRemoteRequestBody
import az.talmir.app.structure.core.helpers.ApiCallParams
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders

suspend fun HttpClient.signIn(
    requestBody: SignInRemoteRequestBody,
    standardParams: ApiCallParams
) = post(AUTH_ENDPOINT) {
    standardParams.language?.let {
        header(HttpHeaders.AcceptLanguage, it)
    }
    setBody(requestBody)
}
