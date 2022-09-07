package az.talmir.app.structure.kernel.mappers

import az.talmir.app.structure.kernel.models.presentation.Presentation

internal abstract class Presentation2LocalMapper<P, L> : Mapper<P, L> where P : Presentation {
    abstract override fun map(`in`: P): L
}
