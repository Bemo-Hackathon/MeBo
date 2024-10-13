package com.example.bemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bemo.ChatViewModel
import com.example.bemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBotScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column() {
                        Text("Be'Mo", fontSize = 22.sp)
                        Text("assistente virtual", fontSize = 16.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple_chat),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Item da BottomAppBar")
            }
        }
    ) { innerPadding ->
        ChatBotScreenContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun ChatBotScreenContent(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val chatMessages = viewModel.chatMessages
    var userInput by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(top = 12.dp)) {
        LazyColumn(
            modifier = Modifier
                .weight(3F)
                .padding(start = 16.dp, end = 16.dp),
            reverseLayout = false,
        ) {
            items(chatMessages) { message ->
                MessageItem(
                    message = message
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            TextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier.weight(1F),
                placeholder = { Text("Digite sua pergunta...") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(R.drawable.arrow_right),
                contentDescription = "Enviar",
                tint = colorResource(R.color.purple_chat),
                modifier = Modifier
                    .size(32.dp)
                    .sizeIn(48.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .clickable {
                        if (userInput.isNotBlank()) {
                            viewModel.sendMessage(userInput) // Enviar a mensagem para o ViewModel
                            userInput = "" // Limpar o campo de texto após enviar
                        }
                    }
            )
        }
    }
}

@Composable
fun MessageItem(
    modifier: Modifier = Modifier,
    message: String
) {
    val isUserMessage = message.startsWith("User:")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (isUserMessage) Arrangement.End else Arrangement.Start
    ) {
        Text(
            text = message,
            modifier = Modifier
                .background(
                    color = if (isUserMessage) colorResource(R.color.purple_chat_transparent) else colorResource(
                        R.color.gray_chat
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
    }
}
