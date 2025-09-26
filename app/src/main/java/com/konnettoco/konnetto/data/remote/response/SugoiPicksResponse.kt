package com.konnettoco.konnetto.data.remote.response

import com.google.gson.annotations.SerializedName

data class SugoiPicksResponse(

	@field:SerializedName("pagination")
	val pagination: SugoiPicksPagination? = null,

	@field:SerializedName("data")
	val data: List<SugoiPicksDataItem?>? = null
)

data class SugoiPicksMediaItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("order")
	val order: Int? = null
)

data class Review(

	@field:SerializedName("addToLibrary")
	val addToLibrary: Boolean? = null,

	@field:SerializedName("animeId")
	val animeId: String? = null,

	@field:SerializedName("posterUrl")
	val posterUrl: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("animeName")
	val animeName: String? = null
)

data class SugoiPicksDataItem(

	@field:SerializedName("advertismentId")
	val advertismentId: Any? = null,

	@field:SerializedName("visibility")
	val visibility: String? = null,

	@field:SerializedName("avatarUrl")
	val avatarUrl: String? = null,

	@field:SerializedName("postType")
	val postType: String? = null,

	@field:SerializedName("caption")
	val caption: String? = null,

	@field:SerializedName("likeCount")
	val likeCount: Int? = null,

	@field:SerializedName("media")
	val media: List<SugoiPicksMediaItem?>? = null,

	@field:SerializedName("sharedFromPostId")
	val sharedFromPostId: Any? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null,

	@field:SerializedName("commentCount")
	val commentCount: Int? = null,

	@field:SerializedName("shareCount")
	val shareCount: Int? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("review")
	val review: Review? = null,

	@field:SerializedName("displayname")
	val displayname: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class SugoiPicksPagination(

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("totalPages")
	val totalPages: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)
