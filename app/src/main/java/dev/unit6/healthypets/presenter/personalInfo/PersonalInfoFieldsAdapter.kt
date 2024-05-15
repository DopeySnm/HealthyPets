package dev.unit6.healthypets.presenter.personalInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import dev.unit6.healthypets.databinding.PersonalInfoFieldItemBinding

class PersonalInfoFieldsAdapter :
    RecyclerView.Adapter<PersonalInfoFieldsAdapter.PersonalInfoFieldsViewHolder>() {

    private val list = mutableListOf<PersonalInfoFieldUi>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonalInfoFieldsViewHolder {
        return PersonalInfoFieldsViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(list: List<PersonalInfoFieldUi>) = with(this.list) {
        addAll(list)
    }

    override fun onBindViewHolder(holder: PersonalInfoFieldsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class PersonalInfoFieldsViewHolder(
        private val binding: PersonalInfoFieldItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(fieldUi: PersonalInfoFieldUi) = binding.apply {
            binding.materialTextView.text = fieldUi.name
            binding.textInputEditText.hint = fieldUi.hint
            binding.textInputEditText.doOnTextChanged { text, start, before, count ->
                text
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
            ): PersonalInfoFieldsViewHolder {
                return PersonalInfoFieldsViewHolder(
                    PersonalInfoFieldItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }
}
