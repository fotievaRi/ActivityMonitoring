package com.example.activitymonitoring2.di.modules

import com.example.activitymonitoring2.di.anatations.DispatcherDefault
import com.example.activitymonitoring2.di.anatations.DispatcherIo
import com.example.activitymonitoring2.di.anatations.DispatcherMain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope =
        CoroutineScope(Dispatchers.Main)

    @DispatcherMain
    @Provides
    @Singleton
    fun provideDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @DispatcherIo
    @Provides
    @Singleton
    fun provideDispatcherIo(): CoroutineDispatcher = Dispatchers.IO

    @DispatcherDefault
    @Provides
    @Singleton
    fun provideDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

}