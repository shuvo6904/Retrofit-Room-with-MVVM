package com.example.retrofit_room_mvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_room_mvvm.model.QuoteList
import com.example.retrofit_room_mvvm.network.ApiInterface

class QuoteRepository(private val apiInterface : ApiInterface) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quote : LiveData<QuoteList>
    get() = quoteLiveData

    suspend fun getQuote(page : Int){
        val result = apiInterface.getQuotes(page)
        if (result?.body() != null){
            quoteLiveData.postValue(result.body())
        }
    }
}