package com.example.bemo.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.bemo.ui.screens.SignInScreen
import com.example.bemo.viewmodels.SignInViewModel
import kotlinx.coroutines.launch

const val signInRoute = "signIn"

fun NavGraphBuilder.signInScreen(
    onNavigateToSignUp: () -> Unit
) {
    composable(signInRoute) {
        val viewModel: SignInViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val isAuthenticated by viewModel.isAuthenticated.collectAsState(false)
        LaunchedEffect(isAuthenticated) {
            if (isAuthenticated) {
                //onNavigateToHome()
            }
        }
        SignInScreen(
            uiState = uiState,
            onSignInClick = {
                scope.launch {
                    viewModel.signIn()
                }
            },
            onSignUpClick = onNavigateToSignUp
        )
    }
}

fun NavHostController.navigateToSignIn(
    navOptions: NavOptions? = null
) {
    navigate(signInRoute, navOptions)
}