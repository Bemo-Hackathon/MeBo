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
        startDestination = signUpRoute
    ) {
        signUpScreen(onNavigationToSignIn = {
            onNavigateToSignIn(navOptions {
                popUpTo(signUpRoute)
            })
        })
    }
}

fun NavHostController.navigateToAuthGraph() {
    navigate(authGraphRoute)
}