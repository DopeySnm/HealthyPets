package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import javax.inject.Inject

interface DislikeFoodUseCase {
    suspend operator fun invoke(foodId: Int)
}

class DislikeFoodUseCaseImpl @Inject constructor(
    private val repository: HealthyPetsRepository
): DislikeFoodUseCase {
    override suspend fun invoke(foodId: Int) {
        repository.deleteFavoriteFood(foodId)
    }
}
