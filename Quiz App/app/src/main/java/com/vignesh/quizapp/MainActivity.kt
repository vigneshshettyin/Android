package com.vignesh.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideSystemUI()

        val btn_start = findViewById<Button>(R.id.btn_start) as Button

        val et_name = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.et_name) as androidx.appcompat.widget.AppCompatEditText

        btn_start.setOnClickListener{
            if(et_name.text.toString().isEmpty())
                Toast.makeText(this, "Please enter your name!!", Toast.LENGTH_SHORT).show()
            //TODO else move to activity_quiz_question.xml
            else{
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
                startActivity(intent)
                finish()
            }
        }


        }


    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
                                }
    }