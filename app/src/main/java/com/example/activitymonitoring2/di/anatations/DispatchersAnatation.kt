package com.example.activitymonitoring2.di.anatations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherMain()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherDefault()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherIo()

