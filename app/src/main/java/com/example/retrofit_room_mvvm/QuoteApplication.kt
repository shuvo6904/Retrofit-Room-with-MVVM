package com.example.retrofit_room_mvvm

import android.app.Application
import com.example.retrofit_room_mvvm.network.ApiInterface
import com.example.retrofit_room_mvvm.network.RetrofitClient
import com.example.retrofit_room_mvvm.repository.QuoteRepository
import com.example.retrofit_room_mvvm.room_db.QuoteDatabase

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {

        val apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(apiInterface, database)

    }
}