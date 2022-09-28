package az.talmir.app.structure.core.storage.prefs.language

import androidx.core.content.edit
import az.talmir.app.structure.core.storage.SHARED_PREF_STORAGE_KEY_LANGUAGE
import org.koin.core.annotation.Single

@Single
class LangWriterService : LangStorage() {
    override suspend fun setLang(code: String) {
        encryptedSharedPreferences.edit {
            putString(SHARED_PREF_STORAGE_KEY_LANGUAGE, code)
        }
    }
}
