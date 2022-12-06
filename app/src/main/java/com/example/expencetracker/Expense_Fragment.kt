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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class Expense_Fragment : Fragment(R.layout.fragment_expense_) {

    private lateinit var ExpenceAdapter: ExpenceViewAdapter
    private lateinit var ExpenceRecyclerView: RecyclerView
    private lateinit var ExpenceList: ArrayList<ExpenceItemData>
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ExpenceList = arrayListOf<ExpenceItemData>()
        val FloatButton:FloatingActionButton = view.findViewById(R.id.AddExpenceBtn)
        FloatButton.setOnClickListener {
            val intent = Intent(context,AddExpenceDataActivity::class.java).apply {}
            startActivity(intent)
        }

        val ExpenceLayoutManager = LinearLayoutManager(context)
        ExpenceRecyclerView = view.findViewById(R.id.ExpenceRecyclerView)
        ExpenceRecyclerView.layoutManager = ExpenceLayoutManager
        ExpenceRecyclerView.setHasFixedSize(true)
        ExpenceAdapter = ExpenceViewAdapter(ExpenceList)
        ExpenceRecyclerView.adapter = ExpenceAdapter
        EventChangeListener()

    }
    fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS ${Firebase.auth.uid}")
        db.collection("Users").document(Firebase.auth.uid.toString()).collection("Notes").addSnapshotListener{ snapshot,e ->
            if(e != null){
                Log.w(ContentValues.TAG,"Failed $e")
            }
            if(snapshot != null){
                println("ILOSCCCCCCCCCCCCCCCCCCCCCCCCCCCCC ${snapshot.documents.size}")
                for (document in snapshot.documents){

                    println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD${document.toObject<ExpenceItemData>()}")
                }
            }

        }




    }

}