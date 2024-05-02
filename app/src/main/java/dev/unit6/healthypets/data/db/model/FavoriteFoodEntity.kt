package dev.unit6.healthypets.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favoriteFood"
)
data class FavoriteFoodEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val idFood: Int
)
