package dev.unit6.healthypets.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.unit6.healthypets.data.db.model.FavoriteFoodEntity
import dev.unit6.healthypets.data.db.model.PersonalInfoEntity
import dev.unit6.healthypets.data.db.model.PinCodeEntity

@Database(
    entities = [
        PinCodeEntity::class,
        FavoriteFoodEntity::class,
        PersonalInfoEntity::class
    ],
    version = 1
)
abstract class PetsHealthyDatabase : RoomDatabase() {
    abstract fun pinCodeDao(): PinCodeDao

    abstract fun favoriteFoodDao(): FavoriteFoodDao
    abstract fun personalInfoDao(): PersonalInfoDao
}
