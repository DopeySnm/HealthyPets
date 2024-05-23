package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.repository.personalInfo.PersonalInfoRepository
import javax.inject.Inject

interface ClearPersonalInfoUseCase {
    suspend operator fun invoke()
}

class ClearPersonalInfoUseCaseImpl @Inject constructor(
    private val repository: PersonalInfoRepository
) : ClearPersonalInfoUseCase {
    override suspend fun invoke() {
        repository.clearPersonalInfo()
    }

}
