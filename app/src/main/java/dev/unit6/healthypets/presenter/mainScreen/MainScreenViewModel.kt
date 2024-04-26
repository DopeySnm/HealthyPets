package dev.unit6.healthypets.presenter.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.state.DataState
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.GetAllFoodsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getAllFoods: GetAllFoodsUseCase
) : ViewModel() {

    private val _feedList = MutableLiveData<UiState<List<Food>>>(UiState.Loading)

    val feedList: LiveData<UiState<List<Food>>>
        get() = _feedList


    fun loadFeedList() {
        viewModelScope.launch {
            val foods = getAllFoods()
            foods.let {
                _feedList.postValue(UiState.fromDataState(it))
            }
        }
    }

}
