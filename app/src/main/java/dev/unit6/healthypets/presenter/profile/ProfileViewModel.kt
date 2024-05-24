package dev.unit6.healthypets.presenter.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.GetPersonalInfoUseCase
import dev.unit6.healthypets.domain.SavePersonalInfoUseCase
import dev.unit6.healthypets.presenter.personalInfo.PersonalInfoUi
import dev.unit6.healthypets.domain.WipeDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getPersonalInfo: GetPersonalInfoUseCase,
    private val savePersonalInfoUseCase: SavePersonalInfoUseCase,
    private val wipeData: WipeDataUseCase
) : ViewModel() {

    private val _personalInfo = MutableLiveData<UiState<PersonalInfoUi>>(UiState.Loading)
    val personalInfo: LiveData<UiState<PersonalInfoUi>>
        get() = _personalInfo

    private val _newPersonalInfo = MutableLiveData<UiState<PersonalInfoUi>>(UiState.Loading)

    fun loadPersonalInfo(id: Int) {
        viewModelScope.launch {
            val value = getPersonalInfo(id)
            _personalInfo.value = (UiState.fromDataState(value) {
                it?.toPersonalInfoUi() ?: PersonalInfoUi()
            })
        }
    }

    fun savePhoto(urlPhoto: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _personalInfo.value.let {
                if (it is UiState.Success) {
                    val value = it.value
                    value.urlPhoto = urlPhoto
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
