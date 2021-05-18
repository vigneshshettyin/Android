package com.vignesh.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.btn_login
import kotlinx.android.synthetic.main.activity_main.btn_signup
import kotlinx.android.synthetic.main.activity_register.*

class register_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

            val email = findViewById<TextView>(R.id.et_email) as TextView
            val password = findViewById<TextView>(R.id.et_password) as TextView

            btn_signup.setOnClickListener {
            if((et_name.text.toString().isEmpty()) && (email.text.toString().isEmpty()) || (password.text.toString().isEmpty())){
                FancyToast.makeText(this, "Enter all the fields!!", FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show()
            }
            else{
                FancyToast.makeText(this, "Registration Success!!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,false);
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
                intent.putExtra(Constants.USER_EMAIL, email.text.toString())
                intent.putExtra(Constants.USER_PASSWORD, password.text.toString())
                startActivity(intent)
            }
        }

        btn_login.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}