package az.talmir.app.structure.kernel.helpers

import az.talmir.app.structure.core.koin.KoinCoreModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

@Module
@ComponentScan("az.talmir.app.structure.kernel")
class KoinKernelModule {
    val coreModule = KoinCoreModule().module
}
