package dev.unit6.healthypets.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dev.unit6.healthypets.data.preference.PreferenceProvider
import dev.unit6.healthypets.data.preference.PreferenceProviderImpl
import dev.unit6.healthypets.data.repository.PinCodeRepository
import dev.unit6.healthypets.data.repository.PinCodeRepositoryImpl
import dev.unit6.healthypets.domain.GetPinCodeHashUseCase
import dev.unit6.healthypets.domain.GetPinCodeHashUseCaseImpl
import dev.unit6.healthypets.domain.SavePinCodeHashUseCase
import dev.unit6.healthypets.domain.SavePinCodeHashUseCaseImpl
import javax.inject.Singleton


@Module
interface AppBindsModule {
    @Binds
    fun bindGetPinCodeHashUseCase(
        useCase: GetPinCodeHashUseCaseImpl
    ): GetPinCodeHashUseCase

    @Binds
    fun bindSavePinCodeHashUseCase(
        useCase: SavePinCodeHashUseCaseImpl
    ): SavePinCodeHashUseCase

    @Binds
    fun bindPinCodeRepository(repository: PinCodeRepositoryImpl) : PinCodeRepository

    @Binds
    fun bindPreferenceProvider(
        useCase: PreferenceProviderImpl
    ): PreferenceProvider

    companion object {
        @Provides
        fun providesContext(app: Application): Context =
            app.applicationContext

    }
}
