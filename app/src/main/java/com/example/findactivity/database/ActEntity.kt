package com.example.findactivity.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.findactivity.common.StatusBored
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json


@Entity(tableName = "BORED_ACTIVITY")
data class BoredActEntity(
    @PrimaryKey val id: Int? = 0,
    @Json(name = "activity")
    @SerializedName("activity")
    val boredActivity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val key: String,
    val accessibility: String,
    val status: StatusBored? = StatusBored.NOT_SAVED,
){
}