package dev.unit6.healthypets.presenter.mainScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.FragmentMainScreenBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.presenter.MainFragmentDirections
import javax.inject.Inject

class MainScreenFragment : Fragment(R.layout.fragment_main_screen), FeedListener {
    private val binding: FragmentMainScreenBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainScreenViewModel by viewModels { viewModelFactory }

    private val adapter = FeedListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.feedList.observe(viewLifecycleOwner) {
            when(it){
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
        initializeButtonAllFeeds()
    }

    private fun initializeButtonAllFeeds() {
        binding.feedListContainer.feedGetAllButtonTextView.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.fullListFeedsFragment)
        }
    }

    private fun initializeRecycler() = with(binding.feedListContainer.feedListRecyclerView) {
        layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        addItemDecoration(HorizontalSpaceItemDecoration(20))
        adapter = this@MainScreenFragment.adapter
    }

    override fun onBuyClick(feed: FeedUi) {
        TODO("Not yet implemented")
    }

    override fun onLikeClick(feed: FeedUi) {
        feed.like = feed.like.not()
    }

    override fun onClickFeed(feed: FeedUi) {
        val action = MainFragmentDirections.actionMainFragmentToFeedInfoFragment(feed.id)
        Navigation.findNavController(requireView()).navigate(action)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment =
            MainScreenFragment()
    }
}
