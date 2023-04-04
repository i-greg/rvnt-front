package com.example.rvnt_front.api

import android.util.Log
import com.example.rvnt_front.BASE_URL
import com.example.rvnt_front.models.CarouselDataItem
import com.example.rvnt_front.models.CategoriesDataItem
import com.example.rvnt_front.models.DetailEventItem
import com.example.rvnt_front.models.SuggestionsDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

    fun getCarouselData(callback: (List<CarouselDataItem>) -> Unit) {
        apiInterface.getCarouselData().enqueue(object : Callback<List<CarouselDataItem>> {
            override fun onResponse(
                call: Call<List<CarouselDataItem>>,
                response: Response<List<CarouselDataItem>>
            ) {
                if (response.isSuccessful) {
                    val carouselData = response.body()
                    callback(carouselData ?: emptyList())
                } else {
                    Log.e("ApiManager", "Failed to get carousel data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<CarouselDataItem>>, t: Throwable) {
                Log.e("ApiManager", "Failed to get carousel data: ${t.message}")
            }
        })
    }

    fun getCardData(callback: (List<CategoriesDataItem>) -> Unit) {
        apiInterface.getCategoriesData().enqueue(object : Callback<List<CategoriesDataItem>> {
            override fun onResponse(
                call: Call<List<CategoriesDataItem>>,
                response: Response<List<CategoriesDataItem>>
            ) {
                if (response.isSuccessful) {
                    val carouselData = response.body()
                    callback(carouselData ?: emptyList())
                } else {
                    Log.e("ApiManager", "Failed to get carousel data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<CategoriesDataItem>>, t: Throwable) {
                Log.e("ApiManager", "Failed to get carousel data: ${t.message}")
            }
        })
    }

    fun getSuggestionData(callback: (List<SuggestionsDataItem>) -> Unit) {
        apiInterface.getSuggestionData().enqueue(object : Callback<List<SuggestionsDataItem>> {
            override fun onResponse(
                call: Call<List<SuggestionsDataItem>>,
                response: Response<List<SuggestionsDataItem>>
            ) {
                if (response.isSuccessful) {
                    val carouselData = response.body()
                    callback(carouselData ?: emptyList())
                } else {
                    Log.e("ApiManager", "Failed to get carousel data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<SuggestionsDataItem>>, t: Throwable) {
                Log.e("ApiManager", "Failed to get carousel data: ${t.message}")
            }
        })
    }

    // A function in order to get event's detail from DataBase for DetailEventActivity UI
    fun getDetailEventItem(eventID: String, callback: (DetailEventItem) -> Unit) {
        apiInterface.getDetailEventItem(eventID).enqueue(object : Callback<DetailEventItem> {
            override fun onResponse(
                call: Call<DetailEventItem>,
                response: Response<DetailEventItem>
            ) {
                if (response.isSuccessful) {
                    val eventData = response.body()
                    callback(eventData ?: DetailEventItem("","","","","","","",0))
                } else {
                    Log.e("ApiManager", "Failed to get carousel data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailEventItem>, t: Throwable) {
                Log.e("ApiManager", "Failed to get carousel data: ${t.message}")
            }
        })
    }
}