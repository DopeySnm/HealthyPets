package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.model.PinCode
import dev.unit6.healthypets.data.repository.pinCode.PinCodeRepository
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject

interface GetPinCodeHashUseCase {
    suspend operator fun invoke(id: Int): DataState<PinCode>
}

class GetPinCodeHashUseCaseImpl @Inject constructor(
    private val repository: PinCodeRepository
) : GetPinCodeHashUseCase {
    override suspend fun invoke(id: Int): DataState<PinCode> {
        return repository.getPinCodeHash(id)
    }
}
