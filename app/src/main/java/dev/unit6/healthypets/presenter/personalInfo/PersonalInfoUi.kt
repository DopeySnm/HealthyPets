package dev.unit6.healthypets.presenter.personalInfo

import dev.unit6.healthypets.DateHelper
import dev.unit6.healthypets.data.model.PersonalInfo

data class PersonalInfoUi(
    var name: String = "",
    var surname: String = "",
    var dateBirth: String = "",
    var mail: String = "",
    var phoneNumber: String = ""
) {
    fun toPersonalInfo(id: Int): PersonalInfo =
        PersonalInfo(
            id = id,
            name = name,
            surname = surname,
            dateBirth = if (dateBirth == "") 0.0f else DateHelper.dateToFloat(dateString = dateBirth),
            mail = mail,
            phoneNumber = phoneNumber
        )
}
