package com.vignesh.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.btn_signup
import kotlinx.android.synthetic.main.activity_main.btn_login

class MainActivity : AppCompatActivity() {

    private var loginCount = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv_name = intent.getStringExtra(Constants.USER_NAME)
        val tv_email = intent.getStringExtra(Constants.USER_EMAIL)
        val tv_password = intent.getStringExtra(Constants.USER_PASSWORD)

//        println("Email is " +tv_email)
//        println("Name  is " +tv_name)
//        println("Password is " +tv_password)

        val email = findViewById<TextView>(R.id.et_email) as TextView
        val password = findViewById<TextView>(R.id.et_password) as TextView

        btn_login.setOnClickListener {
            if (loginCount>3){
                FancyToast.makeText(this, "Your account has been locked!!", FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show()
            }
            else if((email.text.toString().isEmpty()) || (password.text.toString().isEmpty())){
                FancyToast.makeText(this, "Enter all the fields!!", FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show()
            }else if (email.text.toString()==tv_email && password.text.toString()==tv_password){
                FancyToast.makeText(this, "Hey, $tv_name Login Success!!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false).show()
                val intent = Intent(this, logout_activity::class.java)
                intent.putExtra(Constants.USER_NAME, tv_name.toString())
                intent.putExtra(Constants.USER_EMAIL, email.text.toString())
                intent.putExtra(Constants.USER_PASSWORD, password.text.toString())
                startActivity(intent)
                finish()
            }else{
                FancyToast.makeText(this, "Invalid Credentials!!", FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show()
                loginCount++
            }
        }

        btn_signup.setOnClickListener{
            startActivity(Intent(this, register_activity::class.java))
            finish()}
    }
}