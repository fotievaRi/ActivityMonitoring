package com.example.activitymonitoring2.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.activitymonitoring2.R
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

    //private val binding: ActivityWelcomeBinding by lazy { ActivityWelcomeBinding.inflate(layoutInflater) }

    var prefs: SharedPreferences? = null
    var prefsEdit: SharedPreferences.Editor? = null
    val PREFS_NAME = "com.example.activitymonitoring_prefs"
    val FIRST_NAME = "firstName"
    val SECOND_NAME = "secondName"

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        prefsEdit = prefs?.edit()

        setContentView(R.layout.activity_welcome)
        val intent = Intent(this, MainActivity::class.java)
        val btNext: Button = findViewById(R.id.btNext)
        val firstName: TextInputEditText = findViewById(R.id.firstName)
        val secondName: TextInputEditText = findViewById(R.id.secondName)
        btNext.setOnClickListener {
            prefsEdit?.putString(FIRST_NAME, firstName.text.toString())
            prefsEdit?.putString(SECOND_NAME, secondName.text.toString())
            startActivity(intent)
        }
//        with(binding) {
//            btNext.setOnClickListener {
//                prefsEdit?.putString(FIRST_NAME, firstName.text.toString())
//                prefsEdit?.putString(SECOND_NAME, secondName.text.toString())
//                startActivity(intent)
//            }
//        }
    }
}