package com.demo.tandem.ui.tandem

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.domain.CommunityMember
import com.demo.tandem.R
import com.demo.tandem.databinding.TandemActivityBinding
import com.demo.tandem.ui.base.BaseActivity
import com.demo.tandem.ui.common.PaginationRecyclerViewScrollListener
import com.demo.tandem.ui.common.RecyclerViewPaginationHandler
import com.demo.tandem.viewmodel.CommunityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TandemActivity : BaseActivity<TandemActivityBinding>(R.layout.tandem_activity),
    RecyclerViewPaginationHandler {

    companion object {
        private const val numberOfMembersPerPage = 20
    }

    override var pagingInProgress: Boolean = false
    override var currentPage: Int = 1
    override var isLastPage: Boolean = false
    override val pagingAction: () -> Unit = { getCommunityMembers() }

    private val communityViewModel: CommunityViewModel by viewModels()
    private val adapter = CommunityMembersRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        getCommunityMembers()
    }

    private fun observeViewModel() {
        communityViewModel.getCommunityMembersSuccess.observe(this) {
            adapter.appendAll(it)
            updatePagingParameters(it)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager?.let {
            if (it is LinearLayoutManager) binding.recyclerView.addOnScrollListener(
                PaginationRecyclerViewScrollListener(it, this)
            )
        }
        adapter.setItemClickListener { _, position, communityMember ->
            toggleFavoriteMemberAt(communityMember, position)
        }
    }

    private fun toggleFavoriteMemberAt(communityMember: CommunityMember, position: Int) {
        communityViewModel.toggleFavorite(communityMember)
        val toggleFavorite = communityMember.isFavorite?.not()
        adapter.replaceItemAt(communityMember.apply {
            isFavorite = toggleFavorite
        }, position)
    }

    private fun getCommunityMembers() {
        if (currentPage > 1) togglePagingProgress()
        communityViewModel.getCommunityMembers(currentPage)
    }

    private fun updatePagingParameters(items: List<CommunityMember>) {
        currentPage++;
        if (items.size < numberOfMembersPerPage) isLastPage = true
        if (currentPage > 2) togglePagingProgress()
    }

    private fun togglePagingProgress() {
        pagingInProgress = !pagingInProgress
        binding.isPagingInProgress = pagingInProgress
    }
}