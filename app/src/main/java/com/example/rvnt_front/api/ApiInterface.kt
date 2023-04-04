package com.example.rvnt_front.api

import com.example.rvnt_front.models.CarouselDataItem
import com.example.rvnt_front.models.CategoriesDataItem
import com.example.rvnt_front.models.SuggestionsDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("events/popular/5")
    fun getCarouselData(): Call<List<CarouselDataItem>>

    @GET("events/categories")
    fun getCategoriesData(): Call<List<CategoriesDataItem>>

    @GET("events/cities")
    fun getSuggestionData(): Call<List<SuggestionsDataItem>>
}

