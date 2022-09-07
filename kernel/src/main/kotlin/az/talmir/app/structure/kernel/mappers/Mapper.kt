package az.talmir.app.structure.kernel.mappers

internal interface Mapper<IN, OUT> {
    fun map(`in`: IN): OUT
}
