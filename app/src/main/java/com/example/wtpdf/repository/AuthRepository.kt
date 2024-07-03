package com.example.wtpdf.repository

import com.example.wtpdf.repository.auth.BaseAuthenticator
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authenticator : BaseAuthenticator
) : BaseAuthRepository {
    override suspend fun signUpWithEmail(email: String, password: String): FirebaseUser? {
        return authenticator.signUpWithEmail(email, password)
    }

    override suspend fun signInWithEmail(email: String, password: String): FirebaseUser? {
        return authenticator.signUpWithEmail(email, password)
    }

    override fun signOut(): FirebaseUser? {
        return authenticator.signOut()
    }

    override fun getUser(): FirebaseUser? {
        return authenticator.getUser()
    }

    override suspend fun sendPasswordResetEmail(email: String): Boolean {
         authenticator.sendPasswordResetEmail(email)
        return true
    }
}