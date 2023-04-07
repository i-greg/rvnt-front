package com.example.rvnt_front.models

data class DetailEventItem (
    val _id: String,
    val name: String,
    val time: String,
    val image: String,
    val date: String,
    val location: String,
    val city_id: String,
    val category_id: String,
    val description: String,
    val tickets_remaining: Int,
    val tickets_total: Int,
    val price: Int

)