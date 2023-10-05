package com.example.mybooks.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mybooks.model.Book

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)

    @Query("SELECT * FROM Book")
    fun getAll(): List<Book>

}
