package com.zulfadar.konnetto.data.repository

import com.zulfadar.konnetto.data.model.FakePostDataSource
import com.zulfadar.konnetto.data.model.Posting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PostRepository {
    private val postings = mutableListOf<Posting>()

    init {
        if (postings.isEmpty()) {
            FakePostDataSource.dummyPosting.forEach {
                postings.add(Posting(it))
            }
        }
    }

    fun getAllPosting(): Flow<List<Posting>> {
        return flowOf(postings)
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