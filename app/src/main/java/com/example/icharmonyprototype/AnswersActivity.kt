package com.example.icharmonyprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AnswersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)

        val backButton = findViewById<FloatingActionButton>(R.id.backButtonAnswers)

        backButton.setOnClickListener {
            finish()
        }
    }
}