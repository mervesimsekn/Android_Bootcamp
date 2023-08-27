package com.example.budgetapp.ui.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.budgetapp.R
import com.example.budgetapp.common.viewBinding
import com.example.budgetapp.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        if (auth.currentUser != null) {
            findNavController().navigate(R.id.signInToSummary)
        }

        with(binding) {
            btnSignIn.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    signIn(email, password)
                } else {
                    Toast.makeText(requireContext(), "Fill Required Fields !", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            tvDontHaveAnAccount.setOnClickListener {
                findNavController().navigate(R.id.signInToSignUp)
            }
        }

    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val action = SignInFragmentDirections.signInToSummary()
                    findNavController().navigate(action)
                } else {
                    // task.exception?.message.orEmpty()
                }
            }
    }

}