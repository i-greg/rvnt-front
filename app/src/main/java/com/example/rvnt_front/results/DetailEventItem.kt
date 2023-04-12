package com.example.rvnt_front.results

import android.os.Parcelable
import com.example.rvnt_front.models.CategoryItem
import com.example.rvnt_front.models.CityItem
import kotlinx.android.parcel.Parcelize


//@Parcelize
data class DetailEventItem(

    /*
    val _id: String,
    val category: String,
    val city: String,
    val date: String,
    val id: Int,
    val location: String,
    val price: String,
    val time: String,
    val title: String


     */
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




)//: Parcelable