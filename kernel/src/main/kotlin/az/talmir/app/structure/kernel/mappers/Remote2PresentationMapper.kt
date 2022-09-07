package az.talmir.app.structure.kernel.mappers

import az.talmir.app.structure.core.helpers.Remote

internal abstract class Remote2PresentationMapper<R, P> : Mapper<R, P> where R : Remote {
    abstract override fun map(`in`: R): P
}
