package dev.unit6.healthypets.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.unit6.healthypets.data.db.PetsHealthyDatabase
import dev.unit6.healthypets.data.db.PinCodeDao

@Module
class DataBaseModule {
    @Provides
    fun provideDatabase(context: Context): PetsHealthyDatabase =
        Room.databaseBuilder(
            context,
            PetsHealthyDatabase::class.java,
            "petsHealthy.db"
        ).build()


    @Provides
    fun provideCurrencyDao(db: PetsHealthyDatabase): PinCodeDao {
        return db.pinCodeDao()
    }
}
