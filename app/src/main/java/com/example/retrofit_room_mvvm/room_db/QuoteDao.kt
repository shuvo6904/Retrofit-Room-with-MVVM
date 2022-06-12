package com.example.retrofit_room_mvvm.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofit_room_mvvm.model.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuote(quote : List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuote() : List<Result>

}