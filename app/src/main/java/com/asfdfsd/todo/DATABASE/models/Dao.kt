package com.asfdfsd.todo.DATABASE.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface Dao  {
    @Insert
    fun insert(entity :databamodel)
    @Delete
    fun delete(entity :databamodel)
    @Update
    fun update(entity :databamodel)
    @Query("select *from databamodel")
    fun gitalltodo():List<databamodel>
    @Query("select *from databamodel where date=:date")
    fun get_where(date:Date):List<databamodel>

}