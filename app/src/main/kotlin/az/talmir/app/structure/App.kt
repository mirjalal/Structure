package az.talmir.app.structure

import android.app.Application
import az.talmir.app.structure.helpers.koinViewModelModule
import az.talmir.app.structure.kernel.helpers.koinKernelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                koinKernelModule,
                koinViewModelModule
            )
        }
    }
}
