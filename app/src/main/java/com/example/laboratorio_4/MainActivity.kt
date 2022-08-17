package com.example.laboratorio_4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var startButton: Button
    private lateinit var downloadText: TextView
    private lateinit var directionsArrow: ImageView
    private lateinit var detailsButton: Button

    private lateinit var name: TextView;
    private lateinit var directions: TextView;
    private lateinit var schedule: TextView;
    lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Boton de Direcciones
        directionsArrow = findViewById(R.id.directionsArrow)
        //Boton de Descarga
        downloadText = findViewById(R.id.download_Text)
        //Boton de Toast
        startButton = findViewById(R.id.startButton)
        //Boton de Toast
        detailsButton = findViewById(R.id.detailsButton)

        name = findViewById(R.id.restaurantName)
        directions = findViewById(R.id.directionsText)
        schedule = findViewById(R.id.ScheduleText)

        initListeners()
    }

    private fun initListeners() {
        startButton.setOnClickListener {
            Toast.makeText(applicationContext, "Andrea Ximena Ramirez Recinos", Toast.LENGTH_LONG)
                .show()
        }
        downloadText.setOnClickListener {
            val instagramURL = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android&hl=es_GT&gl=US")
            )
            startActivity(instagramURL)
        }

        directionsArrow.setOnClickListener {
            val restaurantDirection = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.gt/maps/place/Denny's+Vista+Hermosa/@14.5907212,-90.4916113,17.73z/data=!4m12!1m6!3m5!1s0x8589a3a8bda9bd31:0x48dcc59fd53bea16!2sDenny's+Vista+Hermosa!8m2!3d14.5907526!4d-90.4911771!3m4!1s0x8589a3a8bda9bd31:0x48dcc59fd53bea16!8m2!3d14.5907526!4d-90.4911771?hl=es"))
            startActivity(restaurantDirection)
        }

        detailsButton.setOnClickListener {
            val Sname = name.text.toString()
            val Sdirection = directions.text.toString()
            val Sschedule = schedule.text.toString()
            data = Data(Sname, Sdirection, Sschedule)

            val changeToDetailsAct = Intent(this, DetailsActivity::class.java)
            intent.putExtra("restaurantInformation", data);
            startActivity(changeToDetailsAct);
        }
    }
}
