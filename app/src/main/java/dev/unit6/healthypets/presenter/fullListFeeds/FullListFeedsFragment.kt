package dev.unit6.healthypets.presenter.fullListFeeds

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.FragmentFullListFeedsBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.presenter.mainScreen.FeedListAdapter
import dev.unit6.healthypets.presenter.mainScreen.FeedListener
import dev.unit6.healthypets.presenter.mainScreen.FeedUi
import dev.unit6.healthypets.presenter.mainScreen.MainScreenViewModel
import javax.inject.Inject

class FullListFeedsFragment : Fragment(R.layout.fragment_full_list_feeds), FeedListener {

    private val binding: FragmentFullListFeedsBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainScreenViewModel by viewModels { viewModelFactory }

    private val adapter = FeedListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.feedList.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Success -> {
                    val result = it.value.map { food ->
                        food.toFeedUI()
                    }
                    adapter.submitList(result)
                }
                is UiState.Failure -> adapter.submitList(listOf())
                is UiState.Loading -> adapter.submitList(listOf())
            }
        }

        viewModel.loadFeedList()

        initializeUI()
    }

    private fun initializeUI() {
        initializeRecycler()
        initializeButtonBack()
    }

    private fun initializeButtonBack() {
        binding.buttonBackImageView.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
    }

    private fun initializeRecycler() = with(binding.feedsListRecyclerView) {
        layoutManager = GridLayoutManager(
            context,
            2
        )
        addItemDecoration(GridSpacingItemDecoration(2, 60, false))
        adapter = this@FullListFeedsFragment.adapter
    }


    override fun onBuyClick(feed: FeedUi) {
        TODO("Not yet implemented")
    }

    override fun onLikeClick(feed: FeedUi) {
        feed.favorite = feed.favorite.not()
        if (feed.favorite) {
            viewModel.saveFavoriteFood(feed.id)
        } else {
            viewModel.deleteFavoriteFood(feed.id)
        }
    }

    override fun onClickFeed(feed: FeedUi) {
        val action = FullListFeedsFragmentDirections.actionFullListFeedsFragmentToFeedInfoFragment(feed.id)
        Navigation.findNavController(requireView()).navigate(action)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment =
            FullListFeedsFragment()
    }
}
