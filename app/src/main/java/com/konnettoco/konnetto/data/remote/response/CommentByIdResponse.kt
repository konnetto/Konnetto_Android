package com.konnettoco.konnetto.data.remote.response

import com.google.gson.annotations.SerializedName

data class CommentByIdResponse(

	@field:SerializedName("data")
	val data: List<CommentsDataItem?>? = null,

	@field:SerializedName("page_info")
	val pageInfo: CommentsPageInfo? = null
)

data class CommentsPageInfo(

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

data class CommentsDataItem(

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("reply_count")
	val replyCount: Int? = null,

	@field:SerializedName("media_url")
	val mediaUrl: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("is_deleted")
	val isDeleted: Boolean? = null,

	@field:SerializedName("post_id")
	val postId: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("comment_type")
	val commentType: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("parent_comment_id")
	val parentCommentId: Any? = null,

	@field:SerializedName("has_child_comment")
	val hasChildComment: Boolean? = null,

	@field:SerializedName("username")
	val username: String? = null
)
