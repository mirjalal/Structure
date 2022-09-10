package az.talmir.app.structure.kernel.helpers

import az.talmir.app.structure.core.helpers.Remote
import az.talmir.app.structure.kernel.models.presentation.Presentation
import az.talmir.app.structure.shared.mappers.Mapper
import az.talmir.app.structure.shared.models.Result

/**
 * An extension function to convert [T] to [U].
 *
 * @param mapper - the implementation of [Mapper] to convert [T]
 * @param T type received from source (backend/presentation/local layer).
 * @param U type which requester layer expects.
 *
 * @author mirjalal
 */
internal fun <T, U> Result<T>.toPresentation(
    mapper: Mapper<T, U>
): Result<U> =
    when (this) {
        is Result.Success -> Result.Success(mapper.map(data))
        is Result.Failure -> this
        is Result.Error -> this
        is Result.Unauthorized -> this
        is Result.NotFound -> this
    }

internal fun <P : Presentation, R : Remote> P.toRemote(mapper: Mapper<P, R>): R =
    mapper.map(this)
