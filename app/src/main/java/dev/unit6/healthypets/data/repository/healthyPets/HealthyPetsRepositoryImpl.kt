package dev.unit6.healthypets.data.repository.healthyPets

import dev.unit6.healthypets.data.api.HealthyPetsService
import dev.unit6.healthypets.data.db.FavoriteFoodDao
import dev.unit6.healthypets.data.db.model.FavoriteFoodEntity
import dev.unit6.healthypets.data.db.model.FoodId
import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject

class HealthyPetsRepositoryImpl @Inject constructor(
    private val service: HealthyPetsService,
    private val dao: FavoriteFoodDao
): HealthyPetsRepository {
    override suspend fun deleteFavoriteFood(foodId: Int) {
        dao.deleteFavoriteFood(FoodId(foodId))
    }

    override suspend fun getAllFoods(): DataState<List<Food>> {
        kotlin.runCatching {
            service.getAllFoods()
        }.fold(
            onSuccess = { response->
                return if (response.isSuccessful) {
                    response.body()?.let { foods ->
                        val result = foods.map { food ->
                            val imageUrl = getImage(food.image).let {
                                if (it is DataState.Success) {
                                    it.value
                                } else {
                                    null
                                }
                            }

                            val favorite = getFavoriteFoodById(food.id).let {
                                if (it is DataState.Success) {
                                    it.value
                                } else {
                                    false
                                }
                            }

                            food.toFood(imageUrl, favorite)
                        }
                        DataState.Success(result)
                    } ?: DataState.Failure("Empty response")
                } else DataState.Failure("Unable to get all foods")
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

    override suspend fun getImage(name: String): DataState<String> {
        kotlin.runCatching {
            service.getImage(name)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful) {
                    DataState.Success(it.raw().request.url.toString())
                } else DataState.Failure("Unable to get image")
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

    override suspend fun getFoodById(id: Int): DataState<Food> {
        kotlin.runCatching {
            service.getFoodById(id)
        }.fold(
            onSuccess = { response ->
                return if (response.isSuccessful) {
                    response.body()?.let { food ->
                        val imageUrl = getImage(food.image).let {
                            if (it is DataState.Success) {
                                it.value
                            } else {
                                null
                            }
                        }

                        val favorite = getFavoriteFoodById(food.id).let {
                            if (it is DataState.Success) {
                                it.value
                            } else {
                                false
                            }
                        }

                        DataState.Success(food.toFood(imageUrl, favorite))
                    } ?: DataState.Failure("Empty response")
                } else DataState.Failure("Unable to get food")
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

    override suspend fun getFavoriteFoodById(idFood: Int): DataState<Boolean> {
        kotlin.runCatching {
            dao.getFavoriteFoodById(idFood)
        }.fold(
            onSuccess = {
                it?.let {
                    return DataState.Success(true)
                } ?: return DataState.Success(false)
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

    override suspend fun saveFavoriteFood(foodId: Int) {
        dao.saveFavoriteFood(FavoriteFoodEntity(foodId = foodId))
    }
}
