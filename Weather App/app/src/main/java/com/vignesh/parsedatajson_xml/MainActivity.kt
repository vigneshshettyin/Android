package com.vignesh.parsedatajson_xml
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity() {

    var city : String = "Pune"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        progressBar.visibility = View.GONE
    }


    fun XMLParse(view: View) {

        val progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        progressBar.visibility = View.VISIBLE

        val xml_data = assets.open("data.xml")
        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()

        parser.setInput(xml_data, null)

        var event = parser.eventType
        while (event!=XmlPullParser.END_DOCUMENT){
            var tag_name = parser.name
            when(event){
                XmlPullParser.END_TAG->{
                    if (tag_name=="city"){
                        val cityName = findViewById<TextView>(R.id.cityName) as TextView
                        cityName.text = "City : ${parser.getAttributeValue(0)}"
                    }
                    if (tag_name=="temperature_max"){
                        val temp_max_display = findViewById<TextView>(R.id.maxTemp) as TextView
                        temp_max_display.text = "Max Temp : ${parser.getAttributeValue(0)}°F"
                    }
                    if (tag_name=="temperature_min"){
                        val temp_min_display = findViewById<TextView>(R.id.minTemp) as TextView
                        temp_min_display.text = "Min Temp : ${parser.getAttributeValue(0)}°F"
                    }
                    if (tag_name=="humidity"){
                        val humidityDisplay = findViewById<TextView>(R.id.humidity) as TextView
                        humidityDisplay.text = "Humidity : ${parser.getAttributeValue(0)}${parser.getAttributeValue(1)}"
                    }
                    if (tag_name=="coord"){
                        val lat_lon = findViewById<TextView>(R.id.pressure) as TextView
                        lat_lon.text = "Location : ${parser.getAttributeValue(0)}° : ${parser.getAttributeValue(1)}°"
                    }
                    if (tag_name=="date"){
                        val DateDisplay = findViewById<TextView>(R.id.date) as TextView
                        DateDisplay.text = "${parser.getAttributeValue(0)}"
                        progressBar.visibility = View.GONE
                    }
                }
            }

            event = parser.next()
        }

    }

        fun JSONParse(view: View){

            val progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
            progressBar.visibility = View.VISIBLE

        val getMyCity = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.city) as androidx.appcompat.widget.AppCompatEditText
        city = getMyCity.text.toString()
        if(getMyCity.text.toString().isEmpty()){
            Toast.makeText(this, "Please enter a city!!", Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.GONE
        }else{
            val url = "https://api.vigneshin.ml/v2/kotlin/$city"
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    val date = response.getString("date")
                    val lat = response.getString("lat")
                    val lon = response.getString("lon")
                    val temp_max = response.getString("temp_max")
                    val temp_min = response.getString("temp_max")
                    val humidity = response.getString("humidity")

                    val DateDisplay = findViewById<TextView>(R.id.date) as TextView
                    val lat_lon = findViewById<TextView>(R.id.pressure) as TextView
                    val humidityDisplay = findViewById<TextView>(R.id.humidity) as TextView
                    val cityName = findViewById<TextView>(R.id.cityName) as TextView
                    val temp_max_display = findViewById<TextView>(R.id.maxTemp) as TextView
                    val temp_min_display = findViewById<TextView>(R.id.minTemp) as TextView

                    progressBar.visibility = View.GONE

                    DateDisplay.text = "$date"
                    cityName.text = "City : $city"
                    lat_lon.text = "Location : $lat° : $lon°"
                    humidityDisplay.text = "Humidity : $humidity%"
                    temp_max_display.text = "Max Temp : $temp_max°F"
                    temp_min_display.text = "Min Temp : $temp_min°F"
                },
                {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Something Went Wrong!!", Toast.LENGTH_LONG).show()
                })
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }

    }
}