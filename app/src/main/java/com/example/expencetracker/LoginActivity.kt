package com.example.expencetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val RegisterBtn: Button = findViewById(R.id.loginRegisterBtn)
        val LoginBtn: Button = findViewById(R.id.loginLoginBtn)
        val EmailEditText:EditText = findViewById(R.id.loginEmailEditText)
        val PasswordEditText:EditText = findViewById(R.id.loginPasswordEditText)
        val ForgotPasswordBtn:Button = findViewById(R.id.loginForgotPasswordBtn)
        RegisterBtn.setOnClickListener{
            val intent = Intent(applicationContext,RegisterActivity::class.java)
            startActivity(intent)
        }
        LoginBtn.setOnClickListener{
            if (EmailEditText.text.isNullOrEmpty() || PasswordEditText.text.isNullOrEmpty()) return@setOnClickListener

            Firebase.auth.signInWithEmailAndPassword(EmailEditText.text.toString(),PasswordEditText.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext, "Successfully login in with email link!",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext, "Error login in with email link", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        ForgotPasswordBtn.setOnClickListener {
            val intent = Intent(applicationContext,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }


}