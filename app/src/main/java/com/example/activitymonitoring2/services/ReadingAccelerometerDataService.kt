package com.example.activitymonitoring2.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.example.activitymonitoring2.domain.usecase.accelerometer.AccelerometerDataUseCase
import com.example.activitymonitoring2.services.NotificationService.Companion.NOTIF_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReadingAccelerometerDataService: Service(), SensorEventListener {

    @Inject
    lateinit var accelerometerDataUseCase: AccelerometerDataUseCase

    @Inject
    lateinit var notiService: NotificationService

    @Inject
    lateinit var sensorManager: SensorManager

    //private val accelerometerDataReading = mutableListOf<AccelerometerData>()

    //private var time: Long = 0

//    class AccelerometerData (
//        val time: Long,
//        val x: Float,
//        val y: Float,
//        val z: Float
//    )

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (intent.action) {
                ACTION_START_SERVICE -> startForeground(NOTIF_ID, notiService.createNotification())
                ACTION_PAUSE_SERVICE -> stopSelf()
            }
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        startListenSensor()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
       if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
           //accelerometerDataReading.add(AccelerometerData(time, event.values[0], event.values[1], event.values[2]))
           accelerometerDataUseCase.insertAccelerometerData(System.currentTimeMillis(), event.values[0].toDouble(), event.values[1].toDouble(), event.values[2].toDouble())
           notiService.updateNotification(event.values[0], event.values[1], event.values[2])
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        stopListenSensor()
        val restartIntent = Intent(this, ReadingAccelerometerDataService::class.java)
        startService(restartIntent)
//        val am = getSystemService(ALARM_SERVICE) as AlarmManager
//        val pi = PendingIntent.getService(this, 1, restartIntent, PendingIntent.FLAG_ONE_SHOT)
//        am.setExact(AlarmManager.RTC, System.currentTimeMillis(), pi)
    }

    override fun onDestroy() {
//        val size = accelerometerDataReading.size
//        val s = accelerometerDataReading.map {
//            (sqrt((it.x * it.x + it.y * it.y + it.z * it.z).toDouble()) - 9.81).pow(2)
//        }.sum()
//        accelerometerDataUseCase.insertAccelerometerData(time, sqrt(s/size), 0.0, 0.0)
        super.onDestroy()
        stopListenSensor()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    private fun startListenSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    private fun stopListenSensor() {
        sensorManager.unregisterListener(this)
    }


    companion object {
        private const val ACTION_START_SERVICE = "ACTION_START_SERVICE"
        private const val ACTION_PAUSE_SERVICE = "ACTION_POUSE_SERVICE"

        fun startService(context: Context) {
            val intent = Intent(context, ReadingAccelerometerDataService::class.java)
            intent.action = ACTION_START_SERVICE
            ContextCompat.startForegroundService(context, intent)
        }

        //НА БУДУЩЕЕ
        fun pauseService(context: Context) {
            val intent = Intent(context, ReadingAccelerometerDataService::class.java)
            intent.action = ACTION_PAUSE_SERVICE
            ContextCompat.startForegroundService(context, intent)
        }
    }

}