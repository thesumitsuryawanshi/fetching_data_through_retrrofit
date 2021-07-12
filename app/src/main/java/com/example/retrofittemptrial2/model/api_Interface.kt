package com.example.retrofittemptrial2.model

import retrofit2.Call
import retrofit2.http.GET


interface api_Interface {

    @GET("posts")
    fun getdata():Call<List<mydataitems>>

}