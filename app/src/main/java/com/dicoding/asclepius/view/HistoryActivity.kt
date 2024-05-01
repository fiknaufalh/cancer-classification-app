package com.dicoding.asclepius.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.room.CancerAnalysisDatabase
import com.dicoding.asclepius.helper.CancerAnalysisAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private lateinit var cancerAnalysisAdapter: CancerAnalysisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView = findViewById<RecyclerView>(R.id.rvAnalysis)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cancerAnalysisAdapter = CancerAnalysisAdapter()

        recyclerView.adapter = cancerAnalysisAdapter

        val backButton = findViewById<CardView>(R.id.backTab)
        backButton.setOnClickListener{ finish() }

        loadData()
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            val predictions =
                CancerAnalysisDatabase.getDatabase(application)
                    .cancerAnalysisDao().getAllPredictions()
            withContext(Dispatchers.Main) {
                cancerAnalysisAdapter.submitList(predictions)
            }
        }
    }
}