package com.asfdfsd.todo.DATABASE.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asfdfsd.todo.convert

@Database(entities = [databamodel::class], version = 1)
@TypeConverters(convert::class)
abstract class mydatabase : RoomDatabase() {
    abstract fun getDAO(): Dao

    companion object {
        var data: mydatabase? = null
        fun getinstance(context: Context): mydatabase {
            if (data == null) {
                data = Room.databaseBuilder(context, mydatabase::class.java, "mydatabase")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }

            return data!!

        }

    }
}