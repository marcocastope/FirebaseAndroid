package com.marcocastope.firebaseyoutubeandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcocastope.firebaseyoutubeandroid.ui.theme.FirebaseYoutubeAndroidTheme

@Composable
fun HomeScreen(onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenidos al inicio", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onLogoutClick) {
            Text(text = "Cerrar sesión")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    FirebaseYoutubeAndroidTheme {
        HomeScreen {}
    }
}