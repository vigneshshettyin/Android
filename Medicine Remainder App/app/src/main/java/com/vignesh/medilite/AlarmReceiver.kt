package com.vignesh.medilite

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    private var medicineName : String = ""
    override fun onReceive(context: Context?, intent: Intent?) {

        val i = Intent(context, MainActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0,i, 0)
//        medicineName = pendingIntent.getStringExtra("Medicine_Name").toString()

        Toast.makeText(context, "Time to take medicine!", Toast.LENGTH_SHORT).show()


//        val builder = NotificationCompat.Builder(context!!, "mediLite")
//            .setSmallIcon(R.drawable.ic_pills)
//            .setContentTitle("Medicine Name : ${medicineName}")
//            .setContentText("This is a remainder to take your medicine. Please take it as soon as possible!!")
//            .setAutoCancel(true)
//            .setDefaults(NotificationCompat.DEFAULT_ALL)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
//            .build()

//        val notificationManager = NotificationManagerCompat.from(context)

//        notificationManager.notify(123, builder.build())

    }

}
