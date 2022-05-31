package com.example.activitymonitoring2.domain.usecase.user_data

import com.example.activitymonitoring2.domain.Repository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class UserFeelDataUseCase@Inject constructor(private val repository: Repository, private val coroutineScope: CoroutineScope) {

    suspend fun insertUserFeelData(time: Long, active: Boolean) {
        val activeInt = if(active) 1 else 0
        repository.saveUserFeelData(UserFeelDataUseCaseModel(time, activeInt))
    }

    fun getUserFeelAllData() = repository.getUserFeelData()

    fun getUserFeelDataByDay(start: Long, end: Long): List<UserFeelDataUseCaseModel> {
        return repository.getUserFeelDataBuDay(start, end)
    }

    fun deleteUserFeelDataByDay(start: Long, end: Long) {
        repository.deleteUserFeelDataByDay(start, end)
    }
}