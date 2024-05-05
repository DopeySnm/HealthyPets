package dev.unit6.healthypets.presenter.feedInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.domain.GetFeedByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedInfoViewModel @Inject constructor(
    private val getFeedByIdUseCase: GetFeedByIdUseCase
): ViewModel() {

    private val _feed = MutableLiveData<UiState<Food>>(UiState.Loading)

    val feed: LiveData<UiState<Food>>
        get() = _feed

    fun loadFeedById(id: Int) {
        viewModelScope.launch {
            val food = getFeedByIdUseCase(id)
            _feed.postValue(UiState.fromDataState(food))
        }
    }

}
