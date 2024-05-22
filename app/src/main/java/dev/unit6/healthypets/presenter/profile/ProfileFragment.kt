package dev.unit6.healthypets.presenter.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.FragmentProfileBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.presenter.personalInfo.PersonalInfoUi
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    private val adapter = ProfileOptionsAdapter()

    private val personalInfoNumber = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPersonalInfo(personalInfoNumber)

        viewModel.personalInfo.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Success -> {
                    initialize(it.value)
                }
                is UiState.Loading -> {}
                is UiState.Failure -> {}
            }
        }

        initializeUI()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun initializeUI() {
        initializeRecycler()
        initializeProfileOptions()
        initializeProfilePhotoSelector()
    }

    private fun initialize(personalInfoUi: PersonalInfoUi) {
        binding.userNameTextView.text = personalInfoUi.name
    }

    private fun initializeProfilePhotoSelector() {
        binding.profilePhotoSelectorImageView.setOnClickListener {
            TODO()
        }
    }

    private fun initializeProfileOptions() {
        val options = listOf(
            ProfileOptionUi(
                name = getString(R.string.personal_info),
                action = { onClickPersonalInfo() }
            ),
            ProfileOptionUi(
                name = getString(R.string.my_pets),
                action = {  }
            ),
            ProfileOptionUi(
                name = getString(R.string.payment_method),
                action = {  }
            ),
            ProfileOptionUi(
                name = getString(R.string.settings),
                action = {  }
            ),
            ProfileOptionUi(
                name = getString(R.string.support),
                action = { }
            ),
            ProfileOptionUi(
                name = getString(R.string.about_app),
                action = {  }
            ),
            ProfileOptionUi(
                name = getString(R.string.exit),
                action = {  }
            )
        )

        adapter.submitList(options)
    }

    private fun onClickPersonalInfo() {
        Navigation.findNavController(requireView()).navigate(R.id.personalInfoFragment)
    }

    private fun initializeRecycler() = with(binding.profileOptionsRecyclerView) {
        layoutManager = LinearLayoutManager(
            context
        )
        addItemDecoration(createItemDecorator())
        adapter = this@ProfileFragment.adapter
    }

    private fun createItemDecorator() =
        DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.profile_options_item_decorator)
                ?.let { this@apply.setDrawable(it) }
        }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment =
            ProfileFragment()
    }
}
