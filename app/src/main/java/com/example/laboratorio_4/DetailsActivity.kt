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
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {
    private lateinit var nameText: TextView
    private lateinit var directionsText: TextView
    private lateinit var scheduleText: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var appointmentButton: Button
    private val CAMERA_PERMISSION_CODE = 12
    private lateinit var rootLayout:ConstraintLayout;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Texto de Llamada
        phoneNumber = findViewById(R.id.callButton)
        appointmentButton = findViewById(R.id.initializeAppointment)
        nameText = findViewById(R.id.restaurantName)
        directionsText = findViewById(R.id.restaurantDirections)
        scheduleText = findViewById(R.id.restaurantSchedule)
        rootLayout = findViewById(R.id.rootConstraint)

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
            checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE)
        }
    }

    private fun checkPermission(permission:String,requestCode:Int){
        if(ContextCompat.checkSelfPermission(this,permission)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(permission),requestCode)
        } else{
            Toast.makeText(this,"Permiso otorgado",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permiso de Camara Otorgado", Toast.LENGTH_SHORT).show()
            } else{
                checkRequestRationale()
            }
        }
    }

    private fun checkRequestRationale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                val snackbar = Snackbar.make(
                    rootLayout,
                    "Acceso a cámara es necesario para poder tomar fotos",
                    Snackbar.LENGTH_INDEFINITE
                )
                snackbar.setAction("Ok"){

                    checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE)
                }
                snackbar.show()
            } else {
                checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE)
            }
        }
    }
}
