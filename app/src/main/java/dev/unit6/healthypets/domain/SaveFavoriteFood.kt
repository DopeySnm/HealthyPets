package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import javax.inject.Inject

interface LikeFoodUseCase {
    suspend operator fun invoke(foodId: Int)
}

class LikeFoodUseCaseImpl @Inject constructor(
    private val repository: HealthyPetsRepository
) : LikeFoodUseCase {
    override suspend fun invoke(foodId: Int) {
        repository.saveFavoriteFood(foodId)
    }
}
