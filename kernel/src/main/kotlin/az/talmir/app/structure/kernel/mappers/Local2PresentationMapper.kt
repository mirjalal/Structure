package az.talmir.app.structure.kernel.mappers

import az.talmir.app.structure.core.helpers.Local
import az.talmir.app.structure.shared.mappers.Mapper

internal abstract class Local2PresentationMapper<L, P> : Mapper<L, P> where L : Local {
    abstract override fun map(`in`: L): P
}
