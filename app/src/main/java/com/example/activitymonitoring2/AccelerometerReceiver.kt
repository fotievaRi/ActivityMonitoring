package com.example.activitymonitoring2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.activitymonitoring2.services.ReadingAccelerometerDataService


class AccelerometerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val startServiceIntent = Intent(context, ReadingAccelerometerDataService::class.java)
        context.startService(startServiceIntent)
    }

}