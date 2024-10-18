package com.example.bemo.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.bemo.ui.screens.SignInScreen

const val signInRoute = "signIn"

fun NavGraphBuilder.signInScreen(
    onNavigateToSignUp: () -> Unit
) {
    composable(signInRoute) {
        SignInScreen(onSignInClick = onNavigateToSignUp)
    }
}

fun NavHostController.navigateToSignIn(
    navOptions: NavOptions? = null
) {
    navigate(signInRoute, navOptions)
}