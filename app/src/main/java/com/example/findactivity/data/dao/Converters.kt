package com.example.findactivity.data.dao

import androidx.room.TypeConverter
import com.example.findactivity.data.model.ActEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromActEntityToJson(list: ActEntity?): String {
        return list?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToActEntity(jsonList: String): ActEntity {
        val listType = object : TypeToken<ActEntity>() {}.type
        return gson.fromJson(jsonList, listType)
    }

}