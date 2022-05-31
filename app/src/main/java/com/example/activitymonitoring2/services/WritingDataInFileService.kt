package com.example.activitymonitoring2.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.example.activitymonitoring2.domain.usecase.accelerometer.AccelerometerDataUseCase
import com.example.activitymonitoring2.domain.usecase.user_data.UserFeelDataUseCase
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class WritingDataInFileService : Service() {

    @Inject
    lateinit var userFeelDataUseCase: UserFeelDataUseCase

    @Inject
    lateinit var accelerometerDataUseCase: AccelerometerDataUseCase


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        intent?.let {
//            when (intent.action) {
//                ACTION_START_SERVICE -> startForeground(NOTIF_ID, notifStep.createNotification())
//                ACTION_PAUSE_SERVICE -> stopSelf()
//            }
//        }
        return START_NOT_STICKY
    }

    override fun onCreate() {
        val userFeel = userFeelDataUseCase.getUserFeelAllData()
        val appDirString = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString() + "/" + "ActivityMonitoring" + "/"
        val letDirectory = File(appDirString, "LET")
        letDirectory.mkdirs()
        val file = File(letDirectory, "UserFeelData.txt")
        file.appendText(userFeel.toString())
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    companion object {
        private const val ACTION_START_SERVICE = "ACTION_START_SERVICE"
        private const val ACTION_PAUSE_SERVICE = "ACTION_POUSE_SERVICE"

        fun startService(context: Context) {
            val intent = Intent(context, WritingDataInFileService::class.java)
            intent.action = ACTION_START_SERVICE
            ContextCompat.startForegroundService(context, intent)
        }

    }
}