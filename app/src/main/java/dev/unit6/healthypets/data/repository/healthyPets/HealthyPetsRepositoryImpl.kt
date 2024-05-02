package dev.unit6.healthypets.data.repository.healthyPets

import dev.unit6.healthypets.data.api.HealthyPetsService
import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject
import dev.unit6.healthypets.data.db.FoodDao
import dev.unit6.healthypets.data.db.model.FavoriteFoodEntity


class HealthyPetsRepositoryImpl @Inject constructor(
    private val service: HealthyPetsService,
    private val dao: FoodDao
): HealthyPetsRepository {

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
                            food.toFood(imageUrl)
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
                        DataState.Success(food.toFood(imageUrl))
                    } ?: DataState.Failure("Empty response")
                } else DataState.Failure("Unable to get food")
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )

    override suspend fun saveFavoriteFood(idFood: Int) {
        dao.saveFavoriteFood(idFood)
    }
}
