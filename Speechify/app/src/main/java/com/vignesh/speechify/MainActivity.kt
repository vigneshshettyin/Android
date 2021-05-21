package com.vignesh.speechify

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val speakButton = findViewById<Button>(R.id.speakButton) as Button

        speakButton.setOnClickListener {
            val et_text_input = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.getText) as com.google.android.material.textfield.TextInputEditText

            val text = et_text_input.text.toString().trim()
            if (text.isNotEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeechEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
                } else {
                    textToSpeechEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                }
            } else {
                var error : String = "Hey, dude text cannot be empty!!"
                Toast.makeText(this, "Text can't be empty!!", Toast.LENGTH_SHORT).show()
                textToSpeechEngine.speak(error, TextToSpeech.QUEUE_FLUSH, null)
            }
        }


        }

    private val textToSpeechEngine: TextToSpeech by lazy {
        TextToSpeech(this,
            TextToSpeech.OnInitListener { status ->
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeechEngine.language = Locale.UK
                }
            })
    }


}