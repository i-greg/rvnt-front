package com.example.rvnt_front.models

data class DetailEventItem (
    val _id: String,
    val name: String,
    val image: String,
    val date: String,
    val location: String,
    val category: String,
    val description: String,
    val tickets_remaining: Int
)