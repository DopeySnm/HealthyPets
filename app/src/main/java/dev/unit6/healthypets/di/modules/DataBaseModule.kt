package dev.unit6.healthypets.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.unit6.healthypets.data.db.PinCodeDao
import dev.unit6.healthypets.data.db.PinCodeDatabase

@Module
class DataBaseModule {
    @Provides
    fun provideDatabase(context: Context): PinCodeDatabase =
        Room.databaseBuilder(
            context,
            PinCodeDatabase::class.java,
            "petsHealthy.db"
        ).build()


    @Provides
    fun provideCurrencyDao(db: PinCodeDatabase): PinCodeDao {
        return db.currencyDao()
    }
}
