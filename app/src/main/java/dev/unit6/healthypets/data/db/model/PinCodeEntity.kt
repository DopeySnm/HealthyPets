package dev.unit6.healthypets.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pinCode"
)
data class PinCodeEntity(
    @PrimaryKey
    val value: ByteArray
)
