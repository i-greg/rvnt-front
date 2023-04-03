package com.example.rvnt_front

import com.example.rvnt_front.models.TestDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getTestData(): Call<List<TestDataItem>>
}