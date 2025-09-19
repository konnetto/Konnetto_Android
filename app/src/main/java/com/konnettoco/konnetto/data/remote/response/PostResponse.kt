package com.konnettoco.konnetto.data.remote.response

import com.google.gson.annotations.SerializedName

data class PostResponse(

	@field:SerializedName("pagination")
	val pagination: Pagination? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class MediaItem(

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("thumbnailUrl")
	val thumbnailUrl: String? = null,

	@field:SerializedName("order")
	val order: Int? = null
)

data class DataItem(

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
	val media: List<MediaItem?>? = null,

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

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class Pagination(

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("totalPages")
	val totalPages: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)
