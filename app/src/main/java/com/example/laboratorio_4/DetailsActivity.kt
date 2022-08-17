package com.example.laboratorio_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    private lateinit var nameText: TextView
    private lateinit var directionsText: TextView
    private lateinit var scheduleText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        nameText = findViewById(R.id.restaurantName)
        directionsText = findViewById(R.id.restaurantDirections)
        scheduleText = findViewById(R.id.restaurantSchedule)

        val data: Data = intent.getSerializableExtra("restaurantInformation") as Data

        nameText.text = data.name
        directionsText.text = data.directions
        scheduleText.text = data.schedule

        initListeners()
    }

    private fun initListeners() {
    }
}