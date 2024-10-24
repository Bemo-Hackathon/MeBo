package com.example.bemo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bemo.R
import com.example.bemo.ui.states.SignInUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    uiState: SignInUiState
) {
    val isError = uiState.error != null

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Login") })
        }
    ) { innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedVisibility(visible = isError) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.error)
                ) {
                    val error = uiState.error ?: ""
                    Text(
                        text = error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = uiState.onEmailChange,
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = uiState.onPasswordChange,
                    shape = RoundedCornerShape(25),
                    label = {
                        Text("Senha")
                    },
                    trailingIcon = {
                        val trailingIconModifier = Modifier
                            .clickable {
                                uiState.onTogglePasswordVisibility()
                            }
                            .size(24.dp)
                        when (uiState.isShowPassword) {
                            true -> {
                                Icon(
                                    painter = painterResource(R.drawable.eye_regular),
                                    contentDescription = "ícone de visível",
                                    trailingIconModifier
                                )
                            }

                            else -> Icon(
                                painter = painterResource(R.drawable.eye_slash_solid),
                                contentDescription = "ícone de não visível",
                                trailingIconModifier
                            )
                        }
                    },
                    visualTransformation = when (uiState.isShowPassword) {
                        false -> PasswordVisualTransformation()
                        true -> VisualTransformation.None
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.purple_chat)),
                    onClick = { onSignInClick() }) {
                    Text("Sign In")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.purple_chat)),
                    onClick = { onSignUpClick() }) {
                    Text("Sign Up")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen(onSignInClick = {}, uiState = SignInUiState(), onSignUpClick = {})
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenErrorPreview() {
    val errorState =
        SignInUiState(error = "erro ao fazer login") // Cria uma nova instância do SignInUiState
    SignInScreen(onSignInClick = {}, uiState = errorState, onSignUpClick = {})
}

