package dev.unit6.healthypets.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.unit6.healthypets.utils.ConfigUtils
import dev.unit6.healthypets.data.api.HealthyPetsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideHealthyPets(context: Context): HealthyPetsService {
        return Retrofit.Builder()
            .baseUrl(ConfigUtils.getConfigValue(context, "healthy_pets_api_url"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HealthyPetsService::class.java)
    }
}
