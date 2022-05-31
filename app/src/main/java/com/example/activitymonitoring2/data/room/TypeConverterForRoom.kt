package com.example.activitymonitoring2.data.room

import androidx.room.TypeConverter
import java.util.*

class TypeConverterForRoom {
    @TypeConverter
    fun fromDateToLong(date: Date): Long = date.time

    @TypeConverter
    fun fromLongToDate(seconds: Long): Date = Date(seconds)
}