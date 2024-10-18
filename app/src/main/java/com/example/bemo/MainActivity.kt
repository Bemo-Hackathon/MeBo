package com.example.bemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.bemo.ui.components.ChatBotScreen
import com.example.bemo.ui.navigation.authGraph
import com.example.bemo.ui.navigation.authGraphRoute
import com.example.bemo.ui.navigation.navigateToSignUp
import com.example.bemo.ui.theme.BeMoTheme
import com.google.firebase.auth.ktx.auth
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivy"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        auth.createUserWithEmailAndPassword(
//            "gabriel@gmail.com",
//            "123456"
//        ).addOnCompleteListener { task ->
//            if(task.isSuccessful){
//                Log.i(TAG, "created user: sucesso")
//            }else{
//                Log.i(TAG, "created user: falha -> ${task.exception}")
//            }
//        }
//        auth.signInWithEmailAndPassword(
//            "gabriel@gmail.com",
//            "123456"
//        )
//


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
                        onNavigateToSignIn = {},
                        onNavigateToSignUp = {
                            navController.navigateToSignUp()
                        }
                    )
                }
            }
        }
    }
}
