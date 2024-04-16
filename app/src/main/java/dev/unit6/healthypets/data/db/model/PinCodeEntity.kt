package dev.unit6.healthypets.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pinCode"
)
data class PinCodeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val value: ByteArray
)
