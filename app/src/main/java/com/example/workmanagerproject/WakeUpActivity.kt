package com.example.workmanagerproject

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MotionEventCompat


class WakeUpActivity : AppCompatActivity() {
    lateinit var objectAnimator : ObjectAnimator
    private lateinit var textScreen : View
    private val DEFAULT_ANIMATION_DURATION = 2500L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up)
        textScreen = findViewById(R.id.screen)
        allowOnLockScreen()
        animationAppearance()

    }

    private fun animationAppearance() {
        objectAnimator = ObjectAnimator.ofObject(
            textScreen,
            "backgroundColor",
            ArgbEvaluator(),
            ContextCompat.getColor(this, R.color.white),
            ContextCompat.getColor(this, R.color.black)
        )

        objectAnimator.repeatCount = 10000
        objectAnimator.repeatMode = ValueAnimator.REVERSE

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
    private fun allowOnLockScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        } else {
            this.window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        }
    }
}