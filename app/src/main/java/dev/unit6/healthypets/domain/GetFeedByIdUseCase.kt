package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject

interface GetFeedByIdUseCase {
    suspend operator fun invoke(id: Int): DataState<Food>
}

class GetFeedByIdUseCaseImpl @Inject constructor(
    private val repository: HealthyPetsRepository
): GetFeedByIdUseCase {
    override suspend fun invoke(id: Int): DataState<Food> {
        return repository.getFoodById(id)
    }
}
