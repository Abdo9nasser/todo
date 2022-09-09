package com.asfdfsd.todo.DATABASE.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class databamodel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id :Int?=null,
    @ColumnInfo
    var title :String?=null,
    @ColumnInfo

    var descraption :String?=null,
    @ColumnInfo
    var isdone:Boolean?=null,
    @ColumnInfo
    var date:Date?=null



)
