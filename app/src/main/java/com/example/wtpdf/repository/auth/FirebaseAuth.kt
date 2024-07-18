package com.example.wtpdf.repository.auth

import com.example.wtpdf.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.google.firebase.auth.FirebaseAuth

@Module
@InstallIn(SingletonComponent::class)
object FirebaseAuth {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository{
        return AuthRepositoryImpl(firebaseAuth = firebaseAuth)
    }

//    @Provides
//    @Singleton
//    fun providesDatabaseRepositoryImpl(): DatabaseRepository {
//        return DatabaseRepositoryImpl()
//    }
}