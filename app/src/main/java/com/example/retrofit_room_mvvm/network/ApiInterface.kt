package com.example.retrofit_room_mvvm.network

import com.example.retrofit_room_mvvm.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page : Int) : Response<QuoteList>
}