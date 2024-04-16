package dev.unit6.healthypets.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.unit6.healthypets.data.db.model.PinCodeEntity

@Dao
interface PinCodeDao {
    @Upsert
    suspend fun saveHashPinCode(pinCode: PinCodeEntity)

    @Query("SELECT * FROM pinCode WHERE id = :id")
    suspend fun getHashPinCode(id: Int): PinCodeEntity?
}
