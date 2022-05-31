package com.example.activitymonitoring2.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitymonitoring2.domain.usecase.accelerometer.AccelerometerDataUseCase
import com.example.activitymonitoring2.domain.usecase.user_data.UserFeelDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject public constructor(private val userFeelDataUseCase: UserFeelDataUseCase,
                                                       private val accelerometerDataUseCase: AccelerometerDataUseCase):
    ViewModel(), LifecycleObserver {


    fun saveUserFeelData(time:Long, active: Boolean) {
        viewModelScope.launch {
            userFeelDataUseCase.insertUserFeelData(time, active)
        }
    }

    fun getUserFeelAllData() = userFeelDataUseCase.getUserFeelAllData()

    fun getAccelerometerData() = accelerometerDataUseCase.getAllAccelerometerData()
}