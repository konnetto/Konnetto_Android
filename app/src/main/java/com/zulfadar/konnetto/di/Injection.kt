package com.zulfadar.konnetto.di

import com.zulfadar.konnetto.data.repository.PostRepository

object Injection {
    fun provideRepositoy(): PostRepository {
        return PostRepository.getInstance()
    }
}