package com.example.laboratorio_4
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {
    private lateinit var nameText: TextView
    private lateinit var directionsText: TextView
    private lateinit var scheduleText: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var appointmentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Texto de Llamada
        phoneNumber = findViewById(R.id.callButton)
        appointmentButton = findViewById(R.id.initializeAppointment)
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
        phoneNumber.setOnClickListener {
            val phoneNum = "+50222925151"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(intent)
        }
        appointmentButton.setOnClickListener {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 0)
        }
    }


}
