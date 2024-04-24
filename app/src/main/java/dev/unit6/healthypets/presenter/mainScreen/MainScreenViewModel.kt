package dev.unit6.healthypets.presenter.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.state.DataState
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.GetAllFoodsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getAllFoods: GetAllFoodsUseCase
) : ViewModel() {

    private val _feedList = MutableLiveData<UiState<List<FeedUi>>>(UiState.Loading)

    val feedList: LiveData<UiState<List<FeedUi>>>
        get() = _feedList


    fun loadFeedList() {
        viewModelScope.launch {
            val foods = getAllFoods()
            foods.let {
                when(it) {
                    is DataState.Success -> {
                        val result = it.value.map { food ->
                            FeedUi(food.name, food.urlImage, false)
                        }
                        _feedList.postValue(UiState.Success(result.subList(0, 10)))
                    }
                    is DataState.Failure -> {
                        _feedList.postValue(UiState.Failure(it.message))
                    }
                }
            }
        }
    }

}
