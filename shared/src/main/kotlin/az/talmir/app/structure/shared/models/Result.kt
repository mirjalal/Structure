package az.talmir.app.structure.shared.models

/**
 * Wrapper class for _any type_ of result data. This class could be used
 * in any layer of application.
 *
 * @author mirjalal
 */
sealed interface Result<out Model> {
    /**
     * If data fetch call (from api or database) succeeded, the result captured
     * by this class and the will be _stored_ in [data].
     *
     * @param data holds the result of successful execution
     */
    data class Success<out T>(val data: T) : Result<T>

    /**
     * General failure results will be stored by this type & [FailModel] instance.
     *
     * @param fail holds relative information about failure
     */
    data class Failure(val fail: FailModel) : Result<Nothing>

    /**
     * This type will be mostly used to handle unauthorized remote call results.
     */
    object Unauthorized : Result<Nothing>

    /**
     * This type will be mostly used to handle unauthorized remote call results.
     */
    object NotFound : Result<Nothing>

    /**
     * This class is used to handle _any other kind of_ possible error or
     * exception which could be occur during execution (ex: IOException, HttpException etc).
     */
    data class Error(val throwable: Throwable) : Result<Nothing>
}
