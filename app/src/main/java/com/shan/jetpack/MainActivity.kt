package com.shan.jetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lint.kotlin.metadata.Visibility
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
                        CounterExample()
                        Spacer(Modifier.padding(20.dp))
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

