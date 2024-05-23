package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepository
import javax.inject.Inject

interface ClearPinCodeUseCase {
    suspend operator fun invoke()
}

class ClearPinCodeUseCaseImpl @Inject constructor(
    private val repository: PinCodeRepository
) : ClearPinCodeUseCase {
    override suspend fun invoke() {
        repository.clearPinCode()
    }
}
