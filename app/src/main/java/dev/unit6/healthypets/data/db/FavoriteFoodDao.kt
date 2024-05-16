package dev.unit6.healthypets.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Query
import androidx.room.Upsert
import dev.unit6.healthypets.data.db.model.FavoriteFoodEntity
import java.util.Objects

@Dao
interface FavoriteFoodDao {

    @Upsert
    suspend fun saveFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity)

    @Query("DELETE FROM favoriteFood WHERE foodId = :foodId")
    suspend fun deleteFavoriteFood(foodId: Int)

    @Query("SELECT * FROM favoriteFood WHERE foodId = :foodId")
    suspend fun getFavoriteFoodById(foodId: Int): FavoriteFoodEntity?

}
