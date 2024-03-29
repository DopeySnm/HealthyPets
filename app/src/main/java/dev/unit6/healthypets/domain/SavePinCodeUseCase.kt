package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.PetsRepository
import javax.inject.Inject

interface SavePinCodeHashUseCase {
    suspend operator fun invoke(pinCodeHash: ByteArray)
}

class SavePinCodeHashUseCaseImpl @Inject constructor(
    private val repository: PetsRepository
) : SavePinCodeHashUseCase {
    override suspend fun invoke(pinCodeHash: ByteArray) {
        repository.savePinCodeHash(pinCodeHash)
    }
}
