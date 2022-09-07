package az.talmir.app.structure.core.helpers

import az.talmir.app.structure.shared.models.FailModel

@kotlinx.serialization.Serializable
data class FailRemoteResponse(val statusCode: Int? = null) : FailModel
