package az.talmir.app.structure.core.storage.prefs.language

import az.talmir.app.structure.core.storage.SHARED_PREF_STORAGE_KEY_LANGUAGE

class LangReaderService : LangStorage() {
    private lateinit var langCode: String

    override suspend fun getLangCode(): String? =
        if (::langCode.isInitialized)
            langCode
        else
            encryptedSharedPreferences.getString(SHARED_PREF_STORAGE_KEY_LANGUAGE, null)?.also {
                langCode = it
            }
}
