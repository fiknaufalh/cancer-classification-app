package com.dicoding.asclepius.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter {
    companion object {
        fun longToDatetime(timestamp: Long): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val date = Date(timestamp)
            return dateFormat.format(date)
        }
    }
}