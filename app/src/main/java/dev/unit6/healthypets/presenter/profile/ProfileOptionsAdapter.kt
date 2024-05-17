package dev.unit6.healthypets.presenter.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.ProfileOptionItemBinding
import dev.unit6.healthypets.databinding.ProfileOptionLastItemBinding

class ProfileOptionsAdapter :
    RecyclerView.Adapter<ProfileOptionsAdapter.ProfileOptionsViewHolder>() {

    private val list = mutableListOf<ProfileOptionUi>()

    override fun getItemViewType(position: Int): Int {
        return when(position){
            list.lastIndex -> R.layout.profile_option_last_item
            else -> R.layout.profile_option_item
        }
    }

    fun submitList(list: List<ProfileOptionUi>) = with(this.list) {
        addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileOptionsViewHolder {
        return when(viewType) {
            R.layout.profile_option_item -> ProfileOptionsViewHolder.create(parent)
            R.layout.profile_option_last_item -> ProfileOptionsViewHolder.createLast(parent)
            else -> throw IllegalArgumentException("Invalid ViewType Provider")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProfileOptionsViewHolder, position: Int) {
        when(holder){
            is ProfileOptionsViewHolder.ProfileOptionsMainViewHolder -> holder.bind(list[position])
            is ProfileOptionsViewHolder.ProfileOptionsLastViewHolder -> holder.bind(list[position])
        }
    }

    sealed class ProfileOptionsViewHolder(
        binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        class ProfileOptionsMainViewHolder(
            private val binding: ProfileOptionItemBinding
        ) : ProfileOptionsViewHolder(binding) {
            fun bind(profileOptionUi: ProfileOptionUi) {
                binding.nameTextView.text = profileOptionUi.name

                binding.root.setOnClickListener {
                    profileOptionUi.action.invoke()
                }
            }
        }

        class ProfileOptionsLastViewHolder(
            private val binding: ProfileOptionLastItemBinding
        ): ProfileOptionsViewHolder(binding) {
            fun bind(profileOptionUi: ProfileOptionUi) {
                binding.nameTextView.text = profileOptionUi.name

                binding.root.setOnClickListener {
                    profileOptionUi.action.invoke()
                }
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
            ): ProfileOptionsMainViewHolder {
                return ProfileOptionsMainViewHolder(
                    ProfileOptionItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            fun createLast(
                parent: ViewGroup,
            ): ProfileOptionsLastViewHolder {
                return ProfileOptionsLastViewHolder(
                    ProfileOptionLastItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

        }

    }

}
