package dev.unit6.healthypets.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.unit6.healthypets.data.db.model.PinCodeEntity

@Database(
    entities = [
        PinCodeEntity::class
    ],
    version = 1
)
abstract class PinCodeDatabase : RoomDatabase() {
    abstract fun currencyDao(): PinCodeDao
}
