package com.example.myapplication.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.TimeZone

object DateFormatter {
    @SuppressLint("SimpleDateFormat")
    fun format(inputDateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Assuming input date is in UTC timezone
        val inputDate = inputFormat.parse(inputDateString)
        val outputFormat = SimpleDateFormat("MMMM d, yyyy")
        return if (inputDate != null)
            outputFormat.format(inputDate)
        else ""
    }
}