package com.demo.tandem.ui.tandem

import com.demo.domain.CommunityMember
import com.demo.tandem.R
import com.demo.tandem.databinding.CommunityMemberViewHolderBinding
import com.demo.tandem.ui.base.BaseRecyclerViewAdapter

class CommunityMembersRecyclerViewAdapter :
    BaseRecyclerViewAdapter<CommunityMemberViewHolderBinding, CommunityMember>(
        R.layout.community_member_view_holder
    ) {
    override fun onBind(
        binding: CommunityMemberViewHolderBinding,
        position: Int,
        item: CommunityMember,
        vararg payloads: MutableList<Any>
    ) {
        binding.member = item
    }

}
