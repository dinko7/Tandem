package com.demo.tandem.ui.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationRecyclerViewScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val handler: RecyclerViewPaginationHandler
) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        with(handler) {
            if (!pagingInProgress && !isLastPage && isLastVisibleItem()) pagingAction()
        }
    }

    private fun isLastVisibleItem(): Boolean {
        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val pastVisibleItemsPosition: Int = layoutManager.findFirstVisibleItemPosition()
        return visibleItemCount + pastVisibleItemsPosition >= totalItemCount && pastVisibleItemsPosition >= 0
    }
}