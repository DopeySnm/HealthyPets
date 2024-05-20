package dev.unit6.healthypets.domain

import javax.inject.Inject

interface WipeDataUseCase {
    suspend operator fun invoke()
}

class WipeDataUseCaseImpl @Inject constructor(
    private val clearFavoriteFood: ClearFavoriteFoodUseCase,
    private val clearPinCodeUseCase: ClearPinCodeUseCase
) : WipeDataUseCase{
    override suspend fun invoke() {
        clearFavoriteFood.invoke()
        clearPinCodeUseCase.invoke()
    }
}
