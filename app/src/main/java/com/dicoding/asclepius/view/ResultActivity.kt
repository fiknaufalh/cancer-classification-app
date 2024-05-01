package com.dicoding.asclepius.view

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.entity.CancerAnalysis
import com.dicoding.asclepius.data.local.room.CancerAnalysisDatabase
import com.dicoding.asclepius.databinding.ActivityResultBinding
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var _imageUri: Uri
    private lateinit var _result: String
    private lateinit var _inferenceTime: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        setAnalysisVar(
            intent.data,
            intent.getStringExtra("RESULT_TEXT")!!,
            intent.getStringExtra("INFERENCE_TIME_TEXT")!!
        )

        val resultImageBind = findViewById<ImageView>(R.id.result_image)
        val resultTextBind = findViewById<TextView>(R.id.result_text)
        val inferenceTimeTextBind = findViewById<TextView>(R.id.inference_time_text)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val backButton = findViewById<CardView>(R.id.backTab)

        resultImageBind.setImageURI(_imageUri)
        resultTextBind.text = _result
        inferenceTimeTextBind.text = _inferenceTime

        saveButton.setOnClickListener {
            lifecycleScope.launch {
                saveAnalysis()
            }
        }

        backButton.setOnClickListener{ finish() }
    }

    private fun setAnalysisVar(imageUri: Uri?, result: String, inferenceTime: String) {
        _imageUri = imageUri!!
        _result = result
        _inferenceTime = inferenceTime
    }

    private suspend fun saveAnalysis() {
        val cancerAnalysisDao = CancerAnalysisDatabase.getDatabase(application).cancerAnalysisDao()
        val timeStamp = System.currentTimeMillis()
        val cancerAnalysis = CancerAnalysis(timeStamp, _imageUri, _result, _inferenceTime)
        cancerAnalysisDao.insert(cancerAnalysis)

        Toast.makeText(this, "Success to save the result", Toast.LENGTH_SHORT).show()
    }
}