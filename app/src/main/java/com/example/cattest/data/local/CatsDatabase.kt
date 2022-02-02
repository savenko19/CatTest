package com.example.cattest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cattest.data.local.entity.FavoriteCatEntity

@Database(
    entities = [FavoriteCatEntity::class],
    version = 1
)
abstract class CatsDatabase : RoomDatabase() {
    abstract fun catsDao(): CatsDao

    companion object {
        const val DATABASE_NAME = "cats_db"
    }
}