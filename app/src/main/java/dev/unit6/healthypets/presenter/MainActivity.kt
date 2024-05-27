package dev.unit6.healthypets.presenter

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.state.DataState
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.ActivityMainBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.domain.GetPinCodeHashUseCase
import dev.unit6.healthypets.presenter.auth.AuthViewModel
import dev.unit6.healthypets.presenter.auth.PinCodeState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
//
//    private val viewModel: AuthViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var getPinCodeHashUseCase: GetPinCodeHashUseCase

    private var keep = true

    private val pinCodeNumber = 1

    private val DELAY: Long = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.appComponent.inject(this)

        installSplashScreen().setKeepOnScreenCondition {
//            viewModel.checkPinCodeStatus(pinCodeNumber)
//            viewModel.pinCodeState.observe(this) {
//                when (it) {
//                    is UiState.Success -> {
//                        when(it.value) {
//                            PinCodeState.Access -> {
//                                setStartDestination(R.id.mainFragment)
//                            }
//                            else -> {
//                                setStartDestination(R.id.authFragment)
//                            }
//                        }
//                        val handler = Handler(Looper.getMainLooper())
//                        handler.postDelayed({ keep = false }, DELAY)
//                    }
//                    is UiState.Failure -> {}
//                    is UiState.Loading -> {}
//                }
//            }

            lifecycleScope.launch {
                val result = getPinCodeHashUseCase(pinCodeNumber)

                if (result is DataState.Success) {
                    if (!result.value.isProtected) {
                        setStartDestination(R.id.authFragment)
                    } else if (result.value.pinCodeHash == null) {
                        setStartDestination(R.id.mainFragment)
                    } else {
                        setStartDestination(R.id.authFragment)
                    }
                }
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({ keep = false }, DELAY)
            }
            keep
        }

    }
    private fun setStartDestination(fragmentId: Int) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)
        navGraph.setStartDestination(fragmentId)
        navController.graph = navGraph
    }
}
