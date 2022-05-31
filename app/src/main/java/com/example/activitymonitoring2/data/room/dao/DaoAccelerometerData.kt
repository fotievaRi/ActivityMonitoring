package com.example.activitymonitoring2.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.activitymonitoring2.data.room.entity.AccelerometerDataEntity

@Dao
interface DaoAccelerometerData {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccelerometerData(accelerometerData: AccelerometerDataEntity)

    @Query("SELECT * FROM table_accelerometer_data")
    fun getAccelerometerData(): List<AccelerometerDataEntity>

    @Query("DELETE FROM table_accelerometer_data")
    suspend fun deleteAll()

    @Query("SELECT * FROM table_accelerometer_data WHERE time > :startDay AND time < :endDay")
    fun getAccelerometerDataByDay(startDay: Long, endDay: Long) : List<AccelerometerDataEntity>

    @Query("DELETE FROM table_accelerometer_data WHERE time > :startDay AND time < :endDay")
    fun deleteAccelerometerDataByDay(startDay: Long, endDay: Long)
}