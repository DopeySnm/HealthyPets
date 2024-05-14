package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository

interface DeleteFavoriteFoodUseCase {
    suspend operator fun invoke(idFood: Int)
}

class DeleteFavoriteFoodUseCaseImpl(
    private val repository: HealthyPetsRepository
): DeleteFavoriteFoodUseCase {
    override suspend fun invoke(idFood: Int) {
        repository.deleteFavoriteFood(idFood)
    }
}
