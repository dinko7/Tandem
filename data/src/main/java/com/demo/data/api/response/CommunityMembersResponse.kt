package com.demo.data.api.response

class CommunityMembersResponse {
    var response: List<MemberResponse>? = null
}

class MemberResponse {
    var id: String? = null
    var topic: String? = null
    var firstName: String? = null
    var pictureUrl: String? = null
    var natives: List<String>? = null
    var learns: List<String>? = null
    var referenceCnt: Int? = null
}
