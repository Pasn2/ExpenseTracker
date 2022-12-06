package com.example.expencetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val EmailEditText:EditText = findViewById(R.id.forgotpassEmailEditText)
        val ResetBtn:Button = findViewById(R.id.forgotpassResetBtn)
        ResetBtn.setOnClickListener {
            if (EmailEditText.text.isNullOrEmpty()) return@setOnClickListener
            Firebase.auth.sendPasswordResetEmail(EmailEditText.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"Email sended",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}