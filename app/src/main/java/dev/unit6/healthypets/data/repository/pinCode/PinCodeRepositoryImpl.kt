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

    override suspend fun getPinCodeHash(id: Int): DataState<PinCode> {
        return kotlin.runCatching {
            dao.getHashPinCode(id) to preferenceProvider.getIsProtected()
        }.fold(
            onSuccess = { pair ->
                pair.first?.let {
                    DataState.Success(PinCode(it.value, pair.second))
                }
                DataState.Success(PinCode(null, pair.second))
            },
            onFailure = {
                DataState.Failure(it.message ?: "Unknown error")
            }
        )
    }

}
