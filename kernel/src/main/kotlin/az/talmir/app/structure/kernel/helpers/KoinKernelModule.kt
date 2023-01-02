package az.talmir.app.structure.kernel.helpers

import az.talmir.app.structure.core.koin.koinCoreModule
import az.talmir.app.structure.kernel.features.sign_in.SignInUseCase
import az.talmir.app.structure.kernel.features.sign_up.SignUpUseCase
import az.talmir.app.structure.kernel.features.token.TokenUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.scopedOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinKernelModule = module {
    loadKoinModules(koinCoreModule)

    factoryOf(::TokenUseCase)

    scope(qualifier = named("auth_scope_qualifier")) {
        scopedOf(::SignUpUseCase)
        scopedOf(::SignInUseCase)
    }
    scope(qualifier = named("main_scope_qualifier")) {

    }
}
