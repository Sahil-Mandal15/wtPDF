package com.example.wtpdf.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wtpdf.repository.BaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class wtPDFViewModel @Inject constructor(
    private val repository : BaseAuthRepository
) : ViewModel() {

    private val TAG = "wtPDFViewModel"

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val currentUser get() = _firebaseUser

    private val eventsChannels = Channel<AllEvents>()
    val allEventsFlow = eventsChannels.receiveAsFlow()

    fun signInUser(email: String, password: String) = viewModelScope.launch {
        when {
            email.isEmpty() -> {
                eventsChannels.send(AllEvents.ErrorCode(1))
            }
            password.isEmpty() -> {
                eventsChannels.send(AllEvents.ErrorCode(2))
            }
            else -> {
                actualSignIn(email, password)
            }
        }
    }

    fun actualSignIn(email: String, password: String) = viewModelScope.launch {
        try {
            val user = repository.signInWithEmail(email, password)
            user?.let {
                _firebaseUser.postValue(it)
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            Log.d(TAG, "signInUser: ${error[1]}")
            eventsChannels.send(AllEvents.Error(error[1]))
        }

    }

    fun signUpUser(email: String, password: String, confirmPassword: String) = viewModelScope.launch {
        when {
            email.isEmpty() -> {
                eventsChannels.send(AllEvents.ErrorCode(1))
            }
            password.isEmpty() -> {
                eventsChannels.send(AllEvents.ErrorCode(2))
            }
            password != confirmPassword -> {
                eventsChannels.send(AllEvents.ErrorCode(3))
            }
            else -> {
                actualSignUp(email, password)
            }
        }
    }

    fun actualSignUp(email: String, password: String) = viewModelScope.launch {
        try {
            val user = repository.signUpWithEmail(email, password)
            user?.let {
                _firebaseUser.postValue(it)
                eventsChannels.send(AllEvents.Message("Sign Up Successful"))
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            Log.d(TAG, "signUpUser: ${error[1]}")
            eventsChannels.send(AllEvents.Error(error[1]))
        }
    }

    fun signOutUser() = viewModelScope.launch {
        try {
            val user = repository.signOut()
            user?.let {
                eventsChannels.send(AllEvents.Message("Logout Failure"))
            } ?: eventsChannels.send(AllEvents.Message("Sign Out Successful"))

            getCurrentUser()
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            Log.d(TAG, "signInUser: ${error[1]}")
            eventsChannels.send(AllEvents.Error(error[1]))
        }
    }

    fun getCurrentUser() = viewModelScope.launch {
        val user = repository.getUser()
        _firebaseUser.postValue(user)
    }

    suspend fun sendPasswordReset(email: String) {
        if(email.isEmpty()) {
            viewModelScope.launch {
                eventsChannels.send(AllEvents.ErrorCode(1))
            }
        } else {
            actualSendPasswordResetEmail(email)
        }
    }

    suspend fun actualSendPasswordResetEmail(email: String) {
        try {
            val result = repository.sendPasswordResetEmail(email)
            if(result) {
                eventsChannels.send(AllEvents.Message("Reset Email sent"))
            } else {
                eventsChannels.send(AllEvents.Message("Could not send reset email"))
            }
        } catch (e: Exception) {
            val error = e.toString().split(":").toTypedArray()
            Log.d(TAG, "sendPasswordReset: ${error[1]}")
            eventsChannels.send(AllEvents.Error(error[1]))
        }
    }


}

sealed class AllEvents {
    data class Message(val message: String) : AllEvents()
    data class ErrorCode(val code: Int) : AllEvents()
    data class Error(val error: String) : AllEvents()
}