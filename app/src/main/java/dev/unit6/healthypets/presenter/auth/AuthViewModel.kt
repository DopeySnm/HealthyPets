package dev.unit6.healthypets.presenter.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.PinCodeHelp
import dev.unit6.healthypets.data.state.DataState
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.DoNotSetPinCodeUseCase
import dev.unit6.healthypets.domain.GetPinCodeHashUseCase
import dev.unit6.healthypets.domain.SavePinCodeHashUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel
@Inject constructor(
    private val savePinCodeHashUseCase: SavePinCodeHashUseCase,
    private val getPinCodeHashUseCase: GetPinCodeHashUseCase,
    private val doNotSetPinCodeUseCase: DoNotSetPinCodeUseCase
) : ViewModel() {

    private val _pinCodeLength = MutableLiveData<Int>()
    val pinCodeLength: LiveData<Int>
        get() = _pinCodeLength

    private val _pinCode = MutableLiveData("")

    private val _repeatPinCode = MutableLiveData("")

    private val _pinCodeState = MutableLiveData<UiState<PinCodeState>>(UiState.Loading)
    val pinCodeState: LiveData<UiState<PinCodeState>>
        get() = _pinCodeState

    private val pinCodeHelp = PinCodeHelp()

    fun checkPinCodeStatus(id: Int) {
        viewModelScope.launch {
            val pinCodeHash = getPinCodeHashUseCase(id)
            pinCodeHash.let {
                when (it) {
                    is DataState.Success -> {
                        if (!it.value.isProtected) {
                            _pinCodeState.postValue(UiState.Success(PinCodeState.Register))
                        } else if (it.value.pinCodeHash == null) {
                            _pinCodeState.postValue(UiState.Success(PinCodeState.Access))
                        } else {
                            _pinCodeState.postValue(UiState.Success(PinCodeState.Enter))
                        }
                    }

                    is DataState.Failure -> {
                        _pinCodeState.postValue(UiState.Failure("Verify failure"))
                    }
                }
            }
        }

    }

    private fun setPinCode(pinCode: String, liveData: MutableLiveData<String>): Boolean {
        var isFilled = false
        liveData.value?.let {
            if (it.length < 4) {
                val value = it + pinCode
                _pinCodeLength.postValue(value.length)
                liveData.value = value
                if (value.length == 4) {
                    isFilled = true
                }
            }
        } ?: run {
            liveData.postValue(pinCode)
            _pinCodeLength.postValue(pinCode.length)
        }
        return isFilled
    }

    fun trySetPinCode(id: Int, pinCode: String) {
        if (pinCode.length != 1) {
            throw IllegalArgumentException("Method accepts a string with one PIN code character")
        }

        _pinCodeState.value?.let {
            if (it is UiState.Success) {
                when (it.value) {
                    PinCodeState.Register -> {
                        if (setPinCode(pinCode, _pinCode)) {
                            _pinCodeState.postValue(UiState.Success(PinCodeState.Repeat))
                        }
                    }

                    PinCodeState.Repeat -> {
                        if (setPinCode(pinCode, _repeatPinCode)) {
                            savePinCode(id)
                        }
                    }

                    PinCodeState.Enter -> {
                        if (setPinCode(pinCode, _pinCode)) {
                            verify(id)
                        }
                    }

                    PinCodeState.Wrong -> {
                        _pinCodeState.postValue(UiState.Success(PinCodeState.Enter))
                        _pinCode.value = ""
                        _pinCodeLength.postValue(0)
                        setPinCode(pinCode, _pinCode)
                    }

                    PinCodeState.NotMatch -> {
                        _pinCodeState.postValue(UiState.Success(PinCodeState.Register))
                        _repeatPinCode.value = ""
                        _pinCode.value = ""
                        _pinCodeLength.postValue(0)
                        setPinCode(pinCode, _pinCode)
                    }

                    else -> _pinCodeState.postValue(UiState.Failure(""))
                }
            }
        }
    }

    fun emptyPinCode() {
        viewModelScope.launch {
            doNotSetPinCodeUseCase()
            _pinCodeState.postValue(UiState.Success(PinCodeState.Access))
        }
    }

    private fun savePinCode(id: Int) {
        viewModelScope.launch {
            _pinCode.value?.let { pinCode ->
                _repeatPinCode.value?.let { repeatPinCode ->
                    if (repeatPinCode == pinCode) {
                        val pinCodeHash = pinCodeHelp.hashPinCode(pinCode)
                        savePinCodeHashUseCase(pinCodeHash, id)
                        _pinCodeState.postValue(UiState.Success(PinCodeState.Access))
                    } else {
                        _pinCodeState.postValue(UiState.Success(PinCodeState.NotMatch))
                    }
                }
            }
        }
    }

    private fun verify(id: Int) {
        viewModelScope.launch {
            _pinCode.value?.let { pinCode ->
                when (val pinCodeHash = getPinCodeHashUseCase(id)) {
                    is DataState.Success -> {
                        val verify = pinCodeHelp.verify(pinCode, pinCodeHash.value.pinCodeHash!!)
                        if (verify) {
                            _pinCodeState.postValue(UiState.Success(PinCodeState.Access))
                        } else {
                            _pinCodeState.postValue(UiState.Success(PinCodeState.Wrong))
                        }
                    }

                    is DataState.Failure -> {
                        _pinCodeState.postValue(UiState.Failure("Verify failure"))
                    }
                }
            }
        }
    }

    private fun backSpacePinCode(pinCode: MutableLiveData<String>) {
        pinCode.value?.let {
            val value = it.dropLast(1)
            pinCode.postValue(value)
            _pinCodeLength.postValue(value.length)
        }
    }

    fun resettingRegistration() {
        _pinCodeState.postValue(UiState.Success(PinCodeState.Register))
        _pinCodeLength.postValue(0)
        _pinCode.postValue("")
        _repeatPinCode.postValue("")
    }

    fun tryBackSpacePinCode() {
        _pinCodeState.value.let {
            if (it is UiState.Success) {
                when (it.value) {
                    PinCodeState.Repeat -> {
                        backSpacePinCode(_repeatPinCode)
                    }

                    else -> backSpacePinCode(_pinCode)
                }
            }
        }

    }
}
