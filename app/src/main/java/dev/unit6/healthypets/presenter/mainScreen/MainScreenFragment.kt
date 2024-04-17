package dev.unit6.healthypets.presenter.mainScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FragmentMainScreenBinding
import dev.unit6.healthypets.presenter.auth.AuthFragment

class MainScreenFragment : Fragment(R.layout.fragment_main_screen), FeedListener {
    private val binding: FragmentMainScreenBinding by viewBinding()

    private val viewModel: MainScreenViewModel = MainScreenViewModel()

    private val adapter = FeedListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.feedList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadFeedList()

        initializeUI()
    }

    private fun initializeUI() {
        initializeRecycler()
    }

    private fun initializeRecycler() = with(binding.feedListContainer.feedListRecyclerView) {
        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapter = this@MainScreenFragment.adapter
    }

    override fun onBuyClick(feed: FeedUi) {
        TODO("Not yet implemented")
    }

    override fun onLikeClick(feed: FeedUi) {
        feed.like = feed.like.not()
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment =
            MainScreenFragment()
    }
}
