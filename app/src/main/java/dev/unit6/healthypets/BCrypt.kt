package dev.unit6.healthypets

import com.toxicbakery.bcrypt.Bcrypt

object BCrypt {
    fun hashPinCode(pinCode: String): ByteArray {
        return Bcrypt.hash(pinCode, 2)
    }

    fun verify(pinCode: String, expected: ByteArray): Boolean {
        return Bcrypt.verify(pinCode, expected)
    }

}
