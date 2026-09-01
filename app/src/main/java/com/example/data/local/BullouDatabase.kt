package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class, CategoryEntity::class, GoalEntity::class], version = 1, exportSchema = false)
abstract class BullouDatabase : RoomDatabase() {
    abstract fun bullouDao(): BullouDao

    companion object {
        @Volatile
        private var INSTANCE: BullouDatabase? = null

        fun getDatabase(context: Context): BullouDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BullouDatabase::class.java,
                    "bullou_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
