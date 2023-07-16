package com.example.surveyapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.surveyapp.databinding.ActivityResultsBinding

class Results : AppCompatActivity() {
    private lateinit var results: ActivityResultsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        results = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(results.root)

        val info = intent.getParcelableExtra("info", Info::class.java)

        results.tvName.text = info?.users?.name.toString()
        results.tvAge.text = info?.users?.age.toString()
        results.tvPhoneNumber.text = info?.users?.phoneNumber.toString()
        results.tvEmail.text = info?.users?.email.toString()

        results.tvAnswer1.text = info?.answer1.toString()
        results.tvAnswer2.text = info?.answer2.toString()
        results.tvAnswer3.text = info?.answer3.toString()

        results.btnCikis.setOnClickListener {
            val intent = Intent(this@Results, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }
}