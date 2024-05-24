package dev.unit6.healthypets.presenter.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.state.DisplayIntent
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.GetPersonalInfoUseCase
import dev.unit6.healthypets.domain.SavePersonalInfoUseCase
import dev.unit6.healthypets.domain.WipeDataUseCase
import dev.unit6.healthypets.presenter.personalInfo.PersonalInfoUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getPersonalInfo: GetPersonalInfoUseCase,
    private val savePersonalInfoUseCase: SavePersonalInfoUseCase,
    private val wipeData: WipeDataUseCase
) : ViewModel() {

    private val _personalInfo = MutableLiveData<UiState<PersonalInfoUi>>(UiState.Loading)
    private val _displayIntent = MutableLiveData<DisplayIntent?>(null)
    val personalInfo: LiveData<UiState<PersonalInfoUi>>
        get() = _personalInfo
    val displayIntent: LiveData<DisplayIntent?>
        get() = _displayIntent

    fun loadPersonalInfo(id: Int) {
        viewModelScope.launch {
            val value = getPersonalInfo(id)
            _personalInfo.value = (UiState.fromDataState(value) {
                it?.toPersonalInfoUi() ?: PersonalInfoUi()
            })
        }
    }

    fun openCamera() {
        _displayIntent.postValue(DisplayIntent.Camera)
    }

    fun openGallery() {
        _displayIntent.postValue(DisplayIntent.Gallery)
    }

    fun savePhoto(urlPhoto: String, id: Int) {
        _displayIntent.postValue(null)
        viewModelScope.launch(Dispatchers.IO) {
            _personalInfo.value.let {
                if (it is UiState.Success) {
                    val value = it.value
                    _personalInfo.postValue(UiState.Success(value.copy(urlPhoto = urlPhoto)))
                    savePersonalInfoUseCase(value.toPersonalInfo(id))
                }
            }
        }
    }

    fun wipeData() {
        viewModelScope.launch(Dispatchers.IO) {
            wipeData.invoke()
        }
    }
}
