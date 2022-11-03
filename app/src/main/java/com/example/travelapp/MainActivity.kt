package com.example.travelapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import java.io.IOError
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var mCity: String = ""
    private var mCountry: String = ""
    private var cityNameList: MutableList<City> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val addcityBT = findViewById<Button>(R.id.addcityBT)

        addcityBT.setOnClickListener {
            startMapActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        val addcityRV = findViewById<RecyclerView>(R.id.addcityRV)

        try {
            val city = City(mCity, mCountry, "")
            Log.i("RightHere: ", mCity)
            cityNameList.add(city)
            val citylistAdapter = CitylistAdapter(cityNameList)
            addcityRV.adapter = citylistAdapter
            addcityRV.layoutManager = LinearLayoutManager(this)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    var editActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // If the user comes back to this activity from EditActivity
        // with no error or cancellation
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            // Get the data passed from EditActivity
            if (data != null) {
                val cityCountry = data.extras!!.getString("newString")
                if (cityCountry != null) {
                    Log.i("THERE", cityCountry)
                    val temp = cityCountry.split(",").toTypedArray()
                    mCity = temp[0]
                    mCountry = temp[1]
                }
            }
        }
    }

    fun startMapActivity() {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("stringToEdit", "CodePath")
        editActivityResultLauncher.launch(intent)
    }
}