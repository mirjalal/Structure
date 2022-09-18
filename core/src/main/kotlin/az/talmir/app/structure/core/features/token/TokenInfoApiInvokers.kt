package az.talmir.app.structure.core.features.token

import az.talmir.app.structure.core.helpers.ApiCallParams
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders

suspend fun HttpClient.getNewAccessToken(
    requestBody: TokenInfoRequestBody,
    standardParams: ApiCallParams
) = post(TOKEN_INFO_ENDPOINT) {
    standardParams.language?.let {
        header(HttpHeaders.AcceptLanguage, it)
    }
    setBody(requestBody)
}
