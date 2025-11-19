package com.example.ecomind

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecomind.ui.theme.EcoMindTheme



/**
 * Composable que define a tela de login com design responsivo e temático.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen() {
    // Estados para os campos de email e senha
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // Usando BoxWithConstraints para responsividade básica
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isLandscape = this.maxHeight < this.maxWidth

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 1. Logotipo/Imagem Temática (Adicione um drawable 'ic_plant_logo' à pasta res/drawable)
            // Certifique-se de ter um drawable chamado 'ic_plant_logo' ou altere o R.drawable para o nome correto
            Image(
                painter = painterResource(id = R.drawable.ic_plant_logo),
                contentDescription = "Plant Logo",
                modifier = Modifier
                    .size(if (isLandscape) 80.dp else 120.dp)
                .padding(16.dp)
            )

            // 2. Título Moderno
            Text(
                text = "Bem-vindo ao EcoApp",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Faça login para gerenciar suas ações verdes",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // 3. Campo de Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )

            // 4. Campo de Senha
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        // Lógica de login pode ser chamada aqui
                    }
                ),
                trailingIcon = {
                    val image = if (isPasswordVisible)
                        painterResource(id = R.drawable.ic_visibility_on) // Adicione drawables ic_visibility_on/off
                    else
                        painterResource(id = R.drawable.ic_visibility_off)

                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(painter = image, contentDescription = "Toggle Password Visibility")
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium
            )

            // 5. Botão de Login
            Button(
                onClick = {
                    // Implementar a lógica de autenticação aqui
                    keyboardController?.hide()
                    println("Tentativa de Login com: $email e $password")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = email.isNotBlank() && password.isNotBlank(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "ENTRAR", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

/**
 * Preview da tela de Login.
 */
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EcoMindTheme {
        LoginScreen()
    }
}

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }  // remove o login da backstack
                    }
                }
            }
        ) {
            Text("Entrar")
        }
    }
}

