package dev.unit6.healthypets.presenter.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {

    private val _feedList = MutableLiveData<List<FeedUi>>()

    val feedList: LiveData<List<FeedUi>>
        get() = _feedList


    fun loadFeedList() {
        val testFeedList = listOf(
            FeedUi(
                name = "Royal Canin Golden Retriever Adult, 3 кг",
                false
            ),
            FeedUi(
                name = "Hills Adult 1-6, 10 кг",
                true
            ),
            FeedUi(
                name = "Royal Canin Golden Retriever Adult, 3 кг",
                false
            )
        )
        _feedList.postValue(testFeedList)
    }

}
