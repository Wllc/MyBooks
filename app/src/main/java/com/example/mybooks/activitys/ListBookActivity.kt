package com.example.mybooks.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.R
import com.example.mybooks.databinding.ActivityListBooksBinding
import com.example.mybooks.viewModel.BookViewModel

class ListBookActivity : AppCompatActivity() {
    lateinit var binding : ActivityListBooksBinding
    lateinit var viewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_books)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_books)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.book.observe(this, Observer {
            binding.textViewTitle.text = it.title
            binding.textViewAutor.text = it.author
            binding.textViewAno.text = it.year.toString()
            binding.textViewNota.text = it.note.toString()
        })

        viewModel.showBook()

        binding.buttonAnterior.setOnClickListener {
            viewModel.previousBook()
        }

        binding.buttonProximo.setOnClickListener {
            viewModel.nextBook()
        }

    }
}