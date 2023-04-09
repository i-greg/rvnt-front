package com.example.rvnt_front.api

import android.util.Log
import com.example.rvnt_front.BASE_URL
import com.example.rvnt_front.models.CarouselDataItem
import com.example.rvnt_front.models.CategoriesDataItem
import com.example.rvnt_front.models.SuggestionsDataItem
import com.example.rvnt_front.results.ResultsItem
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

    // 7/4/23 function for results activity added..
    fun getResultsData(callback: (List<ResultsItem>) -> Unit) {
        apiInterface.getResultsData().enqueue(object : Callback<List<ResultsItem>> {
            override fun onResponse(
                call: Call<List<ResultsItem>>,
                response: Response<List<ResultsItem>>
            ) {
                if (response.isSuccessful) {
                    val resultsData = response.body()
                    callback(resultsData ?: emptyList())
                } else {
                    Log.e("ApiManager", "Failed to get results data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResultsItem>>, t: Throwable) {
                Log.e("ApiManager", "Failed to get results data: ${t.message}")
            }
        })
    }

}