package com.vignesh.dmeo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var counter = 0
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Counter Application"
    }
    fun startTimeCounter(view: View) {
        val countTime: TextView = findViewById(R.id.countTime)
        timer = object : CountDownTimer(99990000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = counter.toString()
                counter++
            }

            override fun onFinish() {
                countTime.text = "Finished"
            }
        }.start()

    }

    fun stopTimer(view: View) {
        timer.cancel()
        val countTime: TextView = findViewById(R.id.countTime)
        countTime.text = "Stopped @ $counter"
        counter = 0
    }
}