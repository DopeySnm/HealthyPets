package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepository
import javax.inject.Inject

interface SavePinCodeHashUseCase {
    suspend operator fun invoke(pinCodeHash: ByteArray, id: Int)
}

class SavePinCodeHashUseCaseImpl @Inject constructor(
    private val repository: PinCodeRepository
) : SavePinCodeHashUseCase {
    override suspend fun invoke(pinCodeHash: ByteArray, id: Int) {
        repository.savePinCodeHash(pinCodeHash, id)
    }
}
