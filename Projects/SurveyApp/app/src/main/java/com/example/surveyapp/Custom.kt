package com.example.surveyapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi


import com.example.surveyapp.databinding.ActivityCustomBinding

class Custom : AppCompatActivity() {
    private lateinit var custom: ActivityCustomBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        custom = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(custom.root)

        val user = intent.getParcelableExtra("user", Users::class.java)

        custom.tvNameDisplay.text = user?.name.toString()

        custom.btnSonucGecis.setOnClickListener {
            val answerA = custom.etA1.text.toString()
            val answer2 = custom.etA2.text.toString()
            val answer3 = custom.etA3.text.toString()

            val info = Info(user, answerA, answer2, answer3)

            val resultsIntent = Intent(this@Custom, Results::class.java)
            resultsIntent.putExtra("info", info)
            finish()
            startActivity(resultsIntent)
        }

    }
}