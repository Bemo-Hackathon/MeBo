package com.example.bemo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bemo.ChatViewModel

@Composable
fun ChatBotScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    // Track user input
    var userInput by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Display chat messages
        LazyColumn(
            modifier = Modifier
                .weight(1F)
                .padding(16.dp),
            reverseLayout = true
        ) {
            items(viewModel.chatMessages) { message ->
                Text(text = message)
            }
        }

        // Input field and send button
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier.weight(1F),
                placeholder = { Text("Digite sua mensagem...") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (userInput.isNotBlank()) {
                    viewModel.sendMessage(userInput)  // Send the message via ViewModel
                    userInput = "" // Clear input field
                }
            }) {
                Text("Enviar")
            }
        }
    }
}

@Composable
fun ChatBotList(modifier: Modifier = Modifier) {

}

@Composable
fun MessageItem(
    modifier: Modifier = Modifier,
    message: String
) {

}