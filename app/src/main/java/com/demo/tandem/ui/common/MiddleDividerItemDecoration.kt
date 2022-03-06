package com.demo.tandem.ui.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MiddleDividerItemDecoration(context: Context, orientation: Int) :
    DividerItemDecoration(context, orientation) {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val lastPosition = state.itemCount - 1
        if (position != lastPosition)
            super.getItemOffsets(outRect, view, parent, state)
    }
}
