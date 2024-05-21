package dev.unit6.healthypets.data.repository.personalInfo

import dev.unit6.healthypets.data.db.PersonalInfoDao
import dev.unit6.healthypets.data.model.PersonalInfo
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject

class PersonalInfoRepositoryImpl @Inject constructor(
    private val dao: PersonalInfoDao
) : PersonalInfoRepository {

    override suspend fun getPersonalInfoById(id: Int): DataState<PersonalInfo?> {
        kotlin.runCatching {
            dao.getById(id)
        }.fold(
            onSuccess = {
                it?.let {
                    return DataState.Success(it.toPersonalInfo())
                } ?: return DataState.Success(null)
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

    override suspend fun savePersonalInfo(personalInfo: PersonalInfo) {
        if (dao.getById(personalInfo.id) != null) {
            dao.update(personalInfo.toPersonalInfoEntity())

        } else {
            dao.save(personalInfo.toPersonalInfoEntity())
        }
    }

}
