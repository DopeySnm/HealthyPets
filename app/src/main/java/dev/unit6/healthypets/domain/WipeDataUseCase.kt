package dev.unit6.healthypets.domain

import javax.inject.Inject

interface WipeDataUseCase {
    suspend operator fun invoke()
}

class WipeDataUseCaseImpl @Inject constructor(
    private val clearFavoriteFood: ClearFavoriteFoodUseCase,
    private val clearPinCode: ClearPinCodeUseCase,
    private val clearPersonalInfo: ClearPersonalInfoUseCase
) : WipeDataUseCase{
    override suspend fun invoke() {
        clearFavoriteFood()
        clearPinCode()
        clearPersonalInfo()
    }
}
