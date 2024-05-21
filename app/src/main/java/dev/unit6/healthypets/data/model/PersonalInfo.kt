package dev.unit6.healthypets.data.model

import dev.unit6.healthypets.DateHelper
import dev.unit6.healthypets.data.db.model.PersonalInfoEntity
import dev.unit6.healthypets.presenter.personalInfo.PersonalInfoUi

data class PersonalInfo(
    val id: Int,
    val name: String,
    val surname: String,
    val dateBirth: Float,
    val mail: String,
    val phoneNumber: String
) {
    fun toPersonalInfoEntity(): PersonalInfoEntity =
        PersonalInfoEntity(
            id = id,
            name = name,
            surname = surname,
            dateBirth = dateBirth,
            mail = mail,
            phoneNumber = phoneNumber
        )

    fun toPersonalInfoUi(): PersonalInfoUi =
        PersonalInfoUi(
            name = name,
            surname = surname,
            dateBirth = DateHelper.getDateInFormatted(dateBirth),
            mail = mail,
            phoneNumber = phoneNumber
        )
}
