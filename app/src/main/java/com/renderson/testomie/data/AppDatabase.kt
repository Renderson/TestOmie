package com.renderson.testomie.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.renderson.testomie.model.Products

@Database(entities = [Products::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun omieDao(): OmieDao
}