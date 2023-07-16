package com.example.surveyapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(val name: String, val age: Int, val phoneNumber: Int, val email: String) :
    Parcelable