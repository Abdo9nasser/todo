package com.asfdfsd.todo

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@TypeConverters
class convert {
    @TypeConverter

    fun fromdate(data: Date): Long {
        return data.time
    }

    @TypeConverter

    fun todate(data: Long): Date {
        return Date(data)
    }
}