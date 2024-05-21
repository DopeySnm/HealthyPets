package dev.unit6.healthypets.presenter.personalInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.GetPersonalInfoUseCase
import dev.unit6.healthypets.domain.SavePersonalInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonalInfoViewModel @Inject constructor(
    private val getPersonalInfo: GetPersonalInfoUseCase,
    private val savePersonalInfoUseCase: SavePersonalInfoUseCase
) : ViewModel() {

    private val _personalInfo = MutableLiveData<UiState<PersonalInfoUi>>(UiState.Loading)
    val personalInfo: LiveData<UiState<PersonalInfoUi>>
        get() = _personalInfo

    private val _newPersonalInfo = MutableLiveData<UiState<PersonalInfoUi>>(UiState.Loading)

    fun loadPersonalInfo(id: Int) {
        viewModelScope.launch {
            val value = getPersonalInfo(id)
            _personalInfo.postValue(UiState.fromDataState(value) {
                it?.toPersonalInfoUi() ?: PersonalInfoUi()
            })
        }
    }

    fun savePersonalInfo(id: Int) {
        viewModelScope.launch {
            _newPersonalInfo.value.let {
                if (it is UiState.Success) {
                    savePersonalInfoUseCase(it.value.toPersonalInfo(id))
                }
            }
        }
    }

    fun setPersonalInfo(personalInfoUi: PersonalInfoUi) {
        _newPersonalInfo.postValue(UiState.Success(personalInfoUi))
    }

}
