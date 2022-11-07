package az.talmir.app.structure.screens.root

import androidx.lifecycle.ViewModel
import az.talmir.app.structure.kernel.features.token.TokenUseCase

class RootViewModel(
    private val tokenUseCase: TokenUseCase
) : ViewModel() {
    suspend fun isTokenValid(): Boolean = tokenUseCase.isTokenValid()
}
