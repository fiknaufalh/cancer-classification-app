package com.dicoding.asclepius.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dicoding.asclepius.data.local.entity.CancerAnalysis
import com.dicoding.asclepius.helper.UriTypeConverter

@Database(entities = [CancerAnalysis::class], version = 1, exportSchema = false)
@TypeConverters(UriTypeConverter::class)
abstract class CancerAnalysisDatabase : RoomDatabase() {
    abstract fun cancerAnalysisDao(): CancerAnalysisDao

    companion object {
        @Volatile
        private var INSTANCE: CancerAnalysisDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): CancerAnalysisDatabase {
            if (INSTANCE == null) {
                synchronized(CancerAnalysisDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CancerAnalysisDatabase::class.java, "cancer_database")
                        .build()
                }
            }

            return INSTANCE as CancerAnalysisDatabase
        }
    }
}