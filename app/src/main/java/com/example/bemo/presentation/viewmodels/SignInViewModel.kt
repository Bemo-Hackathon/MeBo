package com.example.bemo.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bemo.authentication.FirebaseAuthAPI
import com.example.bemo.ui.states.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthAPI
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    val isAuthenticated = firebaseAuthRepository.currentUser
        .map {
            it != null
        }

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
                onTogglePasswordVisibility = {
                    _uiState.update {
                        it.copy(isShowPassword = !it.isShowPassword)
                    }
                }
            )
        }
    }

    suspend fun signIn() {
        try {
            firebaseAuthRepository.signIn(
                email = _uiState.value.email,
                password = _uiState.value.password
            )
        } catch (e: Exception) {
            Log.e("SignInViewModel", "signIn: ", e)
            _uiState.update {
                it.copy(error = "Erro ao logar Usuário!")
            }
            delay(3000)
            _uiState.update {
                it.copy(error = null)
            }
        }

    }
}