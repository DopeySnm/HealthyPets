package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.dataManagement.DataManagementRepository
import javax.inject.Inject

interface WipeDataUseCase {
    suspend operator fun invoke()
}

class WipeDataUseCaseImpl @Inject constructor(
    private val repository: DataManagementRepository
) : WipeDataUseCase{
    override suspend fun invoke() {
        repository.wipeData()
    }
}
