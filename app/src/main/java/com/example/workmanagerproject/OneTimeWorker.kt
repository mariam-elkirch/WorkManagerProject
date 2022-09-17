package com.example.workmanagerproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.*
import java.util.concurrent.TimeUnit

class OneTimeWorker (context: Context, params: WorkerParameters): CoroutineWorker(context,params)  {
    override suspend fun doWork(): ListenableWorker.Result {

        val alarmIntent = Intent(applicationContext,WakeUpActivity::class.java)
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

       applicationContext.startActivity(alarmIntent)

        Log.i("tag","work mmmmmmmmmmmm")

        return  ListenableWorker.Result.success()

    }



}