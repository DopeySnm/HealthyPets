package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.model.PinCode
import dev.unit6.healthypets.data.repository.healthyPets.HealthyPetsRepository
import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepository
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject

interface GetAllFoodsUseCase {
    suspend operator fun invoke(): DataState<List<Food>>
}

class GetAllFoodsUseCaseImpl  @Inject constructor(
    private val repository: HealthyPetsRepository
) : GetAllFoodsUseCase {
    override suspend fun invoke(): DataState<List<Food>> {
        return repository.getAllFoods()
    }
}
