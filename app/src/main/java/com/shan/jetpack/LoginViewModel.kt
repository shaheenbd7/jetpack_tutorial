package com.shan.jetpack

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var name by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false)
        private set

    val isNameValid: Boolean
        get() = name.isNotBlank() && name.first().isUpperCase()

    val isPasswordValid: Boolean
        get() = password.length >= 6

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
    }
}