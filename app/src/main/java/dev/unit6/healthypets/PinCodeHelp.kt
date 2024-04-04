package dev.unit6.healthypets

import com.toxicbakery.bcrypt.Bcrypt

class PinCodeHelp {
    fun hashPinCode(pinCode: String): ByteArray {
        return Bcrypt.hash(pinCode, 6)
    }

    fun verify(pinCode: String, expected: ByteArray): Boolean {
        return Bcrypt.verify(pinCode, expected)
    }

}
