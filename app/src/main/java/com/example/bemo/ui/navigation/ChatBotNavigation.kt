package com.example.bemo.ui.navigation

import com.example.bemo.viewmodels.ChatViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.bemo.ui.screens.ChatBotScreen


const val chatBotRoute = "chatBot"

fun NavGraphBuilder.chatBotScreen(
    onBackToLogin: () -> Unit
) {
    composable(chatBotRoute) {
        val viewModel: ChatViewModel = hiltViewModel()
        ChatBotScreen(onBackToLogin = onBackToLogin)
    }
}

fun NavHostController.navigateToChatBot(
    navOptions: NavOptions? = null
) {
    navigate(chatBotRoute, navOptions)
}