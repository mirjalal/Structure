package az.talmir.app.structure.kernel.mappers

import az.talmir.app.structure.kernel.models.presentation.Presentation
import az.talmir.app.structure.shared.mappers.Mapper

internal abstract class Presentation2RemoteMapper<P, R> : Mapper<P, R> where P : Presentation {
    abstract override fun map(`in`: P): R
}
