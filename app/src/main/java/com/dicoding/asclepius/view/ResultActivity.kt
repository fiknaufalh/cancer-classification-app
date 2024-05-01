package com.dicoding.asclepius.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val resultText = intent.getStringExtra("RESULT_TEXT")
        val inferenceTimeText = intent.getStringExtra("INFERENCE_TIME_TEXT")
        val imageUri = intent.data

        val resultImageBind = findViewById<ImageView>(R.id.result_image)
        val resultTextBind = findViewById<TextView>(R.id.result_text)
        val inferenceTimeTextBind = findViewById<TextView>(R.id.inference_time_text)

        resultImageBind.setImageURI(imageUri)
        resultTextBind.text = resultText
        inferenceTimeTextBind.text = inferenceTimeText

    }


}