package com.example.activitymonitoring2.domain.usecase.accelerometer

import com.example.activitymonitoring2.domain.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccelerometerDataUseCase@Inject constructor(private val repository: Repository, private val coroutineScope: CoroutineScope) {

    fun insertAccelerometerData(time: Long, x: Double, y: Double, z: Double) {
        coroutineScope.launch {
            repository.saveAccelerometerData(AccelerometerDataUseCaseModel(time, x, y, z))
        }

    }

    fun getAllAccelerometerData(): List<AccelerometerDataUseCaseModel> {
        return repository.getAccelerometerData()
    }

    fun getAccelerometerDataByDay(start: Long, end: Long): List<AccelerometerDataUseCaseModel> {
        return repository.getAccelerometerDataByDay(start, end)
    }

    fun deleteAccelerometerDataByDay(start: Long, end: Long) {
        repository.deleteAccelerometerDataByDay(start, end)
    }

}