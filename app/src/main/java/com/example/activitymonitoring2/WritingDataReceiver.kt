package com.example.activitymonitoring2

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.activitymonitoring2.services.WritingDataInFileService
import java.util.*


class WritingDataReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 24)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val startServiceIntent = Intent(context, WritingDataInFileService::class.java)
        val pi = PendingIntent.getService(
            context, 0, startServiceIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi)
    }
}