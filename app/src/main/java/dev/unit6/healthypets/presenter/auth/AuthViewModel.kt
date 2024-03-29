package dev.unit6.healthypets.presenter.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.BCrypt
import dev.unit6.healthypets.data.state.DataState
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.GetPinCodeHashUseCase
import dev.unit6.healthypets.domain.SavePinCodeHashUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel
@Inject constructor(
    val savePinCodeHashUseCase: SavePinCodeHashUseCase,
    val getPinCodeHashUseCase: GetPinCodeHashUseCase
) : ViewModel() {

    private val _pinCode = MutableLiveData<String>()
    val pinCode: LiveData<String>
        get() = _pinCode

    private val _verify = MutableLiveData<UiState<Boolean>>()
    val verify: LiveData<UiState<Boolean>>
        get() = _verify

    fun setPinCode(pinCode: String) {
        if (pinCode.length != 1) {
            throw IllegalArgumentException("Method accepts a string with one PIN code character")
        }

        _pinCode.value?.let {
            if (it.length < 4) {
                val value = it + pinCode
                _pinCode.postValue(value)
            }
        } ?: run {
            _pinCode.postValue(pinCode)
        }
    }

    fun savePinCode() {
        viewModelScope.launch {
            _pinCode.value?.let {
                if (it.length == 4) {
                    val pinCodeHash = BCrypt.hashPinCode(it)
                    savePinCodeHashUseCase.invoke(pinCodeHash)
                }
            }
        }
    }

    fun verify() {
        viewModelScope.launch {
            _pinCode.value?.let { pinCode ->
                val pinCodeHash = getPinCodeHashUseCase()
                pinCodeHash.let {
                    when(it) {
                        is DataState.Success -> {
                            val verify = BCrypt.verify(pinCode, it.value)
                            _verify.postValue(UiState.Success(verify))
                        }
                        is DataState.Failure -> {
                            _verify.postValue(UiState.Failure("Verify failure"))
                        }
                    }
                }
            }
        }
    }

    fun backSpacePinCode() {
        _pinCode.value?.let {
            val value = it.dropLast(1)
            _pinCode.postValue(value)
        }
    }
}
