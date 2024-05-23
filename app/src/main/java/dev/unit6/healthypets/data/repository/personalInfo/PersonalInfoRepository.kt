package dev.unit6.healthypets.data.repository.personalInfo

import dev.unit6.healthypets.data.model.PersonalInfo
import dev.unit6.healthypets.data.state.DataState

interface PersonalInfoRepository {

    suspend fun getPersonalInfoById(id: Int): DataState<PersonalInfo?>

    suspend fun savePersonalInfo(personalInfo: PersonalInfo)

}
