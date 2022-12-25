package com.example.expencetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val DescriptionEditText:EditText = findViewById(R.id.ExpenceEditDescryptionEditText)
        val Cost:EditText = findViewById(R.id.ExpenceCostEditNumber)

        val Extra = intent.getStringExtra("Who")
        SaveBtn.setOnClickListener{
                if (NameEditText.text.isNullOrEmpty() || DescriptionEditText.text.isNullOrEmpty() || Cost.text.isNullOrEmpty()) return@setOnClickListener
                val data = hashMapOf("Name" to NameEditText.text.toString(),"Descryption" to DescriptionEditText.text.toString(),"Cost" to Cost.text.toString(),"Category" to "None")
                val UserId = Firebase.auth.currentUser?.uid.toString()
            if (Extra == "Expence"){
                AddData(data,UserId,"Expence",NameEditText.text.toString())

            }
            else if(Extra == "Income"){
                AddData(data,UserId,"Income",NameEditText.text.toString())
            }
        }
    }
    fun AddData(hashdata:HashMap<String,String>, UserID:String,where:String,docname:String){
        val db = Firebase.firestore
        db.collection("Users").document(UserID).collection(where).document(docname).set(hashdata).addOnCompleteListener { task ->
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