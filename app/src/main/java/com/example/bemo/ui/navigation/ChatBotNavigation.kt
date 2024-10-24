package com.example.bemo.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bemo.viewmodels.ChatViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.bemo.ui.screens.ChatBotScreen


const val chatBotRoute = "chatBot"

fun NavGraphBuilder.chatBotScreen(
    onBackToLogin: () -> Unit,
    onNavigateToPayment: () -> Unit
) {
    composable(chatBotRoute) {
        val viewModel: ChatViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()

        ChatBotScreen(
            onNavigateToPayment = onNavigateToPayment,
            onBackToLogin = {
                viewModel.signOut()
                onBackToLogin()
            },
            uiState = uiState,
            onButtonClick = { userInput ->
                viewModel.sendCustomerMessage(userInput = userInput)
            }
        )
    }
}

fun NavHostController.navigateToChatBot(
    navOptions: NavOptions? = null
) {
    navigate(chatBotRoute, navOptions)
}