package com.konnettoco.konnetto.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CommentRepliesResponse(

	@field:SerializedName("data")
	val data: List<CommentRepliesDataItem?>? = null,

	@field:SerializedName("page_info")
	val pageInfo: CommentRepliesPageInfo? = null
)

data class CommentRepliesDataItem(

	@field:SerializedName("has_child_comment")
	val hasChildComment: Boolean? = null,

	@field:SerializedName("parent_id")
	val parentId: String? = null,

	@field:SerializedName("comment_type")
	val commentType: String? = null,

	@field:SerializedName("mentions")
	val mentions: List<MentionsItem?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("media_url")
	val mediaUrl: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("avatar_url")
	val avatar: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("like_count")
	val likeCount: Int? = null
)

data class CommentRepliesPageInfo(

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

data class MentionsItem(

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
