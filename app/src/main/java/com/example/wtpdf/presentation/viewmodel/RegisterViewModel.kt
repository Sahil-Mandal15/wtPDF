package com.example.wtpdf.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wtpdf.repository.Resource
import com.example.wtpdf.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val authRepository: AuthRepository
): ViewModel() {

    var _registerState = MutableStateFlow<RegisterState>(value = RegisterState())

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        authRepository.registerUser(email = email, password = email).collectLatest { result ->
            when(result) {
                is Resource.Loading -> {
                    _registerState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    _registerState.update { it.copy(isSuccess = "Register Successful!") }
                }
                is Resource.Error -> {
                    _registerState.update { it.copy(isError = result.message) }
                }
            }
        }
    }
}

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: String? = null,
    val isError: String? = null
)