package com.example.activitymonitoring2.di.modules

import android.content.Context
import androidx.room.Room
import com.example.activitymonitoring2.data.repository.RepositoryImpl
import com.example.activitymonitoring2.data.room.MonitoringDataBase
import com.example.activitymonitoring2.data.room.dao.DaoAccelerometerData
import com.example.activitymonitoring2.data.room.dao.DaoDataUserFeel
import com.example.activitymonitoring2.di.anatations.DispatcherIo
import com.example.activitymonitoring2.di.anatations.ReleaseDataBase
import com.example.activitymonitoring2.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    //  REPOSITORY
    @Provides
    @Singleton
    fun provideRepository(
        daoAccelerometerData: DaoAccelerometerData,
        daoDataUserFeel: DaoDataUserFeel,
        @DispatcherIo dispatcherIo: CoroutineDispatcher,
    ): Repository {
        return RepositoryImpl(daoAccelerometerData, daoDataUserFeel, dispatcherIo)
    }

    //  ROOM
    @ReleaseDataBase
    @Provides
    @Singleton
    fun provideMonitoringDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MonitoringDataBase::class.java, MonitoringDataBase.dataBaseName)
            .build()

    @Provides
    @Singleton
    fun provideDaoAccelerometerData(@ReleaseDataBase dataBase: MonitoringDataBase) =
        dataBase.getDaoAccelerometerData()

    @Provides
    @Singleton
    fun provideDaoDataUserFeel(@ReleaseDataBase dataBase: MonitoringDataBase) =
        dataBase.getDaoDataUserFeel()
}
