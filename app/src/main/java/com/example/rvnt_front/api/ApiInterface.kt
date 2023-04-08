package com.example.rvnt_front.api

import com.example.rvnt_front.models.CarouselDataItem
import com.example.rvnt_front.models.CategoriesDataItem
import com.example.rvnt_front.models.DetailEventItem
import com.example.rvnt_front.models.SuggestionsDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("events/popular/5")
    fun getCarouselData(): Call<List<CarouselDataItem>>

    @GET("categories")
    fun getCategoriesData(): Call<List<CategoriesDataItem>>

    @GET("cities")
    fun getSuggestionData(): Call<List<SuggestionsDataItem>>

    @GET("events/{eventID}")
    fun getDetailEventItem(@Path("eventID") eventID: String): Call<DetailEventItem>

}

