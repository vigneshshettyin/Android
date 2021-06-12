package com.vignesh.caller

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun editText(view: View) {
        val editText = findViewById<TextView>(R.id.tvInput) as TextView
        editText.append((view as Button).text)
    }

    fun delNum(view: View) {
        val editText = findViewById<TextView>(R.id.tvInput) as TextView
        val text = editText.text.toString()
        val length = text.length
        editText.text = text.dropLast(1)
    }

    fun onCall(view: View) {
        val editText = findViewById<TextView>(R.id.tvInput) as TextView
        val text = editText.text.toString().trim()
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:"+text)
        startActivity(dialIntent)
    }

    fun createContact(view: View) {
        val editText = findViewById<TextView>(R.id.tvInput) as TextView
        val text = editText.text.toString().trim()
        val createContactIntent = Intent(Intent.ACTION_INSERT)
        createContactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        createContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, text);
        startActivity(createContactIntent)

    }
}