package com.konnettoco.konnetto.data.remote.connection

import com.konnettoco.konnetto.data.remote.response.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getAllPosts(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): PostResponse

}