package com.adidas.sports.goal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GoalEntity::class],
        version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun goalDao(): GoalDao
}

private lateinit var INSTANCE: AppDatabase

fun getAppDataBase(context: Context): AppDatabase {

    synchronized(AppDatabase::class){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "goalDB")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
    return INSTANCE
}