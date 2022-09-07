package az.talmir.app.structure.kernel.mappers

import az.talmir.app.structure.core.helpers.Local
import az.talmir.app.structure.shared.mappers.Mapper

internal abstract class Local2RemoteMapper<L, R> : Mapper<L, R> where L : Local {
    abstract override fun map(`in`: L): R
}
