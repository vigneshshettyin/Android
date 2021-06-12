package com.vignesh.medilite

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_add_med.*
import java.util.*

class add_med : AppCompatActivity() {

    lateinit var viewModal: MedicineViewModal

    private lateinit var picker : MaterialTimePicker

    private lateinit var calendar: Calendar

    private lateinit var alarmManager: AlarmManager

    private lateinit var pendingIntent: PendingIntent

    var setMedicineName : String = ""

    var currentTime : Long = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_med)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        selectTime.setOnClickListener {
            showTimePicker()
        }

        viewModal = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MedicineViewModal::class.java)


        submitMed.setOnClickListener {
            setMedicineName = name.text.toString().trim()
            currentTime = System.currentTimeMillis()

//            Toast.makeText(this, "${setMedicineName} and ${currentTime}  and ${setMedicineHour}", Toast.LENGTH_SHORT).show()

            viewModal.insertMedicine(Medicine(setMedicineName,calendar.timeInMillis,currentTime))

            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

            val intent = Intent(this, AlarmReceiver::class.java)

            intent.putExtra("Medicine_Name", setMedicineName)

            pendingIntent = PendingIntent.getBroadcast(this, 0,intent, 0)

            Log.d("TAG-1245", pendingIntent.toString())

            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY, pendingIntent
            )

            Toast.makeText(this, "Medicine Timer Set Successfully!!", Toast.LENGTH_SHORT).show()

            val goTOMain = Intent(this, MainActivity::class.java)
            startActivity(goTOMain)
            finish()
        }

    }

    private fun showTimePicker(){
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Time")
            .build()

        picker.show(supportFragmentManager, "mediLite")

        picker.addOnPositiveButtonClickListener{
            if (picker.hour>12){
                selectedTime.text = String.format("%02d", picker.hour-12) + ":" + String.format(
                    "%02d", picker.minute)+ " PM"
            }
            else{
                selectedTime.text = String.format("%02d", picker.hour) + ":" + String.format(
                    "%02d", picker.minute)+ " AM"

            }

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }

    }
}