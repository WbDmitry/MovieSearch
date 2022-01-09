package com.wbdmitry.moviesearch

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

private const val NOTIFICATION_ID = 1
private const val CHANNEL_ID = "ACCESS_FINE_LOCATION"
private const val CHANNEL_NAME = "ACCESS_FINE_LOCATION"

class Notification { fun sendNotification(context: Context, title: String, body: String) {

    val notificationManager = NotificationManagerCompat.from(context)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationManager.createNotificationChannel(createNotificationChannel())
    }

    val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_movie_24)
        .setColor(Color.BLUE)
        .setContentTitle(title)
        .setContentText(body)

    notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
}

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        return NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
    }
}