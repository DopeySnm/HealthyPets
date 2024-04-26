package dev.unit6.healthypets.presenter.feedInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.unit6.healthypets.databinding.FeedOptionItemBinding

class FeedOptionsAdapter(

) : ListAdapter<FeedOptionUi, FeedOptionsAdapter.FeedOptionsViewHolder>(FeedOptionsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedOptionsViewHolder {
        return FeedOptionsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FeedOptionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FeedOptionsViewHolder(
        private val binding: FeedOptionItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(option: FeedOptionUi) {
            binding.nameTextView.text = option.name
            binding.valueTextView.text = option.value
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

    class FeedOptionsDiffUtil : DiffUtil.ItemCallback<FeedOptionUi>() {
        override fun areItemsTheSame(oldItem: FeedOptionUi, newItem: FeedOptionUi): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FeedOptionUi, newItem: FeedOptionUi): Boolean {
            return oldItem == newItem
        }
    }
}
