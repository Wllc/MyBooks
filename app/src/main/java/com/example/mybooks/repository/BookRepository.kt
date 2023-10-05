package com.example.mybooks.repository

import com.example.mybooks.dao.BookDao
import com.example.mybooks.model.Book

class BookRepository(private val bookDao: BookDao) {

    fun getAll(): List<Book> {
        return bookDao.getAll()
    }

    fun addLivro(book: Book) {
        bookDao.insert(book)
    }
}