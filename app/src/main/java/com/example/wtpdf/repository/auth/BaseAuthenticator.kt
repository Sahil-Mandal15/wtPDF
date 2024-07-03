package com.example.wtpdf.repository.auth

import com.google.firebase.auth.FirebaseUser

interface BaseAuthenticator {

    suspend fun signUpWithEmail(email: String, password: String) : FirebaseUser?

    suspend fun signInWithEmail(email: String, password: String) : FirebaseUser?

    fun signOut() : FirebaseUser?

    fun getUser() : FirebaseUser?

    suspend fun sendPasswordResetEmail(email : String)
}