package com.vignesh.medilite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table")
class Medicine(@ColumnInfo(name = "medicine_name") val medicine_name: String,
               @ColumnInfo(name = "medicine_time") val medicine_time: Long,
               @ColumnInfo(name = "updated_at") val updated_at: Long){
    @PrimaryKey(autoGenerate = true) var id = 0
}