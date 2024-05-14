package dev.unit6.healthypets.di.modules

import dagger.Binds
import dagger.Module
import dev.unit6.healthypets.data.preference.PreferenceProvider
import dev.unit6.healthypets.data.preference.PreferenceProviderImpl
import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepositoryImpl
import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepository
import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepositoryImpl
import dev.unit6.healthypets.domain.GetAllFoodsUseCase
import dev.unit6.healthypets.domain.GetAllFoodsUseCaseImpl
import dev.unit6.healthypets.domain.GetFeedByIdUseCase
import dev.unit6.healthypets.domain.GetFeedByIdUseCaseImpl
import dev.unit6.healthypets.domain.GetPinCodeHashUseCase
import dev.unit6.healthypets.domain.GetPinCodeHashUseCaseImpl
import dev.unit6.healthypets.domain.SaveFavoriteFoodUseCase
import dev.unit6.healthypets.domain.SaveFavoriteFoodUseCaseImpl
import dev.unit6.healthypets.domain.SavePinCodeHashUseCase
import dev.unit6.healthypets.domain.SavePinCodeHashUseCaseImpl
import javax.inject.Singleton

@Module
interface AppBindsModule {
    @Binds
    @Singleton
    fun bindGetPinCodeHashUseCase(
        useCase: GetPinCodeHashUseCaseImpl
    ): GetPinCodeHashUseCase

    @Binds
    @Singleton
    fun bindSavePinCodeHashUseCase(
        useCase: SavePinCodeHashUseCaseImpl
    ): SavePinCodeHashUseCase

    @Binds
    @Singleton
    fun bindGetAllFoodsUseCase(
        useCase: GetAllFoodsUseCaseImpl
    ): GetAllFoodsUseCase

    @Binds
    @Singleton
    fun bindGetFeedByIdUseCase(
        useCase: GetFeedByIdUseCaseImpl
    ): GetFeedByIdUseCase

    @Binds
    @Singleton
    fun bindSaveFavoriteFoodUseCase(
        useCase: SaveFavoriteFoodUseCaseImpl
    ): SaveFavoriteFoodUseCase

    @Binds
    @Singleton
    fun bindPinCodeRepository(
        repository: PinCodeRepositoryImpl
    ) : PinCodeRepository

    @Binds
    @Singleton
    fun bindHealthyPetsRepository(
        repository: HealthyPetsRepositoryImpl
    ) : HealthyPetsRepository

    @Binds
    @Singleton
    fun bindPreferenceProvider(
        useCase: PreferenceProviderImpl
    ): PreferenceProvider
}
