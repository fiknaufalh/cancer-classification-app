package com.dicoding.asclepius.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.entity.CancerAnalysis

class CancerAnalysisAdapter: ListAdapter<CancerAnalysis, CancerAnalysisAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_cancer_analysis, parent, false)
    ) {
        private val imageSkin: ImageView = itemView.findViewById(R.id.ivSkin)
        private val resultText: TextView = itemView.findViewById(R.id.tvResult)
        private val inferenceTimeText: TextView = itemView.findViewById(R.id.tvInferenceTime)
        private val timeStampText: TextView = itemView.findViewById(R.id.tvTimeStamp)

        fun bind(cancerAnalysis: CancerAnalysis) {
            imageSkin.setImageURI(cancerAnalysis.imageUri)
            resultText.text = cancerAnalysis.result
            inferenceTimeText.text = cancerAnalysis.inferenceTime
            timeStampText.text = DateConverter.longToDatetime(cancerAnalysis.timeStamp).replace(" ", "\n")
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CancerAnalysis>() {
            override fun areItemsTheSame(oldItem: CancerAnalysis, newItem: CancerAnalysis): Boolean {
                return oldItem.timeStamp == newItem.timeStamp
            }

            override fun areContentsTheSame(oldItem: CancerAnalysis, newItem: CancerAnalysis): Boolean {
                return oldItem == newItem
            }
        }
    }
}