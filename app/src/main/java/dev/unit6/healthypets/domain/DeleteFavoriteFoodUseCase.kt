package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import javax.inject.Inject

interface DeleteFavoriteFoodUseCase {
    suspend operator fun invoke(idFood: Int)
}

class DeleteFavoriteFoodUseCaseImpl @Inject constructor(
    private val repository: HealthyPetsRepository
): DeleteFavoriteFoodUseCase {
    override suspend fun invoke(idFood: Int) {
        repository.deleteFavoriteFood(idFood)
    }
}
