package com.example.mybooks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.mybooks.dao.BookDao
import com.example.mybooks.model.Book

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "book_db"
                ).build()
                INSTANCE = instance
                return instance
            }


        }
    }
}