package com.example.expencetracker

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

class ExpenceViewAdapter(private val ExpenceItemList: ArrayList<ExpenceItemData>): RecyclerView.Adapter<ExpenceViewAdapter.MyViewHolder>() {


    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.expence_card,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = ExpenceItemList[position]
        holder.ExpenceName.text = currentItem.Name
        holder.ExpenceDescrytpion.text = currentItem.Descryption
        holder.ExpenceCost.setText("${currentItem.Cost} PLN")

        holder.ExpenceCardView.setOnClickListener {
            val intent = Intent(context,ExpenceDetailsActivity::class.java)
            intent.putExtra("Who","Expence")
            intent.putExtra("Title",currentItem.Name)
            intent.putExtra("Descryption",currentItem.Descryption)
            intent.putExtra("Cost",currentItem.Cost)
            intent.putExtra("Category",currentItem.Category)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        println("CCCCCCC ${ExpenceItemList.size}")
        return ExpenceItemList.size
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val ExpenceName: TextView = itemView.findViewById(R.id.ExpenceName)
        val ExpenceDescrytpion: TextView =itemView.findViewById(R.id.ExpenceDescryption)
        val ExpenceCost: TextView = itemView.findViewById(R.id.ExpenceValue)
        val ExpenceCardView:CardView = itemView.findViewById(R.id.ExpenceCardView)


    }
}