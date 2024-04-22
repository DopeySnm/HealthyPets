package dev.unit6.healthypets.presenter.auth

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.FragmentAuthBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import javax.inject.Inject

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val binding: FragmentAuthBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AuthViewModel by viewModels { viewModelFactory }

    private val pinCodeNumber = 1

    private val listKeyBoardButtons by lazy {
        listOf(
            binding.btn0,
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9,
        )
    }

    private val listPinCodePoint by lazy {
        listOf(
            binding.pincodePoint1,
            binding.pincodePoint2,
            binding.pincodePoint3,
            binding.pincodePoint4,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pinCodeLength.observe(viewLifecycleOwner) {
            setPinCodePoint(it)
        }
        viewModel.pinCodeState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> pinCodeViewState(it.value)
                is UiState.Failure -> {}
                is UiState.Loading -> {}
            }
        }
        viewModel.checkPinCodeStatus(pinCodeNumber)
        initializeKeyboard()
    }

    private fun pinCodeViewState(pinCodeState: PinCodeState) {
        when (pinCodeState) {
            PinCodeState.Enter -> enter()
            PinCodeState.Repeat -> repeatPinCode()
            PinCodeState.Register -> register()
            PinCodeState.NotMatch -> notMatchPinCode()
            PinCodeState.Wrong -> wrongPinCode()
            PinCodeState.Access -> goMainScreen()
        }
    }

    private fun initializeKeyboard() {
        listKeyBoardButtons.forEach { btn ->
            btn.setOnClickListener { onDigitClick(btn.text.toString()) }
        }
        binding.btnBackspace.setOnClickListener { onBackspaceClick() }
        binding.buttonBackImageView.setOnClickListener { buttonBack() }
    }

    private fun onDigitClick(digit: String) {
        viewModel.trySetPinCode(pinCodeNumber, digit)
    }

    private fun onBackspaceClick() {
        viewModel.tryBackSpacePinCode()
    }

    private fun setPinCodePoint(pinCodeLength: Int) {
        emptyPinCodePoints()
        if (pinCodeLength < 4) {
            for (i in 0 until pinCodeLength) {
                listPinCodePoint[i].setBackgroundResource(R.drawable.pincode_point_filled)
            }
        }
    }

    private fun buttonBack() {
        viewModel.resettingRegistration()
    }

    private fun register() {
        binding.buttonBackImageView.isEnabled = false
        binding.buttonBackImageView.visibility = View.INVISIBLE

        binding.wrongTextView.visibility = View.INVISIBLE
        binding.buttonTextView.setText(R.string.not_install_code)
        binding.buttonTextView.setOnClickListener {
            viewModel.emptyPinCode()
        }
        binding.helpTextView.setText(R.string.auth_help_come_up_code)
    }

    private fun enter() {
        binding.buttonBackImageView.isEnabled = false
        binding.buttonBackImageView.visibility = View.INVISIBLE

        binding.buttonTextView.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.PINCodeResetFragment)
        }
        binding.wrongTextView.visibility = View.INVISIBLE
        binding.buttonTextView.setText(R.string.can_not_enter)
        binding.helpTextView.setText(R.string.auth_help_enter_сode)
    }

    private fun notMatchPinCode() {
        binding.wrongTextView.setText(R.string.not_match_pin_code)
        wrongPinCodePoints()
    }

    private fun wrongPinCode() {
        binding.wrongTextView.setText(R.string.wrong_pin_code)
        wrongPinCodePoints()
    }

    private fun repeatPinCode() {
        binding.buttonBackImageView.isEnabled = true
        binding.buttonBackImageView.visibility = View.VISIBLE

        binding.helpTextView.setText(R.string.auth_help_repeat_code)
        emptyPinCodePoints()
    }

    private fun emptyPinCodePoints() {
        for (point in listPinCodePoint) {
            point.setBackgroundResource(R.drawable.pincode_point_empty)
        }
    }

    private fun wrongPinCodePoints() {
        binding.wrongTextView.visibility = View.VISIBLE
        for (point in listPinCodePoint) {
            point.setBackgroundResource(R.drawable.pincode_point_wrong)
        }
    }

    private fun goMainScreen() {
        Navigation.findNavController(requireView()).navigate(R.id.mainFragment)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
    companion object {
        @JvmStatic
        fun newInstance(): Fragment =
            AuthFragment()
    }
}
