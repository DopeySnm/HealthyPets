package dev.unit6.healthypets.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import dev.unit6.healthypets.data.db.model.PersonalInfoEntity

@Dao
interface PersonalInfoDao {

    @Query("SELECT * FROM personalInfo WHERE id = :id")
    suspend fun getById(id: Int): PersonalInfoEntity?

    @Upsert
    suspend fun save(personalInfoEntity: PersonalInfoEntity)

    @Update
    suspend fun update(personalInfoEntity: PersonalInfoEntity)

    @Query("DELETE FROM personalInfo")
    suspend fun deleteAllPersonalInfo()

}
