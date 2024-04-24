package dev.unit6.healthypets.di.modules

import dagger.Module
import dagger.Provides
import dev.unit6.healthypets.data.api.HealthyPetsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideHealthyPets(): HealthyPetsService {
        return Retrofit.Builder()
            .baseUrl("https://test.healthypets.dev.unit6.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HealthyPetsService::class.java)
    }
}
