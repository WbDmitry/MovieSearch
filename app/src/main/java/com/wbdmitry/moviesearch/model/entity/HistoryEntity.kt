package com.wbdmitry.moviesearch.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val movie_id: Int,
    val time: String,
    val movie_title: String
)