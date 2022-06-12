package com.example.retrofit_room_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_room_mvvm.viewmodel.MainViewModel
import com.example.retrofit_room_mvvm.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quote.observe(this, Observer {
            //Log.d("SHUVO", it.results.toString())
            Toast.makeText(this, it.results.size.toString(), Toast.LENGTH_LONG).show()
        })

    }
}