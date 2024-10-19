package com.example.bemo.ui.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
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

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Login") })
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!uiState.error.isNullOrEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    Text("Error: ${uiState.error}")
                }
            }
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
                    val trailingIconModifier = Modifier.clickable {
                        uiState.onTogglePasswordVisibility()
                    }.size(24.dp)
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
            Button(onClick = { onSignInClick() }) {
                Text("Sign In")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onSignUpClick() }) {
                Text("Sign Up")
            }
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    SignInScreen(onSignInClick = {}, uiState = SignInUiState(), onSignUpClick = {})
}

@Preview
@Composable
private fun SignInScreenErrorPreview() {
    SignInScreen(onSignInClick = {}, uiState = SignInUiState(error = "error"), onSignUpClick = {})
}

