package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.surveyapp.databinding.ActivityPersonalInfoBinding

class PersonalInfo : AppCompatActivity() {
    private lateinit var personalInfo: ActivityPersonalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personalInfo = ActivityPersonalInfoBinding.inflate(layoutInflater)
        setContentView(personalInfo.root)

        val name = intent.getStringExtra("name")

        personalInfo.tvMerhaba.text = "Merhaba $name"

        personalInfo.btnCustomGecis.setOnClickListener {
            val age = personalInfo.etAge.text.toString()
            val phoneNumber = personalInfo.etPhoneNumber.text.toString()
            val email = personalInfo.etEmail.text.toString()

            val user = Users(name!!, age.toInt(), phoneNumber.toInt(), email)

            val customIntent = Intent(this@PersonalInfo, Custom::class.java)
            customIntent.putExtra("user", user)
            finish()
            startActivity(customIntent)
        }
    }
}