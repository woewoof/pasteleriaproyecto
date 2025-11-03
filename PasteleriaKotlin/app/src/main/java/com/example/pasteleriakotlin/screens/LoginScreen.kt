package com.example.pasteleriakotlin.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onLogin: ()->Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.Center) {
        Text(text = "PasteleriaKotlin", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            showError = !(email.contains("@") && password.length >= 4)
            if (!showError) onLogin()
        }) {
            Text("Ingresar")
        }
        AnimatedVisibility(visible = showError, enter = fadeIn(), exit = fadeOut()) {
            Text("Email inválido o contraseña muy corta", color = MaterialTheme.colorScheme.error)
        }
    }
}
