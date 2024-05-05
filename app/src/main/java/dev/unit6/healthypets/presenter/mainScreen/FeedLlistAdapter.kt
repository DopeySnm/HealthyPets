package dev.unit6.healthypets.presenter.mainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FeedItemBinding

class FeedListAdapter(
    private val feedListener: FeedListener
) : ListAdapter<FeedUi, FeedListAdapter.FeedListViewHolder>(FeedDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        return FeedListViewHolder.create(parent, feedListener)
    }

    override fun onBindViewHolder(holder: FeedListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FeedListViewHolder(
        private val binding: FeedItemBinding,
        private val feedListener: FeedListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feed: FeedUi) {
            binding.nameFeedTextView.text = feed.name.trimStart()

            if (feed.imageUrl != null) {
                Picasso
                    .get()
                    .load(feed.imageUrl)
                    .placeholder(R.drawable.ic_pet_food)
                    .into(binding.feedImageView)
            } else {
                val drawablePlaceholder =
                    AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_pet_food)
                binding.feedImageView.setImageDrawable(drawablePlaceholder)
            }

            setLikeBackground(feed.like)
            binding.buyButton.setOnClickListener {
                feedListener.onBuyClick(feed)
            }

            binding.root.setOnClickListener {
                feedListener.onClickFeed(feed)
            }

            binding.likeImageView.setOnClickListener {
                feedListener.onLikeClick(feed)
                setLikeBackground(feed.like)
            }
        }

        private fun setLikeBackground(like: Boolean) {
            if (like) {
                binding.likeImageView.setBackgroundResource(R.drawable.like_fill_picture)
            } else {
                binding.likeImageView.setBackgroundResource(R.drawable.like_empty_picture)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                feedListener: FeedListener,
            ): FeedListViewHolder {
                return FeedListViewHolder(
                    FeedItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false),
                    feedListener
                )
            }
        }
    }

    class FeedDiffUtil : DiffUtil.ItemCallback<FeedUi>() {
        override fun areItemsTheSame(oldItem: FeedUi, newItem: FeedUi): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FeedUi, newItem: FeedUi): Boolean {
            return oldItem == newItem
        }

    }
}
