package dev.unit6.healthypets.presenter.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.GetPersonalInfoUseCase
import dev.unit6.healthypets.presenter.personalInfo.PersonalInfoUi
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getPersonalInfo: GetPersonalInfoUseCase
) : ViewModel() {

    private val _personalInfo = MutableLiveData<UiState<PersonalInfoUi>>(UiState.Loading)
    val personalInfo: LiveData<UiState<PersonalInfoUi>>
        get() = _personalInfo

    fun loadPersonalInfo(id: Int) {
        viewModelScope.launch {
            val value = getPersonalInfo(id)
            _personalInfo.value = (UiState.fromDataState(value) {
                it?.toPersonalInfoUi() ?: PersonalInfoUi()
            })
        }
    }

}
