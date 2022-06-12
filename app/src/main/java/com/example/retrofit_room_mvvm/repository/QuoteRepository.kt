package com.example.retrofit_room_mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_room_mvvm.model.QuoteList
import com.example.retrofit_room_mvvm.network.ApiInterface
import com.example.retrofit_room_mvvm.room_db.QuoteDatabase

class QuoteRepository(private val apiInterface : ApiInterface, private val quoteDatabase: QuoteDatabase) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quote : LiveData<QuoteList>
    get() = quoteLiveData

    suspend fun getQuote(page : Int){
        val result = apiInterface.getQuotes(page)
        if (result?.body() != null){
            quoteDatabase.quoteDao().addQuote(result.body()!!.results)
            quoteLiveData.postValue(result.body())
        }
    }
}