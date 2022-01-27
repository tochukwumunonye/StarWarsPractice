package com.tochukwu.starwarspractice.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tochukwu.starwarspractice.data.cache.model.PosterEntity

@Database(entities = [PosterEntity::class], version = 1)
abstract class DisneyDatabase : RoomDatabase() {

    abstract fun disneyDao(): DisneyDao

}
