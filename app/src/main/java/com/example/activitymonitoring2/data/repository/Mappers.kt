package com.example.activitymonitoring2.data.repository

import com.example.activitymonitoring2.data.room.entity.AccelerometerDataEntity
import com.example.activitymonitoring2.data.room.entity.UserFeelDataEntity
import com.example.activitymonitoring2.domain.usecase.accelerometer.AccelerometerDataUseCaseModel
import com.example.activitymonitoring2.domain.usecase.user_data.UserFeelDataUseCaseModel


fun AccelerometerDataEntity.mapToDomainModel(): AccelerometerDataUseCaseModel {
    return AccelerometerDataUseCaseModel(
        time = this.time,
        x = this.x,
        y = this.y,
        z = this.z
    )
}

fun AccelerometerDataUseCaseModel.mapToEntity(): AccelerometerDataEntity {
    return AccelerometerDataEntity(
        time = this.time,
        x = this.x,
        y = this.y,
        z = this.z
    )
}

fun UserFeelDataEntity.mapToDomainModel(): UserFeelDataUseCaseModel {
    return UserFeelDataUseCaseModel(
        time = this.time,
        active = this.active
    )
}

fun UserFeelDataUseCaseModel.mapToEntity(): UserFeelDataEntity {
    return UserFeelDataEntity(
        time = this.time,
        active = this.active
    )
}


