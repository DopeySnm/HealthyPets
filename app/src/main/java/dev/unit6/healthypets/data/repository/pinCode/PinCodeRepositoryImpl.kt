package dev.unit6.healthypets.data.repository.pinCode

import dev.unit6.healthypets.data.db.PinCodeDao
import dev.unit6.healthypets.data.db.model.PinCodeEntity
import dev.unit6.healthypets.data.model.PinCode
import dev.unit6.healthypets.data.preference.PreferenceProvider
import dev.unit6.healthypets.data.state.DataState
import javax.inject.Inject

class PinCodeRepositoryImpl @Inject constructor(
    private val dao: PinCodeDao,
    private val preferenceProvider: PreferenceProvider
) : PinCodeRepository {
    override suspend fun savePinCodeHash(pinCodeHash: ByteArray) {
        dao.saveHashPinCode(PinCodeEntity(value = pinCodeHash))
        preferenceProvider.setFirstRun()
    }

    override suspend fun getPinCodeHash(id: Int): DataState<PinCode> {
        kotlin.runCatching {
            dao.getHashPinCode(id) to preferenceProvider.getFirstRun()
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
