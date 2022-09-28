package az.talmir.app.structure.kernel.features.sign_in.models

import az.talmir.app.structure.kernel.models.presentation.Presentation

data class SignInRequest(val username: String, val password: String) : Presentation()
