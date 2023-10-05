package com.example.mybooks.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.R
import com.example.mybooks.databinding.ActivityRegisterBookBinding
import com.example.mybooks.viewModel.BookViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterBookActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBookBinding
    lateinit var viewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_book)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_book)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.books.observe(this, Observer {
            Log.i("teste", it.toString())
        })
        viewModel.bookRegisterEvent.observe(this, Observer {
            if (it){
                Snackbar.make(binding.btnSalvar, "Livro Cadastrado", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.btnSalvar, "Erro ao Cadastrar", Snackbar.LENGTH_SHORT).show()
            }
        })

        binding.btnCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        binding.btnSalvar.setOnClickListener {
            viewModel.addBook()
        }
    }
}