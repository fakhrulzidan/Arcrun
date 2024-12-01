package com.belajar.arcruneo.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.arcruneo.Adapter.EventAdapter
import com.belajar.arcruneo.Model.EventModel
import com.belajar.arcruneo.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()

        binding.addBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, TambahEventActivity::class.java)
            startActivity(intent)
        }
        initViewEvent()
    }
    private fun initViewEvent() {
        val myRef: DatabaseReference = database.getReference("TiketEvents")
        val items = ArrayList<EventModel>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        val event = issue.getValue(EventModel::class.java)
                        if (event != null) {
                            items.add(event)
                            Log.d("FirebaseData", "Loaded event: ${event.nama_event}")
                        }
                    }
                    if (items.isNotEmpty()) {
                        binding.viewEvent.layoutManager = LinearLayoutManager(
                            this@MainActivity, LinearLayoutManager.VERTICAL, false
                        )
                        binding.viewEvent.adapter = EventAdapter(items, this@MainActivity)
                    } else {
                        Toast.makeText(this@MainActivity, "No events found.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "No data in Firebase.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Failed to load data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}