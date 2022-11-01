package az.talmir.app.structure.core.storage.prefs.token

import az.talmir.app.structure.core.storage.SHARED_PREF_STORAGE_KEY_TOKEN_INFO
import kotlinx.serialization.decodeFromString
import org.koin.core.annotation.Single

@Single
class TokenInfoReaderService : TokenInfoLocalService() {
    override suspend fun getTokenInfo(): TokenInfoLocalResponse? =
        encryptedSharedPreferences.getString(SHARED_PREF_STORAGE_KEY_TOKEN_INFO, null)?.let {
            json.decodeFromString(it)
        }
}
