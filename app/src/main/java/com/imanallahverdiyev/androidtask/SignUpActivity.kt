package com.mobileapps.lesson2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        credentialsManager = CredentialsManager(this)

        val fullNameEditText: EditText = findViewById(R.id.fullNameEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val phoneNumberEditText: EditText = findViewById(R.id.phoneNumberEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val confirmPasswordEditText: EditText = findViewById(R.id.confirmPasswordEditText)
        val termsCheckBox: CheckBox = findViewById(R.id.termsCheckBox)
        val nextButton: Button = findViewById(R.id.nextButton)
        val loginTextView: TextView = findViewById(R.id.loginTextView)

        nextButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val phoneNumber = phoneNumberEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            when {
                fullName.isEmpty() -> showToast("Please enter your full name")
                email.isEmpty() -> showToast("Please enter your email")
                !credentialsManager.isEmailValid(email) -> showToast("Please enter a valid email")
                phoneNumber.isEmpty() -> showToast("Please enter your phone number")
                password.isEmpty() -> showToast("Please enter a password")
                confirmPassword.isEmpty() -> showToast("Please confirm your password")
                password != confirmPassword -> showToast("Passwords do not match")
                !termsCheckBox.isChecked -> showToast("Please agree to the terms and conditions")
                else -> {
                    val result = credentialsManager.register(email, password)
                    if (result == "Success") {
                        showToast("Registration successful!")
                        navigateToAccount()
                    } else {
                        showToast(result)
                    }
                }
            }
        }

        loginTextView.setOnClickListener { navigateToAccount() }
    }

    private fun navigateToAccount() {
        startActivity(Intent(this, AccountActivity::class.java))
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
