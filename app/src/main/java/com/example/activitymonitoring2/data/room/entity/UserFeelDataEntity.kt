package com.example.activitymonitoring2.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user_feel_data")
data class UserFeelDataEntity(
    @PrimaryKey
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "active") val active: Int, // 1 - active, 0 - passive
)