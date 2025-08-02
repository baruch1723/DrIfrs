package com.example.drifrs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val nameEditText: EditText = findViewById(R.id.editTextName)
        val emailEditText: EditText = findViewById(R.id.editTextEmail)
        val workEditText: EditText = findViewById(R.id.editTextWork)
        val educationEditText: EditText = findViewById(R.id.editTextEducation)
        val passwordEditText: EditText = findViewById(R.id.editTextPassword)
        val signUpButton: Button = findViewById(R.id.buttonSignUp)

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        val userMap = hashMapOf(
                            "name" to nameEditText.text.toString(),
                            "email" to email,
                            "work" to workEditText.text.toString(),
                            "education" to educationEditText.text.toString()
                        )
                        user?.let {
                            db.collection("users").document(it.uid).set(userMap)
                                .addOnSuccessListener {
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                        }
                    } else {
                        Toast.makeText(this, "Sign Up failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
