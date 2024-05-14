package dev.unit6.healthypets.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import dev.unit6.healthypets.data.db.model.FavoriteFoodEntity

@Dao
interface FavoriteFoodDao {

    @Upsert
    suspend fun saveFavoriteFood(favoriteFoodEntity: FavoriteFoodEntity)

    @Query("DELETE FROM favoriteFood WHERE idFood = :idFood")
    suspend fun deleteFavoriteFood(idFood: Int)

    @Query("SELECT * FROM favoriteFood WHERE idFood = :idFood")
    suspend fun getFavoriteFoodById(idFood: Int): FavoriteFoodEntity?
}
