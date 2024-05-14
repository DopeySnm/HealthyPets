package dev.unit6.healthypets.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.di.viewModel.ViewModelKey
import dev.unit6.healthypets.presenter.auth.AuthViewModel
import dev.unit6.healthypets.presenter.feedInfo.FeedInfoViewModel
import dev.unit6.healthypets.presenter.mainScreen.MainScreenViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedInfoViewModel::class)
    abstract fun bindFeedInfoViewModel(viewModel: FeedInfoViewModel): ViewModel

}
