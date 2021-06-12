package com.vignesh.medilite
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMedicinesRVAdapter  {

    lateinit var viewModal: MedicineViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(this)
        val adapter = MedicineRVAdapter(this, this)
        recycleView.adapter = adapter

        viewModal = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MedicineViewModal::class.java)
        

        viewModal.allMedicines.observe(this, Observer {list->list?.let {
            adapter.updateList(it)
        }})

        addMedicine.setOnClickListener {
            val intent = Intent(this, add_med::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onItemClicked(medicine: Medicine) {
        viewModal.deleteMedicine(medicine)
    }
}