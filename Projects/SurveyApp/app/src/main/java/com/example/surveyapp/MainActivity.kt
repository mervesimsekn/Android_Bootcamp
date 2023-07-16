package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.surveyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var girisBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        girisBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(girisBinding.root)

        girisBinding.btnPersonalInfoGecis.setOnClickListener {
            val name = girisBinding.etAdSoyad.text.toString()
            val personalInfoIntent = Intent(this@MainActivity, PersonalInfo::class.java)
            personalInfoIntent.putExtra("name", name)
            startActivity(personalInfoIntent)
        }
    }
}