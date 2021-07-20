package com.example.icharmonyprototype

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly

// TODO: 7/18/2021 Make being logged in a persistent activity

class MainActivity : AppCompatActivity() {

    // Stored for later use
    private var userPhone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Finding the button and text entry
        val signInButton = findViewById<Button>(R.id.signIn)
        val textEntry = findViewById<EditText>(R.id.textEntry)

        // Setting On Click Listener for the Sign in/up button
        signInButton.setOnClickListener {
            if (validPhone(textEntry.text.toString())){

                userPhone = textEntry.text.toString()
                Toast.makeText(this, "You entered $userPhone", Toast.LENGTH_SHORT).show()
                goToAuth()
            }
            textEntry.text.clear()
        }

        // Utilizes a lambda function which listens for the enter key to be pressed on the soft
        // keyboard
        textEntry.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {

                if (validPhone(textEntry.text.toString())){

                    userPhone = textEntry.text.toString()
                    Toast.makeText(this, "You entered $userPhone", Toast.LENGTH_SHORT).show()
                    goToAuth()
                }
                closeKeyboard(textEntry)
                textEntry.text.clear()
                return@OnKeyListener true
            }
            false
        })
    }

    // Input validation for phone entry
    private fun validPhone(userPhone: String?): Boolean {
        when {
            userPhone?.length != 10 -> {
                Toast.makeText(
                    this, "Enter a 10 digit phone number",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
            !userPhone.isDigitsOnly() -> {
                Toast.makeText(
                    this, "Non-digits detected! Enter a 10 digit phone number",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
            else -> return true
        }
    }

    //Closes the soft keyboard based on what View the user is entering text into.
    private fun closeKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Used by floatingActionButton in activity_main.xml
    fun goToHelp(view: View) {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }

    private fun goToAuth() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.putExtra("userPhone", userPhone)
        startActivity(intent)
    }

    // Intent that sends user to their preferred web browser to view the company's "About" webpage.
    private val webIntent: Intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://rstangesdev153.s3.amazonaws.com/FinalProject/about.html")
    )

    fun goToWeb(view: View) {
        startActivity(webIntent)
    }
}