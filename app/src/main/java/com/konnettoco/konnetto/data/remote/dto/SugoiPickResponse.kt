package com.konnettoco.konnetto.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SugoiPickResponse(

	@field:SerializedName("data")
	val data: List<SugoiPickDataItem?>? = null,

	@field:SerializedName("page_info")
	val pageInfo: SugoiPickPageInfo? = null
)

data class Review(

	@field:SerializedName("anime_name")
	val animeName: String? = null,

	@field:SerializedName("add_to_library")
	val addToLibrary: Boolean? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("poster_url")
	val posterUrl: String? = null,

	@field:SerializedName("anime_id")
	val animeId: String? = null
)

data class SugoiPickPageInfo(

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("total_elements")
	val totalElements: Int? = null,

	@field:SerializedName("has_next")
	val hasNext: Boolean? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("has_prev")
	val hasPrev: Boolean? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null
)

data class SugoiPickMediaItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("order")
	val order: Int? = null
)

data class SugoiPickDataItem(

	@field:SerializedName("comment_count")
	val commentCount: Int? = null,

	@field:SerializedName("advertisment_id")
	val advertismentId: Any? = null,

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("visibility")
	val visibility: String? = null,

	@field:SerializedName("caption")
	val caption: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("media")
	val media: List<SugoiPickMediaItem?>? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null,

	@field:SerializedName("share_count")
	val shareCount: Int? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("shared_from_post_id")
	val sharedFromPostId: Any? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("review")
	val review: Review? = null,

	@field:SerializedName("post_type")
	val postType: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
