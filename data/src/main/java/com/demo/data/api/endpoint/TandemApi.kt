package com.demo.data.api.endpoint

import com.demo.data.api.response.CommunityMembersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TandemApi {
    @GET("community_{page}.json")
    suspend fun getCommunityMembers(@Path(value = "page") page: Int): CommunityMembersResponse
}