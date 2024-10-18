package com.example.bemo.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bemo.authentication.FirebaseAuthRepository
import com.example.bemo.ui.states.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val fireAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    private val _signUpIsSuccessful = MutableSharedFlow<Boolean>()
    val signUpIsSuccessful = _signUpIsSuccessful.asSharedFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { email ->
                    _uiState.update {
                        it.copy(email = email)
                    }
                },
                onPasswordChange = { password ->
                    _uiState.update {
                        it.copy(password = password)
                    }
                },
                onConfirmPasswordChange = { confirmPassword ->
                    _uiState.update {
                        it.copy(confirmPassword = confirmPassword)
                    }
                }
            )
        }
    }

    suspend fun signUp() {
        try {
            fireAuthRepository.signUp(
                _uiState.value.email,
                _uiState.value.password
            )
            _signUpIsSuccessful.emit(true)
        } catch (e: Exception) {
            Log.e("SignUpViewModel", "signUp:", e)
            _uiState.update {
                it.copy(
                    error = "Erro ao cadastrar usuário"
                )
            }
            _signUpIsSuccessful.emit(false)
        }
    }
}