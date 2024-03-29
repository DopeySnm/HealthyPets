package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.PetsRepository
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject


interface GetPinCodeHashUseCase {
    suspend operator fun invoke(): DataState<ByteArray>
}

class GetPinCodeHashUseCaseImpl @Inject constructor(
    private val repository: PetsRepository
) : GetPinCodeHashUseCase {
    override suspend fun invoke(): DataState<ByteArray> {
        return repository.getPinCodeHash()
    }
}
