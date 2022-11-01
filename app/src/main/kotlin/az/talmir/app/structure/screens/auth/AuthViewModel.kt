package az.talmir.app.structure.screens.auth

import androidx.lifecycle.ViewModel
import az.talmir.app.structure.kernel.features.sign_in.SignInUseCase
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Scope

@KoinViewModel
@Scope(name = "auth_scope")
class AuthViewModel(
    private val useCase: SignInUseCase
) : ViewModel() {

}
