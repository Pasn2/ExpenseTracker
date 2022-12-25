package com.example.expencetracker

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expencetracker.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class fragment_income : Fragment(R.layout.fragment_income) {
    private lateinit var IncomeAdapter: IncomeViewAdapter
    private lateinit var IncomeRecyclerView: RecyclerView
    private lateinit var IncomeList: ArrayList<ExpenceItemData>
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        IncomeList = arrayListOf<ExpenceItemData>()
        EventChangeListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        val FloatButton:FloatingActionButton = view.findViewById(R.id.IncomefloatingActionButton)
        FloatButton.setOnClickListener{
            val intent = Intent(context,AddExpenceDataActivity::class.java).apply {}
            intent.putExtra("Who","Income")
            startActivity(intent)
        }

        val LayoutManager = LinearLayoutManager(context)
        IncomeRecyclerView = view.findViewById(R.id.IncomeRecyclerView)
        IncomeRecyclerView.layoutManager = LayoutManager
        IncomeRecyclerView.setHasFixedSize(true)
        IncomeAdapter = IncomeViewAdapter(IncomeList)
        IncomeRecyclerView.adapter = IncomeAdapter
    }
    fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        db.collection("Users").document(Firebase.auth.uid.toString()).collection("Income").addSnapshotListener{ snapshot, e ->
            if(e != null){
                Log.w(ContentValues.TAG,"Failed $e")
            }
            if(snapshot != null){
                for (document in snapshot.documents) {
                    println("${document.id} => ${document.data}")
                    IncomeList.add(ExpenceItemData(document.id,document.get("Descryption") as String,document.get("Cost") as String,document.get("Category") as String,))
                }
            }

        }




    }
}