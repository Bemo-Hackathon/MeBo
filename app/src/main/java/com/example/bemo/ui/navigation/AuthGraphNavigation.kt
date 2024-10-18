package com.example.bemo.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation

const val authGraphRoute = "auth_graph_route"

fun NavGraphBuilder.authGraph(
    onNavigateToSignUp: () -> Unit,
    onNavigateToHomeGraph: (NavOptions) -> Unit,
    onNavigateToSignIn: (NavOptions) -> Unit
) {
    navigation(
        route = authGraphRoute,
        startDestination = signInRoute
    ) {
        signInScreen(
            onNavigateToSignUp = onNavigateToSignUp,
        )
        signUpScreen(onNavigationToSignIn = {
            onNavigateToSignIn(navOptions {
                popUpTo(authGraphRoute)
            })
        })
    }
}

fun NavHostController.navigateToAuthGraph() {
    navigate(authGraphRoute)
}