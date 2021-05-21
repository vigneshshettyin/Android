package com.vignesh.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_logout.*

class logout_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val name = intent.getStringExtra(Constants.USER_NAME)
        val email = intent.getStringExtra(Constants.USER_EMAIL)
        val password = intent.getStringExtra(Constants.USER_PASSWORD)

        displayName.text = "Name : $name"
        displayEmail.text = "Email : $email"
        displayPassword.text = "Password : $password"


        btn_logout.setOnClickListener {
            FancyToast.makeText(this, "Login Credentials Rested!!", FancyToast.LENGTH_LONG, FancyToast.WARNING,false).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish() }
    }
}