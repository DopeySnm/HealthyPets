package dev.unit6.healthypets.presenter.mainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.unit6.healthypets.databinding.FeedItemBinding

class FeedListAdapter(
    private val onItemClick: (FeedUi) -> Unit,
) : RecyclerView.Adapter<FeedListAdapter.FeedListViewHolder>() {

    private val list = mutableListOf<FeedUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FeedItemBinding.inflate(layoutInflater, parent, false)
        return FeedListViewHolder(binding, onItemClick)
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
        private val onItemClick: (FeedUi) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(feed: FeedUi){
            binding.nameFeedTextView.text = feed.name

            binding.buyButton.setOnClickListener  {
                onItemClick(feed)
            }
        }
    }

}
