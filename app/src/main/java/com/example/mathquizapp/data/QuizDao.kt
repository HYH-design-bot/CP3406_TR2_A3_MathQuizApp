package com.example.mathquizapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Insert
    suspend fun insertResult(result: QuizResult)

    @Query("SELECT * FROM quiz_results ORDER BY timestamp DESC")
    fun getAllResults(): Flow<List<QuizResult>>

    @Query("DELETE FROM quiz_results")
    suspend fun clearAllHistory()

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun saveSettings(settings: AppSettings)

    @Query("SELECT * FROM app_settings WHERE id = 1 LIMIT 1")
    fun getSettings(): kotlinx.coroutines.flow.Flow<AppSettings?>
}