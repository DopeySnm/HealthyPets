package dev.unit6.healthypets.presenter.mainScreen

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration (
    private val spaceSize: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != RecyclerView.NO_POSITION && position != 0) {
            outRect.left = spaceSize
        }
    }
}
