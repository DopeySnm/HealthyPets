package dev.unit6.healthypets.presenter.mainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FeedItemBinding

class FeedListAdapter(
    private val onBuyClick: (FeedUi) -> Unit,
    private val onLikeClick: (FeedUi) -> Unit,
) : RecyclerView.Adapter<FeedListAdapter.FeedListViewHolder>() {

    private val list = mutableListOf<FeedUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FeedItemBinding.inflate(layoutInflater, parent, false)
        return FeedListViewHolder(binding, onBuyClick, onLikeClick)
    }

    fun submitList(list: List<FeedUi>) = with(this.list) {
        clear()
        addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class FeedListViewHolder(
        private val binding: FeedItemBinding,
        private val onBuyClick: (FeedUi) -> Unit,
        private val onLikeClick: (FeedUi) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feed: FeedUi) {
            binding.nameFeedTextView.text = feed.name

            setLikeBackground(feed.like)

            binding.buyButton.setOnClickListener {
                onBuyClick(feed)
            }

            binding.likeImageView.setOnClickListener {
                onLikeClick(feed)
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

    }

}
