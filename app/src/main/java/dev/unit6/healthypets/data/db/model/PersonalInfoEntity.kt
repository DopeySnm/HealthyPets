package dev.unit6.healthypets.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.unit6.healthypets.data.model.PersonalInfo

@Entity(
    tableName = "personalInfo"
)
data class PersonalInfoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String?,
    val surname: String?,
    val dateBirth: String?,
    val mail: String?,
    val phoneNumber: String?
) {
    fun toPersonalInfo(): PersonalInfo =
        PersonalInfo(
            id,
            name,
            surname,
            dateBirth,
            mail,
            phoneNumber
        )
}
