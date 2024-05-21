package dev.unit6.healthypets.presenter.personalInfo

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.DateHelper.dateToFloat
import dev.unit6.healthypets.DateHelper.getDateInFormatted
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.model.PersonalInfo
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.FragmentPersonalInfoBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.presenter.mainScreen.MainScreenViewModel
import java.util.Date
import java.util.logging.Logger
import javax.inject.Inject
import javax.xml.datatype.DatatypeConstants.MONTHS

class PersonalInfoFragment : Fragment(R.layout.fragment_personal_info) {
    private val binding: FragmentPersonalInfoBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PersonalInfoViewModel by viewModels { viewModelFactory }

    private val personalInfoNumber = 1

    private val calendar = Calendar.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPersonalInfo(personalInfoNumber)

        viewModel.personalInfo.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    initializeUI(it.value)
                }
                is UiState.Loading -> {}
                is UiState.Failure -> {}
            }
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun initializeUI(personalInfoUi: PersonalInfoUi) {
        initializeSaveButton()
        initializeTextInput(personalInfoUi)
        initializeButtonBack()
    }

    private fun initializeButtonBack() {
        binding.buttonBackImageView.setOnClickListener {
            goBack()
        }
    }

    private fun initializeSaveButton() {
        binding.saveButton.setOnClickListener {
            viewModel.savePersonalInfo(personalInfoNumber)
            goBack()
        }
    }

    private fun goBack() {
        Navigation.findNavController(requireView()).popBackStack()
    }

    private fun initializeTextInput(personalInfoUi: PersonalInfoUi) {
        binding.nameEditText.setText(personalInfoUi.name)
        binding.nameEditText.doOnTextChanged { text, _, _, _ ->
            personalInfoUi.name = text.toString()
            viewModel.setPersonalInfo(personalInfoUi)
        }

        binding.surnameEditText.setText(personalInfoUi.surname)
        binding.surnameEditText.doOnTextChanged { text, _, _, _ ->
            personalInfoUi.surname = text.toString()
            viewModel.setPersonalInfo(personalInfoUi)
        }

        binding.dateBirthEditText.setText(personalInfoUi.dateBirth)
        binding.dateBirthEditText.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(), { _, year, monthOfYear, dayOfMonth ->
                    val date = "$dayOfMonth.$monthOfYear.$year"
                    personalInfoUi.dateBirth = date
                    viewModel.setPersonalInfo(personalInfoUi)
                    binding.dateBirthEditText.setText(date)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }

        binding.mailEditText.setText(personalInfoUi.mail)
        binding.mailEditText.doOnTextChanged { text, _, _, _ ->
            personalInfoUi.mail = text.toString()
            viewModel.setPersonalInfo(personalInfoUi)
        }

        binding.phoneNumberEditText.setText(personalInfoUi.phoneNumber)
        binding.phoneNumberEditText.doOnTextChanged { text, _, _, _ ->
            personalInfoUi.phoneNumber = text.toString()
            viewModel.setPersonalInfo(personalInfoUi)
        }

    }

}
