package com.ojanbelajar.moviekatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ojanbelajar.moviekatalogue.data.source.local.entity.MovieEntity
import com.ojanbelajar.moviekatalogue.data.source.local.entity.SeriesEntity

@Database(entities = [MovieEntity::class, SeriesEntity::class], version = 3, exportSchema = false)
abstract class ContentDatabase: RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {

        @Volatile
        private var INSTANCE: ContentDatabase? = null

        fun getInstance(context: Context): ContentDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ContentDatabase::class.java,
                    "content.db"
                ).fallbackToDestructiveMigration()
                    .build().apply {
                    INSTANCE = this
                }
            }
    }

}