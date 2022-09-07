package az.talmir.app.structure.shared.mappers

interface Mapper<IN, OUT> {
    fun map(`in`: IN): OUT
}
