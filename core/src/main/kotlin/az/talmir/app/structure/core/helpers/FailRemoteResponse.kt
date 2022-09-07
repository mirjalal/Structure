package az.talmir.app.structure.core.helpers

import az.talmir.app.structure.shared.models.FailModel

@kotlinx.serialization.Serializable
data class FailRemoteResponse(val data: Unit, val statusCode: Int) : FailModel
