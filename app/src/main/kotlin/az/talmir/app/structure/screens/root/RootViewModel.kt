package az.talmir.app.structure.screens.root

import androidx.lifecycle.ViewModel
import az.talmir.app.structure.kernel.features.token.TokenUseCase
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Scope

@KoinViewModel
//@Scope(name = "root_scope")
class RootViewModel(
    private val tokenUseCase: TokenUseCase
) : ViewModel() {
    suspend fun isTokenValid(): Boolean = tokenUseCase.isTokenValid()
}
