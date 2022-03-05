package com.demo.tandem.ui.common

interface RecyclerViewPaginationHandler {
    var pagingInProgress: Boolean
    var currentPage: Int
    var isLastPage: Boolean
    val pagingAction: () -> Unit
}