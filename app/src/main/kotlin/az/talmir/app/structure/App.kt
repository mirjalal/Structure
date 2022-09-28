package az.talmir.app.structure

import android.app.Application
import az.talmir.app.structure.helpers.KoinViewModelModule
import az.talmir.app.structure.kernel.helpers.KoinKernelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val km = KoinKernelModule()
        startKoin {
            androidContext(this@App)
            modules(
                KoinViewModelModule().module,
                km.module,
                km.coreModule
            )
        }
    }
}
