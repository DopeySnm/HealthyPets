package dev.unit6.healthypets.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.unit6.healthypets.presenter.auth.AuthFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ AppModule::class ]
)
interface AppComponent {
    fun inject(fragment: AuthFragment)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}
