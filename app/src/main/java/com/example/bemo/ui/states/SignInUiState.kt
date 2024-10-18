package com.example.bemo.ui.states

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val onEmailChange: (String) -> Unit = {},
    val onPasswordChange: (String) -> Unit = {},
    val onTogglePasswordVisibility: () -> Unit = {},
    val isShowPassword: Boolean = false,
    val error: String? = null
)
