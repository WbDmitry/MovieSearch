package com.wbdmitry.moviesearch.model.impl

import androidx.room.Room
import androidx.room.RoomDatabase
import com.wbdmitry.moviesearch.App
import com.wbdmitry.moviesearch.model.entity.HistoryEntity

@androidx.room.Database(
    entities = [
        HistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        private const val DB_NAME = "movie_search.db"
        val db: Database by lazy {
            Room.databaseBuilder(
                App.appInstance,
                Database::class.java,
                DB_NAME
            ).build()
        }
    }
}