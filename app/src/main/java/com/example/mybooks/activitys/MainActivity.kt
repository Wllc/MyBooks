package com.example.mybooks.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mybooks.R
import com.example.mybooks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonCadastrar.setOnClickListener {
            val intent = Intent(this, RegisterBookActivity::class.java)
            startActivity(intent)
        }

        binding.buttonListar.setOnClickListener {
            val intent = Intent(this, ListBookActivity::class.java)
            startActivity(intent)

        }

    }
}