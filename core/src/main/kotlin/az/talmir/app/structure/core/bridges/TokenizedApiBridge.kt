package az.talmir.app.structure.core.bridges

import az.talmir.app.structure.core.helpers.ApiCallParams
import az.talmir.app.structure.core.helpers.doApiCall
import az.talmir.app.structure.core.storage.prefs.language.LangReaderService
import az.talmir.app.structure.core.storage.prefs.token.TokenInfoReaderService
import az.talmir.app.structure.shared.models.Result
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers

class TokenizedApiBridge(
    val tokenReaderService: TokenInfoReaderService,
    val langReaderService: LangReaderService
) {
    suspend inline fun <reified T> buildApiCall(
        crossinline request: suspend (ApiCallParams) -> HttpResponse
    ): Result<T> =
        doApiCall(Dispatchers.IO) {
            request(
                ApiCallParams(
                    token = tokenReaderService.getTokenInfo()?.jwt,
                    language = langReaderService.getLangCode()
                )
            )
        }
}
