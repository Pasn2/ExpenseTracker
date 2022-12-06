package com.example.expencetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddExpenceDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expence_data)
        val SaveBtn:Button = findViewById(R.id.ExpenceSaveBtn)
        val NameEditText:EditText = findViewById(R.id.ExpenceNameEditText)
        val DescriptionEditText:EditText = findViewById(R.id.ExpenceDescryptionEditText)
        val Cost:EditText = findViewById(R.id.ExpenceCostEditNumber)
        val db = Firebase.firestore

        SaveBtn.setOnClickListener{
            if (NameEditText.text.isNullOrEmpty() || DescriptionEditText.text.isNullOrEmpty() || Cost.text.isNullOrEmpty()) return@setOnClickListener
            val data = hashMapOf("Name" to NameEditText.text.toString(),"Descryption" to DescriptionEditText.text.toString(),"Cost" to Cost.text.toString(),"Category" to "None")
            val UserId = Firebase.auth.currentUser?.uid.toString()
            db.collection("Users").document(UserId).collection("Notes").document(NameEditText.text.toString()).set(data).addOnCompleteListener { task ->
                if (task.isSuccessful){
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext,"Save Failed",Toast.LENGTH_SHORT).show()
                }
            }



        }
    }
}