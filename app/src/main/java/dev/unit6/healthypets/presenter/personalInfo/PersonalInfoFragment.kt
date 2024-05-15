package dev.unit6.healthypets.presenter.personalInfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment(R.layout.fragment_personal_info) {
    private val binding: FragmentPersonalInfoBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
