package dev.unit6.healthypets.data.repository

import dev.unit6.healthypets.data.state.DataState

interface PetsRepository {

    suspend fun savePinCodeHash(pinCodeHash: ByteArray)

    suspend fun getPinCodeHash(): DataState<ByteArray>
}
