package dev.unit6.healthypets.data.repository.pinCode

import dev.unit6.healthypets.data.db.PinCodeDao
import dev.unit6.healthypets.data.db.model.PinCodeEntity
import dev.unit6.healthypets.data.model.PinCode
import dev.unit6.healthypets.data.preference.PreferenceProvider
import dev.unit6.healthypets.data.state.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PinCodeRepositoryImpl @Inject constructor(
    private val dao: PinCodeDao,
    private val preferenceProvider: PreferenceProvider
) : PinCodeRepository {
    override suspend fun savePinCodeHash(pinCodeHash: ByteArray) {
        kotlin.runCatching {
            dao.saveHashPinCode(PinCodeEntity(value = pinCodeHash))
        }.fold(
            onSuccess = {
                preferenceProvider.setIsProtected()
            },
            onFailure = {
                throw IllegalArgumentException("Can't save PIN code")
            }
        )
    }

    override suspend fun clearPinCode() {
        dao.deleteAllPinCode()
        preferenceProvider.clearSharedPreferences()
    }

    override suspend fun getPinCodeHash(id: Int): DataState<PinCode> {
        kotlin.runCatching {
            dao.getHashPinCode(id) to preferenceProvider.getIsProtected()
        }.fold(
            onSuccess = { pair ->
                pair.first?.let {
                    return DataState.Success(PinCode(it.value, pair.second))
                }
                return DataState.Success(PinCode(null, pair.second))
            },
            onFailure = {
                return DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

    override suspend fun setIsProtected() {
        preferenceProvider.setIsProtected()
    }

}
