package com.example.rvnt_front.results


import com.example.rvnt_front.ResultsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    // We build an instance of api and we use it to make
    // our api calls


    //.baseUrl("https://jsonplaceholder.typicode.com")

    // const val BASE_URL = "https://rvnt-api.onrender.com/"

    // .baseUrl("https://mockend.com")

  /*
    val api: ResultsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://rvnt-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ResultsApi::class.java)
    }

   */
}