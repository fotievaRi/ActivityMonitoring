package com.example.activitymonitoring2.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.MainThread
import androidx.core.app.NotificationCompat
import com.example.activitymonitoring2.R
import com.example.activitymonitoring2.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationService@Inject constructor(
    @ApplicationContext
    private val context: Context,
) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(context, NOTIF_CHANNEL_ID)
            .setAutoCancel(false)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setContentText("x: 0, y: 0, z: 0")
            .setSmallIcon(R.drawable.ic_step_light)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(openActivityFromNotification())
    }

    private fun openActivityFromNotification(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).also {
            it.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun createNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIF_CHANNEL_ID,
                NOTIF_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationBuilder.build()
    }

    @MainThread
    fun updateNotification(x: Float, y: Float, z: Float) {
        notificationManager.notify(
            NOTIF_ID,
            notificationBuilder
                .setContentText("x: $x, y: $y, z: $z")
                .build()
        )
    }


    companion object {
        const val NOTIF_ID = 1
        const val NOTIF_CHANNEL_ID = "CHANNEL_ID_NOTIFICATION_STEP_COUNTER"
        const val NOTIF_CHANNEL_NAME = "CHANNEL_NAME_"
    }
}