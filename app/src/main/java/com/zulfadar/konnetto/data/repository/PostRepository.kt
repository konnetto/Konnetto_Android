package com.zulfadar.konnetto.data.repository

import com.zulfadar.konnetto.data.FakePostDataSource
import com.zulfadar.konnetto.data.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PostRepository {
    private val posts = mutableListOf<Post>()

    init {
        if (posts.isEmpty()) {
            FakePostDataSource.dummyPosting.forEach {
                posts.add(it)
            }
        }
    }

    fun getAllPosts(): Flow<List<Post>> {
        return flowOf(posts)
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