package com.konnettoco.konnetto.data.remote.connection

import com.konnettoco.konnetto.data.remote.response.CommentByIdResponse
import com.konnettoco.konnetto.data.remote.response.PostResponse
import com.konnettoco.konnetto.data.remote.response.SugoiPickResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getAllPosts(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): PostResponse

    @GET("posts/sugoipicks")
    suspend fun getallSugoiPicks(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): SugoiPickResponse

    @GET("posts/{post_id}/comments")
    suspend fun getCommentByPostId(
        @Path("post_id") postId: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 5
    ): CommentByIdResponse

}