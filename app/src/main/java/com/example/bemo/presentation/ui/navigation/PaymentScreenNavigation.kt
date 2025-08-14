package com.example.bemo.ui.navigation


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bemo.presentation.viewmodels.ChatViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.bemo.ui.screens.PaymentScreen


const val paymentRoute = "payment"

fun NavGraphBuilder.paymentScreen(
    onBackToChat: () -> Unit
) {
    composable(paymentRoute) {
        val viewModel: ChatViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()

        PaymentScreen(
            onBackToChat = onBackToChat,
            uiState = uiState,
        )
    }
}

fun NavHostController.navigateToPaymentScreen(
    navOptions: NavOptions? = null
) {
    navigate(paymentRoute, navOptions)
}