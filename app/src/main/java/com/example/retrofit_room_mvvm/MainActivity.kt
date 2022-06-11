package com.example.retrofit_room_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_room_mvvm.network.ApiInterface
import com.example.retrofit_room_mvvm.network.RetrofitClient
import com.example.retrofit_room_mvvm.repository.QuoteRepository
import com.example.retrofit_room_mvvm.viewmodel.MainViewModel
import com.example.retrofit_room_mvvm.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)

        val repository = QuoteRepository(apiInterface)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quote.observe(this, Observer {
            Log.d("SHUVO", it.results.toString())
        })

    }
}