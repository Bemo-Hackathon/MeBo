package com.example.bemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bemo.R
import com.example.bemo.ui.states.ChatUiState
import com.example.bemo.viewmodels.ChatViewModel

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel(),
    onBackToChat: () -> Unit,
    uiState: ChatUiState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Resposta do Pagamento:")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = uiState.paymentResponse ?: "Aguardando resposta...")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_chat)),
            onClick = { viewModel.sendPaymentStatus() }
        ) {
            Text("Enviar Pagamento")
        }
        Button(
            onClick = { onBackToChat() },
        ) {
            Text(text = "Voltar")
        }
    }
}