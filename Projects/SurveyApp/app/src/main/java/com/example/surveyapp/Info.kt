package com.example.surveyapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(val users: Users?,val answer1: String, val answer2: String, val answer3: String) :
    Parcelable