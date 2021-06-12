package com.vignesh.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1

    private var mQuestionsList : ArrayList<Question>? = null

    private var mSelectedPosition : Int = 0

    private var mCorrectAnswers : Int = 0

    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        val tv_option_one = findViewById<TextView>(R.id.tv_option_one) as TextView
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two) as TextView
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three) as TextView
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four) as TextView

        val btn_submit = findViewById<Button>(R.id.btn_submit) as Button


        mQuestionsList = Constants.getQuestions()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }


    private fun defaultOptionsView(){
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one) as TextView
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two) as TextView
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three) as TextView
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four) as TextView

        val options = ArrayList<TextView>()

        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }
    }

    private fun setQuestion(){

        val progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        val tv_progress = findViewById<TextView>(R.id.tv_progress) as TextView
        val tv_question = findViewById<TextView>(R.id.tv_question) as TextView
        val tv_image = findViewById<ImageView>(R.id.tv_image) as ImageView
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one) as TextView
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two) as TextView
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three) as TextView
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four) as TextView
        val btn_submit = findViewById<Button>(R.id.btn_submit) as Button


        val question = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if(mCurrentPosition==mQuestionsList!!.size){
            btn_submit.text = "Finish"
        }else{
            btn_submit.text = "Submit"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition"+"/"+progressBar.max
        tv_question.text = question!!.question
        tv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour

    }

    override fun onClick(v: View?) {
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one) as TextView
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two) as TextView
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three) as TextView
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four) as TextView
        val btn_submit = findViewById<Button>(R.id.btn_submit) as Button

        when(v?.id){
            R.id.tv_option_one->{
                selectedOptionView(tv_option_one,1 )
            }R.id.tv_option_two->{
                selectedOptionView(tv_option_two,2 )
            }R.id.tv_option_three->{
                selectedOptionView(tv_option_three,3)
            }R.id.tv_option_four->{
                selectedOptionView(tv_option_four,4 )
            }R.id.btn_submit->{
                    if(mSelectedPosition==0){
                        mCurrentPosition++
                        when{
                            mCurrentPosition<=mQuestionsList!!.size->{
                                setQuestion()
                            }else->{
                            val intent  = Intent(this, activity_result::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                            }
                        }

                    }
            else{
                val question = mQuestionsList?.get(mCurrentPosition-1)
                        if(question!!.correctAnswer!=mSelectedPosition){
                            answerView(mSelectedPosition, R.drawable.wrong_option_border_bg)
                        }else{
                            mCorrectAnswers++
                        }
                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                        if(mCurrentPosition == mQuestionsList!!.size){
                            btn_submit.text = "Finish"
                        }
                        else{
                            btn_submit.text = "Go to Next Question"
                        }
                        mSelectedPosition = 0

                    }

        }
        }

    }

    private fun answerView(answer : Int, drawableView : Int){
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one) as TextView
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two) as TextView
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three) as TextView
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four) as TextView
        when(answer){
            1-> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            } 2-> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            } 3-> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            } 4-> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum : Int){
        defaultOptionsView()
        mSelectedPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }


}