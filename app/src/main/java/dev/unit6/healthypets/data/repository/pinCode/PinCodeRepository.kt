package dev.unit6.healthypets.data.repository.pinCode

import dev.unit6.healthypets.data.model.PinCode
import dev.unit6.healthypets.data.state.DataState

interface PinCodeRepository {
    suspend fun savePinCodeHash(pinCodeHash: ByteArray)

    suspend fun getPinCodeHash(id: Int): DataState<PinCode>

    suspend fun clearPinCode()

    suspend fun setIsProtected()
}
