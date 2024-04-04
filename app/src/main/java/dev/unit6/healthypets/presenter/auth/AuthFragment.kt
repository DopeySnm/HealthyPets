package dev.unit6.healthypets.presenter.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FragmentAuthBinding
import java.util.Stack

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val binding: FragmentAuthBinding by viewBinding()

    private val viewModel: AuthViewModel = AuthViewModel()

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
        viewModel.pinCode.observe(viewLifecycleOwner) { pinCode ->
            setPinCodePoint(pinCode.length)
        }
        initializeKeyboard()
    }

    private fun initializeKeyboard() {
        listKeyBoardButtons.forEach { btn ->
            btn.setOnClickListener { onDigitClick(btn.text.toString()) }
        }
        binding.btnBackspace.setOnClickListener { onBackspaceClick() }
    }

    private fun onDigitClick(digit: String) {
        viewModel.setPinCode(digit)
    }

    private fun onBackspaceClick() {
        viewModel.backSpacePinCode()
    }

    private fun setPinCodePoint(pinCodeLength: Int) {
        emptyPinCodePoints()
        if (pinCodeLength < 4) {
            for (i in 0 until pinCodeLength) {
                listPinCodePoint[i].setBackgroundResource(R.drawable.pincode_point_filled)
            }
        } else {
            Navigation.findNavController(requireView()).navigate(R.id.mainScreenFragment)
        }
    }

    private fun notMatchPinCode() {
        binding.wrongTextView.visibility = View.VISIBLE
        binding.wrongTextView.setText(R.string.not_match_pin_code)
        wrongPinCodePoints()
    }

    private fun wrongPinCode() {
        binding.wrongTextView.visibility = View.VISIBLE
        binding.wrongTextView.setText(R.string.wrong_pin_code)
        wrongPinCodePoints()
    }

    private fun repeatPinCode() {
        binding.helpTextView.setText(R.string.auth_help_repeat_code)
        emptyPinCodePoints()
    }

    private fun emptyPinCodePoints() {
        for (point in listPinCodePoint) {
            point.setBackgroundResource(R.drawable.pincode_point_empty)
        }
    }

    private fun wrongPinCodePoints() {
        for (point in listPinCodePoint) {
            point.setBackgroundResource(R.drawable.pincode_point_wrong)
        }
    }
}
