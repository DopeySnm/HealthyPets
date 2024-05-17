package dev.unit6.healthypets.presenter

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FragmentMainBinding
import dev.unit6.healthypets.presenter.auth.AuthFragment
import dev.unit6.healthypets.presenter.mainScreen.MainScreenFragment
import dev.unit6.healthypets.presenter.profile.ProfileFragment

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigation.setOnItemSelectedListener {
            changeTab(it.itemId)
            true
        }
        changeTab(R.id.select_main)
    }

    private fun changeTab(@IdRes id: Int){
        val navHostId = binding.navHost.id
        val transaction = childFragmentManager.beginTransaction()
        when (id) {
            R.id.select_main -> {
                transaction.replace(navHostId, MainScreenFragment.newInstance())
            }
            R.id.select_profile -> {
                transaction.replace(navHostId, ProfileFragment.newInstance())
            }
        }
        transaction.commit()
    }
}
