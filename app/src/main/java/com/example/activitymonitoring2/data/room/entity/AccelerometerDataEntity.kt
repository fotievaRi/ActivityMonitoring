package com.example.activitymonitoring2.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_accelerometer_data")
data class AccelerometerDataEntity(
    @PrimaryKey
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "x") val x: Double,
    @ColumnInfo(name = "y") val y: Double,
    @ColumnInfo(name = "z") val z: Double,
)