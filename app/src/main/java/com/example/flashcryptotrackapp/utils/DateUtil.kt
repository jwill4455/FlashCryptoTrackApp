package com.example.flashcryptotrackapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertTimestampToDate(timestamp: Double): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = Date(timestamp.toLong())
    return sdf.format(date)
}

fun getCurrentDate(): Pair<String, String> {
    val sdfDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val sdfTime = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val currentDate = sdfDate.format(Date())
    val currentTime = sdfTime.format(Date())

    return Pair(currentDate, currentTime)
}