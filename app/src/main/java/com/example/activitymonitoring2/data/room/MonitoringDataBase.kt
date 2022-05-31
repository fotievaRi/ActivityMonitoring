package com.example.activitymonitoring2.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.activitymonitoring2.data.room.dao.DaoAccelerometerData
import com.example.activitymonitoring2.data.room.dao.DaoDataUserFeel
import com.example.activitymonitoring2.data.room.entity.AccelerometerDataEntity
import com.example.activitymonitoring2.data.room.entity.UserFeelDataEntity

@Database(
    entities = [
        AccelerometerDataEntity::class,
        UserFeelDataEntity::class,
    ],
    version = 1
)

@TypeConverters(TypeConverterForRoom::class)
abstract class MonitoringDataBase: RoomDatabase()  {

    abstract fun getDaoAccelerometerData(): DaoAccelerometerData
    abstract fun getDaoDataUserFeel(): DaoDataUserFeel

    companion object {
        const val dataBaseName = "monitoring_date_base"
    }
}