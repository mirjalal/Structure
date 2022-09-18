package az.talmir.app.structure.core.bridges

import az.talmir.app.structure.core.features.token.TokenInfoRepository
import az.talmir.app.structure.core.helpers.ApiCallParams
import az.talmir.app.structure.core.helpers.doApiCall
import az.talmir.app.structure.core.storage.prefs.language.LangReaderService
import az.talmir.app.structure.shared.models.Result
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers

class TokenizedApiBridge(
    val tokenInfoRepository: TokenInfoRepository,
    val langReaderService: LangReaderService
) {
    suspend inline fun <reified T> buildApiCall(
        crossinline request: suspend (ApiCallParams) -> HttpResponse
    ): Result<T> =
        doApiCall(Dispatchers.IO) {
            request(
                ApiCallParams(
                    token = tokenInfoRepository.getJwt()?.jwt,
                    language = langReaderService.getLangCode()
                )
            )
        }
}
