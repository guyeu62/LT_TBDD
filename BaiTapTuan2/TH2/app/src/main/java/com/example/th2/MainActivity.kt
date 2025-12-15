package com.example.th2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PracticeScreen()
            }
        }
    }
}

@Composable
fun PracticeScreen() {
    var input by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var list by remember { mutableStateOf(listOf<Int>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Thực hành 02",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.width(200.dp),
                    placeholder = { Text("Nhập vào số lượng") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    when {
                        input.isBlank() -> {
                            message = "Vui lòng nhập dữ liệu!"
                            list = emptyList()
                        }
                        input.toIntOrNull() == null || input.toInt() <= 0 -> {
                            message = "Dữ liệu bạn nhập không hợp lệ!"
                            list = emptyList()
                        }
                        else -> {
                            message = ""
                            val number = input.toInt()
                            list = (1..number).toList()
                        }
                    }
                }) {
                    Text("Tạo")
                }
            }

            if (message.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = message,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            list.forEach { item ->
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 6.dp)
                        .background(Color.Red, RoundedCornerShape(20.dp))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.toString(),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
