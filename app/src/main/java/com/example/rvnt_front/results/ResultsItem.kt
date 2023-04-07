package com.example.rvnt_front.results

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResultsItem(

    val _id: String,
    val category: String,
    val city: String,
    val date: String,
    val id: Int,
    val location: String,
    val price: String,
    val time: String,
    val title: String


): Parcelable