package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepository
import javax.inject.Inject

interface DoNotSetPinCodeUseCase {
    suspend operator fun invoke()
}

class DoNotSetPinCodeUseCaseImpl @Inject constructor(
    private val repository: PinCodeRepository
) : DoNotSetPinCodeUseCase {
    override suspend fun invoke() {
        repository.setIsProtected()
    }
}
