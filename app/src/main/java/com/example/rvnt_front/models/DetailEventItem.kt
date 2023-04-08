package com.example.rvnt_front.models

import java.util.Dictionary

data class DetailEventItem (
    val _id: String,
    val name: String,
    val time: ArrayList<String>,
    val image: String,
    val date: ArrayList<String>,
    val location: String,
    val city_id: CityItem,
    val category_id: CategoryItem,
    val description: String,
    val tickets_remaining: Int,
    val tickets_total: Int,
    val price: Int

)