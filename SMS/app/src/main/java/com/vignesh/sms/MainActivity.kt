package com.vignesh.sms

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),
                111
            )
        } else {
            receiveMessage()
        }

        receiveMessage()

        val submitButton = findViewById<Button>(R.id.submit) as Button

        submitButton.setOnClickListener {
            var sms = SmsManager.getDefault()
            val phone = findViewById<EditText>(R.id.phone) as EditText
            val text = findViewById<EditText>(R.id.message) as EditText
            sms.sendTextMessage(phone.text.toString(), "ME", text.text.toString(), null, null)
            Toast.makeText(this, "Message Sent!!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            receiveMessage()
        }
    }


    private fun receiveMessage() {

        val br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                        Toast.makeText(
                            applicationContext,
                            "Message Received!!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val phone = findViewById<EditText>(R.id.phone) as EditText
                        val text = findViewById<EditText>(R.id.message) as EditText
                        phone.setText(sms.originatingAddress.toString())
                        text.setText(sms.displayMessageBody.toString())
                    }
                }
            }

        }
        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }
}