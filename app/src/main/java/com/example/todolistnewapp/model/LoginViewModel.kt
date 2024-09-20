package com.example.todolistapp.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun emailFlow(context: Context): Flow<String?> {
        return UserPreferences.emailFlow(context)
    }
    fun passwordFlow(context: Context):Flow<String?> {
       return UserPreferences.passwordFlow(context)
    }


    fun saveCredentials(context: Context, email: String, password: String) {
        viewModelScope.launch {
            UserPreferences.saveCredentials(context, email, password)
        }
    }

    fun clearCredentials(context: Context) {
        viewModelScope.launch {
            UserPreferences.clearCredentials(context)
        }
    }
}