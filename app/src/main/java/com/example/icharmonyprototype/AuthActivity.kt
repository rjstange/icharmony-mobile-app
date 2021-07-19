package com.example.icharmonyprototype

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Receiving userPhone from previous intent
        val userPhone = intent.getStringExtra("userPhone")

        val phoneDisplay = findViewById<TextView>(R.id.userPhone)

        phoneDisplay.text = userPhone

        // Will eventually be randomly generated
        val authCode = "156732"

        val authCodeDisplay = findViewById<TextView>(R.id.authCode)

        authCodeDisplay.text = authCode

        val textEntry = findViewById<EditText>(R.id.codeEntry)

        textEntry.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                closeKeyboard(textEntry)

                if (textEntry.text.toString() == authCode) {
                    textEntry.text.clear()
                    goToSurvey()
                }
                else{
                    Toast.makeText(this, "Invalid code, please try again!", Toast.LENGTH_SHORT)
                        .show()
                    textEntry.text.clear()
                }
                return@OnKeyListener true
            }
            false
        })
    }

    //Closes the soft keyboard based on what View the user is entering text into.
    fun closeKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun goToSurvey() {
        val intent = Intent(this, SurveyActivity::class.java)
        startActivity(intent)
    }
}