package com.konnettoco.konnetto.di

import android.content.Context
import com.konnettoco.konnetto.data.local.datastrore.AppSettingsDataStore
import com.konnettoco.konnetto.data.repository.SettingsRepositoryImpl
import com.konnettoco.konnetto.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object SettingsDataModule {
    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context): AppSettingsDataStore {
        return AppSettingsDataStore(context)
    }
}