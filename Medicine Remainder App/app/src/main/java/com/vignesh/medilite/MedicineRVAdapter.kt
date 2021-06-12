package com.vignesh.medilite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicineRVAdapter(private val context : Context, private val listener: IMedicinesRVAdapter) : RecyclerView.Adapter<MedicineRVAdapter.MedicineViewHolder>() {

    val allMedicines = ArrayList<Medicine>()

    inner class MedicineViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val medListName = itemView.findViewById<TextView>(R.id.medListName)
        val createdAt = itemView.findViewById<TextView>(R.id.createdAt)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val viewHolder= MedicineViewHolder(LayoutInflater.from(context).inflate(R.layout.medicine_item, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allMedicines[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val currentMedicine = allMedicines[position]
        holder.medListName.text = currentMedicine.medicine_name
        holder.createdAt.text = Utils.getTimeAgo(currentMedicine.updated_at).toString()
    }

    override fun getItemCount(): Int {
        return allMedicines.size
    }

    fun updateList(newList: List<Medicine>){
        allMedicines.clear()
        allMedicines.addAll(newList)
        notifyDataSetChanged()
    }
}

interface IMedicinesRVAdapter{
    fun onItemClicked(medicine: Medicine)
}