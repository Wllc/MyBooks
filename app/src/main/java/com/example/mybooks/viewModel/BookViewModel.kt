package com.example.mybooks.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.model.Book
import com.example.mybooks.repository.BookRepository
import java.lang.Exception

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : BookRepository

    private var _books = MutableLiveData<List<Book>>()
    var books : LiveData<List<Book>> = _books

    private var _bookRegisterEvent = MutableLiveData<Boolean>()
    var bookRegisterEvent : LiveData<Boolean> = _bookRegisterEvent



    private val _book = MutableLiveData<Book>()
    val book : LiveData<Book>
        get() = _book


    init {
        val livroDao = AppDatabase.getDatabase(application).bookDao()
        repository = BookRepository(livroDao)
        _book.value = Book("", "", 0.0f, 0)
        getAll()
    }


    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            _books.postValue(repository.getAll())
        }

    }
    private var livroIndex = 0

    fun showBook() {
        if (_books.value.isNullOrEmpty()) return
        _book.value = _books.value!![livroIndex]
    }

    fun nextBook() {
        if (_books.value.isNullOrEmpty()) return
        livroIndex = (livroIndex + 1) % _books.value!!.size
        _book.value = _books.value!![livroIndex]
    }

    fun previousBook () {
        if (_books.value.isNullOrEmpty()) return

        livroIndex = if (livroIndex > 0) livroIndex - 1 else _books.value!!.size - 1
        _book.value = _books.value!![livroIndex]
    }


    fun addBook(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.addLivro(book.value!!)
                _bookRegisterEvent.postValue(true)
            }catch (e: Exception){
                _bookRegisterEvent.postValue(false)
            }

        }
    }

}