package dev.unit6.healthypets.data.repository.healthyPets

import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.state.DataState

interface HealthyPetsRepository {
    suspend fun getAllFoods(): DataState<List<Food>>

    suspend fun getImage(name: String): DataState<String>

    suspend fun getFoodById(id: Int): DataState<Food>

    suspend fun deleteFavoriteFood(foodId: Int)

    suspend fun getFavoriteFoodById(idFood: Int): DataState<Boolean>

    suspend fun saveFavoriteFood(foodId: Int)

    suspend fun clearFavoriteFood()
}
