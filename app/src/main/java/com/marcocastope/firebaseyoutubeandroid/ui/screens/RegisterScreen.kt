package com.marcocastope.firebaseyoutubeandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcocastope.firebaseyoutubeandroid.ui.theme.FirebaseYoutubeAndroidTheme

@Composable
fun RegisterScreen(
    onLoginClick: (email: String, password: String) -> Unit,
    onRegisterClick: (email: String, password: String) -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val onEmailChange: (String) -> Unit = {
        email = it
    }

    val onPasswordChange: (String) -> Unit = {
        password = it
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Regístrate", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = {
                Text(text = "Correo electrónico")
            })

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {
                Text(text = "Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onRegisterClick(email, password) },
            enabled = validateInputs(email, password)
        ) {
            Text(text = "Registrar")
        }

        Button(
            onClick = { onLoginClick(email, password) },
            enabled = validateInputs(email, password)
        ) {
            Text(text = "Iniciar sesión")
        }
    }
}

private fun validateInputs(email: String, password: String): Boolean {
    return email.isNotEmpty() && password.isNotEmpty()
}

@Preview
@Composable
private fun RegisterScreenPrev() {
    FirebaseYoutubeAndroidTheme {
        RegisterScreen(
            onLoginClick = { _, _ -> },
            onRegisterClick = { _, _ -> })
    }
}