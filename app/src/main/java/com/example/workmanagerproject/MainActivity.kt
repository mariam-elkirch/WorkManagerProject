package com.example.workmanagerproject

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
            !android.provider.Settings.canDrawOverlays(applicationContext))
        {
            ActivityResultContracts.RequestPermission()
        }

        checkDrawOverlayPermission()
        val reminderRequest = OneTimeWorkRequest.Builder(OneTimeWorker::class.java)
            .setInitialDelay(20, TimeUnit.SECONDS)
            .addTag("alarms")
            .build()
        WorkManager.getInstance(this).enqueue(reminderRequest)
    }

    private fun checkDrawOverlayPermission() {

        if (!android.provider.Settings.canDrawOverlays(applicationContext)) {
            Log.v("App", "Requesting Permission" + android.provider.Settings.canDrawOverlays(applicationContext));

            intent =  Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + applicationContext.packageName));
            val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
            resultLauncher.launch(intent)



        } else {
            Log.v("App", "We already have permission for it.");

        }
    }
}