package com.example.expencetracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class IncomeViewAdapter(private val IncomeItemList: ArrayList<ExpenceItemData>): RecyclerView.Adapter<IncomeViewAdapter.MyViewHolder>() {


    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.expence_card,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = IncomeItemList[position]
        holder.ExpenceName.text = currentItem.Name
        holder.ExpenceDescrytpion.text = currentItem.Descryption
        holder.ExpenceCost.setText("${currentItem.Cost} PLN")

        holder.ExpenceCardView.setOnClickListener {
            val intent = Intent(context,ExpenceDetailsActivity::class.java)
            intent.putExtra("Who","Income")
            intent.putExtra("Title",currentItem.Name)
            intent.putExtra("Descryption",currentItem.Descryption)
            intent.putExtra("Cost",currentItem.Cost)
            intent.putExtra("Category",currentItem.Category)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        println("CCCCCCC ${IncomeItemList.size}")
        return IncomeItemList.size
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val ExpenceName: TextView = itemView.findViewById(R.id.ExpenceName)
        val ExpenceDescrytpion: TextView =itemView.findViewById(R.id.ExpenceDescryption)
        val ExpenceCost: TextView = itemView.findViewById(R.id.ExpenceValue)
        val ExpenceCardView: CardView = itemView.findViewById(R.id.ExpenceCardView)


    }
}
