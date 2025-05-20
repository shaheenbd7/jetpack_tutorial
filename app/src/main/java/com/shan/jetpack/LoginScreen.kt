package com.shan.jetpack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

//@Composable
//fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
//    val name = viewModel.name
//    val password = viewModel.password
//    val isPasswordVisible = viewModel.isPasswordVisible
//
//    Column(
//        modifier = Modifier
//            .padding(24.dp)
//            .fillMaxWidth(),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        NameInput(
//            name = name,
//            onNameChange = viewModel::onNameChange,
//            isError = name.isNotBlank() && !name.first().isUpperCase()
//        )
//
//        if (!viewModel.isNameValid && name.isNotBlank()) {
//            Text("Name must start with capital letter", color = Color.Red)
//        }
//
//        PasswordInput(
//            password = password,
//            onPasswordChange = viewModel::onPasswordChange,
//            isVisible = isPasswordVisible,
//            onVisibilityToggle = viewModel::togglePasswordVisibility
//        )
//
//        if (!viewModel.isPasswordValid && password.isNotBlank()) {
//            Text("Password must be at least 6 characters", color = Color.Red)
//        }
//
////        Button(
////            onClick = { /* Login action */ },
////            enabled = viewModel.isNameValid && viewModel.isPasswordValid,
////            modifier = Modifier.fillMaxWidth()
////        ) {
////            Text("Login")
////        }
//
//        Button(
//            onClick = {
//                if (viewModel.isNameValid && viewModel.isPasswordValid) {
//                    onLoginSuccess()
//                }
//            },
//            enabled = viewModel.isNameValid && viewModel.isPasswordValid
//        ) {
//            Text("Login")
//        }
//    }
//}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit
) {
    val name = viewModel.name
    val password = viewModel.password
    val isPasswordVisible = viewModel.isPasswordVisible

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NameInput(
            name = name,
            onNameChange = viewModel::onNameChange,
            isError = name.isNotBlank() && !name.first().isUpperCase()
        )

        PasswordInput(
            password = password,
            onPasswordChange = viewModel::onPasswordChange,
            isVisible = isPasswordVisible,
            onVisibilityToggle = viewModel::togglePasswordVisibility
        )

        Button(
            onClick = {
                if (viewModel.isNameValid && viewModel.isPasswordValid) {
                    onLoginSuccess() // ✅ call navigation here
                }
            },
            enabled = viewModel.isNameValid && viewModel.isPasswordValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@Composable
fun NameInput(
    name: String,
    onNameChange: (String) -> Unit,
    isError: Boolean
) {
    TextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        isError = isError,
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordInput(
    password: String,
    onPasswordChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (isVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
            val description = if (isVisible) "Hide password" else "Show password"
            IconButton(onClick = onVisibilityToggle) {
                Icon(imageVector = icon, contentDescription = description)
            }
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}