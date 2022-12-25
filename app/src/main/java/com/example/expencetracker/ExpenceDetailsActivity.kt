package com.example.expencetracker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.expencetracker.databinding.ActivityAddExpenceDataBinding.inflate
import com.example.expencetracker.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ExpenceDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_expence_details)
        val db = Firebase.firestore
        val UserId = Firebase.auth.currentUser?.uid.toString()
        val TitleEdit = findViewById<EditText>(R.id.ExpenceEditTitleEditText)
        val DescryptionEdit = findViewById<EditText>(R.id.ExpenceEditDescryptionEditText)
        val CategoryEdit = findViewById<EditText>(R.id.ExpenceEditCategoryEditText)
        val CostEdit = findViewById<EditText>(R.id.ExpenceEditCostEditText)
        val SaveBtn = findViewById<Button>(R.id.ExpenceEditSavetBtn)
        val DeleteBtn = findViewById<Button>(R.id.ExpenceEditDeletetBtn)
        val CurrentExpenceName: String? = intent.getStringExtra("Title")
        val WhoAsk = intent.getStringExtra("Who")
        TitleEdit.setText(intent.getStringExtra("Title"))
        DescryptionEdit.setText(intent.getStringExtra("Descryption"))
        CategoryEdit.setText(intent.getStringExtra("Category"))
        if (TitleEdit.text == null){
            return
        }
        var SaveData = hashMapOf("Name" to TitleEdit.text.toString(), "Descryption" to DescryptionEdit.text.toString(), "Category" to CategoryEdit.text.toString(), "Cost" to "Null")

        if(WhoAsk == "Expence"){
            SaveBtn.setOnClickListener {
                AddData(db,SaveData,UserId,"Expence",TitleEdit.text.toString(),applicationContext)
            }
            DeleteBtn.setOnClickListener {
                DeleteData(db,"Expence",UserId,TitleEdit.text.toString())
            }
        }
        else if (WhoAsk == "Income"){
            SaveBtn.setOnClickListener {
                AddData(db,SaveData,UserId,"Income",TitleEdit.text.toString(),applicationContext)
            }
            DeleteBtn.setOnClickListener {
                DeleteData(db,"Income",UserId,TitleEdit.text.toString())
            }
        }





    }
    fun AddData(db:FirebaseFirestore,hashdata:HashMap<String,String>, UserID:String,where:String,docname:String,context: Context){

        db.collection("Users").document(UserID).collection(where).document(docname).set(hashdata).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val intent = Intent(context,MainActivity::class.java)
                    context.startActivity(intent)
                Toast.makeText(context,"Sucesfull saved", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context,"Save Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun DeleteData(db:FirebaseFirestore,where: String,UserID:String,docname:String){
        db.collection("Users").document(UserID).collection(where).document(docname).delete()
            .addOnSuccessListener { Toast.makeText(applicationContext,"Sucesfull deleted", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)}
            .addOnFailureListener { Toast.makeText(applicationContext,"Error", Toast.LENGTH_SHORT).show() }
    }
}