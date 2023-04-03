package com.example.rvnt_front.results


import com.example.rvnt_front.ResultsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    // We build an instance of api and we use it to make
    // our api calls


    //.baseUrl("https://jsonplaceholder.typicode.com")

    val api: ResultsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://mockend.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ResultsApi::class.java)
    }
}