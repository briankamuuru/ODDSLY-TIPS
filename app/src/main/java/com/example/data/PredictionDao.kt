package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PredictionDao {

    @Query("SELECT * FROM predictions WHERE category = 'FREE' ORDER BY creationTimestamp DESC")
    fun getFreePredictionsFlow(): Flow<List<PredictionEntity>>

    @Query("SELECT * FROM predictions WHERE category = :category AND isHistorical = 0 ORDER BY creationTimestamp DESC")
    fun getActiveVipPredictionsFlow(category: String): Flow<List<PredictionEntity>>

    @Query("SELECT * FROM predictions WHERE isHistorical = 1 AND historyCategory = :historyCategory ORDER BY resultDeclarationTimestamp DESC")
    fun getVipHistoryPredictionsFlow(historyCategory: String): Flow<List<PredictionEntity>>

    @Query("SELECT * FROM predictions ORDER BY creationTimestamp DESC")
    fun getAllPredictionsFlow(): Flow<List<PredictionEntity>>

    @Query("SELECT * FROM predictions WHERE id = :id LIMIT 1")
    suspend fun getPredictionById(id: String): PredictionEntity?

    @Query("SELECT COUNT(*) FROM predictions")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(predictions: List<PredictionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prediction: PredictionEntity)

    @Update
    suspend fun update(prediction: PredictionEntity)

    @Delete
    suspend fun delete(prediction: PredictionEntity)

    @Query("DELETE FROM predictions")
    suspend fun deleteAll()
}
