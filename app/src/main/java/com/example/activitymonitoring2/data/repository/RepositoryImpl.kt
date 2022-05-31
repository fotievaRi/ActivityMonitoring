package com.example.activitymonitoring2.data.repository

import com.example.activitymonitoring2.data.room.dao.DaoAccelerometerData
import com.example.activitymonitoring2.data.room.dao.DaoDataUserFeel
import com.example.activitymonitoring2.di.anatations.DispatcherIo
import com.example.activitymonitoring2.domain.Repository
import com.example.activitymonitoring2.domain.usecase.accelerometer.AccelerometerDataUseCaseModel
import com.example.activitymonitoring2.domain.usecase.user_data.UserFeelDataUseCaseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val daoAccelerometerData: DaoAccelerometerData,
    private val daoDataUserFeel: DaoDataUserFeel,
    @DispatcherIo private val dispatcherIo: CoroutineDispatcher,
): Repository {

    override suspend fun saveAccelerometerData(accelerometerData: AccelerometerDataUseCaseModel) {
        withContext(dispatcherIo) {
            daoAccelerometerData.insertAccelerometerData(accelerometerData.mapToEntity())
        }
    }

    override suspend fun saveUserFeelData(userFeelData: UserFeelDataUseCaseModel) {
        withContext(dispatcherIo) {
            daoDataUserFeel.insertUserFeelData(userFeelData.mapToEntity())
        }
    }

    override fun getUserFeelData(): List<UserFeelDataUseCaseModel> {
        return daoDataUserFeel.getUserAllDataFeel().map { it.mapToDomainModel() }
    }

    override fun getAccelerometerData(): List<AccelerometerDataUseCaseModel> {
        return  daoAccelerometerData.getAccelerometerData().map { it.mapToDomainModel() }

    }

    override fun getAccelerometerDataByDay(
        start: Long,
        end: Long
    ): List<AccelerometerDataUseCaseModel> {
        return daoAccelerometerData.getAccelerometerDataByDay(start, end)
            .map {it.mapToDomainModel() }
    }

    override fun deleteAccelerometerDataByDay(start: Long, end: Long) {
        daoAccelerometerData.deleteAccelerometerDataByDay(start, end)
    }

    override fun getUserFeelDataBuDay(start: Long, end: Long): List<UserFeelDataUseCaseModel> {
        return  daoDataUserFeel.getUserDataFeelByDay(start, end)
            .map { it.mapToDomainModel() }
    }

    override fun deleteUserFeelDataByDay(start: Long, end: Long) {
        daoDataUserFeel.deleteUserFeelDataByDay(start, end)
    }
}