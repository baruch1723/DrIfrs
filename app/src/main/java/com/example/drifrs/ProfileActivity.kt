package com.example.drifrs

import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            db.collection("users").document(it.uid).get().addOnSuccessListener { document ->
                if (document != null) {
                    findViewById<TextView>(R.id.textViewName).text = "Name: ${document.getString("name")}"
                    findViewById<TextView>(R.id.textViewEmail).text = "Email: ${document.getString("email")}"
                    findViewById<TextView>(R.id.textViewWork).text = "Work: ${document.getString("work")}"
                    findViewById<TextView>(R.id.textViewEducation).text = "Education: ${document.getString("education")}"
                }
            }
        }
    }
}