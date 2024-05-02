package dev.unit6.healthypets.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.unit6.healthypets.data.db.model.FavoriteFoodEntity

@Dao
interface FoodDao {

    @Upsert
    suspend fun saveFavoriteFood(idFood: Int)

    @Query("SELECT * FROM favoriteFood")
    suspend fun getAllFavoriteFoods(): List<FavoriteFoodEntity>
}
