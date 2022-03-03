package com.demo.tandem.ui.tandem

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.demo.domain.CommunityMember
import com.demo.tandem.R
import com.demo.tandem.databinding.TandemActivityBinding
import com.demo.tandem.di.utils.ViewModelFactory
import com.demo.tandem.ui.base.BaseActivity
import com.demo.tandem.viewmodel.CommunityViewModel
import javax.inject.Inject

class TandemActivity : BaseActivity<TandemActivityBinding>(R.layout.tandem_activity) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val communityViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[CommunityViewModel::class.java]
    }
    private val adapter = CommunityMembersRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector().inject(this)
        setupRecyclerView()
        observeViewModel()
        communityViewModel.getCommunityMembers()
    }

    private fun observeViewModel() {
        communityViewModel.getCommunityMembersSuccess.observe(this) {
            adapter.appendAll(it)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
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
}