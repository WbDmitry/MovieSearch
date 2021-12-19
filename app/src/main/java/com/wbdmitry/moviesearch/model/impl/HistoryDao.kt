package com.wbdmitry.moviesearch.model.impl

import androidx.room.*
import com.wbdmitry.moviesearch.model.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HistoryEntity)
}