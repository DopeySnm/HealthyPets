package dev.unit6.healthypets.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.unit6.healthypets.di.modules.AppBindsModule
import dev.unit6.healthypets.di.modules.DataBaseModule
import dev.unit6.healthypets.di.modules.ViewModelModule
import dev.unit6.healthypets.presenter.auth.AuthFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        AppBindsModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: AuthFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): AppComponent
    }
}
