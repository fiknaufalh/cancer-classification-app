package com.dicoding.asclepius.data.local.entity

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "cancer_analysis")
@Parcelize
data class CancerAnalysis(
    @PrimaryKey(autoGenerate = false)
    var timeStamp: Long = System.currentTimeMillis(),
    var imageUri: Uri,
    var result: String,
    var inferenceTime: String
) : Parcelable