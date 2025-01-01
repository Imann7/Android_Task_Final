package com.mobileapps.lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_login, container, false)

        val emailEditText: EditText = binding.findViewById(R.id.emailEditText)
        val passwordEditText: EditText = binding.findViewById(R.id.passwordEditText)
        val loginButton: Button = binding.findViewById(R.id.loginButton)
        val registerButton: Button = binding.findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (credentialsManager.isEmailValid(email) && credentialsManager.isPasswordValid(password)) {
                if (email == credentialsManager.getStoredEmail() && password == credentialsManager.getStoredPassword()) {
                    showToast("Login successful")
                } else {
                    showToast("Invalid credentials")
                }
            } else {
                showToast("Invalid email or password")
            }
        }

        registerButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }

        return binding
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
