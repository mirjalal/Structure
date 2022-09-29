package az.talmir.app.structure

import android.app.Application
import az.talmir.app.structure.kernel.helpers.KoinKernelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.az_talmir_app_structure_kernel_helpers_KoinKernelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val km = KoinKernelModule()
        startKoin {
            androidContext(this@App)
            modules(
                defaultModule,
                az_talmir_app_structure_kernel_helpers_KoinKernelModule,
                km.coreModule
            )
        }
    }
}
