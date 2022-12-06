package com.example.expencetracker

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val RegisterBtn: Button = findViewById(R.id.registerRegisterBtn)
        val LoginBtn: Button = findViewById(R.id.registerLoginBtn)
        val EmailEditText:EditText = findViewById(R.id.registerEmailEditText)
        val LoginEditText:EditText = findViewById(R.id.registerLoginEditText)
        val PasswordEditText:EditText = findViewById(R.id.registerPasswordEditText)
        val db = Firebase.firestore
        RegisterBtn.setOnClickListener {
            if (EmailEditText.text.isNullOrEmpty() || PasswordEditText.text.isNullOrEmpty()) return@setOnClickListener
            Firebase.auth.createUserWithEmailAndPassword(EmailEditText.text.toString(),PasswordEditText.text.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Successfully signed in with email link!", Toast.LENGTH_SHORT).show()
                    val UserId = Firebase.auth.currentUser?.uid.toString()
                    val data = hashMapOf(
                        "UserUID" to UserId,
                        "Login" to LoginEditText.text.toString()
                    )
                    val dummy = hashMapOf(
                        "Name" to "TestName",
                        "Descryption" to "TestDescryption",
                        "Cost" to 30,
                        "Category" to "TestCategory")
                    db.collection("Users").document(UserId).set(data).addOnCompleteListener {
                        task -> if (task.isSuccessful){
                                    println("KOXXXXXXXXXXXXXXXXXXXXXX")
                                    db.collection("Users").document(UserId).collection("Notes").document("Testt").set(dummy)
                                }
                    }
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "Error signing in with email link", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        LoginBtn.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}