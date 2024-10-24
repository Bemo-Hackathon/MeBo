package com.example.bemo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.bemo.R
import com.example.bemo.ui.states.ChatUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBotScreen(
    modifier: Modifier = Modifier,
    onBackToLogin: () -> Unit,
    onNavigateToPayment: () -> Unit,
    onButtonClick: (String) -> Unit = {},
    uiState: ChatUiState

) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Column() {
                            Text(
                                "Be'Mo",
                                fontSize = 22.sp,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                            Text("assistente virtual", fontSize = 16.sp)
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.purple_chat)),
                            onClick = { onNavigateToPayment() },
                            modifier = Modifier
                                .padding(start = 65.dp, top = 16.dp),
                            shape = CircleShape
                        ) {
                            Text("Pagamento")
                        }
                    }


                },
                navigationIcon = {
                    IconButton(onClick = { onBackToLogin() }) {
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
                ),
            )
        }
    ) { innerPadding ->
        ChatBotScreenContent(
            onButtonClick = onButtonClick,
            uiState = uiState,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun ChatBotScreenContent(
    modifier: Modifier = Modifier,
    uiState: ChatUiState,
    onButtonClick: (String) -> Unit = {},
) {
    var userInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 12.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .weight(3F)
                .padding(start = 16.dp, end = 16.dp),
            reverseLayout = false,
        ) {
            items(uiState.messages) { message ->
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
//                            viewModel.sendCustomerMessage(userInput)
                            onButtonClick(userInput)
                            userInput = ""
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
            .padding(vertical = 6.dp),
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
