package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.FakeSugoiPicksDataSource
import com.konnettoco.konnetto.data.local.model.SugoiPicks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SugoiPicksRepository {
    private val sugoiPicks = mutableListOf<SugoiPicks>()

    init {
        if (sugoiPicks.isEmpty()) {
            FakeSugoiPicksDataSource.dummySugoiPicks.forEach {
                sugoiPicks.add(it)
            }
        }
    }

    fun getAllSugoiPicks(): Flow<List<SugoiPicks>> {
        return flowOf(sugoiPicks)
    }

    companion object {
        @Volatile
        private var instance: SugoiPicksRepository? = null

        fun getInstance(): SugoiPicksRepository =
            instance ?: synchronized(this) {
                SugoiPicksRepository().apply {
                    instance = this
                }
            }
    }
}