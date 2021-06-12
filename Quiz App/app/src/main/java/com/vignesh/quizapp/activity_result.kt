package com.vignesh.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class activity_result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        hideSystemUI()

        val tv_name = findViewById<TextView>(R.id.tv_name) as TextView
        val tv_score = findViewById<TextView>(R.id.tv_score) as TextView
        val btn_finish = findViewById<Button>(R.id.btn_finish) as Button

        val username = intent.getStringExtra(Constants.USER_NAME)
        val score = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val total_questions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)


        tv_name.text = username

        tv_score.text = "Your Score is $score out of $total_questions"

        btn_finish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}