package com.example.activitymonitoring2.domain

import com.example.activitymonitoring2.domain.usecase.accelerometer.AccelerometerDataUseCaseModel
import com.example.activitymonitoring2.domain.usecase.user_data.UserFeelDataUseCaseModel

interface Repository {

    suspend fun saveAccelerometerData(AccelerometerData: AccelerometerDataUseCaseModel)

    suspend fun saveUserFeelData(userFeelData: UserFeelDataUseCaseModel)

    fun getUserFeelData(): List<UserFeelDataUseCaseModel>

    fun getAccelerometerData(): List<AccelerometerDataUseCaseModel>

    fun getAccelerometerDataByDay(start: Long, end: Long): List<AccelerometerDataUseCaseModel>

    fun deleteAccelerometerDataByDay(start: Long, end: Long)

    fun getUserFeelDataBuDay(start: Long, end: Long): List<UserFeelDataUseCaseModel>

    fun deleteUserFeelDataByDay(start: Long, end: Long)

}