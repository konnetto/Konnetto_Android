package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.FakeCurrentlyWatchingDataSource
import com.konnettoco.konnetto.data.model.CurrentlyWatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CurrentlyWatchingRepository {
    private val currentlyWatchings = mutableListOf<CurrentlyWatching>()

    init {
        if (currentlyWatchings.isEmpty()) {
            FakeCurrentlyWatchingDataSource.dummyCurrentlyWatching.forEach {
                currentlyWatchings.add(it)
            }
        }
    }

    fun getAllCurrentlyWatching(): Flow<List<CurrentlyWatching>> {
        return flowOf(currentlyWatchings)
    }

    companion object {
        @Volatile
        private var instance: CurrentlyWatchingRepository? = null

        fun getInstance(): CurrentlyWatchingRepository =
            instance ?: synchronized(this) {
                CurrentlyWatchingRepository().apply {
                    instance = this
                }
            }
    }
}