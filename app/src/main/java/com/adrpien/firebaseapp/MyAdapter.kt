package com.adrpien.firebaseapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dataArray: ArrayList<DatabaseModel>): RecyclerView.Adapter<MyHolder>() {

    override fun getItemCount(): Int {
    return dataArray.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.name.text = dataArray[holder.adapterPosition].name
        holder.surname.text = dataArray[holder.adapterPosition].surname

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recyclerview_row, parent, false)
        return MyHolder(view)
    }

}

class MyHolder(view: View): RecyclerView.ViewHolder(view){

    val name = view.findViewById<TextView>(R.id.nameTextView)
    val surname = view.findViewById<TextView>(R.id.surnameTaxtView)

}
