package az.talmir.app.structure

import android.app.Application
import az.talmir.app.structure.helpers.ViewModelModule
import az.talmir.app.structure.kernel.helpers.KernelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val km = KernelModule()
        startKoin {
            androidContext(this@App)
            modules(
                ViewModelModule().module,
                km.module,
                km.coreModule
            )
        }
    }
}
