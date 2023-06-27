package com.example.findactivity.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.findactivity.common.StatusBored
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.io.Serializable


@Entity(tableName = "ACTIVITY_TABLE")
data class ActEntity(
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
) : Serializable{
}