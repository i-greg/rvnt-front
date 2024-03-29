package com.example.rvnt_front.api

import com.example.rvnt_front.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    // A function in order to get the 5 most popular events for carousel
    @GET("events/popular/5")
    fun getCarouselData(): Call<List<CarouselDataItem>>

    // A function in order to get categories
    @GET("categories")
    fun getCategoriesData(): Call<List<CategoriesDataItem>>

    // A function in order to get cities for searchbar
    @GET("cities")
    fun getSuggestionData(): Call<List<SuggestionsDataItem>>

    // A function in order to get event's data
    @GET("events/{eventID}")
    fun getDetailEventItem(@Path("eventID") eventID: String): Call<DetailEventItem>

    // get function for categories
    @GET("events/category/{categoryID}")
    fun getCategoryResultsItem(@Path("categoryID") categoryID: String): Call<List<ResultsItem>>


    // get function for searchbar
    @GET("events/city/{cityID}")
    fun getCityResultsItem(@Path("cityID") cityID: String): Call<List<ResultsItem>>
}

