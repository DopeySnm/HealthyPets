package dev.unit6.healthypets.presenter.profile

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
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
                action = { onClickMyPets() }
            ),
            ProfileOptionUi(
                name = getString(R.string.payment_method),
                action = { onClickPaymentMethod() }
            ),
            ProfileOptionUi(
                name = getString(R.string.settings),
                action = { onClickSettings() }
            ),
            ProfileOptionUi(
                name = getString(R.string.support),
                action = { onClickSupport() }
            ),
            ProfileOptionUi(
                name = getString(R.string.about_app),
                action = { onClickAboutApp() }
            ),
            ProfileOptionUi(
                name = getString(R.string.exit),
                action = { onClickExit() }
            )
        )

        adapter.submitList(options)
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

    private fun onClickPersonalInfo() {
        TODO()
    }

    private fun onClickMyPets() {
        TODO()
    }

    private fun onClickPaymentMethod() {
        TODO()
    }

    private fun onClickSettings() {
        TODO()
    }

    private fun onClickSupport() {
        TODO()
    }

    private fun onClickAboutApp() {
        TODO()
    }

    private fun onClickExit() {
        TODO()
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment =
            ProfileFragment()
    }
}
