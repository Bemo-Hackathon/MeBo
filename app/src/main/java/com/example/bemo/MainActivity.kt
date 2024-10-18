package com.example.bemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.bemo.ui.navigation.authGraph
import com.example.bemo.ui.navigation.authGraphRoute
import com.example.bemo.ui.navigation.navigateToSignIn
import com.example.bemo.ui.navigation.navigateToSignUp
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
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = authGraphRoute
                ) {
                    authGraph(
                        onNavigateToHomeGraph = {},
                        onNavigateToSignIn = { navController.navigateToSignIn() },
                        onNavigateToSignUp = { navController.navigateToSignUp() }
                    )
                }
            }
        }
    }
}
