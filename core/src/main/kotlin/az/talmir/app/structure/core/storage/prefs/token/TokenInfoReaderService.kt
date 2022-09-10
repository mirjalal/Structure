package az.talmir.app.structure.core.storage.prefs.token

import az.talmir.app.structure.core.storage.SHARED_PREF_STORAGE_KEY_TOKEN_INFO
import kotlinx.serialization.decodeFromString

class TokenInfoReaderService : TokenStorage() {
    private lateinit var tokenInfo: TokenInfo

    override suspend fun getTokenInfo(): TokenInfo? =
        if (::tokenInfo.isInitialized)
            tokenInfo
        else
            encryptedSharedPreferences.getString(SHARED_PREF_STORAGE_KEY_TOKEN_INFO, null)?.let {
                json.decodeFromString<TokenInfo?>(it)
            }?.also {
                tokenInfo = it
            }
}
