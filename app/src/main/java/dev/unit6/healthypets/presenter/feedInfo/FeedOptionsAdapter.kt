package dev.unit6.healthypets.presenter.feedInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FeedOptionItemBinding

class FeedOptionsAdapter : RecyclerView.Adapter<FeedOptionsAdapter.FeedOptionsViewHolder>() {

    private val list = mutableListOf<FeedOptionUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedOptionsViewHolder {
        return FeedOptionsViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(list: List<FeedOptionUi>) = with(this.list) {
        addAll(list)
    }

    override fun onBindViewHolder(holder: FeedOptionsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class FeedOptionsViewHolder(
        private val binding: FeedOptionItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(option: FeedOptionUi) = binding.apply {
            nameTextView.text = root.context.getString(option.nameStringId)

            valueTextView.text = option.value?.let {
                it
            } ?: root.context.getString(R.string.empty_value)
        }

        companion object {
            fun create(
                parent: ViewGroup,
            ): FeedOptionsViewHolder {
                return FeedOptionsViewHolder(
                    FeedOptionItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }
}
