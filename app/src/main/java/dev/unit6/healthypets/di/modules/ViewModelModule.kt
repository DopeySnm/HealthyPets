package dev.unit6.healthypets.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.di.viewModel.ViewModelKey
import dev.unit6.healthypets.presenter.auth.AuthViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindConverterViewModel(viewModel: AuthViewModel): ViewModel

}
