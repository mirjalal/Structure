package az.talmir.app.structure.core.storage.prefs.language

import androidx.security.crypto.EncryptedSharedPreferences
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class LangStorage : KoinComponent {
    val encryptedSharedPreferences: EncryptedSharedPreferences by inject()

    open suspend fun getLangCode(): String? =
        throw NotImplementedError("This function must not be called directly")

    open suspend fun setLang(code: String): Unit =
        throw NotImplementedError("This function must not be called directly")
}
