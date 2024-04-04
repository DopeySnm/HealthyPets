package dev.unit6.healthypets.data.repository

import android.content.SharedPreferences
import dev.unit6.healthypets.data.db.PinCodeDao
import dev.unit6.healthypets.data.db.model.PinCodeEntity
import dev.unit6.healthypets.data.model.PinCode
import dev.unit6.healthypets.data.preference.PreferenceProvider
import dev.unit6.healthypets.data.preference.PreferenceProviderImpl
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject

interface PinCodeRepository {

    suspend fun savePinCodeHash(pinCodeHash: ByteArray)

    suspend fun getPinCodeHash(): DataState<PinCode>
}

class PinCodeRepositoryImpl @Inject constructor(
    private val dao: PinCodeDao,
    private val preferenceProvider: PreferenceProvider
) : PinCodeRepository {
    override suspend fun savePinCodeHash(pinCodeHash: ByteArray) {
        dao.saveHashPinCode(PinCodeEntity(pinCodeHash))
        preferenceProvider.setFirstRun()
    }

    override suspend fun getPinCodeHash(): DataState<PinCode> {
        kotlin.runCatching {
            dao.getHashPinCode() to preferenceProvider.getFirstRun()
        }.fold(
            onSuccess = {
                return DataState.Success(PinCode(it.first, it.second))
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

}
