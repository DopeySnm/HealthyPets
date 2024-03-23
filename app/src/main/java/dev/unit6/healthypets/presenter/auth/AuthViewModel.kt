package dev.unit6.healthypets.presenter.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _pinCode = MutableLiveData<String>()
    val pinCode : LiveData<String>
        get() = _pinCode

    fun setPinCode(pinCode: String) {
        if (pinCode.length != 1) return
        _pinCode.value?.let {
            if (it.length < 4) {
                val value = it + pinCode
                _pinCode.postValue(value)
            }
        } ?: run {
            _pinCode.postValue(pinCode)
        }
    }

    fun backSpacePinCode() {
        _pinCode.value?.let {
            val value = it.dropLast(1)
            _pinCode.postValue(value)
        }
    }
}
