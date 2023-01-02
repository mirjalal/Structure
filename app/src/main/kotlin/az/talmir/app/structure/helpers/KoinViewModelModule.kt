package az.talmir.app.structure.helpers

import az.talmir.app.structure.screens.auth.AuthViewModel
import az.talmir.app.structure.screens.root.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinViewModelModule = module {
    viewModelOf(::RootViewModel)

    scope(qualifier = named("auth_scope_qualifier")) {
        viewModelOf(::AuthViewModel)
    }
}
