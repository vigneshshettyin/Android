package com.vignesh.newsdesk

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), NewsItemClicked {


    private lateinit var mAdapter: NewsListAdapter
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val recyclerView =findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView


            recyclerView.layoutManager = LinearLayoutManager(this)
            fetchData()
            mAdapter = NewsListAdapter(this)
            recyclerView.adapter = mAdapter


    }

    private fun fetchData(){
        val url = "https://api.vigneshin.ml/news/in"
        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { val newsJsonArray = it.getJSONArray("articles")
                                val newsArray = ArrayList<News>()

                                    for (i in 0 until newsJsonArray.length()){
                                        val newsJsonObject =  newsJsonArray.getJSONObject(i)
                                        val news = News(
                                            newsJsonObject.getString("title"),
                                            newsJsonObject.getString("author"),
                                            newsJsonObject.getString("url"),
                                            newsJsonObject.getString("urlToImage"),

                                        )
                                        newsArray.add(news)
                                    }
                mAdapter.updatedNews(newsArray)

            },
            {
                Toast.makeText(this, "Something Went Wrong!!", Toast.LENGTH_LONG).show()
            })

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder()
        val CustomTabsIntent = builder.build()
        CustomTabsIntent.launchUrl(this, Uri.parse(item.url));

    }
}