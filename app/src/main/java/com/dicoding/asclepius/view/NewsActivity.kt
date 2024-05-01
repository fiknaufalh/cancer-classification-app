package com.dicoding.asclepius.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.remote.response.NewsResponse
import com.dicoding.asclepius.data.remote.retrofit.ApiConfig
import com.dicoding.asclepius.helper.NewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val recyclerView = findViewById<RecyclerView>(R.id.rvNews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter()

        recyclerView.adapter = newsAdapter

        val backButton = findViewById<CardView>(R.id.backTab)
        backButton.setOnClickListener{ finish() }

        loadNewsData()
    }

    private fun loadNewsData() {
        showLoading(true)
        val client = ApiConfig.getApiService().getNews()
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        newsAdapter.submitList(responseBody.articles)
                    }
                }
                else {
                    if (BuildConfig.DEBUG) Log.d(TAG, "onFailResponse: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                if (BuildConfig.DEBUG) Log.d(TAG, "onFailure: ${t.message}")
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
        private const val TAG = "NewsActivity"
    }
}