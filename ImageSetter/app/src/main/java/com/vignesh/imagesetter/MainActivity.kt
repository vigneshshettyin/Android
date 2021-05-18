package com.vignesh.imagesetter

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var currentImageUrl : String? = null
    var count : Int = 1
    val scope = MainScope() // could also use an other scope such as viewModelScope if available
    var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadImage()
        startUpdates()


    }

    private fun loadImage(){

        val progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        progressBar.visibility = View.VISIBLE

        count++

        val url = "https://picsum.photos/id/$count/info"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                currentImageUrl = response.getString("download_url")
                val displayImageView = findViewById<ImageView>(R.id.display_image) as ImageView
                Glide.with(this).load(currentImageUrl).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                }).into(displayImageView)
            },
            {
                Toast.makeText(this, "Something Went Wrong!!", Toast.LENGTH_LONG).show()
            })

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    fun setWallpaper(view: View) {

        val display_image = findViewById<ImageView>(R.id.display_image) as ImageView
        val bitmap : Bitmap = display_image.drawable.toBitmap()

        val wallpaperManager : WallpaperManager = WallpaperManager.getInstance(this)
        wallpaperManager.setBitmap(bitmap)

        Toast.makeText(this,"Wallpaer Set", Toast.LENGTH_LONG).show()
    }

    suspend fun setAutoWal (){
        loadImage()
        delay(5000)
        val display_image = findViewById<ImageView>(R.id.display_image) as ImageView
        val bitmap : Bitmap = display_image.drawable.toBitmap()

        val wallpaperManager : WallpaperManager = WallpaperManager.getInstance(this)
        wallpaperManager.setBitmap(bitmap)

        Toast.makeText(this,"Wallpaer Set Autoset (Default 30 Secounds)", Toast.LENGTH_LONG).show()
    }
    
    fun nextWallpaper(view: View) {
        loadImage()
    }

    fun startUpdates() {
        stopUpdates()
        job = scope.launch {
            while(true) {
                setAutoWal() // the function that should be ran every second
                delay(30000)
            }
        }
    }
    fun stopUpdates() {
        job?.cancel()
        job = null
    }



}