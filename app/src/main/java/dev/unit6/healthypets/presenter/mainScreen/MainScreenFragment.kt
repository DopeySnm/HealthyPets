package dev.unit6.healthypets.presenter.mainScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val binding: FragmentMainScreenBinding by viewBinding()

    private val adapter = FeedListAdapter(
        ::onFeedBuyItemClick,
        ::onFeedLikeItemClick
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.submitList(
            listOf(
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
        )

        initializeUI()
    }

    private fun initializeUI() {
        initializeRecycler()
    }

    private fun initializeRecycler() = with(binding.feedListRecyclerView) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = this@MainScreenFragment.adapter
    }

    private fun onFeedBuyItemClick(entry: FeedUi) {

    }

    private fun onFeedLikeItemClick(entry: FeedUi) {
        entry.like = entry.like.not()
    }

}
