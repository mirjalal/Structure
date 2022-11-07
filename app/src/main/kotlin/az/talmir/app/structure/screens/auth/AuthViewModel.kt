package az.talmir.app.structure.screens.auth

import androidx.lifecycle.ViewModel
import az.talmir.app.structure.kernel.features.sign_in.SignInUseCase

class AuthViewModel(
    private val useCase: SignInUseCase
) : ViewModel() {

}
