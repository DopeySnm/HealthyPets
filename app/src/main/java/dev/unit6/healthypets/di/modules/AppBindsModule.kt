package dev.unit6.healthypets.di.modules

import dagger.Binds
import dagger.Module
import dev.unit6.healthypets.data.preference.PreferenceProvider
import dev.unit6.healthypets.data.preference.PreferenceProviderImpl
import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepositoryImpl
import dev.unit6.healthypets.data.repository.personalInfo.PersonalInfoRepository
import dev.unit6.healthypets.data.repository.personalInfo.PersonalInfoRepositoryImpl
import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepository
import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepositoryImpl
import dev.unit6.healthypets.domain.DislikeFoodUseCase
import dev.unit6.healthypets.domain.DislikeFoodUseCaseImpl
import dev.unit6.healthypets.domain.GetAllFoodsUseCase
import dev.unit6.healthypets.domain.GetAllFoodsUseCaseImpl
import dev.unit6.healthypets.domain.GetFeedByIdUseCase
import dev.unit6.healthypets.domain.GetFeedByIdUseCaseImpl
import dev.unit6.healthypets.domain.GetPersonalInfoUseCase
import dev.unit6.healthypets.domain.GetPersonalInfoUseCaseImpl
import dev.unit6.healthypets.domain.GetPinCodeHashUseCase
import dev.unit6.healthypets.domain.GetPinCodeHashUseCaseImpl
import dev.unit6.healthypets.domain.LikeFoodUseCase
import dev.unit6.healthypets.domain.LikeFoodUseCaseImpl
import dev.unit6.healthypets.domain.SavePersonalInfoUseCase
import dev.unit6.healthypets.domain.SavePersonalInfoUseCaseImpl
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
    fun bindLikeFoodUseCase(
        useCase: LikeFoodUseCaseImpl
    ): LikeFoodUseCase

    @Binds
    @Singleton
    fun bindDislikeFoodUseCase(
        useCase: DislikeFoodUseCaseImpl
    ) : DislikeFoodUseCase

    fun bindGetPersonalInfoUseCase(
        useCase: GetPersonalInfoUseCaseImpl
    ): GetPersonalInfoUseCase

    @Binds
    @Singleton
    fun bindSavePersonalInfoUseCase(
        useCase: SavePersonalInfoUseCaseImpl
    ): SavePersonalInfoUseCase

    @Binds
    @Singleton
    fun bindPersonalInfoRepository(
        repository: PersonalInfoRepositoryImpl
    ) : PersonalInfoRepository

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
