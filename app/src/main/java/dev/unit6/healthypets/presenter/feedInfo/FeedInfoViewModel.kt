package dev.unit6.healthypets.presenter.feedInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.DislikeFoodUseCase
import dev.unit6.healthypets.domain.GetFeedByIdUseCase
import dev.unit6.healthypets.domain.LikeFoodUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedInfoViewModel @Inject constructor(
    private val getFeedByIdUseCase: GetFeedByIdUseCase,
    private val likeFood: LikeFoodUseCase,
    private val dislikeFood: DislikeFoodUseCase
): ViewModel() {

    private val _feed = MutableLiveData<UiState<Food>>(UiState.Loading)

    val feed: LiveData<UiState<Food>>
        get() = _feed

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

    fun loadFeedById(id: Int) {
        viewModelScope.launch {
            val food = getFeedByIdUseCase(id)
            _feed.postValue(UiState.fromDataState(food))
        }
    }

}
