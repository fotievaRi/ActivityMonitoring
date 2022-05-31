package com.example.activitymonitoring2.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.activitymonitoring2.data.room.entity.UserFeelDataEntity

@Dao
interface DaoDataUserFeel {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserFeelData(userData: UserFeelDataEntity)

//    @Query("SELECT * FROM table_user_feel_data WHERE time = :date LIMIT 1")
//    fun getUserDataFeelByDate(ti: Date): List<Flow<UserFeelDataEntity?>>

    @Query("SELECT * FROM table_user_feel_data")
    fun getUserAllDataFeel(): List<UserFeelDataEntity>

    @Query("DELETE FROM table_user_feel_data")
    suspend fun deleteAll()

    @Query("SELECT * FROM table_user_feel_data WHERE time > :startDay AND time < :endDay")
    fun getUserDataFeelByDay(startDay: Long, endDay: Long): List<UserFeelDataEntity>

    @Query("DELETE FROM table_user_feel_data WHERE time > :startDay AND time < :endDay")
    fun deleteUserFeelDataByDay(startDay: Long, endDay: Long)
}