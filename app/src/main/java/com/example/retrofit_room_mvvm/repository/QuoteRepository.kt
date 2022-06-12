package com.example.retrofit_room_mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_room_mvvm.model.QuoteList
import com.example.retrofit_room_mvvm.network.ApiInterface
import com.example.retrofit_room_mvvm.room_db.QuoteDatabase
import com.example.retrofit_room_mvvm.util.NetworkUtil

class QuoteRepository(
    private val apiInterface: ApiInterface,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quote : LiveData<QuoteList>
    get() = quoteLiveData

    suspend fun getQuote(page : Int){

        if(NetworkUtil.isInternetAvailable(applicationContext)){

            val result = apiInterface.getQuotes(page)
            if (result?.body() != null){
                quoteDatabase.quoteDao().addQuote(result.body()!!.results)
                quoteLiveData.postValue(result.body())
            }

        }
        else{

            val quote = quoteDatabase.quoteDao().getQuote()
            val quoteList = QuoteList(1,1,1,quote, 1, 1)
            quoteLiveData.postValue(quoteList)

        }


    }
}