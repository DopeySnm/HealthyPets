package dev.unit6.healthypets.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.unit6.healthypets.di.modules.AppBindsModule
import dev.unit6.healthypets.di.modules.DataBaseModule
import dev.unit6.healthypets.di.modules.NetworkModule
import dev.unit6.healthypets.di.modules.ViewModelModule
import dev.unit6.healthypets.presenter.auth.AuthFragment
import dev.unit6.healthypets.presenter.mainScreen.MainScreenFragment
import dev.unit6.healthypets.presenter.fullListFeeds.FullListFeedsFragment
import dev.unit6.healthypets.presenter.feedInfo.FeedInfoFragment
import dev.unit6.healthypets.presenter.personalInfo.PersonalInfoFragment
import dev.unit6.healthypets.presenter.profile.ProfileFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        AppBindsModule::class,
        DataBaseModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: AuthFragment)
    fun inject(fragment: MainScreenFragment)
    fun inject(fragment: FullListFeedsFragment)
    fun inject(fragment: FeedInfoFragment)
    fun inject(fragment: PersonalInfoFragment)
    fun inject(fragment: ProfileFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): AppComponent
    }
}
