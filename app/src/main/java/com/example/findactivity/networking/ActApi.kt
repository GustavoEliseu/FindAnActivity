package com.example.findactivity.networking

import com.example.findactivity.data.model.ActEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ActApi {
    @GET("activity/")//key! so the request stays as key != myKey
    suspend fun getRandomActivity(@Query("key!")key: String? = null) : Response<ActEntity>
}