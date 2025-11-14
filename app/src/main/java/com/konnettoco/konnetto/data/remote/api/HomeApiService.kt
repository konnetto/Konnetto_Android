package com.konnettoco.konnetto.data.remote.api

import com.konnettoco.konnetto.data.remote.dto.CommentByIdResponse
import com.konnettoco.konnetto.data.remote.dto.CommentRepliesResponse
import com.konnettoco.konnetto.data.remote.dto.PostResponse
import com.konnettoco.konnetto.data.remote.dto.SugoiPickResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApiService {
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

    @GET("posts/{post_id}/comments/{parent_comment_id}")
    suspend fun getCommentRepliesByCommentId(
        @Path("post_id") postId: String,
        @Path("parent_comment_id") parentCommentId: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 5
    ): CommentRepliesResponse

}