package com.vignesh.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

var lastNumeric : Boolean = false
var lastDot : Boolean = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideDefaultUi()
    }



    fun onDigit(view : View){
        val tvInput = findViewById<TextView>(R.id.tvInput) as TextView
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun OnClear(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput) as TextView
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput) as TextView
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun removeZero(result : String) : String {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length-2)
        return value
    }

    fun onOperator(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput) as TextView
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view: View){
        val tvInput = findViewById<TextView>(R.id.tvInput) as TextView
        if(lastNumeric){
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text=removeZero((one.toDouble() - two.toDouble()).toString())
                }

                else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text=removeZero((one.toDouble() + two.toDouble()).toString())
                }

                else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text=removeZero((one.toDouble() / two.toDouble()).toString())
                }

                else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text=removeZero((one.toDouble() * two.toDouble()).toString())
                }

            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }

    private fun isOperatorAdded(value : String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    private fun hideDefaultUi(){
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

}