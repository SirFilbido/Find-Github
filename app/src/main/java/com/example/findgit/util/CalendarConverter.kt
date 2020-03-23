package com.example.findgit.util

import androidx.room.TypeConverter
import java.util.*

object CalendarConverter {

    @TypeConverter
    @JvmStatic
    fun calendarParaLong(timestamp: Calendar?): Long? = timestamp?.timeInMillis

    @TypeConverter
    @JvmStatic
    fun longParaCalendar(value: Long?): Calendar? = value?.let {
        GregorianCalendar().also { calendar ->
            calendar.timeInMillis = it
        }
    }

}