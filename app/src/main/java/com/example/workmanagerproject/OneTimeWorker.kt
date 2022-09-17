package com.example.workmanagerproject


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.*


class OneTimeWorker (context: Context, params: WorkerParameters): CoroutineWorker(context,params)  {
    override suspend fun doWork(): Result {

        val alarmIntent = Intent(applicationContext,WakeUpActivity::class.java)
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

       applicationContext.startActivity(alarmIntent)

        Log.i("tag","work manager")

        return  Result.success()

    }



}