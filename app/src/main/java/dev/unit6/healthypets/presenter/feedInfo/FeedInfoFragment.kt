package dev.unit6.healthypets.presenter.feedInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.unit6.healthypets.R
import dev.unit6.healthypets.databinding.FragmentFeedInfoBinding

class FeedInfoFragment : Fragment(R.layout.fragment_feed_info) {
    private val binding: FragmentFeedInfoBinding by viewBinding()

    private val adapter = FeedOptionsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}
