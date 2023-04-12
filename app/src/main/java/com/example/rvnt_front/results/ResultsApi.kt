package com.example.rvnt_front

import retrofit2.Response
import retrofit2.http.GET

interface ResultsApi {


    @GET("/kotsak1/demo/events")
   // @GET("/posts")
    // we add "suspend" in order to run a coroutine and run asychronous
    suspend fun getResults(): Response<List<com.example.rvnt_front.results.DetailEventItem>>



}