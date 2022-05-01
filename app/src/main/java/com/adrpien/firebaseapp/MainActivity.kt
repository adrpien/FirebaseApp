package com.adrpien.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.adrpien.firebaseapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listOfItems: ArrayList<DatabaseModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listRecyclerView.layoutManager = GridLayoutManager(applicationContext, 1)

        val firebaseDatabase = FirebaseDatabase.getInstance("https://fir-app-788fb-default-rtdb.firebaseio.com/")
        val ref = firebaseDatabase.getReference("MyDatabase")

        binding.addButton.setOnClickListener {

            val name = binding.nameEditText.text.toString()
            val surname = binding.surnameEditText.text.toString()
            val row = DatabaseModel(name, surname)
            ref.child("${Date().time}").setValue(row)
        }

        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                listOfItems = ArrayList()
                for(item in dataSnapshot.children){
                    val newRow = item.getValue(DatabaseModel::class.java)
                    listOfItems.add(newRow!!)
                }
                setupAdapter(listOfItems)

            }

            override fun onCancelled(p0: DatabaseError) {
            }

        })
    }

    private fun setupAdapter(arrayList: ArrayList<DatabaseModel>){
        binding.listRecyclerView.adapter = MyAdapter(arrayList)
    }
}