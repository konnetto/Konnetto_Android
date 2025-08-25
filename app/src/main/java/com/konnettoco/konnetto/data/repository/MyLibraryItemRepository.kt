package com.konnettoco.konnetto.data.repository

import com.konnettoco.konnetto.data.FakeMyLibraryItemDataSource
import com.konnettoco.konnetto.data.model.MyLibraryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MyLibraryItemRepository {
    private val myLibraryItem = mutableListOf<MyLibraryItem>()

    init {
        if (myLibraryItem.isEmpty()) {
            FakeMyLibraryItemDataSource.dummyMyLibraryItem.forEach {
                myLibraryItem.add(it)
            }
        }
    }

    fun getAllMyLibraryItems(): Flow<List<MyLibraryItem>> {
        return flowOf(myLibraryItem)
    }

    fun getLibraryItemById(libraryItemId: Long): MyLibraryItem {
        return myLibraryItem.first {
            it.id.toLong() == libraryItemId
        }
    }

    companion object {
        @Volatile
        private var instance: MyLibraryItemRepository? = null

        fun getInstance(): MyLibraryItemRepository =
            instance ?: synchronized(this) {
                MyLibraryItemRepository().apply {
                    instance = this
                }
            }
    }
}