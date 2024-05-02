package dev.unit6.healthypets.presenter.mainScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.FragmentMainScreenBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.presenter.auth.AuthFragment
import dev.unit6.healthypets.presenter.auth.AuthViewModel
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
                is UiState.Success -> adapter.submitList(it.value)
                is UiState.Failure -> adapter.submitList(listOf())
                is UiState.Loading -> adapter.submitList(listOf())
            }
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
