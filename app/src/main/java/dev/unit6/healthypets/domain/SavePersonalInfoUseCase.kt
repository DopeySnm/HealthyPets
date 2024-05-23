package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.model.PersonalInfo
import dev.unit6.healthypets.data.repository.personalInfo.PersonalInfoRepository
import javax.inject.Inject

interface SavePersonalInfoUseCase {
    suspend operator fun invoke(personalInfo: PersonalInfo)
}

class SavePersonalInfoUseCaseImpl @Inject constructor(
    private val repository: PersonalInfoRepository
): SavePersonalInfoUseCase {
    override suspend fun invoke(personalInfo: PersonalInfo) {
        repository.savePersonalInfo(personalInfo)
    }
}
