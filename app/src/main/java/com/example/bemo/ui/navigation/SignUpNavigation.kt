package com.example.bemo.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.bemo.ui.screens.SignUpScreen
import com.example.bemo.viewmodels.SignUpViewModel
import kotlinx.coroutines.launch

const val signUpRoute = "signUp"

fun NavGraphBuilder.signUpScreen(
    onNavigationToSignIn: () -> Unit
) {
    composable(signUpRoute) {
        val viewModel: SignUpViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val signUpIsSuccessful by viewModel.signUpIsSuccessful.collectAsState(false)

        LaunchedEffect(signUpIsSuccessful) {
            if (signUpIsSuccessful) {
                onNavigationToSignIn()
            }
        }
        SignUpScreen(
            signUpUiState = uiState,
            onSignUpClick = {
                scope.launch {
                    viewModel.signUp()
                }
            }
        )
    }
}

fun NavHostController.navigateToSignUp() {
    navigate(signUpRoute)
}