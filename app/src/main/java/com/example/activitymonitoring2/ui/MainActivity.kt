package com.example.activitymonitoring2.ui

import android.app.ActivityManager
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Switch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.activitymonitoring2.R
import com.example.activitymonitoring2.services.ReadingAccelerometerDataService
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var prefs: SharedPreferences? = null
    var prefsEdit: SharedPreferences.Editor? = null
    val PREFS_NAME = "com.example.activitymonitoring_prefs"
    val IS_FIRST_RUN = "firstRun"
    val SWITCH_STATE = "switchState"

//    val appDirString = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString() + "/" + "activitymonitoring" + "/"

    private val viewModel: MainActivityViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        prefsEdit = prefs?.edit()
        if (prefs?.getBoolean(IS_FIRST_RUN, true) == true) {
            prefsEdit?.putBoolean(IS_FIRST_RUN, false)?.apply()
            setContentView(R.layout.activity_welcome)
            val intentWelcomeActivity = Intent(this@MainActivity, WelcomeActivity::class.java)
            startActivity(intentWelcomeActivity)
        }

        setContentView(R.layout.activity_main)

        setupView()

        val serviceReadingAccelerometerDataClass = ReadingAccelerometerDataService::class.java
        val intentReadingAccelerometerData = Intent(this, serviceReadingAccelerometerDataClass)
        if (!isServiceRunning(serviceReadingAccelerometerDataClass)) {
            startService(intentReadingAccelerometerData)
        }

    }

    private fun setupView() {

        val switchFeel: Switch = findViewById(R.id.switchFeel)

        switchFeel.isChecked = prefs?.getBoolean(SWITCH_STATE, true) == true
        switchFeel.setOnCheckedChangeListener { _, b ->
            viewModel.saveUserFeelData(System.currentTimeMillis(), b)
            prefsEdit?.putBoolean(SWITCH_STATE, b)?.apply()
        }

    }

//    private fun saveFeelUserData() {
//        writeUserFeelData(viewModel.getUserFeelAllData())
//    }
//
//    private fun saveAccelerometerData() {
//        viewModel.getAccelerometerData().map {
//            writeAccelerometerData(it)
//        }
//    }
//
//    private fun writeAccelerometerData(accelerometerData: List<AccelerometerDataUseCaseModel>) {
//        val appDirString = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString() + "/" + "ActivityMonitoring" + "/"
//        val letDirectory = File(appDirString, "LET")
//        letDirectory.mkdirs()
//        val file = File(letDirectory, "AccelerometerData.txt")
//        file.appendText(accelerometerData.toString())
//    }
//
//    private fun writeUserFeelData(userFeel: List<UserFeelDataUseCaseModel>) {
//        Toast.makeText(
//            applicationContext,
//            "writeUserFeelData", Toast.LENGTH_SHORT
//        ).show()
//        Log.i("Start", "writeUserFeelData")
//        val appDirString = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString() + "/" + "ActivityMonitoring" + "/"
//        val letDirectory = File(appDirString, "LET")
//        letDirectory.mkdirs()
//        val file = File(letDirectory, "UserFeelData.txt")
//        file.appendText(userFeel.toString())
//        Toast.makeText(
//            applicationContext,
//            "writeUserFeelData finish", Toast.LENGTH_SHORT
//        ).show()
//        Log.i("Finish", "writeUserFeelData")
//    }

    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }

}