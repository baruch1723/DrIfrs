package com.example.drifrs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*

class ContactActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val questionEditText: EditText = findViewById(R.id.editTextQuestion)
        val answerEditText: EditText = findViewById(R.id.editTextAnswer)
        val suggestedAnswerEditText: EditText = findViewById(R.id.editTextSuggestedAnswer)
        val sendButton: Button = findViewById(R.id.buttonSend)
        val thanksTextView: TextView = findViewById(R.id.textViewThanks)

        sendButton.setOnClickListener {
            val question = questionEditText.text.toString()
            val answer = answerEditText.text.toString()
            val suggestedAnswer = suggestedAnswerEditText.text.toString()

            val emailBody = """
                Question:
                $question

                Answer:
                $answer

                Suggested Answer:
                $suggestedAnswer
            """.trimIndent()

            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("your-email@example.com"))
                putExtra(Intent.EXTRA_SUBJECT, "App Feedback")
                putExtra(Intent.EXTRA_TEXT, emailBody)
            }

            try {
                startActivity(Intent.createChooser(emailIntent, "Send email..."))
                thanksTextView.visibility = View.VISIBLE
                questionEditText.text.clear()
                answerEditText.text.clear()
                suggestedAnswerEditText.text.clear()
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}