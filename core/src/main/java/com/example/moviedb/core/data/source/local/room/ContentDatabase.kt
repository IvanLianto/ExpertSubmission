package com.example.moviedb.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedb.core.data.source.local.entity.DataEntity

@Database(entities = [DataEntity::class], version = 1, exportSchema = false)
abstract class ContentDatabase : RoomDatabase() {
    abstract fun getDao(): ContentDao
}