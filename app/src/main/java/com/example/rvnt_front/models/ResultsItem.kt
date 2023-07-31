package com.example.rvnt_front.models

// A class in order to get event's details for ResultsUI
data class ResultsItem (
    val _id: String,
    val name: String,
    val time: ArrayList<String>,
    val image: String,
    val date: ArrayList<String>,
    val location: String,
    val city_id: String,
    val category_id: String,
    val description: String,
    val tickets_remaining: Int,
    val tickets_total: Int,
    val price: Int
    )

