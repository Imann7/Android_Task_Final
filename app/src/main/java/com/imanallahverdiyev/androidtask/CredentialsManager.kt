package com.mobileapps.lesson2

import android.content.Context
import android.content.SharedPreferences

class CredentialsManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun register(email: String, password: String): String {
        sharedPreferences.edit().apply {
            putString("user_email", email)
            putString("user_password", password)
            apply()
        }
        return "Success"
    }

    fun isEmailValid(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean = password.isNotEmpty()

    fun getStoredEmail(): String? = sharedPreferences.getString("user_email", null)

    fun getStoredPassword(): String? = sharedPreferences.getString("user_password", null)
}
