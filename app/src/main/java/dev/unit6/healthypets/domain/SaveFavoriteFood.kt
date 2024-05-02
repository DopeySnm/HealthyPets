package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import javax.inject.Inject

interface SaveFavoriteFoodUseCase {
    suspend operator fun invoke(foodId: Int)
}

class SaveFavoriteFoodUseCaseImpl @Inject constructor(
    private val repository: HealthyPetsRepository
) : SaveFavoriteFoodUseCase {
    override suspend fun invoke(foodId: Int) {
        repository.saveFavoriteFood(foodId)
    }
}
