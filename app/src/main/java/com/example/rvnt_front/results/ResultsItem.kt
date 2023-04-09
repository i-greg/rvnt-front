package com.example.rvnt_front.results

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResultsItem(

/*
    val _id: String,
    val category: String,
    val city: String,
    val date: String,

    val location: String,
    val price: String,
    val time: String,
    val title: String
*/

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



): Parcelable