package com.dicoding.asclepius.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dicoding.asclepius.data.local.entity.CancerAnalysis

@Dao
interface CancerAnalysisDao {
    @Insert
    suspend fun insert(analysis: CancerAnalysis)

    @Query("SELECT * FROM cancer_analysis")
    suspend fun getAllPredictions(): List<CancerAnalysis>
}