package az.talmir.app.structure.kernel.features.sign_up.models

import az.talmir.app.structure.kernel.models.presentation.Presentation

data class SignUpRequest(
    val username: String,
    val password: String,
    val name: String,
    val surname: String
) : Presentation()
