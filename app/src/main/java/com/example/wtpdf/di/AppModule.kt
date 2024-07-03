package com.example.wtpdf.di

import com.example.wtpdf.repository.AuthRepository
import com.example.wtpdf.repository.BaseAuthRepository
import com.example.wtpdf.repository.auth.BaseAuthenticator
import com.example.wtpdf.repository.auth.FirebaseAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn
object AppModule {

    @Singleton
    @Provides
    fun provideAuthenticator() : BaseAuthenticator {
        return FirebaseAuthenticator()
    }

    @Singleton
    @Provides
    fun provideRepository(authenticator : BaseAuthenticator) : BaseAuthRepository {
        return AuthRepository(authenticator)
    }
}