package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.FakePostDataSource
import com.konnettoco.konnetto.data.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PostRepository {
    private val posts = mutableListOf<Post>()

    init {
        if (posts.isEmpty()) {
            FakePostDataSource.otherDummyPost.forEach {
                posts.add(it)
            }
        }
    }

    fun getAllPosts(): Flow<List<Post>> {
        return flowOf(posts)
    }

    fun getPostsByUserId(userId: Long): Flow<List<Post>> {
        return flowOf(posts.filter { it.author.id == userId })
    }

    companion object {
        @Volatile
        private var instance: PostRepository? = null

        fun getInstance(): PostRepository =
            instance ?: synchronized(this) {
                PostRepository().apply {
                    instance = this
                }
            }
    }
}