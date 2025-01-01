package com.mobileapps.lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_register, container, false)

        val emailEditText: EditText = binding.findViewById(R.id.emailEditText)
        val passwordEditText: EditText = binding.findViewById(R.id.passwordEditText)
        val confirmPasswordEditText: EditText = binding.findViewById(R.id.confirmPasswordEditText)
        val registerButton: Button = binding.findViewById(R.id.registerButton)
        val loginButton: Button = binding.findViewById(R.id.loginButton)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            when {
                password != confirmPassword -> showToast("Passwords do not match")
                !credentialsManager.isEmailValid(email) || !credentialsManager.isPasswordValid(password) ->
                    showToast("Invalid email or password")
                else -> {
                    showToast(credentialsManager.register(email, password))
                    navigateToLoginFragment()
                }
            }
        }

        loginButton.setOnClickListener {
            navigateToLoginFragment()
        }

        return binding
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLoginFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LoginFragment())
            .addToBackStack(null)
            .commit()
    }
}
