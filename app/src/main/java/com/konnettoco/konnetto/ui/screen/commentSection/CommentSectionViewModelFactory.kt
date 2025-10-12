package com.konnettoco.konnetto.ui.screen.commentSection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CommentSectionViewModelFactory(
    private val postId: String,
    private val parentCommentId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentSectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommentSectionViewModel(postId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}