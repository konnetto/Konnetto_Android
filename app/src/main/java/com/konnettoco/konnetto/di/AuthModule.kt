package com.konnettoco.konnetto.di

import com.konnettoco.konnetto.data.repository.auth.RegisterRepositoryImpl
import com.konnettoco.konnetto.domain.repository.auth.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindingRegisterRepository(
        impl: RegisterRepositoryImpl
    ): RegisterRepository
}