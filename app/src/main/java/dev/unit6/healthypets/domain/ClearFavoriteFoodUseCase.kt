package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import javax.inject.Inject

interface ClearFavoriteFoodUseCase {
    suspend operator fun invoke()
}

class ClearFavoriteFoodUseCaseImpl @Inject constructor(
    private val repository: HealthyPetsRepository
) : ClearFavoriteFoodUseCase {
    override suspend fun invoke() {
        repository.clearFavoriteFood()
    }
}
