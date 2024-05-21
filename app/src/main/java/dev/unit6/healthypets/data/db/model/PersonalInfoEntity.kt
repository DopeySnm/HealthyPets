package dev.unit6.healthypets.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.unit6.healthypets.data.model.PersonalInfo

@Entity(
    tableName = "personalInfo"
)
data class PersonalInfoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(defaultValue = "")
    val name: String,
    @ColumnInfo(defaultValue = "")
    val surname: String,
    @ColumnInfo(defaultValue = "")
    val dateBirth: Float,
    @ColumnInfo(defaultValue = "")
    val mail: String,
    @ColumnInfo(defaultValue = "")
    val phoneNumber: String
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
