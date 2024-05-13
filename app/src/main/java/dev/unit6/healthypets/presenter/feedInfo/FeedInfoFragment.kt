package dev.unit6.healthypets.presenter.feedInfo

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.picasso.Picasso
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.model.Food
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.FragmentFeedInfoBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import javax.inject.Inject

class FeedInfoFragment : Fragment(R.layout.fragment_feed_info) {
    private val binding: FragmentFeedInfoBinding by viewBinding()

    private val adapter = FeedOptionsAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FeedInfoViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.feed.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Success -> {
                    initializeUI(it.value)
                }
                is UiState.Failure -> adapter.submitList(listOf())
                is UiState.Loading -> adapter.submitList(listOf())
            }
        }

        arguments?.let {
            it.getInt("feedId").let { id ->
                viewModel.loadFeedById(id)
            }
        }
    }

    private fun initializeUI(food: Food) {
        initializeRecycler()
        initializeFeedHeader(food)
        initializeFeedOptions(food)
        initializeButtonBack()
    }

    private fun initializeButtonBack() {
        binding.buttonBackImageView.setOnClickListener {
            Navigation.findNavController(requireView()).navigateUp()
        }
    }

    private fun initializeFeedHeader(food: Food) {
        setLikeBackground(food.like)
        binding.likeImageView.setOnClickListener {
            food.like = !food.like
            setLikeBackground(food.like)
        }

        binding.nameTextView.text = food.name.trimStart()

        if (food.urlImage != null) {
            Picasso
                .get()
                .load(food.urlImage)
                .placeholder(R.drawable.ic_pet_food)
                .into(binding.feedImageView)
        }
    }

    private fun initializeFeedOptions(food: Food) {
        val listOptions = listOf(
            FeedOptionUi(
                nameStringId = R.string.type,
                value = if (food.dry) getString(R.string.dry) else null
            ),
            FeedOptionUi(
                nameStringId = R.string.compound,
                value = food.composition
            ),
            FeedOptionUi(
                nameStringId = R.string.age_range,
                value = food.ageRange.joinToString(", ", "", ".") {
                    getString(it.stringId)
                }
            ),
            FeedOptionUi(
                nameStringId = R.string.weight_range,
                value = food.weightRange.joinToString(separator = ", ", postfix = ".") {
                    getString(it.stringId)
                }
            ),
            FeedOptionUi(
                nameStringId = R.string.type_protein,
                value = food.typeProtein
            ),
            FeedOptionUi(
                nameStringId = R.string.special_needs,
                value = food.specialNeeds
            ),
            FeedOptionUi(
                nameStringId = R.string.minerals,
                value = food.minerals
            ),
            FeedOptionUi(
                nameStringId = R.string.nutrients,
                value = "${getString(R.string.proteins)} ${food.dryProtein}%, " +
                        "${getString(R.string.fats)} ${food.dryFat}%, " +
                        "${getString(R.string.minerals)} ${food.dryCarbon}%," +
                        "${getString(R.string.dietaryFiber)} ${food.dryCellulose}%."
            ),
            FeedOptionUi(
                nameStringId = R.string.manufacturer_country,
                value = food.countryName
            ),
        )
        adapter.submitList(listOptions)
    }

    private fun setLikeBackground(like: Boolean) {
        if (like) {
            binding.likeImageView.setBackgroundResource(R.drawable.like_fill_picture)
        } else {
            binding.likeImageView.setBackgroundResource(R.drawable.like_empty_picture)
        }
    }

    private fun initializeRecycler() = with(binding.feedOptionsRecyclerView) {
        layoutManager = LinearLayoutManager(
            context
        )
        addItemDecoration(createItemDecorator())
        adapter = this@FeedInfoFragment.adapter
    }

    private fun createItemDecorator() =
        DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.feed_options_item_decorator)
                ?.let { this@apply.setDrawable(it) }
        }
}
