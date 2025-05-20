package com.shan.jetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.shan.jetpack.ui.theme.JetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Apply innerPadding and add additional padding if needed
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
//                        CounterExample()
//                        Spacer(Modifier.padding(20.dp))
//                        TextInputExample()
//                        Spacer(Modifier.padding(20.dp))
//                        PasswordInputExample()
//                        Spacer(Modifier.padding(20.dp))
                        LoginForm()
                    }
                }
            }
        }
    }
}

@Composable
fun CounterExample() {
    var count by remember { mutableStateOf(0) }
    var toastShown by remember { mutableStateOf(false) }

    val context = LocalContext.current

    if (count == 10 && !toastShown) {
        Toast.makeText(context, "Count reached 10!", Toast.LENGTH_SHORT).show()
        toastShown = true
    }

    // Reset toastShown when count drops below 10
    if (count < 10 && toastShown) {
        toastShown = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Count: $count")
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { count-- }, enabled = count > 0) {
                Text("Decrement")
            }
            Button(onClick = { count++ }) {
                Text("Increment")
            }
        }
    }
}

// Example: Greeting Based on User Input
@Composable
fun TextInputExample() {
    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { input ->
                if (input.length <= 10) {
                    name = input
                }
            },
            label = { Text("Enter your name") },
            singleLine = true,
            trailingIcon = {
                if(name.isNotEmpty()) {
                    IconButton( onClick = {name = ""}) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear text")
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(8.dp))

        if (name.isBlank()) {
            Text("⚠️ Name cannot be empty", color = Color.Red)
        } else {
            Text("Hello, $name!")
        }
    }
}

@Composable
fun PasswordInputExample() {
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter password") },
            singleLine = true,
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (isVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(imageVector = icon, contentDescription = "Toggle password visibility")
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Password length: ${password.length}")
    }
}


@Composable
fun LoginForm() {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Name input
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            singleLine = true,
            isError = name.isNotBlank() && !name.first().isUpperCase()
        )

        if (name.isNotBlank() && !name.first().isUpperCase()) {
            Text(
                "Name must start with a capital letter",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Password input
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(icon, contentDescription = "Toggle password visibility")
                }
            }
        )

        // Submit button (enabled only if both fields are valid)
        val isNameValid = name.isNotBlank() && name.first().isUpperCase()
        val isPasswordValid = password.length >= 6

        Button(
            onClick = { /* Handle login here */ },
            enabled = isNameValid && isPasswordValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        // Optional feedback
        if (!isPasswordValid && password.isNotBlank()) {
            Text(
                "Password must be at least 6 characters",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
