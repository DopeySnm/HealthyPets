package dev.unit6.healthypets.presenter.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.DislikeFoodUseCase
import dev.unit6.healthypets.domain.GetAllFoodsUseCase
import dev.unit6.healthypets.domain.LikeFoodUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getAllFoods: GetAllFoodsUseCase,
    private val likeFood: LikeFoodUseCase,
    private val dislikeFood: DislikeFoodUseCase
) : ViewModel() {

    private val _feedList = MutableLiveData<UiState<List<Food>>>(UiState.Loading)

    val feedList: LiveData<UiState<List<Food>>>
        get() = _feedList

    fun saveFavoriteFood(foodId: Int) {
        viewModelScope.launch {
            likeFood.invoke(foodId)
        }
    }

    fun deleteFavoriteFood(foodId: Int) {
        viewModelScope.launch {
            dislikeFood.invoke(foodId)
        }
    }

    fun loadFeedList() {
        viewModelScope.launch {
            val foods = getAllFoods()
            _feedList.postValue(UiState.fromDataState(foods))
        }
    }

}
