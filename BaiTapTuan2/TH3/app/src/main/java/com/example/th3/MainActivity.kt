package com.example.th3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinhToanApp()
        }
    }
}

@Composable
fun TinhToanApp() {
    MaterialTheme {
        TinhToanScreen()
    }
}

@Composable
fun TinhToanScreen() {

    var soA by remember { mutableStateOf("") }
    var soB by remember { mutableStateOf("") }
    var ketQua by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Thực hành 03",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = soA,
            onValueChange = { soA = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nhập số thứ nhất") }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OperatorButton("+", Color.Red) {
                ketQua = tinhToan(soA, soB, "+")
            }
            OperatorButton("-", Color(0xFFFBC02D)) {
                ketQua = tinhToan(soA, soB, "-")
            }
            OperatorButton("*", Color(0xFF673AB7)) {
                ketQua = tinhToan(soA, soB, "*")
            }
            OperatorButton("/", Color.Black) {
                ketQua = tinhToan(soA, soB, "/")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = soB,
            onValueChange = { soB = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nhập số thứ hai") }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = if (ketQua.isEmpty()) "Kết quả:" else "Kết quả: $ketQua",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun OperatorButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(color, RoundedCornerShape(8.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        TextButton(onClick = onClick) {
            Text(
                text = text,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}


fun tinhToan(a: String, b: String, phepToan: String): String {
    if (a.isEmpty() || b.isEmpty()) return ""

    val soA = a.toDoubleOrNull() ?: return ""
    val soB = b.toDoubleOrNull() ?: return ""

    return when (phepToan) {
        "+" -> (soA + soB).toString()
        "-" -> (soA - soB).toString()
        "*" -> (soA * soB).toString()
        "/" -> if (soB != 0.0) (soA / soB).toString() else "Lỗi chia 0"
        else -> ""
    }
}
