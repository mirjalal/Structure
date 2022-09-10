package az.talmir.app.structure.core.storage.prefs.token

import androidx.security.crypto.EncryptedSharedPreferences
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class TokenStorage : KoinComponent {
    internal val encryptedSharedPreferences: EncryptedSharedPreferences by inject()
    protected val json: Json by inject()

    open suspend fun getTokenInfo(): TokenInfo? =
        throw NotImplementedError("This function must not be called directly")

    open suspend fun setToken(data: TokenInfo): Unit =
        throw NotImplementedError("This function must not be called directly")
}
