package dev.unit6.healthypets.presenter.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.picasso.Picasso
import dev.unit6.healthypets.R
import dev.unit6.healthypets.data.state.DisplayIntent
import dev.unit6.healthypets.data.state.UiState
import dev.unit6.healthypets.databinding.FragmentProfileBinding
import dev.unit6.healthypets.di.appComponent
import dev.unit6.healthypets.di.viewModel.ViewModelFactory
import dev.unit6.healthypets.presenter.MainFragmentDirections
import dev.unit6.healthypets.presenter.personalInfo.PersonalInfoUi
import dev.unit6.healthypets.utils.CameraUtils
import dev.unit6.healthypets.utils.ImageUtils
import java.io.File
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    private val adapter = ProfileOptionsAdapter()

    private val personalInfoNumber = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var photo: File? = null
        val pickImage =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
                uri?.let {
                    setProfilePhoto(it)
                }
            }
        val takePicture =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
                if (success && photo != null) {
                    val context = requireContext()
                    val photoUri = FileProvider.getUriForFile(
                        context,
                        "com.example.android.fileprovider",
                        photo!!
                    )
                    setProfilePhoto(photoUri)
                    ImageUtils.savePhotoToGallery(
                        photo!!.absolutePath,
                        context.contentResolver
                    )
                } else {
                    Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        viewModel.displayIntent.observe(viewLifecycleOwner) {
            when (it) {
                DisplayIntent.Gallery -> pickImage.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )

                DisplayIntent.Camera -> {
                    photo = CameraUtils.takePicture(requireContext())
                    val photoUri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.example.android.fileprovider",
                        photo!!
                    )
                    takePicture.launch(photoUri)
                }

                else -> {}
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPersonalInfo(personalInfoNumber)

        viewModel.personalInfo.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    initialize(it.value)
                }

                is UiState.Loading -> {}
                is UiState.Failure -> {}
            }
        }

        initializeUI()
    }

    private fun initializeUI() {
        initializeRecycler()
        initializeProfileOptions()
        initializeProfilePhotoSelector()
    }

    private fun initialize(personalInfoUi: PersonalInfoUi) {
        binding.userNameTextView.text = personalInfoUi.name
        if (personalInfoUi.urlPhoto != "") {
            Picasso
                .get()
                .load(personalInfoUi.urlPhoto)
                .placeholder(R.drawable.profile_empty_photo_picture)
                .into(binding.profilePhotoImageView)
        }
    }

    private fun initializeProfilePhotoSelector() {
        binding.profilePhotoSelectorImageView.setOnClickListener {
            showImagePickerDialog()
        }
    }

    private fun showImagePickerDialog() {
        val options = arrayOf(
            getString(R.string.camera),
            getString(R.string.gallery)
        )
        val builder = AlertDialog.Builder(requireContext())
        builder.setItems(options) { _, index ->
            when (index) {
                0 -> {
                    viewModel.openCamera()
                }

                1 -> {
                    viewModel.openGallery()
                }
            }
        }
        builder.show()
    }

    private fun setProfilePhoto(uri: Uri) {
        viewModel.savePhoto(uri.toString(), personalInfoNumber)
    }

    private fun initializeProfileOptions() {
        val options = listOf(
            ProfileOptionUi(
                name = getString(R.string.personal_info),
                action = { onClickPersonalInfo() }
            ),
            ProfileOptionUi(
                name = getString(R.string.my_pets),
                action = { }
            ),
            ProfileOptionUi(
                name = getString(R.string.payment_method),
                action = { }
            ),
            ProfileOptionUi(
                name = getString(R.string.settings),
                action = { }
            ),
            ProfileOptionUi(
                name = getString(R.string.support),
                action = { }
            ),
            ProfileOptionUi(
                name = getString(R.string.about_app),
                action = { }
            ),
            ProfileOptionUi(
                name = getString(R.string.exit),
                action = { onClickExit() }
            )
        )

        adapter.submitList(options)
    }

    private fun onClickPersonalInfo() {
        Navigation.findNavController(requireView()).navigate(R.id.personalInfoFragment)
    }

    private fun onClickExit() {
        viewModel.wipeData()
        val action = MainFragmentDirections.actionMainFragmentToAuthFragment()
        Navigation.findNavController(requireView()).navigate(action)
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

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment =
            ProfileFragment()
    }
}
