package dev.unit6.healthypets.domain

import dev.unit6.healthypets.data.model.PersonalInfo
import dev.unit6.healthypets.data.repository.personalInfo.PersonalInfoRepository
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject

interface GetPersonalInfoUseCase {
    suspend operator fun invoke(id: Int): DataState<PersonalInfo?>
}

class GetPersonalInfoUseCaseImpl @Inject constructor(
    private val repository: PersonalInfoRepository
): GetPersonalInfoUseCase {
    override suspend fun invoke(id: Int): DataState<PersonalInfo?> {
        return repository.getPersonalInfoById(id)
    }
}
