package az.talmir.app.structure.screens.root

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import az.talmir.app.structure.screens.auth.AuthActivity
import az.talmir.app.structure.screens.main.MainActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.annotation.Scope

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        val rootViewModel: RootViewModel by inject()
        lifecycleScope.launch {
            if (rootViewModel.isTokenValid())
                start<MainActivity>()
            else
                start<AuthActivity>()
        }
    }

    private inline fun <reified C> start() =
        startActivity(Intent(this@RootActivity, C::class.java))
}
