package com.example.drifrs // Replace with your package name

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hide the action bar for a cleaner splash screen
        supportActionBar?.hide()

        // Use a Handler to delay the start of the MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an Intent to start the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finish the SplashActivity so the user can't go back to it
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
