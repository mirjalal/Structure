package az.talmir.app.structure.kernel.features.sign_in

import az.talmir.app.structure.kernel.models.presentation.Presentation

data class SignInResponse(
    val jwt: String,
    val refreshToken: String? = null
) : Presentation()
