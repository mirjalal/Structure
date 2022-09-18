package az.talmir.app.structure.core.storage.prefs.token

import androidx.core.content.edit
import az.talmir.app.structure.core.storage.SHARED_PREF_STORAGE_KEY_TOKEN_INFO
import kotlinx.serialization.encodeToString

class TokenInfoWriterService : TokenInfoLocalService() {
    override suspend fun setToken(data: TokenInfo) {
        encryptedSharedPreferences.edit {
            putString(SHARED_PREF_STORAGE_KEY_TOKEN_INFO, json.encodeToString(data))
        }
    }
}
