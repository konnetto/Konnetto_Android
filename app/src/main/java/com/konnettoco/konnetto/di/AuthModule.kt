package com.konnettoco.konnetto.di

import com.konnettoco.konnetto.data.repository.auth.RegisterRepositoryImpl
import com.konnettoco.konnetto.data.repository.auth.ResendOtpRepositoryImpl
import com.konnettoco.konnetto.data.repository.auth.VerifyOtpRepositoryImpl
import com.konnettoco.konnetto.domain.repository.auth.RegisterRepository
import com.konnettoco.konnetto.domain.repository.auth.ResendOtpRepository
import com.konnettoco.konnetto.domain.repository.auth.VerifyOtpRepository
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
    abstract fun bindRegisterRepository(
        impl: RegisterRepositoryImpl
    ): RegisterRepository

    @Binds
    @Singleton
    abstract fun bindVerifyOtpRepository(
        impl: VerifyOtpRepositoryImpl
    ): VerifyOtpRepository

    @Binds
    @Singleton
    abstract fun bindResendOtprepository(
        impl: ResendOtpRepositoryImpl
    ): ResendOtpRepository
}
