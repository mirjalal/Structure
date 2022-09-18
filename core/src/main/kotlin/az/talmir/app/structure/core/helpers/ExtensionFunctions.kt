package az.talmir.app.structure.core.helpers

import az.talmir.app.structure.shared.models.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Executes the [request] in [CoroutineDispatcher] to invoke API Endpoint.
 *
 * @param request a suspendable network request method to consume.
 *
 * @author mirjalal
 */
suspend inline fun <reified R> doApiCall(
    dispatcher: CoroutineDispatcher,
    crossinline request: suspend () -> HttpResponse
): Result<R> =
    withContext(dispatcher) {
        try {
            val apiResponse = request()
            when (val code = apiResponse.status.value) {
                in 200..299 -> Result.Success(data = apiResponse.body())
                401 -> Result.Unauthorized
                404 -> Result.NotFound
                in 300..399,
                400, 402, 403,
                in 405..499,
                in 500..599 ->
                    Result.Failure(
                        fail = FailRemoteResponse(
                            data = apiResponse.body(),
                            statusCode = code
                        )
                    )
                else -> Result.Error(throwable = Throwable())
            }
        } catch (cause: Throwable) {
            Result.Error(throwable = cause)
        }
    }
