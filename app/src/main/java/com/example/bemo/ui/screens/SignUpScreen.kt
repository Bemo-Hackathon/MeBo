package com.example.bemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bemo.ui.states.SignUpUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    signUpUiState: SignUpUiState,
    onSignUpClick: () -> Unit,
    onNavigateToSignIn: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Cadastro") },
            navigationIcon = {
                IconButton(onClick = { onNavigateToSignIn() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (signUpUiState.error != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "ERROR",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(),
                        color = Color.Red
                    )
                }
            }
            OutlinedTextField(
                value = signUpUiState.email,
                onValueChange = signUpUiState.onEmailChange,
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = signUpUiState.password,
                onValueChange = signUpUiState.onPasswordChange,
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = signUpUiState.confirmPassword,
                onValueChange = signUpUiState.onConfirmPasswordChange,
                label = { Text("Confirmar Senha") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onSignUpClick() }) {
                Text("Cadastrar")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        signUpUiState = SignUpUiState(
            email = "gabriel",
            password = "teste",
            confirmPassword = "teste"
        ), onSignUpClick = {},
        onNavigateToSignIn = {})
}

@Preview
@Composable
private fun SignUpScreenErrorPreview() {
    SignUpScreen(
        signUpUiState = SignUpUiState(error = "Erro"),
        onSignUpClick = {},
        onNavigateToSignIn = {})
}