package az.talmir.app.structure.core.storage.prefs.token

import android.content.SharedPreferences
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class TokenInfoLocalService : KoinComponent {
    protected val encryptedSharedPreferences: SharedPreferences by inject()
    protected val json: Json by inject()

    open suspend fun getTokenInfo(): TokenInfoLocalResponse? =
        throw NotImplementedError("This function must not be called directly")

    open suspend fun setToken(data: TokenInfoLocalResponse): Unit =
        throw NotImplementedError("This function must not be called directly")
}
