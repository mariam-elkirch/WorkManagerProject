package com.example.workmanagerproject

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MotionEventCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class WakeUpActivity : AppCompatActivity() {
    lateinit var objectAnimator : ObjectAnimator
    private lateinit var textScreen : View
    val DEFAULT_ANIMATION_DURATION = 2500L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up)
        textScreen = findViewById(R.id.screen)
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON

        )


        objectAnimator = ObjectAnimator.ofObject(
            textScreen,
            "backgroundColor",
            ArgbEvaluator(),
            ContextCompat.getColor(this, R.color.white),
            ContextCompat.getColor(this, R.color.black)
        )

// 2
        objectAnimator.repeatCount = 1000
        objectAnimator.repeatMode = ValueAnimator.REVERSE

// 3
        objectAnimator.duration = DEFAULT_ANIMATION_DURATION
        objectAnimator.start()


    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (MotionEventCompat.getActionMasked(event)) {

            MotionEvent.ACTION_MOVE -> {
                objectAnimator.cancel()

                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}