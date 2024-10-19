package com.example.bemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.bemo.ui.navigation.authGraph
import com.example.bemo.ui.navigation.authGraphRoute
import com.example.bemo.ui.navigation.navigateToChatBot
import com.example.bemo.ui.navigation.navigateToPaymentScreen
import com.example.bemo.ui.navigation.navigateToSignIn
import com.example.bemo.ui.navigation.navigateToSignUp
import com.example.bemo.ui.screens.PaymentScreen
import com.example.bemo.ui.theme.BeMoTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BeMoTheme {
//                PaymentScreen()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = authGraphRoute
                ) {
                    authGraph(
                        onNavigateToChat = { navController.navigateToChatBot() },
                        onNavigateToSignIn = { navController.navigateToSignIn() },
                        onNavigateToSignUp = { navController.navigateToSignUp() },
                        onNavigatePayment = { navController.navigateToPaymentScreen() }
                    )
                }
            }
        }
    }
}
