package dev.unit6.healthypets.presenter.profile

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()

    private val adapter = ProfileOptionsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        initializeRecycler()
        initializeProfileOptions()
        initializeProfilePhotoSelector()
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
